<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <script src="scripts/jquery-1.6.2.min.js" type="text/javascript"></script>
        <title>添加课程</title>
        <script src="scripts/jquery.validate.min.js" type="text/javascript"></script>
        <script type="text/javascript"> 
            $(document).ready(function() {
                $("#addCourseForm").validate();
            });
        </script> 
    </head>
    <body>
   
        <th colspan="2"><h2>课程添加</h2><s:actionmessage/></th>
        
        <form id="addCourseForm" action="courseManage!addCourse.action">
            <p>
               请选择班级：&nbsp;&nbsp;&nbsp;
                <select name="classid">
                    <s:iterator value="myClasses" id="row">
                        <option value="<s:property value="#row.id"/>"><s:property value="#row.className"/></option>
                    </s:iterator>
                </select>
            </p>
          <p> 

               请输入课程名：&nbsp;&nbsp;<input type="text" name="course.name" class="required" />
            </p>
            <p>
                请输入考勤次数：<input type="text"  name="course.attendNum" class="required"> </input>
            </p>
          <p>
                请输入作业次数：<input type="text"  name="course.taskNum" class="required"></input>
            </p>
        <p>
                备注：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;<textarea name="course.remark" class="required"></textarea>
            </p>
            <input type="submit"  value="提交" />
        </form>
        这个课程是属于当前登陆教师的。
    </body>
</html>
