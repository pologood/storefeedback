;
(function() {
    $(function() {
        function loadData() {
            // ======原因列表=============================================
            $("#resultNoReasonList").datagrid({
                title : "不补货原因列表",
                toolbar : "#tb",
                height : document.body.clientHeight * 0.98,
                url : basePath + 'n/resultNoReason/findList',
                loadMsg : "数据加载中...",
                pagination : true,
                rownumbers : true,
                striped : true,
                singleSelect : true,
                pageSize : 20,
                columns : [ [ {
                    checkbox : true
                }, {
                    field : 'resultNoCode',
                    title : '编码',
                    width : 200,
                    hidden : true,
                    fitColumns : true,
                    styler : function(value, row, index) {
                        return 'font-size:12px;';
                    }
                }, {
                    field : 'resultNoName',
                    title : '名称',
                    align : 'center',
                    width : 300,
                    styler : function(value, row, index) {
                        return 'font-size:12px;';
                    }
                }, {
                    field : 'isUsing',
                    title : '启用停用',
                    align : 'center',
                    width : 100,
                    hidden : true,
                    styler : function(value, row, index) {
                        return 'font-size:12px;';
                    }
                }, {
                    field : 'sortOrder',
                    title : '排序',
                    align : 'center',
                    hidden : true,
                    width : 300,
                    styler : function(value, row, index) {
                        return 'font-size:12px;';
                    }
                } ] ]
            });
        }

        function initEvent() {
            // 不补货原因列表查询按钮
            $("#searchBtn").click(function() {
                $("#resultNoReasonList").datagrid({
                    queryParams : {
                        _orderBy : "order by result_no_code asc"
                    }
                });
            });
            // 添加不补货原因按钮
            $("#addResultNoReasonBtn").click(function() {
                $("#addResultNoReasondlg").dialog("open").dialog("setTitle", "添加不补货原因");
            });
            // 添加不补货原因保存
            $("#addResultNoReasonSaveBtn").click(function() {
                $.messager.progress();
                $('#addResultNoReasonfm').form('submit', {
                    url : basePath + "n/resultNoReason/insert",
                    onSubmit : function() {
                        var isValid = $(this).form('validate');
                        if (!isValid) {
                            $.messager.progress('close');
                        }
                        return isValid;
                    },
                    success : function() {
                        $.messager.progress('close');
                        $('#addResultNoReasondlg').dialog('close');
                        $("#resultNoReasonList").datagrid("reload");
                        $.messager.show({
                            title : '提示',
                            msg : '添加不补货原因成功',
                            timeout : 3000,
                            showType : 'slide'
                        });
                    }
                });
            });
            // 编辑不补货原因按钮
            $("#editResultNoReasonBtn").click(function() {
                var selectRows = $("#resultNoReasonList").datagrid('getChecked');
                if (selectRows.length == 0) {
                    $.messager.alert('提示', '请选择您要修改的行!', 'info');
                    return false;
                } else if (selectRows.length > 1) {
                    $.messager.alert('提示', '一次只能编辑一条数据!', 'info');
                    return false;
                } else {
                    $("#editResultNoReasonfm").find("#isUsing").val(selectRows[0].isUsing);
                    $("#editResultNoReasonfm").find("#sortOrder").val(selectRows[0].sortOrder);
                    $("#editResultNoReasonfm").find("#resultNoCode").val(selectRows[0].resultNoCode);
                    $("#editResultNoReasonfm").find("#resultNoName").val(selectRows[0].resultNoName);
                    $("#editResultNoReasondlg").dialog("open").dialog("setTitle", "编辑不补货原因");
                }
            });
            // 编辑不补货原因保存按钮
            $("#editResultNoReasonSaveBtn").click(function() {
                $.messager.progress();
                $('#editResultNoReasonfm').form('submit', {
                    url : basePath + "n/resultNoReason/update",
                    onSubmit : function() {
                        var isValid = $(this).form('validate');
                        if (!isValid) {
                            $.messager.progress('close');
                        }
                        return isValid;
                    },
                    success : function() {
                        $.messager.progress('close');
                        $('#editResultNoReasondlg').dialog('close');
                        $("#resultNoReasonList").datagrid("reload");
                        $.messager.show({
                            title : '提示',
                            msg : '更新不补货原因成功',
                            timeout : 3000,
                            showType : 'slide'
                        });
                    }
                });
            });
            // 删除不补货原因按钮
            $("#deleteResultNoReasonBtn").click(function() {
                var selectRows = $("#resultNoReasonList").datagrid('getChecked');
                if (selectRows.length == 0) {
                    $.messager.alert('提示', '请选择您要修改的行!', 'info');
                    return false;
                } else if (selectRows.length > 1) {
                    $.messager.alert('提示', '一次只能编辑一条数据!', 'info');
                    return false;
                } else {
                    $.messager.confirm('确认', '您确定删除?', function(r) {
                        if (r) {
                            var option = {
                                "resultNoCode" : selectRows[0].resultNoCode
                            };
                            $.post(basePath + "n/resultNoReason/delete", option, function(data) {
                                if (data.flag == "1") {
                                    $.messager.show({
                                        title : '提示',
                                        msg : '删除不补货原因成功',
                                        timeout : 3000,
                                        showType : 'slide'
                                    });
                                    $("#resultNoReasonList").datagrid("reload");
                                } else {
                                    $.messager.show({
                                        title : '提示',
                                        msg : data.message,
                                        timeout : 3000,
                                        showType : 'slide'
                                    });
                                    $("#resultNoReasonList").datagrid("reload");
                                }
                            });
                        }
                    });
                }
            });
        }
        function init() {
            loadData();
            initEvent();
        }
        init();
    });
})();