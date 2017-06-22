<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>500服务器错误页面</title>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="fonts/font-awesome/css/font-awesome.css" rel="stylesheet">
<link href="fonts/fontello/css/fontello.css" rel="stylesheet">
<link href="plugins/magnific-popup/magnific-popup.css"
	rel="stylesheet">
<link href="css/animations.css" rel="stylesheet">
<link href="plugins/owl-carousel/owl.carousel.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link href="css/skins/red.css" rel="stylesheet">
<link href="css/custom.css" rel="stylesheet">
</head>
<body>
	<div class="page-wrapper">
		<div class="page-intro">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<ol class="breadcrumb">
								<li><i class="fa fa-home pr-10"></i><a href="index">首页</a></li>
								<li class="active">Error500</li>
							</ol>
						</div>
					</div>
				</div>
			</div>
			<section class="main-container">

				<div class="container">
					<div class="row">

						<!-- main start -->
						<!-- ================ -->
						<div class="main col-md-6 col-md-offset-3">
							<h2 class="title">服务器发生错误，请等待管理员处理</h2>
							<br>
							<p>网站服务器内部发生错误，请等待工程师修复，错误原因<%= exception.getMessage()%> </p>
							<a href="index">点击此处返回首页.</a>
							<form role="search">
								<div class="form-group has-feedback">
									<input type="text" class="form-control" placeholder="Search">
									<i class="fa fa-search form-control-feedback"></i>
								</div>
							</form>
						</div>
						<!-- main end -->
					</div>
				</div>
			</section>
	</div>
</body>
</html>