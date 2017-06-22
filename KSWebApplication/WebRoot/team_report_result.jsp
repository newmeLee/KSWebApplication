<%@page import="java.text.SimpleDateFormat"%>
<%@page import="cn.zjzt.entity.PhyAcceptUnit"%>
<%@page import="cn.zjzt.entity.ViewPhyCheckResult"%>
<%@page import="cn.zjzt.entity.PhyCheckUpList"%>
<%@ page language="java" import="java.util.*"
	import="com.opensymphony.xwork2.ActionContext"
	import="cn.zjzt.entity.ViewPhyCheckMaster"
	import="cn.zjzt.entity.ViewPhyCheckResult" pageEncoding="utf-8"%>
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

<title>团检结果-桐乡康石体检中心</title>
<!-- 解决IE11不兼容的问题 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
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
<!--[if it IE 9]  -->
<script type="text/javascript" src="js/html5shiv.js"></script>
<script type="text/javascript" src="js/selectivizr.js"></script>
</head>
<body>
	<!-- JS Files -->
	<script type="text/javascript" src="plugins/jquery.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
	<div class="scrollToTop">
		<i class="icon-up-open-big"></i>
	</div>
	<div class="page-wrapper">
		<!-- 顶部导航，引用文件common_header.jsp -->
		<%@include file="common_header.jsp" %>
		<section class="main-container">
		<div class="container">
			<%
				ActionContext context = ActionContext.getContext();
				SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
				List<ViewPhyCheckMaster> empList = null;
				List<ViewPhyCheckResult> empResultList = null;
				PhyAcceptUnit unitInfo = null;
				String startDate=null;
				String endDate=null;
				if (context.get("empList") != null) {
					empList = (List<ViewPhyCheckMaster>) context.get("empList");
				}
				if (context.get("empResultList") != null) {
					empResultList = (List<ViewPhyCheckResult>) context.get("empResultList");
				}
				if (context.get("unitInfo") != null) {
					unitInfo = (PhyAcceptUnit) context.get("unitInfo");
				}
				String unitID = context.get("unitID").toString();
				if(context.get("startDate")!=null){
					 startDate=context.get("startDate").toString();
				}
				if(context.get("endDate")!=null){
					 endDate=context.get("endDate").toString();
				}
			%>
			<div class="row">
				<!-- 时间段搜索栏 -->
				<div class="col-md-2 col-sm-4">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" href="#phyHistory"> <i
									class="fa fa-bold"></i>体检记录 </a>
							</h4>
						</div>
						<div id="phyHistory" class="panel-collapse collapse in">
							<div class="panel-body">
								<form action="getUnitEmpInfoByDate" method="post">
									<span>起始时间：</span> <input type="text" class="form-control"
										name="startDate" value="<%=context.get("startDate")==null?"":context.get("startDate") %>" 
										onfocus="WdatePicker({maxDate:'%y-%M-%d'})" />
									<span>截止时间：</span> <input type="text" class="form-control"
										name="endDate" value="<%=context.get("endDate")==null?"":context.get("endDate") %>" 
											onfocus="WdatePicker({maxDate:'%y-%M-%d'})" />
									<input type="text" name="unitID" style="display: none;"
										value="<%=unitID%>" />
									<button id="btnShowPersonal"
										class="btn radius btn-white btn-sm" type="submit">查询</button>
								</form>
							</div>

						</div>
					</div>
					<!-- 打印按钮 -->
					<button type="button" class="btn btn-default"
						onclick="PrintTable(excel_export)">
						<i class="fa fa-print"></i> 打印报告
					</button>
					<!--导出控件  -->
					<form action="ExportUnitExcel" method="post">
						<input style="display: none;" name="unitID" type="text" value="<%=unitInfo.getCode()%>">
						<input style="display: none;" name="startDate" type="text" value="<%=startDate %>" >
						<input style="display: none;" name="endDate" type="text" value="<%=endDate %>" >
						<button type="submit" class="btn btn-default">
						<i class="fa fa-file-excel-o"></i> 导出Excel
						</button>
					</form>
					
					<!-- 修改密码 -->
					<div class="btn-group dropdown">
						<button type="button" class="btn btn-default"
							data-toggle="dropdown">
							<i class="fa fa-lock"></i> 修改查询密码
						</button>
						<ul class="dropdown-menu dropdown-menu-right dropdown-animation">
							<li>
								<form class="login-form" >
									<div id="groupOldPass" class="form-group has-feedback">
										<label class="control-label">原密码(初始密码为单位联系电话)</label> <input
											name="oldPassword" id="oldPassword" type="password" 
											class="form-control" placeholder=""/> <i
											class="fa fa-lock form-control-feedback"></i>
									</div>
									<div id="groupNewPass" class="form-group has-feedback">
										<label class="control-label">新密码</label> <input
											type="password" name="newPassword" id="newPassword" 
											class="form-control" placeholder=""> <i
											class="fa fa-lock form-control-feedback"></i>
									</div>
									<div id="groupConfirmPass" class="form-group has-feedback">
										<label class="control-label">确认新密码</label> <input
											type="password" name="confirmPassword" id="confirmPassword" 
											class="form-control" placeholder=""> <i
											class="fa fa-lock form-control-feedback"></i>
									</div>
									<div class="alert alert-danger" id="validate" style="display: none">
										<strong id="validateTip">Oh snap!</strong>
									</div>
									<button type="button" id="btnUpdatePass"
										class="btn btn-group btn-default btn-sm">提交</button>
								</form>
							</li>
						</ul>
					</div>
				</div>
				<!-- 单位体检报告 -->
				<div id="excel_export" class="main col-md-10">
				<!-- 基本信息 -->
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" href="#userInfo"> <i
									class="icon-user"></i>团体信息 </a>
							</h4>
						</div>
						<div id="userInfo" class="panel-collapse collapse in">
							<div class="panel-body" style="font-size: 10px">
								<div class="col-md-4 col-sm-4">
									<ul class="list-icons">
										<li><i class="icon-info"></i>单位编号： <%=unitInfo.getCode()%></li>
										
									</ul>
								</div>
								<div class="col-md-4 col-sm-4">
									<ul class="list-icons">
										<li><i class="pr-10 glyphicon glyphicon-flag"> </i>单位名称：<%=unitInfo.getName()%></li>
									</ul>
								</div>
								<div class="col-md-4 col-sm-4">
									<ul class="list-icons">
										<li><i class="icon-users"></i>单位类型：<%=unitInfo.getProperty()%></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<!-- 体检详细结果 -->
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" href="#phyCheckResult"> <i
									class="fa fa-flickr"></i>体检结果 </a>
							</h4>
						</div>
						<div id="phyCheckResult" class="panel-collapse collapse in">
							<div class="panel-body">
								<div class="alert alert-info alert-dismissible" role="alert">
									<span style="font-size:10px ">结果数目：<%=empList == null ? 0 : empList.size()%></span>
									<span style="font-size:10px" >(点击人员名字可查看详细体检报告)</span>
								</div>
								<table class="table table-bordered" style="font-size: 10px">
									<thead>
										<tr>
											<td width="8%">姓名</td>
											<td width="5%">体检日期</td>
											<td width="20%">异常特征</td>
											<td width="35%">体检综述</td>
											<td>体检建议</td>
											<td width="8%">导出报告</td>
										</tr>
									</thead>
									<tbody>
										<%
											if (empList!=null) {
												if(empList.size()>0){
												for (int i = 0; i < empList.size(); i++) {
										%>
										<tr>
											<td><a
												href="getCheckRecord?regID=<%=empList.get(i).getRegID()%>">
													<%=empList.get(i).getPtName()%></a>
											</td>
											<td><%=dateFormat.format(empList.get(i).getPhyDate())%></td>
											<td><%=empList.get(i).getAbnoResult()%></td>
											<td><%=empList.get(i).getChkSummary()%></td>
											<td><%=empList.get(i).getChkAdvice()%></td>
											<td>
											<form action="exportExcel" method="post">
												<input style="display: none;" type="text" 
													name="regID" value="<%=empList.get(i).getRegID()%>">
												<button type="submit">导出</button>
											</form>
											</td>
										</tr>
										<%
										}
											}
											}
										%>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<!-- 体检综述 -->
					<p class="text-warning">*以上数据仅供参考，最终结果以医院数据为准。</p>
				</div>
			</div>
		</div>
		</section>
		<!-- 底部信息栏 -->
		<%@ include file="common_foot.jsp"%>
	</div>
