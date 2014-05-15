<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="scripts/jquery-1.6.2.min.js" type="text/javascript"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/jquery-1.11.0.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/layer.min.js"></script>
<script src="scripts/jquery.validate.min.js" type="text/javascript"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("#modifyCourseForm").validate();
		var text = document.getElementById("showmsg").innerHTML;
		if (text == null || text == "") {

		} else {
			layer.alert(text, 1);
		}
	});
</script>
<title>课程信息修改</title>
</head>
<body>
	<div style="margin-top: 2%; margin-left: 5%; text-align:center;">
		<table style="margin:0 auto;" height="200">
			<tr colspan="2">
				<h2>课程信息修改</h2>
			</tr>
			<form id="modifyCourseForm"
				action="courseManage!modify.action?courseid=<%=request.getParameter("courseid")%>"
				method="post">
				<tr>
					<td>课程名称：</td>
					<td><input type="text" name="course.name" class="required"
						value="<s:property value='course.name' />" />
					</td>
				</tr>
				<tr>
					<td>考勤次数：</td>
					<td><input type="text" name="course.attendNum"
						class="required" value="<s:property value='course.attendNum' />" />
					</td>
				</tr>
				<tr>
					<td>作业次数：</td>
					<td><input type="text" name="course.taskNum" class="required"
						value="<s:property value='course.taskNum' />" />
					</td>
				</tr>
				<tr>
					<td>备注：</td>
					<td><textarea name="course.remark" class="required" value="<s:property value='course.remark'/> "></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="提交" />
						<input type="reset" />
					</td>
				</tr>
			</form>
		</table>
	</div>
	<div id="showmsg" style="height:15px;color:white;">${msg}</div>
</body>
</html>
