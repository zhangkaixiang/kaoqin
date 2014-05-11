<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>作业管理</title>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font.css" />
        <script src="scripts/jquery-1.6.2.min.js" type="text/javascript"></script>
        <style type="text/css">
            .doubleselect br { display:none;}
            td{ text-align:center; width: 150px; font-size: 12px;}
        </style>
    </head>
    <body>
        教师进入作业管理页面，先选择TA教的某个班的某门课。
        系统会列出该课的所有作业名称。
        可以点击详细进行作业内容详细的查看。
        可以点击进行上传。
        <div>
            <form action="taskManage!search.action" name="form1">
                <div class="doubleselect">
                    <p>
                        请选择课程：
                        <s:doubleselect id="d1" formName="form1" label="请选择" name="classid" list="#session.currentClass" listKey="id" listValue="className" doubleName="courseid" doubleList="#attr.courseList.get(top.id)" doubleListKey="id" doubleListValue="name" theme="simple"></s:doubleselect>
                            <input type="submit" value="搜索" />
                        </p>
                    </div>
                </form>
            </div>
        <s:if test="allTask.size()>0">
            <div>
                所有作业：
                <table>
                    <th>序号</th><th>课程名</th><th>作业名称</th><th>任课教师</th><th>操作</th>
                    <s:iterator value="allTask" id="row" status="st">
                        <tr>
                            <td><s:property value="#st.index+1" /></td>
                            <td><s:property value="#row.course.name" /></td>
                            <td><a href="taskManage!viewTask.action?taskid=<s:property value="#row.id" />"><s:property value="#row.name" /></a></td>
                            <td><s:property value="#row.teacher.name" /></td>
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
    </body>
</html>