<script type="text/javascript">
		//打印
		function PrintTable(Id) {
			var mStr;
			mStr = window.document.body.innerHTML;
			var mWindow = window;
			window.document.body.innerHTML = Id.innerHTML;
			mWindow.print();
			window.document.body.innerHTML = mStr;
		}
</script>
<script type="text/javascript">
	/* 导出数据到excel */
        var idTmr;  
        //获取浏览器的类型
        function  getExplorer() {  
            var explorer = window.navigator.userAgent ;  
            //ie  
            if (explorer.indexOf("MSIE") >= 0) {  
                return 'ie';  
            }  
            //ie11
            else if(explorer.indexOf("Trident")>-1 &&explorer.indexOf("rv:11")>-1) {
            	return 'ie';
            }
            //firefox  
            else if (explorer.indexOf("Firefox") >= 0) {  
                return 'Firefox';  
            }  
            //Chrome  
            else if(explorer.indexOf("Chrome") >= 0){  
                return 'Chrome';  
            }  
            //Opera  
            else if(explorer.indexOf("Opera") >= 0){  
                return 'Opera';  
            }  
            //Safari  
            else if(explorer.indexOf("Safari") >= 0){  
                return 'Safari';  
            }  
        }  
        function exportReport(tableid) {  
            if(getExplorer()=='ie')  
            {  
                var curTbl = document.getElementById(tableid);  
                var oXL = new ActiveXObject("Excel.Application");  
                var oWB = oXL.Workbooks.Add();  
                var xlsheet = oWB.Worksheets(1);  
                var sel = document.body.createTextRange();  
                sel.moveToElementText(curTbl);  
                sel.select();  
                sel.execCommand("Copy");  
                xlsheet.Paste();  
                oXL.Visible = true;  
                try {  
                    var fname = oXL.Application.GetSaveAsFilename("Excel.xls", "Excel Spreadsheets (*.xls), *.xls");  
                } catch (e) {  
                    print("Nested catch caught " + e);  
                } finally {  
                    oWB.SaveAs(fname);  
                    oWB.Close(savechanges = false);  
                    oXL.Quit();  
                    oXL = null;  
                    idTmr = window.setInterval("Cleanup();", 1);  
                }  
  
            }  
            else  
            {  
                tableToExcel(tableid);  
            }  
        }  
        function Cleanup() {  
            window.clearInterval(idTmr);  
            CollectGarbage();  
        }  
        var tableToExcel = (function() {  
            var uri = 'data:application/vnd.ms-excel;base64,',  
                    template = '<html><head><meta charset="UTF-8"></head><body><table>{table}</table></body></html>',  
                    base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))); },  
                    format = function(s, c) {  
                        return s.replace(/{(\w+)}/g,  
                                function(m, p) { return c[p]; }); };
            return function(table, name) {  
                if (!table.nodeType) table = document.getElementById(table);  
                var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML};  
                window.location.href = uri + base64(format(template, ctx));  
            };  
        })();  	
	</script>
