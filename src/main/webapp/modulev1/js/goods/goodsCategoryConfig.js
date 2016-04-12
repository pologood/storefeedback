;(function(){
	$(function(){
		function loadData(){
			$("#goodCategoryConfigList").datagrid({
				title: "品类列表",
				toolbar: "#tb",
				height : document.body.clientHeight*0.98,
				url : basePath + 'n/goodsCategoryConfig/findGoodsCategoryConfigByPage',
				columns: [[
							{
								   checkbox:true
							},
				           {
				        	   field:'id',
				        	   title:'ID',
				        	   width:80,
				        	   hidden:true,
				        	   fitColumns :true,
				        	   styler: function(value,row,index){
				   					return 'font-size:12px;';
				   				}   
				           },
				           {
				        	   field:'categoryCode',
				        	   title:'品类编码',
				        	   align : 'center',
				        	   width:100,
				        	   styler: function(value,row,index){
				   					return 'font-size:12px;';
				   				}
				           },
				           {
				        	   field:'categoryName',
				        	   title:'品类名称',
				        	   align : 'center',
				        	   width:100,
				        	   styler: function(value,row,index){
				   					return 'font-size:12px;';
				   				}
				           },
				           {
				        	   field:'createTime',
				        	   title:'创建时间',
				        	   align : 'center',
				        	   width:300,
				        	   styler: function(value,row,index){
				        		   return 'font-size:12px;';
				        	   },
				        	   formatter : function(value){
				        		   return new Date(value).format("yyyy-MM-dd HH:mm:ss");
				        	   }
				           }/*,
				           {
				        	   field:'id',
				        	   title:'编辑',
				        	   align : 'center',
				        	   width:200,
				        	   styler: function(value,row,index){
				   					return 'font-size:12px;';
				   			   },
				   			   formatter : function(value){
				   				   return "<a href='javascript:void(0);' id='" + value + "' onclick='editGoodsCategoryConfig(this);'>编辑</a>" + " | " + 
				   				   			"<a href='javascript:void(0);' id='" + value + "' onclick='deleteGoodsCategoryConfig(this);'>删除</a>" + " | " + 
				   				   			"<a href='javascript:void(0);' id='" + value + "' onclick='linkPosition(this);'>关联职务</a>";
				   			   }
				           }*/
				           ]],
				loadMsg: "数据加载中...",
				pagination: true,
				rownumbers: true,
				striped: true,
				pageSize : 20,
				queryParams: {
					"_orderBy" : "order by create_time asc"
				}
			});
			//品类职务列表
			$("#positionList").datagrid({
				title: "职务列表",
				toolbar: "#positionListToolBar",
				height : document.body.clientHeight*0.98,
				url : basePath + 'n/categoryPosition/findCategoryPositionByPage',
				columns: [[
				           {
				        	   checkbox:true
				           },
				           {
				        	   field:'id',
				        	   title:'ID',
				        	   width:80,
				        	   hidden:true,
				        	   fitColumns :true,
				        	   styler: function(value,row,index){
				   					return 'font-size:12px;';
				   				}   
				           },
				           {
				        	   field:'positionCode',
				        	   title:'职务编码',
				        	   align : 'center',
				        	   width:100,
				        	   styler: function(value,row,index){
				        		   return 'font-size:12px;';
				        	   }
				           },
				           {
				        	   field:'positionDesc',
				        	   title:'职务名称',
				        	   align : 'center',
				        	   width:100,
				        	   styler: function(value,row,index){
				        		   return 'font-size:12px;';
				        	   }
				           },
				           {
				        	   field:'createTime',
				        	   title:'创建时间',
				        	   align : 'center',
				        	   width:300,
				        	   styler: function(value,row,index){
				        		   return 'font-size:12px;';
				        	   },
				        	   formatter : function(value){
				        		   return new Date(value).format("yyyy-MM-dd HH:mm:ss");
				        	   }
				           }/*,
				           {
				        	   field:'id',
				        	   title:'编辑',
				        	   align : 'center',
				        	   width:200,
				        	   styler: function(value,row,index){
				        		   return 'font-size:12px;';
				        	   },
				        	   formatter : function(value){
				        		   return "<a href='javascript:void(0);' id='" + value + "' onclick='editCategoryPosition(this);'>编辑</a>" + " | " + 
				        		   "<a href='javascript:void(0);' id='" + value + "' onclick='deleteCategoryPosition(this);'>删除</a>";
				        	   }
				           }*/
				           ]],
				           loadMsg: "数据加载中...",
				           pagination: true,
				           rownumbers: true,
				           striped: true,
				           pageSize : 20,
				           queryParams: {
				        	   goodsCategoryId : $("#linkPositionDialog").find("#goodsCategoryId").val(),
				        	   "_orderBy" : "order by create_time asc"
				           }
			});
			
		}
		function initEvent(){
			//品类列表查询按钮
			$("#searchBtn").click(function(){
				$("#goodCategoryConfigList").datagrid({
					queryParams: {
						categoryCode: $("#categoryCode").val(),
						categoryName: $("#categoryName").val()
					}
				});
			});
			//添加品类按钮
			$("#addCategoryConfigBtn").click(function(){
				$("#addGoodsCategoryConfigdlg").dialog("open").dialog("setTitle", "添加品类");
			});
			//添加品类保存
			$("#addCategorySaveBtn").click(function(){
				$.messager.progress();	// display the progress bar
				$('#addCategoryfm').form('submit', {
					url: basePath + "n/goodsCategoryConfig/addGoodsCategoryConfig",
					onSubmit: function(){
						var isValid = $(this).form('validate');
						if (!isValid){
							$.messager.progress('close');	// hide progress bar while the form is invalid
						}
						return isValid;	// return false will stop the form submission
					},
					success: function(){
						$.messager.progress('close');	// hide progress bar while submit successfully
						$('#addGoodsCategoryConfigdlg').dialog('close');
						
						$("#addCategoryfm").find("input[name='categoryCode']").val("");
						$("#addCategoryfm").find("input[name='categoryName']").val("");
						
						$("#goodCategoryConfigList").datagrid("reload");
						$.messager.show({
							title:'提示',
							msg:'添加品类成功',
							timeout:3000,
							showType:'slide'
						});
					}
				});
			});
			//编辑品类按钮
			$("#editCategoryConfigBtn").click(function(){
				var selectRows = $("#goodCategoryConfigList").datagrid('getChecked');
				if(selectRows.length == 0){
					$.messager.alert('提示','请选择您要修改的行!','info');
					return false;
				}else if(selectRows.length > 1){
					$.messager.alert('提示','一次只能编辑一条数据!','info');
					return false;
				}else{
					$("#editCategoryfm").find("#categoryCode").val(selectRows[0].categoryCode);
					$("#editCategoryfm").find("#categoryName").val(selectRows[0].categoryName);
					$("#editCategoryfm").find("#id").val(selectRows[0].id);
					$("#editGoodsCategoryConfigdlg").dialog("open").dialog("setTitle", "编辑品类");
				}
			});
			//编辑品类保存按钮
			$("#editCategorySaveBtn").click(function(){
				$.messager.progress();	// display the progress bar
				$('#editCategoryfm').form('submit', {
					url: basePath + "n/goodsCategoryConfig/updateGoodsCategoryConfig",
					onSubmit: function(){
						var isValid = $(this).form('validate');
						if (!isValid){
							$.messager.progress('close');	// hide progress bar while the form is invalid
						}
						return isValid;	// return false will stop the form submission
					},
					success: function(){
						$.messager.progress('close');	// hide progress bar while submit successfully
						$('#editGoodsCategoryConfigdlg').dialog('close');
						
						$("#editCategoryfm").find("input[name='resourceName']").val("");
						$("#editCategoryfm").find("input[name='resourceUrl']").val("");
						
						$("#goodCategoryConfigList").datagrid("reload");
						$.messager.show({
							title:'提示',
							msg:'更新品类成功',
							timeout:3000,
							showType:'slide'
						});
					}
				});
			});
			//删除品类按钮
			$("#deleteCategoryConfigBtn").click(function(){
				var selectRows = $("#goodCategoryConfigList").datagrid('getChecked');
				if(selectRows.length == 0){
					$.messager.alert('提示','请选择您要修改的行!','info');
					return false;
				}else if(selectRows.length > 1){
					$.messager.alert('提示','一次只能编辑一条数据!','info');
					return false;
				}else{
					$.messager.confirm('确认','您确定删除?',function(r){
					    if (r){
					        var option = {
					        		"cccId" : selectRows[0].id
					        };
					        $.post(basePath + "n/goodsCategoryConfig/deleteGoodsCategoryConfig", option, function(data){
					        	if(data.flag == "1"){
					        		$.messager.show({
										title:'提示',
										msg:'删除品类成功',
										timeout:3000,
										showType:'slide'
									});
					        		$("#goodCategoryConfigList").datagrid("reload");
					        	}else{
					        		$.messager.show({
										title:'提示',
										msg:data.message,
										timeout:3000,
										showType:'slide'
									});
					        		$("#goodCategoryConfigList").datagrid("reload");
					        	}
					        });
					    }
					});
				}
			});
			//关联职务按钮
			$("#linkPositionBtn").click(function(){
				var selectRows = $("#goodCategoryConfigList").datagrid('getChecked');
				if(selectRows.length == 0){
					$.messager.alert('提示','请选择您要修改的行!','info');
					return false;
				}else if(selectRows.length > 1){
					$.messager.alert('提示','一次只能编辑一条数据!','info');
					return false;
				}else{
					$("#linkPositionDialog").find("#goodsCategoryId").val(selectRows[0].id);
					$("#linkPositionDialog").dialog("open").dialog("setTitle", "关联职务");
					$("#positionList").datagrid({
						queryParams: {
							goodsCategoryId : $("#linkPositionDialog").find("#goodsCategoryId").val(),
							"_orderBy" : "order by create_time asc"
						}
					});
				}
				
			});
			//加载到缓存
			$("#loadCacheBtn").click(function(){
				$.post(basePath + "n/goodsCategoryConfig/loadGoodsCategoryConfigCache", null, function(data){
					if(data.flag == "1"){
						$.messager.show({
							title:'提示',
							msg:'加载缓存成功',
							timeout:3000,
							showType:'slide'
						});
					}else{
						$.messager.show({
							title:'提示',
							msg:data.message,
							timeout:3000,
							showType:'slide'
						});
					}
				});
			});
			//添加职务查询按钮
			$("#addPositionSearchBtn").click(function(){
				$("#positionList").datagrid({
					queryParams: {
						positionName: $("#addPositionName").val(),
						positionCode: $("#addPositionCode").val(),
						goodsCategoryId : $("#linkPositionDialog").find("#goodsCategoryId").val()
					}
				});
			});
			//添加职务按钮
			$("#addPositionBtn").click(function(){
				var categoryId = $("#linkPositionDialog").find("#goodsCategoryId").val();
				$("#addPositionDialog").find("#goodsCategoryId").val(categoryId);
				$("#addPositionDialog").dialog("open").dialog("setTitle", "添加职务");;
			});
			//添加职务保存按钮
			$("#addPositionSaveBtn").click(function(){
				$.messager.progress();	// display the progress bar
				$('#addPositionfm').form('submit', {
					url: basePath + "n/categoryPosition/addCategoryPosition",
					onSubmit: function(){
						var isValid = $(this).form('validate');
						if (!isValid){
							$.messager.progress('close');	// hide progress bar while the form is invalid
						}
						return isValid;	// return false will stop the form submission
					},
					success: function(){
						$.messager.progress('close');	// hide progress bar while submit successfully
						$('#addPositionDialog').dialog('close');
						
						$("#addPositionfm").find("input[name='positionCode']").val("");
						$("#addPositionfm").find("input[name='positionDesc']").val("");
						
						$("#positionList").datagrid("reload");
						$.messager.show({
							title:'提示',
							msg:'添加职务成功',
							timeout:3000,
							showType:'slide'
						});
					}
				});
			});
			//编辑职务按钮
			$("#editPositionBtn").click(function(){
				var selectRows = $("#positionList").datagrid('getChecked');
				if(selectRows.length == 0){
					$.messager.alert('提示','请选择您要修改的行!','info');
					return false;
				}else if(selectRows.length > 1){
					$.messager.alert('提示','一次只能编辑一条数据!','info');
					return false;
				}else{
					$("#editPositionDialog").find("#goodsPositionId").val(selectRows[0].id);
					$("#editPositionDialog").find("#positionCode").val(selectRows[0].positionCode);
					$("#editPositionDialog").find("#positionDesc").val(selectRows[0].positionDesc);
					$("#editPositionDialog").dialog("open").dialog("setTitle", "编辑职务");
				}
			});
			//编辑职务保存按钮
			$("#editPositionSaveBtn").click(function(){
				$.messager.progress();	// display the progress bar
				$('#editPositionfm').form('submit', {
					url: basePath + "n/categoryPosition/updateCategoryPosition",
					onSubmit: function(){
						var isValid = $(this).form('validate');
						if (!isValid){
							$.messager.progress('close');	// hide progress bar while the form is invalid
						}
						return isValid;	// return false will stop the form submission
					},
					success: function(){
						$.messager.progress('close');	// hide progress bar while submit successfully
						$('#editPositionDialog').dialog('close');
						
						$("#editPositionfm").find("input[name='positionCode']").val("");
						$("#editPositionfm").find("input[name='positionDesc']").val("");
						
						$("#positionList").datagrid("reload");
						$.messager.show({
							title:'提示',
							msg:'编辑职务成功',
							timeout:3000,
							showType:'slide'
						});
					}
				});
			});
			//删除职务按钮
			$("#deletePositionBtn").click(function(){
				var selectRows = $("#positionList").datagrid('getChecked');
				if(selectRows.length == 0){
					$.messager.alert('提示','请选择您要修改的行!','info');
					return false;
				}else if(selectRows.length > 1){
					$.messager.alert('提示','一次只能编辑一条数据!','info');
					return false;
				}else{
					$.messager.confirm('确认','您确定删除吗?',function(r){
					    if (r){
					        var option = {
					        		"id" : selectRows[0].id
					        };
					        $.post(basePath + "n/categoryPosition/deleteCategoryPosition", option, function(data){
					        	if(data.flag == "1"){
					        		$.messager.show({
										title:'提示',
										msg:'删除职务成功',
										timeout:3000,
										showType:'slide'
									});
					        		$("#positionList").datagrid("reload");
					        	}else{
					        		$.messager.show({
										title:'提示',
										msg:data.message,
										timeout:3000,
										showType:'slide'
									});
					        		$("#positionList").datagrid("reload");
					        	}
					        });
					    }
					});
				}
			});
			
		}
		function init(){
			loadData();
			initEvent();
		}
		init();
	});
})();

