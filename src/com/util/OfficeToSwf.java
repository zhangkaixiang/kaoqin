package com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.ConnectException;

import javax.servlet.http.HttpSession;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

public class OfficeToSwf {
	private File sourceFile;		//转换源文件
	private File pdfFile;			//PDF目标文件
	private File swfFile;			//SWF目标文件
	private Runtime r;	
	public boolean ToSwf(String url,String fileName,String fileExt) throws ConnectException{
		sourceFile = new File(url+"/upload/"+fileName+"."+fileExt);
		pdfFile = new File(url+"/upload/"+fileName+".pdf");
		swfFile = new File(url+"/upload/"+fileName+".swf");
		System.out.println(sourceFile+"..."+pdfFile+"..."+swfFile);
		System.out.println("第一步：生成文件对象，准备转换");
		//转换成pdf文件
		if(sourceFile.exists()) {
			if(!pdfFile.exists()) {
				OpenOfficeConnection connection = new SocketOpenOfficeConnection("127.0.0.1", 8100);
				try {
					connection.connect();
					DocumentConverter converter = new OpenOfficeDocumentConverter(connection);   
					converter.convert(sourceFile, pdfFile);
					pdfFile.createNewFile();
					connection.disconnect();  
					System.out.println("第二步：转换为PDF格式	路径" + pdfFile.getPath());
				} catch (java.net.ConnectException e) {
					e.printStackTrace();
					System.out.println("OpenOffice服务未启动");
					throw e;
				} catch (com.artofsolving.jodconverter.openoffice.connection.OpenOfficeException e) {
					e.printStackTrace();
					System.out.println("读取文件失败");
					throw e;
				} catch (Exception e){
					e.printStackTrace();
					try {
						throw e;
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			} else {
				System.out.println("已转换为PDF，无需再次转换");
			}
		} else {
			System.out.println("要转换的文件不存在");
		} 
		//转换成swf文件
		r = Runtime.getRuntime();
		if(!swfFile.exists()){
			if(pdfFile.exists()) {
				try {
					Process p = r.exec(url+"/tool/pdf2swf.exe " + pdfFile.getPath() + " -o " + swfFile.getPath() + " -T 9");
					//重要！缓冲区，没有该代码会偶尔出现swf无法转换
					BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(p.getInputStream()));
							String line;
							while ((line=bufferedReader.readLine()) != null) {
							    System.out.println(line);
							}
					p.waitFor();
					swfFile.createNewFile();
					System.out.println("第三步：转换为SWF格式	路径：" + swfFile.getPath());
					System.out.println("第四步：转换为SWF格式名称：" + swfFile.getName());
					if(pdfFile.exists()) {
						pdfFile.delete();
					}
				} catch (Exception e) {
					e.printStackTrace();
					try {
						throw e;
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			} else {
				System.out.println("PDF文件不存在，无法转换");
			}
		} else {
			System.out.println("已经转为SWF文件，无需再次转换");
		}
		return true;
	}
}
