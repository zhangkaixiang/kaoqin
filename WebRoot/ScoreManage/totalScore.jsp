<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="scripts/jquery-1.6.2.min.js" type="text/javascript"></script>
        <title>总成绩</title>
        <style type="text/css">
            .doubleselect br { display:none;}
            td{ width: 120px; text-align: center;}
        </style>
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
    </head>
    <body>
        <h1>学生总成绩查看</h1>
        总成绩计算规则：总成绩=考勤成绩+作业成绩。
 
        <form action="scoreManage!staticAllScore.action" name="form2" method="post">
            <div class="doubleselect">
                统计：
                <s:doubleselect id="d2" formName="form2" label="请选择" name="classid" list="#session.currentClass" listKey="id" listValue="className" doubleName="courseid" doubleList="#attr.courseList.get(top.id)" doubleListKey="id" doubleListValue="name" theme="simple"></s:doubleselect>
                    <input type="submit" value="统计" />
                </div>

            </form>   
            <form action="scoreManage!viewAllScore.action" name="form1" method="post">
                <div class="doubleselect">
                  查看：
                <s:doubleselect id="d1" formName="form1" label="请选择" name="classid" list="#session.currentClass" listKey="id" listValue="className" doubleName="courseid" doubleList="#attr.courseList.get(top.id)" doubleListKey="id" doubleListValue="name" theme="simple"></s:doubleselect>
                    <input type="submit" value="查看" />
                </div>
            </form>

        <s:if test="scoreViews.size() > 0 ">
            <table>
                <th>学号</th><th>学生姓名</th><th>课程</th><th>总成绩</th><th>所在班级</th>
                <s:iterator value="scoreViews" id="row">
                    <tr>
                        <td><s:property value="#row.student.sno" /></td>
                        <td><s:property value="#row.student.name" /></td>
                        <td><s:property value="#row.course.name" /></td>
                        <td><s:property value="#row.ttscore" />分</td>
                        <td><s:property value="#row.student.TClass.className" /></td>
                    </tr>
                </s:iterator>
            </table>
        </s:if>
 <div id="showmsg" style="height:15px;color:white;">${msg}</div>
    </body>
</html>
