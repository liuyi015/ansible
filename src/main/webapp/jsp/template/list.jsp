<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java"%>
<!-- 引入springmvc标签库，才能解析 ${pageContext.request.contextPath}-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ansibleTest</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
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
	var url="${pageContext.request.contextPath}/templates/toDelete?id="+id;
	location.href=url;
}


</script>
<body>
<input type="button"  onclick="add()" value="新增 JOB TEMPLATE"/>
<table border="1px">
	<tr>
		<td>id</td>
		<td>name</td>
		<td>type</td>
		<td>description</td>
		<td>activity</td>
		<td>labeles</td>
		<td>操作</td>
	</tr>
	<c:if test="${requestScope.list !=null}">
		<c:forEach var="template" items="${requestScope.list}">
			<tr>
				<td>${template.id}</td>
				<td>${template.name}</td>
				<td>${template.type}</td>
				<td>${template.description}</td>
				<td>${template.status}</td>
				<td></td>
				<td><input type="button" onclick="edit(${template.id})" value="修改"/>
				<input type="button"  onclick="del(${template.id})" value="删除"/></td>
			</tr>
		</c:forEach>
	</c:if>
</table>


</body>
</html>