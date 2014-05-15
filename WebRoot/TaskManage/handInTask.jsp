<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
      <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>上交作业</title>
        <script src="<%=request.getContextPath()%>/scripts/jquery-1.6.2.min.js" type="text/javascript"></script>
        <script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/jquery-1.11.0.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/layer.min.js"></script>
<script type="text/javascript">
	$(function() {
		var text = document.getElementById("showmsg").innerHTML;
		if (text == null || text == "") {

		} else {
			layer.alert(text, 1);
		}

	});
	</script>
    </head>
    <body>
        <h1>上交作业</h1>
        <div>
        <h5><strong><span style="color:#E53333;">温馨提示：您可以上传的文件类型可以为以下几种类型,否则0分哦！<br/>&nbsp;&nbsp;&nbsp;&nbsp;doc/docx/ppt/pptx/xls/xlsx/pdf</span></strong></h5>
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
   <div id="showmsg" style="height:15px;color:white;">${msg}</div>
</body>
</html>