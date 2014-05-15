<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"  %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>添加作业</title>
        <script src="scripts/jquery-1.6.2.min.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font.css" />
        <style type="text/css">
            .doubleselect br { display:none;}
            table{ font-size: 12px; }
        </style>
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
          <script type="text/javascript" src="<%=request.getContextPath() %>/ckeditor_3.6.2/ckeditor/ckeditor.js"></script>  
    </head>
    <body>
        <div>
            <h2>添加作业要求</h2> <s:actionmessage/>
            <s:form action="taskManage!addTask.action" name="form1" method="post">
                <div class="doubleselect">
                    <p>
                        &nbsp;&nbsp;&nbsp; 请选择课程：
                        <s:doubleselect id="d1" formName="form1" label="请选择" name="classid" list="#session.currentClass" listKey="id" listValue="className" doubleName="courseid" doubleList="#attr.courseList.get(top.id)" doubleListKey="id" doubleListValue="name" theme="simple"></s:doubleselect>
                    </p>
                </div>
                <table>
                    <tr>
                        <td align="right">请输入作业名称：</td><td> <input size="30" type="text"  name="task.name"></input></td>
                    </tr>
                    <tr>
                        <td align="right" valign="top">作业要求：</td><td> <textarea id="tskcontent" name="task.content" rows="20" cols="100" ></textarea></td>
                    </tr>

                    <tr>
                        <td></td><td><input type="submit"  value="提交" /></td>
                    </tr>
                </table>
            </s:form>
        </div>
        <script  type="text/javascript">
            CKEDITOR.replace( 'tskcontent',
            {
                filebrowserImageUploadUrl  :'uploadImg.action',
                filebrowserImageBrowseUrl  :'showImage.jsp?type=image',
                toolbar :'Full',
                width:'700',
                filebrowserWindowWidth  : 700,
                filebrowserWindowHeight : 500
            });
        </script>
        <div id="showmsg" style="height:15px;color:white;">${message}</div>
    </body>
</html>
