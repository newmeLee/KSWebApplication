<%@page import="cn.zjzt.entity.system.WebNews"%>
<%@page import="java.util.List"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新闻列表-桐乡康石体检中心</title>
<!-- 解决IE11不兼容的问题 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
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
		<%@include file="common_header.jsp"%>
		<section class="main-container">
		<div class="container">
			<div class="row">
				<h2 class="page-title">新闻列表</h2>
				<div class="separator-2"></div>
				<div class="main col-md-12">
					<table id="table_news" onmouseover="changeCursor()" class="table table-hover">
						<%
							ActionContext context = ActionContext.getContext();
							List<WebNews> newsList = (List<WebNews>) context.get("newsList");
							;
							if (newsList != null) {
								if (newsList.size() > 0) {
									for (int i = 0; i < newsList.size(); i++) {
						%>
						<tr onclick="news_click(<%=newsList.get(i).getId()%>)">
							<td width="2%"><%=i+1%>.</td>
							<td><%=newsList.get(i).getNews_title() %></td>
							<td><%=newsList.get(i).getGmt_create()%></td>
						</tr>
						<%
							}
								}
							}
						%>
					</table>
				</div>
			</div>
		</div>
		</section>
		<!-- 底部信息栏 -->
		<%@ include file="common_foot.jsp"%>
	</div>
</body>
<script type="text/javascript">
	/* 跳转到新闻详情页 */
	function news_click(newsId){
		window.location.href="getNewsByID?newsID="+newsId+"";
	}
	/* 改变列表的 */
	function changeCursor(){
		$("#table_news").css("cursor","pointer");
	}
</script>
</html>