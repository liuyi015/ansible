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
<!-- <input type="button"  onclick="add()" value="add"/> -->
<form id="add" name="add" action="${pageContext.request.contextPath}/project/doAdd" method="post">
	*name:<input type="text" name="name"/><br><br>
	description:<input type="text" name="description"/><br><br>
	*organization:<input type="text" name="organization" value="1"/><br><br>
	*scm_type:<select name="scm_type">
				<option value="">Manual</option>
				<option value="git">Git</option>
			</select><br><br>
	project base path:<input type="text" name="local_path"/><br><br>
	scm_url:<input type="text" name="scm_url"/><br><br>
	<input type="submit" value="提交" />
</form>


</body>
</html>