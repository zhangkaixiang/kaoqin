<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎您——首页</title>
<script type="text/javascript" src="scripts/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="scripts/layer.min.js"></script>
<script type="text/javascript">
function img(image,mes){
	  layer.tips(mes,image, {
	    guide: 2,
	    maxWidth:240,
		time:3,
		});
	 }
function over(mes){
	  layer.tips(mes, document.getElementById("image"), {
	    guide: 2,
	    maxWidth:240,
		closeBtn:[0,true]
	  });
	 }
</script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font.css" />
<style type="text/css">
br {
	display: none;
}

#kaoqin {
	margin-left: 10%;
	margin-top: 4%;
}

#kaoqin table {
	text-align: center;
}

#kaoqin table td {
	width: 140px;
	font-size: 13px;
}

.actionMessage ul {
	list-style-type: none;
}
</style>
<script src="<%=request.getContextPath()%>/scripts/jquery-1.6.2.min.js"
	type="text/javascript"></script>

</head>

<body>
<div style="height:15px; line-height:15px;">
	<div style="float:left;margin-left:10px;"><iframe width="300" scrolling="no" height="25" frameborder="0"
		allowtransparency="true"
		src="http://i.tianqi.com/index.php?c=code&id=10&bdc=%23&icon=1"></iframe></div>
		<div style="float:left;margin-left:55px;"><strong><span style="color:#E53333;font-size:15px;">通知：</span><marquee behavior="scroll" onmouseover=this.stop() onmouseout=this.start() width=200 height=13 direction="left" scrollamount="2">今天下午三点在会议室开会！</marquee></strong>
		</div><div style="float:right;margin-right:200px;"><img id="image" onmouseover="over('有学生上传了作业，请尽快批改！')" src="images/mail.png" width="30" height="25" border="0" alt="mail" onload="img(this,'您收到一条新通知！')" /></div>
</div>	
	<div style="margin-left:5%;">
		<div id="myWeather"></div>
		<s:if test="#attr.signs.size() > 0">
			<div id="kaoqin">
				<h3>今日考勤</h3>
			<div style="margin:20px;">
				<form name="form1" id="form1" action="tindex!viewSignInfo.action">
					<table>
						<th>课程名称</th>
						<th>签到开始时间</th>
						<th>签到结束时间</th>
						<th>上课班级</th>
						<th>操作</th>
						<s:iterator value="#attr.signs" id="row">
							<tr>
								<td><s:property value="#row.course.name" /></td>
								<td><s:property value="#row.startTime" /></td>
								<td><s:property value="#row.endTime" /></td>
								<td><s:property value="#row.TClass.className" /></td>
								<td><a
									href="tindex!viewSignInfo.action?signid=<s:property value="#row.id" />">查看上课情况</a>
								</td>
							</tr>
						</s:iterator>
					</table>
				</form>
				</div>
				<div style="margin:20px;">
				<s:if test="records.size()>0">
					<h5>
						您正在查看的：<br /> 课程名称：
						<s:property value="curSign.course.Name" />
						<br /> 班级人数：
						<s:property value="curSign.course.TClass.stuNum" />
						<br /> 签到人数:
						<s:property value="staticNum.signedStudentNum" />
						<br /> 缺课人数：
						<s:property value="staticNum.unSignedStudentNum" />
						<br />
					</h5>
					<table>
						<th>ip</th>
						<th>学号</th>
						<th>姓名</th>
						<th>签到时间</th>
						<th>状态</th>
						<s:iterator value="records" id="row">
							<tr>
								<td><s:if test="#row.ip!=null">
										<s:property value="#row.ip" />
									</s:if> <s:else>
                                            ---
                                        </s:else>
								</td>
								<td><s:property value="#row.student.sno" />
								</td>
								<td><s:property value="#row.student.name" /></td>
								<td><s:if test="#row.signTime!=null">
										<s:date name="#row.signTime" format="HH:mm:ss" />
									</s:if> <s:else>
                                            ---
                                        </s:else>
								</td>
								<td><s:if test="#row.lost==true">
                                            缺课
                                        </s:if> <s:elseif
										test="#row.lost==false">
                                            已签到
                                        </s:elseif>
								</td>
							</tr>
						</s:iterator>

					</table>
					<a href="tindex!viewSignDraw.action?signid=<s:property value="getSignid"/> ">查看统计报表</a>
				</s:if>
				<s:elseif test="records.size()<=0">
					<b style="color:red;font-size:15px;">暂无记录！
					上课情况只有在考勤结束并由系统统计完成后才能查看！</b>
				</s:elseif>
			</div>
		</s:if>
		<s:else>
			<div style="margin-left: 10%; margin-top: 5%;">
				<h3>今日考勤</h3>
				<h4>Hi,您今天没有课，可以好好的休息了~O(∩_∩)O哈哈~</h4>
			</div>
		</s:else>
	</div>
</body>
</html>
