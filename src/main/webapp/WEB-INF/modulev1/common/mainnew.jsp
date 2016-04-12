<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commonJsp.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>门店缺断货管理系统</title>
<style type="text/css">
.cWhite {
	color: white;
}
</style>
<script type="text/javascript" src="<%=basePath%>modulev1/js/main.js"></script>
<script type="text/javascript">
    $(function() {
    	

             // ======品类列表=============================================
             $("#tabs").datagrid({
                 title : "品类列表",
                 toolbar : "#tb",
                 height : document.body.clientHeight * 0.98,
                 url : basePath + 'n/appToWebFeedback/getByEmp',
                 loadMsg : "数据加载中...",
                 pagination : true,
                 rownumbers : true,
                 striped : true,
                 singleSelect : true,
                 pageSize : 20,
                 queryParams : {
                     "_orderBy" : "order by category_code asc"
                 },
                 columns : [ [ {
                     checkbox : true
                 }, {
                     field : 'id',
                     title : 'ID',
                     width : 80,
                     hidden : true,
                     fitColumns : true,
                     styler : function(value, row, index) {
                         return 'font-size:12px;';
                     }
                 }, {
                     field : 'categoryCode',
                     title : '品类编码',
                     align : 'center',
                     width : 100,
                     styler : function(value, row, index) {
                         return 'font-size:12px;';
                     }
                 }, {
                     field : 'categoryName',
                     title : '品类名称',
                     align : 'center',
                     width : 100,
                     styler : function(value, row, index) {
                         return 'font-size:12px;';
                     }
                 }, {
                     field : 'createTime',
                     title : '创建时间',
                     align : 'center',
                     width : 300,
                     styler : function(value, row, index) {
                         return 'font-size:12px;';
                     },
                     formatter : function(value) {
                         return new Date(value).format("yyyy-MM-dd HH:mm:ss");
                     }
                 } ] ]
             });
    });
</script>
</head>
<body class="easyui-layout">
	<%-- 	<div data-options="region:'north',split:false,border:false"
				style="height: 65px; background-color: rgb(102, 102, 102);" class="header-image">
				<div id="header-inner">
						<table cellpadding="0" cellspacing="0" style="width: 100%;">
								<tr>
										<td rowspan="2" style="width: 20px;"></td>
										<td style="height: 60px;" rowspan="2">
												<div style="color: #fff; font-size: 22px; font-weight: bold;">
														<a href="javascript:void();"
																style="color: #fff; font-size: 22px; font-weight: bold; text-decoration: none">门店缺断货管理系统 V2.0</a>
												</div>
										</td>
										<td style="padding-right: 5px; text-align: right; vertical-align: bottom;">
												<div id="topmenu">
														<span> <input type="hidden" id="currentUserId" value="${em.id }" /> <input type="hidden"
																id="currentEmployeeId" value="${em.employeeId }" /> <input type="hidden" id="currentCompanyId"
																value="${em.companyId }" />
														</span> <span style="font-size: 12px;"> <font color="white">管理员</font> <a href="javascript:void(0);"
																class="cWhite" id="modifyPsd">修改密码</a> <a href="javascript:void(0);" class="cWhite" id="logout">退出</a>
														</span>
												</div>
										</td>
								</tr>
						</table>
				</div>
		</div> --%>
		<div data-options="region:'south',split:false,plain:true" style="height: 30px;">
				<div align="center" class="style6" style="height: 100%; line-height: 25px;">Copyright 版权所有:国美电器有限公司 2014</div>
		</div>
		<div data-options="region:'center'">
				<div id="tabs" class="easyui-tabs" data-options="fit:true,plain:true,border:false">
						<div title="缺断货查询" style="padding: 20px;">欢迎登录门店缺断货管理系统</div>
				</div>
		</div>

		<div id="mm" class="easyui-menu" style="width: 150px;">
				<div id="tabupdate">刷新</div>
				<div class="menu-sep"></div>
				<div id="close">关闭</div>
				<div id="closeall">全部关闭</div>
				<div id="closeother">除此之外全部关闭</div>
				<div class="menu-sep"></div>
				<div id="closeright">当前页右侧全部关闭</div>
				<div id="closeleft">当前页左侧全部关闭</div>
				<div class="menu-sep"></div>
				<div id="exit">退出</div>
		</div>

		<!-- 修改密码 开始 -->
		<div id="modifyPwdDialog" class="easyui-dialog" title="修改密码" style="width: 400px; height: 300px;"
				data-options="iconCls:'icon-save',resizable:true,modal:true,top:100" buttons="#modifyPwdDialogBtn" closed="true">
				<div style="margin: 10px; text-align: center;">
						<form action="" method="post" id="modifyPwdFm">
								<table style="margin: 0 auto;">
										<tr>
												<td class="labelTd">原密码：</td>
												<td><input type="password" class="easyui-validatebox" data-options="required:true" id="oldPwd">
												</td>
										</tr>
										<tr>
												<td class="labelTd">新密码：</td>
												<td><input type="password" id="newPwd" class="easyui-validatebox" data-options="required:true" /></td>
										</tr>
										<tr>
												<td class="labelTd">确认密码：</td>
												<td><input type="password" id="newPwdCF" class="easyui-validatebox" data-options="required:true" /></td>
										</tr>
								</table>
						</form>
				</div>
		</div>
		<div id="modifyPwdDialogBtn">
				<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" id="modifyPwdSaveBtn">保存</a>
		</div>
		<!-- 修改密码 结束 -->

</body>
</html>