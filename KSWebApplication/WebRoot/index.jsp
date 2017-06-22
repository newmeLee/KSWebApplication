<%@page import="cn.zjzt.entity.system.Equipment"%>
<%@page import="cn.zjzt.entity.system.Depart"%>
<%@page import="cn.zjzt.entity.system.Expert"%>
<%@page import="cn.zjzt.entity.system.WebNews"%>
<%@ page language="java" import="java.util.*"
	import="com.opensymphony.xwork2.ActionContext"
	import="cn.zjzt.entity.*" pageEncoding="utf-8"%>
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

<title>桐乡康石体检中心</title>
<!-- 解决IE11不兼容的问题 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="嘉兴,康石,健康体检">
<meta http-equiv="description" content="嘉兴康石健康体检中心">
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
<!--[if it IE 9]  -->
<script type="text/javascript" src="js/html5shiv.js"></script>
<script type="text/javascript" src="js/selectivizr.js"></script>
</head>
<body>
	<!-- script文件 -->
	<script type="text/javascript" src="plugins/jquery.min.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="plugins/modernizr.js"></script>
	<script type="text/javascript"
		src="plugins/rs-plugin/js/jquery.themepunch.tools.min.js"></script>
	<script type="text/javascript"
		src="plugins/rs-plugin/js/jquery.themepunch.revolution.min.js"></script>
	<script type="text/javascript"
		src="plugins/isotope/isotope.pkgd.min.js"></script>
	<script type="text/javascript"
		src="plugins/owl-carousel/owl.carousel.js"></script>
	<script type="text/javascript"
		src="plugins/magnific-popup/jquery.magnific-popup.min.js"></script>
	<script type="text/javascript" src="plugins/jquery.appear.js"></script>
	<script type="text/javascript" src="plugins/jquery.countTo.js"></script>
	<script src="plugins/jquery.parallax-1.1.3.js"></script>
	<!-- <script src="plugins/jquery.validate.js"></script> -->
	<script type="text/javascript" src="js/template.js"></script>
	<script type="text/javascript" src="js/custom.js"></script>
	<!--  -->
	<div class="page-wrapper">
		<!-- 顶部导航，引用文件common_header.jsp -->
		<%@include file="common_header.jsp"%>
		<!-- 图片滚动条 -->
		<div class="section clearfix">
			<div class="container">
				<!-- 介绍和图片滚动 -->
				<div class="row">
					<div class="col-md-5">
						<div class="panel-group panel-transparent">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a data-toggle="collapse" href="#colIntro"> <i
											class="fa fa-bold"></i>中心简介 </a>
									</h4>
								</div>
								<div id="colIntro" class="panel-collapse collapse in">
									<div class="panel-body">
										<div class="col-md-6">
											<img style="height: 180px" src="images/ksImages/1.jpg"></img>
										</div>
										<span>&nbsp;&nbsp;&nbsp;桐乡市康石体检中心（康石中西医结合门诊部）是嘉兴市首家大陆与台湾合作的民营医疗机构。
											隶属于振石控股集团有限公司，成立于2012年，位于桐乡市崇福大道666号振石科技大楼的1—6层，面积约4200平方米，
											设备投入3000多万元，是一所专业健康体检，和门诊医疗为一体的综合性医疗机构，是桐乡市第一人民医院技术指导合作单位。</span> <a
											style="float: right;" href="about_us.jsp">更多>></a>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-7">
						<div class="banner">
							<!-- slideshow start -->
							<!-- ================ -->
							<div class="slideshow">
								<!-- slider revolution start -->
								<!-- ================ -->
								<div class="slider-banner-container">
									<div class="slider-banner">
										<ul>
											<!-- slide 1 start -->
											<li data-transition="random" data-slotamount="7"
												data-masterspeed="800" data-saveperformance="on"
												data-title="Premium HTML5 template">
												<!-- main image --> <img src="images/ksImages/1.jpg" alt=""
												data-bgposition="center bottom" data-bgfit="cover"
												data-bgrepeat="no-repeat"> <!-- LAYER NR. 1 -->
												<div class="tp-caption default_bg large sfr tp-resizeme"
													data-x="0" data-y="70" data-speed="600" data-start="1200"
													data-end="9400" data-endspeed="600"></div> <!-- LAYER NR. 2 -->
											</li>
											<!-- slide 1 end -->

											<!-- slide 2 start -->
											<li data-transition="random" data-slotamount="7"
												data-masterspeed="800" data-saveperformance="on"
												data-title="Powerful Bootstrap Theme">
												<!-- main image --> <img src="images/ksImages/2.jpg"
												alt="slidebg1" data-bgposition="center" data-bgfit="cover"
												data-bgrepeat="no-repeat"></li>
											<!-- slide 2 end -->
											<!-- slide 3 start -->
											<li data-transition="random" data-slotamount="7"
												data-masterspeed="800" data-saveperformance="on"
												data-title="Powerful Bootstrap Theme">
												<!-- main image --> <img src="images/ksImages/3.jpg"
												alt="kenburns" data-bgposition="center" data-kenburns="on"
												data-duration="10000" data-ease="Linear.easeNone"
												data-bgfit="100" data-bgfitend="115"
												data-bgpositionend="right center">
												<div class="tp-caption default_bg large sfr tp-resizeme"
													data-x="0" data-y="70" data-speed="600" data-start="1200"
													data-end="9400" data-endspeed="600">一流的环境</div></li>
											<li data-transition="random" data-slotamount="7"
												data-masterspeed="800" data-saveperformance="on"
												data-title="Powerful Bootstrap Theme">
												<!-- main image --> <img src="images/ksImages/4.jpg"
												alt="kenburns" data-bgposition="center" data-kenburns="on"
												data-duration="10000" data-ease="Linear.easeNone"
												data-bgfit="100" data-bgfitend="115"
												data-bgpositionend="right center">
												<div class="tp-caption default_bg large sfr tp-resizeme"
													data-x="0" data-y="70" data-speed="600" data-start="1200"
													data-end="9400" data-endspeed="600">一流的服务</div></li>
											<li data-transition="random" data-slotamount="7"
												data-masterspeed="800" data-saveperformance="on"
												data-title="Powerful Bootstrap Theme">
												<!-- main image --> <img src="images/ksImages/5.jpg"
												alt="kenburns" data-bgposition="center" data-kenburns="on"
												data-duration="10000" data-ease="Linear.easeNone"
												data-bgfit="100" data-bgfitend="115"
												data-bgpositionend="right center">
												<div class="tp-caption default_bg large sfr tp-resizeme"
													data-x="0" data-y="70" data-speed="600" data-start="1200"
													data-end="9400" data-endspeed="600">一流的技术</div></li>
											<li data-transition="random" data-slotamount="7"
												data-masterspeed="800" data-saveperformance="on"
												data-title="Powerful Bootstrap Theme">
												<!-- main image --> <img src="images/ksImages/8.jpg"
												alt="kenburns" data-bgposition="center" data-kenburns="on"
												data-duration="10000" data-ease="Linear.easeNone"
												data-bgfit="100" data-bgfitend="115"
												data-bgpositionend="right center">
												<div class="tp-caption default_bg large sfr tp-resizeme"
													data-x="0" data-y="70" data-speed="600" data-start="1200"
													data-end="9400" data-endspeed="600">一流的设备</div></li>
										</ul>
										<div class="tp-bannertimer tp-bottom"></div>
									</div>
								</div>
								<!-- slider revolution end -->
							</div>
							<!-- slideshow end -->

						</div>
					</div>
				</div>
				<!-- 新闻和宣传视频 -->
				<div class="row">
					<div class="col-md-5">
						<div class="panel-group panel-transparent">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a data-toggle="collapse" href="#news"> <i
											class="fa fa-bold"></i>新闻动态</a>
									</h4>
								</div>
								<div id="news" class="panel-collapse collapse in">
									<div class="panel-body">
										<ul class="list-icons">
											<%
												ActionContext context = ActionContext.getContext();
												List<WebNews> news = (List<WebNews>) context.get("newsList");
												if (news.size() > 0) {
													int length = news.size() > 8 ? 8 : news.size();
													for (int i = 0; i < length; i++) {
											%>
											<li><a style="overflow:hidden; text-overflow:ellipsis"
												href="getNewsByID?newsID=<%=news.get(i).getId()%>"> <%=news.get(i).getNews_title()%>
													<span style="font-size: 12px;float: right;"> <%=news.get(i).getGmt_create()%>
												</span> </a></li>
											<%
												}
												}
											%>
										</ul>
										<a style="float: right;" href="newsList">更多>></a>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-7">
						<div class="panel-group panel-transparent">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a data-toggle="collapse" href="#colVideo">宣传视频 </a>
									</h4>
								</div>
								<div id="colVideo" class="panel-collapse collapse in">
									<div class="panel-body">
										<video width="620" controls> <source
											src="plugins/123.MP4" type="video/mp4"> </source> </video>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- 专家和设备 -->
				<div class="row">
					<!-- 设备设施 -->
					<div class="col-md-12">
						<h2 class="page-title">先进设备</h2>
						<div class="separator-2"></div>
						<div class="owl-carousel carousel">
							<%
								List<Equipment> equipList = (List<Equipment>) context
										.get("equipList");
								if (equipList != null) {
									if (equipList.size() > 0) {
										for (int p = 0; p < equipList.size(); p++) {
							%>
							<div class="image-box">
								<div class="overlay-container">
									<img src="<%=equipList.get(p).getPic_url()%>" alt="">
									<div class="overlay">
										<div class="overlay-links">
											<a href="getEquipInfo?equipId=<%=equipList.get(p).getId()%>">
												<i class="fa fa-link"></i> </a> <a
												href="<%=equipList.get(p).getPic_url()%>" class="popup-img"><i
												class="fa fa-search-plus"></i> </a>
										</div>
									</div>
								</div>
								<div class="image-box-body">
									<h5 class="title">
										<a href="getEquipInfo?equipId=<%=equipList.get(p).getId()%>">
											<%=equipList.get(p).getName()%></a>
									</h5>
									<div class="elements-list pull-right">
										<a href="getEquipInfo?equipId=<%=equipList.get(p).getId()%>"><span>详细介绍</span>
										</a>
									</div>
								</div>
							</div>
							<%
								}
									}
								}
							%>
						</div>
					</div>
					<!-- 其他医生部分 -->
					<div class="col-md-12">
						<!-- <div class="owl-carousel carousel">
							<%List<Expert> expertList = (List<Expert>) context.get("expertList");
			if (expertList.size() > 0) {
				for (int j = 0; j < expertList.size(); j++) {%>
							<div class="image-box">
								<div class="overlay-container">
									<img src="<%=expertList.get(j).getPic_url()%>" alt="">
									<div class="overlay">
										<div class="overlay-links">
											<a
												href="expertDetail?expertID=<%=expertList.get(j).getId()%>">
												<i class="fa fa-link"></i> </a> <a
												href="<%=expertList.get(j).getPic_url()%>"
												class="popup-img"><i class="fa fa-search-plus"></i> </a>
										</div>
									</div>
								</div>
								<div class="image-box-body">
									<h5 class="title">
										<a
											href="expertDetail?expertID=<%=expertList.get(j).getId()%>">
											姓名:<%=expertList.get(j).getName()%></a>
									</h5>
									<div>
										<span>职称:<%=expertList.get(j).getProfer_title()%></span>| <span>科室:<%=expertList.get(j).getDepart()%></span>
									</div>
									<span>职务:<%=expertList.get(j).getDuty()%></span>
									<div class="elements-list pull-right">
										<a
											href="expertDetail?expertID=<%=expertList.get(j).getId()%>"><span>详细介绍</span>
										</a>
									</div>
								</div>
							</div>
							<%}
			}%>
					</div> -->
					</div>
					<!-- 关于我们 -->
					<div class="col-md-12">
						<h4>我们的愿景</h4>
						<div class="separator-2"></div>
						<p>
							永远维持最专业的团队、最精良的仪器、最舒适的环境、最亲切的服务、最完备的管理，成为全国健康体检行业的典范。让每一个来到康石体检中心的人，都能“活的健康、活的年轻、活的快乐”。
						</p>
						<h3 class="title">专业的体检团队</h3>
						<!-- 院长简介 -->
						<div class="col-md-12">
							<div class="row">
								<div class="col-md-3">
									<!-- Tab panes start-->
									<div class="tab-content clear-style">
										<div class="tab-pane active" id="product-images">
											<div class="owl-carousel content-slider-with-controls-bottom">
												<div class="overlay-container">
													<img src="images/ksImages/yuanshuren1.jpg" alt=""></img>
												</div>
												<div class="overlay-container">
													<img src="images/ksImages/yuanshuren2.jpg" alt="">
												</div>
											</div>
										</div>
									</div>
									<!-- Tab panes end-->
								</div>
								<aside class="col-md-9">
								<div class="sidebar">
									<div class="side product-item vertical-divider-left">
										<div class="tabs-style-2">
											<!-- Nav tabs -->
											<ul class="nav nav-tabs" role="tablist">
												<li class="active"><a role="tab" data-toggle="tab"><i
														class="fa fa-file-text-o pr-5"></i>个人简介</a></li>
											</ul>
											<!-- Tab panes -->
											<div
												class="tab-content padding-top-clear padding-bottom-clear">
												<div class="tab-pane fade in active" id="h2tab1">
													<h3 class="title">袁树人</h3>
													<h4 class="title">执行院长 主任医师.</h4>
													<p>1982年毕业于浙江医科大学，从事儿科临床工作30多年，熟练掌握儿科常见病、多发病的诊治及疑难病症的诊断分析、
														急、危、重病人的抢救治疗；对小儿营养保健、生长发育，有较深的造诣；对小儿心血管疾病，小儿腹泻等有相当丰富的经验；
														还擅长治疗小儿遗尿症、过敏性紫癜等疑难杂症。.</p>
													<p>在全国性杂志发表论文10余篇，省级杂志发表20余篇，获省科技进步奖一项，市科技成果奖4项 .</p>
													<p>现为中国县医院管理协会委员，浙江省中西医结合学会儿科分会委员，嘉兴市医学会儿科分会副主任委员、
														嘉兴市新生儿专业委员会主任委员、桐乡市医学会副会长、桐乡市儿科学科带头人、桐乡市第一人民医院正院级调研员。</p>
												</div>
											</div>
										</div>
									</div>
								</div>
								</aside>
								<!-- product side end -->


							</div>
						</div>
						<!-- 郑建芳简介-->
						<div class="col-md-12">
							<div class="row">
								<div class="col-md-3">
									<!-- Tab panes start-->
									<div class="tab-content clear-style">
										<div class="tab-pane active" id="product-images">
											<div class="owl-carousel content-slider-with-controls-bottom">
												<div class="overlay-container">
													<img src="images/ksImages/zhengjianfang.jpg" alt="">
													</img>
												</div>
											</div>
										</div>
									</div>
									<!-- Tab panes end-->
								</div>
								<aside class="col-md-9">
								<div class="sidebar">
									<div class="side product-item vertical-divider-left">
										<div class="tabs-style-2">
											<!-- Nav tabs -->
											<ul class="nav nav-tabs" role="tablist">
												<li class="active"><a role="tab" data-toggle="tab"><i
														class="fa fa-file-text-o pr-5"></i>个人简介</a></li>
											</ul>
											<!-- Tab panes -->
											<div
												class="tab-content padding-top-clear padding-bottom-clear">
												<div class="tab-pane fade in active" id="h2tab1">
													<h3 class="title">郑建芳</h3>
													<h4 class="title">内科副主任医师.</h4>
													<p>毕业于浙江医科大学医疗系，曾在浙江省桐乡市第一人民医院普内科、急诊、传染、儿科临床工作后调至北京航天总医院心血管、高压氧科工作至退休.</p>
													<p>从事内科临床工作30余年，熟练掌握内科常见病、多发病的诊治及疑难病症的诊断分析、急、危、重病人的抢救治疗；
														专业特长主攻无创心电学检测的诊断及心血管疾病的诊治，对高血压、冠心病及各种心律失常重症的诊断和治疗有着丰富的临床经验，
														并在各种医疗刊物发表过多篇论文并获奖</p>
												</div>
											</div>
										</div>
									</div>
								</div>
								</aside>
								<!-- product side end -->
							</div>
						</div>
						<table class="table">
							<tr>
								<td width="25%"><img style="width:200px"
									src="images/ksImages/team.jpg" alt="专业的体检团队"></img>
								</td>
								<td style="padding-left: 20px;vertical-align:top">
									<p style="font-size: 14px">康石体检中心现有医师     位，医技人员    
										位，均为经验丰富的医疗人员，其中医师及医技人员有一半以上为具有10年以上临床经验的主任医师、
										副主任医师、主治医师、主管技师、主管药师等，并接受过专业的体检培训，能确实地为您的健康把关。 另有  
										位市场开拓的健康专员，能为您提供最适当的健康体检项目设计，为您量身订制体检套餐。</p></td>
							</tr>
						</table>

						<h3 class="title">精良先进的仪器</h3>
						<table class="table">
							<tr>
								<td width="25%"><img style="width:200px"
									src="images/ksImages/equipment1.jpg"></img></td>
								<td style="padding-left: 20px;vertical-align:top">
									<p style="font-size: 14px">康石体检中心拥有先进的、进口的精密仪器设备，例如：GE数字化X光机（DR）、车载加拿大IDC
										DR（全嘉兴第一部）、 GE双能量全身型骨密仪（全嘉兴第一部）、GE
										LOGIC7彩超、OLMPUS无痛肠胃镜、颅内多普勒超音波、十二导联心电图、
										全自动生化分析仪、全自动免疫分析仪、全自动血球机、平板运动心电图等，这些高端精密的仪器，通过专业的医疗人员操作，
										可确保科学正确的体检数据，完全满足客户对检验及检查精确性的严格要求。</p>
								</td>
							</tr>
						</table>

						<h3 class="title">舒适的体检环境</h3>
						<table class="table">
							<tr>
								<td width="25%"><img style="width:200px"
									src="images/ksImages/3.jpg"></img></td>
								<td style="padding-left: 20px;vertical-align:top">
									<p style="font-size: 14px">这里没有拥挤的空间，没有形形色色的病人，没有此起彼落的咳嗽声，没有刺鼻的药水味儿，
										没有令人害怕的院内感染等。当您换好健检服后，走在柔软的地毯上，欣赏充满艺术文化的书法、
										中国画及雕塑品，聆听悠畅悦耳的轻音乐，享受舒适隐秘的人性化诊间，如同在高级会馆的舒适感，
										您会感受：健康体检本来就应该这样让身心放松、惬意的。</p>
									<p style="font-size: 14px">温馨的用餐环境，整洁明快的检查区，方便流畅的采血区</p></td>
							</tr>
						</table>

						<h3 class="title">亲切的贴心服务</h3>
						<table class="table">
							<tr>
								<td width="25%"><img style="width:200px"
									src="images/ksImages/6.jpg"></img></td>
								<td style="padding-left: 20px;vertical-align:top">
									<p style="font-size: 14px">
										我们可以依照客户不同的需求，为您提供各种专业化、个性化的体检服务。体检项目可由专家为您量身定制，
										也可以依照您的需求设计，全程的引导，亲切的接待，耐心的讲解，让您轻松参检，全程体检完成后，
										可享受专业营养师为您准备的健康早餐。</p>
									<p style="font-size: 14px">
										我们拥有VIP套房式体检服务，在独立舒畅的空间大空间做完大部分的检查，作为贵宾的您，
										可以轻松的在体检室内休息，我们的医师会上门为您做检查，使您尽享VIP尊荣。</p>
								</td>
							</tr>
						</table>
						<h3 class="title">完备的健康管理：</h3>
						<table class="table">
							<tr>
								<td width="25%"><img style="width:200px"
									src="images/ksImages/16.jpg"></img></td>
								<td style="padding-left: 20px;vertical-align:top">
									<p style="font-size: 14px">
										我们适用信息化的科学管理，为体检的客户，作全面性的健康筛查，并为每个人建立完备的健康档案。
										从既往史、现况检查、基因检测，进而对未来的生活保健、饮食、增进健康等，提出结合中西医的建议。
										万一发现疾病，我们的门诊部也可提供内科、外科、妇科、中医科、五官科等的诊疗及转诊的服务。
										秉持四全照顾，康石体检全体人员对客户本人、全家，作检查→咨询→预防→治疗的服务，让您全程无忧。</p>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
		<!-- 科室风采 -->
		<div class="section clearfix">
			<div class="container">
				<div class="row">
					<div class="main col-md-9">
						<h2>科室风采</h2>
						<div class="separator-2"></div>
						<%
							List<Depart> departList = (List<Depart>) context.get("departList");
							if (departList != null) {
								if (departList.size() > 0) {
									for (int i = 0; i < departList.size(); i++) {
						%>
						<a href="getDepartInfo?departID=<%=departList.get(i).getId()%>"
							class="btn radius btn-white btn"> <i
							class="fa fa-stethoscope"></i>&nbsp; <%=departList.get(i).getName()%></a>
						<%
							}
								}
							}
						%>
					</div>
				</div>
			</div>
		</div>
		<!-- 底部信息栏 -->
		<%@ include file="common_foot.jsp"%>
	</div>
</body>
</html>
