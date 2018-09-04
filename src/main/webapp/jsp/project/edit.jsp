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
 * 获取config信息
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

$(function(){
	////动态添加select的option
	getConfig();
	
	//scm_type回显
	var scm_type='${project.scm_type}';
	//alert(scm_type);
	if(scm_type==''){
		$("#man").attr('selected','selected');
		$("#path").show();
	}else if(scm_type=='git'){
		$("#url").show();
		$("#git").attr('selected','selected');
	}
	
	//返回提示信息
	tips();
});
</script>
<body>
<!-- 返回主页 -->
<a href="${pageContext.request.contextPath}/main" >返回主菜单</a>
<a href="${pageContext.request.contextPath}/project/list" >返回上一页</a>
<br><hr>
<form id="add" name="add" action="${pageContext.request.contextPath}/project/doEdit" method="put">
	<input type="hidden" name="id" value="${project.id }"/>
	*name:<input type="text" name="name" value="${project.name}"></input><br><br>
	description:<input type="text" name="description" value="${requestScope.project.description}"></input><br><br>
	*organization:<input type="text" name="organization" value="${requestScope.project.organization}"></input><br><br>
	*scm_type:<select id ="scm_type" name="scm_type" value="${requestScope.project.scm_type}" onchange="select()">
				<option value="" id="man">Manual</option>
				<option value="git" id="git">Git</option>
			</select><br><br>
<%-- 	project base path:<input type="text" name="local_path" value="${requestScope.project.local_path}"/></input><br><br>
	scm_url:<input type="text" name="scm_url" value="${requestScope.project.scm_url}"></input><br><br>
			</select><br><br> --%>
	<div id="path" hidden="">
		PROJECT BASE PATH:<input type="text" id ="base_dir" disabled="disabled"/><br><br>
		PLAYBOOK DIRECTORY:<select id="playbook" name="local_path"><option value="${project.local_path}" selected="selected">${project.local_path}</option></select>
	</div>
	<div id="url" hidden="">
		scm_url:<input type="text" name="scm_url" value="${project.scm_url }"/><br><br>
	</div>
	<input type="submit" value="提交" />
</form>


</body>
</html>