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
<form id="add" name="add" action="${pageContext.request.contextPath}/project/doEdit" method="put">
	*name:<input type="text" name="name" value="${requestScope.project.name}"></input><br><br>
	description:<input type="text" name="description" value="${requestScope.project.description}"></input><br><br>
	*organization:<input type="text" name="organization" value="${requestScope.project.organization}"></input><br><br>
	*scm_type:<select name="scm_type" >
				<option value=""  selected='<c:if test="${requestScope.project.scm_type} eq '' "></c:if>'>Manual</option>
				<option value="git">Git</option>
			</select><br><br>
	project base path:<input type="text" name="local_path" value="${requestScope.project.local_path}"/></input><br><br>
	scm_url:<input type="text" name="scm_url" value="${requestScope.project.scm_url}"></input><br><br>
	<input type="submit" value="提交" />
</form>


</body>
</html>