/*function deleteGoodsCategoryConfig(element){//删除品类
	var ele = $(element);
	var id = ele.prop("id");
	$.messager.confirm('确认','您确定删除?',function(r){
	    if (r){
	        var option = {
	        		"cccId" : id
	        };
	        $.post(basePath + "n/goodsCategoryConfig/deleteGoodsCategoryConfig", option, function(data){
	        	if(data.flag == "1"){
	        		$.messager.show({
						title:'提示',
						msg:'删除品类成功',
						timeout:3000,
						showType:'slide'
					});
	        		$("#goodCategoryConfigList").datagrid("reload");
	        	}else{
	        		$.messager.show({
						title:'提示',
						msg:data.message,
						timeout:3000,
						showType:'slide'
					});
	        		$("#goodCategoryConfigList").datagrid("reload");
	        	}
	        });
	    }
	});
}*/
/*function editGoodsCategoryConfig(element){//编辑品类
	var ele = $(element);
	var id = ele.prop("id");
	var option = {
			"cccId" : id
	};
	$.post(basePath + "n/goodsCategoryConfig/findGoodsCategoryConfigById", option, function(data){
		$("#editCategoryfm").find("#categoryCode").val(data.categoryCode);
		$("#editCategoryfm").find("#categoryName").val(data.categoryName);
		$("#editCategoryfm").find("#id").val(data.id);
		$("#editGoodsCategoryConfigdlg").dialog("open").dialog("setTitle", "编辑品类");
	});
}*/
/*function linkPosition(element){//关联职务
	var ele = $(element);
	var id = ele.prop("id");
//	alert(id);
	$("#linkPositionDialog").find("#goodsCategoryId").val(id);
}
function editCategoryPosition(element){//编辑职务
	var ele = $(element);
	var id = ele.prop("id");
	alert(id);
}
function deleteCategoryPosition(element){//删除职务
	var ele = $(element);
	var id = ele.prop("id");
	alert(id);
}*/