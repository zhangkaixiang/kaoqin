<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page import="com.javareport.beans.*"%>
<%@ page extends="com.javareport.http.WebReportEngine"%>
<%@ page import="java.awt.*"%>
<%@ page import="java.util.List"%>
<%@ page import="com.model.ReportView"%>
<%@ page import="com.orm.Signrecord"%>
<%@ page import="java.text.SimpleDateFormat"%>
<<!DOCTYPE head PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<title>考勤统计图表</title>
</head>
<%!
	public Report createReport(HttpServletRequest request) throws Exception{
		//图片类型数组
		int[] chartType = new int[]{
		Chart.CHART_STACKBAR3D
		};
		//单元数据的显示标签字符数组
		String[] labels = new String[]{"已签到人数", "未签到人数"};
		//实例化报表对象
		Report report = new Report();
		//在页眉中添加文本信息内容
		report.addHeaderText("学生考勤和作业管理系统签到情况统计图");
		//在报表表的页眉添加一条横线                                                                               
		report.addHeaderSeparator(1);
		//在页尾添加一条直线
		
		report.addFooterSeparator(1);
		//在页尾添加文本信息内容
		report.addFooterText("第{P}页， 共{N}页");
			try{
				//实例化一个图表对象
				Chart chart = new Chart((Number[][]) getData(request));
				//设置图表中的单元数据的显示的标签
				chart.setLabels(labels);
				//设置统计图中显示的时候把具体的数值也显示出来
				chart.setShowValue(true);
				//在报表中添加图表信息内容
				chart.setStyle(chartType[0]);
				report.addChart(chart);
				report.addTable(getTableB(request));
				//在报表中添加换行符号
				report.addBreak();
				report.addBreak();
				report.addBreak();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		return report;
	}
public Table getTableB(HttpServletRequest request){
	String[][] data = getTableData(request);
    Table table = new Table(data);
    table.setAlignment(Table.H_CENTER + Table.V_CENTER);
    table.setColAutoSize(true);
    table.setRowBackground(0,Color.LIGHT_GRAY);
    table.setRowBackground(7,new Color(255,255,128));
    table.setHeaderRowCount(1);
    table.setRowBorder(table.LINE_THIN);
    table.setColBorder(table.LINE_THIN);
    return table;
}
	//读者可根据需要设置数组的值，或从数据库中取出值放入数组中以动态显示数据
	public Integer[][] getData(HttpServletRequest request){
	    /*  String snum=(String)request.getSession().getAttribute("signstu");
	      String unsnum=(String)request.getSession().getAttribute("unsignstu");
      int signstu= Integer.parseInt(snum);
      int unsignstu=Integer.parseInt(unsnum);*/
      ReportView num=(ReportView)request.getSession().getAttribute("report");
		Integer[][] data=new Integer[1][2];
		data[0][0]=num.getSign();
		data[0][1]=num.getUnsign();
		return data;
	}
	public String[][] getTableData(HttpServletRequest request){
      String date="";
      ReportView signnum=(ReportView)request.getSession().getAttribute("report");
      List<Signrecord> num=(List<Signrecord>)request.getSession().getAttribute("sign");
      int sum=signnum.getSign()+signnum.getUnsign();
		String[][] data=new String[sum+1][5];
		data[0][0]="IP地址";
		data[0][1]="学号";	
		data[0][2]="姓名";
		data[0][3]="签到时间";
		data[0][4]="状态";
		for(int i=0;i<sum;i++){
			if("".equals(num.get(i).getIp())||num.get(i).getIp()==null)
				data[i+1][0]="无";
			else
				data[i+1][0]=num.get(i).getIp();
			data[i+1][1]=String.valueOf(num.get(i).getStudent().getSno());
			data[i+1][2]=num.get(i).getStudent().getName();
			if(num.get(i).getSignTime()!=null){
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
				date=df.format(num.get(i).getSignTime());
			}else
			{
				date="无";
			}
			data[i+1][3]=date;
			if(num.get(i).getLost())
				data[i+1][4]="已缺课";
			else
				data[i+1][4]="已签到";
		}
		return data;
		
	}
	//定制Web报表在页面首部显示的工具栏为标准的样式，增加一个“返回”按钮，返回到首页
	public String getToolbarScript(HttpServletRequest request){
		return "<a href=\"tIndex.jsp\"><img src=\""+request.getRequestURI()+
				"?op=Resource&name=/resource/back.gif\" border=\"0\" alt=\"返回\"></a>";
	}
%>
</html>