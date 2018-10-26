<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java"%>
<!-- 引入springmvc标签库，才能解析 ${pageContext.request.contextPath}-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ansibleTest</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.3.1.js"></script>
<link href="${pageContext.request.contextPath}/static/bootstrap-4.0.0-dist/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/custom.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/common.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/static/bootstrap-4.0.0-dist/js/bootstrap.min.js"></script>
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
<!-- 面包屑导航 -->
<div class="BreadCrumb">
	<ol class="BreadCrumb BreadCrumb-list">
	  <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/home">首页</a></li>
	  <li class="breadcrumb-item active">新增playbook</li>
	  <li style="text-align: right; width: 85%"><a href="${pageContext.request.contextPath}/templates/list"><i class="fa fa-refresh"></i></a></li>
	</ol>
</div>
<!-- <div class="jumbotron">
<div class="container"> -->
       <div class="container" style="margin-top:100px"> 
       <!-- <div class="content"> -->
         <div class="row">
        	<div class="col-sm-12">
        	<div class="Panel">
            <div class="nest" id="FootableClose">
				<form role="form" id="addForm" name="addForm" action="${pageContext.request.contextPath}/playbook/doAdd" method="post">
					<div class="form-group">
				      <label for="playbookSelect"><span class="xingSpan">*</span>playbook功能:</label>
				      <select id="PBPackage" name="folder" class="form-control" onchange="select()"><option value="">选择playbook功能</option></select>
				    </div>
				    <div class="form-group">
					  <label class="form-control-label" for="inputSuccess1"><span class="xingSpan">*</span>项目文件夹名字</label>
					  <input type="text" class="form-control" id="project" name="peoject_name" onblur="checkName()">
					  <div id="rs" ></div>
					</div>
					<div id="myDiv"></div>
					<button id="addBton" type="button" class="btn btn-navy" onclick="doAdd()">提交</button>
				</form>
			</div>
		</div>
		</div>
		</div>
		</div>
</body>
</html>