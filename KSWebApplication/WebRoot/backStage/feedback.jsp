<%@page import="cn.zjzt.entity.system.UserFeedback"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户反馈</title>
<!-- 解决IE11不兼容的问题 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="../bootstrap/css/docs.css">
<link rel="stylesheet" type="text/css" href="../bootstrap/css/panel.css" />
<link href="../bootstrap/css/bootstrap-combined.no-icons.min.css"
	rel="stylesheet">
<link href="../bootstrap/css/bootstrap-responsive.min.css"
	rel="stylesheet">
<link href="../bootstrap/css/fileinput.min.css" rel="stylesheet">
<script src="../js/jquery-1.11.1.min.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap-fileinput/4.3.9/js/fileinput.js"></script>
<style>
body {
	font-family: "ff-tisa-web-pro-1", "ff-tisa-web-pro-2", "Lucida Grande",
		"Helvetica Neue", Helvetica, Arial, "Hiragino Sans GB",
		"Hiragino Sans GB W3", "Microsoft YaHei UI", "Microsoft YaHei",
		"WenQuanYi Micro Hei", sans-serif;
}
</style>
</head>
<body style="padding: 20px">
	<div class="container-fluid">
		<div class="row">
			<div class="navbar navbar-fixed-top">
				<div class="navbar-inner">
					<ul class="nav">
						<li><a href="getAllNews">新闻管理</a></li>
						<li ><a href="getAllExpert">专家管理</a></li>
						<li><a href="getAllEquipment">设备管理</a></li>
						<li ><a href="getAllDepart">科室管理</a></li>
						<li><a href="getAllTemplate">套餐管理</a></li>
						<li class="active"><a href="feedback">用户反馈管理</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="span8">
		<section id="news">
		<div class="panel panel-primary">
			<div class="panel-heading">反馈信息列表</div>
			<div class="panel-body">
				<table class="table">
				<%
							ActionContext context = ActionContext.getContext();
							List<UserFeedback> feedbackList = (List<UserFeedback>) context
									.get("feedbackList");
							if (feedbackList != null) {
								if (feedbackList.size() > 0) {
								for(int i=0;i<feedbackList.size();i++){
						%>
					<tr>
						<td width="10%"><img src="../images/avatar.jpg"></img>
						</td>
						<td><span><%=feedbackList.get(i).getUser_name() %></span>| 
							<span class="text-muted"><%=feedbackList.get(i).getGmt_create()%></span>
							<button onclick ="delete_feedback(<%=feedbackList.get(i).getId()%>)" 
							class="btn btn-mini btn-danger" >删除</button>
							<br>
							<br>
							<p><%=feedbackList.get(i).getMessage() %></p> 
							<%
								/*如果未回复显示回复框 */
								if(feedbackList.get(i).getResponse()==null){
							 %>
							<div id="reply<%=i%>">
							<textarea rows="2" id="txtReply<%=i%>"></textarea>
							<button  onclick="reply(<%=feedbackList.get(i).getId()%>,<%=i%>)" 
								class='btn btn-info'>回复</button>
							</div>
							<%
							}
							 %>
							<hr>
							<div id="answer<%=i%>">
								<%
									/*如果未回复显示回复框 */
									if (feedbackList.get(i).getResponse() != null) {
								%>
								<!-- 工作人员回复 -->
								<span> <img width="30px" src="../images/ksImages/logo.jpg"></img> </span> 
								<span>中心回复：<%=feedbackList.get(i).getResponse()%></span>
								<span><%=feedbackList.get(i).getGmt_response()%></span>
								<%
									}
								%>
							</div></td>
					</tr>
					<%
						}
							}
						}
					%>
				</table>
			</div>
		</div>
		</section>
	</div>
</body>
<script type="text/javascript">
function reply(feedbackId,index){
	var response=document.getElementById("txtReply"+index+"").value;
	if($.trim(response)==""){
		alert("回复不能为空！");
		return false;
	}
	$.ajax({
		type : "POST",//方法
		url : "replyFeedback",//链接
		dataType : "json",
		data : {
			id : feedbackId,
			response:response,
		},
		success : function(data) {
			if(data.result.status==200){
				var appendHtml="<span> <img width='30px' src='../images/ksImages/logo.jpg'></img> </span><span>回复："+response+"</span> ";
				$("#answer"+index+"").prepend(appendHtml);
				//document.getElementById("answer"+index+"").innerHTML=appendHtml;
				$("#reply"+index+"").css("display","none");
			}
		}	
	});
}
</script>
<script type="text/javascript">
/* 删除 */
function delete_feedback(id){
	if(window.confirm("你确定要删除该信息吗？")){
		location="deleteFeedback?id="+id+"";
	}
	else{
		 return false;
	}
}
</script>
</html>