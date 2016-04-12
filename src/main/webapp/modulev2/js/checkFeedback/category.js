;
(function() {
    $(function() {
    	var flag = $("#buyTypeAuth").text();
    	var year = $("#year").text();
    	var month = $("#month").text();
    	var weekCount = $("#weekCount").text();
    	var checkMessage = $("#checkMessage").text();

        function loadData() {
            // ======category列表=============================================
            $("#categoryList").datagrid({

                title : "各品类缺断货信息列表",
                toolbar : "#tb",
                height : document.body.clientHeight * 0.98,
                url : basePath + 'n/checkSapFeedback/getByEmp',
                loadMsg : "数据加载中...",
                pagination : false,
                rownumbers : true,
                striped : true,
                singleSelect : true,
//                pageSize : 20,
                queryParams: {
            		year : year,
            		month : month,
            		weekCount : weekCount
            	},
            	//datagrid加载完后合并指定单元格   
        		onLoadSuccess : function(data){  
                    $.messager.progress('close');
                    if(data.hasOwnProperty("error_code")){
                    	$.messager.alert('提示', '考核时间选择不正确,请重新选择', 'info',function (){
                    		window.location = basePath + 'n/checkSapFeedback/goToCheckWeekPage';
                    	});
                	} 
        			
        			//合并单元格
        		   /* var arr =[{mergeFiled:"categoryName",premiseFiled:"PROJECTID"}    //合并列的field数组及对应前提条件filed（为空则直接内容合并）   
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
        		                 if(cellName=="ORGSTARTTIME"){//特殊处理这个时间字段 
        		                    curValue =formatDate(dg.datagrid("getRows")[row][cellName],""); 
        		                }   
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
        		    }*/
        		}, 

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
						if(flag == '1'){
//						if(flag == '11001110' || flag == '11111100'){
//							if(index % 2 == 0){
//								return '<a href="goToCategoryBrandPage?categoryCode='+ value + '&categoryName=' + row.categoryName +'&buyType=0">地采</a>';
//							}else{
								return '<a href="goToCategoryBrandPage?categoryCode='+ value + '&categoryName=' + row.categoryName +'&buyType=1">集采</a>';
//							}
						}
						if(flag == '0'){
//							if(flag == '10100001' || flag == '10110100'){
							return '<a href="goToCategoryBrandPage?categoryCode='+ value + '&categoryName=' + row.categoryName +'&buyType=0">地采</a>';
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
                    	if(flag == '1'){
//                    		if(flag == '11001110' || flag == '11111100'){
//							if(index % 2 == 0){
//								return row.dcnum;
//							}else{
								return row.jcnum;
//							}
						}
						if(flag == '0'){
//							if(flag == '10100001' || flag == '10110100'){
							return row.dcnum;
						}
					}
                } ] ]
            });
        }

        function init() {
        	if(checkMessage == ""){
        		loadData();
        	} else {
        		$.messager.show({
        			title : '提示',
        			msg : checkMessage,
        			timeout : 5000,
        			showType : 'slide'
        		});
        		
        		window.setTimeout(function(){
        			window.location = basePath + 'n/checkSapFeedback/goToCheckWeekPage';
        		},3000);
        	}
            initEvent();
        }
        init();
    });
})();