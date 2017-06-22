<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>桐乡康石体检中心报告查询</title>
<!-- 解决IE11不兼容的问题 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!-- Mobile Meta -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="images/ksImages/logo.ico">
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="fonts/font-awesome/css/font-awesome.css" rel="stylesheet">
<link href="fonts/fontello/css/fontello.css" rel="stylesheet">
<link href="plugins/magnific-popup/magnific-popup.css" rel="stylesheet">
<link href="css/animations.css" rel="stylesheet">
<link href="plugins/owl-carousel/owl.carousel.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link href="css/skins/red.css" rel="stylesheet">
<link href="css/custom.css" rel="stylesheet">

</head>

<body>
	<!-- JS 文件 -->
	<script type="text/javascript" src="plugins/jquery.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
	<div class="page-wrapper">
		<!-- 顶部导航，引用文件common_header.jsp -->
		<%@ include file="common_header.jsp"%>
		
		<section class="main-container">
		<div class="container">
			<div class="row">
				<div class="main col-md-2">
				<!-- 体检建议 -->
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#phyAdvice"> <i class="fa fa-flickr"></i>查询类别</a>
							</h4>
						</div>
						<div id="phyAdvice" class="panel-collapse collapse in">
							<div class="panel-body">
								<button id="btnShowPersonal" class="btn radius btn-white btn-sm" 
									type="button" >个人查询</button>
								<button id="btnShowTeam" class="btn radius btn-white btn-sm" 
									type="button" >团体查询</button>
							</div>
						</div>
					</div>
				</div>
			
				<div class="main col-md-9">
					<!-- 个人查询报告 -->
					<div id="panelPersonal" class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#phyUserCheck"> <i class="fa fa-flickr"></i>个人报告查询</a>
							</h4>
						</div>
						<div id="phyUserCheck" class="panel-collapse collapse in">
							<div class="panel-body">
								<!-- <form action="queryPatient" method="post"> -->
								<!-- <form action="getCheckResult" method="post"> -->
								<form action="getCheckRecord" method="post">
						<div id="groupIDCard" class="form-group">
							<label >身份证号</label> <input type="text"
								class="form-control" id="IDCard" name="IDCard"
								placeholder="输入身份证号">
						</div>
						
						<div id="groupPassword" class="form-group">
							<label for="exampleInputPassword1">查询密码</label> <input
								type="password" class="form-control" id="checkPassword" name="checkPassword"
								placeholder="输入查询密码(默认密码123456)">
						</div>
						<div class="alert alert-danger" id="validate" style="display: none">
								<strong id="validateTip">Oh snap!</strong>
						</div>
						<button id="btnCheck" type="submit" class="btn radius btn-default">查询</button>
					</form>
							</div>
						</div>
					</div>
					
					<!-- 团体查询 (默认不显示)-->
					<div id="panelTeam" style="display: none" class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" href="#phyCheckResult"> <i
									class="fa fa-flickr"></i>团体报告查询 </a>
							</h4>
						</div>
						<div id="phyCheckResult" class="panel-collapse collapse in">
							<div class="panel-body">
							<form action="getUnitEmpInfo" method="post">
								<div id="formUnitID" class="form-group">
									<label >单位编号</label> 
									<input type="text" class="form-control"
											id="unitID" name="unitID" placeholder="单位编号">
								</div>
								<div id="formUnitPassword" class="form-group">
									<label >查询密码</label> 
									<input type="text" class="form-control" 
											id="unitPassword" name="unitPassword"
											placeholder="查询密码">
								</div>
								<div class="alert alert-danger" id="unitValidate" style="display: none">
								<strong id="unitValidateTip">Oh snap!</strong>
								</div>
								<button id="btnTeamCheck" type="submit" 
									class="btn radius btn-default">查询</button>
							</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</section>
		<!-- 底部信息栏 -->
		<%@ include file="common_foot.jsp"%>
	</div>
</body>
<script type="text/javascript">
	//显示个人体检查询登录界面
	$("#btnShowPersonal").click(function(){
		$("#panelPersonal").css("display","block");
		$("#panelTeam").css("display","none");
	});
	//显示团体查询登录界面
	$("#btnShowTeam").click(function(){
		$("#panelPersonal").css("display","none");
		$("#panelTeam").css("display","block");
	});
	//查询个人信息完整性验证
	$("#btnCheck").click(function() {
		//根据按钮ID获取action名
		var IDCard = $("#IDCard").val();
		var checkPassword = $("#checkPassword").val();
		//体检编号检验
		IDCard!=null?$("#groupIDCard").attr("class","form-group"):
		$("#groupIDCard").attr("class","form-group has-error");
		//
		checkPassword!=null?$("#groupPassword").attr("class","form-group"):
		$("#groupPassword").attr("class","form-group has-error");
		if ((IDCard == null) || (IDCard == "")) {
			//alert("体检编号不能为空！");
			$("#validate").css("display","block");
			$("#validateTip").html("身份证号不能为空！");
			$("#groupIDCard").attr("class","form-group has-error");
			return false;
		} else if ((checkPassword == null) || (checkPassword == "")) {
			//alert("身份证号码不能为空！");
			$("#validate").css("display","block");
			$("#validateTip").html("查询密码不能为空！");
			$("#groupPassword").attr("class","form-group has-error");
			return false;
		}
		var validateFlag = false;
		//验证查询信息
		$.ajax({
			type : "POST",//方法
			url : "validateInfo",//链接
			dataType : "json",
			async:false, //设置为同步，才能阻止提交
			data : {
				IDCard : IDCard,
				checkPassword : checkPassword
			},
			success : function(data) {
				if (data.result.status == 2) {
					validateFlag = true;
					return true;
				}else {
					if (data.result.status == 0) {
						$("#groupIDCard").attr("class","form-group has-error");
					}else if(data.result.status == 1){
						$("#groupIDCard").attr("class","form-group has-error");
					}
					$("#validate").css("display","block");
					$("#validateTip").html(data.result.message);
				} 
			}
		}); 
		return validateFlag;
	});
	
	//单位查询完整性验证
	$("#btnTeamCheck").click(function(){
		var unitID=$("#unitID").val();
		var unitPassword=$("#unitPassword").val();
		if((unitID==null)||(unitID=="")){
			//alert("单位编号不能为空！");
			$("#unitValidate").css("display","block");
			$("#unitValidateTip").html("单位编号不能为空！");
			$("#formUnitID").attr("class","form-group has-error");
			return false;
		}
		if((unitPassword==null)||(unitPassword=="")){
			//alert("单位编号不能为空！");
			$("#unitValidate").css("display","block");
			$("#unitValidateTip").html("查询密码不能为空！");
			$("#formUnitPassword").attr("class","form-group has-error");
			return false;
		}
		var validateFlag = false;
		//验证查询信息
		$.ajax({
			type : "POST",//方法
			url : "validateUnitInfo",//链接
			dataType : "json",
			async:false, //设置为同步，才能阻止提交
			data : {
				unitID : unitID,
				unitPassword:unitPassword
			},
			success : function(data) {
				if (data.result.status == 2) {
					validateFlag = true;
					return true;
				}else {
					$("#formUnitPassword").attr("class","form-group has-error");
					$("#unitValidate").css("display","block");
					$("#unitValidateTip").html(data.result.message);
				} 
			}
		});
			return validateFlag;
	});
</script>
</html>
