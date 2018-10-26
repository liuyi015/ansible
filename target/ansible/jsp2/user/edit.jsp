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

</script>
</head>
<body>
<!-- 返回主页 -->
<a href="${pageContext.request.contextPath}/main" >返回主菜单</a>
<a href="${pageContext.request.contextPath}/user/all">返回上一页</a>
<br><hr>
<h4>修改用户信息</h4>
<form id="add" name="add" action="${pageContext.request.contextPath}/user/doEdit" method="post">
	<input type="hidden" name="id" value="${user.id }" />
	*username:<input type="text" name="username" value="${user.username }" /><br><br>
	first_name:<input type="text" name="first_name" value="${user.first_name}"/><br><br>
	last_name:<input type="text" name="last_name"value="${user.last_name}"/><br><br>
	email:<input type="text" name="email" value="${user.email}"/><br><br>
	用户类型:<select name="user_type"><option value="system_administrator" ${user.user_type=='system_administrator' ? 'selected':''} >System Administrator</option>
		<option value="normal" ${user.user_type=='normal' ? 'selected':''} >Normal User</option>
		<option value="system_auditor" ${user.user_type=='system_auditor' ? 'selected':''} >System Auditor</option>
	</select><br><br>
	<%-- password:<input type="password" name="password" value="${user.password }"/><br><br> --%>
	<input type="submit" value="提交" />
</form>


</body>
</html>