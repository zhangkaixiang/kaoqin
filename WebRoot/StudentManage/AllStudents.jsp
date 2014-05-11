 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>查看学生信息</title>
        <style type="text/css">
            table{ border-collapse: collapse;}
            th{ width: 150px;}
            tr{ border-bottom: 1px solid black;  }
            td{padding: 5px;}
            tr:hover{ background-color:aquamarine;}
        </style>
         <script src="scripts/jquery-1.6.2.min.js" type="text/javascript"></script>
    </head>
    <body>
   <div style="text-align:center;">
        <h3><s:property value="currentClass.className"/></h3>
        <div style=" text-align: center; margin-bottom: 40px;">
        <table style="text-align: center;margin:0 auto; width:80%;">
            <tr>
                <th>学号</th><th>姓名</th><th>性别</th><th>联系电话</th><th>班级</th>
            </tr>
        <s:iterator value="currentClass.students" id="row" >
            <tr>
                <td><s:property value="#row.sno" /></td>
                <td> <s:property value="#row.name" /></td>
                <td> <s:property value="#row.sex" /></td>
                <td> <s:property value="#row.phone" /></td>
                <td><s:property value="currentClass.className"/></td>
           </tr>
        </s:iterator>
       </table>
        </div>
        </div>
        </body>
</html>
