<%@page import="cn.zjzt.entity.PhyAcceptUnit"%>
<%@page import="java.util.List"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>单位信息</title>
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
<!-- JS 文件 -->
<script type="text/javascript" src="plugins/jquery.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div class="page-wrapper">
		<section class="main-container">
		<div class="container">
			<div class="row">
				<div class="main col-md-9">
					<!-- 个人查询报告 -->
					<div id="panelPersonal" class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#phyUserCheck"> <i class="fa fa-flickr"></i>单位信息查询</a>
							</h4>
						</div>
						<div id="phyUserCheck" class="panel-collapse collapse in">
							<div class="panel-body">
								<%
								 	ActionContext context=ActionContext.getContext(); 
									PhyAcceptUnit unitInfo=(PhyAcceptUnit)context.get("unitInfo");
									if(unitInfo!=null){
								%>
								<div class="col-md-4 col-sm-4">

									<ul class="list-icons">

										<li><i class="icon-info"></i>单位编号：<%=unitInfo.getCode()%></li>
										<li><i class="icon-bookmarks"></i>联系电话：<%=unitInfo.getPhone()%></li>
										<li><i class="icon-phone"></i>手机号码：<%=unitInfo.getMobileTel()%></li>
										<li><i class="icon-user"></i>销售业务员二：<%=unitInfo.getSecondSales()%></li>
									</ul>

								</div>
								<div class="col-md-4 col-sm-4">
									<ul class="list-icons">
										<li><i class="icon-user"></i>单位名称：<%=unitInfo.getName()%></li>
										<li><i class="icon-vcard"></i>邮箱：<%=unitInfo.getEMail()%></li>
										<li><i class="icon-tag"></i>体检人数：
										<%=unitInfo.getStafferTotal()!=null?unitInfo.getStafferTotal():""%></li>
										<li><i class="icon-tag"></i>登记人员：<%=unitInfo.getUpdateUser()!=null?unitInfo.getUpdateUser():""%></li>
									</ul>
								</div>
								<div class="col-md-4 col-sm-4">
									<ul class="list-icons">
										<li><i class="icon-users"></i>地址：<%=unitInfo.getAddress()%></li>
										<li><i class="icon-newspaper"></i>单位类型：<%=unitInfo.getProperty()%></li>
										<li><i class="icon-user"></i>销售业务员：<%=unitInfo.getSalesForce()%></li>
										<li><i class="icon-tag"></i>登记日期：<%=unitInfo.getUpdateDate()!=null?unitInfo.getUpdateDate():""%></li>
									</ul>
								</div>
								<%
									}
								 %>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</section>
	</div>
</body>
</html>