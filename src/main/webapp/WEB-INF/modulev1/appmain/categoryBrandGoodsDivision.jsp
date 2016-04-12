<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commonJsp.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>缺断货不补货原因</title>
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
<script type="text/javascript" src="<%=basePath%>modulev2/js/feedback/categoryBrandGoodsDivision.js"></script>
</head>
<body>

	<table id="categoryBrandGoodsDivisionList"></table>
	<a id="buyType" style="display:none">${requestScope.buyType }</a>
	<a id="categoryCode" style="display:none">${requestScope.categoryCode }</a>
	<a id="brandCode" style="display:none">${requestScope.brandCode }</a>
	<a id="goodsCode" style="display:none">${requestScope.goodsCode }</a>
	<a id="id" style="display:none">${requestScope.goodsId }</a>
	<a id="buyTypeAuthRole" style="display:none">${requestScope.buyTypeAuthRole }</a>

  <div id="tb">
    <table>
      <tbody>
        <tr>
          <td colspan="4">
          <a href="javascript:void(0);"  class="easyui-linkbutton" data-options="iconCls:'icon-edit'" id="addResultNoReasonBtn" style="display:none">是补货</a>
          <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" id="editResultNoReasonBtn" style="display:none">否补货</a> 
          <a href="javascript:history.go(-1);"  class="easyui-linkbutton" data-options="iconCls:'icon-edit'">返回</a>
        </tr>
      </tbody>
    </table>
  </div>
    
  <!-- 补货 -->
  <div id="addResultNoReasondlg" class="easyui-dialog" style="width: 400px; height: 300px; padding: 10px 20px"
    closed="true" buttons="#addResultNoReasondlg-buttons" data-options="modal:true,top:100">
    <form id="addResultNoReasonfm" method="post">
    	<input name="resultFlag" id="resultFlag" type="hidden" value="1"/>
    	<input name="buyType" id="buyType" type="hidden" value="${requestScope.buyType}"/>
    	<input name="categoryCode" id="categoryCode" type="hidden" value="${requestScope.categoryCode}"/>
    	<input name="brandCode" id="brandCode" type="hidden" value="${requestScope.brandCode}"/>
    	<input name="goodsCode" id="goodsCode" type="hidden" value="${requestScope.goodsCode}"/>
    	<input name="ids" id="id1" type="hidden" value=""/>
      <table>
        <tr>
          <td class="labelTd">请输入订单号：</td>
          <td><input id="order" name="order" class="easyui-validatebox" required="true"></td>
        </tr>
      </table>
    </form>
  </div>
  <div id="addResultNoReasondlg-buttons">
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" id="addResultNoReasonSaveBtn">提交</a> 
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#addResultNoReasondlg').dialog('close')">取消</a>
  </div>
  <!-- 补货结束 -->
  
  <!-- 否补货开始 -->
  <div id="editResultNoReasondlg" class="easyui-dialog" style="width: 400px; height: 300px; padding: 10px 20px"
    closed="true" buttons="#editResultNoReasondlg-buttons" data-options="modal:true,top:100">
    <form id="editResultNoReasonfm" method="post">
    	<input name="resultFlag" id="resultFlag" type="hidden" value="0"/>
    	<input name="buyType" id="buyType" type="hidden" value="${requestScope.buyType}"/>
    	<input name="categoryCode" id="categoryCode" type="hidden" value="${requestScope.categoryCode}"/>
    	<input name="brandCode" id="brandCode" type="hidden" value="${requestScope.brandCode}"/>
    	<input name="goodsCode" id="goodsCode" type="hidden" value="${requestScope.goodsCode}"/>
    	<input name="ids" id="id2" type="hidden" value=""/>
    	<input name="resultNoName" id="resultNoName" type="hidden" value=""/>
    	
      <table>
        <tr>
          <td class="labelTd">无法补货原因：</td>
          <td><select id="resultNoCode" name="resultNoCode" class="easyui-validatebox" required="true">
         	<option>请选择</option>
          	<c:forEach items="${requestScope.reason.result}" var="reasonOption">
          		<option id="resultNoName1" value="${reasonOption.resultNoCode }">${reasonOption.resultNoName}</option>
          	</c:forEach>
          </select></td>
        </tr>
      </table>
    </form>
  </div>

  <div id="editResultNoReasondlg-buttons">
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" id="editResultNoReasonSaveBtn">提交</a> 
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#editResultNoReasondlg').dialog('close')">取消</a>
  </div>
  
</body>
</html>