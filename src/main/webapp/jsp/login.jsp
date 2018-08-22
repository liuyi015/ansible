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
function login(){
	var user={
			username:$("#username").val(),
			password:$("#password").val()
	};
	$.ajax({
		type:'post',
		url:'https://172.168.65.88/api/v1/authtoken/',
		contentType: "application/json",   //入参为json格式的数据
		//data:'{"username":"admin","password":"admin"}', /*这个地方不是json对象，是json字符串,所以json要加''  */
		//data:JSON.stringify({"username":"admin","password":"admin"}), 
		data:JSON.stringify(user),
		dataType:"json",   //返回json格式的数据
		success:function(data){
			$("#token").text(data.token);
			$("#expires").text(data.expires);
		}
	});
}
function doLogin(){
	var username=$("#username").val();
	if(username==null||username==""){
		alert("username不能为空");
		return false;
	}
	var password=$("#password").val();
	if(password==null||username==""){
		alert("password不能为空");
		return false;
	}
	//document.loginForm.submit();
	$("#loginForm").submit();
}
</script>
<body>
<c:if test="${!empty requestScope.error}">
	<p>${requestScope.error }</p>
</c:if>
<form id="loginForm"  name ="loginForm" action="${pageContext.request.contextPath}/doLogin" method="post">
username:<input type="text" name="username" id="username"/> <br>
password:<input type="password" name="password" id="password"/><br>
<input type="button" name="bt_submit" onclick="doLogin()" value="登录"/><br>

</form>
<!-- token:<p id="token"></p>
expires:<p id="expires"></p> -->
</body>
</html>