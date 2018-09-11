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
	<table id="mytable">
	<c:if test="${list !=null}">
	<c:forEach var="param" items="${requestScope.list }">
		<tr>
			<td>*参数名称:</td><td><input type="text"  name="parameter[0].name" value="${param.name }"/></td>
			<td>*参数代码:</td><td><input type="text"  name="parameter[0].parameter_name" value="{${param.parameter_name }"/></td>
			<td>*参数赋值:</td><td><input type="text"  name="parameter[0].parameter_value" value="{${param.parameter_value }"/></td>
		</tr>
	</c:if>
	</c:forEach>
	</table>
</body>
</html>