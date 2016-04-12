;
(function() {
    $(function() {
    	var flag = $("#buyTypeAuthRole").text();
        function loadData() {
            // ======category列表=============================================
            $("#categoryList").datagrid({

            	//datagrid加载完后合并指定单元格   
        		onLoadSuccess : function(data){  
        		    var arr =[{mergeFiled:"categoryName",premiseFiled:"PROJECTID"}    //合并列的field数组及对应前提条件filed（为空则直接内容合并）   
        		             ];   
        		    var dg = $("#categoryList");   //要合并的datagrid中的表格id   
        		    var rowCount = dg.datagrid("getRows").length;  
        		    var cellName;  
        		    var span;  
        		    var perValue = "";  
        		    var curValue = "";  
        		    var perCondition="";  
        		    var curCondition="";  
        		    var flag1=true;  
        		    var condiName="";  
        		    var length = arr.length - 1;  
        		    for (i = length; i >= 0; i--) {  
        		        cellName = arr[i].mergeFiled;  
        		        condiName=arr[i].premiseFiled;  
        		        if(!$.isEmptyObject(condiName)){  
        		            flag1=false;  
        		        }  
        		        perValue = "";  
        		        perCondition="";  
        		        span = 1;  
        		        for (row = 0; row <= rowCount; row++) {  
        		            if (row == rowCount) {  
        		                curValue = "";  
        		                curCondition="";  
        		            } else {  
        		                curValue = dg.datagrid("getRows")[row][cellName];  
        		                /* if(cellName=="ORGSTARTTIME"){//特殊处理这个时间字段 
        		                    curValue =formatDate(dg.datagrid("getRows")[row][cellName],""); 
        		                } */  
        		                if(!flag1){  
        		                    curCondition=dg.datagrid("getRows")[row][condiName];  
        		                }  
        		            }  
        		            if (perValue == curValue&&(flag1||perCondition==curCondition)) {  
        		                span += 1;  
        		            } else {  
        		                var index = row - span;  
        		                dg.datagrid('mergeCells', {  
        		                    index : index,  
        		                    field : cellName,  
        		                    rowspan : span,  
        		                    colspan : null  
        		                });  
        		                span = 1;  
        		                perValue = curValue;  
        		                if(!flag1){  
        		                    perCondition=curCondition;  
        		                }  
        		            }  
        		        }  
        		    }  
        		},  
            	
                title : "品类信息列表",
                toolbar : "#tb",
                height : document.body.clientHeight * 0.98,
                url : basePath + 'n/aspFeedBack/getByEmp',
                loadMsg : "数据加载中...",
                pagination : true,
                rownumbers : true,
                striped : true,
                singleSelect : true,
                pageSize : 20,
        		/*rowStyler: function(index,row){
        			if (flag == '10100001' || flag == '10110100'&&index % 2 ==1){
        				return 'display:none;';
        			}
        		},*/
        		
                columns : [ [ {
                    checkbox : false
                }, {
                    field : 'categoryName',
                    title : '品类',
                    halign : "center",//标题居中
                    align : 'center',//内容居中
                    width : 200,
                    hidden : false,
                    fitColumns : true,
                    styler : function(value, row, index) {
                        return 'font-size:12px;';
                    }
                }, {
                	field : 'categoryCode',//集采|地采
                    title : '采购形式',
                    align : 'center',
                    width : 300,
                    styler : function(value, row, index) {
                        return 'font-size:12px;';                    
                    },
					formatter: function(value,row,index){
						if(flag == '11001110' || flag == '11111100'){
							if(index % 2 == 0){
								return '<a href="goToCategoryBrandPage?categoryCode='+ value +'&buyType=0">地采</a>';
							}else{
								return '<a href="goToCategoryBrandPage?categoryCode='+ value +'&buyType=1">集采</a>';
							}
						}
						if(flag == '10100001' || flag == '10110100'){
							return '<a href="goToCategoryBrandPage?categoryCode='+ value +'&buyType=0">地采</a>';
						}
					}
                }, {
                	field : '1',
                    title : '品牌数量',
                    align : 'center',
                    width : 150,
                    //分部隐藏
                    hidden : false,
                    styler : function(value, row, index) {
                        return 'font-size:12px;';
                    },
                    formatter: function(value,row,index){
                    	if(flag == '11001110' || flag == '11111100'){
							if(index % 2 == 0){
								return row.dcnum;
							}else{
								return row.jcnum;
							}
						}
						if(flag == '10100001' || flag == '10110100'){
							return row.dcnum;
						}
					}
                } ] ]
            });
        }

        function init() {
            loadData();
            initEvent();
        }
        init();
    });
})();