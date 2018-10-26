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
<div class="form-group">
	<table id="mytable" style="border-collapse:separate; border-spacing:10px 10px;" >
	<c:if test="${requestScope.list !=null}">
		<c:forEach var="item" items="${requestScope.list }" varStatus="status">
			<div class="form-group row">
			      <label class="col-1 col-form-label">参数名称:</label>
			      <div class="col-4">
			        <input type="text" id="name${status.index}" class="form-control" name="parameter[${status.index}].name" value="${item.name }" readonly="">
			      </div>
			      <input type="hidden" id="parameter_name${status.index}" class="form-control" name="parameter[${status.index}].parameter_name" value="${item.parameter_name }" readonly=""/>
			      <label class="col-1 col-form-label"><span class="xingSpan">*</span>参数赋值:</label>
			      <div class="col-6">
			        <input type="text" id="parameter_value${status.index}" class="form-control" name="parameter[${status.index}].parameter_value" value="${item.parameter_value }" placeholder="${item.remark }"/>
			      </div>
		    </div>
		       <%-- <tr>
					<td>参数名称:</td><td><input type="text" id="name${status.index}" class="form-control" name="parameter[${status.index}].name" value="${item.name }" readonly=""/></td>
					<td>参数代码:</td><td><input type="text" id="parameter_name${status.index}" class="form-control" name="parameter[${status.index}].parameter_name" value="${item.parameter_name }" readonly=""/></td>
					<td><span class="xingSpan">*</span>参数赋值:</td><td><input type="text" id="parameter_value${status.index}" class="form-control" name="parameter[${status.index}].parameter_value" value="${item.parameter_value }"/></td>
				</tr> --%>
			
		</c:forEach>
	</c:if>
	</table>
</div>
</body>
</html>