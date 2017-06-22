<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="cn.zjzt.entity.ViewPhyCheckMaster"%>
<%@page language="java" import="java.util.*"
	import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>体检记录列表-桐乡康石体检中心</title>
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
<!--[if it IE 9]  -->
<script type="text/javascript" src="js/html5shiv.js"></script>
<script type="text/javascript" src="js/selectivizr.js"></script>
<!-- JS 文件 -->
<script type="text/javascript" src="plugins/jquery.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="page-wrapper">
		<!-- 顶部导航，引用文件common_header.jsp -->
		<%@ include file="common_header.jsp"%>
		<section class="main-container">
		<div class="container">
			<div class="row">
				<div class="col-md-5 col-md-offset-4">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" href="#colIntro"></i>体检记录 </a>
							</h4>
						</div>
						<div id="colIntro" class="panel-collapse collapse in">
							<div class="panel-body">
								<%
									ActionContext context = ActionContext.getContext();
									List<ViewPhyCheckMaster> phyRecords = (List<ViewPhyCheckMaster>) context
											.get("phyRecords");
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
									if (phyRecords != null) {
										for (int r = 0; r < phyRecords.size(); r++) {
								%>
									<button class="btn btn-info btn-sm" onclick="openCheck(<%=r%>)">
											体检日期: <%=sdf.format(phyRecords.get(r).getPhyDate())%>|点击此处查询
									</button><br>
									<div id="check<%=r%>" style="display: none;">
									<input type="password"	class="form-control" id="password<%=r%>"
										placeholder="输入查询密码(默认密码123456)">	
									<div class="alert alert-danger" id="validate<%=r%>" style="display: none">
										<strong id="validateTip<%=r%>">Oh snap!</strong>
									</div>
									<button id="btnShowTeam"   class="btn btn-white radius btn-sm"
										type="button" onclick="check_report(<%=phyRecords.get(r).getRegID() %>,<%=r%>)" >查询</button>
									</div>
								<%
									}
									}
								%>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</section>
		<!-- 底部信息栏 -->
		<%@ include file="common_foot.jsp"%>
	</div>
</body>
<script type="text/javascript">
function openCheck(index){
	if($("#check"+index).css("display")=='none'){
		$("#check"+index).css("display","block");
	}else{
		$("#check"+index).css("display","none");
	}
};
</script>
<script type="text/javascript">
function check_report(regID,index){
	var password=$("#password"+index).val();
	if($.trim(password)==""){
 		$("#validate"+index).css("display","block");
		$("#validateTip"+index).html('查询密码不能为空！');
 		return;
 	}else{
 		$.ajax({
 			type : "POST",//方法
			url : "validateRegIDAndPsd",//链接
			dataType : "json",
			//async:false, //设置为同步，才能阻止提交
			data : {
				regID : regID,
				checkPassword : password
			},
			success : function(data) {
				if (data.result.status == 2) {
					validateFlag = true;
					window.location.href="getCheckRecord?regID="+regID+"";
				}else {
					$("#validate"+index).css("display","block");
					$("#validateTip"+index).html(data.result.message);
				} 
			},error :function(data){
				alert(data);
			}
		}); 
 	}
}
</script>
</html>