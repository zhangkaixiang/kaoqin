<%-- 
    Document   : modifyClass
    Created on : 2011-12-24, 14:54:49
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
  <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>修改班级信息</title>
        <script src="scripts/jquery-1.6.2.min.js" type="text/javascript"></script>
        <script src="scripts/jquery.validate.min.js" type="text/javascript"></script>
        <script type="text/javascript"> 
            $(document).ready(function() {
                $("#addClassForm").validate();
            });
        </script> 
    </head>
    <body>
        <div style="margin-left: 15px;">
            <s:actionmessage/>
            <h2>修改班级信息</h2>
            <form id="addClassForm" action="classManage!modify.action?classid=<%=request.getParameter("classid")%>" method="post" >
                <p>
                    请输入入学年份：
                    <input type="text"  name="classes.rxnf" class="required" value="<s:property value='classes.rxnf' />" />
                </p>
                <p>
                    请输入班级名称： <input type="text"  name="classes.className" class="required" value="<s:property value='classes.className' />" />
                    <s:fielderror />
                </p>
                <p>
                    请输入班级人数： <input type="text"  name="classes.stuNum" class="required" value="<s:property value='classes.stuNum'/>" />
                </p>              
                <input type="submit"  value="提交" />
            </form>
        </div>
    </body>
</html>
