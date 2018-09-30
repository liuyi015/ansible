<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java"%>
<!-- 引入springmvc标签库，才能解析 ${pageContext.request.contextPath}-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ansibleTest</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.3.1.js"></script>
<link href="${pageContext.request.contextPath}/static/bootstrap-4.0.0-dist/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/custom.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/common.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/static/bootstrap-4.0.0-dist/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(function(){
	var rs="<%=session.getAttribute("msg")%>";
	if(rs!="null"){
		<%session.removeAttribute("msg");%>
		alert(rs);
	}
	//获取config信息
	getConfig();
	getOrganization();
});

/* 
获取config信息
*/
function getConfig(){
	$.ajax({
		type:"GET",
		async:false, //设为同步请求（异步加载的话后面的遍历方法获取不到option）
		url:"${pageContext.request.contextPath}/config/getConfig",
		success:function(data){
			var jsonDate=JSON.parse(data);
			var base_dir=jsonDate.project_base_dir;
			$("#base_dir").val(base_dir);
			var local_path=jsonDate.project_local_paths;
			//动态添加select的option
			for(var i in local_path){
				$("#playbook").append("<option value='"+local_path[i]+"'>"+local_path[i]+"</option>");
			}
		}
	});
}
/* 
获取organization信息
*/
function getOrganization(){
	$.ajax({
		type:"GET",
		async:false, //设为同步请求（异步加载的话后面的遍历方法获取不到option）
		url:"${pageContext.request.contextPath}/organization/getOrganization",
		success:function(data){
			var json=JSON.parse(data);
			//动态添加select的option
			$.each(json,function(i,item){
				$("#organization").append("<option value='"+item.id+"'>"+item.name+"</option>");
			})
		}
	});
}

/*
 * 控制div的显示与隐藏
 */
function select(){
	var scm_type=$("#scm_type").val();
	if(scm_type==''){
		$("#path").show();
		$("#url").hide();
	}else if(scm_type=='git'){
		$("#path").hide();
		$("#url").show();
	}else{
		$("#path").hide();
		$("#url").hide();
	}
}

function doAdd(){
	$("#addform").submit();
};
</script>
</head>
<body>
<!-- 面包屑导航 -->
<div class="BreadCrumb">
	<ol class="BreadCrumb BreadCrumb-list">
	  <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/home">Home</a></li>
	  <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/project/list?page=1" >Project</a></li>
	  <li class="breadcrumb-item active">新增</li>
	</ol>
</div>
<div class="container" style="margin-top:100px"> 
	<div class="row">
     	<div class="col-sm-12">
     		<div class="Panel">
				<form role="form" id="addForm" name="add" action="${pageContext.request.contextPath}/project/doAdd" method="post">
					<div class="form-group">
				      <label for="name"><span class="xingSpan">*</span>name:</label>
				      <input type="text" class="form-control" id="name" name="name" checked="checked" required />
				    </div>
				    <div class="form-group">
					  <label class="form-control-label" for="description">description</label>
					  <input type="text" class="form-control" id="description" name="description">
					</div>
					<div class="form-group">
					  <label class="form-control-label" for="organization"><span class="xingSpan">*</span>organization</label>
					   <select id="organization" name="organization" class="form-control" required><option value="">请选择</option></select>
					</div>
					<!-- <div class="form-group">
				      <label for="scm_type"><span class="xingSpan">*</span>scm_type:</label>
				      <select class="custom-select" name="scm_type" id="scm_type" onchange="select()"> 
					      <option value="请选择上传方式">请选择上传方式</option>
					      <option value="">Manual</option>
					      <option value="git">Git</option>
					  </select>
				    </div> -->
				    <input type="hidden" class="form-control" id="scm_type" name="scm_type" value="">
				    <div class="form-group" id="url" hidden="">
				      <label for="scm_url"><span class="xingSpan">*</span>scm_url:</label>
				      <input type="text" class="form-control" id="scm_url" name="scm_url">
				    </div>
					<div class="form-group" id="path">
				      <label for="base_dir">PROJECT BASE PATH:</label>
				      <input type="text" class="form-control" id="base_dir" disabled="disabled">
				    </div>
				    <div class="form-group">
				      <label for="scm_url"><span class="xingSpan">*</span>PLAYBOOK DIRECTORY:</label>
				       <select id="playbook" name="local_path" class="form-control" required><option value="">请选择一个项目</option></select>
				    </div>
					<button id="addBton" type="submit" class="btn btn-navy">提交</button>
				</form>
			</div>
		</div>
	</div>
</div>

</body>
</html>