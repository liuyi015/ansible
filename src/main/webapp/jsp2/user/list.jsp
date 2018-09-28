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
		<%session.removeAttribute("msg");%>
		alert(rs);
	}
});
function add(){
	var url="${pageContext.request.contextPath}/user/toAdd?";
	location.href=url;
}

function edit(id){
	var url="${pageContext.request.contextPath}/user/toEdit?id="+id;
	location.href=url;
}
function del(id){
	var url="${pageContext.request.contextPath}/user/toDelete?id="+id;
	location.href=url;
}
</script>
<body>
<!-- 返回主页 -->
<a href="${pageContext.request.contextPath}/main" >返回主菜单</a>
<br><hr>
<input type="button"  onclick="add()" value="add"/>
<table border="1px">
	<tr>
		<td>id</td>
		<td>userName</td>
		<td>first_name</td>
		<td>last_name</td>
		<td>email</td>
		<td>操作</td>
	</tr>
	<c:if test="${requestScope.userList !=null}">
		<c:forEach var="user" items="${requestScope.userList}">
			<tr>
				<td>${user.id}</td>
				<td>${user.username}</td>
				<td>${user.first_name}</td>
				<td>${user.last_name}</td>
				<td>${user.email}</td>
				<td><input type="button" onclick="edit(${user.id})" value="修改"></input>
				<input type="button" onclick="del(${user.id})" value="删除"></input></td>
			</tr>
		</c:forEach>
	</c:if>
</table>


</body>
</html>