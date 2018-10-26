<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java"%>
<!-- 引入springmvc标签库，才能解析 ${pageContext.request.contextPath}-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addTemplate</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.3.1.js"></script>
<link href="${pageContext.request.contextPath}/static/bootstrap-4.0.0-dist/css/bootstrap.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/static/css/custom.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/static/css/common.css" rel="stylesheet"/>
<script src="${pageContext.request.contextPath}/static/bootstrap-4.0.0-dist/js/bootstrap.min.js"></script>
<script type="text/javascript">
function allProject(){
	$.ajax({
		type:"GET",
		async:false, //设为同步请求（异步加载的话后面的遍历方法获取不到option）
		url:"${pageContext.request.contextPath}/project/allProject",
		dataType:"json",
		success:function(data){
			//var jsonDate=JSON.parse(data);
			//动态添加select的option
			$.each(data,function(i,item){
				if(item!=null){
					$("#project").append("<option value='"+item.id+"'>"+item.name+"</option>");
				}
				
			});
		}
	});
};
function allCredential(){
	$.ajax({
		type:"GET",
		async:false, //设为同步请求（异步加载的话后面的遍历方法获取不到option）
		url:"${pageContext.request.contextPath}/credential/getCredentialByKind",
		data:{kind:"ssh"},
		dataType:"json",
		success:function(data){
			//var jsonDate=JSON.parse(data);
			//动态添加select的option
			$.each(data,function(i,item){
				if(item!=null){
					$("#credential").append("<option value='"+item.id+"'>"+item.name+"</option>");
				}
				
			});
		}
	});
}

function allInventory(){
	$.ajax({
		type:"GET",
		async:false, //设为同步请求（异步加载的话后面的遍历方法获取不到option）
		url:"${pageContext.request.contextPath}/inventory/allInventory",
		dataType:"json",
		success:function(data){
			//动态添加select的option
			$.each(data,function(i,item){
				if(item!=null){
					$("#inventory").append("<option value='"+item.id+"'>"+item.name+"</option>");
				}
				
			});
		}
	});
}


$(function(){
	var rs="<%=session.getAttribute("msg")%>";
	if(rs!="null"){
		//删除信息，防止刷新页面的时候出现
		<%session.removeAttribute("msg");%>
		alert(rs);
	}
	
	//获取资源
	allProject();
	allCredential();
	allInventory();
	
});

function selProject(){
	var project =$("#project").val();
	if(project!=''){
		$.ajax({
			type:"GET",
			async:false, //设为同步请求（异步加载的话后面的遍历方法获取不到option）
			url:"${pageContext.request.contextPath}/project/getPlaybook",
			data:{id:project},
			dataType:"json",
			success:function(data){
				if(data!=null){
					$("#playbook").val(data);
					$("#playbook1").val(data);
				}
			}
		});
	}
	return;
};

function doAdd(){
	$("#addform").submit();
};
</script>
</head>
<body>
<!-- 面包屑导航 -->
<div class="row">
  <div class="col-sm-6">
  	<div class="BreadCrumb">
		<ol class="BreadCrumb BreadCrumb-list">
		  <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/home">首页</a></li>
		  <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/templates/list">模板</a></li>
		  <li class="breadcrumb-item active">新增</li>
		</ol>
	</div>
 </div>
</div>
<div class="container" style="margin-top:100px"> 
	<div class="row">
     	<div class="col-sm-12">
     		<div class="Panel">
				<form role="form" id="addForm" name="add" action="${pageContext.request.contextPath}/templates/doAddJobTemplate" method="post">
					<div class="form-group">
				      <label for="name"><span class="xingSpan">*</span>名称:</label>
				      <input type="text" class="form-control" id="name" name="name" checked="checked" required />
				    </div>
				    <div class="form-group">
					  <label class="form-control-label" for="description">描述:</label>
					  <input type="text" class="form-control" id="description" name="description">
					</div>
					<div class="form-group">
				      <label for="job_type"><span class="xingSpan">*</span>任务类型:</label>
				      <select class="custom-select" name="job_type" id="job_type"> 
					      	<option value="run">Run</option>
							<option value="check">Check</option>
							<option value="scan">Scan</option>
					  </select>
				    </div>
				    <div class="form-group">
				      	<label for="inventory"><span class="xingSpan">*</span>资产清单:</label>
				        <select id="inventory" name="inventory" class="form-control" required><option value="">请选择</option></select>
				    </div>
				    <div class="form-group">
				      <label for="project"><span class="xingSpan">*</span>项目:</label>
				       <select id="project" name="project" class="form-control" onchange="selProject()" required><option value="">请选择一个项目</option></select>
				    </div>
				    <div class="form-group">
				      <label for="playbook">playbook:</label>
				      <input type="text" class="form-control" id="playbook" name="playbook" readonly="readonly">
				    </div>
				    <div class="form-group">
				      	<label for="credential"><span class="xingSpan">*</span>机器凭证:</label>
				        <select id="credential" name="credential" class="form-control" required><option value="">请选择</option></select>
				    </div>
				    <div class="form-group">
				      <label for="verbosity"><span class="xingSpan">*</span>verbosity:</label>
				      <select class="custom-select" name="verbosity" id="verbosity"> 
					  		<option value="0">"0 (Normal)"</option>
							<option value="1">"1 (Verbose)"</option>
							<option value="2">"2 (More Verbose)"</option>
							<option value="3">"3 (Debug)"</option>
							<option value="4">"4 (Connection Debug)"</option>
							<option value="5">"5 (WinRM Debug)"</option>
					  </select>
				    </div>
					<button id="addBton" type="submit" class="btn btn-navy">提交</button>
				</form>
			</div>
		</div>
	</div>
</div>

</body>
</html>