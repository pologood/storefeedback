<%@ page language="java" pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@include file="/commonJsp.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>商品品类</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="<%=basePath%>modulev1/js/goods/goodsCategoryList.js"></script>
</head>
<body>
		<!-- 商品品类 列表 -->
		<table id="goodsCategoryList"></table>

		<!-- 商品品类 列表 工具栏 -->
		<div id="goodsCategoryToolBar">
				<table>
						<tbody>
								<tr>
										<td>分类代码：</td>
										<td><input id="classCode" class="easyui-validatebox" /></td>
										<td>分类名称：</td>
										<td><input id="className" class="easyui-validatebox" /></td>
										<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'"
												id="goodsCategorySearch">查询</a></td>
								</tr>
						</tbody>
				</table>
		</div>

</body>
</html>

