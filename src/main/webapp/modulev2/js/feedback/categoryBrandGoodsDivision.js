;
(function() {
    $(function() {
    	
    	var buyType = $("#buyType").html();
    	var categoryCode = $("#categoryCode").html();
    	var brandCode = $("#brandCode").html();
    	var goodsCode = $("#goodsCode").html();
    	
    	var flag = $("#buyTypeAuthRole").text();
    	var id = $("#id").text();
    	//只有业务人员可以操作
//    	if(flag == '11001110' || flag == '10100001'){
//    		$("#addResultNoReasonBtn").show();
//    		$("#editResultNoReasonBtn").show();
//    	}
    	if(flag == '11001110'){
    		if(buyType == '1'){
    			$("#addResultNoReasonBtn").show();
        		$("#editResultNoReasonBtn").show();
    		}
    	}
    	if(flag == '10100001'){
    		if(buyType == '0'){
    			$("#addResultNoReasonBtn").show();
        		$("#editResultNoReasonBtn").show();
    		}
    	}
    	
        function loadData() {
        	
            // ======【缺货|断货】【分部】【数量】=============================================
            $("#categoryBrandGoodsDivisionList").datagrid({
            	
            	rowStyler:function(index,row){           
            		if (row.is_handler==4){               
            			return 'background-color:green;';           
            		}
            		if (row.is_handler==3){               
            			return 'background-color:yellow;';           
            		}
            	},
            	
                title : "集采分部列表",
                toolbar : "#tb",
                height : document.body.clientHeight * 0.98,
                url : basePath + 'n/aspFeedBack/getByEmpCategoryBuytypeBrandGoods',
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
                    checkbox : true,
                    width : $(this).width() * 0.1
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
            $("#searchBtn").click(function() {
                $("#categoryBrandGoodsDivisionList").datagrid({
                    queryParams : {
                        _orderBy : "order by result_no_code asc"
                    }
                });
            });
            // 添加  是补货 按钮
            $("#addResultNoReasonBtn").click(function() {
            	var hour=new Date().getHours();
            	if(hour >= 10 && hour <= 23){
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
            					if(rows[i].is_handler == 1){
            						num ++;
            					}
            				}
            				if(rows.length - num > 1){
            					alert("此条目为关联条目，请最后处理");
            					return false;
            				}
            			} */
//            			for(var i=0;i<selectRows.length;i++){
//                    		if(selectRows[i].is_handler != 2){
//                    			$.messager.alert('提示', '已处理的缺断货信息不能再次处理!', 'info');
//                    			return false;
//                    		}
//                    		
//                    	}
            			$("#addResultNoReasondlg").dialog("open").dialog("setTitle", "请输入订单号：");
            			var ids = "";
            			for(var i=0;i<selectRows.length;i++){
            				ids = ids+selectRows[i].id+"+";
            			}
            			
            			$("#addResultNoReasonfm").find("#id1").val(ids);
            			
            		}
            	}else {
            		alert ("前日处理时间已结束，请等待当日数据更新");
            		return false;
            	}
            	
            });
            // 添加  补货  输入订单号提交按钮
            $("#addResultNoReasonSaveBtn").click(function() {
            	
                $.messager.progress();
                $('#addResultNoReasonfm').form('submit', {
                    url : basePath + "n/aspFeedBack/insertResult",
                    onSubmit : function() {
                        var isValid = $(this).form('validate');
                        if (!isValid) {
                            $.messager.progress('close');
                        }
                        return isValid;
                    },
                    success : function(result) {
                    	var map = $.parseJSON(result);
                    	
                        $.messager.progress('close');
                        $('#addResultNoReasondlg').dialog('close');
                        $("#categoryBrandGoodsDivisionList").datagrid("reload");
                        if(map.hasOwnProperty("error_code")){
                        	$.messager.show({
                    			title : '提示',
                    			msg : map["error_msg"],
                    			timeout : 5000,
                    			showType : 'slide'
                    		});
                    	} else {
                    		$.messager.show({
                    			title : '提示',
                    			msg : '输入订单号成功',
                    			timeout : 3000,
                    			showType : 'slide'
                    		});
                    	}
                    }
                });
            });
            
            // 添加  否补货  按钮
            $("#editResultNoReasonBtn").click(function() {
            	var hour=new Date().getHours();
            	if(hour >= 10 && hour <= 23){
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
	                			if(rows[i].is_handler == 1){
	                				num ++;
	                			}
	                		}
	                		if(rows.length - num > 1){
	                			alert("此条目为关联条目，请最后处理");
	                			return false;
	                		}
	                	}*/
	                	for(var i=0;i<selectRows.length;i++){
	                		if(2 != selectRows[i].is_handler){
	                			$.messager.alert('提示', '已处理的缺断货信息不能再次处理!', 'info');
	                			return false;
	                		}
	                		
	                	}
	                	var ids = "";
            			for(var i=0;i<selectRows.length;i++){
            				ids = ids + selectRows[i].id+"+";
            			}
            			
	                	$("#editResultNoReasonfm").find("#id2").val(ids);//-----
	                    $("#editResultNoReasondlg").dialog("open").dialog("setTitle", "编辑不补货原因");
	                	 
	                }
            	}else {
            		alert ("前日处理时间已结束，请等待当日数据更新");
            		return false;
            	}
            });
            // 添加  无法补货原因  提交按钮
            $("#editResultNoReasonSaveBtn").click(function() {
            	if(true){
            		$("#editResultNoReasonfm").find("#resultNoName").val($("option:selected").text());
            	}
            	
            	$.messager.progress();
                $('#editResultNoReasonfm').form('submit', {
                    url : basePath + "n/aspFeedBack/insertResult",
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
                            msg : '更新不补货原因成功',
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