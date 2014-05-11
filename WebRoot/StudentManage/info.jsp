<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>个人信息</title>
        <script src="scripts/jquery-1.6.2.min.js" type="text/javascript"></script>
        <style type="text/css">
            ul {list-style-type: none;}
            ul li { margin-bottom: 15px;}
        </style>
    </head>
    <body>
        <div style="margin-left: 15%;">
            <h1>个人信息</h1>
            <h3>
                <ul>
                    <li>姓名：<s:property value="student.name" /></li>
                    <li>学号：<s:property value="student.sno" /></li>
                    <li>班级：<s:property value="student.TClass.className" /></li>
                    <li>性别：<s:property value="student.sex" /></li>
                    <li>电话：<s:property value="student.phone" /></li>
                </ul>
            </h3>
        </div>
    </body>
</html>
