<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>姓名列表</title>
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
									href="#phyUserCheck"> <i class="fa fa-flickr"></i>人员列表</a>
							</h4>
						</div>
						<div id="phyUserCheck" class="panel-collapse collapse in">
							<div class="panel-body">
								<!-- 加载数据 -->
								<s:if test="#request.masterList!=null" >
									<s:iterator var="master" value="#request.masterList" status="st">
										<button class="btn btn-info btn-sm" onclick="checkResult('<s:property value="#master.RegID"/>')">
											<s:property value="#master.PtName"/>|<s:property value="#master.PhyDate"/>
										</button>									
									</s:iterator>
								</s:if>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</section>
	</div>
</body>
<script type="text/javascript">
	function checkResult(regID){
		window.location.href="getCheckRecordByAdmin?regID="+regID+"";
	}
</script>
</html>