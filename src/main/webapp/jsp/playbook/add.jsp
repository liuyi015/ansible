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
   		url:"${pageContext.request.contextPath}/playbook/getPBTemp",
   		type:"get",
   		async:false,
   		data:{'uri':folder},
   		dataType:"json",
   		success:function(data){
   			for(var i in data){
                   $("#palybook").append("<option value='"+data[i]+"'>"+data[i]+"</option>");
             }
   		}
   	});
}

function doAdd(){
	 var palybook=$("#palybook").val();
	if(palybook==null ||palybook==''){
		alert("请选择playbook模板");
		return;
	} 
	var mytable=document.getElementById("mytable");
	var len=mytable.rows.length;//获得行数
	for(var i=0;i<len;i++){
		var nameId="name"+i;
		var valueId="value"+i;
		var name=$("#"+nameId).val();
		if(name==null ||name==""){
			alert("变量名不能为空");
			return;
		}
		var value=$("#"+valueId).val();
		if(value==null ||value==""){
			alert("变量值不能为空");
			return;
		}
	}
	$("#addForm").submit();
};

function del(row){
	//row就是当前对象 this
	var i=$(row).parents("tr").index();  //获取当前tr的下标
	$(row).parents("tr").remove();
};

var index=1;
function addRow(){
	var text="<tr>"+
		"<td>*变量名:</td><td><input type=\"text\" id=\"name"+index+"\" name=\"parameter["+index+"].parameter_name\"/></td>"+
		"<td>*变量值:</td><td><input type=\"text\" id=\"value"+index+"\" name=\"parameter["+index+"].parameter_value\"/></td>"+
		"<td><input type=\"button\" onclick=\"del(this)\" value=\"删除该行\"/></td>"+
		"</tr>";
	jQuery("table").append(text);
	index+=1;
}
</script>
</head>
<body>
<!-- 返回主页 -->
<a href="${pageContext.request.contextPath}/main" >返回主菜单</a>
<br><hr>
<h4>新增playbook</h4>
<!-- <input type="button"  onclick="add()" value="add"/> -->
<form id="addForm" name="addForm" action="${pageContext.request.contextPath}/playbook/doAdd" method="post">
	*playboo功能:<select id="PBPackage" name="folder" onchange="select()"><option value="">选择playbook功能</option></select><br><br>
	*playbook模块名:<select id="palybook" name="playbook"><option value="">选择playbook模板</option></select><br><br>
	<table id="mytable">
	<tr>
	<td>*变量名:</td><td><input type="text" id="name0" name="parameter[0].parameter_name"/></td>
	<td>*变量值:</td><td><input type="text" id="value0" name="parameter[0].parameter_value"/></td>
	</tr>
	</table>
	<hr><br>
	<input type="button" value="添加一行" onclick="addRow()"/>&nbsp;&nbsp;
	<input type="button" value="提交"  onclick="doAdd()"/>
</form>


</body>
</html>