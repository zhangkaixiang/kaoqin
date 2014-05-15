<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        

        <title>考勤规则设置</title>
        
        <%-- 纠结了一上午js引用问题，jqueryjs放在装饰页其他页面无法共享，因为执行顺序是
        1. 被包含页面 ----> 2. 装饰页面。
        首先执行本页面，最后才把其它地方装饰上。这一点和ASP.NET母版页是相反的。
        有空研究研究sitemesh
        意味着，sitemesh装饰器页面时最后加载上去的，所以其它子页面引用过的js它（装饰器）都不需要引用
        --%>
        
          <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.11.0.min.js"></script>   


  <script src="<%=request.getContextPath()%>/scripts/jquery-1.6.2.min.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/scripts/zebra_datepicker.js" type="text/javascript"></script>
        <link href="<%=request.getContextPath()%>/css/zebra_datepicker.css" rel="stylesheet" type="text/css" />    
                	
        <script type="text/javascript">
            $(document).ready(function () {
                // assuming the controls you want to attach the plugin to have the "datepicker" class set
                $('input.datepicker').Zebra_DatePicker();
                $('#chooseDate').Zebra_DatePicker();
                var myDate = new Date();
                var year=myDate.getFullYear();    //获取完整的年份(4位,1970-????)
                var month=myDate.getMonth()+1;
                var min=myDate.getMinutes();
               var hour= myDate.getHours();       //获取当前小时数(0-23)
               var sec=  myDate.getSeconds(); 
               var week=myDate.getDay(); 
                if(month<10)
                	month="0"+month;
                //获取当前月份(0-11,0代表1月)
                var day=myDate.getDate(); 
                $('#chooseDate').val(year+"-"+month+"-"+day);
                $('select[name="startTime.hour"]').find("option[text="+hour+"]").attr("selected",true);
                $('select[name="startTime.minute"]').find("option[text="+min+"]").attr("selected",true);
                $('select[name="endTime.hour"]').find("option[text="+hour+"]").attr("selected",true);
                $('select[name="endTime.minute"]').find("option[text="+min+"]").attr("selected",true);
            });
            function timecheck(){  
            	var start=$('select[name="startTime.hour"]').find("option:selected").text();  
            	var startm=$('select[name="startTime.minute"]').find("option:selected").text();
            	var endh=$('select[name="endTime.hour"]').find("option:selected").text();  
            	var endm=$('select[name="endTime.minute"]').find("option:selected").text(); 
            	if(start>endh){
            		alert('亲,您输入的结束小时小于开始小时哦！');	
            		return false;
            		}
            	else if(start==endh&&endm<startm){
            		alert('亲,您输入的结束分钟小于开始分钟哦！');	
            		return false;	
            	}else{
            		return true;
            	}
            	return false;
            }
        </script>
        <style type="text/css">
            #custom {margin-left: 15%;}
            #custom label{ font-style:normal; }
            select{   text-align: center;}
            actionMessage .span{ color: red;}
            .doubleselect br {display:none;  }  
        </style>
    </head>
    <body>
        <h2 style="padding-left: 10%;">设置考勤时间</h2>
        <b style="color:red;"> <s:actionmessage/></b>
        <div style="margin-left: 15%;">
            <h3>固定周期设置：</h3>
            <form action="timeManage" name="form1" method="post" onSubmit="return timecheck()">
                <div class="doubleselect">
                    请选择课程：
                    <s:doubleselect id="d1" formName="form1" label="请选择" name="classid" list="#session.currentClass" listKey="id" listValue="className" doubleName="courseid" doubleList="#attr.courseList.get(top.id)" doubleListKey="id" doubleListValue="name" theme="simple"></s:doubleselect>
                    </div>
                    <p>请选择星期：
                        <select name="dayofWeek">
                            <option value="1" > 周一</option>
                            <option value="2">周二</option>
                            <option value="3">周三</option>
                            <option value="4">周四</option>
                            <option value="5">周五</option>
                            <option value="6">周六</option>
                            <option value="7">周日</option>
                        </select>
                    </p>
                    <p>
                        请选择时间:
                        <select name="startTime.hour">
                        <%for (int i = 6; i <= 22; i++) {%>
                        <option><%=i%></option>
                        <% }%>
                    </select>
                    时
                    <select name="startTime.minute">
                        <%for (int i = 0; i < 60; i++) {%>
                        <option><%=i%></option>
                        <% }%>
                    </select>
                    分
                    <b style="color:red;">至</b>
                    &nbsp;<select name="endTime.hour">
                        <%for (int i = 6; i <= 22; i++) {%>
                        <option><%=i%></option>
                        <% }%>
                    </select>
                    时
                    <select name="endTime.minute">
                        <%for (int i = 0; i < 60; i++) {%>
                        <option><%=i%></option>
                        <% }%>
                    </select>
                    分
                </p>
                <p>
                    <input type="submit" value="提交" />
                </p>
            </form>
        </div>

        <div id="custom" >
            <h3>自定义时间设置</h3>
            <form action="timeManage" name="form2" method="post" onSubmit="return timecheck()">
                <div class="doubleselect">
                    请选择：
                    <s:doubleselect id="d2" formName="form2" label="请选择" name="classid" list="#session.currentClass" listKey="id" listValue="className" doubleName="courseid" doubleList="#attr.courseList.get(top.id)" doubleListKey="id" doubleListValue="name" theme="simple"></s:doubleselect>
                    </div>

                    <p>
                        请输入日期：<input type="text" id="chooseDate" name="customDate" />
                    </p>

                    请选择时间：
                    <select name="startTime.hour">
                    <%for (int i = 6; i <= 22; i++) {%>
                    <option><%=i%></option>
                    <% }%>
                </select>
                时
                <select name="startTime.minute">
                    <%for (int i = 0; i < 60; i++) {%>
                    <option><%=i%></option>
                    <% }%>
                </select>
                分
                <b style="color:red;">至</b>
                &nbsp;<select name="endTime.hour">
                    <%for (int i = 6; i <= 22; i++) {%>
                    <option><%=i%></option>
                    <% }%>
                </select>
                时
                <select name="endTime.minute">
                    <%for (int i = 0; i < 60; i++) {%>
                    <option><%=i%></option>
                    <% }%>
                </select>
                分
                <p>
                    <input type="submit" id="tijiao1" value="提交" />
                </p>
            </form> 
        </div>

    </body>
</html>
