 <%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>课程管理</title>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font.css" />
        <script src="scripts/jquery-1.6.2.min.js" type="text/javascript"></script>
        <style type="text/css">
            td{ text-align: center; width: 150px; font-size: 12px;}
        </style>
        <script type="text/javascript">
             function confrimDel()
             {
                 if (confirm("您确认要删除吗？这样可能会影响相关联的记录")) {
                    return true;
                } 
                return false;
             }
        </script>
    </head>
    <body>
        <div style="margin-top: 2%; margin-left: 5%;">
            <h3>
                <a href="courseManage!prepareAdd.action">添加课程</a>
            </h3>
            <h4>您所教授的所有课程列表：</h4>
            <table>
                <th>班级</th><th>课程名称</th><th>班级人数</th><th>上课次数</th><th>作业次数</th><th colspan="2">操作</th>
                <s:iterator value="myCourses" id="row">
                    <tr>
                        <td><s:property value="#row.TClass.className" /></td>
                        <td><s:property value="#row.name" /></td>
                        <td><s:property value="#row.TClass.stuNum" />人</td>
                   
                        <td><s:property value="#row.attendNum" />次</td>
                        <td><s:property value="#row.taskNum" />次</td>
                        <td><a href="courseManage!prepareModify.action?courseid=<s:property value="#row.id" /> ">修改</a></td>
                        <td><a href="courseManage!delete.action?courseid=<s:property value="#row.id" />" onclick="return confrimDel()">删除</a></td>
                    </tr>
                </s:iterator>
            </table>

        </div>
        <s:actionmessage/>
    </body>
</html>
