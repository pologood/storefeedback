<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commonJsp.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>品类职务对应关系</title>
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
<script type="text/javascript" src="<%=basePath%>modulev1/js/feedback/feedbackCategoryPushConfig.js"></script>
</head>
<body>

  <table id="feedbackPushConfigList"></table>

  <div id="tb">
    <table>
      <tbody>
        <tr>
          <td class="align-right"><font style="font-size: 12px;">品类编码：</font></td>
          <td><input id="categoryCode" class="easyui-validatebox" /></td>
          <td class="align-right"><font style="font-size: 12px;">品类名称：</font></td>
          <td><input id="categoryName" class="easyui-validatebox" /></td>
        </tr>
        <tr>
          <td colspan="4">
          <a href="javascript:void(0);"  class="easyui-linkbutton" data-options="iconCls:'icon-search'" id="searchBtn">查询</a> 
          <a href="javascript:void(0);"  class="easyui-linkbutton" data-options="iconCls:'icon-edit'" id="addCategoryConfigBtn">添加品类</a>
          <a  href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" id="editCategoryConfigBtn">编辑品类</a> 
          <a href="javascript:void(0);"  class="easyui-linkbutton" data-options="iconCls:'icon-edit'" id="deleteCategoryConfigBtn">删除品类</a> 
          <a href="javascript:void(0);"  class="easyui-linkbutton" data-options="iconCls:'icon-edit'" id="linkPositionBtn">关联职务</a> 
        </tr>
      </tbody>
    </table>
  </div>
    
  <!-- 添加品类开始 -->
  <div id="addGoodsCategoryConfigdlg" class="easyui-dialog" style="width: 400px; height: 300px; padding: 10px 20px"
    closed="true" buttons="#addGoodsCategoryConfigdlg-buttons" data-options="modal:true,top:100">
    <form id="addCategoryfm" method="post">
      <table>
        <tr>
          <td class="labelTd">品类编码：</td>
          <td><input id="categoryCode" name="categoryCode" class="easyui-validatebox" required="true"></td>
        </tr>
        <tr>
          <td class="labelTd">品类名称：</td>
          <td><input id="categoryName" name="categoryName" class="easyui-validatebox" required="true"></td>
        </tr>
      </table>
    </form>
  </div>
  <div id="addGoodsCategoryConfigdlg-buttons">
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" id="addCategorySaveBtn">保存</a> 
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#addGoodsCategoryConfigdlg').dialog('close')">取消</a>
  </div>
  <!-- 添加品类结束 -->
  
  <!-- 编辑品类开始 -->
  <div id="editGoodsCategoryConfigdlg" class="easyui-dialog" style="width: 400px; height: 300px; padding: 10px 20px"
    closed="true" buttons="#editGoodsCategoryConfigdlg-buttons" data-options="modal:true,top:100">
    <form id="editCategoryfm" method="post">
      <input type="hidden" id="id" name="id">
      <table>
        <tr>
          <td class="labelTd">品类编码：</td>
          <td><input id="categoryCode" name="categoryCode" class="easyui-validatebox" required="true"></td>
        </tr>
        <tr>
          <td class="labelTd">品类名称：</td>
          <td><input id="categoryName" name="categoryName" class="easyui-validatebox" required="true"></td>
        </tr>
      </table>
    </form>
  </div>

  <div id="editGoodsCategoryConfigdlg-buttons">
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" id="editCategorySaveBtn">保存</a> 
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#editGoodsCategoryConfigdlg').dialog('close')">取消</a>
  </div>
  <!-- 编辑品类结束 -->
  
  
  <!-- 关联职务 开始 -->
   <div id="linkPositionDialog" class="easyui-dialog" style="padding: 10px 10px"
    closed="true" buttons="#linkPositionDialog-buttons" data-options="modal:true">
    <input type="hidden" id="goodsCategoryId" />
    <input type="hidden" id="goodsCategoryName" />
    <input type="hidden" id="goodsOrgNumber" />
    <input type="hidden" id="goodsOrgName" />
    <div>
    <div style="width:275px;float: left;"><table id="orgList"></table></div>
    <div style="width:1025px;float: left;"><table id="positionList"></table></div>
    </div>
    <div id="positionListToolBar" >
      <table>
        <tr>
          <td class="labelTd">职务编码：</td>
          <td><input id="addPositionCode" class="easyui-validatebox" /></td>
          <td class="labelTd">职务名称：</td>
          <td><input id="addPositionName" class="easyui-validatebox" /></td>
        </tr>
        <tr>
          <td colspan="4">  			  
            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'" id="addPositionSearchBtn">查询</a> 
            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add'" id="addPositionBtn">添加职务</a> 
            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" id="editPositionBtn">修改职务</a> 
            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" id="deletePositionBtn">删除职务</a>
          </td>
        </tr>
      </table>
    </div>
  </div>
  <div id="linkPositionDialog-buttons">
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" onclick="javascript:$('#linkPositionDialog').dialog('close')">确定</a>
  </div>
  <!-- 关联职务 结束 -->

  <!-- 添加职务开始 -->
  <div id="addPositionDialog" class="easyui-dialog" style="width: 500px; height: 400px; padding: 10px 20px"
    closed="true" buttons="#addPositiondlg-buttons" data-options="modal:true,top:100">
    <form id="addPositionfm" method="post">
      <input type="hidden" id="CategoryCode" name="CategoryCode">
      <input type="hidden" id="CategoryName" name="CategoryName">
      <table>
      
       <tr>
          <td class="labelTd">缺断货单位编码：</td>
          <td><input id="srcOrgNumber" name="srcOrgNumber" class="easyui-validatebox" required="true"></td>
        </tr>
        <tr>
          <td class="labelTd">缺断货单位名称：</td>
          <td><input id="srcOrgName" name="srcOrgName" class="easyui-validatebox" required="true"></td>
        </tr>
        <tr>
          <td class="labelTd">缺断货单位级别：</td>
          <td>
          <input id="srcOrgType" name="srcOrgType" />
          </td>
        </tr>
        
        <tr>
          <td class="labelTd">推送单位编码：</td>
          <td><input id="orgNumber" name="orgNumber" class="easyui-validatebox" required="true"></td>
        </tr>
        <tr>
          <td class="labelTd">推送货单位名称：</td>
          <td><input id="orgName" name="orgName" class="easyui-validatebox" required="true"></td>
        </tr>
        <tr>
          <td class="labelTd">推送货单位级别：</td>
          <td>
          <input id="isGMZB" name="isGMZB" />
          </td>
        </tr>
        <tr>
          <td class="labelTd">职务编码：</td>
          <td><input id="positionCode" name="positionCode" class="easyui-validatebox" required="true"></td>
        </tr>
        <tr>
          <td class="labelTd">职务名称：</td>
          <td><input id="positionDesc" name="positionDesc" class="easyui-validatebox" required="true"></td>
        </tr>
        <tr>
          <td class="labelTd">职务角色：</td>
          <td>
            <input id="roleId" name="roleId"/>
        </tr>
      </table>
    </form>
  </div>

  <div id="addPositiondlg-buttons">
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" id="addPositionSaveBtn">保存</a> 
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#addPositionDialog').dialog('close')">取消</a>
  </div>
  <!-- 添加职务结束 -->
  
  <!-- 修改职务开始 -->
  <div id="editPositionDialog" class="easyui-dialog" style="width: 500px; height: 400px; padding: 10px 20px"
    closed="true" buttons="#editPositiondlg-buttons" data-options="modal:true,top:100">
    <form id="editPositionfm" method="post">
      <input type="hidden" id="id" name="id">
      <input type="hidden" id="CategoryCode" name="CategoryCode" disabled="true" readOnly="true">
      <input type="hidden" id="CategoryName" name="CategoryName" disabled="true" readOnly="true">
      <table>
         <tr>
          <td class="labelTd">缺断货单位编码：</td>
          <td><input id="srcOrgNumber" name="srcOrgNumber" class="easyui-validatebox" required="true"></td>
        </tr>
        <tr>
          <td class="labelTd">缺断货单位名称：</td>
          <td><input id="srcOrgName" name="srcOrgName" class="easyui-validatebox" required="true"></td>
        </tr>
        <tr>
          <td class="labelTd">缺断货单位级别：</td>
          <td>
          <input id="srcOrgType" name="srcOrgType" />
          </td>
        </tr>
        
        <tr>
          <td class="labelTd">推送单位编码：</td>
          <td><input id="orgNumber" name="orgNumber" class="easyui-validatebox" required="true"></td>
        </tr>
        <tr>
          <td class="labelTd">推送货单位名称：</td>
          <td><input id="orgName" name="orgName" class="easyui-validatebox" required="true"></td>
        </tr>
        <tr>
          <td class="labelTd">推送货单位级别：</td>
          <td>
          <input id="isGMZB" name="isGMZB" />
          </td>
        </tr>
        
        <tr>
          <td class="labelTd">职务编码：</td>
          <td><input id="positionCode" name="positionCode" class="easyui-validatebox" required="true"></td>
        </tr>
        <tr>
          <td class="labelTd">职务名称：</td>
          <td><input id="positionDesc" name="positionDesc" class="easyui-validatebox" required="true"></td>
        </tr>
        <tr>
          <td class="labelTd">职务角色：</td>
          <td>
            <input id="roleId" name="roleId"/>
        </tr>
      </table>
    </form>
  </div>

  <div id="editPositiondlg-buttons">
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" id="editPositionSaveBtn">保存</a> 
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#editPositionDialog').dialog('close')">取消</a>
  </div>
  <!--  修改职务结束 -->
</body>
</html>