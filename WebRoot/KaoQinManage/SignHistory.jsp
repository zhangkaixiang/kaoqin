<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>签到历史</title>
        <script src="scripts/jquery-1.6.2.min.js" type="text/javascript"></script>
        <style type="text/css">
            #searchBar{ margin-left: 4%;}
            td{ width:150px; text-align: center; font-size: 12px;}
        </style>
        <script type="text/javascript" >
            window.onload = function(){
                var status = '<%=request.getParameter("status")%>';
              
                var sts = document.getElementById("sts");
                if (status=="unsign") {
                    sts.selectedIndex = 1;
                }
                
                var tbl = document.getElementById("tb");
                var tds = tbl.getElementsByTagName("td");
                for (i = 0; i < tds.length; i++) {
                    if (tds[i].innerHTML == "true") {
                        tds[i].innerHTML ="已缺课";
                    }
                    else if(tds[i].innerHTML=="false")
                    {
                        tds[i].innerHTML ="已签到";
                    }
                }
            }
         
        </script>
    </head>
    <body>
        学生进入可以看按最近时间排序的签到记录，可以分类查找，已签到，缺课。也可以看到统计。
        <div>
            <h2>签到历史</h2>    
            <div id="searchBar">
                <form action="signrecordManage!search.action" method="get">
                    分类搜索：
                    <select id="sts" name="status">
                        <option value="signed" >已签到</option>
                        <option value="unsign" >已缺课</option>
                    </select>
                    <input type="submit" value="查询" />
                </form>
                <s:actionmessage/>
            </div>
                <s:if test="allR.size()>0">
            <div id="record">
                <table id="tb" >
                    <th>课程名</th><th>签到时间</th><th>状态</th><th>日期</th><th>时间</th>
                    <s:iterator value="allR" id="row">
                        <tr>
                            <td><s:property value="#row.sign.course.name" />  </td>
                            <td><s:property value="#row.sign.startTime" />~<s:property value="#row.sign.endTime" /></td>
                            <td><s:property value="#row.lost" /></td>
                            <td><s:property value="#row.signdate" /></td>
                            <td><s:date name="#row.signTime" format="HH:mm:ss" /> </td>
                        </tr>
                    </s:iterator>
                </table>
            </div>
            </s:if>
                    
        </div>
    </body>
</html>
