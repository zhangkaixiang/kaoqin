<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加班级</title>
<script src="scripts/jquery-1.6.2.min.js" type="text/javascript"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/jquery-1.11.0.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/layer.min.js"></script>
<script src="scripts/jquery.validate.min.js" type="text/javascript"></script>
<script type="text/javascript"> 
            $(document).ready(function() {
                $("#addClassForm").validate();
            		var text = document.getElementById("showmsg").innerHTML;
            		if (text == null || text == "") {

            		} else {
            			layer.alert(text, 1);
            		}
            });
        </script>
</head>
<body>
	<div style="margin-left: 15px;text-align:center;">
		<h2>添加班级</h2>
		<form id="addClassForm" action="classManage!add.action" method="post">
			<table align="center" height="150">
				<tr>
					<td>入学年份：</td>
					<td><input type="text" name="classes.rxnf" class="required" />
					</td>
				</tr>
				<tr>
					<td>班级名称：</td>
					<td><input type="text" name="classes.className"
						class="required"></input> <s:fielderror /></td>
				</tr>
				<tr>
					<td>班级人数：</td>
					<td><input type="text" name="classes.stuNum" class="required"></input>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="提交" />
					</td>
				</tr>
		</form>
	</div>
	<div id="showmsg" style="height:15px;color:white;">${msg}</div>
</body>
</html>
