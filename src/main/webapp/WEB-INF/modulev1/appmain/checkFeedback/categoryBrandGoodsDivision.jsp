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
<script type="text/javascript" src="<%=basePath%>modulev2/js/checkFeedback/categoryBrandGoodsDivision.js"></script>
</head>
<body>

	<table id="categoryBrandGoodsDivisionList"></table>
	<%-- <a id="buyType" style="display:none">${requestScope.buyType }</a>
	<a id="categoryCode" style="display:none">${requestScope.categoryCode }</a>
	<a id="brandCode" style="display:none">${requestScope.brandCode }</a>
	<a id="goodsCode" style="display:none">${requestScope.goodsCode }</a>
	<a id="goodsName" style="display:none">${requestScope.goodsName }</a>
	<a id="id" style="display:none">${requestScope.goodsId }</a>
	<a id="buyTypeAuthRole" style="display:none">${requestScope.buyTypeAuthRole }</a>
	<a id="flag" style="display:none">${requestScope.flag }</a> --%>
	<input id="buyType" value="${requestScope.buyType }" type="hidden">
	<input id="categoryCode" value="${requestScope.categoryCode }" type="hidden">
	<input id="brandCode" value="${requestScope.brandCode }" type="hidden">
	<input id="goodsCode" value="${requestScope.goodsCode }" type="hidden">
	<input id="goodsName" value="${requestScope.goodsName }" type="hidden">
	<input id="id" value="${requestScope.goodsId }" type="hidden">
	<input id="buyTypeAuthRole" value="${requestScope.buyTypeAuthRole }" type="hidden">
	<input id="flag" value="${requestScope.flag }" type="hidden">

  <div id="tb">
    <table>
      <tbody>
        <tr>
          <td colspan="4">
          <a href="javascript:void(0);"  class="easyui-linkbutton" data-options="iconCls:'icon-edit'" id="confirmResultBtn" style="display:none">确认</a>
          <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" id="getRppealListBtn" style="display:none">申诉</a> 
          <a href="javascript:history.go(-1);"  class="easyui-linkbutton" data-options="iconCls:'icon-edit'">返回</a>
        </tr>
       <%--  <tr><td colspan="4" align="center" style="font-size:20px">${requestScope.goodsName }</td></tr> --%>
      </tbody>
    </table>
  </div>
    
  <!-- 添加品类开始 -->
  <div id="addResultNoReasondlg" class="easyui-dialog" style="width: 400px; height: 300px; padding: 10px 20px"
    closed="true" buttons="#addResultNoReasondlg-buttons" data-options="modal:true,top:100">
    <form id="confirmResultfm" method="post">
    	<input name="resultFlag" id="resultFlag" type="hidden" value="1"/>
    	<input name="buyType" id="buyType" type="hidden" value="${requestScope.buyType}"/>
    	<input name="categoryCode" id="categoryCode" type="hidden" value="${requestScope.categoryCode}"/>
    	<input name="brandCode" id="brandCode" type="hidden" value="${requestScope.brandCode}"/>
    	<input name="goodsCode" id="goodsCode" type="hidden" value="${requestScope.goodsCode}"/>
    	<input name="ids" id="id1" type="hidden" value=""/>
      <!-- <table>
        <tr>
          <td class="labelTd">请输入订单号：</td>
          <td><input id="order" name="order" class="easyui-validatebox" required="true"></td>
        </tr>
      </table> -->
    </form>
  </div>
  <div id="addResultNoReasondlg-buttons">
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" id="addResultNoReasonSaveBtn">提交</a> 
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#addResultNoReasondlg').dialog('close')">取消</a>
  </div>
  <!-- 添加品类结束 -->
  
  <!-- ------------申诉页面---------------- -->
  <div id="editResultNoReasondlg" class="easyui-dialog" style="width: 400px; height: 300px; padding: 10px 20px"
    closed="true" buttons="#editResultNoReasondlg-buttons" data-options="modal:true,top:100">
    <form id="appealfm" method="post">
    	<input name="resultFlag" id="resultFlag" type="hidden" value="0"/>
    	<input name="buyType" id="buyType" type="hidden" value="${requestScope.buyType}"/>
    	<input name="categoryCode" id="categoryCode" type="hidden" value="${requestScope.categoryCode}"/>
    	<input name="brandCode" id="brandCode" type="hidden" value="${requestScope.brandCode}"/>
    	<input name="goodsCode" id="goodsCode" type="hidden" value="${requestScope.goodsCode}"/>
    	<input name="ids" id="id2" type="hidden" value=""/>
    	<input name="appealModel" id="appealModel" type="hidden" value=""/>
    	
      <table>
        <tr>
          <td class="labelTd">&nbsp&nbsp&nbsp&nbsp申诉人：</td>
			<td colspan="1">
				<input type="text" id="appealName" name="appealName" value="姓名拼音" onfocus="this.value=''" onblur="if(this.value==''){this.value='姓名拼音'}"><input id="searchAppealEmpName" type="submit" value="验证"/>
				<input type="hidden" id="appealAccount" name="appealAccount" >
				<input type="hidden" id="appealEmp" name="appealEmp" >
			</td>
        </tr>
        <tr>
          <td class="labelTd">&nbsp&nbsp&nbsp&nbsp申诉原因：</td>
			<td colspan="1">
				<textarea rows="3" cols="20" style="font-size:13px" id="appealReason" name="appealReason"></textarea>
			</td>
        </tr>
        <tr>
          <td class="labelTd">&nbsp&nbsp&nbsp&nbsp审批人：</td>
			<td colspan="1">
				<input type="text" id="examineName" name="examineName" value="姓名拼音" onfocus="this.value=''" onblur="if(this.value==''){this.value='姓名拼音'}"><input id="searchExamineEmpName" type="submit" value="验证"/>
				<input type="hidden" id="examineAccount" name="examineAccount" >
				<input type="hidden" id="examineEmp" name="examineEmp" >
			</td>
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