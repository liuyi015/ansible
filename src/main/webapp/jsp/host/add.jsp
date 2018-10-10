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
<script type="text/javascript">
$(function(){
	var rs="<%=session.getAttribute("msg")%>";
	if(rs!="null"){
		<%session.removeAttribute("msg");%>
		alert(rs);
	}
});

function doAdd(){
	$("#addform").submit();
};
</script>
</head>
<body>
<!-- 面包屑导航 -->
<div class="container content">
	<div class="row">
	  <div class="col-sm-6 ">
	  	<div class="BreadCrumb">
			<ol class="BreadCrumb BreadCrumb-list">
			  <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/home">首页</a></li>
			  <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/inventory/list">资产清单</a></li>
			  <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/inventory/view?id=${host.inventory }">资产清单管理</a></li>
			  <li class="breadcrumb-item active">增加主机</li>
			</ol>
		</div>
	 </div>
	</div>
	<div class="row">
     	<div class="col-sm-12">
     		<div class="Panel">
				<form role="form" id="addForm" name="add" action="${pageContext.request.contextPath}/host/doAdd" method="post">
					<input type="hidden" name="inventory" value="${host.inventory }"/>
					<div class="form-group">
				      <label for="name"><span class="xingSpan">*</span>名称:</label>
				      <input type="text" class="form-control" id="name" name="name" checked="checked" required />
				    </div>
				    <div class="form-group">
					  <label class="form-control-label" for="description">描述</label>
					  <input type="text" class="form-control" id="description" name="description">
					</div>
					<!-- <div class="form-group">
					  <label class="form-control-label" for="enabled"><span class="xingSpan">*</span>enabled</label>
					</div> -->
					<div class="form-group row"> 
					   <label class="radio-inline col-sm-2"><input type="radio" name="enabled" value="true">可用</label>
					   <label class="radio-inline col-sm-2"><input type="radio" name="enabled" value="false">不可用</label>
					</div>
					<button id="addBton" type="submit" class="btn btn-navy">提交</button>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>