<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commonJsp.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<script type="text/javascript" src="<%=basePath%>modulev1/js/store/storelist.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<title>门店管理</title>
</head>
<body>
  <!-- 列表 start -->
  <table id="storelist"></table>
  <!-- 列表 end -->
  <!-- 顶部  start-->
  <div id="storeToolBar">
<!--     <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add'" id="sendMessage">发送消息</a>
 -->    <table>
      <tr>
        <td>门店名称：</td>
        <td><input id="store_name" class="easyui-validatebox" /></td>
        <td>门店编码：</td>
        <td><input id="store_code" class="easyui-validatebox" /></td>
        <td>公司编码：</td>
        <td><input id="company_code" class="easyui-validatebox" /></td>
        <td>门店地址：</td>
        <td><input id="store_address" class="easyui-validatebox" /></td>
        <td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'"
          id="searchStore">查询</a></td>
      </tr>
    </table>
  </div>
  <!-- 顶部  end-->
  
</body>
</html>