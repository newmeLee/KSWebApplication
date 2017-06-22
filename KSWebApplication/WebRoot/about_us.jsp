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

<title>关于我们-桐乡康石体检中心</title>
<!-- 解决IE11不兼容的问题 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="康石,桐乡康石,康石体检">
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
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<!--[if it IE 9]  -->
<script type="text/javascript" src="js/html5shiv.js"></script>
<script type="text/javascript" src="js/selectivizr.js"></script>
<!-- 图片滚动js -->
<script type="text/javascript"
	src="plugins/owl-carousel/owl.carousel.js"></script>
</head>
<body>
	<div class="page-wrapper">
		<!-- 顶部导航，引用文件common_header.jsp -->
		<%@include file="common_header.jsp"%>
		<section class="main-container">
		<div class="container">
			<div class="row">
				<h2 class="page-title">关于我们</h2>
				<div class="separator-2"></div>
				<div class="main col-md-12">
					<div class="row">
						<div class="col-md-3">
							<img style="height: 180px" src="images/ksImages/1.jpg"></img> <span>
								桐乡市康石体检中心（康石中西医结合门诊部）是嘉兴市首家大陆与台湾合作的民营医疗机构。
								隶属于振石控股集团有限公司，成立于2012年，位于桐乡市崇福大道666号振石科技大楼的1—6层， 面积约4200平方米，
								设备投入3000多万元，是一所专业健康体检，和门诊医疗为一体的综合性医疗机构， 是桐乡市第一人民医院技术指导合作单位。 </span>
						</div>
						<div class="col-md-9">
							<div class="sidebar">
								<div class="side product-item vertical-divider-left">
									<h4>我们的愿景</h4>
									<div class="separator-2"></div>
									<p>
										永远维持最专业的团队、最精良的仪器、最舒适的环境、最亲切的服务、最完备的管理，成为全国健康体检行业的典范。让每一个来到康石体检中心的人，都能“活的健康、活的年轻、活的快乐”。
									</p>
									<h3 class="title">专业的体检团队</h3>
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
				</div>
				<hr>
				<!--诊疗设备-->
			</div>
		</div>
		</section>
		<!-- 底部信息栏 -->
		<%@ include file="common_foot.jsp"%>
	</div>
</body>
</html>
