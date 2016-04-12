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
 tr{
 display:block; /*将tr设置为块体元素*/
  margin:4px 0; /*设置tr间距为2px*/
 }
</style>
<script type="text/javascript" src="<%=basePath%>modulev1/js/feedback/feedbackCategoryCheckConfig.js"></script>
</head>
<body>

  <table id="feedbackCheckConfigList"></table>

  <div id="tb">
		<table>
			<tbody>
				<!-- <tr>
					<td class="labelTd">&nbsp&nbsp&nbsp&nbsp大区：</td>
					<td colspan="1"><input id="regions_combo" value="--请选择--"
						class="easyui-validatebox"></td>
					<td class="labelTd">&nbsp&nbsp&nbsp&nbsp分部：</td>
					<td colspan="1"><input id="division_combo" name="orgNumber"
						class="easyui-validatebox"> <input type="hidden"
						id="orgName" name=orgName class="combo-value"></td>
				</tr>
				<tr>
					<td class="labelTd">&nbsp&nbsp&nbsp&nbsp品类：</td>
					<td colspan="1"><input id="category_combo" name="categoryCode"
						value="--请选择--"> <input type="hidden" id="categoryName"
						name="categoryName" class="combo-value"></td>
					<td class="labelTd">&nbsp&nbsp&nbsp&nbsp品牌：</td>
					<td colspan="1"><input id="brandCode_combo" name="brandCode"
						class="easyui-validatebox"> <input type="hidden"
						id="brandName" name="brandName" class="combo-value"></td>
				</tr>
				<tr> -->
				<tr>
					<td class="labelTd">OA账号：</td>
					<td colspan="1"><input id="oaNumber" class="easyui-validatebox" name="oaNumber" required="true" missingMessage="关联员工编码"></td>
				</tr>
				<tr>
					<td colspan="4"><a href="javascript:void(0);"
						class="easyui-linkbutton" data-options="iconCls:'icon-search'"
						id="searchBtn">查询</a> <a href="javascript:void(0);"
						class="easyui-linkbutton" data-options="iconCls:'icon-edit'"
						id="addCategoryConfigBtn">添加</a> <a href="javascript:void(0);"
						class="easyui-linkbutton" data-options="iconCls:'icon-edit'"
						id="editCategoryConfigBtn">修改</a> <a href="javascript:void(0);"
						class="easyui-linkbutton" data-options="iconCls:'icon-edit'"
						id="deleteCategoryConfigBtn">删除</a>
				</tr>
			</tbody>
		</table>
	</div>
    
  <!-- 添加品类开始 -->
  <div id="addGoodsCategoryConfigdlg" class="easyui-dialog" style="width: 350px; height: 400px; padding: 10px 20px"
    closed="true" buttons="#addGoodsCategoryConfigdlg-buttons" data-options="modal:true,top:100">
    <form id="addCategoryfrom" method="post">
			<table>
				<tr>
					<td class="labelTd">&nbsp&nbsp&nbsp&nbsp大区：</td>
					<td colspan="1"><input id="regions_combo" name="regionName"  value="--请选择--" class="easyui-validatebox" ></td>

				</tr>
				<tr>
					<td class="labelTd">&nbsp&nbsp&nbsp&nbsp分部：</td>
					<td colspan="1">
						<input id="division_combo" name="orgNumber" class="easyui-validatebox" >
						<input type="hidden" id="orgName" name="orgName" class="combo-value" >
					</td>
				</tr>
				<tr>
					<td class="labelTd">&nbsp&nbsp&nbsp&nbsp品类：</td>
					<td colspan="1"><input id="category_combo" name="categoryCode" value="--请选择--" >
					<input type="hidden" id="categoryName" name="categoryName" class="combo-value" >
					</td>
				</tr>
				<tr>
					<td class="labelTd">&nbsp&nbsp&nbsp&nbsp品牌：</td>
					<td colspan="1"><input id="brandCode_combo" name="brandCode" class="easyui-validatebox" >
					<input type="hidden" id="brandName" name="brandName" class="combo-value" >
					</td>
					<!-- <td colspan="1"><input id="brandCode" name="brandCode" class="easyui-validatebox" required="true" missingMessage="品牌编码必须填写" ></td> -->
				</tr>
				 <tr>
		          <td class="labelTd">是否领导：</td>
		          <td>
		            <input id="roleId" name="roleId"/>
		        </tr>
				<tr>
					<td class="labelTd">员工编码：</td>
					<td colspan="1"><input id="empNumber" name="empNumber" class="easyui-validatebox" required="true" missingMessage="员工域账号必须填写" ><input id="searchEmpByNum1" type="submit" value="关联"></td>
				</tr>
			</table>
		</form>
  </div>
  <div id="addGoodsCategoryConfigdlg-buttons">
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" id="addSaveBtn">保存</a> 
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#addGoodsCategoryConfigdlg').dialog('close')">取消</a>
  </div>
  <!-- 添加品类结束 -->
  
  <!-- 编辑品类开始 -->
  <div id="editGoodsCategoryConfigdlg" class="easyui-dialog" style="width: 350px; height: 400px; padding: 10px 20px"
    closed="true" buttons="#editGoodsCategoryConfigdlg-buttons" data-options="modal:true,top:100">
    <form id="editCategoryfm" method="post">
      <input type="hidden" id="id" name="id">
			<table>
				<tr>
					<td class="labelTd">&nbsp&nbsp&nbsp&nbsp大区：</td>
					<td colspan="1"><input id="regions_combo1" name="regionName" value="--请选择--" class="easyui-validatebox" ></td>

				</tr>
				<tr>
					<td class="labelTd">&nbsp&nbsp&nbsp&nbsp分部：</td>
					<td colspan="1">
						<input id="division_combo1" name="orgNumber" class="easyui-validatebox" >
						<input type="hidden" id="orgName1" name="orgName" class="combo-value" >
					</td>
				</tr>
				<tr>
					<td class="labelTd">&nbsp&nbsp&nbsp&nbsp品类：</td>
					<td colspan="1"><input id="category_combo1" name="categoryCode" value="--请选择--">
					<input type="hidden" id="categoryName1" name="categoryName" class="combo-value" >
					</td>
				</tr>
				<tr>
					<td class="labelTd">&nbsp&nbsp&nbsp&nbsp品牌：</td>
					<td colspan="1"><input id="brandCode_combo1" name="brandCode" class="easyui-validatebox" >
					<input type="hidden" id="brandName1" name="brandName" class="combo-value" >
					</td>
					<!-- <td colspan="1"><input id="brandCode" name="brandCode" class="easyui-validatebox" required="true" missingMessage="品牌编码必须填写" ></td> -->
				</tr>
				 <tr>
		          <td class="labelTd">是否领导：</td>
		          <td>
		            <input id="roleId1" name="roleId"/>
		        </tr>
				<tr>
					<td class="labelTd">员工编码：</td>
					<td colspan="1"><input id="empNumber1" name="empNumber" class="easyui-validatebox" required="true" missingMessage="员工域账号必须填写" ><input id="searchEmpByNum2" type="submit" value="关联"></td>
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
    <div style="width:300px;float: left;"><table id="orgList"></table></div>
    <div style="width:1200px;float: left;"><table id="positionList"></table></div>
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