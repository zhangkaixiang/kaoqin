<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>查看班级信息</title>
         <script type="text/javascript">
             function confrimDel()
             {
                 if (confirm("您没有删除班级信息的权限！")) {
                    return true;
                } 
                return false;
             }
        </script>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font.css" />
        <script src="scripts/jquery-1.6.2.min.js" type="text/javascript"></script>
        <style type="text/css">
            td{ text-align: center; width: 120px; font-size: 13px;}
        </style>
    </head>
    <body>
        <div style="margin-top: 2%; margin-left: 5%;">
            <h3>
                <a href="classManage!prepareAdd.action">添加班级</a>
            </h3>
            <h4>您所教授的所有班级列表：</h4>
            <table>
                <th>班级名称</th><th>班级人数</th><th>年级</th><th colspan="2">操作</th>
                <s:iterator value="allClasses" id="row">
                    <tr>
                        <td><s:property value="#row.className" /></td>
                        <td><s:property value="#row.stuNum" />人</td>
                        <td><s:property value="#row.rxnf"/>级</td>
                        <td><a href="classManage!prepareModify.action?classid=<s:property value="#row.id" />">修改</a></td>
                        <td><a href="#" onclick="return confrimDel()">删除</a></td>
                    </tr>
                </s:iterator>
            </table>

        </div>
    </body>
</html>
