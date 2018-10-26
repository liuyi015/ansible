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
function allProject(){
	$.ajax({
		type:"GET",
		async:false, //设为同步请求（异步加载的话后面的遍历方法获取不到option）
		url:"${pageContext.request.contextPath}/project/allProject",
		dataType:"json",
		success:function(data){
			//var jsonDate=JSON.parse(data);
			//动态添加select的option
			$.each(data,function(i,item){
				if(item!=null){
					$("#project").append("<option value='"+item.id+"'>"+item.name+"</option>");
				}
				
			});
		}
	});
};
function allCredential(){
	$.ajax({
		type:"GET",
		async:false, //设为同步请求（异步加载的话后面的遍历方法获取不到option）
		url:"${pageContext.request.contextPath}/credential/getCredentialByKind",
		data:{kind:"ssh"},
		dataType:"json",
		success:function(data){
			//var jsonDate=JSON.parse(data);
			//动态添加select的option
			$.each(data,function(i,item){
				if(item!=null){
					$("#credential").append("<option value='"+item.id+"'>"+item.name+"</option>");
				}
				
			});
		}
	});
}

function allInventory(){
	$.ajax({
		type:"GET",
		async:false, //设为同步请求（异步加载的话后面的遍历方法获取不到option）
		url:"${pageContext.request.contextPath}/inventory/allInventory",
		dataType:"json",
		success:function(data){
			//动态添加select的option
			$.each(data,function(i,item){
				if(item!=null){
					$("#inventory").append("<option value='"+item.id+"'>"+item.name+"</option>");
				}
				
			});
		}
	});
}


$(function(){
	var rs="<%=session.getAttribute("msg")%>";
	if(rs!="null"){
		//删除信息，防止刷新页面的时候出现
		<%session.removeAttribute("msg");%>
		alert(rs);
	}
	
	//获取资源
	allProject();
	allCredential();
	allInventory();
	
});

function selProject(){
	var project =$("#project").val();
	if(project!=''){
		$.ajax({
			type:"GET",
			async:false, //设为同步请求（异步加载的话后面的遍历方法获取不到option）
			url:"${pageContext.request.contextPath}/project/getPlaybook",
			data:{id:project},
			dataType:"json",
			success:function(data){
				if(data!=null){
					$("#playbook").val(data);
					$("#playbook1").val(data);
				}
			}
		});
	}
	return;
};

function doAdd(){
	$("#addform").submit();
};
</script>
</head>
<body>
<!-- 返回主页 -->
<a href="${pageContext.request.contextPath}/main" >返回主菜单</a>
<a href="${pageContext.request.contextPath}/templates/list" >返回上一页</a>
<br><hr>
<form id="addform" name="add" action="${pageContext.request.contextPath}/templates/doAddJobTemplate" method="post">
	*name:<input type="text" name="name"/><br><br>
	description:<input type="text" name="description"/><br><br>
	*job type:<select id="job_type" name="job_type"><option value="">请选择工作类型</option>
		<option value="run">Run</option>
		<option value="check">Check</option>
		<option value="scan">Scan</option>
	</select><br><br>
	*inventory:<select id="inventory" name="inventory"><option value="">请选择</option></select><br><br>
	*project:<select name="project" id="project" onchange="selProject()"><option value="">请选择项目</option></select><br><br>
	*playbook:<input type="text" id="playbook" name="playbook" disabled="disabled"/><br><br>
	<input type="hidden" id="playbook1" name="playbook"/>
	*machine credential:<select id="credential" name="credential"><option value="">请选择</option></select><br><br>
	*verbosity:<select id="verbosity" name="verbosity"><option value="">请选择</option>
		<option value="0">"0 (Normal)"</option>
		<option value="1">"1 (Verbose)"</option>
		<option value="2">"2 (More Verbose)"</option>
		<option value="3">"3 (Debug)"</option>
		<option value="4">"4 (Connection Debug)"</option>
		<option value="5">"5 (WinRM Debug)"</option>
	</select><br><br>
	<input type="button"  value="提交"  onclick="doAdd()" />
</form>

</body>
</html>