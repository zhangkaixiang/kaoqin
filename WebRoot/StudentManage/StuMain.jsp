 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>查看学生信息</title>
        <style type="text/css"> 
            #currentClass {font-size: 16px; text-align: left; margin-left: 35%;}
           #currentClass ul { list-style-type: none;}
           #currentClass  ul li{ margin: 10px; }
           #currentClass ul li a{ text-decoration: none;}
        </style>
         <script src="scripts/jquery-1.6.2.min.js" type="text/javascript"></script>
    </head>
    <body>
        <%--<s:debug/>--%>
        <h4 style="padding-left:40%;">学生信息查看</h4>
        <div id="currentClass">
             查看各班学生信息：
            <ul>
            <s:iterator value="classes" id="row">
                <li><a href="stu!showAllStudent.action?cid=<s:property  value="#row.id"/>"><s:property value="#row.className"/> &nbsp;&nbsp;(<s:property value="#row.stuNum" />人) </a></li>
            </s:iterator>
            </ul>
        </div>
    </body>
</html>
