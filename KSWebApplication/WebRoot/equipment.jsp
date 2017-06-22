<%@page import="cn.zjzt.entity.system.Equipment"%>
<%@page import="java.util.List"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>科室设备-桐乡康石体检中心</title>
<!-- 解决IE11不兼容的问题 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
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
<!-- script文件 -->
<script type="text/javascript" src="plugins/jquery.min.js"></script>
<script type="text/javascript" src="plugins/modernizr.js"></script>
<script type="text/javascript" src="js/template.js"></script>
<script type="text/javascript" src="plugins/isotope/isotope.pkgd.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<!--[if it IE 9]  -->
<script type="text/javascript" src="js/html5shiv.js"></script>
<script type="text/javascript" src="js/selectivizr.js"></script>
</head>
<body>
	<div class="page-wrapper">
		<!-- 顶部导航，引用文件common_header.jsp -->
		<%@include file="common_header.jsp"%>
		<section class="main-container">
				<div class="gray-bg section">
					<div class="container">
					<h2 class="page-title">先进设备</h2>
					<div class="separator-2"></div>
						<div class="isotope-container row grid-space-10">
						<%
							ActionContext context = ActionContext.getContext();
							List<Equipment> equipList = (List<Equipment>) context
									.get("equipList");
							if (equipList.size() > 0) {
								for (int i = 0; i < equipList.size(); i++) {
						%>
							<div class="col-sm-6 col-md-3 isotope-item web-design">
								<div class="box-style-1 white-bg">
									<div class="overlay-container">
										<img src="<%=equipList.get(i).getPic_url()%>" alt="">
										<a href="getEquipInfo?equipId=<%=equipList.get(i).getId() %>" class="overlay small">
											<i class="fa fa-plus"></i>
										</a>
									</div>
									<h4><a href="getEquipInfo?equipId=<%=equipList.get(i).getId() %>"><%=equipList.get(i).getName()%></a></h4>
									<a href="getEquipInfo?equipId=<%=equipList.get(i).getId() %>">详细</a>
								</div>
							</div>
							<%
							}
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