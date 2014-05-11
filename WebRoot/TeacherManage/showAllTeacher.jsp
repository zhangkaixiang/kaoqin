 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>查看教师信息</title>
        <style type="text/css">
            table{ border-collapse: collapse;}
            th{ width: 150px;}
            tr{ border-bottom: 1px solid black;  }
            td{padding: 5px;padding-left:60px;}
            tr:hover{ background-color:aquamarine;}
        </style>
         <script src="scripts/jquery-1.6.2.min.js" type="text/javascript"></script>
         <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font.css" />
    </head>
    <body>
  <div style="text-align:center;">
        <h3>教师信息</h3>
        <div style=" text-align: center; margin-bottom: 40px;">
        <table style="text-align: center;margin:0 auto; width:80%;">
            <tr>
                <th>用户名</th><th>姓名</th><th>权限</th><th>权限管理</th>
            </tr>
        <s:iterator value="allteaher" id="row" >
            <tr>
                <td align="left"><s:property value="#row.username" /></td>
                <td align="left"> <s:property value="#row.name" /></td>
                <td align="left"> <s:if test="#row.Qx!=0">管理员</s:if><s:else>教师</s:else> </td>
                <td align="left" style="padding-left:110px;"> <s:if test="#row.Qx!=0"><a href="teacherinfo!updateQx.action?qx=0&id=<s:property value="#row.id"  />" >降级为教师</a></s:if><s:else><a href="teacherinfo!updateQx.action?qx=1&id=<s:property value="#row.id" />">提升为管理员</a></s:else> </td>
           </tr>
        </s:iterator>
       </table>
        </div>
        </div> 
        </body>
</html>
