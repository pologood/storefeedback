;(function(){
    $(function(){
        function loadData(){
            $("#feedbackList").datagrid({
                title: "缺断货反馈列表",
                toolbar: "#toolbar",
                height : document.body.clientHeight*0.98,
                url : basePath + 'n/feedback/list',
                columns: [[
                           {
                               checkbox:true
                           },
                           {
                               field: 'sponsorEmployeeName',
                               title: '发起人',
                               align : 'center',
                               width:120,
                               styler: function(value,row,index){
                                    return 'font-size:12px;';
                                }
                           },
                           {
                               field: 'store_name',
                               title: '门店',
                               align : 'center',
                               width:120,
                               styler: function(value,row,index){
                                    return 'font-size:12px;';
                                }
                           },
                           {
                               field: 'division_desc',
                               title: '分部',
                               align : 'center',
                               width:120,
                               styler: function(value,row,index){
                                    return 'font-size:12px;';
                                }
                           },
                           {
                               field: 'second_division_des',
                               title: '二级分部',
                               align : 'center',
                               width:120,
                               styler: function(value,row,index){
                                    return 'font-size:12px;';
                                }
                           },
                           {
                               field: 'region_des',
                               title: '大区',
                               align : 'center',
                               width:120,
                               styler: function(value,row,index){
                                    return 'font-size:12px;';
                                }
                           },
                           {
                               field: 'className1',
                               title: '一级品类',
                               align : 'center',
                               width:120,
                               styler: function(value,row,index){
                                    return 'font-size:12px;';
                                }
                           },
                           {
                               field : 'className2',
                                title : '二级品类',
                                align : 'center',
                                width : 80,
                                sortable : true,
                                styler : function(value, row, index) {
                                    return 'font-size:12px;';
                                }
                           },
                           {
                               field : 'cnText',
                               title : '品牌',
                               align : 'center',
                               width : 80,
                               sortable : true,
                               styler : function(value, row, index) {
                                   return 'font-size:12px;';
                               }
                           },
                           {
                               field:'goods_name',
                               title:'商品名',
                               align : 'center',
                               width:200,
                               styler: function(value,row,index){
                                    return 'font-size:12px;';
                                }
                           },
                           {
                               field:'categoryName',
                               title:'缺货类别',
                               align : 'center',
                               width:200,
                               styler: function(value,row,index){
                                   return 'font-size:12px;';
                               }
                           },
                           {
                               field:'anticipatedDatesSoldOut',
                               title:'预计售完日期',
                               align : 'center',
                               width:200,
                               styler: function(value,row,index){
                                   return 'font-size:12px;';
                               }
                           },
                           {
                               field:'saleOutDate',
                               title:'最高售完天数',
                               align : 'center',
                               width:200,
                               styler: function(value,row,index){
                                   return 'font-size:12px;';
                               }
                           },
                           {
                               field:'quantity',
                               title:'数量',
                               align : 'center',
                               width:200,
                               styler: function(value,row,index){
                                   return 'font-size:12px;';
                               }
                           },
                           {
                               field:'createTime',
                               title:'创建时间',
                               align : 'center',
                               width:200,
                               styler: function(value,row,index){
                                   return 'font-size:12px;';
                               },
                               formatter : function(value){
                                   var d = new Date(value);
                                   return d.format("yyyy-MM-dd HH:mm:ss");
                               }
                           },
                           {
                               field:'storeName',
                               title:'门店名',
                               align : 'center',
                               width:200,
                               styler: function(value,row,index){
                                   return 'font-size:12px;';
                               }
                           },{
                               field:'fid',
                               title:'操作',
                               align : 'center',
                               width:200,
                               styler: function(value,row,index){
                                    return 'font-size:12px;';
                                },
                               formatter : function(value){
                                   return "<a href='javascript:void(0);' fid='" + value + "' onclick='showFeedbackRecord(this);'>查看反馈信息记录</a>";
                               }
                           }
                           ]],
                loadMsg: "数据加载中...",
                pagination: true,
                rownumbers: true,
                striped: true,
                pageSize : 20,
                queryParams: {
                    _orderBy : 'order by createTime desc'
                }
            });
        }

        function initEvent(){
            $("#searchBtn").click(function(){//工具栏上的“查询”按钮
                $("#feedbackList").datagrid({
                    queryParams: {
                        sponsorEmployeeName : $("#sponsorEmployeeName").val(),
                        storeCode : $('#store_combo').siblings('span').find('.combo-value').val(),
                        _orderBy : 'order by createTime desc'
                    }
                });
            }); 
            $().click(function(){//每一行记录的查看链接
                
            })
        }
        function SDivisionStore(fDivisionCode){
            $('#secondDivision_combo').combobox(
                    {
                         url:basePath + 'n/secondDivision/findSencondDivisions?first_division_code='+fDivisionCode,
                         valueField:'second_division_code',
                         textField:'second_division_des',
                         onSelect:function(record){
                             //alert(record.second_division_code);
                             $('#store_combo').combobox(
                                     {
                                          url:basePath + 'n/store/findStores?first_division_code='+fDivisionCode
                                              +'&&second_division_code='+record.second_division_code,
                                          valueField:'store_code',
                                          textField:'store_name',
                                          onSelect:function(record){
                                          }
                                      });
                             
                         }
                     });
        }
        function init(){
            loadData();
            initEvent();
            $('#regions_combo').combobox(
                    {
                         url:basePath + 'n/region/findRegions',
                         valueField:'region_code',
                         textField:'region_des',
                         onChange:function(record,o){
                             //alert(record+"-------"+o);
                             $('#division_combo').combobox(
                                     {
                                          url:basePath + 'n/division/findDivisions?region_code='+record,
                                          valueField:'division_code',
                                          textField:'division_desc',
                                          onSelect:function(record){
                                              //alert(record.division_code);
                                              SDivisionStore(record.division_code);
                                          }
                                      });
                             
                         }
                     });
        }
        
        init();
        
    });
})();
function showFeedbackRecord(el){//查看角色资源
    var ele = $(el);
    var fid = ele.attr("fid");
    $("#feedbackRecordId").val(fid);
    $("#feedBackRecordList").datagrid({
        title: "反馈信息列表",
        toolbar: "#FeedBackRecordListToolBar",
        height : document.body.clientHeight*0.98,
        url : basePath + 'n/feedbackRecord/list',
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
                       field:'feedbackPersonEmployeeId',
                       title:'反馈人员工编号',
                       align : 'center',
                       width:100,
                       styler: function(value,row,index){
                            return 'font-size:12px;';
                        }
                   },
                   {
                       field:'feedbackPersonEmployeeName',
                       title:'反馈人姓名',
                       align : 'center',
                       width:200,
                       styler: function(value,row,index){
                            return 'font-size:12px;';
                        }
                   },
                   {
                       field:'feedbackContent',
                       title:'反馈内容',
                       align : 'center',
                       width:100,
                       styler: function(value,row,index){
                            return 'font-size:12px;';
                        }
                   },
                   {
                       field:'feedbackTime',
                       title:'反馈时间',
                       align : 'center',
                       width:100,
                       styler: function(value,row,index){
                            return 'font-size:12px;';
                       },
                       formatter : function(value){
                           var d = new Date(value);
                           return d.format("yyyy-MM-dd HH:mm:ss");
                       }
                   }
                   ]],
        loadMsg: "数据加载中...",
        pagination: true,
        rownumbers: true,
        striped: true,
        pageSize : 20,
        queryParams : {
            feedbackId : fid
        }
    });
    $("#showFeedBackRecord").dialog("open");
    
}
