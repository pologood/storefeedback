;
(function() {
    $(function() {
    	var buyType = $("#buyType").html();
    	var categoryCode = $("#categoryCode").html();
    	var brandCode = $("#brandCode").html();
    	
        $("#goodsList").datagrid({
        	
        	rowStyler:function(index,row){           
        		if (row.is_handler==4){               
        			return 'background-color:green;';           
        		}     
        		if (row.is_handler==3){               
        			return 'background-color:yellow;';           
        		}
        	},
        	
            title : "商品列表",
            toolbar : "#tb",
            height : document.body.clientHeight * 0.96,
            url : basePath + 'n/aspFeedBack/getByEmpCategoryBuytypeBrand',
            loadMsg : "请稍后 ,数据加载中...",
            pagination : false,
            rownumbers : true,
            striped : true,
            pageSize : 500,
            scrollbarSize : 20,
            queryParams : {
            	buyType : buyType,
            	categoryCode : categoryCode,
            	brandCode : brandCode
            },
            sortName : 'is_handler',
            sortOrder: 'asc',
            remoteSort : false,
//            multiSort : true ,
            columns : [[{
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
                	return '<a href="n/aspFeedBack/goToCategoryBrandGoodsDivisionPage?categoryCode='+ categoryCode +'&buyType='+ buyType +'&brandCode='+ brandCode +'&goodsCode='+ goodsCode +'&goodsId='+ ids[0] +'&dataparkId='+ ids[1] +'&record='+ ids[2] +'">'+ value +'</a>';
				}
            }, {
                field : 'feedbackNum',
                title : '库存周转率(%)',
                align : 'center',
                width : $(this).width() * 0.1,
                sortable : true,
                styler : function(value, row, index) {
                    return 'font-size:12px;';
                }
            } ] ]
        });

    });

})();
