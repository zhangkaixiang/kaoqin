<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>搜索结果</title>
        <script src="scripts/jquery-1.6.2.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="scripts/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="scripts/layer.min.js"></script>
          <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font.css" />
        <style type="text/css">
            td{ width: 140px; text-align: center;}
        </style>
        
        <script type="text/javascript">
        $(function(){
        	var len=$("#tb").find("tr").length;
        	if(len===1){
        		$("#result").css("display", "none");
        		$("#warring").html("<h2><strong><span style='color:#E53333;'>提醒：暂时还没有学生上传作业！</span></strong></h2>");
        	}
        	
        	else{
        		$("#result").css("display", "block");
        	}
        });
            function correct(id)
            {
            		
            	$.layer({
            	    shade :  [0.5 , '#000' , true], //不显示遮罩
            	    title : '提醒',
            	    area : ['auto','auto'],
            	    dialog : {
            	        msg:'您确定要将该作业设置为合格吗？',
            	        btns : 2, 
            	        type : 4,
            	        btn : ['确定','取消'],
            	        yes : function(){
                            $.post("taskManage!taskRecordFinished.action",{recordid:id},function(data){
                            	if(data="success")
                            		location.reload();
                            	else
                            		 layer.msg('出错啦，请重试！',2,4);
                            });
            	        },
            	        no : function(index){
            	        	layer.close(index);
            	        }
            	    }
            	}); 
                return false;
            }
        </script>
    </head>
    <body>
        <br/>
        <div id="warring"></div>
        <br/>
        <div id="result">
        <h3>查询结果：</h3>
        <table id="tb">
            <th>作业名称</th><th>学号</th><th>姓名</th><th>上交作业名称</th><th>上交时间</th><th>下载</th><th>操作</th>
            <s:iterator value="taskrecords" id="row">
                <tr>
                    <td><s:property value="#row.task.name" /></td>
                    <td><s:property value="#row.student.sno" /></td>
                    <td><s:property value="#row.student.name" /></td>
                    <td><s:property value="#row.name" /></td>
                    <td><s:property value="#row.uptime" /></td>
                    <td><a href="<%=request.getContextPath()%>\ViewOnline.action?fileName=<s:property value="#row.path"/>">作业查看</a></td>
                    <s:if test="#row.correct==false">
                        <td><input type="button" id="<s:property value="#row.id" />" value="合格" onclick="correct(this.id)" /></td>
                        </s:if>
                        <s:else>
                        <td>已合格</td>
                    </s:else>
                </tr> 
            </s:iterator>
        </table>
        </div>
    </body>
</html>
