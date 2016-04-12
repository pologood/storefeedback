;
(function() {
    $(function() {
    	var buyType = $("#buyType").html();
    	var categoryCode = $("#categoryCode").html();

        $("#goodsBrandList").datagrid({
            title : "品牌信息列表",
            toolbar : "#tb",
            height : document.body.clientHeight * 0.98,
            url : basePath + 'n/aspFeedBack/getByEmpCategoryBuytype',
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
				    return '<a href="n/aspFeedBack/goToCategoryBrandGoodsPage?categoryCode='+ categoryCode +'&buyType='+ buyType +'&brandCode='+ brandCode +'">'+ value +'</a>';
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
