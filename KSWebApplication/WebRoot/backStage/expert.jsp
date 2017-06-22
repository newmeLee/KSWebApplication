<%@page import="cn.zjzt.entity.system.Expert"%>
<%@page import="java.util.List"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>专家管理</title>
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
<script src="../plugins/ajaxfileupload.js"></script>
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
						<li class="active"><a href="getAllExpert">专家管理</a></li>
						<li><a href="getAllEquipment">设备管理</a></li>
						<li ><a href="getAllDepart">科室管理</a></li>
						<li><a href="getAllTemplate">套餐管理</a></li>
						<li><a href="feedback">用户反馈管理</a></li>
					</ul>
				</div>
			</div>
		</div>
		<!-- 套餐列表 -->
		<div class="span4">
			<!-- 新闻管理部分 -->
			<section id="news">
			<div class="panel panel-primary">
				<div class="panel-heading">医生列表</div>
				<div class="panel-body">
					<%
						ActionContext context =ActionContext.getContext();
						List<Expert> expertList=null;
						if(context.get("expertList")!=null){
							expertList=(List<Expert>)context.get("expertList");
						}
							%>
					<table class="table">
						<thead>
							<tr>
								<th>姓名</th>
								<th>编辑</th>
								<th>删除</th>
							</tr>
						</thead>
						<tbody>
							<%
								if(expertList!=null){
									if(expertList.size()>0){
										for(int i=0;i<expertList.size();i++){
							%>
							<tr>
								<td><%=i+1+"."+expertList.get(i).getName()%></td>
								<td style="display: none;">
								<input  id="intro<%=expertList.get(i).getId()%>" value="<%=expertList.get(i).getIntro()%>"/>
								</td>
								
								<td>
									<button class="btn btn-mini btn-info"
										onclick="expert_click(<%=expertList.get(i).getId()%>,
												'<%=expertList.get(i).getName()%>','<%=expertList.get(i).getProfer_title()%>',
												'<%=expertList.get(i).getDepart()%>','<%=expertList.get(i).getDuty()%>',
												'<%=expertList.get(i).getPic_url()%>')">
										编辑</button>
								</td>
								<td><button class="btn btn-mini btn-danger" onclick="expert_delete(<%=expertList.get(i).getId()%>)">
										删除
									</button>
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
			</section>
		</div>
		<div class="span8">
			<!-- 新闻管理部分 -->
			<section id="news">
			<div class="panel panel-primary">
				<div class="panel-heading">添加专家医生</div>
				<div class="panel-body">
					<form action="addOrUpdateExpert" method="post">
						<table class="table">
							<tr>
								<td>id：
								<td><textarea rows="1" style="display: none" id="id"
										name="expert.id"></textarea>
								</td>
							</tr>
							<tr>
								<td>图片：</td>
								<td>
								<input style="display: none;" id="picUrl" name="expert.pic_url"
									<%
										if (context.get("expertPicUrl") != null) {
									%> 
										value="<%=context.get("expertPicUrl")%>"
									<%
 										}
 									%> 
 									> 
 									<input type="file" id="file" name="file" />
        							<input type="button" value="上传" onclick="ajaxFileUpload();"><hr>
 										<!-- <a class="btn btn-info" href="uploadExpertPic.jsp">添加图片</a> -->
 										<img id="expertPic" width="150px" alt="" src="<%=context.get("expertPicUrl")%>">
								</td>
							</tr>
							<tr>
								<td>姓名：</td>
								<td><textarea rows="1" id="name" name="expert.name"></textarea>
								</td>
							</tr>

							<tr>
								<td>职称：</td>
								<td><textarea rows="1" id="prof" name="expert.profer_title"></textarea>
								</td>
							</tr>
							<tr>
								<td>科室：</td>
								<td><textarea rows="1" id="depart" name="expert.depart"></textarea>
								</td>
							</tr>
							<tr>
								<td>职务：</td>
								<td><textarea rows="1" id="duty" name="expert.duty"></textarea>
								</td>
							</tr>
							<tr>
								<td>介绍：</td>
								<td><textarea rows="6" id="intro" name="expert.intro"></textarea>
								</td>
							</tr>
							
							<tr>
								<td colspan="2">
									<button class="btn btn-info" type="submit" id="btnCommit">提交</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			</section>
		</div>
	</div>
</body>
<script type="text/javascript"> 
	/* 图片异步上传 */
  	function ajaxFileUpload(){
        $.ajaxFileUpload({
           	url:'uploadExpertPic',//用于文件上传的服务器端请求地址
            secureuri:false,//一般设置为false
         	fileElementId:'file',//文件上传空间的id属性  <input type="file" id="file" name="file" />
            dataType: 'json',//返回值类型 一般设置为json
            success: function (data, status)  //服务器成功响应处理函数
            {
             	//alert(data.message);
             	alert("图片保存成功！");
             	$("#expertPic").attr("src",data.message);
             	$("#picUrl").val(data.message);
            },
            error: function (data, status, e){
                //服务器响应失败处理函数
                alert(e);
            }
        });
}
</script> 
<script type="text/javascript">
/* 数据完整性验证 */
  $("#btnCommit").click(function(){
  	var name=$("#name").val();
  	var prof=$("#prof").val();
  	var depart=$("#depart").val();
  	var duty=$("#duty").val();
  	var intro=$("#intro").val();
  	var picUrl=$("#picUrl").val();
  	if($.trim(name)==""){
 		alert('医生姓名不能为空！');
 		return false;
 	}else if($.trim(prof)==""){
 		alert('医生职称不能为空！');
 		return false;
 	}else if($.trim(depart)==""){
 		alert('医生科室不能为空！');
 		return false;
 	}else if($.trim(duty)==""){
 		alert('医生职务不能为空！');
 		return false;
 	}else if($.trim(intro)==""){
 		alert('医生介绍不能为空！');
 		return false;
 	}else if($.trim(picUrl)==""){
 		alert('医生照片不能为空！');
 		return false;
 	}
  });
  
</script>
<script type="text/javascript">
function expert_click(id,name,prof,depart,duty,picUrl){
	$("#id").val(id);
	$("#name").val(name);
	$("#prof").val(prof);
	$("#depart").val(depart);
	$("#duty").val(duty);
	$("#intro").val(document.getElementById("intro"+id+"").value);
	$("#picUrl").val(picUrl);
	$("#expertPic").attr("src",picUrl);
}
/* 删除专家信息 */
function expert_delete(id){
		if(window.confirm("确定要删除吗？")){
			location="deleteExpert?expertID="+id+"";
	}
	else{
		 return false;
	}
};
</script>
</html>
