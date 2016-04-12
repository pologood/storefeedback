var arrIdMp = [];
;
(function() {
	$(function() {
		$("#storelist").datagrid({
			title : "门店列表",
			toolbar : "#storeToolBar",
			height : document.body.clientHeight * 0.98,
			url : basePath + 'n/store/findByPage',
			loadMsg : "数据加载中...",
			pagination : true,
			rownumbers : true,
			striped : true,
			pageSize : 20,
			/*
			 * queryParams : { _orderBy : 'order by notification_time desc' }
			 */
			frozenColumns : [ [ {// 冻结列
				checkbox : true
			}, {
				field : 'store_code',
				title : '门店编码',
				width : 150,
				sortable : true,
				styler : function(value, row, index) {
					return 'font-size:12px;';
				}
			}, {
				field : 'store_name',
				title : '门店名称',
				width : 150,
				sortable : true,
				styler : function(value, row, index) {
					return 'font-size:12px;';
				}
			}, {
				field : 'store_address',
				title : '门店地址',
				width : 100,
				sortable : true,
				styler : function(value, row, index) {
					return 'font-size:12px;';
				}
			}, {
				field : 'store_tel',
				title : '门店电话',
				width : 200,
				sortable : true,
				styler : function(value, row, index) {
					return 'font-size:12px;';
				}
			} ] ],
			columns : [ [ {
				field : 'company_code',
				title : '公司代码',
				width : 120,
				sortable : true,
				styler : function(value, row, index) {
					return 'font-size:12px;';
				}
			}, {
				field : 'sale_org',
				title : '销售组织',
				width : 200,
				sortable : true,
				styler : function(value, row, index) {
					return 'font-size:12px;';
				}
			}, {
				field : 'division_code',
				title : '一级分部编码',
				width : 200,
				sortable : true,
				styler : function(value, row, index) {
					return 'font-size:12px;';
				}
			}, {
				field : 'division_des',
				title : '一级分部描述',
				width : 200,
				sortable : true,
				styler : function(value, row, index) {
					return 'font-size:12px;';
				}
			}, {
				field : 'second_division_code',
				title : '二级分部编码',
				width : 200,
				sortable : true,
				styler : function(value, row, index) {
					return 'font-size:12px;';
				}
			}, {
				field : 'second_division_des',
				title : '二级分部描述',
				width : 200,
				sortable : true,
				styler : function(value, row, index) {
					return 'font-size:12px;';
				}
			}, {
				field : 'update_flag',
				title : '更新标识',
				width : 200,
				sortable : true,
				styler : function(value, row, index) {
					return 'font-size:12px;';
				}
			} ] ]

		});

		$('#cc').combogrid(
				{
					panelWidth : 550,
					value : '请输入应用系统账号',
					idField : 'appAcount',
					textField : 'appAcount',
					url : basePath + 'n/employee/findByPage',
					columns : [ [ {
						checkbox : true,
						width : $(this).width() * 0.1
					}, {
						field : 'employeeId',
						title : '员工编号',
						width : $(this).width() * 0.1,
						fitColumns : true,
						styler : function(value, row, index) {
							return 'font-size:12px;';
						}
					}, {
						field : 'serialNumber',
						title : '员工序列号',
						align : 'center',
						width : $(this).width() * 0.1,
						sortable : true,
						styler : function(value, row, index) {
							return 'font-size:12px;';
						}
					}, {
						field : 'employeeName',
						title : '员工姓名',
						align : 'center',
						width : $(this).width() * 0.1,
						sortable : true,
						styler : function(value, row, index) {
							return 'font-size:12px;';
						}
					}, {
						field : 'adAcount',
						title : 'AD域账号',
						align : 'center',
						width : $(this).width() * 0.1,
						sortable : true,
						styler : function(value, row, index) {
							return 'font-size:12px;';
						}
					}, {
						field : 'adAcountPwd',
						title : 'AD域密码',
						align : 'center',
						width : $(this).width() * 0.1,
						sortable : true,
						styler : function(value, row, index) {
							return 'font-size:12px;';
						}
					}, {
						field : 'appAcount',
						title : '应用系统账号',
						align : 'center',
						width : $(this).width() * 0.1,
						sortable : true,
						styler : function(value, row, index) {
							return 'font-size:12px;';
						}
					}, {
						field : 'appAcountPwd',
						title : '应用系统密码',
						align : 'center',
						width : $(this).width() * 0.1,
						sortable : true,
						styler : function(value, row, index) {
							return 'font-size:12px;';
						}
					}, {
						field : 'companyCode',
						title : '公司代码',
						align : 'center',
						width : 120,
						sortable : true,
						styler : function(value, row, index) {
							return 'font-size:12px;';
						}
					}, {
						field : 'mobile',
						title : '移动手机号码',
						align : 'center',
						width : $(this).width() * 0.1,
						sortable : true,
						styler : function(value, row, index) {
							return 'font-size:12px;';
						}
					}, {
						field : 'email',
						title : '电子邮件',
						align : 'center',
						width : $(this).width() * 0.1,
						sortable : true,
						styler : function(value, row, index) {
							return 'font-size:12px;';
						}
					}, {
						field : 'status',
						title : 'status',
						align : 'center',
						width : $(this).width() * 0.1,
						sortable : true,
						styler : function(value, row, index) {
							return 'font-size:12px;';
						},
						formatter : function(value) {
							if (1 == value) {
								return "正常";
							} else if (0 == value) {
								return "<font color='red'>冻结</font>";
							}
						}
					} ] ],
					loadMsg : "数据加载中...",
					pagination : true,
					rownumbers : true,
					striped : true,
					pageSize : 20,
					pageList : [ 10, 20, 30, 40, 50 ],
					onChange : function(newValue, oldValue) {
						$('#cc').combogrid({
							queryParams : {
								appAcount : newValue
							}
						});
					},
					onLoadSuccess : function() {
						$('#ccParent input.validatebox-text').focus();
					},
					onHidePanel : function() {
						var g = $('#cc').combogrid('grid'); // get datagrid
						// object
						var rData = g.datagrid('getSelected'); // get the
						// selected row
						if (rData) {
							if (arrIdMp.containElem(rData.employeeId)) {
								$.messager.alert('提示', '员工'
										+ rData.employeeName + '已经选择');
								$('#cc').combogrid('clear');
							} else {
								arrIdMp.push(rData.employeeId);
								var sp = $("#closeBtn").find("span").clone();
								sp.find("font").html(rData.employeeName);
								sp.data("employeeId", rData.employeeId);
								sp.find("#close").click(function() {
									$(this).parent().remove();
									arrIdMp.remove(rData.employeeId);
								});
								$("#sendEmployee").append(sp);
								$('#cc').combogrid('clear');
							}
						}
					}
				});

		$("#sendByEmployeeOrGroup").combobox({
			valueField : 'id',
			textField : 'text',
			data : [ {
				id : 'group',
				text : '选组'
			}, {
				id : 'employee',
				text : '选人'
			} ],
			onChange : function(newValue, oldValue) {
				if (newValue == "employee") {// 选人
					$("#sendByGroup").slideUp();
					$("#sendByEmployee").slideDown();
				} else if (newValue == "group") {// 选组
					$("#sendByEmployee").slideUp();
					$("#sendByGroup").slideDown();
				}
			}
		});

		$("#ccGroup").combobox({
			url : basePath + 'n/messageGroup/find',
			valueField : 'messageGroupId',
			textField : 'messageGroupName'
		});

		$("#sendMessage").click(function() {
			$('#sendMessageDlg').dialog('open').dialog('setTitle', '发送消息');
			$('#sendMessageFm').form('clear');
		});

		/* 查询 */
		$("#searchStore").click(function() {
			$('#storelist').datagrid({
				queryParams : {
					store_name : $("#store_name").val(),
					store_code : $("#store_code").val(),
					company_code : $("#company_code").val(),
					store_address : $("#store_address").val()
				}
			});
		});
		$("#sendBtn")
				.click(
						function() {
							var title = $("#sendTitle").val();
							var content = $("#sendContent").val();
							if (title.getStrLength() > 20) {
								$.messager.alert('提示', '标题最多不能超过10个字');
								return;
							} else if (content.getStrLength() > 100) {
								$.messager.alert('提示', '内容最多不能超过50个字');
								return;
							} else {
								$.messager.progress(); // display the progress
								// bar
								$('#sendMessageFm')
										.form(
												'submit',
												{
													url : basePath
															+ "n/message/insert",
													onSubmit : function(param) {
														var isValid = $(this)
																.form(
																		'validate');
														var selectType = $(
																"#sendByEmployeeOrGroup")
																.combobox(
																		"getValue");
														if (!selectType) {
															$.messager
																	.progress('close');
															$.messager.alert(
																	'提示',
																	'请选择发送方式');
															return false;
														} else if (selectType == "group") {// 选组
															param.messageGroupId = $(
																	"#ccGroup")
																	.combobox(
																			"getValue");
														} else if (selectType == "employee") {// 选人
															param.employeeId = arrIdMp
																	.join(",");
														}
														param.selectType = selectType;
														if (!isValid) {
															$.messager
																	.progress('close');
														}
														return isValid; // return
														// false
														// will
														// stop
														// the
														// form
														// submission
													},
													success : function() {
														$.messager
																.progress('close'); // hide
														// progress
														// bar
														// while
														// submit
														// successfully
														$('#sendMessageDlg')
																.dialog('close');
														$("#messagelist")
																.datagrid(
																		"reload");
														$.messager.show({
															title : '提示',
															msg : '消息发送成功',
															timeout : 3000,
															showType : 'slide'
														});
														arrIdMp = [];
													}
												});
							}
						});

	});
})();
