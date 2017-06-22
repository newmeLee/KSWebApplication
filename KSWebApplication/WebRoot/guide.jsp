<%@page import="cn.zjzt.entity.system.WebNews"%>
<%@page import="java.util.List"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>体检指南-桐乡康石体检中心</title>
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
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<mark class="page-title">体检注意事项</mark>
					<div class="separator-2"></div>
					<ul>
						<li>检查前三日开始，饮食宜清淡，勿吃菠菜、猪血、鸭血等食物（以免引起大便隐血假阳性）。</li>
						<li>体检前一天要注意休息，不熬夜，避免剧烈运动和情绪激动，保证充足睡眠，以免影响体检结果。</li>
						<li>前一日晚最好禁食牛奶、豆制品、饮料、糖类等食品。勿饮酒。空腹8小时以上。<br />
						<li>检查当天要穿轻便服装，不要化妆，不要穿连衣裙、连裤袜，不要穿有金属扣子的内衣裤、不要戴胸针、项链等贵重饰品和隐形眼镜。</li>
						<li>体检当日禁食早餐，在抽血、B超检查后方可进餐。</li>
						<li>心电图和测量血压时应避免精神紧张，保持心情稳定。</li>
						<li>慢性病史者，请携带病历，并及时向医生提供既往病史，手术史等，如正在服用某些药物也应及时告之，便于分析体检资料做出结论。
						</li>
						<li>妇科检查前应排空小便，女性例假期间，不宜做妇科检查及尿检。</li>
						<li>怀孕及有可能怀孕的女性受检者，请先告知体检医生，勿做X 光检查。</li>
						<li>体检前五天开始，争取每天喝两瓶开水，这样会易于排毒。</li>
						<li>保持一颗平常心。</li>
						<li>体检时应在医师指导下检查所有体检项目，勿漏项，以免影响体检结果。</li>
					</ul>
				</div>
				<!-- 体检健康常识 -->
				<div class="col-md-6">
					<mark class="page-title">体检健康常识</mark>
					<div class="separator-2"></div>
					<ul class="list-icons">
						<%
							ActionContext context = ActionContext.getContext();
							List<WebNews> socialNews = (List<WebNews>) context
									.get("socialNews");
							if (socialNews != null) {
								if (socialNews.size() > 0) {
									for (int i = 0; i < socialNews.size(); i++) {
						%>
						<li><a 	href="getNewsByID?newsID=<%=socialNews.get(i).getId()%>"> 
								<%=socialNews.get(i).getNews_title()%></a>
						</li>
						<%
							}
								}
							}
						%>
					</ul>
				</div>
			</div>
			<div class="row">	
				<!-- 会员办理流程 -->
				<div class="col-md-12">
					<mark class="page-title">体检会员办理流程</mark>
					<div class="separator-2"></div>
					<ol>
						<li>出示身份证复印文件.</li>
						<li>缴纳入会费</li>
						<li>会员卡中心人员对会员资料确认建档后，提供会员卡.</li>
						<li>专家咨询，设计健康体检方案.</li>
						<li>建立个人健康档案.</li>
						<li>安排体检.</li>
						<li>享受会员服务.</li>
					</ol>
					<div class="alert alert-info alert-dismissible" role="alert">
						<button type="button" class="close" data-dismiss="alert">
						</button>
						<p>会员充值事项:</p>
						<ul class="">
							<li>1.凡持有合法身份证明、愿意交纳年费认同康石健康理念的个人，均可成为&ldquo;康石体检中心&rdquo;的会员。
							</li>
							<li>2.非办卡会员当年累计消费满10000元，可获得会员资格。（限VIP银卡）</li>
							<li>3.充值方式：续充会员卡1000元起充。</li>
							<li>4.会籍为永久有效，年费期限为12个月，到期系统将自动从会员卡余额中扣除。 持卡人应在卡上签名，若有
								遗失请立即挂失补办，补办会员卡收工本费20元。</li>
							<li>5.会员办理贵宾卡后15天可申请退会，已经消费的项目按照全额收取。超过15天不再办理退会手续</li>
							<li>6.每1个积分相当于1元的消费。</li>
							<li>7.会员积分达到某一兑换标准时，可凭会员卡进行兑换，即时从用户积分中扣减相应分数。</li>
							<li>8.会员积分每年清零一次，以每年的12月31日0:00:00为界。</li>
							<li>9.康石体检中心保留最终解释权。</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		</section>
		<!-- 底部信息栏 -->
		<%@ include file="common_foot.jsp"%>
	</div>
</body>
</html>