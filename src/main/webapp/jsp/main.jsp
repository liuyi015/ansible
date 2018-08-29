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
<%-- token:<p id="token">${requestScope.tokenResult.token }</p>
expires:<p id="expires">${requestScope.tokenResult.expires }</p> --%>
<a href="${pageContext.request.contextPath}/doMe">aboutMe</a>
<a href="${pageContext.request.contextPath}/user/all">allUser</a>
<br><br>
<a href="${pageContext.request.contextPath}/project/list">allProject</a>
<br><br>
<a href="${pageContext.request.contextPath}/templates/list">allTemplate</a>
<br><br>
<a href="${pageContext.request.contextPath}/inventory/list">allInventory</a>
<br><br>
<a href="${pageContext.request.contextPath}/job/list">allJob</a>
</body>
</html>