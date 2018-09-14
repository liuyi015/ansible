<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java"%>
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
	<c:if test="${requestScope.list !=null}">
		<c:forEach var="item" items="${requestScope.list }" varStatus="status">
			<tr>
				<td>*参数名称:</td><td><input type="text" id="name${status.index}" name="parameter[${status.index}].name" value="${item.name }"/></td>
				<td>*参数代码:</td><td><input type="text" id="parameter_name${status.index}" name="parameter[${status.index}].parameter_name" value="${item.parameter_name }"/></td>
				<td>*参数赋值:</td><td><input type="text" id="parameter_value${status.index}" name="parameter[${status.index}].parameter_value" value="${item.parameter_value }"/></td>
			</tr>
		</c:forEach>
	</c:if>
	</table>
</body>
</html>