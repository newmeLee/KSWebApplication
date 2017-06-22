<%@page import="cn.zjzt.entity.system.WebNews"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>新闻详情-桐乡康石体检中心</title>
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
<script type="text/javascript" src="plugins/modernizr.js"></script>
<script type="text/javascript" src="js/template.js"></script>
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
			<div class="col-md-9 col-md-offset-2">
				<%
					ActionContext context=ActionContext.getContext();
					WebNews news=(WebNews)context.get("news");
				%>
				<h2><%=news.getNews_title()%></h2>
				<span>关键字：<%=news.getKey_words()%></span><br>
				<span>编辑：<%=news.getCreator()%></span><br>
				<%=news.getNews_content()%>
			</div>
		</div>
	</div>
	</section>
	<!-- 底部信息栏 -->
	<%@ include file="common_foot.jsp"%>
</div>
</body>
</html>
