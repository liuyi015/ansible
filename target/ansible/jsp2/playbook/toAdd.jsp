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
		//删除信息，防止刷新页面的时候出现
		<%session.removeAttribute("msg");%>
		alert(rs);
	}
	getPlaybook();
});

function getPlaybook(){
	$.ajax({
		url:"${pageContext.request.contextPath}/playbook/getPBPackage",
		type:"get",
		async:false,
		dataType:"json",
		success:function(data){
			for(var i in data){
                $("#PBPackage").append("<option value='"+data[i]+"'>"+data[i]+"</option>");
          }
		}
	});
};

function select(){
    var folder=$("#PBPackage").val();
   	$.ajax({
   		url:"${pageContext.request.contextPath}/playbook/readFile",
   		type:"get",
   		//async:false,
   		data:{'uri':folder},
   		success:function(data){
            $("#myDiv").html(data);
      	}
   	});
}
function checkName(){
	var projectname=$("#project").val();
	$.ajax({
   		url:"${pageContext.request.contextPath}/playbook/checkProjectName",
   		type:"get",
   		data:{'projectName':projectname},
   		dataType:"json",
   		success:function(data){
   			if(data=="true"){
   				$("#rs").html("<font color='red'>*名字已存在！！！</font>");
   				$("#addBton").attr("disabled","true");
   			}else{
   				$("#rs").html("");
   				$("#addBton").removeAttr("disabled");
   			}
      	}
   	});
}
function doAdd(){
	 var palybook=$("#PBPackage").val();
	if(palybook==null ||palybook==''){
		alert("请选择playbook功能");
		return;
	} 
	var projectname=$("#project").val();
	if(projectname==null || projectname==''){
		alert("项目名字不能为空！");
		return;
	}
	var mytable=document.getElementById("mytable");
	var len=mytable.rows.length;//获得行数
	for(var i=0;i<len;i++){
		var valueId="parameter_value"+i;
		var value=$("#"+valueId).val();
		if(value==null ||value==""){
			alert("参数赋值不能为空");
			return;
		}
	}
	$("#addForm").submit();
};

</script>
</head>
<body>
<!-- 返回主页 -->
<a href="${pageContext.request.contextPath}/main" >返回主菜单</a>
<br><hr>
<h4>新增playbook</h4>
<!-- <input type="button"  onclick="add()" value="add"/> -->
<form id="addForm" name="addForm" action="${pageContext.request.contextPath}/playbook/doAdd" method="post">
	*playbook功能:<select id="PBPackage" name="folder" onchange="select()"><option value="">选择playbook功能</option></select><br><br>
	*项目文件夹名字:<input id="project" name="peoject_name" onblur="checkName()"/> <div id="rs"></div><br><br>
	<div id="myDiv"></div>
	<hr><br>
	<input id="addBton" type="button" value="提交"  onclick="doAdd()"/> 
</form>


</body>
</html>