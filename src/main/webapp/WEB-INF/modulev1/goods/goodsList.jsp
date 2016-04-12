<%@ page language="java" pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@include file="/commonJsp.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>商品</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="<%=basePath%>modulev1/js/goods/goodsList.js"></script>
</head>
<body>
		<!-- 商品 列表 -->
		<table id="goodsList"></table>

		<!-- 商品 列表 工具栏 -->
		<div id="goodsToolBar">
				<table>
						<tbody>
								<tr>
										<td>商品编码：</td>
										<td><input id="goodsCode" class="easyui-validatebox" /></td>
										<td>商品条码：</td>
										<td><input id="goodsBarcode" class="easyui-validatebox" /></td>
										<td>商品名称：</td>
										<td><input id="goodsName" class="easyui-validatebox" /></td>
										<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'"
												id="goodsSearch">查询</a></td>
								</tr>
						</tbody>
				</table>
		</div>

</body>
</html>

