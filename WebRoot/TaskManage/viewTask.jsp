u<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font.css" />
        <title>查看作业信息</title>
         <script src="scripts/jquery-1.6.2.min.js" type="text/javascript"></script>
    </head>
    <body>
       
        <s:if test="#session.student!=null">
            <h2><a href="taskManage!prepareHandInTask.action?taskid=<%=request.getParameter("taskid")%>&courseid=<s:property value="%{courseid}" /> " />上传作业</a></h2>
        </s:if>
        <table>
            <tr>
                <td>课程名称：</td><td> <s:property value="task.course.name" /></td>
            </tr>
            <tr>
                <td>作业名称：</td><td><s:property value="task.name" /></td>
            </tr>
            <tr>
                <td valign="top">作业要求：</td><td valign="top" style="width:500px; height: 700px; overflow: visible;"><s:property value="task.content" escape="false" /></td>
            </tr>
        </table>
    </body>
</html>
