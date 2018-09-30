<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java"%>
<!-- 引入springmvc标签库，才能解析 ${pageContext.request.contextPath}-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ansibleTest</title>
<script type="text/javascript" src="static\js\jquery-3.3.1.min.js"></script>
<link href="static/bootstrap-4.0.0-dist/css/bootstrap.css" rel="stylesheet">
<!-- <link href="static/css/custom.min.css" rel="stylesheet"> -->
<script src="static/bootstrap-4.0.0-dist/js/bootstrap.min.js"></script>
<script type="text/javascript">

//控制iframe 自适应高度宽度
function changeFrameHeight(){
    var ifm= document.getElementById("mainFrame"); 
    ifm.height=document.documentElement.clientHeight;

};
window.onresize=function(){  
     changeFrameHeight();  

}; 
</script>
<style type="text/css">
body {
	overflow: hidden !important;
}
</style>
</head>
<body>
<%-- <a href="${pageContext.request.contextPath}/doMe">aboutMe</a>
<br><br>
<a href="${pageContext.request.contextPath}/user/all">allUser</a> --%>
<!-- 导航菜单 （响应式）-->
 <div class="navbar navbar-expand-lg fixed-top navbar-dark" style="background-color: #003366 /* 002B80  */">
 	<div class="container-fluid">
	  <a class="navbar-brand" href="#">Tower</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
       <div class="collapse navbar-collapse" id="navbarResponsive">
	    <ul class="navbar-nav mr-auto">
	      <li class="nav-item">
	        <a class="nav-link" target="mainFrame" href="${pageContext.request.contextPath}/playbook/toAdd">新建playbook</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" target="mainFrame" href="${pageContext.request.contextPath}/project/list">allProject</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" target="mainFrame" href="${pageContext.request.contextPath}/inventory/list">allInventory</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" target="mainFrame" href="${pageContext.request.contextPath}/templates/list">allTemplate</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" target="mainFrame" href="${pageContext.request.contextPath}/job/list">allJob</a>
	      </li>
	    </ul>
	   </div>
  	</div>
</div>

<!--内容展示页  -->
<div>
<!-- 		<iframe src="index.html" name="mainFrame" id="mainFrame" style=" border:0;" width="100%" height="100%" noresize="noresize"  onload="changeFrameHeight()"></iframe>
 -->		<iframe src="${pageContext.request.contextPath}/home" name="mainFrame" id="mainFrame"  width="100%" onload="changeFrameHeight()" frameborder="0"></iframe>
</div>
</body>
</html>