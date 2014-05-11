<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>修改密码</title>
        <script src="scripts/jquery-1.6.2.min.js" type="text/javascript"></script>
    </head>
    <body>
        <h1>修改密码</h1>
        学生进入修改密码页面
        系统列出三个文本框要求学生进行输入
        原始密码，新密码，确认密码，确认。
        验证通过，密码修改成功。
        如果原密码输入错误，提示。
        新密码确认输入错误则提示重新确认。
        <form action="stu!modifyPassword.action" name="form1">
            <table>
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
                <tr>
                    <td>
                        <input  type="submit" />
                    </td>
                </tr>
            </table>
            
        </form>
        <b style="color:red;"> <s:actionmessage /> </b>
    </body>
</html>
