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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="<%=basePath%>modulev2/js/checkFeedback/categoryBrandGoods.js"></script>
</head>
<body>
	<!-- 商品 列表 -->
	<table id="goodsList"></table>
		
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
		
		<%-- <a id="buyType" style="display:none">${requestScope.buyType }</a>
		<a id="categoryCode" style="display:none">${requestScope.categoryCode }</a>
		<a id="brandCode" style="display:none">${requestScope.brandCode }</a>
		<a id="brandName" style="display:none">${requestScope.brandName }</a> --%>
		<input id="buyType" value="${requestScope.buyType }" type="hidden">
		<input id="categoryCode" value="${requestScope.categoryCode }" type="hidden">
		<input id="brandCode" value="${requestScope.brandCode }" type="hidden">
		<input id="brandName" value="${requestScope.brandName }" type="hidden">
</body>
</html>

