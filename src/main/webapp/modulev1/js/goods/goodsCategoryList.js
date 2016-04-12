;
(function() {
    $(function() {
        $("#goodsCategoryList").datagrid({
            title : "商品品类列表",
            toolbar : "#goodsCategoryToolBar",
            height : document.body.clientHeight * 0.98,
            url : basePath + 'n/goodsCategory/findByPage',
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
                field : 'classCode',
                title : '分类代码',
                width : $(this).width() * 0.1,
                // hidden : true,
                fitColumns : true,
                styler : function(value, row, index) {
                    return 'font-size:12px;';
                }
            }, {
                field : 'className',
                title : '分类名称',
                align : 'center',
                width : $(this).width() * 0.1,
                sortable : true,
                styler : function(value, row, index) {
                    return 'font-size:12px;';
                }
            }, {
                field : 'classLevel',
                title : '分类级别',
                align : 'center',
                width : $(this).width() * 0.1,
                sortable : true,
                styler : function(value, row, index) {
                    return 'font-size:12px;';
                }
            }, {
                field : 'isLeaf',
                title : '是否末级',
                align : 'center',
                width : $(this).width() * 0.1,
                sortable : true,
                styler : function(value, row, index) {
                    return 'font-size:12px;';
                }
            /*
             * , formatter : function(value) { if (1 == value) { return "启用"; }
             * else if (0 == value) { return "<font color='red'>停用</font>"; } }
             */
            }, {
                field : 'divisionCode',
                title : '事业部代码',
                align : 'center',
                width : $(this).width() * 0.1,
                sortable : true,
                styler : function(value, row, index) {
                    return 'font-size:12px;';
                }
            }, {
                field : 'divisionName',
                title : '事业部名称',
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
         * 查询
         */
        $("#goodsCategorySearch").click(function() {
            $('#goodsCategoryList').datagrid({
                queryParams : {
                    classCode : $("#classCode").val(),
                    className : $("#className").val()
                }
            });
        });
    });

})();
