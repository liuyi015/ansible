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
<!-- 返回主页 -->
<a href="${pageContext.request.contextPath}/main" >返回主菜单</a>
<a href="${pageContext.request.contextPath}/inventory/list" >返回上一页</a>
<br><hr>

<div id="groupDiv">
<h4>groups</h4>
<input type="button"  onclick="addGroup(${inventory})" value="新增"/>
<br><br>
<%-- <form action="${pageContext.request.contextPath}/inventory/search" method="post">
	name:<input type="text" name="name" value="${inventory.name }">
	<input type="button" value="查询">
</form>
<br><hr><br> --%>
<table border="1px">
	<tr>
		<td>name</td>
		<td>操作</td>
	</tr>
	<c:if test="${requestScope.rootGrops !=null}">
		<c:forEach var="group" items="${requestScope.rootGrops}">
			<tr>
				<td>${group.name}</td>
				<td><input type="button" onclick="editGroup(${group.id})" value="修改"/>
				<input type="button"  onclick="delGroup(${group.id},${inventory})" value="删除"/></td>
			</tr>
		</c:forEach>
	</c:if>
</table>
</div>
<br><hr>
<div id="hostDiv">
<h4>主机</h4>
<input type="button"  onclick="addHost(${inventory})" value="新增"/>
<br><br>
<%-- <form action="${pageContext.request.contextPath}/inventory/search" method="post">
	name:<input type="text" name="name" value="${inventory.name }">
	<input type="button" value="查询">
</form>
<br><hr><br> --%>
<table border="1px">
	<tr>
		<td>name</td>
		<td>enabled</td>
		<td>操作</td>
	</tr>
	<c:if test="${requestScope.hosts !=null}">
		<c:forEach var="host" items="${requestScope.hosts}">
			<tr>
				<td>${host.name}</td>
				<td>${host.enabled}</td>
				<td><input type="button" onclick="editHost(${host.id})" value="修改"/>
				<input type="button"  onclick="delHost(${host.id},${inventory})" value="删除"/></td>
			</tr>
		</c:forEach>
	</c:if>
</table>
</div>
</body>
</html>