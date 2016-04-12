;
(function() {
    $(function() {
    	var buyType = $("#buyType").val();
    	var getType;
    	if(buyType == 1){
    		getType = "集采";
    	}else if(buyType == 0){
    		getType = "地采";
    	}
    	var categoryCode = $("#categoryCode").val();
    	var brandCode = $("#brandCode").val();
    	var brandName = $("#brandName").val();

        $("#goodsList").datagrid({
        	
        	rowStyler:function(index,row){           
        		if (row.is_handler==5){               
        			return 'background-color:red;';           
        		}     
        		if (row.is_handler==4){               
        			return 'background-color:yellow;';           
        		}
        		if (row.is_handler==3){               
        			return 'background-color:blue;';           
        		}
        		if (row.is_handler==2){               
        			return 'background-color:green;';           
        		}
        	},
        	
            title : "["+ brandName + "]各型号" + getType + "缺断货信息列表",
            toolbar : "#tb",
            height : document.body.clientHeight * 0.98,
            url : basePath + 'n/checkSapFeedback/getByEmpCategoryBuytypeBrand',
            loadMsg : "请稍后 ,数据加载中...",
            pagination : false,
            rownumbers : true,
            striped : true,
            pageSize : 500,
            scrollbarSize : 20,
            sortName : 'is_handler',
            sortOrder: 'asc',
            remoteSort : false,
            queryParams : {
            	buyType : buyType,
            	categoryCode : categoryCode,
            	brandCode : brandCode
            },
            columns : [ [ {
                checkbox : false,
                width : $(this).width() * 0.1
            }, {
                field : 'is_handler',
                title : '处理的结果',
                align : 'center',
                hidden : true,
                width : $(this).width() * 0.3,
                fitColumns : true,
                styler : function(value, row, index) {
                    return 'font-size:12px;';
                }
            }, {
                field : 'goodsName',
                title : '商品型号',
                align : 'center',
                width : $(this).width() * 0.3,
                fitColumns : true,
                styler : function(value, row, index) {
                    return 'font-size:12px;';
                },
                formatter: function(value,row,index){
                	var goodsCode = row.goodsCode;
                	var id = row.id;
                	var ids = id.split("#");
                	return '<a href="n/checkSapFeedback/goToCategoryBrandGoodsDivisionPage?categoryCode='+ categoryCode +'&buyType='+ buyType +'&brandCode='+ brandCode +'&goodsCode='+ goodsCode +'&goodsName='+ value +'&goodsId='+ ids[0] +'&dataparkId='+ ids[1] +'&record='+ ids[2] +'">'+ value +'</a>';
				}
            }, {
                field : 'feedbackNum',
                title : '库存百分率(%)',
                align : 'center',
                width : $(this).width() * 0.15,
                sortable : true,
                styler : function(value, row, index) {
                    return 'font-size:12px;';
                }
            } ] ]
        });

    });

})();
