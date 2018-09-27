<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html>
<head>
<title>ansibleTest</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.3.1.js"></script>
<link href="${pageContext.request.contextPath}/static/bootstrap-4.0.0-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/static/bootstrap-4.0.0-dist/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container" style="margin-top:30px">
 <div class="jumbotron">
      <form id="loginForm" class="form-signin" action="${pageContext.request.contextPath}/doLogin" method="post">
       <!--  <img class="mb-4" src="images/logo.png" alt="" width="180" height="72"> -->
        <h2 class="form-signin-heading text-center">Please sign in</h2>
        <div class="form-group">
	        <label class="col-form-label" for="inputDefault">Username</label>
	        <input type="text" id="inputUsername" name="username" class="form-control" placeholder="Username" required autofocus> </input>
        </div>
        <div class="form-group">
        <label for="inputPassword">Password</label>
        <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required />
        </div>
        <!-- <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div> -->
        <button class="btn btn-lg btn-primary btn-block" id="check" type="submit" >Sign in</button>
      </form>
  </div> 
</div>
</body>
</html>