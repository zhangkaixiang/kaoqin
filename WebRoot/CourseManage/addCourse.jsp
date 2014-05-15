<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script src="scripts/jquery-1.6.2.min.js" type="text/javascript"></script>
<title>添加课程</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/layer.min.js"></script>
<script src="scripts/jquery.validate.min.js" type="text/javascript"></script>
<script type="text/javascript"> 
            $(document).ready(function() {
             
        		var text = document.getElementById("showmsg").innerHTML;
        		if (text == null || text == "") {

        		} else {
        			layer.alert(text, 1);
        		}
            });
function check(){
	var name=$("#name").val();
	var attendnum=$("#attendnum").val();
	var tasknum=$("#tasknum").val();
	var remark=$("#remark").val();
	if(name===""||attendnum===""||tasknum===""||remark===""){
		layer.alert("请把各项信息填写完整后再提交！",3);
		return false;
		
	}
	else
		return true;
}
        </script>
</head>
<body>
<div style="text-align:center;">
	<th colspan="2"><h2>课程添加</h2>
	</th>
	<form id="addCourseForm" action="courseManage!addCourse.action" onsubmit="return check()">
			<table style="margin:0 auto;" height="200"><tr>
			<td>请选择班级：</td><td>&nbsp;&nbsp;&nbsp; <select name="classid">
				<s:iterator value="myClasses" id="row">
					<option value="<s:property value="#row.id"/>">
						<s:property value="#row.className" />
					</option>
				</s:iterator>
			</select></td>
		</tr>
	
			<tr>
				<td>课程名称：</td><td><input type="text" id="name" name="course.name" class="required" /></td>
			</tr>
			<tr>
				<td>考勤次数：</td><td><input type="text" id="attendnum" name="course.attendNum" class="required"/></td>
			</tr>
			<tr>
				<td>作业次数：</td><td>
				<input type="text" name="course.taskNum" id="tasknum" class="required" /></td>
			</tr><tr><td>
					备注：</td><td>
					<textarea name="course.remark" class="required" id="remark"></textarea></td></tr>
<tr><td colspan="2" align="center">
				<input type="submit" value="提交" /> <input type="reset"/></td></tr>
				</form>
				<div id="showmsg" style="height:15px;color:white;">${msg}</div>
				</div>
</body>
</html>
