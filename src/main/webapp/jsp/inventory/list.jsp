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
<!-- 返回主页 -->
<a href="${pageContext.request.contextPath}/main" >返回主菜单</a>
<br><hr>
<input type="button"  onclick="add()" value="新增"/>
<br><br>
<form action="${pageContext.request.contextPath}/inventory/search" method="post">
	name:<input type="text" name="name" value="${inventory.name }">
	<input type="submit" value="查询">
</form>
<br><hr><br>
<table border="1px">
	<tr>
		<td>id</td>
		<td>name</td>
		<td>organization</td>
		<td>操作</td>
	</tr>
	<c:if test="${requestScope.list !=null}">
		<c:forEach var="inventory" items="${requestScope.list}">
			<tr>
				<td>${inventory.id}</td>
				<td>${inventory.name}</td>
				<td>${inventory.summary_fields.organization.name}</td>
				<td><input type="button" onclick="view(${inventory.id})" value="查看详情"/>
				<input type="button" onclick="edit(${inventory.id})" value="修改"/>
				<input type="button"  onclick="del(${inventory.id})" value="删除"/></td>
			</tr>
		</c:forEach>
	</c:if>
</table>


</body>
</html>