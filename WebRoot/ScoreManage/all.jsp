<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>学生考勤成绩查看</title>
        <script src="scripts/jquery-1.6.2.min.js" type="text/javascript"></script>
        <style type="text/css">
            .doubleselect br { display:none;}
        </style>
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
        <h1>学生考勤成绩查看</h1>
        考勤成绩计算规则：签到成功记一分，未签到记零分，每门课程考勤成绩累计相加即为考勤成绩。
     
        <form action="scoreManage!staticScore.action" name="form2" method="post">
            <div class="doubleselect">
                请选择课程：
                <s:doubleselect id="d2" formName="form2" label="请选择" name="classid" list="#session.currentClass" listKey="id" listValue="className" doubleName="courseid" doubleList="#attr.courseList.get(top.id)" doubleListKey="id" doubleListValue="name" theme="simple"></s:doubleselect>
                 <input type="submit" value="统计" />
            </div>
           
        </form>   
        <form action="scoreManage!viewAll.action" name="form1" method="post">
            <div class="doubleselect">
                请选择课程：
                <s:doubleselect id="d1" formName="form1" label="请选择" name="classid" list="#session.currentClass" listKey="id" listValue="className" doubleName="courseid" doubleList="#attr.courseList.get(top.id)" doubleListKey="id" doubleListValue="name" theme="simple"></s:doubleselect>
                <input type="submit" value="查看" />
            </div>
        </form>

        <s:if test="allScores.size() > 0 ">
            <table>
                <th>学号</th><th>学生姓名</th><th>考勤成绩</th><th>所在班级</th>
                <s:iterator value="allScores" id="row">
                    <tr>
                        <td><s:property value="#row.student.sno" /></td>
                        <td><s:property value="#row.student.name" /></td>
                        <td><s:property value="#row.tscore" />分</td>
                        <td><s:property value="#row.student.TClass.className" /></td>
                    </tr>
                </s:iterator>
            </table>
        </s:if>
        <div id="showmsg" style="height:15px;color:white;">${msg}</div>
    </body>
</html>
