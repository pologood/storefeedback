<%@ page language="java" pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@include file="/commonJsp.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>品牌相关信息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="<%=basePath%>modulev2/js/feedback/categoryBrand.js"></script>
</head>
<body>
	<!-- 商品品牌 列表 -->
	<table id="goodsBrandList"></table>

	<div id="tb">
		<table>
			<tbody>
				<tr>
					<td colspan="4">
					<a href="javascript:history.go(-1);"  class="easyui-linkbutton" data-options="iconCls:'icon-edit'">返回</a>
				</tr>
			</tbody>
		</table>
	</div>

	<a id="buyType" style="display:none">${requestScope.buyType }</a>
	<a id="categoryCode" style="display:none">${requestScope.categoryCode }</a>

		<!-- 商品品牌 列表 工具栏 -->
		<div id="goodsBrandToolBar">
				<!-- <table>
						<tbody>
								<tr>
										<td>品牌编码：</td>
										<td><input id="brandCode" class="easyui-validatebox" /></td>
										<td>中文描述：</td>
										<td><input id="cnText" class="easyui-validatebox" /></td>
										<td>品牌类型：</td>
										<td><input id="brandClass" class="easyui-validatebox" /></td>
										<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'"
												id="goodsBrandSearch">查询</a></td>
								</tr>
						</tbody>
				</table> -->
		</div>

</body>
</html>

