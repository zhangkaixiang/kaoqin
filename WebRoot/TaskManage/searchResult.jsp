 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font.css" />
        <title>查看所有作业</title>
    </head>
    <body>
        <div>
            所有作业：
            <table>
                <th>序号</th><th>课程名</th><th>作业名称</th><th>任课教师</th><th>操作</th>
                <s:iterator value="taskrecords" id="row" status="st">
                    <tr>
                        <td><s:property value="#st.index+" /></td>
                    <td><s:property value="#row.name" /></td>
                    <td><s:property value="#row.path" /></td>
                    <td><a href="taskManage!viewTask.action?taskid=<s:property value="#row.id" />"><s:property value="#row.name" /></a></td>
                    <td><s:property value="#row.teacher.name" /></td>
                    <td><a href="taskManage!searchTaskrecord.action?taskid=<s:property value="#row.id" />">查看作业情况</a></td>
                    </tr>
                </s:iterator>
            </table>
        </div>
    </body>
</html>
