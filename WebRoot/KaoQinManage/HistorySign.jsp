<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>历史考勤记录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
        <style type="text/css">
            #outer{ margin-left: 2%;}
            table{ text-align: center;}
            table caption{ color: red;}
            table td{ width: 150px; font-size: 12px;}
            #form1 br {display:none;  }  
            #custom{ margin-top: 25px;}
            .actionMessage { list-style-type: none;}
            .actionMessage li { margin-top: 15px;}
        </style>
        <script src="scripts/jquery-1.6.2.min.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/scripts/zebra_datepicker.js" type="text/javascript"></script>
        <link href="<%=request.getContextPath()%>/css/zebra_datepicker.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font.css" />
        <script type="text/javascript">
            $(document).ready(function () {
                // assuming the controls you want to attach the plugin to have the "datepicker" class set
                $('input.datepicker').Zebra_DatePicker();
                $('#chooseDate').Zebra_DatePicker();
            });
        </script>
  </head>
  
  <body>
    <div id="outer">


            <%-- *****************************Start 根据班级和周几来搜索********************************* --%>  
            <div style="float:left; display: inline-block;">
                <form name="form1" id="form1" action="timeManage!searchSignSetting.action" >
                    分类搜索：
                    <select name="classid">
                        <s:iterator value="#session.currentClass" id="row">
                            <option value="<s:property value="#row.id" />" ><s:property value="#row.className" /></option>
                        </s:iterator>
                    </select>
                    <span id="detailSearch">
                        <select name="dayOfWeek" id="selectDayOfWeek">
                            <option value="1" >星期一</option>
                            <option value="2" >星期二</option>
                            <option value="3" >星期三</option>
                            <option value="4" >星期四</option>
                            <option value="5" >星期五</option>
                            <option value="6" >星期六</option>
                            <option value="7" >星期日</option>
                        </select>

                    </span>
                    <input type="submit" value="查看" />
                </form>  
            </div>
            <%-- *****************************End 根据班级和周几来搜索********************************* --%> 

            <%-- ....................Start按班级和自定义日期来搜索........................... --%>

            <div style="float:left; display: inline-block; margin-left: 45px;">
                <form name="customForm" action="timeManage!searchSignSetting.action">
                    自定义日期搜索：
                    <select name="classid">
                        <s:iterator value="#session.currentClass" id="row">
                            <option value="<s:property value="#row.id" />" ><s:property value="#row.className" /></option>
                        </s:iterator>
                    </select>
                    请输入日期：<input type="text" id="chooseDate" name="customDate" />
                    <input type="submit"  value="搜索" />
                </form> 
                </span>
            </div>
  </body>
</html>
