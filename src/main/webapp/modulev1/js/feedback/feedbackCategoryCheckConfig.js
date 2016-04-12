;
(function() {
    $(function() {
    	
    	//关联员工信息
    	$("#searchEmpByNum1").click(function(){
    		
    		$.ajax({
    			type: "GET",
    			url: basePath + 'n/feedbackCheck/getEmpByAcccount',
    			data: {"empNumber" : $("#empNumber").val()},
    			dataType: "json",
    			success: function(data){
    				
    				$("#empNumber").val(data.result.empNum);
    			}
    		});
    		return false;
    	});
    	
    	$("#searchEmpByNum2").click(function(){
    		$.ajax({
    			type: "GET",
    			url: basePath + 'n/feedbackCheck/getEmpByAcccount',
    			data: {"empNumber" : $("#empNumber1").val()},
    			dataType: "json",
    			success: function(data){
    				$("#empNumber1").val(data.result.empNum);
    			}
    		});
    		return false;
    	});
    	
        function loadData() {

            // ======品类列表=============================================
            $("#feedbackCheckConfigList").datagrid({
                title : "品类列表",
                toolbar : "#tb",
                height : document.body.clientHeight * 0.98,
                url : basePath + 'n/feedbackCheck/findConfigByPage',
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
                    field : 'regionName',
                    title : '大区',
                    width : 80,
                    hidden : true,
                    fitColumns : true,
                    styler : function(value, row, index) {
                        return 'font-size:12px;';
                    }
                }, {
                    field : 'id',
                    title : 'ID',
                    width : 80,
                    hidden : true,
                    fitColumns : true,
                    styler : function(value, row, index) {
                        return 'font-size:12px;';
                    }
                },  {
                    field : 'orgName',
                    title : '部门名称',
                    align : 'center',
                    width : 100,
                    styler : function(value, row, index) {
                        return 'font-size:12px;';
                    }
                }, {
                    field : 'orgNumber',
                    title : '部门编码',
                    align : 'center',
                    width : 100,
                    styler : function(value, row, index) {
                        return 'font-size:12px;';
                    }
                }, {
                    field : 'empNumber',
                    title : '员工编码',
                    align : 'center',
                    width : 100,
                    styler : function(value, row, index) {
                        return 'font-size:12px;';
                    }
                },{
                    field : 'categoryCode',
                    title : '品类编码',
                    align : 'center',
                    width : 100,
                    styler : function(value, row, index) {
                        return 'font-size:12px;';
                    }
                }
                , {
                    field : 'categoryName',
                    title : '品类名称',
                    align : 'center',
                    width : 100,
                    styler : function(value, row, index) {
                        return 'font-size:12px;';
                    }
                }, {
                    field : 'brandName',
                    title : '品牌名称',
                    align : 'center',
                    width : 100,
                    styler : function(value, row, index) {
                        return 'font-size:12px;';
                    }
                }, {
                    field : 'brandCode',
                    title : '品牌编码',
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
                }, {
                    field : 'roleId',
                    title : '是否领导',
                    align : 'center',
                    width : 300,
                    hidden : true,
                    styler : function(value, row, index) {
                        return 'font-size:12px;';
                    }
                } ] ]
            });
           /* // ====品类职务列表=======================================================
            $("#positionList").datagrid({
                title : "职务列表",
                toolbar : "#positionListToolBar",
                height : document.body.clientHeight,
                loadMsg : "数据加载中...",
                pagination : true,
                rownumbers : true,
                striped : true,
                pageSize : 20,
                columns : [ [ {
                    checkbox : true
                }, {
                    field : 'id',
                    title : 'ID',
                    width : 10,
                    hidden : true,
                    styler : function(value, row, index) {
                        return 'font-size:12px;';
                    }
                }, {
                    field : 'srcOrgType',
                    title : '缺断货单位级别',
                    align : 'center',
                    width : 80,
                    styler : function(value, row, index) {
                        return 'font-size:12px;';
                    },
                    formatter : function(value) {
                        if (value == 1) {
                            return "一级分部";
                        } else if (value == 2) {
                            return "二级分部";
                        }else{
                            return value;
                        }
                    }
                }, {
                    field : 'srcOrgNumber',
                    title : '缺断货单位编码',
                    align : 'center',
                    width : 80,
                    fitColumns : true,
                    styler : function(value, row, index) {
                        return 'font-size:12px;';
                    }
                }, {
                    field : 'srcOrgName',
                    title : '缺断货单位名称',
                    align : 'center',
                    width : 150,
                    fitColumns : true,
                    styler : function(value, row, index) {
                        return 'font-size:12px;';
                    }
                }, {
                    field : 'isGMZB',
                    title : '推送单位级别',
                    align : 'center',
                    width : 80,
                    styler : function(value, row, index) {
                        return 'font-size:12px;';
                    },
                    formatter : function(value) {
                        if (value == 0) {
                            return "总部集采";
                        } else if (value == 1) {
                            return "一级分部地采";
                        } else if (value == 2) {
                            return "二级分部地采";
                        } else {
                            return value;
                        }
                    }
                }, {
                    field : 'orgNumber',
                    title : '推送单位编码',
                    align : 'center',
                    width : 80,
                    fitColumns : true,
                    styler : function(value, row, index) {
                        return 'font-size:12px;';
                    }
                }, {
                    field : 'orgName',
                    title : '推送单位名称',
                    align : 'center',
                    width : 150,
                    fitColumns : true,
                    styler : function(value, row, index) {
                        return 'font-size:12px;';
                    }
                }, {
                    field : 'positionCode',
                    title : '职务编码',
                    align : 'center',
                    width : 100,
                    fitColumns : true,
                    styler : function(value, row, index) {
                        return 'font-size:12px;';
                    }
                }, {
                    field : 'positionDesc',
                    title : '职务名称',
                    align : 'center',
                    width : 150,
                    fitColumns : true,
                    styler : function(value, row, index) {
                        return 'font-size:12px;';
                    }
                }, {
                    field : 'roleId',
                    title : '角色',
                    align : 'center',
                    width : 100,
                    styler : function(value, row, index) {
                        return 'font-size:12px;';
                    },
                    formatter : function(value) {
                        if (value) {
                            if (value =="11111100") {
                                return '总部领导';
                            } else if (value == "11001110") {
                                return '总部业务';
                            } else if (value == "10110100") {
                                return '分部领导';
                            } else if (value == "10100001") {
                                return '分部业务';
                            }
                        }
                        return value;
                    }
                } ] ]
            });*/

            // ====部门列表=======================================================
            /*$("#orgList").datagrid({
                title : "缺断货单位列表",
                height : document.body.clientHeight,
                url : basePath + 'n/feedbackPush/findOrgPageByCategory',
                loadMsg : "数据加载中...",
                pagination : true,
                rownumbers : true,
                striped : true,
                singleSelect : true,
                pageSize : 20,
                onClickRow : function(index, row) {

                    $("#linkPositionDialog").find("#goodsOrgNumber").val("").val(row.srcOrgNumber);
                    $("#linkPositionDialog").find("#goodsOrgName").val("").val(row.srcOrgName);

                    $("#positionList").datagrid({
                        url : basePath + 'n/feedbackPush/findCategoryPositionByPage',
                        queryParams : {
                            categoryCode : $("#linkPositionDialog").find("#goodsCategoryId").val(),
                            srcOrgNumber : row.srcOrgNumber,
                            _orderBy : "order by org_number asc,position_code asc"
                        }
                    });
                },
                queryParams : {
                    category : $("#linkPositionDialog").find("#goodsCategoryId").val(),
                    _orderBy : "order by org_number asc"
                },
                columns : [ [ {
                    checkbox : true
                }, {
                    field : 'srcOrgNumber',
                    title : '缺断货单位编码',
                    align : 'center',
                    hidden : true
                }, {
                    field : 'srcOrgName',
                    title : '缺断货单位名称',
                    align : 'center',
                    width : 200,
                    styler : function(value, row, index) {
                        return 'font-size:12px;';
                    }
                } ] ]
            });*/

        }

        function initEvent() {
            // 品类列表查询按钮
            $("#searchBtn").click(function() {

                $("#feedbackCheckConfigList").datagrid({
                	
                    queryParams : {
                        oaNumber : $("#oaNumber").val(),
                        _orderBy : "order by category_code asc"
                    }
                });
                $("#oaNumber").val("");
            });
            // 添加品类按钮
            $("#addCategoryConfigBtn").click(function() {
            	$("#addGoodsCategoryConfigdlg").find("#roleId").combobox({
                    valueField : 'label',
                    textField : 'value',
                    data : [ /*{
                        label : '11111100',
                        value : '总部领导'
                    }, */{
                        label : '11001110',
                        value : '总部业务'
                    },/* {
                        label : '10110100',
                        value : '分部领导'
                    }, */{
                        label : '10100001',
                        value : '分部业务'
                    } ]
                }).combobox("setValue", "11001110");
                $("#addGoodsCategoryConfigdlg").dialog("open").dialog("setTitle", "添加配置");
            });
            // 添加品类保存
            $("#addSaveBtn").click(function() {
                $.messager.progress();
                $('#addCategoryfrom').form('submit', {
                    url : basePath + "n/feedbackCheck/addCheckConfig",
                    onSubmit : function() {
                        var isValid = $(this).form('validate');
                        if (!isValid) {
                            $.messager.progress('close');
                        }
                        return isValid;
                    },
                    success:function(data){
                        $.messager.progress('close');
                        $('#addGoodsCategoryConfigdlg').dialog('close');

                      /*  $("#addCategoryfm").find("input[name='categoryCode']").val("");
                        $("#addCategoryfm").find("input[name='categoryName']").val("");*/

                        var msgg  = eval("("+data+")");
                        var fag = msgg.flag;
                        if(fag == '3'){
                        	$.messager.alert('提示',msgg.mess);
                        }else{
                        	$.messager.show({
                        		title : '提示',
                        		msg : msgg.mess,
                        		timeout : 3000,
                        		showType : 'slide'
                        	});
                        }
                        $("#feedbackCheckConfigList").datagrid("reload");
                    }
                });
            });
            // 编辑品类按钮
            $("#editCategoryConfigBtn").click(function() {
                var selectRows = $("#feedbackCheckConfigList").datagrid('getChecked');
                if (selectRows.length == 0) {
                    $.messager.alert('提示', '请选择您要修改的行!', 'info');
                    return false;
                } else if (selectRows.length > 1) {
                    $.messager.alert('提示', '一次只能编辑一条数据!', 'info');
                    return false;
                } else {
                	$("#editCategoryfm").find('#regions_combo1').combobox('setValue',selectRows[0].regionName);//大区
                    $("#editCategoryfm").find("#division_combo1").combobox('setValue',selectRows[0].orgNumber);//分部
                    $("#editCategoryfm").find("#orgName1").val(selectRows[0].orgName);
                    $("#editCategoryfm").find("#category_combo1").combobox('setValue',selectRows[0].categoryCode);//品类
                    $("#editCategoryfm").find("#categoryName1").val(selectRows[0].categoryName);
                    $("#editCategoryfm").find("#brandCode_combo1").combobox('setValue',selectRows[0].brandCode);//品牌
                    $("#editCategoryfm").find("#brandName1").val(selectRows[0].brandName);
                    
                    $("#editCategoryfm").find("#roleId1").combobox({//是否领导
                        valueField : 'label',
                        textField : 'value',
                        data : [ /*{
                            label : '11111100',
                            value : '总部领导'
                        },*/ {
                            label : '11001110',
                            value : '总部业务'
                        }, /*{
                            label : '10110100',
                            value : '分部领导'
                        },*/ {
                            label : '10100001',
                            value : '分部业务'
                        } ]
                    }).combobox("setValue", selectRows[0].roleId);
                    
                    $("#editCategoryfm").find("#empNumber1").val(selectRows[0].empNumber);//员工编码
                    $("#editCategoryfm").find("#id").val(selectRows[0].id);
                    $("#editGoodsCategoryConfigdlg").dialog("open").dialog("setTitle", "编辑品类");
                }
            });
            // 编辑品类保存按钮
            $("#editCategorySaveBtn").click(function() {
                $.messager.progress();
                $('#editCategoryfm').form('submit', {
                    url : basePath + "n/feedbackCheck/updateGoodsCategoryConfig",
                    onSubmit : function() {
                        var isValid = $(this).form('validate');
                        if (!isValid) {
                            $.messager.progress('close');
                        }
                        return isValid;
                    },
                    success : function() {
                        $.messager.progress('close');
                        $('#editGoodsCategoryConfigdlg').dialog('close');

                        $("#editCategoryfm").find("input[name='resourceName']").val("");
                        $("#editCategoryfm").find("input[name='resourceUrl']").val("");

                        $("#feedbackCheckConfigList").datagrid("reload");
                        $.messager.show({
                            title : '提示',
                            msg : '更新品类成功',
                            timeout : 3000,
                            showType : 'slide'
                        });
                    }
                });
            });
            // 删除品类按钮
            $("#deleteCategoryConfigBtn").click(function() {
                var selectRows = $("#feedbackCheckConfigList").datagrid('getChecked');
                if (selectRows.length == 0) {
                    $.messager.alert('提示', '请选择您要修改的行!', 'info');
                    return false;
                } else if (selectRows.length > 1) {
                    $.messager.alert('提示', '一次只能编辑一条数据!', 'info');
                    return false;
                } else {
                    $.messager.confirm('确认', '您确定删除?', function(r) {
                        if (r) {
                            var option = {
                                "cccId" : selectRows[0].id
                            };
                            $.post(basePath + "n/feedbackCheck/deleteCheckConfig", option, function(data) {
                                if (data.flag == "1") {
                                    $.messager.show({
                                        title : '提示',
                                        msg : '删除成功',
                                        timeout : 3000,
                                        showType : 'slide'
                                    });
                                    $("#feedbackCheckConfigList").datagrid("reload");
                                } else {
                                    $.messager.show({
                                        title : '提示',
                                        msg : data.message,
                                        timeout : 3000,
                                        showType : 'slide'
                                    });
                                    $("#feedbackCheckConfigList").datagrid("reload");
                                }
                            });
                        }
                    });
                }
            });
            
            
            // 关联职务按钮
           /* $("#linkPositionBtn").click(function() {
                var selectRows = $("#feedbackCheckConfigList").datagrid('getChecked');
                if (selectRows.length == 0) {
                    $.messager.alert('提示', '请选择您要修改的行!', 'info');
                    return false;
                } else if (selectRows.length > 1) {
                    $.messager.alert('提示', '一次只能编辑一条数据!', 'info');
                    return false;
                } else {
                    $("#linkPositionDialog").find("#goodsCategoryId").val("").val(selectRows[0].categoryCode);
                    $("#linkPositionDialog").find("#goodsCategoryName").val("").val(selectRows[0].categoryName);

                    $("#linkPositionDialog").dialog({
                        title : "关联" + $("#linkPositionDialog").find("#goodsCategoryName").val() + "品类职务",
                        width : document.body.clientWidth,
                        height : document.body.clientHeight
                    }).dialog("open");
                    $("#orgList").datagrid({
                        queryParams : {
                            categoryCode : $("#linkPositionDialog").find("#goodsCategoryId").val(),
                            _orderBy : "order by src_org_number asc"
                        }
                    });
                }

            });*/
            // 添加职务查询按钮
            /*$("#addPositionSearchBtn").click(function() {
                $("#positionList").datagrid({
               	 url : basePath + 'n/feedbackPush/findCategoryPositionByPage',

                    queryParams : {
                        positionName : $("#addPositionName").val(),
                        positionCode : $("#addPositionCode").val(),
                        categoryCode : $("#linkPositionDialog").find("#goodsCategoryId").val()
                    }
                });
            });*/
            // 添加职务按钮
            /*$("#addPositionBtn").click(
                    function() {

                        $("#addPositionDialog").find("#isGMZB").combobox({
                            valueField : 'label',
                            textField : 'value',
                            data : [{
                                label : '0',
                                value : '总部集采'
                            }, {
                                label : '1',
                                value : '一级分部地采'
                            }, {
                                label : '2',
                                value : '二级分部地采'
                            } ]
                        }).combobox("setValue", "1");
                        $("#addPositionDialog").find("#srcOrgType").combobox({
                            valueField : 'label',
                            textField : 'value',
                            data : [ {
                                label : '1',
                                value : '一级分部'
                            }, {
                                label : '2',
                                value : '二级分部'
                            } ]
                        }).combobox("setValue", "1");
                        $("#addPositionDialog").find("#roleId").combobox({
                            valueField : 'label',
                            textField : 'value',
                            data : [ {
                                label : '11111100',
                                value : '总部领导'
                            }, {
                                label : '11001110',
                                value : '总部业务'
                            }, {
                                label : '10110100',
                                value : '分部领导'
                            }, {
                                label : '10100001',
                                value : '分部业务'
                            } ]
                        }).combobox("setValue", "10100001");

                        $("#addPositionDialog").find("#categoryCode").val(
                                $("#linkPositionDialog").find("#goodsCategoryId").val());
                        $("#addPositionDialog").find("#categoryName").val(
                                $("#linkPositionDialog").find("#goodsCategoryName").val());
                        $("#addPositionDialog").find("#srcOrgNumber").val(
                                $("#linkPositionDialog").find("#goodsOrgNumber").val());
                        $("#addPositionDialog").find("#srcOrgName").val(
                                $("#linkPositionDialog").find("#goodsOrgName").val());

                        $("#addPositionDialog").dialog("open").dialog("setTitle", "添加职务");
                    });*/
            // 添加职务保存按钮
            /*$("#addPositionSaveBtn").click(function() {
                $.messager.progress();
                $('#addPositionfm').form('submit', {
                    url : basePath + "n/feedbackPush/addCategoryPosition",
                    onSubmit : function() {
                        var isValid = $(this).form('validate');
                        if (!isValid) {
                            $.messager.progress('close');
                        }
                        return isValid;
                    },
                    success : function() {
                        $.messager.progress('close');
                        $('#addPositionDialog').dialog('close');

                        $("#addPositionfm").find("input[name='positionCode']").val("");
                        $("#addPositionfm").find("input[name='positionDesc']").val("");
                        $("#addPositionfm").find("input[name='orgNumber']").val("");
                        $("#addPositionfm").find("input[name='orgName']").val("");
                        $("#addPositionfm").find("input[name='isGMZB']").val("");
                        $("#addPositionfm").find("input[name='srcOrgNumber']").val("");
                        $("#addPositionfm").find("input[name='srcOrgName']").val("");
                        $("#addPositionfm").find("input[name='srcOrgType']").val("");
                        $("#addPositionfm").find("input[name='roleId']").val("");

                        $("#positionList").datagrid("reload");
                        $.messager.show({
                            title : '提示',
                            msg : '添加职务成功',
                            timeout : 3000,
                            showType : 'slide'
                        });
                    }
                });
            });*/
            // 编辑职务按钮
            /*$("#editPositionBtn").click(function() {
                var selectRows = $("#positionList").datagrid('getChecked');
                if (selectRows.length == 0) {
                    $.messager.alert('提示', '请选择您要修改的行!', 'info');
                    return false;
                } else if (selectRows.length > 1) {
                    $.messager.alert('提示', '一次只能编辑一条数据!', 'info');
                    return false;
                } else {

                    $("#editPositionDialog").find("#isGMZB").combobox({
                        valueField : 'label',
                        textField : 'value',
                        data : [{
                            label : '0',
                            value : '总部集采'
                        }, {
                            label : '1',
                            value : '一级分部地采'
                        }, {
                            label : '2',
                            value : '二级分部地采'
                        } ]
                    }).combobox("setValue", selectRows[0].isGMZB);

                    $("#editPositionDialog").find("#srcOrgType").combobox({
                        valueField : 'label',
                        textField : 'value',
                        data : [ {
                            label : '1',
                            value : '一级分部'
                        }, {
                            label : '2',
                            value : '二级分部地采'
                        } ]
                    }).combobox("setValue", selectRows[0].srcOrgType);
                    
                    $("#editPositionDialog").find("#roleId").combobox({
                        valueField : 'label',
                        textField : 'value',
                        data : [ {
                            label : '11111100',
                            value : '总部领导'
                        }, {
                            label : '11001110',
                            value : '总部业务'
                        }, {
                            label : '10110100',
                            value : '分部领导'
                        }, {
                            label : '10100001',
                            value : '分部业务'
                        } ]
                    }).combobox("setValue", selectRows[0].roleId);
                    $("#editPositionDialog").find("#id").val(selectRows[0].id);
                    $("#editPositionDialog").find("#categoryCode").val(selectRows[0].categoryCode);
                    $("#editPositionDialog").find("#categoryName").val(selectRows[0].categoryName);
                    $("#editPositionDialog").find("#orgNumber").val(selectRows[0].orgNumber);
                    $("#editPositionDialog").find("#orgName").val(selectRows[0].orgName);
                    $("#editPositionDialog").find("#srcOrgNumber").val(selectRows[0].srcOrgNumber);
                    $("#editPositionDialog").find("#srcOrgName").val(selectRows[0].srcOrgName);
                    $("#editPositionDialog").find("#positionCode").val(selectRows[0].positionCode);
                    $("#editPositionDialog").find("#positionDesc").val(selectRows[0].positionDesc);

                    $("#editPositionDialog").dialog("open").dialog("setTitle", "编辑职务");
                }
            });*/
            
            // 编辑职务保存按钮
            /*$("#editPositionSaveBtn").click(function() {
                $.messager.progress();
                $('#editPositionfm').form('submit', {
                    url : basePath + "n/feedbackCheck/updateCategoryPosition",
                    onSubmit : function() {
                        var isValid = $(this).form('validate');
                        if (!isValid) {
                            $.messager.progress('close');
                        }
                        return isValid;
                    },
                    success : function() {
                        $.messager.progress('close');
                        $('#editPositionDialog').dialog('close');

                        $("#editPositionfm").find("input[name='positionCode']").val("");
                        $("#editPositionfm").find("input[name='positionDesc']").val("");
                        $("#editPositionfm").find("input[name='orgNumber']").val("");
                        $("#editPositionfm").find("input[name='orgName']").val("");
                        $("#editPositionfm").find("input[name='isGMZB']").val("");
                        $("#editPositionfm").find("input[name='srcOrgNumber']").val("");
                        $("#editPositionfm").find("input[name='srcOrgName']").val("");
                        $("#editPositionfm").find("input[name='srcOrgType']").val("");
                        $("#editPositionfm").find("input[name='roleId']").val("");

                        $("#positionList").datagrid("reload");
                        $.messager.show({
                            title : '提示',
                            msg : '编辑职务成功',
                            timeout : 3000,
                            showType : 'slide'
                        });
                    }
                });
            });*/
            // 删除职务按钮
            /*$("#deletePositionBtn").click(function() {
                var selectRows = $("#positionList").datagrid('getChecked');
                if (selectRows.length == 0) {
                    $.messager.alert('提示', '请选择您要修改的行!', 'info');
                    return false;
                } else if (selectRows.length > 1) {
                    $.messager.alert('提示', '一次只能编辑一条数据!', 'info');
                    return false;
                } else {
                    $.messager.confirm('确认', '您确定删除吗?', function(r) {
                        if (r) {
                            var option = {
                                "id" : selectRows[0].id
                            };
                            $.post(basePath + "n/feedbackPush/deleteCategoryPosition", option, function(data) {
                                if (data.flag == "1") {
                                    $.messager.show({
                                        title : '提示',
                                        msg : '删除职务成功',
                                        timeout : 3000,
                                        showType : 'slide'
                                    });
                                    $("#positionList").datagrid("reload");
                                } else {
                                    $.messager.show({
                                        title : '提示',
                                        msg : data.message,
                                        timeout : 3000,
                                        showType : 'slide'
                                    });
                                    $("#positionList").datagrid("reload");
                                }
                            });
                        }
                    });
                }
            });*/
        }
        
        function SDivisionStore(fDivisionCode){
            $('#secondDivision_combo').combobox(
                    {
                         url:basePath + 'n/secondDivision/findSencondDivisions?first_division_code='+fDivisionCode,
                         valueField:'orgNumber',
                         textField:'orgName' ,
                         onSelect:function(record){
                                 alert(record.orgName);
                            	
                             }
                        	 
                     });
        }
        function init() {
            loadData();
            initEvent();
            $('#regions_combo').combobox(
                    {
                         url:basePath + 'n/region/findRegions',
                         valueField:'region_code',
                         textField:'region_des',
                         onSelect:function(sss){
                        	 $("#regions_combo").val(sss.region_des); 
                         },
                         onChange:function(record,o){
//                             alert(record+"+++++++"+o);
                             $('#division_combo').combobox(
                                     {
                                          url:basePath + 'n/division/findDivisions?region_code='+record,
                                          valueField:'division_code',
                                          textField:'division_desc',
                                          onSelect:function(record){
                                              //alert(record.division_code);
                                        	  $("#orgName").val(record.division_desc); 
//                                            SDivisionStore(record.division_code);
                                          }
                                      });
                             
                         }
                     });
            
            $('#regions_combo1').combobox(
                    {
                         url:basePath + 'n/region/findRegions',
                         valueField:'region_code',
                         textField:'region_des',
                         onSelect:function(sss){
                        	 $("#regions_combo1").val(sss.region_des); 
                         },
                         onChange:function(record,o){
//                             alert(record+"-------"+o);
                        	 $('#division_combo1').val("");
                             $('#division_combo1').combobox(
                                     {
                                          url:basePath + 'n/division/findDivisions?region_code='+record,
                                          valueField:'division_code',
                                          textField:'division_desc',
                                          onSelect:function(record){
                                              //alert(record.division_code);
                                        	  $("#orgName1").val(record.division_desc); 
//                                            SDivisionStore(record.division_code);
                                          }
                                      });
                             
                         }
                     });
            
            
            $('#category_combo').combobox(
                    {
                         url:basePath + '/n/feedbackCheck/findCategoryConfigList',
                         valueField:'categoryCode',
                         textField:'categoryName',
                         onSelect:function(sss){
                        	 $("#categoryName").val(sss.categoryName);
                         },
                         onChange:function(record,o){
                             $('#brandCode_combo').combobox(
                             
                                     {
                                          url:basePath + 'n/feedbackCheck/findbrandCodeList?categoryCode='+record,
                                          valueField:'brandCode',
                                          textField:'brandName',
                                          onSelect:function(data){
                                        	  $("#brandName").val(data.brandName);
                                              //alert(record.division_code);
                                              /*SDivisionStore(record.division_code);*/
                                          }
                                      });
                             
                         }
                     });
            
            $('#category_combo1').combobox(
                    {
                         url:basePath + '/n/feedbackCheck/findCategoryConfigList',
                         valueField:'categoryCode',
                         textField:'categoryName',
                         onSelect:function(sss){
                        	 $("#categoryName1").val(sss.categoryName); 
                         },
                         onChange:function(record,o){
                        	 $('#brandCode_combo1').val("");
                             $('#brandCode_combo1').combobox(
                             
                                     {
                                          url:basePath + 'n/feedbackCheck/findbrandCodeList?categoryCode='+record,
                                          valueField:'brandCode',
                                          textField:'brandName',
                                          onSelect:function(data){
//                                        	  alert(data.brandName);
                                        	  $("#brandName1").val(data.brandName);
//                                              alert($("#brandName1").val());
                                              /*SDivisionStore(record.division_code);*/
                                          }
                                      });
                             
                         }
                     });
        }
        init();
    });
})();