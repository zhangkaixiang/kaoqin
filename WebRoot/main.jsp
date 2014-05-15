<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>学生考勤和作业管理系统</title>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font.css" />
        <style type="text/css">
            td{width: 150px; text-align: center; font-size: 12px;}
            #unsigned{ font-size: 14px; }
            #unsigned td { padding-bottom: 10px;    }
            #myWeather{ margin-left: 15px; margin-top: 20px; font-size: 14px;}
            #navTab ul { list-style-type: none; float: left;  margin: 0;  padding: 0; }
            #navTab ul li { float: left; width: 80px; height: 30px; background-color:#444444;}
            #navTab ul li a { display: block; width: 80px; height: 30px; text-decoration: none; color: white; line-height: 30px; text-align: center; }
            #navTab ul li a:hover { background-color:#183257;}
            #unsigned , #signed , #lost {border: 1px solid silver; width: 80%; _position:relative; _top: -20px; height:230px; overflow: visible; } 
            ul a.selected { background-color:#183257;   }
            h4 b { color: red;}
        </style>
        <script src="<%=request.getContextPath()%>/scripts/jquery-1.6.2.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.idTabs.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/layer.min.js"></script>
        <script type="text/javascript">
$(function(){
 var text= document.getElementById("showmsg").innerHTML;
 if(text==null||text==""){
	 
 }else{
	  layer.alert(text,1);
 }

});
        </script>
    </head>
    <body>
<iframe width="300" scrolling="no" height="25" frameborder="0" allowtransparency="true" src="http://i.tianqi.com/index.php?c=code&id=10&bdc=%23&icon=1"></iframe>
<%--<strong><span style="color:#E53333;font-size:15px;">通知：</span><marquee behavior="scroll" onmouseover=this.stop() onmouseout=this.start() width=200 height=13 direction="left" scrollamount="2">这里是公告内容这里是公告内容这里是公告内容</marquee></strong>
        --%><div style="margin-left:18%; font-size: 14px; ">

            <br/>
            <h4> 今日： 已签到 <b ><s:property value="#attr.num.signedNum" /></b> 节   
                &nbsp;&nbsp;&nbsp;
                缺课 <b> <s:property value="#attr.num.lostSignedNum" /> </b>节 <br/>

                统计：  共签到<b>  <s:property value="#attr.num.signedTotalNum" /></b> 节  
                &nbsp;&nbsp;&nbsp;

                共缺课 <b style="color:red;"> <s:property value="#attr.num.lostSignedTotalNum" /> </b>节<br/>
            </h4>
             <div id="showmsg" style="height:15px;color:white;">${msg}</div>
            <%--<s:if test="#msg!=''">
            </s:if>
            --%><div id="navTab" style="margin-top:20px;"> 
                <ul class="idTabs"> 
                    <li class="selected"><a href="#unsigned">待签到</a></li> 
                    <li style="width:1px; background-color: white; height: 14px;"></li>
                    <li><a href="#signed">已签到</a></li> 
                    <li style="width:1px; background-color: white; height: 14px;"></li>
                    <li><a href="#lost">缺课记录</a></li> 
                </ul> 
            </div>
            <div style="clear:both;"></div>

            <div id="unsigned">
                <%-- 待签到：--%>
                <s:if test="#attr.unSignedByWeek.size()== 0 && #attr.unSignedByDate.size()== 0 ">
                    <h3>  目前暂无待签到列表。</h3>
                </s:if>
                <s:else>
                    <table>
                        <th>任课教师</th><th>课程名称</th><th>签到起始时间</th><th>签到结束时间</th><th>状态</th>
                        <s:iterator value="#attr.unSignedByWeek" id="row">
                            <tr> 
                                <td>
                                    <s:property value="#row.course.teacher.name"/> &nbsp;&nbsp;</td>
                                <td>
                                    <s:property value="#row.course.name"/> &nbsp;&nbsp;</td>
                                    <%--<s:set name="startDates" value="@com.util.FormatDateTime@getFormatedDate(#row.startTime)" />--%>
                                <td> <s:property value="#row.startTime" /></td>
                                <td><s:property value="#row.endTime" /> </td>
                                <td id="status">&nbsp;&nbsp; <a href="index!signIn.action?sid=<s:property value="#row.id" />"  onclick="signOk()">我要签到</a></td>
                            </tr>
                        </s:iterator>
                    </table>
                    <table>
                        <s:iterator value="#attr.unSignedByDate" id="row">
                            <tr> 
                                <td>
                                    <s:property value="#row.course.teacher.name"/> &nbsp;&nbsp;</td>
                                <td>
                                    <s:property value="#row.course.name"/> &nbsp;&nbsp;</td>

                                <td>  <s:property value="#row.startTime" /></td>
                                <td><s:property value="#row.endTime" /> </td>
                                <td> &nbsp;&nbsp;<a href="index!signIn.action?sid=<s:property value="#row.id" />" onclick="signOk()">我要签到</a></td>
                            </tr>
                        </s:iterator>
                    </table>
                </s:else>
            </div>

            <div id="signed">     
                <%--已签到--%>
                <s:if test="#attr.signedRecords.size() == 0 ">
                    <h3> 您今日没有签到记录。</h3>
                </s:if>
                <s:else>
                    <table>
                        <th>任课教师</th><th>课程名称</th><th>签到时间</th><th>状态</th>
                        <s:iterator value="#attr.signedRecords" id="row">
                            <tr> 
                                <td> <s:property value="#row.sign.course.teacher.name"/> &nbsp;&nbsp;</td>
                                <td>
                                    <s:property value="#row.sign.course.name"/> &nbsp;&nbsp;</td>
                                <td>
                                    <s:date name="#row.signTime" format="HH:mm:ss"/>
                                </td>
                                <td>
                                    已签到
                                </td>
                            </tr>
                        </s:iterator>
                    </table>
                </s:else>
            </div>
            <div id="lost">
                <%--缺课：--%>
                <s:if test="#attr.loseSignList.size()>0">
                    <table>
                        <th>任课教师</th><th>课程名称</th><th>签到起始时间</th><th>签到结束时间</th> <th>状态</th> 
                        <s:iterator value="#attr.loseSignList" id="row">
                            <tr> 
                                <td>
                                    <s:property value="#row.course.teacher.name"/> </td>
                                <td>
                                    <s:property value="#row.course.name"/> </td>
                                <td><s:property value="#row.startTime" /></td>
                                <td><s:property value="#row.endTime" /> </td>
                                <td>已缺课</td>
                            </tr>
                        </s:iterator>
                    </table>
                </s:if>
                <s:elseif test="#attr.loseSignList.size()<1">
                    <h4 style="color:red;"> 暂无缺课记录 <h4>
                </s:elseif>
            </div>

        </div>

    </body>
</html>
