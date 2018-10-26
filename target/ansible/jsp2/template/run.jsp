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
var id;   //全局变量，定时器

$(function(){
	var url="https://172.168.65.88"+'${run.url}';
	//getStatus(url);
	var status=$("#status").html();
	if("successful"!= status && "failed"!= status){
		var referId = window.setInterval(getStatus,3000,url);    //创建一个定时任务
		 id=referId;            //把定时任务赋值给全局变量，方便后面关闭定时任务，在关闭时不会报（referId未定义错误）
	}
	
});

function getStatus(apiurl){
	$.ajax({
		type:"get",
		//async:false, //同步请求
		url:"${pageContext.request.contextPath}/job/getStatus",
		data:{'url':apiurl},
		success:function(data){
			var jsonDate=JSON.parse(data); 
			var status=jsonDate.status;
			//alert(status);
			if("successful" == status || "failed"== status){
				$("#status").html(status);
				$("#result").val(jsonDate.result_stdout);
				window.clearInterval(id);   //关闭定时器
			}else{
				$("#status").html(status+",请等待.......");
				$("#result").val(jsonDate.result_stdout);
			}
		}
	});
}
</script>
<body>
<!-- 返回主页 -->
<a href="${pageContext.request.contextPath}/main" >返回主菜单</a>
<a href="${pageContext.request.contextPath}/templates/list" >返回上一页</a>
<br>
<hr>
<c:if test="${run.type =='inventory_update'}">
<div>
	<h4>详情：</h4>
	名字： ${run.name}<br>
	<table>
	<tr><td>状态:<td><td id="status">${run.status}</td></tr>
	</table>
	license_error： ${run.license_error}<br>
	开始时间： ${run.started}<br>
	结束时间： ${run.finished}<br>
	elapsed： ${run.elapsed}<br>
	launch_type： ${run.launch_type}<br>
	source： ${run.source}<br>
	group： ${run.name}<br>
	overwrite：  ${run.overwrite}<br>
	overwrite_vars： ${run.overwrite_vars}<br>
</div>
</c:if>

<c:if test="${run.type =='job'}">
<div>
	<h4>详情：</h4>
	
	<table>
	<tr><td>状态:<td><td id="status">${run.status}</td></tr>
	</table>
	开始时间： ${run.started}<br>
	结束时间： ${run.finished}<br>
	模块名： ${run.summary_fields.job_template.name}<br>
	工作类型： ${run.job_type}<br>
	运行操作员： ${run.status}<br>
	资源： ${run.summary_fields.inventory.name}<br>
	项目：  ${run.summary_fields.project.name}<br>
	版本： ${run.scm_revision}<br>
	playbook： ${run.playbook}<br>
	机器凭证： ${run.credential}<br>
	verbosity： ${run.verbosity}<br>
</div>
</c:if>
<c:if test="${run.type =='project_update'}">
<div>
	<h4>详情：</h4>
	名字： ${run.name}<br>
	<table>
	<tr><td>状态:<td><td id="status">${run.status}</td></tr>
	</table>
	开始时间： ${run.started}<br>
	结束时间： ${run.finished}<br>
	elapsed： ${run.elapsed}<br>
	launch_type： ${run.launch_type}<br>
	项目： ${run.name}<br>
</c:if>
<c:if test="${run.type =='system_job'}">
<div>
	<h4>详情：</h4>
	名字： ${run.name}<br>
	<table>
	<tr><td>状态:<td><td id="status">${run.status}</td></tr>
	</table>
	开始时间： ${run.started}<br>
	结束时间： ${run.finished}<br>
	elapsed： ${run.elapsed}<br>
	launch_type： ${run.launch_type}<br>
	extra_vars： <br>
	<textarea rows="10" cols="100" >${run.extra_vars}</textarea><br>
</c:if>
<hr>
<div>
<h4>结果：</h4>
<textarea rows="30" cols="100" id="result">${run.result_stdout}</textarea>
</div>

</body>
</html>