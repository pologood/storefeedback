<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commonJsp.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<script type="text/javascript" src="<%=basePath%>modulev1/js/message/messagelist.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<title>消息管理</title>
</head>
<body>
  <!-- 列表 start -->
  <table id="messagelist"></table>
  <!-- 列表 end -->
  <!-- 顶部  start-->
  <div id="messageToolBar">
    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add'" id="sendMessage">发送消息</a>
    <table>
      <tr>
        <td>标题：</td>
        <td><input id="searchTitle" class="easyui-validatebox" /></td>
        <td>内容：</td>
        <td><input id="searchContent" class="easyui-validatebox" /></td>
        <td>通知人员工编号：</td>
        <td><input id="employeeId" class="easyui-validatebox" /></td>
        <td>通知人员工姓名：</td>
        <td><input id="employeeName" class="easyui-validatebox" /></td>
        <td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'"
          id="searchMessage">查询</a></td>
      </tr>
    </table>
  </div>
  <!-- 顶部  end-->
  <!-- 发送消息对话框  start-->
  <div id="sendMessageDlg" class="easyui-dialog" style="width: 600px; height: 500px; padding: 10px 20px" closed="true"
    buttons="#sendBtns" data-options="modal:true,top:100,resizable:true">
    <form id="sendMessageFm" method="post">
      <table>
        <tr>
          <td class="labelTd">标题：</td>
          <td><input id="sendTitle" name="title" class="easyui-validatebox" required="true" style="width: 400px;"></td>
        </tr>
        <tr>
          <td class="labelTd">内容：</td>
          <td><textarea id="sendContent" rows="5" cols="50" style="width: 400px;" name="content"
              class="easyui-validatebox" required="true"></textarea></td>
        </tr>
        <tr>
          <td class="labelTd">发送方式：</td>
          <td><input id="sendByEmployeeOrGroup" style="width: 396px" /></td>
        </tr>
        <tr id="sendByEmployee" style="display: none;">
          <td class="labelTd">发送目标：</td>
          <td>
            <div id="ccParent">
              <select id="cc" class="easyui-combogrid" style="width: 396px">选择员工
              </select>
            </div>
            <div class="easyui-tooltip" data-options="position:'top'" title="不选择人员默认的发送范围为您所管理区域的所有人员" id="sendEmployee"
              style="margin: 20px 0; border: 1px solid black; width: 396px; height: 100px; overflow: scroll;"></div>
            <p id="closeBtn" style="display: none;">
              <!-- style="display: none;" -->
              <span style="margin-left: 2px;"><font style="background-color: #e0e5ee; font-size: 12px;">张三</font><img
                id="close" style="vertical-align: text-bottom;" alt="删除" src="<%=basePath%>resources/images/del.jpg"></span>
            </p>
          </td>
        </tr>
        <tr id="sendByGroup">
          <td class="labelTd">组别选择：</td>
          <td><input id="ccGroup" style="width: 396px" /></td>
        </tr>
      </table>
    </form>
    <div style="height: 20px;"></div>
  </div>

  <div id="sendBtns">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" id="sendBtn">发送</a> <a
      href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
      onclick="javascript:$('#sendMessageDlg').dialog('close')">取消</a>
  </div>
  <!-- 发送消息对话框  end-->
</body>
</html>