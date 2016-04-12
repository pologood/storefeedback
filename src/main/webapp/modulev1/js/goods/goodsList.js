;
(function() {
    $(function() {
        $("#goodsList").datagrid({
            title : "商品列表",
            toolbar : "#goodsToolBar",
            height : document.body.clientHeight * 0.98,
            url : basePath + 'n/goods/findByPage',
            loadMsg : "请稍后 ,数据加载中...",
            pagination : true,
            rownumbers : true,
            striped : true,
//            nowrap:false,
            pageSize : 20,
            queryParams : {

            },
            frozenColumns : [ [ {
                checkbox : true,
                width : $(this).width() * 0.1
            }, {
                field : 'goodsCode',
                title : '商品编码',
                width : $(this).width() * 0.1,
                fitColumns : true,
                styler : function(value, row, index) {
                    return 'font-size:12px;';
                }
            }, {
                field : 'goodsBarcode',
                title : '商品条码',
                align : 'center',
                width : $(this).width() * 0.1,
                sortable : true,
                styler : function(value, row, index) {
                    return 'font-size:12px;';
                }
            }, {
                field : 'goodsName',
                title : '商品名称',
                align : 'center',
                width : $(this).width() * 0.1,
                sortable : true,
                styler : function(value, row, index) {
                    return 'font-size:12px;';
                }
            } ] ],
            columns : [ [ {
                field : 'specificationsModel',
                title : '规格型号',
                align : 'center',
                width : $(this).width() * 0.1,
                sortable : true,
                styler : function(value, row, index) {
                    return 'font-size:12px;';
                }
            }, {
                field : 'goodsCategory',
                title : '商品分类',
                align : 'center',
                width : $(this).width() * 0.1,
                sortable : true,
                styler : function(value, row, index) {
                    return 'font-size:12px;';
                }
            }, {
                field : 'brandName',
                title : '品牌',
                align : 'center',
                width : $(this).width() * 0.1,
                sortable : true,
                styler : function(value, row, index) {
                    return 'font-size:12px;';
                }
            }, {
                field : 'extendedWarrantyPriceFloor',
                title : '延保价格上限',
                align : 'center',
                width : $(this).width() * 0.1,
                sortable : true,
                styler : function(value, row, index) {
                    return 'font-size:12px;';
                }
            }, {
                field : 'extendedWarrantyPriceCap',
                title : '延保价格下限',
                align : 'center',
                width : $(this).width() * 0.1,
                sortable : true,
                styler : function(value, row, index) {
                    return 'font-size:12px;';
                }
            }, {
                field : 'outputTaxRate',
                title : '销项税率',
                align : 'center',
                width : $(this).width() * 0.1,
                sortable : true,
                styler : function(value, row, index) {
                    return 'font-size:12px;';
                }
            }, {
                field : 'unitsOfMeasurement',
                title : '计量单位',
                align : 'center',
                width : $(this).width() * 0.1,
                sortable : true,
                styler : function(value, row, index) {
                    return 'font-size:12px;';
                }
            }, {
                field : 'unitOfMeasureText',
                title : '度量单位文本',
                align : 'center',
                width : $(this).width() * 0.1,
                sortable : true,
                styler : function(value, row, index) {
                    return 'font-size:12px;';
                }
            }, {
                field : 'whetherBusinessGifts',
                title : '是否经营赠品',
                align : 'center',
                width : $(this).width() * 0.1,
                sortable : true,
                styler : function(value, row, index) {
                    return 'font-size:12px;';
                }
            }, {
                field : 'lotId',
                title : '批次标识',
                align : 'center',
                width : $(this).width() * 0.1,
                sortable : true,
                styler : function(value, row, index) {
                    return 'font-size:12px;';
                }
            }, {
                field : 'productAttributes',
                title : '商品属性',
                align : 'center',
                width : $(this).width() * 0.1,
                sortable : true,
                styler : function(value, row, index) {
                    return 'font-size:12px;';
                }
            }, {
                field : 'goodsClass',
                title : '商品类型',
                align : 'center',
                width : $(this).width() * 0.1,
                sortable : true,
                styler : function(value, row, index) {
                    return 'font-size:12px;';
                }
            }, {
                field : 'placeOfOrigin',
                title : '产地',
                align : 'center',
                width : $(this).width() * 0.1,
                sortable : true,
                styler : function(value, row, index) {
                    return 'font-size:12px;';
                }
            }, {
                field : 'goodsWeight',
                title : '重量（含包装)',
                align : 'center',
                width : $(this).width() * 0.1,
                sortable : true,
                styler : function(value, row, index) {
                    return 'font-size:12px;';
                }
            }, {
                field : 'goodsHeight',
                title : '高(mm)',
                align : 'center',
                width : $(this).width() * 0.1,
                sortable : true,
                styler : function(value, row, index) {
                    return 'font-size:12px;';
                }
            }, {
                field : 'categoryLevel1Name',
                title : '一级分类',
                align : 'center',
                width : $(this).width() * 0.1,
                sortable : true,
                styler : function(value, row, index) {
                    return 'font-size:12px;';
                }
            }, {
                field : 'categoryLevel2Name',
                title : '二级分类',
                align : 'center',
                width : $(this).width() * 0.1,
                sortable : true,
                styler : function(value, row, index) {
                    return 'font-size:12px;';
                }
            }, {
                field : 'categoryLevel3Name',
                title : '三级分类',
                align : 'center',
                width : $(this).width() * 0.1,
                sortable : true,
                styler : function(value, row, index) {
                    return 'font-size:12px;';
                }
            }, {
                field : 'selling',
                title : '产品卖点',
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
        $("#goodsSearch").click(function() {
            $('#goodsList').datagrid({
                queryParams : {
                    goodsCode : $("#goodsCode").val(),
                    goodsBarcode : $("#goodsBarcode").val(),
                    goodsName : $("#goodsName").val()
                }
            });
        });
    });

})();
