<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java"%>
<!-- 引入springmvc标签库，才能解析 ${pageContext.request.contextPath}-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>inventory</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.3.1.js"></script>
<link href="${pageContext.request.contextPath}/static/bootstrap-4.0.0-dist/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/custom.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/common.css" rel="stylesheet">
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

function add(){
	var url="${pageContext.request.contextPath}/inventory/toAdd";
	location.href=url;
};

function view(id){
	var url="${pageContext.request.contextPath}/inventory/view?id="+id;
	location.href=url;
};

function edit(id){
	var url="${pageContext.request.contextPath}/inventory/toEdit?id="+id;
	location.href=url;
}

function del(id){
	if(!confirm("确认删除"+id+"项目")){
		return;
	}
	var url="${pageContext.request.contextPath}/inventory/toDelete?id="+id;
	location.href=url;
}


</script>
<body>
<div class="container-fluid content body">
	<!-- 面包屑导航 -->
	<div class="row">
	  	<div class="BreadCrumb">
			<ol class="BreadCrumb BreadCrumb-list">
			  <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/home">Home</a></li>
			  <li class="breadcrumb-item active">Inventory</li>
			  <li style="text-align: right; width: 85%"><a  href="${pageContext.request.contextPath}/inventory/list"><i class="fa fa-refresh"></i></a></li>
			</ol>
		</div>
	</div>
	<div class="Panel">
		<div class="row">
		  <div class="col-sm-6">
			<form class="form-inline my-2 my-lg-0" role="form" action="${pageContext.request.contextPath}/inventory/search" method="post">
			 <div class="form-group">
		    	<input type="text" class="form-control" id="name" value="${inventory.name}" name="name" placeholder="请输入模板名字">
		    	<span class="input-group-btn">
		    		<button class="btn btn-primary"><i class="fa fa-search"></i></button>
		    	</span>
			  </div>
			</form>
		 </div>
		 <div class="col-sm-6" style="text-align: right; float: right;"><input type="button" class="btn btn-success" onclick="add()" value="新增"/></div>
		</div>
		<div class="container-fluid" style="margin-top: 20px">
			<div class="row">
				<table class="table table-striped table-hover col-sm-12">
					 <thead>
						 <tr class="old">
							<th scope="col">name</th>
							<th scope="col">organization</th>
							<th scope="col">操作</th>
						</tr>
					 </thead>
					 <tbody>
						<c:if test="${reInfo.list !=null}">
							<c:forEach var="inventory" items="${requestScope.reInfo.list}">
								<tr>
									<td>${inventory.name}</td>
									<td>${inventory.summary_fields.organization.name}</td>
									<td>
									<a style="margin-right: 15px;" onclick="view(${inventory.id})"><i class="fa fa-search-plus"></i></a>
									<a style="margin-right: 15px;" onclick="edit(${inventory.id})"><i class="fa fa-pencil"></i></a>
									<a onclick="del(${inventory.id})"><i class="fa fa-trash-o"></i></a>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
		<!--分页  -->
		<div class="row" style="text-align: right;float: right; margin-top: 20px;">
			<ul class="pagination">
			  	<c:choose>
					<c:when test="${reInfo.currentPage == 1}">
			   			 <li class="page-item disabled">
				    </c:when>
				    <c:otherwise>
				    	<li class="page-item">
				    </c:otherwise>
				 </c:choose>
			      <a class="page-link" href="${pageContext.request.contextPath}/templates/list?page=${reInfo.currentPage-1}">&laquo;</a>
			    </li>
			    <c:if test="${reInfo.totalPage !=0}">
					<c:forEach var="s" begin="1" end="${reInfo.totalPage }">
						<c:choose>
							<c:when test="${reInfo.currentPage == s}">
							 	<li class="page-item active">
							 </c:when>
							 <c:otherwise>
							  	<li class="page-item">
							 </c:otherwise>
						 </c:choose>
					      <a class="page-link" href="${pageContext.request.contextPath}/templates/list?page=${s}">${s}</a>
					    </li>
					</c:forEach>
				</c:if>
			    <c:choose>
					<c:when test="${reInfo.currentPage == reInfo.totalPage}">
			   			 <li class="page-item disabled">
				    </c:when>
				    <c:otherwise>
				    	<li class="page-item">
				    </c:otherwise>
				 </c:choose>
			      <a class="page-link" href="${pageContext.request.contextPath}/templates/list?page=${reInfo.currentPage+1}">&raquo;</a>
			    </li>
			</ul>
		</div>
	</div>
</div>
</body>
</html>