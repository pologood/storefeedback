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
<script type="text/javascript" src="<%=basePath%>modulev1/js/feedback/resultNoReasonList.js"></script>
</head>
<body>

  <table id="resultNoReasonList"></table>

  <div id="tb">
    <table>
      <tbody>
        <tr>
          <td colspan="4">
          <a href="javascript:void(0);"  class="easyui-linkbutton" data-options="iconCls:'icon-edit'" id="addResultNoReasonBtn">添加原因</a>
          <a  href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" id="editResultNoReasonBtn">编辑原因</a> 
          <a href="javascript:void(0);"  class="easyui-linkbutton" data-options="iconCls:'icon-edit'" id="deleteResultNoReasonBtn">删除原因</a> 
        </tr>
      </tbody>
    </table>
  </div>
    
  <!-- 添加品类开始 -->
  <div id="addResultNoReasondlg" class="easyui-dialog" style="width: 400px; height: 300px; padding: 10px 20px"
    closed="true" buttons="#addResultNoReasondlg-buttons" data-options="modal:true,top:100">
    <form id="addResultNoReasonfm" method="post">
      <table>
<!--         <tr>
          <td class="labelTd">原因编码：</td>
          <td><input id="resultNoCode" name="resultNoCode" class="easyui-validatebox" required="true"></td>
        </tr> -->
        <tr>
          <td class="labelTd">原因描述：</td>
          <td><input id="resultNoName" name="resultNoName" class="easyui-validatebox" required="true"></td>
        </tr>
      </table>
    </form>
  </div>
  <div id="addResultNoReasondlg-buttons">
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" id="addResultNoReasonSaveBtn">保存</a> 
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#addResultNoReasondlg').dialog('close')">取消</a>
  </div>
  <!-- 添加品类结束 -->
  
  <!-- 编辑品类开始 -->
  <div id="editResultNoReasondlg" class="easyui-dialog" style="width: 400px; height: 300px; padding: 10px 20px"
    closed="true" buttons="#editResultNoReasondlg-buttons" data-options="modal:true,top:100">
    <form id="editResultNoReasonfm" method="post">
    <input name="resultNoCode" id="resultNoCode" type="hidden" value=""/>
    <input name="isUsing" id="isUsing" type="hidden" value=""/>
    <input name="sortOrder" id="sortOrder" type="hidden" value=""/>
      <table>
        <tr>
          <td class="labelTd">原因描述：</td>
          <td><input id="resultNoName" name="resultNoName" class="easyui-validatebox" required="true"></td>
        </tr>
      </table>
    </form>
  </div>

  <div id="editResultNoReasondlg-buttons">
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" id="editResultNoReasonSaveBtn">保存</a> 
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#editResultNoReasondlg').dialog('close')">取消</a>
  </div>
  <!-- 编辑品类结束 -->
  
</body>
</html>