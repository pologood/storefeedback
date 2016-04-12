<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/commonJsp.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>缺断货反馈</title>
<style type="text/css">
.align-right{
	text-align: right;
}
.labelTd{
	font-size: 12px;
	text-align: right;
}
.label{
	font-size: 12px;
}
.lineheight tr{
	line-height: 20px;
}
</style>
<script type="text/javascript" src="<%=basePath%>modulev1/js/system/feedbackList.js"></script>
</head>
<body>
	<table id="feedbackList"></table>
	
	<div id="toolbar">
		<table>
			<tr>
				<td class="labelTd">大区：</td>
				<td colspan="1">
                    <input id="regions_combo" value="--请选择--"/>
				</td>
				<td class="labelTd">分部：</td>
				<td colspan="1">
                    <input id="division_combo"/>
				</td>
				<td class="labelTd">二级分部：</td>
				<td colspan="1">
                    <input id="secondDivision_combo"/>
				</td>
                <td class="labelTd">门店：</td>
                <td colspan="1">
                     <input id="store_combo"/>
                </td>
				<td colspan="1">
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'" id="searchBtn">查询</a>
				</td>
			</tr>
	        <tr>
	        	<td class="labelTd">发起人：</td>
				<td><input class="easyui-validatebox" id="sponsorEmployeeName"></td>
			</tr>
		</table>
	</div>
    <div id="showFeedBackRecord" class="easyui-dialog" title="反馈信息记录" style="width:800px;height:500px;"
              data-options="iconCls:'icon-save',resizable:true,modal:true,top:100" buttons="#addRoleAppDialogBtn" closed="true">
        <div style="margin: 10px;">
            <input type="hidden" id="feedbackRecordId"/>
            <table id="feedBackRecordList"></table>
            <div id="FeedBackRecordListToolBar">
              <table>
                  <tr>
                    <td colspan="3">
                    <a href="javascript:void(0);" class="easyui-linkbutton"
                      data-options="iconCls:'icon-add'" id="addRecordBtn">添加记录</a>
                    <a href="javascript:void(0);" class="easyui-linkbutton"
                      data-options="iconCls:'icon-edit'" id="editRecordBtn">修改记录</a>
                    <a href="javascript:void(0);" class="easyui-linkbutton"
                      data-options="iconCls:'icon-remove'" id="deleteRecordBtn">删除记录</a>
                  </td>
                  </tr>
                    <tr>
                      <td class="labelTd">反馈人：</td>
                      <td>
                        <input id="feedbackPersonEmployeeName" class="easyui-validatebox"/>
                      </td>
                      <td colspan="1">
                        <a href="javascript:void(0);" class="easyui-linkbutton"
                        data-options="iconCls:'icon-search'" id="feedBackRecordsearchBtn">查询</a>
                      </td>
                    </tr>
                  </table>
            </div>
        </div>
      </div>  
</body>
</html>