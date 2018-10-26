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
<script type="text/javascript">
$(function(){
	var rs="<%=session.getAttribute("msg")%>";
	if(rs!="null"){
		<%session.removeAttribute("msg");%>
		alert(rs);
	}
});

function doEdit(){
	$("#groupform").submit();
};
</script>
</head>
<body>
<!-- 返回主页 -->
<a href="${pageContext.request.contextPath}/main" >返回主菜单</a>
<a href="${pageContext.request.contextPath}/inventory/view?id=${group.inventory }">返回上一页</a>
<br><hr>
<!-- 新增模块 -->
<div id="AddGruop">
	<h5>修改group</h5>
	<form id="groupform" name="groupform" action="${pageContext.request.contextPath}/group/doEdit" method="post">
	<input type="hidden" name="inventory" value="${group.inventory }"/>
	<input type="hidden" name="id" value="${group.id }"/>
	*name:<input type="text" name="name" id="groupName" value="${group.name }"/><br><br>
	description:<input type="text" id="groupDescription" name="description" value="${group.description }"/>
	<input type="submit"  value="修改"  onclick="doEdit()" />
</form>
</div>
</body>
</html>