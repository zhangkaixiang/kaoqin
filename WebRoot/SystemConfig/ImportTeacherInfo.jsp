<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"  %>
<%@taglib  prefix="m" uri="/msgDialog" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>学生信息导入</title>
        <style type="text/css">
            .functionIntro{text-align: left; padding-left: 25%; color: red; }
            #importSection{ width: 600px; margin-left: 35%;  text-align: left;  }
        </style>
         <script src="scripts/jquery-1.6.2.min.js" type="text/javascript"></script>
    </head>
    <body><%--
        <s:debug/>
        
        --%><%--如果这里不判断，则刷新页面会重新设置值，由于sclasses现在为空，则会将null给currentClass设置--%>
        <s:if test="#session.currentClass==null">
              <s:set name="currentClass" value="sclasses" scope="session"></s:set>
        </s:if>
        <h2 style="margin-left:20%;">欢迎使用教师信息导入功能</h2>
     
        <div class="functionIntro"> 
            
            <pre>
             功能简介：

                1.下载教师信息模板 <a href="util/teacher.xls" style="font-size:16px;">点此下载</a> 
             
                2.输入导入教师人数。

                3.将要导入的excel文件放入C盘根目录下。
                
                4.确认教师人数与表格记录数相符无误后，单击导入。

                5.导入教师后，默认密码为6个1，即111111。请教师及时修改密码！
            </pre>
           
        </div>
        <div id="importSection">
        <form action="importTeacher!importTeacherInfo.action" method="post">    
            <p> 请输入导入人数：<input type="text" id="tnum" name="tnum"/> 
          </p>
          <p>
           请输入文件名：&nbsp;<input type="text" name="fileName" /><b style="color:red;">(*请不要输入扩展名，如simple.xls,只需输入simple即可)</b> 
          </p>
          <input type="submit" value="导入" />
          </form>
        
        </div>
        <p>
            <%
           if(request.getParameter("result")!=null) 
           { 
              if( request.getParameter("result").toString().equals("done")){
            %>
                     <b style="color:red;">信息导入成功！</b>
            
          <% }}%>
        </p>
        <s:if test="hasFieldErrors()">
              <b style="color:red;">温馨提醒：文件名不能为空！</b>
       </s:if>
        </body>
</html>
