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
<link href="${pageContext.request.contextPath}/static/bootstrap-4.0.0-dist/css/bootstrap.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/static/css/custom.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/static/css/common.css" rel="stylesheet"/>
<script src="${pageContext.request.contextPath}/static/bootstrap-4.0.0-dist/js/bootstrap.min.js"></script>
<style type="text/css">
	label {
	text-transform: uppercase;
}
</style>
</head>
<script type="text/javascript">
var id;   //全局变量，定时器

$(function(){
	var url="https://172.168.65.88"+'${run.url}';
	//getStatus(url);
	var status=$("#status").html();
	if("successful"!= status && "failed"!= status){
		var referId = window.setInterval(getStatus,3000,url);    //创建一个定时任务
		 id=referId;            //把定时任务赋值给全局变量，方便后面关闭定时任务，在关闭时不会报（referId未定义错误）
	}
	
});

function getStatus(apiurl){
	$.ajax({
		type:"get",
		//async:false, //同步请求
		url:"${pageContext.request.contextPath}/job/getStatus",
		data:{'url':apiurl},
		success:function(data){
			var jsonDate=JSON.parse(data); 
			var status=jsonDate.status;
			//alert(status);
			if("successful" == status || "failed"== status){
				$("#status").html(status);
				$("#result").val(jsonDate.result_stdout);
				window.clearInterval(id);   //关闭定时器
			}else{
				$("#status").html(status+",请等待.......");
				$("#result").val(jsonDate.result_stdout);
			}
		}
	});
}
</script>
<body>
<!-- 面包屑导航 -->
<div class="container-fluid content">
	<div class="row">
	  <div class="col-sm-6 ">
	  	<div class="BreadCrumb">
			<ol class="BreadCrumb BreadCrumb-list">
			  <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/home">Home</a></li>
			  <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/job/list">Jobs</a></li>
			  <li class="breadcrumb-item active">${run.name}</li>
			</ol>
		</div>
	 </div>
	</div>
	
	<div class="row">
	  <div class="col-sm-6 ">
		  	<div class="Panel">
		  		<div class="">
					<h4>详情</h4>
				</div>
				
				<c:if test="${run.type =='inventory_update'}">
					<div class="">
					<div class="form-group row">
				      <label for="name" class="col-sm-4 col-form-label">NAME</label>
				      <div class="col-sm-8">
				        <input type="text" disabled="disabled" class="form-control-plaintext" id="name" value="${run.name}">
				      </div>
				    </div>
				    <div class="form-group row">
				      <label for="status" class="col-sm-4 col-form-label">STATUS</label>
				      <div class="col-sm-8">
				        <input type="text" disabled="disabled" class="form-control-plaintext" id="status" value="${run.status}">
				      </div>
				    </div>
				    <div class="form-group row">
				      <label for="license_error" class="col-sm-4 col-form-label">license_error</label>
				      <div class="col-sm-8">
				        <input type="text" disabled="disabled" class="form-control-plaintext" id="license_error" value="${run.license_error}">
				      </div>
				    </div>
				    <div class="form-group row">
				      <label for="started" class="col-sm-4 col-form-label">STARTED</label>
				      <div class="col-sm-8">
				        <input type="text" disabled="disabled" class="form-control-plaintext" id="started" value="${run.started}">
				      </div>
				    </div>
				    <div class="form-group row">
				      <label for="finished" class="col-sm-4 col-form-label">FINISHED</label>
				      <div class="col-sm-8">
				        <input type="text" disabled="disabled" class="form-control-plaintext" id="finished" value="${run.finished}">
				      </div>
				    </div>
				    <div class="form-group row">
				      <label for="elapsed" class="col-sm-4 col-form-label">ELAPSED</label>
				      <div class="col-sm-8">
				        <input type="text" disabled="disabled" class="form-control-plaintext" id="elapsed" value="${run.elapsed}">
				      </div>
				    </div>
				    <div class="form-group row">
				      <label for="launch_type" class="col-sm-4 col-form-label">LAUNCH TYPE</label>
				      <div class="col-sm-8">
				        <input type="text" disabled="disabled" class="form-control-plaintext" id="launch_type" value="${run.launch_type}">
				      </div>
				    </div>
				    <div class="form-group row">
				      <label for="source" class="col-sm-4 col-form-label">source</label>
				      <div class="col-sm-8">
				        <input type="text" disabled="disabled" class="form-control-plaintext" id="source" value="${run.source}">
				      </div>
				    </div>
				    <div class="form-group row">
				      <label for="name" class="col-sm-4 col-form-label">group</label>
				      <div class="col-sm-8">
				        <input type="text" disabled="disabled" class="form-control-plaintext" id="name" value="${run.name}">
				      </div>
				    </div>
				    <div class="form-group row">
				      <label for="overwrite" class="col-sm-4 col-form-label">overwrite</label>
				      <div class="col-sm-8">
				        <input type="text" disabled="disabled" class="form-control-plaintext" id="overwrite" value="${run.overwrite}">
				      </div>
				    </div>
				    <div class="form-group row">
				      <label for="overwrite_vars" class="col-sm-4 col-form-label">overwrite_vars</label>
				      <div class="col-sm-8">
				        <input type="text" disabled="disabled" class="form-control-plaintext" id="overwrite_vars" value="${run.overwrite_vars}">
				      </div>
				    </div>
			    </div>
				</c:if>
	
				<c:if test="${run.type =='job'}">
					<div class="">
						<div class="form-group row">
					      <label for="name" class="col-sm-4 col-form-label">NAME</label>
					      <div class="col-sm-8">
					        <input type="text" disabled="disabled" class="form-control-plaintext" id="name" value="${run.name}">
					      </div>
					    </div>
					    <div class="form-group row">
					      <label for="status" class="col-sm-4 col-form-label">STATUS</label>
					      <div class="col-sm-8">
					        <input type="text" disabled="disabled" class="form-control-plaintext" id="status" value="${run.status}">
					      </div>
					    </div>
					    <div class="form-group row">
					      <label for="started" class="col-sm-4 col-form-label">STARTED</label>
					      <div class="col-sm-8">
					        <input type="text" disabled="disabled" class="form-control-plaintext" id="started" value="${run.started}">
					      </div>
					    </div>
					    <div class="form-group row">
					      <label for="finished" class="col-sm-4 col-form-label">FINISHED</label>
					      <div class="col-sm-8">
					        <input type="text" disabled="disabled" class="form-control-plaintext" id="finished" value="${run.finished}">
					      </div>
					    </div>
					    <div class="form-group row">
					      <label for="temp_name" class="col-sm-4 col-form-label">job_template</label>
					      <div class="col-sm-8">
					        <input type="text" disabled="disabled" class="form-control-plaintext" id="temp_name" value="${run.summary_fields.job_template.name}">
					      </div>
					    </div>
					    <div class="form-group row">
					      <label for="job_type" class="col-sm-4 col-form-label">job_type</label>
					      <div class="col-sm-8">
					        <input type="text" disabled="disabled" class="form-control-plaintext" id="job_type" value="${run.job_type}">
					      </div>
					    </div>
					    <div class="form-group row">
					      <label for="status" class="col-sm-4 col-form-label">status</label>
					      <div class="col-sm-8">
					        <input type="text" disabled="disabled" class="form-control-plaintext" id="status" value="${run.status}">
					      </div>
					    </div>
					    <div class="form-group row">
					      <label for="inventory" class="col-sm-4 col-form-label">inventory</label>
					      <div class="col-sm-8">
					        <input type="text" disabled="disabled" class="form-control-plaintext" id="inventory" value="${run.summary_fields.inventory.name}">
					      </div>
					    </div>
					    <div class="form-group row">
					      <label for="project" class="col-sm-4 col-form-label">project</label>
					      <div class="col-sm-8">
					        <input type="text" disabled="disabled" class="form-control-plaintext" id="project" value="${run.summary_fields.project.name}">
					      </div>
					    </div>
					    <div class="form-group row">
					      <label for="scm_revision" class="col-sm-4 col-form-label">scm_revision</label>
					      <div class="col-sm-8">
					        <input type="text" disabled="disabled" class="form-control-plaintext" id="scm_revision" value="${run.scm_revision}">
					      </div>
					    </div>
					    <div class="form-group row">
					      <label for="playbook" class="col-sm-4 col-form-label">playbook</label>
					      <div class="col-sm-8">
					        <input type="text" disabled="disabled" class="form-control-plaintext" id="playbook" value="${run.playbook}">
					      </div>
					    </div>
					    <div class="form-group row">
					      <label for="credential" class="col-sm-4 col-form-label">credential</label>
					      <div class="col-sm-8">
					        <input type="text" disabled="disabled" class="form-control-plaintext" id="credential" value="${run.credential}">
					      </div>
					    </div>
					    <div class="form-group row">
					      <label for="verbosity" class="col-sm-4 col-form-label">verbosity</label>
					      <div class="col-sm-8">
					        <input type="text" disabled="disabled" class="form-control-plaintext" id="scm_revision" value="${run.verbosity}">
					      </div>
					    </div>
				    </div>
					</c:if>
				<c:if test="${run.type =='project_update'}">
					<div class="">
						<div class="form-group row">
					      <label for="name" class="col-sm-4 col-form-label">NAME</label>
					      <div class="col-sm-8">
					        <input type="text" disabled="disabled" class="form-control-plaintext" id="name" value="${run.name}">
					      </div>
					    </div>
					    <div class="form-group row">
					      <label for="status" class="col-sm-4 col-form-label">STATUS</label>
					      <div class="col-sm-8">
					        <input type="text" disabled="disabled" class="form-control-plaintext" id="status" value="${run.status}">
					      </div>
					    </div>
					    <div class="form-group row">
					      <label for="started" class="col-sm-4 col-form-label">STARTED</label>
					      <div class="col-sm-8">
					        <input type="text" disabled="disabled" class="form-control-plaintext" id="started" value="${run.started}">
					      </div>
					    </div>
					    <div class="form-group row">
					      <label for="finished" class="col-sm-4 col-form-label">FINISHED</label>
					      <div class="col-sm-8">
					        <input type="text" disabled="disabled" class="form-control-plaintext" id="finished" value="${run.finished}">
					      </div>
					    </div>
					    <div class="form-group row">
					      <label for="elapsed" class="col-sm-4 col-form-label">ELAPSED</label>
					      <div class="col-sm-8">
					        <input type="text" disabled="disabled" class="form-control-plaintext" id="elapsed" value="${run.elapsed}">
					      </div>
					    </div>
					    <div class="form-group row">
					      <label for="launch_type" class="col-sm-4 col-form-label">LAUNCH TYPE</label>
					      <div class="col-sm-8">
					        <input type="text" disabled="disabled" class="form-control-plaintext" id="launch_type" value="${run.launch_type}">
					      </div>
					    </div>
					    <div class="form-group row">
					      <label for="name" class="col-sm-4 col-form-label">project</label>
					      <div class="col-sm-8">
					        <input type="text" disabled="disabled" class="form-control-plaintext" id="name" value="${run.name}">
					      </div>
					    </div>
				    </div>
				</c:if>
				<c:if test="${run.type =='system_job'}">
					<div class="">
						<div class="form-group row">
					      <label for="name" class="col-sm-4 col-form-label">NAME</label>
					      <div class="col-sm-8">
					        <input type="text" disabled="disabled" class="form-control-plaintext" id="name" value="${run.name}">
					      </div>
					    </div>
					    <div class="form-group row">
					      <label for="status" class="col-sm-4 col-form-label">STATUS</label>
					      <div class="col-sm-8">
					        <input type="text" disabled="disabled" class="form-control-plaintext" id="status" value="${run.status}">
					      </div>
					    </div>
					    <div class="form-group row">
					      <label for="started" class="col-sm-4 col-form-label">STARTED</label>
					      <div class="col-sm-8">
					        <input type="text" disabled="disabled" class="form-control-plaintext" id="started" value="${run.started}">
					      </div>
					    </div>
					    <div class="form-group row">
					      <label for="finished" class="col-sm-4 col-form-label">FINISHED</label>
					      <div class="col-sm-8">
					        <input type="text" disabled="disabled" class="form-control-plaintext" id="finished" value="${run.finished}">
					      </div>
					    </div>
					    <div class="form-group row">
					      <label for="elapsed" class="col-sm-4 col-form-label">ELAPSED</label>
					      <div class="col-sm-8">
					        <input type="text" disabled="disabled" class="form-control-plaintext" id="elapsed" value="${run.elapsed}">
					      </div>
					    </div>
					    <div class="form-group row">
					      <label for="launch_type" class="col-sm-4 col-form-label">LAUNCH TYPE</label>
					      <div class="col-sm-8">
					        <input type="text" disabled="disabled" class="form-control-plaintext" id="launch_type" value="${run.launch_type}">
					      </div>
					    </div>
					    <div class="form-group">
					        <label for="extra_vars" class="col-form-label">EXTRA VARIABLES</label>
							<textarea disabled="disabled" class="form-control"  rows="5" id="extra_vars">${run.extra_vars}</textarea>
					    </div>
				    </div>
				</c:if>
			</div>
			
		</div>
		<div class="col-sm-6 col-md-6">
		  	<div class="Panel">
				<h4>结果</h4>
				<div class="form-group">
					<textarea disabled="disabled" class="form-control" rows="18"  id="result">${run.result_stdout}</textarea>
				</div>
			</div>
		</div>
	</div>
</div>

</body>
</html>