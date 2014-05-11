<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title><decorator:title default="学生考勤和作业管理系统" />
</title>
<decorator:head />
<style type="text/css">
body {
	font-size: 12px;
	margin: 0px;
}

.clear {
	clear: both;
}

#container {
	width: 72%;
	margin: 0 auto;
	text-align: left;
	font-size: 12px;
	margin-left: 2%;
	background-color: white;
	float: left;
}

#main {
	font-size: 12px;
	min-height: 80%;
	_height: 380px;
	border: 1px solid #0A71B3;
	border-top: none;
	padding-left: 1%;
}

#footer {
	height: 80px;
	font-size: 14px;
	text-align: center;
	line-height: 80px;
	border: 1px solid #0A71B3;
	border-top: none;
}

#side {
	float: left;
	margin-top: 20px;
	width: 18.5%;
	margin-left: 1%;
	font-size: 12px;
	background-color: white;
}

#nav {
	background: url(<%=request.getContextPath()%>/images/bg_nav.gif )
		repeat-x;
	color: white;
	left: 30px;
	padding-left: 10px;
}

#nav a {
	border: none;
	color: white;
	text-decoration: none;
	display: inline-block;
	position: relative;
	top: -5px;
}

#webwidget_vertical_menu {
	background-color: white;
	border-bottom: 1px solid #0A71B3;
	height: 490px;
	_height: 430px;
}

#sideTop {
	background: url(<%=request.getContextPath()%>/images/bg_left_tc.gif )
		repeat-x;
	height: 50px;
}

#logo {
	padding-left: 10px;
	padding-top: 10px;
	left: 20px;
	font-family: sans-serif;
	color: white;
	font-size: large;
}

#containerTitle {
	background: url('<%=request.getContextPath()%>/images/bg_header.gif')
		repeat-x;
	height: 47px;
	width: 100%;
}

#containerTitle span {
	display: inline-block;
	margin: 0;
	padding: 0;
	float: left;
}

#bannerMenu {
	background:
		url('<%=request.getContextPath()%>/images/bg_banner_menu.gif')
		no-repeat;
	width: 343px;
	height: 33px;
	position: absolute;
	right: 0;
}

#rightNav {
	display: inline-block;
	margin-left: 20%;
	_position: absolute;
	_margin-left: 22%;
}

#rightNav img {
	position: relative;
	top: -8px;
}

#nav b {
	position: relative;
	top: -10px;
}
</style>
<script type="text/javascript">
	function Clock() {
		var date = new Date();
		this.year = date.getFullYear();
		this.month = date.getMonth() + 1;
		this.date = date.getDate();
		this.day = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六")[date
				.getDay()];
		this.hour = date.getHours() < 10 ? "0" + date.getHours() : date
				.getHours();
		this.minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date
				.getMinutes();
		this.second = date.getSeconds() < 10 ? "0" + date.getSeconds() : date
				.getSeconds();

		this.toString = function() {
			return "现在是:" + this.year + "年" + this.month + "月" + this.date
					+ "日 " + this.hour + ":" + this.minute + ":" + this.second
					+ " " + this.day;
		};

		this.toSimpleDate = function() {
			return this.year + "-" + this.month + "-" + this.date;
		};

		this.toDetailDate = function() {
			return this.year + "-" + this.month + "-" + this.date + " "
					+ this.hour + ":" + this.minute + ":" + this.second;
		};

		this.display = function(ele) {
			var clock = new Clock();
			ele.innerHTML = clock.toString();
			window.setTimeout(function() {
				clock.display(ele);
			}, 1000);
		};
	}
</script>
<script src="scripts/webwidget_vertical_menu.js" type="text/javascript"></script>
<link href="css/webwidget_vertical_menu.css" rel="stylesheet"
	type="text/css"></link>
<script language="javascript" type="text/javascript">
	$(function() {
		$("#webwidget_vertical_menu").webwidget_vertical_menu({
			menu_width : '160',
			menu_height : '25',
			menu_margin : '1',
			menu_text_size : '12',
			menu_text_color : 'black',
			menu_background_color : '#EFF3F7',
			menu_border_size : '0',
			menu_border_color : '#000',
			menu_border_style : 'solid',
			menu_background_hover_color : '#999',
			directory : 'images'
		});
	});
</script>
</head>

