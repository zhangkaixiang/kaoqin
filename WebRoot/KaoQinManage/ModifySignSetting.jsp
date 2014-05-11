<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>修改</title>
        <style type="text/css">
            #custom {margin-left: 15%;}
            #custom label{ font-style:normal; }
            select{   text-align: center;}
            actionMessage .span{ color: red;}
            .doubleselect br {display:none;  }  
        </style>
        <script type="text/javascript">
            window.onload = function(){
                var d1 = document.getElementById("selectDayOfWeek");
                var day = document.getElementById("day").innerHTML;
                //设置班级select值
                for (i = 0; i < d1.length; i++) {
                    if (day == d1.options[i].value) {
                        d1.selectedIndex = i ;
                        break;
                    }
                }
            }
        </script>
        <script src="scripts/jquery-1.6.2.min.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/scripts/zebra_datepicker.js" type="text/javascript"></script>
        <link href="<%=request.getContextPath()%>/css/zebra_datepicker.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript">
            $(document).ready(function () {
                // assuming the controls you want to attach the plugin to have the "datepicker" class set
                $('input.datepicker').Zebra_DatePicker();
                $('#chooseDate').Zebra_DatePicker();
            });
        </script>
    </head>
    <body>
        <div style="margin-left:15%;">
            <h2> <span id="day" style="display:none;"><s:property value="curSign.dayOfWeek" /></span></h2>
            <h3> 你现在要修改的是： <br/></h3>
            <h2> 
               班级：<s:property value="curSign.TClass.className" /> <br/>
               课程：<s:property value="curSign.course.name" /> <br/>
                    <s:if test="curSign.dayOfWeek != 0">
                        星期<s:property value="curSign.dayOfWeek" />
                    </s:if>
                     <s:else>
                       日期: <s:property value="curSign.customDate" /> <br/>
                      </s:else>
               上课时间：<s:property value="curSign.startTime" /> 至  <s:property value="curSign.endTime" /></h2>
            <form name="form1" action="timeManage!modify.action" method="post">
                <p>
                    <s:hidden name="signid" value="%{signid}" ></s:hidden>
                    请选择时间:
                    <s:if test="type!=0">
                        <span id="detailSearch">
                            <select name="dayOfWeek" id="selectDayOfWeek">
                                <option value="1" >星期一</option>
                                <option value="2" >星期二</option>
                                <option value="3" >星期三</option>
                                <option value="4" >星期四</option>
                                <option value="5" >星期五</option>
                                <option value="6" >星期六</option>
                                <option value="7" >星期日</option>
                            </select>
                        </span>
                    </s:if>
                    <s:if test="type==0">
                        请输入日期：<input type="text" id="chooseDate" name="customDate" />
                    </s:if>
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
    </body>
</html>
