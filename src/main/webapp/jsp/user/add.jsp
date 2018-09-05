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
<body>
<h4>新增用户</h4>
<!-- <input type="button"  onclick="add()" value="add"/> -->
<form id="add" name="add" action="${pageContext.request.contextPath}/user/doAdd" method="post">
	*username:<input type="text" name="username"/><br><br>
	first_name:<input type="text" name="first_name"/><br><br>
	last_name:<input type="text" name="last_name"/><br><br>
	email:<input type="text" name="email"/><br><br>
	用户类型:<select name="user_type"><option value="system_administrator">System Administrator</option>
		<option value="normal">Normal User</option>
		<option value="system_auditor">System Auditor</option>
	</select><br><br>
	password:<input type="password" name="password"/><br><br>
	<input type="submit" value="提交" />
</form>


</body>
</html>