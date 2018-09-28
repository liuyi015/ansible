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
	var url="${pageContext.request.contextPath}/project/toAdd";
	location.href=url;
};

function edit(id){
	var url="${pageContext.request.contextPath}/project/toEdit?id="+id;
	location.href=url;
}

function del(id){
	if(!confirm("确认删除"+id+"项目")){
		return;
	}
	var url="${pageContext.request.contextPath}/project/toDelete?id="+id;
	location.href=url;
}

/*翻页、查询  */
function toPage(page){
	var search=$("#search").val();
	var url="${pageContext.request.contextPath}/project/list?page="+page+"&search="+search;
	location.href=url;
}

</script>
<body>
<!-- 面包屑导航 -->
<div class="container-fluid content">
	<div class="row">
	  <div class="col-sm-6">
	  	<div class="BreadCrumb">
			<ol class="BreadCrumb BreadCrumb-list">
			  <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/home">Home</a></li>
			  <li class="breadcrumb-item active">Project</li>
			  <li style="text-align: right; width: 85%"><a href="${pageContext.request.contextPath}/project/list"><i class="fa fa-refresh"></i></a></li>
			</ol>
		</div>
	 </div>
	</div>
	
 	<div class="Panel">
		<div class="row">
		  <div class="col-sm-6">
			<form class="form-inline my-2 my-lg-0" role="form">
			 <div class="input-group">
			   <!-- <label for="name">name:</label> -->
			    	<input type="text" class="form-control" id="search" value="${search}" name="search" placeholder="请输入项目名字">
			    	<span class="input-group-btn">
			    		<button class="btn btn-primary" onclick="toPage(1)"><i class="fa fa-search"></i></button>
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
				     <th scope="col">type</th>
				     <th scope="col">revision</th>
				     <th scope="col">last_updated</th>
				     <th scope="col">操作</th>
				   </tr>
				 </thead>
				 <tbody>
				 	<c:if test="${requestScope.reInfo.list !=null}">
						<c:forEach var="project" items="${requestScope.reInfo.list}">
							<tr>
								<td>${project.name}</td> 
								<td>${project.scm_type=='' ? 'Manual' : 'Git'}</td>
								<td>${project.scm_revision}</td>
								<td>${project.last_updated}</td>
								<td>
									<a style="margin-right: 20px;" onclick="edit(${project.id})"><i class="fa fa-pencil"></i></a>
									<a onclick="del(${project.id})"><i class="fa fa-trash-o"></i></a>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				   </tbody>
		 	 </table>
		  </div>
		</div>
		<!--分页  -->
		<div class="row" style="text-align: right;float: right; margin-top: 20px;">
			<c:if test="${reInfo.totalPage !=0}">
				<span style="margin-top: 10px; margin-right: 10px">共&nbsp;${reInfo.count}&nbsp;行</span>
				<ul class="pagination">
				  	<c:choose>
						<c:when test="${reInfo.currentPage == 1}">
				   			 <li class="page-item disabled">
					    </c:when>
					    <c:otherwise>
					    	<li class="page-item">
					    </c:otherwise>
					 </c:choose>
				      <%-- <a class="page-link" href="${pageContext.request.contextPath}/job/list?page=${reInfo.currentPage-1}>&laquo;</a> --%>
				      <a class="page-link" onclick="toPage(${reInfo.currentPage-1})"> &laquo;</a>
				    </li>
				   
					<c:forEach var="s" begin="1" end="${reInfo.totalPage }">
						<c:choose>
							<c:when test="${reInfo.currentPage == s}">
							 	<li class="page-item active">
							 </c:when>
							 <c:otherwise>
							  	<li class="page-item">
							 </c:otherwise>
						 </c:choose>
					      <%-- <a class="page-link" href="${pageContext.request.contextPath}/job/list?page=${s}">${s}</a> --%>
					      <a class="page-link" onclick="toPage(${s})">${s}</a>
					    </li>
					</c:forEach>
					
				    <c:choose>
						<c:when test="${reInfo.currentPage == reInfo.totalPage}">
				   			 <li class="page-item disabled">
					    </c:when>
					    <c:otherwise>
					    	<li class="page-item">
					    </c:otherwise>
					 </c:choose>
				     <%--  <a class="page-link" href="${pageContext.request.contextPath}/job/list?page=${reInfo.currentPage+1}">&raquo;</a> --%>
				      <a class="page-link" onclick="toPage(${reInfo.currentPage+1})">&raquo;</a>
				    </li>
				</ul>
			</c:if>
		</div>
	</div>
</div>

</body>
</html>