<body
	style="background:url(<%=request.getContextPath()%>/images/bg_1.gif) repeat-x;">
	<div id="bannerMenu"></div>
	<%--<div id="logo">学生考勤和作业管理系统</div>
        
        --%>
	<div id="logo">
		<img src="<%=request.getContextPath()%>/images/titlepic.png" />
	</div>
	<div id="nav">
		<span><img
			src="<%=request.getContextPath()%>/images/nav_pre.gif" />
		</span> <span style=" position:relative; top: -12px;"> &nbsp;&nbsp;
			欢迎您&nbsp;&nbsp; 郑州轻工业学院 &nbsp;&nbsp; 软件学院 &nbsp; &nbsp; <s:property
				value="#session.student.name" /> <s:property
				value="#session.teacher.name" /> </span>
		<div id="rightNav">
			<span><img
				src="<%=request.getContextPath()%>/images/nav_back.gif" /><a
				href="javascript:history.go(-1);"><b>后退</b>
			</a>
			</span> <span><img
				src="<%=request.getContextPath()%>/images/nav_forward.gif" /><a
				href="javascript:history.go(1);"><b>前进</b>
			</a>
			</span> <span><img
				src="<%=request.getContextPath()%>/images/nav_resetPassword.gif" /><a
				href="#"><b>修改密码</b>
			</a>
			</span> <span><img
				src="<%=request.getContextPath()%>/images/nav_print.gif" />
			<s:if test="#session.student.name!=null"> <a href="<%=request.getContextPath()%>/index.action"><b>返回首页</b></s:if>
			<s:if test="#session.teacher.name!=null"><a href="<%=request.getContextPath()%>/tindex.action"><b>返回首页</b></s:if> 
			</a>
			</span> <span><img
				src="<%=request.getContextPath()%>/images/nav_changePassword.gif" /><a
				href="<%=request.getContextPath()%>/login!exit.action"><b>注销</b>
			</a>
			</span> <img src="<%=request.getContextPath()%>/images/menu_seprator.gif" />
			<span><b id="clock" style="top:-15px;"></b>
			</span>
		</div>
	</div>
	<div id="side">

		<div id="sideTop">
			<img src="<%=request.getContextPath()%>/images/bg_left_tl.gif">
			导航： <img src="<%=request.getContextPath()%>/images/bg_left_tr.gif"
				style="float:right;_float:none; _position: absolute; left: 19.7%; _z-index:1;   ">
		</div>
		<div id="webwidget_vertical_menu" class="webwidget_vertical_menu">
			<ul>

				<s:if test="#session.teacher!=null">
					<li><a href="<%=request.getContextPath()%>/tindex.action">首页</a>
					</li>
					<li><a
						href="<%=request.getContextPath()%>/courseManage.action">课程信息管理</a>
						<ul>
							<li><a
								href="<%=request.getContextPath()%>/courseManage!prepareAdd.action">添加课程</a>
							</li>
							<li><a
								href="<%=request.getContextPath()%>/courseManage.action">查看课程</a>
							</li>
						</ul></li>

					<li><a
						href="<%=request.getContextPath()%>/timeManage!prepareView.action">考勤时间管理</a>
						<ul>
							<li><a
								href="<%=request.getContextPath()%>/timeManage!prepareSetting.action">添加考勤时间</a>
							</li>
							<li><a
								href="<%=request.getContextPath()%>/timeManage!prepareView.action">查看考勤时间</a>
							</li>
						</ul></li>
					<li><a href="taskManage">学生作业管理</a>
						<ul>
							<li><a
								href="<%=request.getContextPath()%>/taskManage!prepareAdd.action">添加作业要求</a>
							</li>
							<li><a
								href="<%=request.getContextPath()%>/taskManage!prepareExame.action">查看学生作业</a>
							</li>
						</ul></li>

					<%-- 不要设置jsp路径，要经过action --%>
					<li><a
						href="<%=request.getContextPath()%>/scoreManage!prepareAllScore.action">学生成绩管理</a>
						<ul>
							<li><a
								href="<%=request.getContextPath()%>/scoreManage!prepareAllScore.action">查看统计总成绩</a>
							</li>
							<li><a
								href="<%=request.getContextPath()%>/scoreManage.action">查看考勤成绩</a>
							</li>
							<li><a
								href="<%=request.getContextPath()%>/scoreManage!taskScore.action">查看作业成绩</a>
							</li>
						</ul></li>

				</s:if>
				<s:if test="#session.student!=null">
					<li><a href="<%=request.getContextPath()%>/index.action">首页</a>
					</li>
					<li><a
						href="<%=request.getContextPath()%>/stu!viewInfo.action">个人信息</a>
					</li>
					<li><a
						href="<%=request.getContextPath()%>/signrecordManage.action">签到历史</a>
					</li>
					<li><a
						href="<%=request.getContextPath()%>/taskManage!stuAllTask.action">作业上传</a>
					</li>
					<li><a
						href="<%=request.getContextPath()%>/stu!preparModifyPsw.action">修改密码</a>
					</li>
				</s:if>
				<s:if test="#session.teacher.qx==1">
					<li>—————————</li>

					<li><a href="classManage.action">班级信息管理</a>
						<ul>
							<li><a
								href="<%=request.getContextPath()%>/classManage.action">查看所有班级</a>
							</li>
							<li><a
								href="<%=request.getContextPath()%>/classManage!prepareAdd.action">添加班级信息</a>
							</li>
						</ul></li>
					<li><a href="stu.action">学生信息管理</a>
						<ul>
							<li><a
								href="<%=request.getContextPath()%>/importStu!prepareImport.action">学生信息导入</a>
							</li>
							<li><a href="<%=request.getContextPath()%>/stu.action">查看学生信息</a>
							</li>
						</ul></li>
					<li><a href="teacherinfo.action">教师信息管理</a>
						<ul>
							<li><a
								href="<%=request.getContextPath()%>/importTeacher">教师信息导入</a>
							</li>
							<li><a href="<%=request.getContextPath()%>/teacherinfo.action">查看教师信息</a>
							</li>
						</ul></li>
				</s:if>
			</ul>
		</div>
	</div>
	<div id="container">
		<div id="containerTitle">
			<span
				style="color:white; position: absolute; font-size: 14px; margin-left: 25px; margin-top:5px; _margin-left:-105px; "><img
				src="<%=request.getContextPath()%>/images/kaoqinpic.png" />
			</span> <span
				style="background:url('<%=request.getContextPath()%>/images/main_hl2.gif') no-repeat; height: 47px; width: 15px; "></span>
			<span
				style="background:url('<%=request.getContextPath()%>/images/main_hb.gif') repeat-x; height: 47px; width: 85px; "></span>
			<span
				style="background:url('<%=request.getContextPath()%>/images/main_hr.gif')  no-repeat; height: 47px;  width: 40px; "></span>
		</div>

		<div class="clear"></div>
		<div id="main">
			<decorator:body />
		</div>
		<%--
            <div id="footer">
                
            </div>
        --%>
	</div>
	<SCRIPT type="text/javascript">
		var clock = new Clock();
		clock.display(document.getElementById("clock"));
	</SCRIPT>

</body>
</html>
