<%@page import="cn.zjzt.entity.system.Template"%>
<%@page import="java.util.List"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>套餐管理</title>
<!-- 解决IE11不兼容的问题 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<link rel="stylesheet" type="text/css" href="../bootstrap/css/docs.css">
<link rel="stylesheet" type="text/css" href="../bootstrap/css/panel.css" />
<link href="../bootstrap/css/bootstrap-combined.no-icons.min.css"
	rel="stylesheet">
<link href="../bootstrap/css/bootstrap-responsive.min.css"
	rel="stylesheet">
<script src="../js/jquery-1.11.1.min.js"></script>
<script src="../plugins/ajaxfileupload.js"></script>
<style>
body {
	font-family: "ff-tisa-web-pro-1", "ff-tisa-web-pro-2", "Lucida Grande",
		"Helvetica Neue", Helvetica, Arial, "Hiragino Sans GB",
		"Hiragino Sans GB W3", "Microsoft YaHei UI", "Microsoft YaHei",
		"WenQuanYi Micro Hei", sans-serif;
}
</style>
</head>
<body style="padding: 40px">
	<div class="container-fluid">
		<div class="row">
			<div class="navbar navbar-fixed-top">
				<div class="navbar-inner">
					<ul class="nav">
						<li><a href="getAllNews">新闻管理</a></li>
						<li><a href="getAllExpert">专家管理</a></li>
						<li><a href="getAllEquipment">设备管理</a></li>
						<li><a href="getAllDepart">科室管理</a></li>
						<li class="active"><a href="getAllTemplate">套餐管理</a></li>
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
				<div class="panel-heading">套餐列表</div>
				<div class="panel-body">
					<%
						ActionContext context=ActionContext.getContext();
						List<Template> tempList=(List<Template>)context.get("templateList");
					 %>	
						<table class="table">
						<thead>
							<tr>
								<th>套餐名</th>
								<th>编辑</th>
								<th>删除</th>
							</tr>
						</thead>
						<tbody>
							<%
								if(tempList!=null){
									if(tempList.size()>0){
										for(int i=0;i<tempList.size();i++){
							%>
							<tr>
								<td><%=i+1+"."+tempList.get(i).getName()%></td>
								<td>
									<button class="btn btn-mini btn-info"
										onclick="template_click(<%=tempList.get(i).getId()%>,
												'<%=tempList.get(i).getName()%>','<%=tempList.get(i).getApply_sex()%>',
												'<%=tempList.get(i).getIntro()%>','<%=tempList.get(i).getPrice()%>',
												'<%=tempList.get(i).getPic_url()%>')">
										编辑</button>
								</td>
								<td><button class="btn btn-mini btn-danger" onclick="dalete_template(<%=tempList.get(i).getId()%>)">
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
				<div class="panel-heading">添加套餐</div>
				<div class="panel-body">
					<form action="addOrUpdateTemplate" method="post">
						<table class="table">
							<tr>
								<td>id：
								<td><textarea rows="1" style="display: none" id="id"
										name="template.id"></textarea>
								</td>
							</tr>
							<tr>
								<td>套餐图片：</td>
								<td>
								<input style="display: none;" type="text" id="pic_url" 
										name="template.pic_url"
								<%
									if(context.get("picUrl")!=null){
									%>
									 value="<%=context.get("picUrl")%>"
									<%
									}
								 %>
								 >
								 <input type="file" id="file" name="file" />
        						<input type="button" value="上传" onclick="ajaxFileUpload();"><hr>
								<img id="templatePic" width="150px" alt="" src="<%=context.get("picUrl")%>">
								<!-- <a class="btn btn-info" href="uploadTemplatePic.jsp">添加图片</a> -->
								</td>
							</tr>
							<tr>
								<td>套餐名称：</td>
								<td><textarea rows="1" id="name" name="template.name"></textarea>
								</td>
							</tr>
							
							<tr>
								<td>适用人群：</td>
								<td><textarea rows="1" id="apply_sex" name="template.apply_sex"></textarea>
								</td>
							</tr>
							<tr>
								<td>套餐介绍：</td>
								<td><textarea rows="2" id="intro" name="template.intro"></textarea>
								</td>
							</tr>
							<tr>
								<td>套餐价格：</td>
								<td><textarea rows="1" id="price" name="template.price"></textarea></td>
							</tr>
							<tr>
								<td colspan="2">
									<button id="btnCommit" class="btn btn-info" type="submit">提交</button>
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
  function ajaxFileUpload(){
        $.ajaxFileUpload({
           	url:'uploadTemplatePic',//用于文件上传的服务器端请求地址
            secureuri:false,//一般设置为false
         	fileElementId:'file',//文件上传空间的id属性  <input type="file" id="file" name="file" />
            dataType: 'json',//返回值类型 一般设置为json
            success: function (data, status)  //服务器成功响应处理函数
            {
             	//alert(data.message);
             	alert("图片保存成功！");
             	$("#templatePic").attr("src",data.message);
             	$("#pic_url").val(data.message);
            },
            error: function (data, status, e){
                //服务器响应失败处理函数
                alert(e);
            }
        });
}
</script> 
<script type="text/javascript">

$("#btnCommit").click(function(){
	var name=$("#name").val();
	var apply_sex=$("#apply_sex").val();
	var intro =$("#intro").val();
	var price=$("#price").val();
	var pic_url=$("#pic_url").val();
	if($.trim(name)==""){
 		alert('套餐姓名不能为空！');
 		return false;
 	}else if($.trim(price)==""){
 		alert('套餐价格不能为空！');
 		return false;
 	}else if($.trim(pic_url)==""){
 		alert('套餐图片不能为空！');
 		return false;
 	}
});

	/* 查看套餐信息 */
	function template_click(id,name,apply_sex,intro,price,pic_url){
		$("#id").val(id);
		$("#pic_url").val(pic_url);
		$("#name").val(name);
		$("#apply_sex").val(apply_sex);
		$("#intro").val(intro);
		$("#price").val(price);
		$("#templatePic").attr("src",pic_url);
	}
	/* 删除套餐 */
	function dalete_template(id){
		if(window.confirm("确定要删除该套餐吗？")){
			location="deleteTemplate?templateID="+id+"";
		}
		else{
			 return false;
		}
	}
</script>
</html>
