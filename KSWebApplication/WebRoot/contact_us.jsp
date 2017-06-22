<%@page import="cn.zjzt.entity.system.UserFeedback"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
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
<title>联系我们-桐乡康石体检中心</title>
<!-- 解决IE11不兼容的问题 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="images/ksImages/logo.ico">
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="fonts/font-awesome/css/font-awesome.css" rel="stylesheet">
<link href="fonts/fontello/css/fontello.css" rel="stylesheet">
<link href="plugins/rs-plugin/css/settings.css" media="screen"
	rel="stylesheet">
<link href="plugins/rs-plugin/css/extralayers.css" media="screen"
	rel="stylesheet">
<link href="plugins/magnific-popup/magnific-popup.css" rel="stylesheet">
<link href="css/animations.css" rel="stylesheet">
<link href="plugins/owl-carousel/owl.carousel.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link href="css/skins/red.css" rel="stylesheet">
<link href="css/custom.css" rel="stylesheet">
<!-- script文件 -->
<script type="text/javascript" src="plugins/jquery.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<!--[if it IE 9]  -->
<script type="text/javascript" src="js/html5shiv.js"></script>
<script type="text/javascript" src="js/selectivizr.js"></script>
</head>

<body>
	<div class="page-wrapper">
		<!-- 顶部导航，引用文件common_header.jsp -->
		<%@include file="common_header.jsp" %>
		
		<section class="main-container">
		<div class="container">
			<div class="row">

				<!-- main start -->
				<!-- ================ -->
				<div class="col-md-6">

					<!-- page-title start -->
					<!-- ================ -->
					<h1 class="page-title">联系我们</h1>
					<!-- page-title end -->
					<div class="alert alert-success hidden" id="contactSuccess">
						<strong>Success!</strong> Your message has been sent to us.
					</div>
					<div class="alert alert-error hidden" id="contactError">
						<strong>Error!</strong> There was an error sending your message.
					</div>
					<div class="contact-form">
							<div class="form-group has-feedback">
								<label for="name">名称*</label> <input type="text"
									class="form-control" id="name" name="name" placeholder="">
								<i class="fa fa-user form-control-feedback"></i>
							</div>
							<div class="form-group has-feedback">
								<label for="email">邮箱*</label> <input type="email"
									class="form-control" id="email" name="email" placeholder="">
								<i class="fa fa-envelope form-control-feedback"></i>
							</div>
							<div class="form-group has-feedback">
								<label for="subject">您的联系电话*</label> <input type="text"
									class="form-control" id="phone" name="subject" placeholder="">
								<i class="fa fa-navicon form-control-feedback"></i>
							</div>
							<div class="form-group has-feedback">
								<label for="message">留言*</label>
								<textarea class="form-control" rows="3" id="message"
									name="message" placeholder=""></textarea>
								<i class="fa fa-pencil form-control-feedback"></i>
							</div>
							<input type="submit" id="btnCommit" value="提交" class="btn btn-default">
					</div>
					<hr>
					<!-- 下方反馈信息的列表 -->
					<table id="tableFeedback" class="table">
						<%
							ActionContext context = ActionContext.getContext();
							List<UserFeedback> feedbackList = (List<UserFeedback>) context
									.get("feedbackList");
							if (feedbackList != null) {
								if (feedbackList.size() > 0) {
								for(int i=0;i<feedbackList.size();i++){
						%>
						<tr>
							<td width="10%"><img width="50px" src="images/avatar.jpg"></img>
							</td>
							<td><span><%=feedbackList.get(i).getUser_name()%></span>
								<span class="text-muted"><%= feedbackList.get(i).getGmt_create()%></span><br>
								<br>
								<p><%= feedbackList.get(i).getMessage()%></p>
								<!-- 工作人员回复 -->
									<table class="table">
									<tr>
										<td width="12%"><img src="images/ksImages/logo.jpg"></img></td>
										<td>中心回复：<%=feedbackList.get(i).getResponse()!=null?feedbackList.get(i).getResponse():""%>
										<p align="right"><%=feedbackList.get(i).getGmt_response()!=null?feedbackList.get(i).getGmt_response():""%></p></td>
									</tr>
								</table>
							</td>
						</tr>
						<%
						}
							}
							}
						%>
					</table>
				</div>

				<!-- sidebar start -->
				<aside class="col-md-6">
				<div class="sidebar">
					<div class="side vertical-divider-left">
						<h3 class="title">我们的联系方式.</h3>
						<ul class="list">
							<li><i class="fa fa-home pr-10"></i> 
							<span>桐乡市崇福大道666号振石控股集团康石体检中心</span>
							</li>
							<li><i class="fa fa-phone pr-10"></i>固话:0573-80883888</li>
						</ul>

						<div id="location">
							<h3 class="title">我们的位置.</h3>
							<a href="http://www.baidu.com" target="ifrPage"></a>
							<iframe height="340px" width="500px" id="ifrPage" name="ifrPage"
								src="location.html"></iframe>
						</div>
					</div>
				</div>
				</aside>
				<!-- sidebar end -->
			</div>
		</div>
		</section>
	
		<!-- 底部信息栏 -->
		<%@ include file="common_foot.jsp"%>
	</div>
</body>
<script type="text/javascript">
$("#btnCommit").click(function(){
	var userName=$("#name").val();
	var userEmail=$("#email").val();
	var userPhone=$("#phone").val();
	var message=$("#message").val();
	if($.trim(userName)==""){
		alert("名称不能为空！");
		return false;
	}else if($.trim(userPhone)==""){
		alert("联系电话不能为空！");
		return false;
	}else if($.trim(message)==""){
		alert("反馈信息不能为空！");
		return false;
	}
	//验证查询信息
		$.ajax({
			type : "POST",//方法
			url : "addUserFeedback",//链接
			dataType : "json",
			data : {
				userName : userName,
				userEmail:userEmail,
				userPhone:userPhone,
				message:message
			},
			success : function(data) {
				if (data.result.status == 200) {
					alert(data.result.message);
					/* window.location.href="contact_us";*/	
					var currentDate = getNowFormatDate();
					var htmlAppend="<tr><td width='10%''><img width='50px' src='images/avatar.jpg'></img></td><td><span>"+userName+"|</span><span class='text-muted'>"+currentDate+"</span><br><br><p>"+message+"</p></td></tr>";
					$("#tableFeedback").prepend(htmlAppend);
				}
			}
		});
});
/*获取格式化的时间*/
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
    return currentdate;
}
</script>
</html>
