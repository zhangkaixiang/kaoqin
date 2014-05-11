<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>学生作业上交</title>
        <script src="scripts/jquery-1.6.2.min.js" type="text/javascript"></script>
            <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font.css" />
        <style type="text/css">
            td{ width: 100px; text-align:  center;}
        </style>
    </head>
    <body>
        <h1>作业上交</h1>
        *某个班的学生进入查看作业页面系统列出有所有有作业的课程。<br/>
        *点击课程，可以看到任务列表。<br/>
        *每个任务都有上交与否这个状态。<br/>
        *学生点击任务名称，进入查看详细。如果未完成，可以点击上上交，进入上传作业页面。
        
        <form action="taskManage!search.action" method="post">
           请选择课程： <select name="courseid">
                <s:iterator value="#session.courses" id="row">
                    <option value="<s:property value="#row.id" />" ><s:property value="#row.name" /></option>
                </s:iterator>
            </select>
            <input type="submit" value="查看" />
        </form>
        <s:if test="allTask.size>0">
            <div>
               <h2> 所有作业：</h2>
                <table>
                    <th>序号</th><th>课程名</th><th>作业名称</th><th>任课教师</th><th>操作</th>
                    <s:iterator value="allTask" id="row" status="st">
                        <tr>
                            <td><s:property value="#st.index+1" /></td>
                            <td><s:property value="#row.course.name" /></td>
                            <td><a href="taskManage!viewTask.action?taskid=<s:property value="#row.id" />&courseid=<s:property value="%{courseid}" />"><s:property value="#row.name" /></a></td>
                            <td><s:property value="#row.teacher.name" /></td>
                            <td><a href="taskManage!viewTask.action?taskid=<s:property value="#row.id" />&courseid=<s:property value="%{courseid}" />">查看</a></td>
                        </tr>
                    </s:iterator>
                </table>
            </div>
        </s:if>
      <s:else>
      <div>
      <h2><strong><span style="color:#E53333;"><s:property value="message"/></span></strong></h2>
      </div>
       </s:else>
        <s:actionmessage/>
         
    </body>
</html>
