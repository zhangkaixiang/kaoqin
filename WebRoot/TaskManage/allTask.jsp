<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>作业管理</title>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font.css" />
        <script src="scripts/jquery-1.6.2.min.js" type="text/javascript"></script>
                       <script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/jquery-1.11.0.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/layer.min.js"></script>
	<script type="text/javascript">
	$(function() {
		var text = document.getElementById("showmsg").innerHTML;
		if (text == null || text == "") {

		} else {
			layer.alert(text, 1);
		}

	});
	</script>
        <style type="text/css">
            .doubleselect br { display:none;}
            td{ text-align:center; width: 150px; font-size: 12px;}
        </style>
    </head>
    <body>
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
       </s:else>
       <div id="showmsg" style="height:15px;color:white;">${message}</div>
    </body>
</html>
