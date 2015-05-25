<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
	<head>
		<base href="<%=basePath%>">
		<title>青蜜·答题</title>
		<meta charset="utf-8">
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10" />
		<meta name="viewport" content="width=device-width,initial-scale=1" />
		
		<link rel="stylesheet" href="<%= basePath %>css/style01.css" />
		<script type="text/javascript" src="<%= basePath %>js/jquery-2.1.3.min.js" ></script>
		<script type="text/javascript" src="<%= basePath %>js/action01.js" ></script>
	</head>
	<body>
		<div class='header'>
			<div class='content'>
				<div class='top_menu'>
					<ul>
						<li><a href="http://www.qing.me" class="menu" >青蜜</a></li>
						<li><a href="#" class="program_url" class="menu" >项目</a></li>
						<li><a href="#" class="selected" class="menu" >答题</a></li>
						<li style='margin-right:20px'><a href="http://www.qing.me/about.html" class="menu" >关于我们</a></li>
					</ul>
				</div>
				<div class='user_sign_in'>
					<ul>
						<li class='login'>登录  </li>
						<li><a href='' title="新浪微博登录"><img class="auth" src="<%= basePath %>img/auth/weibo.png" /></a></li>
						<li><a href='' title="QQ登录"><img class="auth" src="<%= basePath %>img/auth/qq.png" height="22" width="22" /></a></li>
						<li><a href='' title="人人登录"><img class="auth" src="<%= basePath %>img/auth/renren.png" /></a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class='mainbody' style='position:relative;'>
			<div id='project_XX'>
				<a href='#' class="program_logo pic01" target='_blank' title="此为XXX项目，点击可了解详情" style="background: url('<%= basePath %>img/programLogo/programLogo-940-200.jpg');"></a>
				<a href='#' class="program_logo pic02" target='_blank' title="此为XXX项目，点击可了解详情" style="background: url('<%= basePath %>img/programLogo/programLogo-620-132.jpg');"></a>
				<a href='#' class="program_logo pic03" target='_blank' title="此为XXX项目，点击可了解详情" style="background: url('<%= basePath %>img/programLogo/programLogo-480-102.jpg');"></a>
				<div class='main'>
					<div class='columns'>
						<div class='columns_left'>
							<div class='content' id="content00" style="height:auto;">
								<div class="question">1. <span class="question_inner">烫伤后应该如何急救？</span></div>
								<div id="correct">正确率：<span class="correct_rate">74.09</span>%</div>
								<div id='solution'>
									<a href="javascript:void(0)" class="selection_a" onclick="selectionClick(this)">用冷水持续冲洗伤部</a>
									<a href="javascript:void(0)" class="selection_b" onclick="selectionClick(this)">按摩伤处</a>
									<a href="javascript:void(0)" class="selection_c" onclick="selectionClick(this)">迅速包扎起来</a>
									<a href="javascript:void(0)" class="selection_d" onclick="selectionClick(this)">用牙膏敷在伤部</a>
								</div>
							</div>
							<div class='content' id="content01" style="height:auto;">
								<div class="question">2. <span class="question_inner">烫伤后应该如何急救？</span></div>
								<div id="correct">正确率：<span class="correct_rate">74.09</span>%</div>
								<div id='solution'>
									<a href="javascript:void(0)" class="selection_a" onclick="selectionClick(this)">用冷水持续冲洗伤部</a>
									<a href="javascript:void(0)" class="selection_b" onclick="selectionClick(this)">按摩伤处</a>
									<a href="javascript:void(0)" class="selection_c" onclick="selectionClick(this)">迅速包扎起来</a>
									<a href="javascript:void(0)" class="selection_d" onclick="selectionClick(this)">用牙膏敷在伤部</a>
								</div>
							</div>
							<div class='content' id="content02" style="height:auto;">
								<div class="question">3. <span class="question_inner">烫伤后应该如何急救？</span></div>
								<div id="correct">正确率：<span class="correct_rate">74.09</span>%</div>
								<div id='solution'>
									<a href="javascript:void(0)" class="selection_a" onclick="selectionClick(this)">用冷水持续冲洗伤部</a>
									<a href="javascript:void(0)" class="selection_b" onclick="selectionClick(this)">按摩伤处</a>
									<a href="javascript:void(0)" class="selection_c" onclick="selectionClick(this)">迅速包扎起来</a>
									<a href="javascript:void(0)" class="selection_d" onclick="selectionClick(this)">用牙膏敷在伤部</a>
								</div>
							</div>
							<div class='content' id="content03" style="height:auto;">
								<div class="question">4. <span class="question_inner">烫伤后应该如何急救？</span></div>
								<div id="correct">正确率：<span class="correct_rate">74.09</span>%</div>
								<div id='solution'>
									<a href="javascript:void(0)" class="selection_a" onclick="selectionClick(this)">用冷水持续冲洗伤部</a>
									<a href="javascript:void(0)" class="selection_b" onclick="selectionClick(this)">按摩伤处</a>
									<a href="javascript:void(0)" class="selection_c" onclick="selectionClick(this)">迅速包扎起来</a>
									<a href="javascript:void(0)" class="selection_d" onclick="selectionClick(this)">用牙膏敷在伤部</a>
								</div>
							</div>
							<div class='content' id="content04" style="height:auto;">
								<div class="question">5. <span class="question_inner">烫伤后应该如何急救？</span></div>
								<div id="correct">正确率：<span class="correct_rate">74.09</span>%</div>
								<div id='solution'>
									<a href="javascript:void(0)" class="selection_a" onclick="selectionClick(this)">用冷水持续冲洗伤部</a>
									<a href="javascript:void(0)" class="selection_b" onclick="selectionClick(this)">按摩伤处</a>
									<a href="javascript:void(0)" class="selection_c" onclick="selectionClick(this)">迅速包扎起来</a>
									<a href="javascript:void(0)" class="selection_d" onclick="selectionClick(this)">用牙膏敷在伤部</a>
								</div>
							</div>
							<div class='content' id="content05" style="height:auto;">
								<div class="question">6. <span class="question_inner">烫伤后应该如何急救？</span></div>
								<div id="correct">正确率：<span class="correct_rate">74.09</span>%</div>
								<div id='solution'>
									<a href="javascript:void(0)" class="selection_a" onclick="selectionClick(this)">用冷水持续冲洗伤部</a>
									<a href="javascript:void(0)" class="selection_b" onclick="selectionClick(this)">按摩伤处</a>
									<a href="javascript:void(0)" class="selection_c" onclick="selectionClick(this)">迅速包扎起来</a>
									<a href="javascript:void(0)" class="selection_d" onclick="selectionClick(this)">用牙膏敷在伤部</a>
								</div>
							</div>
							<div class='content' id="content06" style="height:auto;">
								<div class="question">7. <span class="question_inner">烫伤后应该如何急救？</span></div>
								<div id="correct">正确率：<span class="correct_rate">74.09</span>%</div>
								<div id='solution'>
									<a href="javascript:void(0)" class="selection_a" onclick="selectionClick(this)">用冷水持续冲洗伤部</a>
									<a href="javascript:void(0)" class="selection_b" onclick="selectionClick(this)">按摩伤处</a>
									<a href="javascript:void(0)" class="selection_c" onclick="selectionClick(this)">迅速包扎起来</a>
									<a href="javascript:void(0)" class="selection_d" onclick="selectionClick(this)">用牙膏敷在伤部</a>
								</div>
							</div>
							<div class='content' id="content07" style="height:auto;">
								<div class="question">8. <span class="question_inner">烫伤后应该如何急救？</span></div>
								<div id="correct">正确率：<span class="correct_rate">74.09</span>%</div>
								<div id='solution'>
									<a href="javascript:void(0)" class="selection_a" onclick="selectionClick(this)">用冷水持续冲洗伤部</a>
									<a href="javascript:void(0)" class="selection_b" onclick="selectionClick(this)">按摩伤处</a>
									<a href="javascript:void(0)" class="selection_c" onclick="selectionClick(this)">迅速包扎起来</a>
									<a href="javascript:void(0)" class="selection_d" onclick="selectionClick(this)">用牙膏敷在伤部</a>
								</div>
							</div>
							<div class='content' id="content08" style="height:auto;">
								<div class="question">9. <span class="question_inner">烫伤后应该如何急救？</span></div>
								<div id="correct">正确率：<span class="correct_rate">74.09</span>%</div>
								<div id='solution'>
									<a href="javascript:void(0)" class="selection_a" onclick="selectionClick(this)">用冷水持续冲洗伤部</a>
									<a href="javascript:void(0)" class="selection_b" onclick="selectionClick(this)">按摩伤处</a>
									<a href="javascript:void(0)" class="selection_c" onclick="selectionClick(this)">迅速包扎起来</a>
									<a href="javascript:void(0)" class="selection_d" onclick="selectionClick(this)">用牙膏敷在伤部</a>
								</div>
							</div>
							<div class='content' id="content09" style="height:auto;">
								<div class="question">10. <span class="question_inner">烫伤后应该如何急救？</span></div>
								<div id="correct">正确率：<span class="correct_rate">74.09</span>%</div>
								<div id='solution'>
									<a href="javascript:void(0)" class="selection_a" onclick="selectionClick(this)">用冷水持续冲洗伤部</a>
									<a href="javascript:void(0)" class="selection_b" onclick="selectionClick(this)">按摩伤处</a>
									<a href="javascript:void(0)" class="selection_c" onclick="selectionClick(this)">迅速包扎起来</a>
									<a href="javascript:void(0)" class="selection_d" onclick="selectionClick(this)">用牙膏敷在伤部</a>
								</div>
							</div>
							<div class="buttons">
								<div class="buttons_inner">
									<button class="btnSubmit" onclick="btnSubmit()">提 交</button>
									<button class="btnReStart" onclick="btnReStart()">重 来</button>
								</div>
							</div>
						</div>
						
						<div class='columns_right'>
							<div class='content'>
								<div class='sponsor_tab' >
									<div class="sponsor">赞助商</div>
									<div class="sponsor_detail">简&nbsp;&nbsp;&nbsp;介：<span class="sponsor_intro">赞助商简介</span></div>
									<div class="sponsor_detail">网&nbsp;&nbsp;&nbsp;站：<a href="#" class="sponsor_site">赞助商网站</a></div>
									<div class="sponsor_detail">电&nbsp;&nbsp;&nbsp;话：<span class="sponsor_tel">赞助商联系方式</span></div>
								</div>
								<div class='progress' style="margin-top: 5px;">
									<div class="progress_text">
										您为本项目答对了 <span class='orange right_count'>0</span> 道题, 还差 
										<span class='orange left_count'>10</span> 题就可以完成答题， 为该项目支持
										<span class='orange fund'>0</span> 元基金~
									</div>
									<div class="explanation" style="display: none;">
										解释：答案是选项 <span class="question_answer"></span> , 因为 <span class="explanation_text"></span>。										
									</div>
									<div class='progress_bar'><div class='progress_bar_inner'></div></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class='footer'>
			<hr color="#D5D5D5" style="margin-bottom: 20px;"/>
			青蜜 &copy; 2014-2015 |&nbsp;<a href="http://www.qing.me/about.html">关于我们</a> &nbsp;|&nbsp;沪ICP备XXX号
		</div>
	</body>
</html>