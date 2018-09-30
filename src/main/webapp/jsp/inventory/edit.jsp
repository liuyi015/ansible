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
</head>
<script type="text/javascript">
/* 
获取organization信息
*/
function getOrganization(){
	var organ=${inventory.organization }
	$.ajax({
		type:"GET",
		async:false, //设为同步请求（异步加载的话后面的遍历方法获取不到option）
		url:"${pageContext.request.contextPath}/organization/getOrganization",
		success:function(data){
			var json=JSON.parse(data);
			//动态添加select的option
			$.each(json,function(i,item){
				if(item.id==organ){
					$("#organization").append("<option value='"+item.id+"' selected='selected'>"+item.name+"</option>");
				}else{
					$("#organization").append("<option value='"+item.id+"'>"+item.name+"</option>");
				}
				
			})
		}
	});
};

/*
 * 输入页面提示信息
 */
function tips(){
	var rs="<%=session.getAttribute("msg")%>";
	if(rs!="null"){
		<%session.removeAttribute("msg");%>
		alert(rs);
	}
};


$(function(){
	//动态添加select的option
	getOrganization();
	//返回提示信息
	tips();
});
</script>
<body>
<!-- 面包屑导航 -->
<div class="BreadCrumb">
	<ol class="BreadCrumb BreadCrumb-list">
	  <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/home">Home</a></li>
	  <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/inventory/list" >Inventory</a></li>
	  <li class="breadcrumb-item active">修改</li>
	</ol>
</div>
<div class="container" style="margin-top:100px"> 
	<div class="row">
     	<div class="col-sm-12">
     		<div class="Panel">
				<form role="form" id="addForm" name="add" action="${pageContext.request.contextPath}/inventory/doEdit" method="post">
					<input type="hidden" name="id" value="${inventory.id }"/>
					<div class="form-group">
				      <label for="name"><span class="xingSpan">*</span>name:</label>
				      <input type="text" class="form-control" id="name" name="name" checked="checked" required value="${inventory.name}"/>
				    </div>
				    <div class="form-group">
					  <label class="form-control-label" for="description">description</label>
					  <input type="text" class="form-control" id="description" name="description" value="${inventory.description}">
					</div>
					<div class="form-group">
					  <label class="form-control-label" for="organization"><span class="xingSpan">*</span>organization</label>
					  <select id="organization" name="organization" class="form-control" required><option value="">请选择</option></select>
					</div>
					<button id="addBton" type="submit" class="btn btn-navy">提交</button>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>