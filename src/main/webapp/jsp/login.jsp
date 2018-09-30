<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html>
<head>
<title>ansibleTest</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.3.1.js"></script>
<link href="${pageContext.request.contextPath}/static/bootstrap-4.0.0-dist/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/common.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/custom.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/static/bootstrap-4.0.0-dist/js/bootstrap.min.js"></script>
<style type="text/css">
body {
	background: #bfbfbf;
}
.b {
	border-radius: 5px;   /* 圆角 */
}
</style>
</head>
<body>
<div class="container"  style="width: 50%; height: 300px;">
 <div class="Panel" style=" margin-top:200px;">
      <form id="loginForm" class="form-signin" action="${pageContext.request.contextPath}/doLogin" method="post">
       <!--  <img class="mb-4" src="images/logo.png" alt="" width="180" height="72"> -->
        <h2 class="form-signin-heading text-center">Please sign in</h2>
        <div class="form-group">
	        <label class="col-form-label" for="inputDefault">Username</label>
	        <input type="text" id="inputUsername" name="username" class="form-control b" placeholder="Username" required autofocus> </input>
        </div>
        <div class="form-group">
	        <label for="inputPassword">Password</label>
	        <input type="password" id="inputPassword" name="password" class="form-control b" placeholder="Password" required />
        </div>
        <!-- <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div> -->
        <div class="form-group" style="text-align: right;">
             <button class="btn btn-navy" id="check" type="submit" >Sign in</button>
        </div>
      </form>
  </div> 
</div>
</body>
</html>