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
	var project='${template.project}';
	$.ajax({
		type:"GET",
		async:false, //设为同步请求（异步加载的话后面的遍历方法获取不到option）
		url:"${pageContext.request.contextPath}/project/allProject",
		dataType:"json",
		success:function(data){
			//动态添加select的option
			$.each(data,function(i,item){
				if(item!=null){
					if(item.id==project){
						$("#project").append("<option value='"+item.id+"' selected='selected'>"+item.name+"</option>");
					}else{
						$("#project").append("<option value='"+item.id+"'>"+item.name+"</option>");
					}		
				}
				
			});
		}
	});
};
function allCredential(){
	var credential='${template.credential}';
	$.ajax({
		type:"GET",
		async:false, //设为同步请求（异步加载的话后面的遍历方法获取不到option）
		url:"${pageContext.request.contextPath}/credential/getCredentialByKind",
		data:{kind:"ssh"},
		dataType:"json",
		success:function(data){
			//动态添加select的option
			$.each(data,function(i,item){
				if(item!=null){
					if(credential==item.id){
						$("#credential").append("<option value='"+item.id+"' selected='selected'>"+item.name+"</option>");
					}else{
						$("#credential").append("<option value='"+item.id+"'>"+item.name+"</option>");
					}	
				}
				
			});
		}
	});
}

function allInventory(){
	var inventory='${template.inventory}';
	$.ajax({
		type:"GET",
		async:false, //设为同步请求（异步加载的话后面的遍历方法获取不到option）
		url:"${pageContext.request.contextPath}/inventory/allInventory",
		dataType:"json",
		success:function(data){
			//动态添加select的option
			$.each(data,function(i,item){
				if(item!=null){
					if(item.id==inventory){
						$("#inventory").append("<option value='"+item.id+"' selected='selected'>"+item.name+"</option>");
					}else{
						$("#inventory").append("<option value='"+item.id+"'>"+item.name+"</option>");
					}
				}
				
			});
		}
	});
}

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

//回显
function echo(){
	var temp='${template.job_type}';
	if(temp=="run"){
		$("#run").attr("selected","selected");
	}else if(temp=="check"){
		$("#check").attr("selected","selected");
	}else if(temp=="scan"){
		$("#scan").attr("selected","selected");
	}
	
	var verbosity='${template.verbosity}';
	$("#"+'${template.verbosity}').attr("selected","selected");
}


function doEdit(){
	$("#editform").submit();
};


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
	//回显
	echo();
	
});

</script>
</head>
<body>
<!-- 返回主页 -->
<a href="${pageContext.request.contextPath}/main" >返回主菜单</a>
<a href="${pageContext.request.contextPath}/templates/list" >返回上一页</a>
<br><hr>
<form id="editform" name="edit" action="${pageContext.request.contextPath}/templates/doEdit" method="post">
	<input type="hidden" name="id" value="${template.id }"/>
	*name:<input type="text" name="name"  value="${template.name }"/><br><br>
	description:<input type="text" name="description" value="${template.description}"/><br><br>
	*job type:<select id="job_type" name="job_type" ><option value="">请选择工作类型</option>
		<option value="run" id="run">Run</option>
		<option value="check" id="check">Check</option>
		<option value="scan" id="scan">Scan</option>
	</select><br><br>
	*inventory:<select id="inventory" name="inventory"><option value="">请选择</option></select><br><br>
	*project:<select name="project" id="project" onchange="selProject()"><option value="">请选择项目</option></select><br><br>
	*playbook:<input type="text" id="playbook" name="playbook" disabled="disabled" value="${template.playbook}"/><br><br>
	<input type="hidden" id="playbook1" name="playbook" value="${template.playbook}"/>
	*machine credential:<select id="credential" name="credential"><option value="">请选择</option></select><br><br>
	*verbosity:<select id="verbosity" name="verbosity"><option value="">请选择</option>
		<option value="0" id="0">"0 (Normal)"</option>
		<option value="1" id="1">"1 (Verbose)"</option>
		<option value="2" id="2">"2 (More Verbose)"</option>
		<option value="3" id="3">"3 (Debug)"</option>
		<option value="4" id="4">"4 (Connection Debug)"</option>
		<option value="5" id="5">"5 (WinRM Debug)"</option>
	</select><br><br>
	<input type="button"  value="提交"  onclick="doEdit()" />
</form>

</body>
</html>