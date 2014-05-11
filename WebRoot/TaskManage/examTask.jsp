<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>审批作业</title>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font.css" />
        <script src="scripts/jquery-1.6.2.min.js" type="text/javascript"></script>
        <script type="text/javascript">
        $(function(){
        	var len=$("#tb").find("tr").length;
        	if(len===1){
        		$("#alltask").css("display", "none");
        	}
        	else
        		$("#alltask").css("display", "block");
        //	if($("#alltask").css("display")=="none")
        	//	$("#message").html("<h2><strong><span style='color:#E53333;'>提醒：暂时还没有学生上传作业！</span></strong></h2>");
        });
        </script>
        <style type="text/css">
            .doubleselect br {display:none;  }  
            td{ width: 120px; text-align: center;}
        </style>
        <script type="text/javascript">
            window.onload=function()
            {
                var tbs = document.getElementById("tb");
                var tds =tbs.getElementsByTagName("td");
                tds[2].style.display='none';
            }
        </script>
    </head>
    <body>
    <p>
        <h3>作业情况查看：</h3>
    </p>
    <form action="taskManage!searchTask.action" method="post" name="form1">
        <div class="doubleselect">
            &nbsp;&nbsp;&nbsp; 请选择课程：
            <s:doubleselect id="d1" formName="form1" label="请选择" name="classid" list="#session.currentClass" listKey="id" listValue="className" doubleName="courseid" doubleList="#attr.courseList.get(top.id)" doubleListKey="id" doubleListValue="name" theme="simple"></s:doubleselect>
            <input type="submit" value="查看" onclick="showmessage()"/>
        </div>
    </form>
<s:if test="allTask.size()>0">
<div id="alltask">
        所有作业：<%int i=0;%>
        <table id="tb">
            <th>序号</th><th>课程名</th><th>作业名称</th><th>操作</th>
            <s:iterator value="allTask" id="row" status="st">
                <tr>
                    <td><s:property value="#st.index+1" /></td>
                    <td><s:property value="#row.course.name" /></td>
                   <%i++;if(i==1){ %><td><s:property value="#row.name" /></td><%}%>
                    <td><a href="taskManage!viewTask.action?taskid=<s:property value="#row.id" />"><s:property value="#row.name" /></a></td>
                    <td><a href="taskManage!searchTaskrecord.action?taskid=<s:property value="#row.id" />&courseid=<s:property value="#row.course.id" />">查看作业情况</a></td>
                </tr>
            </s:iterator>
        </table>
    </div>
</s:if>
<s:else>
 <h2><strong><span style="color:#E53333;"><s:property value="message"/></span></strong></h2>
</s:else>
</body>
</html>
