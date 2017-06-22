<%@page import="cn.zjzt.entity.system.Equipment"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>设备详情-桐乡康石体检中心</title>
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
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<!--[if it IE 9]  -->
<script type="text/javascript" src="js/html5shiv.js"></script>
<script type="text/javascript" src="js/selectivizr.js"></script>
</head>
<body>
<div class="page-wrapper">
		<!-- 顶部导航，引用文件common_header.jsp -->
		<%@include file="common_header.jsp" %>
	<%
		ActionContext context=ActionContext.getContext();
		Equipment equipment=(Equipment)context.get("equipment");
		if (null != equipment) {
	%>
	<section class="main-container">
	<div class="container">
		<div class="row">
			<!-- main start -->
			<div class="main col-md-12">
				<h4 class="page-title margin-top-clear"><%=equipment.getName()%></h4>
				<div class="row">
					<div class="col-md-4">
						<img src="<%=equipment.getPic_url()%>" alt="">

						<!-- Tab panes end-->
						<hr>
						<p>
							设备名称：<%=equipment.getName()%></p>
						<hr>

					</div>

					<!-- 详细介绍 -->
					<aside class="col-md-8">
					<div class="sidebar">
						<div class="side product-item vertical-divider-left">
							<div class="tabs-style-2">
								<!-- Nav tabs -->
								<ul class="nav nav-tabs" role="tablist">
									<li class="active"><a href="#h2tab1" role="tab"
										data-toggle="tab"> <i class="fa fa-file-text-o pr-5"></i>设备介绍</a>
									</li>
								</ul>
								<!-- Tab panes -->
								<div class="tab-content padding-top-clear padding-bottom-clear">
									<div class="tab-pane fade in active" id="h2tab1">
										<p><%=equipment.getIntro()%></p>
									</div>
								</div>
							</div>
						</div>
					</div>
					</aside>
					<!-- product side end -->
				</div>

			</div>
			<!-- main end -->

		</div>
	</div>
	<%
		}
	%>
	</section>
	<!-- 底部信息栏 -->
		<%@ include file="common_foot.jsp"%>
	</div>
</body>
</html>