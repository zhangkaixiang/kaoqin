
	package com.action;

	import java.net.ConnectException;

import javax.servlet.http.HttpServletRequest;

	import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

	import com.opensymphony.xwork2.ActionSupport;
import com.util.OfficeToSwf;

	public class ViewOnlineAction extends ActionSupport implements ServletRequestAware{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String url;
		private String fileName;
		private String fileExt;
		private HttpServletRequest request;
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		
	public String getFileExt() {
			return fileExt;
		}
		public void setFileExt(String fileExt) {
			this.fileExt = fileExt;
		}
		
		public void setServletRequest(HttpServletRequest req) {
			this.request=req;	
		}
	public String execute(){
		
		setUrl(ServletActionContext.getServletContext().getRealPath(""));
		System.out.println(ServletActionContext.getServletContext().getRealPath(""));
		String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
		setFileName(fileName.substring(0, fileName.lastIndexOf(".")));
		request.getSession().setAttribute("swfName", fileName);
		setFileExt(prefix);
	    System.out.println(prefix);
		OfficeToSwf toswf=new OfficeToSwf();
		try {
			System.out.println(toswf.ToSwf(url, fileName, fileExt));
		} catch (ConnectException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}


	}


