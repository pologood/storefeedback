;
(function() {
    $(function() {
        $("#goodsBrandList").datagrid({
            title : "商品品牌列表",
            toolbar : "#goodsBrandToolBar",
            height : document.body.clientHeight * 0.98,
            url : basePath + 'n/goodsBrand/findByPage',
            loadMsg : "请稍后 ,数据加载中...",
            pagination : true,
            rownumbers : true,
            striped : true,
            pageSize : 20,
            queryParams : {

            },
            columns : [ [ {
                checkbox : true,
                width : $(this).width() * 0.1
            }, {
                field : 'brandCode',
                title : '品牌编码',
                width : $(this).width() * 0.1,
                fitColumns : true,
                styler : function(value, row, index) {
                    return 'font-size:12px;';
                }
            }, {
                field : 'cnText',
                title : '中文描述',
                align : 'center',
                width : $(this).width() * 0.1,
                sortable : true,
                styler : function(value, row, index) {
                    return 'font-size:12px;';
                }
            }, {
                field : 'enText',
                title : '英文描述',
                align : 'center',
                width : $(this).width() * 0.1,
                sortable : true,
                styler : function(value, row, index) {
                    return 'font-size:12px;';
                }
            }, {
                field : 'brandClass',
                title : '品牌类型',
                align : 'center',
                width : $(this).width() * 0.1,
                sortable : true,
                styler : function(value, row, index) {
                    return 'font-size:12px;';
                }
            }, {
                field : 'updateFlag',
                title : '更新标志',
                align : 'center',
                width : $(this).width() * 0.1,
                sortable : true,
                styler : function(value, row, index) {
                    return 'font-size:12px;';
                }
            }, {
                field : 'createTime',
                title : '创建时间',
                align : 'center',
                width : 120,
                sortable : true,
                styler : function(value, row, index) {
                    return 'font-size:12px;';
                },
                formatter : function(value) {
                    var d = new Date(value);
                    return d.format("yyyy-MM-dd HH:mm:ss");
                }
            } ] ]
        });

        /**
         * 应用查询
         */
        $("#goodsBrandSearch").click(function() {
            $('#goodsBrandList').datagrid({
                queryParams : {
                    brandCode : $("#brandCode").val(),
                    cnText : $("#cnText").val(),
                    brandClass : $("#brandClass").val()
                }
            });
        });
    });

})();
