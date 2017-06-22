<%@page import="cn.zjzt.entity.system.Template"%>
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

<title>套餐详情-桐乡康石体检中心</title>
<!-- 解决IE11不兼容的问题 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
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
			<div class="col-md-9">
				<%
					ActionContext context = ActionContext.getContext();
					Template template = (Template) context.get("templateInfo");
					if(template!=null){
				%>
				<h2><%=template.getName()%></h2>
				<span class="price">价格：¥<%=template.getPrice()%></span><br>
				<span class="price">适用人群：<%=template.getApply_sex()%></span><br>
				<span class="price">详 细：</span>
				<hr>
				<img alt="" class="img-responsive" src="<%=template.getPic_url()%>">
			<%
			}
			 %>
			</div>
		</div>
	</div>
	</section>
	<!-- 底部信息栏 -->
	<%@ include file="common_foot.jsp"%>
</div>
</body>
</html>
