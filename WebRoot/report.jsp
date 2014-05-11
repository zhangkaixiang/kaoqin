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
<title>����ͳ��ͼ��</title>
</head>
<%!
	public Report createReport(HttpServletRequest request) throws Exception{
		//ͼƬ��������
		int[] chartType = new int[]{
		Chart.CHART_STACKBAR3D
		};
		//��Ԫ���ݵ���ʾ��ǩ�ַ�����
		String[] labels = new String[]{"��ǩ������", "δǩ������"};
		//ʵ�����������
		Report report = new Report();
		//��ҳü������ı���Ϣ����
		report.addHeaderText("ѧ�����ں���ҵ����ϵͳǩ�����ͳ��ͼ");
		//�ڱ�����ҳü���һ������                                                                               
		report.addHeaderSeparator(1);
		//��ҳβ���һ��ֱ��
		
		report.addFooterSeparator(1);
		//��ҳβ����ı���Ϣ����
		report.addFooterText("��{P}ҳ�� ��{N}ҳ");
			try{
				//ʵ����һ��ͼ�����
				Chart chart = new Chart((Number[][]) getData(request));
				//����ͼ���еĵ�Ԫ���ݵ���ʾ�ı�ǩ
				chart.setLabels(labels);
				//����ͳ��ͼ����ʾ��ʱ��Ѿ������ֵҲ��ʾ����
				chart.setShowValue(true);
				//�ڱ��������ͼ����Ϣ����
				chart.setStyle(chartType[0]);
				report.addChart(chart);
				report.addTable(getTableB(request));
				//�ڱ�������ӻ��з���
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
	//���߿ɸ�����Ҫ���������ֵ��������ݿ���ȡ��ֵ�����������Զ�̬��ʾ����
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
		data[0][0]="IP��ַ";
		data[0][1]="ѧ��";	
		data[0][2]="����";
		data[0][3]="ǩ��ʱ��";
		data[0][4]="״̬";
		for(int i=0;i<sum;i++){
			if("".equals(num.get(i).getIp())||num.get(i).getIp()==null)
				data[i+1][0]="��";
			else
				data[i+1][0]=num.get(i).getIp();
			data[i+1][1]=String.valueOf(num.get(i).getStudent().getSno());
			data[i+1][2]=num.get(i).getStudent().getName();
			if(num.get(i).getSignTime()!=null){
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
				date=df.format(num.get(i).getSignTime());
			}else
			{
				date="��";
			}
			data[i+1][3]=date;
			if(num.get(i).getLost())
				data[i+1][4]="��ȱ��";
			else
				data[i+1][4]="��ǩ��";
		}
		return data;
		
	}
	//����Web������ҳ���ײ���ʾ�Ĺ�����Ϊ��׼����ʽ������һ�������ء���ť�����ص���ҳ
	public String getToolbarScript(HttpServletRequest request){
		return "<a href=\"tIndex.jsp\"><img src=\""+request.getRequestURI()+
				"?op=Resource&name=/resource/back.gif\" border=\"0\" alt=\"����\"></a>";
	}
%>
</html>