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

function add(){
	var url="${pageContext.request.contextPath}/project/toAdd";
	location.href=url;
};

function edit(id){
	var url="${pageContext.request.contextPath}/project/toEdit?id="+id;
	location.href=url;
}

function del(id){
	if(!confirm("确认删除"+id+"项目")){
		return;
	}
	var url="${pageContext.request.contextPath}/project/toDelete?id="+id;
	location.href=url;
}


</script>
<body>
<!-- 返回主页 -->
<a href="${pageContext.request.contextPath}/main" >返回主菜单</a>
<br><hr>
<!--查询操作  -->
<input type="button"  onclick="add()" value="新增"/><br><br>
<form action="${pageContext.request.contextPath}/project/search" method="post">
	name:<input type="text" name="name" value="${project.name}">
<!-- type:<select type="text" name="scm_type"> 
		<option value=" ">Manual</option>
		<option value="git">Git</option>
	</select>-->
	<input type="submit" value="查询">
</form>
<br><hr><br>
<table border="1px">
	<tr>
		<td>id</td>
		<td>name</td>
		<td>type</td>
		<td>revision</td>
		<td>last_updated</td>
		<td>操作</td>
	</tr>
	<c:if test="${requestScope.list !=null}">
		<c:forEach var="project" items="${requestScope.list}">
			<tr>
				<td>${project.id}</td>
				<td>${project.name}</td> 
				<td>${project.scm_type=='' ? 'Manual' : 'Git'}</td>
				<td>${project.scm_revision}</td>
				<td>${project.last_updated}</td>
				<td><input type="button" onclick="edit(${project.id})" value="修改"/>
				<input type="button"  onclick="del(${project.id})" value="删除"/></td>
			</tr>
		</c:forEach>
	</c:if>
</table>


</body>
</html>