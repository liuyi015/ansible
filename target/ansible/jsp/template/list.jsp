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
<link href="${pageContext.request.contextPath}/static/css/font-awesome.min.css" rel="stylesheet"/>
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
	var url="${pageContext.request.contextPath}/templates/toAddJobTemp";
	location.href=url;
};

function edit(id){
	var url="${pageContext.request.contextPath}/templates/toEdit?id="+id;
	location.href=url;
}

function del(id){
	if(!confirm("确认删除"+id+"项目")){
		return;
	}
	$.ajax({
		url:"${pageContext.request.contextPath}/templates/toDelete",
		type:"post",
		data:{"id":id},
		dataType:"text",
		success:function(data){
			var currentPage=$("#currentPage").val();
			var count=$("#count").val();
			var pageSize=$("#pageSize").val();
			//计算删除一条后的总页码数
			var totalPage=(Number(count) + Number(pageSize)-2)/pageSize;
			if(currentPage>totalPage){
				toPage(currentPage-1);
			}else{
				toPage(currentPage);
			}
			
		}
	}); 
}

function run(id){
	var url="${pageContext.request.contextPath}/templates/run?id="+id;
	location.href=url;
}

/*翻页、查询  */
function toPage(page){
	var search=$("#search").val();
	var url="${pageContext.request.contextPath}/templates/list?page="+page+"&search="+search;
	location.href=url;
}
</script>
<body>
<!-- 面包屑导航 -->
<div class="container-fluid content">
	<div class="row">
	  	<div class="BreadCrumb">
			<ol class="BreadCrumb BreadCrumb-list">
			  <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/home">首页</a></li>
			  <li class="breadcrumb-item active">模板</li>
			  <li style="text-align: right; width: 85%"><a href="${pageContext.request.contextPath}/templates/list"><i class="fa fa-refresh"></i></a></li>
			</ol>
		</div>
	</div>
	<div class="Panel">
		<div class="row">
		  <div class="col-sm-6">
			<form class="form-inline my-2 my-lg-0" role="form">
			 <div class="form-group">
			   <!-- <label for="name">name:</label> -->
			    	<input type="text" class="form-control" id="search" value="${search}" name="search" placeholder="请输入模板名字">
			    	<span class="input-group-btn">
			    		<button class="btn btn-navy"><i class="fa fa-search"></i></button>
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
							<th scope="col">名称</th>
							<th scope="col">类型</th>
							<th scope="col">描述</th>
							<th scope="col">修改时间</th>
							<th scope="col">操作</th>
						</tr>
					 </thead>
					 <tbody>
						<c:if test="${reInfo.list !=null}">
							<c:forEach var="template" items="${requestScope.reInfo.list}">
								<tr>
									<td>${template.name}</td>
									<td>${template.type}</td>
									<td>${template.description}</td>
									<td>${template.modified}</td>
									<td>
									<a style="margin-right: 15px;" onclick="run(${template.id})"><i class="fa fa-rocket"></i></a>
									<a style="margin-right: 15px;" onclick="edit(${template.id})"><i class="fa fa-pencil"></i></a>
									<a onclick="del(${template.id})"><i class="fa fa-trash-o"></i></a>
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
				<input id="currentPage" type="hidden" value="${reInfo.currentPage}">
			 	<input id="count" type="hidden" value="${reInfo.count}">
				<input id="pageSize" type="hidden" value="${reInfo.pageSize}">
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