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
    	var categoryName = $("#categoryName").val();

        $("#goodsBrandList").datagrid({
            title : "["+ categoryName + "]各品牌" + getType + "缺断货信息列表",
            toolbar : "#tb",
            height : document.body.clientHeight * 0.98,
            url : basePath + 'n/checkSapFeedback/getByEmpCategoryBuytype',
            loadMsg : "请稍后 ,数据加载中...",
            pagination : true,
            rownumbers : true,
            striped : true,
            pageSize : 20,
            queryParams : {
            	//-------------	参数需不需要传入再
            	buyType : buyType,
            	categoryCode : categoryCode
            },
            columns : [ [ {
                checkbox : false,
                width : $(this).width() * 0.1
            }, {
                field : 'brandName',
                title : '品牌',
                align : 'center',
                width : $(this).width() * 0.1,
                fitColumns : true,
                styler : function(value, row, index) {
                    return 'font-size:12px;';
                },
                formatter: function(value,row,index){
                	var brandCode = row.brandCode;
				    return '<a href="n/checkSapFeedback/goToCategoryBrandGoodsPage?categoryCode='+ categoryCode +'&buyType='+ buyType +'&brandCode='+ brandCode +'&brandName='+ row.brandName + '">'+ value +'</a>';
				}
            }, {
                field : 'feedbackNum',
                title : '型号数量',
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
