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

function view(id,type){
	var url="${pageContext.request.contextPath}/job/toView?id="+id+"&type="+type;
	location.href=url;
}

function del(id,type){
	if(!confirm("确认删除"+id+"项目")){
		return;
	}
	var url="${pageContext.request.contextPath}/job/toDelete?id="+id+"&type="+type;
	location.href=url;
}


</script>
<body>
<form action="${pageContext.request.contextPath}/job/search" method="post">
	name:<input type="text" name="name" value="${name }">
	<input type="submit" value="查询">
</form>
<br><hr><br>
<table border="1px">
	<tr>
		<td>id</td>
		<td>name</td>
		<td>finished</td>
		<td>操作</td>
	</tr>
	<tr></tr>
	<c:if test="${requestScope.list !=null}">
		<c:forEach var="job" items="${requestScope.list}">
			<tr>
				<td>${job.id}</td>
				<td>${job.name}</td>
				<td>${job.finished}</td>
				<td><input type="button" onclick="view(${job.id},'${job.type}')" value="查看"/>
				<input type="button"  onclick="del(${job.id},'${job.type}')" value="删除"/></td>
			</tr>
		</c:forEach>
	</c:if>
</table>


</body>
</html>