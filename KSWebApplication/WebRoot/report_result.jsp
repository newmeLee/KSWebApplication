<%@page import="cn.zjzt.utils.MyPrintUtil"%>
<%@page import="cn.zjzt.service.PhyCheckService"%>
<%@page import="cn.zjzt.entity.ViewPhyCheckMaster"%>
<%@page import="cn.zjzt.entity.ViewPhyCheckResult"%>
<%@page import="cn.zjzt.entity.PhyCheckUpList"%>
<%@ page language="java" import="java.util.*"
	import="java.text.SimpleDateFormat"
	import="com.opensymphony.xwork2.ActionContext"
	import="cn.zjzt.entity.Patient" pageEncoding="utf-8"%>
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

<title>报告结果-桐乡康石体检中心</title>
<!-- 解决IE11不兼容的问题 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="体检结果,体检结果查询,健康查询">
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
					List<ViewPhyCheckResult> checkList=null;
					List<ViewPhyCheckResult> deptList =null;
					List<ViewPhyCheckResult> groupList=null;
					List<ViewPhyCheckMaster> phyRecords=null;
					if(context.get("checkList")!=null){
					 	checkList = (List<ViewPhyCheckResult>) context.get("checkList");
					}
					if(context.get("deptList")!=null){
						deptList = (List<ViewPhyCheckResult>) context.get("deptList");
					}
					if(context.get("groupList")!=null){
					 	groupList = (List<ViewPhyCheckResult>) context.get("groupList");
					}
					if(context.get("phyRecords")!=null){
					 	phyRecords = (List<ViewPhyCheckMaster>) context.get("phyRecords");
					}
					//用户信息	
					ViewPhyCheckMaster userInfo = (ViewPhyCheckMaster) context.get("userInfo");
					//日期格式转化
					SimpleDateFormat sdf=new  SimpleDateFormat("yyyy-MM-dd");
			%>
			<div class="row">
				<div class="col-md-2">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" href="#phyHistory"> <i
									class="fa fa-bold"></i>体检记录 </a>
							</h4>
						</div>
						<div id="phyHistory" class="panel-collapse collapse in">
							<div class="panel-body">
								<%
									if(phyRecords!=null){
									for(int r=0;r<phyRecords.size();r++)
									{
								%>
								<a href="getCheckRecord?regID=<%=phyRecords.get(r).getRegID()%>">
										<%=r+1%>: <%=sdf.format(phyRecords.get(r).getPhyDate())%>
									 </a><br>
								<%
									}
									}
								%>
							</div>
						</div>
					</div>
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
										<label class="control-label">原密码(初始密码：123456)</label> <input
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
					<button type="button" class="btn btn-default"
						onclick="PrintTable(printArea)">
						<i class="fa fa-print"></i> 打印报告
					</button>
					<form action="exportExcel" method="post">
						<input style="display: none;" type="text" name="regID" value="<%= userInfo.getRegID()%>">
						<button  type="submit" class="btn btn-default">
							<i class="fa fa-file-excel-o"></i> 导出Excel
						</button>
					</form>
					
				</div>
				<div class="col-md-10" id="printArea">
				<!-- 密码过于简单的提示栏 -->
				<div class="alert alert-danger" id="passwordDanger" 
						style="display: none">
					<strong id="passwordDangerTip">Oh snap!</strong>
				</div>
				<!-- 此处的div用于excel导出 -->
				<div id="excel_export">
					<!-- 基本信息 -->
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" href="#userInfo"> <i
									class="icon-user"></i>基本信息 </a>
							</h4>
						</div>
						<div id="userInfo" class="panel-collapse collapse in">
							<div class="panel-body" style="font-size: 10px">
								<%
									//如果信息不为空
									if (userInfo != null) {
								%>
								<div class="col-md-4 col-sm-4">

									<ul class="list-icons">

										<li><i class="icon-info"></i>登记号：<%=userInfo.getRegID()%></li>
										<li><i class="icon-bookmarks"></i>年 龄：<%=userInfo.getAge()%></li>
										<li><i class="icon-phone"></i>手机号码：<%=userInfo.getMobileTel()%></li>
										<%
											if(deptList.size()>0){
										%>
										<li><i class="fa fa-calendar"></i> 体检日期：
											 <%=deptList.get(0).getChkDate()!=null?sdf.format(deptList.get(0).getChkDate()):""%>
										</li>
										<%
											}
										%>
										
									</ul>

								</div>
								<div class="col-md-4 col-sm-4">
									<ul class="list-icons">
										<li><i class="icon-user"></i>姓 名：<%=userInfo.getPtName()%></li>
										<li><i class="icon-vcard"></i>身份证号：<%=userInfo.getIDCard()%></li>
										<li><i class="icon-tag"></i>体检类别：<%=userInfo.getCategory()%></li>
										<li><i class="icon-user"></i>业务员一：<%=userInfo.getMasterSales()%></li>
									</ul>
								</div>
								<div class="col-md-4 col-sm-4">
									<ul class="list-icons">
										<li><i class="icon-users"></i>性 别：<%=userInfo.getPtSex()%></li>
										<%
											if(deptList.size()>0){
										%>
										<li><i class="icon-layout"> </i>体检套餐：<%=deptList.get(0).getTemplateName()%></li>
										<%
											}
										%>
										<li><i class="icon-newspaper"></i>单位名称：<%=userInfo.getUnitName()%></li>
										<li><i class="icon-user"></i>业务员二：<%=userInfo.getSecondSales()%></li>
									</ul>
								</div>
								<%
									}
								%>
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
									<%
										if (deptList.size()>0) {
										int deptSize=deptList.size();
										for (int i = 0; i < deptSize; i++) {
									%>
									<table id="report_table" class="table table-hover">
										<span style="font-size: 16px;font-weight:bold"
											class="text-danger"> 检查科室：
											<%=deptList.get(i).getDeptName()!=null?deptList.get(i).getDeptName():""%></span>
										<tr>
											<%
												int groupSize=groupList.size();
												for (int j = 0; j < groupSize; j++) {
												if (groupList.get(j).getDeptID().equals(deptList.get(i).getDeptID())) {
											%>
											<td colspan="5"><span class="text-warning"
												style="font-weight:bold;font-size: 15px"> 项目组合：
												<%=groupList.get(j).getGroupName()!=null?groupList.get(j).getGroupName():""%></span>
											</td>
										</tr>
										<tr>
											<td colspan="5">
												<table class="table table-hover">
													<thead>
														<tr>
															<td style="font-size:13px;font-weight:bold">项目明细</td>
															<td style="font-size:13px;font-weight:bold">参考范围</td>
															<td style="font-size:13px;font-weight:bold">单位</td>
															<td style="font-size:13px;font-weight:bold">异常提示</td>
															<td style="font-size:13px;font-weight:bold">结果</td>
															<td id="tdHeadLast" style="font-size:13px;font-weight:bold">上次体检结果</td>
														</tr>
													</thead>
													<%
														//修正日期格式
														SimpleDateFormat formatter = new SimpleDateFormat(
																"yyyy-MM-dd HH:mm:ss");
														int checkSize=checkList.size();
														for (int index = 0; index < checkSize; index++) {
														if (checkList.get(index).getGroupID().equals(groupList.get(j).getGroupID())) {
													%>
													<tr>
														<td><span style="font-size:13px">
															<%=checkList.get(index).getTariffName()!=null?checkList.get(index).getTariffName():""%></span>
														</td>
														
														<td><span style="font-size:13px">
															<%=checkList.get(index).getReferArea()!=null?checkList.get(index).getReferArea():""%></span>
														</td>
														<td><span style="font-size:13px">
															<%=checkList.get(index).getUnit()!=null?checkList.get(index).getUnit():""%></span>
														</td>
														<td><span style="font-size:13px">
															<%=checkList.get(index).getChkRtPrompt()!=null?checkList.get(index).getChkRtPrompt():""%></span>
														</td>
														<td><span style="font-size:13px">
															<%=checkList.get(index).getChkResult()!=null?checkList.get(index).getChkResult():""%></span>
														</td>
														<td id="tdBodyLast"><span style="font-size:13px">
														<%=checkList.get(index).getLastChkResult()!=null?checkList.get(index).getLastChkResult():""%></span>
														</td>
													</tr>
													<%
														}
													%>

													<%
														}
													%>
												</table></td>
										</tr>
										<tr>
											<td colspan="5" style="font-size:12px;padding-top: 0px">
												<span style="font-weight:bold">项目小结：</span> <%=groupList.get(j).getChkGrpResult()%><br>
												<span style="font-weight:bold">小结医生： </span> <%=groupList.get(j).getChkDoctor()%><br>
												<span style="font-weight:bold">小结日期： </span> <%=groupList.get(j).getChkDate()%></td>
										</tr>

										<%
											}
										%>

										<%
											}
										%>
										<%
											}
										}
										%>
									</table>
								</div>
							</div>
						</div>
					<!-- 体检综述 -->
					<div class="panel-group panel-transparent">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#phySummary"><i class="fa fa-flickr"></i>体检综述</a>
								</h4>
							</div>
							<div id="phySummary" class="panel-collapse collapse in">
								<div class="panel-body">
									<%
										if (deptList.size()>0) {
											int index = 0;
											int deptSize=deptList.size();
											for (int k = 0; k < deptSize; k++) {
											if ((deptList.get(k).getChkSummary() == null)||
												(deptList.get(k).getChkSummary().trim().isEmpty())) {
													continue;
												}
											index++;
									%>
									<p style="font-size:14px"><%=index%>.<%=deptList.get(k).getChkSummary()%></p>
									<%
										}
									}
									%>
								</div>
							</div>
						</div>
					</div>
					<!-- 体检建议 -->
					<div class="panel-group panel-transparent">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#phyAdvice"> <i class="fa fa-flickr"></i>建议</a>
								</h4>
							</div>
							<div id="phyAdvice" class="panel-collapse collapse in">
								<div class="panel-body">

									<%
										if (deptList.size()>0) {
											int index = 0;
											int deptSize=deptList.size();
											for (int p = 0; p < deptSize; p++) {
											if ((deptList.get(p).getChkDAdvice() == null)||
												(deptList.get(p).getChkDAdvice().trim().isEmpty())) {
													continue;
												}
											index++;
									%>
									<p style="font-size:14px"><%=index%>.<%=deptList.get(p).getChkDAdvice()%></p>
									<%
										}
									}
									%>
								</div>
							</div>
						</div>
					</div>
				
					<p class="text-warning">*以上数据仅供参考，最终结果以医院数据为准。</p>
				</div>
			</div>
			</div>
		</div>
		</section>
	
		<!-- 底部信息栏 -->
		<%@ include file="common_foot.jsp"%>
	</div>
	<!-- JS Files -->
	<!-- Jquery and Bootstap core js files -->
	<script type="text/javascript" src="plugins/jquery.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
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
		//预加载函数，判断用户密码是够还是默认密码
		$(window).load(function() {
		var regID=<%= userInfo.getRegID()%>;
			$.ajax({
				type : "POST",//方法
				url : "getPassword",//链接
				dataType : "json",
				data : {
					regID : regID
				},
				success : function(data) {
					if(data.userPassword=="123456"){
						$("#passwordDanger").css("display","block");
						$("#passwordDangerTip").html("您的查询密码还是默认密码，为了您的体检信息安全，请尽快修改密码！");
					}
				}
			});
		});
		//更改密码
		$("#btnUpdatePass").click(function(){
			var regID=<%= userInfo.getRegID()%>;
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
				url : "updatePassword",//链接
				dataType : "json",
				data : {
					regID : regID,
					oldPassword : oldPassword,
					newPassword:newPassword
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
						return;
					} 
				}
			});
		});
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
	$("#btnExcel").click(function(){
	var regID=<%= userInfo.getRegID()%>;
			$.ajax({
				type : "POST",//方法
				url : "exportExcel",//链接
				dataType : "json",
				data : {
					regID : regID
				},
				success : function(data) {
					if(data.userPassword=="123456"){
						$("#passwordDanger").css("display","block");
						$("#passwordDangerTip").html("您的查询密码还是默认密码，为了您的体检信息安全，请尽快修改密码！");
					}
				}
			});
		});
</script>
</body>
</html>
