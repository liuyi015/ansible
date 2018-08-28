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
/* 
获取organization信息
*/
function getOrganization(){
	var organ=${inventory.organization }
	$.ajax({
		type:"GET",
		async:false, //设为同步请求（异步加载的话后面的遍历方法获取不到option）
		url:"${pageContext.request.contextPath}/organization/getOrganization",
		success:function(data){
			var json=JSON.parse(data);
			//动态添加select的option
			$.each(json,function(i,item){
				if(item.id==organ){
					$("#organization").append("<option value='"+item.id+"' selected='selected'>"+item.name+"</option>");
				}else{
					$("#organization").append("<option value='"+item.id+"'>"+item.name+"</option>");
				}
				
			})
		}
	});
};

/*
 * 输入页面提示信息
 */
function tips(){
	var rs="<%=session.getAttribute("msg")%>";
	if(rs!="null"){
		<%session.removeAttribute("msg");%>
		alert(rs);
	}
};


$(function(){
	//动态添加select的option
	getOrganization();
	//返回提示信息
	tips();
});
</script>
<body>
<form id="add" name="add" action="${pageContext.request.contextPath}/inventory/doEdit" method="put">
	<input type="hidden" name="id" value="${inventory.id }"/>
	*name:<input type="text" name="name" value="${inventory.name}"></input><br><br>
	description:<input type="text" name="description" value="${inventory.description}"></input><br><br>
	*organization:<select  id="organization" name="organization"><option value="">请选择</option></select><br><br>
	<input type="submit" value="提交" />
</form>


</body>
</html>