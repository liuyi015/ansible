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
function add(){
	var url="${pageContext.request.contextPath}/user/toAdd?";
	location.href=url;
}
</script>
<body>
<input type="button"  onclick="add()" value="add"/>
<table border="1px">
	<tr>
		<td>id</td>
		<td>userName</td>
		<td>email</td>
	</tr>
	<c:if test="${requestScope.userList !=null}">
		<c:forEach var="user" items="${requestScope.userList}">
			<tr>
				<td>${user.id}</td>
				<td>${user.username}</td>
				<td>${user.email}</td>
			</tr>
		</c:forEach>
	</c:if>
</table>


</body>
</html>