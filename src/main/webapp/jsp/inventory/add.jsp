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
	//获取organization信息
	getOrganization();
});

/* 
获取organization信息
*/
function getOrganization(){
	$.ajax({
		type:"GET",
		async:false, //设为同步请求（异步加载的话后面的遍历方法获取不到option）
		url:"${pageContext.request.contextPath}/organization/getOrganization",
		success:function(data){
			var json=JSON.parse(data);
			//动态添加select的option
			$.each(json,function(i,item){
				$("#organization").append("<option value='"+item.id+"'>"+item.name+"</option>");
			})
		}
	});
}

function doAdd(){
	$("#addform").submit();
};
</script>
</head>
<body>
<!-- 返回主页 -->
<a href="${pageContext.request.contextPath}/main" >返回主菜单</a>
<a href="${pageContext.request.contextPath}/inventory/list" >返回上一页</a>
<br><hr>
<form id="addform" name="add" action="${pageContext.request.contextPath}/inventory/doAdd" method="post">
	*name:<input type="text" name="name"/><br><br>
	description:<input type="text" name="description"/><br><br>
	*organization:<select  id="organization" name="organization"><option value="">请选择</option></select>
	<br><br>
	
	<input type="button"  value="增加"  onclick="doAdd()" />
</form>


</body>
</html>