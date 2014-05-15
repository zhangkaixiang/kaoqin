<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>修改教师用户密码</title>
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
    <div style="width:100%;text-align:center;">
        <h1>修改密码</h1>
        <form action="teacherinfo!modifyPassword.action" name="form1">
            <table style="margin:0 auto;" height="150";>
                <tr>
                    <td>
                        原始密码：
                    </td>
                    <td>
                        <input type="password" name="oldPsw"  />
                    </td>
                </tr>
                <tr>
                    <td>
                        新密码：
                    </td>
                    <td>
                        <input type="password" name="newPsw"  />
                    </td>
                </tr>
                <tr>
                    <td>
                        确认密码：
                    </td>
                    <td>
                        <input type="password" name="confirmPsw"  />
                    </td>
                </tr>
                <tr >
                    <td align="center" colspan="2">
                        <input  type="submit" />
                        <input type="reset"/>
                    </td>
                </tr>
            </table>
            
        </form>
        </div>
   <div id="showmsg" style="height:15px;color:white;">${msg}</div>
    </body>
</html>
