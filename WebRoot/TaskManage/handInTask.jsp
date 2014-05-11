<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
      <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>上交作业</title>
        <script src="<%=request.getContextPath()%>/scripts/jquery-1.6.2.min.js" type="text/javascript"></script>
    </head>
    <body>
        <h1>上交作业</h1>
        <div>
            <s:url action="../upload.action" id="target">
                <s:param name="taskid"><%=request.getParameter("taskid")%> </s:param>
            </s:url>
            <s:form action="%{target}" enctype="multipart/form-data">
                <s:file name="doc" />
                名称：<input type="text" name="name" /> <br/>
                备注：<textarea type="text" name="remark" /></textarea>
            <input type="text" style="display:none;" name="courseid" value="<%=request.getParameter("courseid")%>" />
            <input type="submit" value="上传"/>
        </s:form>
    </div>
    <s:actionmessage/>
</body>
</html>