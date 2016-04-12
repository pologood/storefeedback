;
(function() {
    $(function() {
    	var flag = $("#flag").val();
    	var id = $("#id").val();
    	//业务人员只能操作本周数据
    	if(flag == '1'){
    		$("#confirmResultBtn").show();
    		$("#getRppealListBtn").show();
    	}
    	var buyType = $("#buyType").val();
    	var getType;
    	if(buyType == 1){
    		getType = "集采";
    	}else if(buyType == 0){
    		getType = "地采";
    	}
    	var categoryCode = $("#categoryCode").val();
    	var brandCode = $("#brandCode").val();
    	var goodsCode = $("#goodsCode").val();
    	var goodsName = $("#goodsName").val();
    	
    	//查询申诉人信息
    	$("#searchAppealEmpName").click(function(){
    		$("#appealAccount").val($("#appealName").val());
    		$.ajax({
    			type: "GET",
    			url: basePath + 'n/checkSapFeedback/getEmpByAcccount',
    			data: {"empAcccount" : $("#appealName").val()},
    			dataType: "json",
    			success: function(data){
    				$("#appealName").val(data.result.empName);
    				$("#appealEmp").val(data.result.empNum);
    			}
    		});
    		return false;
    	});
    	//查询审批人信息
    	$("#searchExamineEmpName").click(function(){
    		$("#examineAccount").val($("#examineName").val());
    		$.ajax({
    			type: "GET",
    			url: basePath + 'n/checkSapFeedback/getEmpByAcccount',
    			data: {"empAcccount" : $("#examineName").val()},
    			dataType: "json",
    			success: function(data){
    				$("#examineName").val(data.result.empName);
    				$("#examineEmp").val(data.result.empNum);
    			}
    		});
    		return false;
    	});

    	
        function loadData() {
        	
            // ======【缺货|断货】【分部】【数量】=============================================
            $("#categoryBrandGoodsDivisionList").datagrid({
            	
            	rowStyler:function(index,row){       
            		//设置行颜色
            		if (row.is_handler==4){               
            			return 'background-color:green;';           
            		}
            		if (row.is_handler==1){               
            			return 'background-color:red;';           
            		}
//            		if (row.is_handler==2){               
//            			return 'background-color:green;';           
//            		}
            		if (row.is_handler==3){               
            			return 'background-color:yellow;';           
            		}
            	},
            	
                title : "[各分部]["+ goodsName + "]" + getType +"缺断货信息列表",
                toolbar : "#tb",
                height : document.body.clientHeight * 0.98,
                url : basePath + 'n/checkSapFeedback/getByEmpCategoryBuytypeBrandGoods',
                loadMsg : "数据加载中...",
                pagination : true,
                rownumbers : true,
                striped : true,
                singleSelect : false,
                pageSize : 20,
                queryParams : {
                	buyType : buyType,
                	categoryCode : categoryCode,
                	brandCode : brandCode,
                	goodsCode : goodsCode
                },
                columns : [ [ {
                    checkbox : true
                }, {
                    field : 'id',
                    title : 'id',
                    align : 'center',
                    width : 300,
                    hidden : true,
                    styler : function(value, row, index) {
                        return 'font-size:12px;';
                    }
                },{
                    field : 'is_handler',
                    title : '处理结果',
                    align : 'center',
                    width : 300,
                    hidden : true,
                    styler : function(value, row, index) {
                        return 'font-size:12px;';
                    }
                }, {
                    field : 'feedbackStatus',
                    title : '缺断货状态',
                    align : 'center',
                    width : 200,
                    hidden : false,
                    fitColumns : true,
                    styler : function(value, row, index) {
                        return 'font-size:12px;';
                    },
                    formatter : function(value, rec) {								
                    	if (value == 'D')									
                    		return '<a style="color:red">断货</a>';								
                    	else if (value == 'Q')									
                    		return '<a style="color:red">缺货</a>';							
                    }
                }, {
                    field : 'divisionName',
                    title : '分部',
                    align : 'center',
                    width : 300,
                    styler : function(value, row, index) {
                        return 'font-size:12px;';
                    }
                }, {
                    field : 'stock',
                    title : '当前库存',
                    align : 'center',
                    width : 100,
                    hidden : false,
                    styler : function(value, row, index) {
                        return 'font-size:12px;';
                    }
                }, {
                    field : 'saleNum',
                    title : '前四周销量',
                    align : 'center',
                    hidden : false,
                    width : 300,
                    styler : function(value, row, index) {
                        return 'font-size:12px;';
                    }
                } ] ]
            });
        }
        
        function initEvent() {
        	
            // 不补货原因列表查询按钮
//            $("#searchBtn").click(function() {
//                $("#categoryBrandGoodsDivisionList").datagrid({
//                    queryParams : {
//                        _orderBy : "order by result_no_code asc"
//                    }
//                });
//            });
            // 添加  确认  按钮
            $("#confirmResultBtn").click(function() {
            	var rows = $("#categoryBrandGoodsDivisionList").datagrid("getRows");
            	var selectRows = $("#categoryBrandGoodsDivisionList").datagrid('getChecked');
            	
                if (selectRows.length == 0) {
                    $.messager.alert('提示', '请选择您要修改的行!', 'info');
                    return false;
                } /*else if (selectRows.length > 1) {
                    $.messager.alert('提示', '一次只能编辑一条数据!', 'info');
                    return false;
                } */else {
                	/*if(id == selectRows[0].id){
                		var num = 0;
                		for(var i=0;i<rows.length;i++){
                			if(rows[i].is_handler == 1 || rows[i].is_handler == 3 || rows[i].is_handler == 4){
                				num ++;
                			}
                		}
                		if(rows.length - num > 1){
                			alert("此条目为关联条目，请最后处理");
                			return false;
                		}
                	} */
                	for(var i=0;i<selectRows.length;i++){
                		if(selectRows[i].is_handler != 2){
                			$.messager.alert('提示', '已处理的考核信息不能再次处理!', 'info');
                			return false;
                		}
                		
                	}
                	var ids = "";
        			for(var i=0;i<selectRows.length;i++){
        				ids = ids+selectRows[i].id+"+";
        			}
                	$("#confirmResultfm").find("#id1").val(ids);
                	
                    $.messager.progress();
                    $('#confirmResultfm').form('submit', {
                        url : basePath + "n/checkSapFeedback/confirmResult",
                        onSubmit : function() {
                            var isValid = $(this).form('validate');
                            if (!isValid) {
                                $.messager.progress('close');
                            }
                            return isValid;
                        },
                        success : function() {
                            $.messager.progress('close');
                            $('#addResultNoReasondlg').dialog('close');
                            $("#categoryBrandGoodsDivisionList").datagrid("reload");
                            $.messager.show({
                                title : '提示',
                                msg : '确认结果成功',
                                timeout : 3000,
                                showType : 'slide'
                            });
                        }
                    });
                    
                	
                }
            	
            });
            // 添加  补货  输入订单号提交按钮
//            $("#addResultNoReasonSaveBtn").click(function() {
//            	
//                $.messager.progress();
//                $('#addResultNoReasonfm').form('submit', {
//                    url : basePath + "n/checkSapFeedback/insertResult",
//                    onSubmit : function() {
//                        var isValid = $(this).form('validate');
//                        if (!isValid) {
//                            $.messager.progress('close');
//                        }
//                        return isValid;
//                    },
//                    success : function() {
//                        $.messager.progress('close');
//                        $('#addResultNoReasondlg').dialog('close');
//                        $("#categoryBrandGoodsDivisionList").datagrid("reload");
//                        $.messager.show({
//                            title : '提示',
//                            msg : '输入订单号成功',
//                            timeout : 3000,
//                            showType : 'slide'
//                        });
//                    }
//                });
//            });
            
            // 添加  申诉  按钮
            $("#getRppealListBtn").click(function() {
            	var rows = $("#categoryBrandGoodsDivisionList").datagrid("getRows");
                var selectRows = $("#categoryBrandGoodsDivisionList").datagrid('getChecked');
                
	                if (selectRows.length == 0) {
	                    $.messager.alert('提示', '请选择您要修改的行!', 'info');
	                    return false;
	                } /*else if (selectRows.length > 1) {
	                    $.messager.alert('提示', '一次只能编辑一条数据!', 'info');
	                    return false;
	                } */else {
	                	/*if(id == selectRows[0].id){
	                		
	                		var num = 0;
	                		for(var i=0;i<rows.length;i++){
	                			if(rows[i].is_handler == 1 || rows[i].is_handler == 3 || rows[i].is_handler == 4){
	                				num ++;
	                			}
	                		}
	                		if(rows.length - num > 1){
	                			alert("此条目为关联条目，请最后处理");
	                			return false;
	                		}
	                	}*/
	                	for(var i=0;i<selectRows.length;i++){
	                		if(selectRows[i].is_handler != 2){
	                			$.messager.alert('提示', '已处理的考核信息不能再次处理!', 'info');
	                			return false;
	                		}
	                		
	                	}
	                	var ids = "";
            			for(var i=0;i<selectRows.length;i++){
            				ids = ids + selectRows[i].id+"+";
            			}
            			
	                	$("#appealfm").find("#id2").val(ids);//-----
	                	$("#appealfm").find("#appealModel").val(goodsName);//-----
	                	
	                	//申诉制空
                		$("#appealName").val("姓名拼音");
                		$("#appealReason").val("");
                		$("#examineName").val("姓名拼音");
	                	
	                    $("#editResultNoReasondlg").dialog("open").dialog("setTitle", goodsName + "型号申诉申请");
	                	 
	                }

            });
            // 添加  申诉  提交按钮
            $("#editResultNoReasonSaveBtn").click(function() {
            	if(true){
            		$("#appealfm").find("#resultNoName").val($("option:selected").text());
            	}
            	
            	$.messager.progress();
                $('#appealfm').form('submit', {
                    url : basePath + "n/checkSapFeedback/confirmResult",
                    onSubmit : function() {
                        var isValid = $(this).form('validate');
                        if (!isValid) {
                            $.messager.progress('close');
                        }
                        return isValid;
                    },
                    success : function() {
                        $.messager.progress('close');
                        $('#editResultNoReasondlg').dialog('close');
                        $("#categoryBrandGoodsDivisionList").datagrid("reload");
                        $.messager.show({
                            title : '提示',
                            msg : '申诉成功',
                            timeout : 3000,
                            showType : 'slide'
                        });
                    }
                });
            });
        }
        function init() {
            loadData();
            initEvent();
        }
        init();
    });
})();