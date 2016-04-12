<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commonJsp.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户查询</title>
<style type="text/css">
.align-right {
	text-align: right;
}

.labelTd {
	font-size: 12px;
	text-align: right;
}

.label {
	font-size: 12px;
}
</style>
<script type="text/javascript" src="<%=basePath%>modulev2/js/checkFeedback/category.js"></script>
</head>
<body>
	<table id="categoryList">
	
		<%-- <tr>
			<th>品类</th>
			<th>采购形式</th>
			<th>数量</th>
		</tr>
		<c:forEach items="${requestScope.list }" var="sapFeedback">
			<tr>
				
				<td rowspan="2">${sapFeedback.categoryName }</td>
				<td><a href="goToCategoryBrandPage?categoryCode='+ ${sapFeedback.categoryCode } +'&buyType=0">地采</a></td>
				<td>${sapFeedback.dcnum }</td>
			
			</tr>
			<tr>
				
				<td><a href="goToCategoryBrandPage?categoryCode='+ ${sapFeedback.categoryCode } +'&buyType=1">集采</a></td>
				<td>${sapFeedback.jcnum }</td>
				
			</tr>
		</c:forEach> --%>
	</table>
	
	
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
	
	<a id="buyTypeAuth" style="display:none">${requestScope.buyTypeAuth }</a>
	<a id="year" style="display:none">${requestScope.year }</a>
	<a id="month" style="display:none">${requestScope.month }</a>
	<a id="weekCount" style="display:none">${requestScope.weekCount }</a>
	<a id="checkMessage" style="display:none">${requestScope.checkMessage }</a>

</body>
</html>