<script type="text/javascript">
//更改密码
		$("#btnUpdatePass").click(function(){
			var unitCode='<%=unitInfo.getCode()%>';
			var oldPassword=$("#oldPassword").val();
			var newPassword=$("#newPassword").val();
			var confirmPass=$("#confirmPassword").val();
			if ((oldPassword == null) || (oldPassword == "")) {
				$("#validate").css("display","block");
				$("#validateTip").html("原密码不能为空！");
				$("#groupOldPass").attr("class","form-group has-error");
				$("#validate").attr("class","alert alert-danger");
				return false;
			}
			if ((newPassword == null) || (newPassword == "")) {
				$("#validate").css("display","block");
				$("#validateTip").html("新密码不能为空！");
				$("#groupNewPass").attr("class","form-group has-error");
				$("#validate").attr("class","alert alert-danger");
				return false;
			}
			if ((confirmPass == null) || (confirmPass == "")) {
				$("#validate").css("display","block");
				$("#validateTip").html("请再次填写新密码！");
				$("#groupConfirmPass").attr("class","form-group has-error");
				$("#validate").attr("class","alert alert-danger");
				return false;
			}
			if (newPassword!= confirmPass) {
				$("#validate").css("display","block");
				$("#validateTip").html("两次新密码不一致！");
				$("#validate").attr("class","alert alert-danger");
				return false;
			}
			//修改密码
			$.ajax({
				type : "POST",//方法
				url : "updateUnitPassword",//链接
				dataType : "json",
				data : {
					unitCode : unitCode,
					oldPassword : oldPassword,
					newPassword : newPassword
				},
				success : function(data) {
					if (data.result.status == 200) {
						$("#validate").css("display","block");
						$("#validateTip").html(data.result.message);
						$("#validate").attr("class","alert alert-success");
						$("#oldPassword").val("");
						$("#newPassword").val("");
						$("#confirmPassword").val("");
						return;
					}else {
						$("#validate").css("display","block");
						$("#validateTip").html(data.result.message);
						$("#validate").attr("class","alert alert-danger");
						return;
					} 
				}
			});
		});
</script>
</body>
</html>
