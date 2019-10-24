<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>多表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/demo02/demoClass/">多表列表</a></li>
		<shiro:hasPermission name="demo02:demoClass:edit"><li><a href="${ctx}/demo02/demoClass/form">多表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="demoClass" action="${ctx}/demo02/demoClass/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>班级名：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>班级编号</th>
				<th>班级名</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<th>删除标记</th>
				<shiro:hasPermission name="demo02:demoClass:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="demoClass">
			<tr>
				<td><a href="${ctx}/demo02/demoClass/form?id=${demoClass.id}">
					${demoClass.id}
				</a></td>
				<td>
					${demoClass.name}
				</td>
				<td>
					<fmt:formatDate value="${demoClass.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${demoClass.remarks}
				</td>
				<td>
					${fns:getDictLabel(demoClass.delFlag, 'del_flag', '')}
				</td>
				<shiro:hasPermission name="demo02:demoClass:edit"><td>
    				<a href="${ctx}/demo02/demoClass/form?id=${demoClass.id}">修改</a>
					<a href="${ctx}/demo02/demoClass/delete?id=${demoClass.id}" onclick="return confirmx('确认要删除该多表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>