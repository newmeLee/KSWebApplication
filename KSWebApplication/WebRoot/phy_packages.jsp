<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page language="java" import="java.util.*"
	import="cn.zjzt.entity.system.*" pageEncoding="utf-8"%>
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

<title>体检套餐-桐乡康石体检中心</title>
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
					<div class="main col-md-12">
						<h1 class="page-title">体检套餐</h1>
						<div class="separator-2"></div>
						<div class="masonry-grid-fitrows row grid-space-20">

							<%
								ActionContext context = ActionContext.getContext();
								List<Template> templateList = null;
								templateList = (List<Template>) context.get("templateList");
								if (templateList.size() > 0) {
									for (int i = 0; i < templateList.size(); i++) {
							%>
							<div class="col-md-3 col-sm-6 masonry-grid-item">
								<div class="listing-item">
									<div class="overlay-container">
										<img src="<%=templateList.get(i).getPic_url()%>" alt=""> <a
											href="template_detail?templateID=<%=templateList.get(i).getId() %>" 
											class="overlay small"> 
											<i class="fa fa-plus"></i> <span>View Details</span> </a>
									</div>
									<div class="listing-item-body clearfix">
										<h3 class="title">
											<a href="template_detail?templateID=<%=templateList.get(i).getId() %>">
											<%=templateList.get(i).getName()%></a>
										</h3>

										<p>套餐简介：<%=templateList.get(i).getIntro()%></p>
										<p>
											适用性别：<%=templateList.get(i).getApply_sex()%></p>
										<span class="price">¥<%=templateList.get(i).getPrice()%></span>
										<div class="elements-list pull-right">
											 <a href="template_detail?templateID=<%=templateList.get(i).getId() %>">查看详细</a>
										</div>
									</div>
								</div>
							</div>
							<%
								}
								}
							%>
							<div class="clearfix"></div>
						</div>
						<!-- main end -->

					</div>
				</div>
			</div>
			</section>
		<!-- 底部信息栏 -->
		<%@ include file="common_foot.jsp"%>
	</div>
</body>
</html>
