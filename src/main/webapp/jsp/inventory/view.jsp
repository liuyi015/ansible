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
<link href="${pageContext.request.contextPath}/static/css/font-awesome.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/static/bootstrap-4.0.0-dist/js/bootstrap.min.js"></script>
</head>
<script type="text/javascript">
$(function(){
	var rs="<%=session.getAttribute("msg")%>";
	if(rs!="null"){
		//删除信息，防止刷新页面的时候出现
		<%session.removeAttribute("msg");%>
		alert(rs);
	}
});

function addGroup(inventory){
	var url="${pageContext.request.contextPath}/group/toAdd?inventory="+inventory;
	location.href=url;
};

function addHost(inventory){
	var url="${pageContext.request.contextPath}/host/toAdd?inventory="+inventory;
	location.href=url;
};

function editGroup(id){
	var url="${pageContext.request.contextPath}/group/toEdit?id="+id;
	location.href=url;
}
function editHost(id){
	var url="${pageContext.request.contextPath}/host/toEdit?id="+id;
	location.href=url;
}

function delGroup(id,inventory){
	if(!confirm("确认删除?")){
		return;
	}
	var url="${pageContext.request.contextPath}/group/toDelete?id="+id+"&inventory="+inventory;
	location.href=url;
}
function delHost(id,inventory){
	if(!confirm("确认删除?")){
		return;
	}
	var url="${pageContext.request.contextPath}/host/toDelete?id="+id+"&inventory="+inventory;
	location.href=url;
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
			  <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/inventory/list">Inventory</a></li>
			  <li class="breadcrumb-item active">${inventory}</li>
			</ol>
		</div>
	 </div>
	</div>
	
	<div class="row">
	  <div class="col-sm-6 ">
	  	<div class="Panel container">
		  <div class="row">
			  <div class="col-sm-6">
				<form class="form-inline my-2 my-lg-0" role="form" action="${pageContext.request.contextPath}/group/search" method="post">
				 <div class="form-group">
			    	<input type="text" class="form-control" id="name" value="${group.name}" name="name" placeholder="请输入group名字">
			    	<span class="input-group-btn">
			    		<button class="btn btn-navy"><i class="fa fa-search"></i></button>
			    	</span>
				  </div>
				</form>
			 </div>
			 <div class="col-sm-6" style="text-align: right; float: right;"><input type="button" class="btn btn-success" onclick="addGroup(${inventory})" value="新增组"/></div>
			</div>
			<div class="container-fluid" style="margin-top: 20px">
				<div class="row">
					<table class="table table-striped table-hover col-sm-12">
						 <thead>
							 <tr class="old">
								<th scope="col">name</th>
								<th scope="col">操作</th>
							</tr>
						 </thead>
						 <tbody>
						 	<c:if test="${requestScope.rootGrops !=null}">
								<c:forEach var="group" items="${requestScope.rootGrops}">
									<tr>
										<td>${group.name}</td>
										<td>
										<a style="margin-right: 15px;" onclick="editGroup(${group.id})"><i class="fa fa-pencil"></i></a>
										<a onclick="delGroup(${group.id},${inventory})"><i class="fa fa-trash-o"></i></a>
										</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
		  </div>
		</div>
		<div class="col-sm-6">
			<div class="Panel container">
			  <div class="row">
				  <div class="col-sm-6">
					<form class="form-inline my-2 my-lg-0" role="form" action="${pageContext.request.contextPath}/host/search" method="post">
					 <div class="form-group">
				    	<input type="text" class="form-control" id="name" value="${host.name}" name="name" placeholder="请输入主机名字">
				    	<span class="input-group-btn">
				    		<button class="btn btn-navy"><i class="fa fa-search"></i></button>
				    	</span>
					  </div>
					</form>
				 </div>
				 <div class="col-sm-6" style="text-align: right; float: right;"><input type="button" class="btn btn-success" onclick="addHost(${inventory})" value="新增主机"/></div>
				</div>
				<div class="container-fluid" style="margin-top: 20px">
					<div class="row">
						<table class="table table-striped table-hover col-sm-12">
							 <thead>
								 <tr class="old">
									<th scope="col">name</th>
									<th scope="col">enabled</th>
									<th scope="col">操作</th>
								</tr>
							 </thead>
							 <tbody>
							 	<c:if test="${requestScope.hosts !=null}">
									<c:forEach var="host" items="${requestScope.hosts}">
										<tr>
											<td>${host.name}</td>
											<td>${host.enabled}</td>
											<td>
												<a style="margin-right: 15px;" onclick="editHost(${host.id})"><i class="fa fa-pencil"></i></a>
												<a onclick="delHost(${host.id},${inventory})"><i class="fa fa-trash-o"></i></a>
											</td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
				
			</div>
		</div>
		
	</div>
</div>
</body>
</html>