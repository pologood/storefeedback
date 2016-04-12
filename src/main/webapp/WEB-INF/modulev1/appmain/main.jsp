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
        $("#sysTree").tree(
                {
                    data : [ {
                        id : "sys01",
                        text : "我的缺货反馈",//--品类管理
                        url : basePath + "n/navigation/goToGoodsCategoryPage"
                    }, {
                        id : "sys02",
                        text : "我的销售任务",//品牌管理
                        url : basePath + "n/navigation/goToGoodsBrandPage"
                    }, {
                        id : "sys03",
                        text : "新品通知",//商品管理
                        url : basePath + "n/navigation/goToGoodsPage"
                    }, {
                        id : "sys04",
                        text : "缺货通知",//缺断货反馈
                        url : basePath + "n/aspFeedBack/goToCategoryPage"
                    }, {
                        id : "sys05",
                        text : "缺断货考核通知",//缺断货考核通知
                        url : basePath + "n/checkSapFeedback/goToCheckWeekPage"
/*                         url : basePath + "n/checkSapFeedback/goToCategoryPage" */
                    }                    
                    ],
                    onClick : function(node) {
                        if (!$("#tabs").tabs("exists", node.text)) {
                            $('#tabs').tabs(
                                    'add',
                                    {
                                        title : node.text,
                                        content : "<iframe name='" + node.id + "' hideTitle='" + node.text
                                                + "'  scrolling='no' frameborder='0' src='" + node.url
                                                + "' style='width: 100%; height: 100%; overflow: hidden;'></iframe>",
                                        selected : true
                                    });
                            tabClose();
                        } else {
                            $("#tabs").tabs("select", node.text);
                        }

                    }
                });
/*         $("#cacheTree").tree(
                {
                    data : [ {
                        id : "app01",
                        text : "品类管理",
                        url : basePath + "helloworld.jsp"
                    }, {
                        id : "app02",
                        text : "商品管理",
                        url : basePath + "helloworld.jsp"
                    } ],
                    onClick : function(node) {
                        if (!$("#tabs").tabs("exists", node.text)) {
                            $('#tabs').tabs(
                                    'add',
                                    {
                                        title : node.text,
                                        content : "<iframe name='" + node.id + "' hideTitle='" + node.text
                                                + "'  scrolling='no' frameborder='0' src='" + node.url
                                                + "' style='width: 100%; height: 100%; overflow: hidden;'></iframe>",
                                        selected : true
                                    });
                            tabClose();
                        } else {
                            $("#tabs").tabs("select", node.text);
                        }
                    }
                }); */
        $("#jobTree").tree(
                {
                    data : [ {
                        id : "device01",
                        text : "品类管理",
                        url : basePath + "helloworld.jsp"
                    }, {
                        id : "device02",
                        text : "商品管理",
                        url : basePath + "helloworld.jsp"
                    } ],
                    onClick : function(node) {
                        if (!$("#tabs").tabs("exists", node.text)) {
                            $('#tabs').tabs(
                                    'add',
                                    {
                                        title : node.text,
                                        content : "<iframe name='" + node.id + "' hideTitle='" + node.text
                                                + "'  scrolling='no' frameborder='0' src='" + node.url
                                                + "' style='width: 100%; height: 100%; overflow: hidden;'></iframe>",
                                        selected : true
                                    });
                            tabClose();
                        } else {
                            $("#tabs").tabs("select", node.text);
                        }
                    }
                });
      /*   $("#messageTree").tree(
                {
                    data : [ {
                        id : "message01",
                        text : "品类管理",
                        url : basePath + "helloworld.jsp"
                    }, {
                        id : "message02",
                        text : "商品管理",
                        url : basePath + "helloworld.jsp"
                    } ],
                    onClick : function(node) {
                        if (!$("#tabs").tabs("exists", node.text)) {
                            $('#tabs').tabs(
                                    'add',
                                    {
                                        title : node.text,
                                        content : "<iframe name='" + node.id + "' hideTitle='" + node.text
                                                + "'  scrolling='no' frameborder='0' src='" + node.url
                                                + "' style='width: 100%; height: 100%; overflow: hidden;'></iframe>",
                                        selected : true
                                    });
                            tabClose();
                        } else {
                            $("#tabs").tabs("select", node.text);
                        }
                    }
                }); */
    });
</script>
</head>
<body class="easyui-layout">
		<div data-options="region:'north',split:false,border:false"
				style="height: 65px; background-color: rgb(102, 102, 102);" class="header-image">
				<div id="header-inner">
						<table cellpadding="0" cellspacing="0" style="width: 100%;">
								<tr>
										<td rowspan="2" style="width: 20px;"></td>
										<td style="height: 60px;" rowspan="2">
												<div style="color: #fff; font-size: 22px; font-weight: bold;">
														<a href="javascript:void();"
																style="color: #fff; font-size: 22px; font-weight: bold; text-decoration: none">门店缺断货管理系统 V1.0</a>
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
		</div>
		<div data-options="region:'south',split:false,plain:true" style="height: 30px;">
				<div align="center" class="style6" style="height: 100%; line-height: 25px;">Copyright 版权所有:国美电器有限公司 2014</div>
		</div>
		<div data-options="region:'west',title:'导航菜单',split:false" style="width: 250px;">
				<div id="aa" class="easyui-accordion" style="width: 250px; height: 100%"
						data-options="plain:true,fit:true,border:false">

						<div title="系统管理" data-options="selected:true" style="overflow: auto;">
								<ul id="sysTree"></ul>
						</div>
<!-- 						<div title="缓存管理">
								<ul id="cacheTree"></ul>
						</div> -->
						<div title="定时任务管理">
								<ul id="jobTree"></ul>
						</div>
<!-- 						<div title="消息管理">
								<ul id="messageTree"></ul>
						</div> -->

				</div>
		</div>
		<div data-options="region:'center'">
				<div id="tabs" class="easyui-tabs" data-options="fit:true,plain:true,border:false">
						<div title="欢迎页" style="padding: 20px;">欢迎登录门店缺断货管理系统</div>
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