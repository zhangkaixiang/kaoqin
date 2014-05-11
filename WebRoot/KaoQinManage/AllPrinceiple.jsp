<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>所有规则</title>
        <style type="text/css">
            #outer{ margin-left: 2%;}
            table{ text-align: center;}
            table caption{ color: red;}
            table td{ width: 150px; font-size: 12px;}
            #form1 br {display:none;  }  
            #custom{ margin-top: 25px;}
            .actionMessage { list-style-type: none;}
            .actionMessage li { margin-top: 15px;}
        </style>
        <script src="scripts/jquery-1.6.2.min.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/scripts/zebra_datepicker.js" type="text/javascript"></script>
        <link href="<%=request.getContextPath()%>/css/zebra_datepicker.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font.css" />
        <script type="text/javascript">
            $(document).ready(function () {
                // assuming the controls you want to attach the plugin to have the "datepicker" class set
                $('input.datepicker').Zebra_DatePicker();
                $('#chooseDate').Zebra_DatePicker();
            });
        </script>
        <script type="text/javascript">
            function doconfirm()
            {
                if (confirm("您确认要删除吗？\n如果该规则已经包含了签到记录，则会自动成为失效规则\n如果没有包含签到记录，则会完全删除！！")) {
                    return true;
                }
                return false;
            }
        </script>
    </head>
    <body>
        <div id="outer">


            <%-- *****************************Start 根据班级和周几来搜索********************************* --%>  
            <div style="float:left; display: inline-block;">
                <form name="form1" id="form1" action="timeManage!searchSignSetting.action" >
                    分类搜索：
                    <select name="classid">
                        <s:iterator value="#session.currentClass" id="row">
                            <option value="<s:property value="#row.id" />" ><s:property value="#row.className" /></option>
                        </s:iterator>
                    </select>
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
                    <input type="submit" value="查看" />
                </form>  
            </div>
            <%-- *****************************End 根据班级和周几来搜索********************************* --%> 

            <%-- ....................Start按班级和自定义日期来搜索........................... --%>

            <div style="float:left; display: inline-block; margin-left: 45px;">
                <form name="customForm" action="timeManage!searchSignSetting.action">
                    自定义日期搜索：
                    <select name="classid">
                        <s:iterator value="#session.currentClass" id="row">
                            <option value="<s:property value="#row.id" />" ><s:property value="#row.className" /></option>
                        </s:iterator>
                    </select>
                    请输入日期：<input type="text" id="chooseDate" name="customDate" />
                    <input type="submit"  value="搜索" />

                </form> 
                </span>
            </div>
            <div style="clear:both;"></div>
            <input style="" type="button" onclick="javascript:location.href = 'timeManage!prepareView.action' " value="查看所有" />
            <h3>
                <b style="color:red;">
                    <s:actionmessage/>
                </b>
            </h3>
            <s:if test="allWeeklySigns.size() < 1 ">
                没有记录！
            </s:if>
            <s:if test="allWeeklySigns.size() > 0 ">
                <h2>搜索结果</h2>
                <table>
                    <th>班级名称</th>   <th>课程名称</th><th>开始时间</th><th>结束时间</th> <th>星期</th><th>操作</th>
                    <s:iterator value="allWeeklySigns" id="row" >
                        <tr>
                            <td>
                                <s:property value="#row.course.TClass.className" />
                            </td>
                            <td>
                                <s:property value="#row.course.name" />
                            </td>
                            <td>
                                <s:property value="#row.startTime" />
                            </td>
                            <td>
                                <s:property value="#row.endTime" />
                            </td>
                            <td>
                                <s:property value="#row.dayOfWeek" />
                            </td>
                            <td>
                                <a href="timeManage!prepareModify.action?signid=<s:property value="#row.id"/>">修改</a>
                                <a onclick="return doconfirm()" href="timeManage!prepareDelete.action?signid=<s:property value="#row.id"/>">删除</a>
                            </td>
                        </tr>
                    </s:iterator>
                </table>
            </s:if>  
            <s:if test="allDateSigns.size() < 1 ">
                没有记录！
            </s:if>
            <s:if test="allDateSigns.size() > 0">
                <h2>搜索结果</h2>
                <table>
                    <th>班级名称</th> <th>课程名称</th><th>开始时间</th><th>结束时间</th><th>日期</th><th>操作</th>
                    <s:iterator value="allDateSigns" id="row" >
                        <tr>
                            <td>
                                <s:property value="#row.course.TClass.className" />
                            </td>
                            <td>
                                <s:property value="#row.course.name" />
                            </td>
                            <td>
                                <s:property value="#row.startTime" />
                            </td>
                            <td>
                                <s:property value="#row.endTime" />
                            </td>
                            <td>
                                <s:property value="#row.customDate" />
                            </td>
                            <td>
                                <a href="timeManage!prepareModify.action?signid=<s:property value="#row.id"/>">修改</a>
                                <a onclick="return doconfirm()" href="timeManage!prepareDelete.action?signid=<s:property value="#row.id"/>">删除</a>
                            </td>
                        </tr>
                    </s:iterator>
                </table>
            </s:if >
            <%-- ....................End按班级和自定义日期来搜索........................... --%>

            <%-- !!!!!!!!!!!!!!!!!!!!!!!!!!!!!Start显示所有!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! --%>
            <%-- 判断周期性签到规则或者自定义日期规则是否有，针对搜索页面需要隐藏一些东西 --%>
            <s:elseif  test="signsByWeek.size() > 0 ||  signsByDate.size() > 0 ">
                <div id="cycle">
                    <table>
                        <caption>周期性签到规则</caption>
                        <th>班级名称</th> <th>课程名称</th><th>开始时间</th><th>结束时间</th><th>星期</th> <th>操作</th>
                        <s:iterator value="signsByWeek" id="row" >
                            <tr>
                                <td>
                                    <s:property value="#row.course.TClass.className" />
                                </td>
                                <td>
                                    <s:property value="#row.course.name" />
                                </td>
                                <td>
                                    <s:property value="#row.startTime" />
                                </td>
                                <td>
                                    <s:property value="#row.endTime" />
                                </td>
                                <td>
                                    <s:property value="#row.dayOfWeek" />
                                </td>
                                <td>
                                    <a href="timeManage!prepareModify.action?signid=<s:property value="#row.id"/>">修改</a>
                                    <a onclick="return doconfirm()" href="timeManage!prepareDelete.action?signid=<s:property value="#row.id"/>">删除</a>
                                </td>
                            </tr>
                        </s:iterator>
                    </table>
                </div>
                <div id="custom">
                    <s:if test="signsByDate.size()>0">
                    <table>
                        <caption>自定义日期签到规则</caption>
                        <th>班级名称</th><th>课程名称</th><th>开始时间</th><th>结束时间</th> <th>日期</th><th>操作</th>
                        <s:iterator value="signsByDate" id="row" >
                            <tr>
                                <td>
                                    <s:property value="#row.course.TClass.className" />
                                </td>
                                <td>
                                    <s:property value="#row.course.name" />
                                </td>
                                <td>
                                    <s:property value="#row.startTime" />
                                </td>
                                <td>
                                    <s:property value="#row.endTime" />
                                </td>
                                <td>
                                    <s:property value="#row.customDate" />
                                </td>
                                <td>
                                    <a href="timeManage!prepareModify.action?signid=<s:property value="#row.id"/>&type=0">修改</a>
                                    <a  onclick="return doconfirm()" href="timeManage!prepareDelete.action?signid=<s:property value="#row.id"/>">删除</a>
                                </td>
                            </tr>
                        </s:iterator>
                    </table>
                    </s:if>
                </div>
            </s:elseif>
            <%-- !!!!!!!!!!!!!!!!!!!!!!!!!!!!!End显示所有!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! --%>
        </div>
    </body>
</html>
