<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="scripts/jquery-1.6.2.min.js" type="text/javascript"></script>
        <script src="scripts/jquery.validate.min.js" type="text/javascript"></script>
        <script type="text/javascript"> 
            $(document).ready(function() {
                $("#modifyCourseForm").validate();
            });
        </script> 
        <title>课程信息修改</title>
    </head>
    <body>
        <div style="margin-top: 2%; margin-left: 5%;">
            <h2>课程信息修改</h2>
            <s:actionmessage/>
            <form id="modifyCourseForm" action="courseManage!modify.action?courseid=<%=request.getParameter("courseid")%>"  method="post">
                <p> 
                    课程名称：<input type="text" name="course.name" class="required" value="<s:property value='course.name' />" />
                </p>
                <p>
                    考勤次数：<input type="text"  name="course.attendNum" class="required" value="<s:property value='course.attendNum' />" ></input>
                </p>
                <p>
                    作业次数：<input type="text"  name="course.taskNum" class="required" value="<s:property value='course.taskNum' />"></input>
                </p>
                <p>
                    备注：&nbsp;&nbsp;&nbsp; <textarea name="course.remark" class="required" value="<s:property value='course.remark' />"></textarea>
                </p>
                <input type="submit"  value="提交" />
            </form>
            这个课程是属于当前登陆教师的。
        </div>
    </body>
</html>
