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
	//获取config信息
	getConfig();
});

/* 
获取config信息
*/
function getConfig(){
	$.ajax({
		type:"GET",
		async:false, //设为同步请求（异步加载的话后面的遍历方法获取不到option）
		url:"${pageContext.request.contextPath}/config/getConfig",
		success:function(data){
			var jsonDate=JSON.parse(data);
			var base_dir=jsonDate.project_base_dir;
			$("#base_dir").val(base_dir);
			var local_path=jsonDate.project_local_paths;
			//动态添加select的option
			for(var i in local_path){
				$("#playbook").append("<option value='"+local_path[i]+"'>"+local_path[i]+"</option>");
			}
		}
	});
}
/*
 * 控制div的显示与隐藏
 */
function select(){
	var scm_type=$("#scm_type").val();
	var url="${pageContext.request.contextPath}/config/getConfig"
	if(scm_type==''){
		$("#path").show();
		$("#url").hide();
	}else if(scm_type=='git'){
		$("#path").hide();
		$("#url").show();
	}else{
		$("#path").hide();
		$("#url").hide();
	}
}

function doAdd(){
	$("#addform").submit();
};
</script>
</head>
<body>
<form id="addform" name="add" action="${pageContext.request.contextPath}/project/doAdd" method="post">
	*name:<input type="text" name="name"/><br><br>
	description:<input type="text" name="description"/><br><br>
	*organization:<input type="text" name="organization" value="1"/><br><br>
	*scm_type:<select name="scm_type" id="scm_type" onchange="select()">
				<option value="请选择上传方式">请选择上传方式</option>
				<option value="">Manual</option>
				<option value="git">Git</option>
			</select><br><br>
	<div id="path" hidden="">
		PROJECT BASE PATH:<input type="text" id ="base_dir" disabled="disabled"/><br><br>
		PLAYBOOK DIRECTORY:<select id="playbook" name="local_path"><option value="">请选择一个项目</option></select>
	</div>
	<div id="url" hidden="">
		scm_url:<input type="text" name="scm_url"/><br><br>
	</div>
	
	<input type="button"  value="增加"  onclick="doAdd()" />
</form>


</body>
</html>