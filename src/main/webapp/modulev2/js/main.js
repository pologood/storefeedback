var onlyOpenTitle="欢迎页";
var addNotClickFlag = true;
function tabClose()
{
	/*双击关闭TAB选项卡*/
	$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close',subtitle);
	});
	/*为选项卡绑定右键*/
	$(".tabs-inner").bind('contextmenu',function(e){
		
		$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY
		});

		var subtitle =$(this).children(".tabs-closable").text();

		$('#mm').data("currtab",subtitle);
		$('#tabs').tabs('select',subtitle);
		return false;
	});
}



//绑定右键菜单事件
function tabCloseEven() {

    $('#mm').menu({
        onClick: function (item) {
            closeTab(item.id);
        }
    });

    return false;
}

function removeCookieForTab(closeTabTitle){
	//处理tab
	var cstr = $.cookie("tabs");
	cstr = cstr || "";
	var tabsArr = cstr.split("&");
	var tabNew = [];
	$.each(tabsArr, function(i, tab){
		if(tab.indexOf(closeTabTitle) == -1){
			tabNew.push(tab);
		}
	});
	var str = tabNew.join("&");
	$.cookie("tabs", str);
	//处理tabTitle
	var idstr = $.cookie("tabIds");
	idstr =  idstr || "";
	var idArr = idstr.split("&");
	idArr.remove(closeTabTitle);
	var newIdstr = idArr.join("&");
	$.cookie("tabIds", newIdstr);
}

function refreshTab(index){
	var currentTab = $('#tabs').tabs('getTab', index);
	var iframe = $(currentTab.panel('options').content);
	var src = iframe.attr('src');
	var name = iframe.attr('name');
	var hideTitle = iframe.attr('hideTitle');
	var idstr = $.cookie("tabIds");
	idstr =  idstr || "";
	var idArr = idstr.split("&");
	if(idArr.containElem(hideTitle) && !addNotClickFlag){
		iframe.removeAttr("refresh");
		$('#tabs').tabs('update', {
			tab: currentTab,
			options: {
				content: "<iframe  scrolling='no' frameborder='0' src='"+src+"' name='"+name+"' style='width: 100%; height: 100%; overflow: hidden;'></iframe>"
			}
		}); 
	}
}

function closeTab(action)
{
    var alltabs = $('#tabs').tabs('tabs');
    var currentTab =$('#tabs').tabs('getSelected');
	var allTabtitle = [];
	$.each(alltabs,function(i,n){
		allTabtitle.push($(n).panel('options').title);
	});

	
    switch (action) {
        case "tabupdate":
            var iframe = $(currentTab.panel('options').content);
            var src = iframe.attr('src');
            var name = iframe.attr('name');
            $('#tabs').tabs('update', {
                tab: currentTab,
                options: {
                    content: "<iframe  scrolling='no' frameborder='0' src='"+src+"' name='"+name+"' style='width: 100%; height: 100%; overflow: hidden;'></iframe>"
                }
            });
            break;
        case "close":
            var currtab_title = currentTab.panel('options').title;
            $('#tabs').tabs('close', currtab_title);
            //removeCookieForTab(currtab_title);
            break;
        case "closeall":
            $.each(allTabtitle, function (i, n) {
                if (n != onlyOpenTitle){
                    $('#tabs').tabs('close', n);
                    //removeCookieForTab(n);
				}
            });
            break;
        case "closeother":
            var currtab_title = currentTab.panel('options').title;
            $.each(allTabtitle, function (i, n) {
                if (n != currtab_title && n != onlyOpenTitle)
				{
                    $('#tabs').tabs('close', n);
                    //removeCookieForTab(n);
				}
            });
            break;
        case "closeright":
            var tabIndex = $('#tabs').tabs('getTabIndex', currentTab);

            if (tabIndex == alltabs.length - 1){
                return false;
            }
            $.each(allTabtitle, function (i, n) {
                if (i > tabIndex) {
                    if (n != onlyOpenTitle){
                        $('#tabs').tabs('close', n);
                        //removeCookieForTab(n);
					}
                }
            });

            break;
        case "closeleft":
            var tabIndex = $('#tabs').tabs('getTabIndex', currentTab);
            if (tabIndex == 1) {
                return false;
            }
            $.each(allTabtitle, function (i, n) {
                if (i < tabIndex) {
                    if (n != onlyOpenTitle){
                        $('#tabs').tabs('close', n);
                        //removeCookieForTab(n);
					}
                }
            });

            break;
        case "exit":
            $('#closeMenu').menu('hide');
            break;
    }
}
;(function(){
	$(function(){
		
		function initEvent(){
			//退出登录
			$("#logout").click(function(){
				$.messager.confirm('确认','您确定要退出系统吗?',function(r){
				    if (r){
				    	/*$.removeCookie('tabs');
				    	$.removeCookie('tabIds');
				    	$.removeCookie('selectTitle');*/
				        window.location = basePath + "n/user/logout";
				    }
				});
			});
			
			$("#modifyPsd").click(function(){//修改密码按钮
				$("#modifyPwdDialog").dialog("open");
			});
			
			$("#modifyPwdSaveBtn").click(function(){//修改密码保存按钮
				var isValid = $("#modifyPwdFm").form("validate");
				if(isValid){
					var newPwd = $("#newPwd").val();
					var newPwdCF = $("#newPwdCF").val();
					if(newPwd != newPwdCF){
						$.messager.alert('提示','两次输入密码不一致！');
						return;
					}else{
						var option = {
								//employeeId : $("#currentEmployeeId").val(),
								password : $("#oldPwd").val(),
								newPassword : $("#newPwd").val()
						};
						//alert('aa');
						$.post(basePath + "n/user/modifyPassword", option, function(data){
							data = $.parseJSON(data);
							if(data.result){
								$("#modifyPwdDialog").dialog("close");
								$.messager.show({
									title:'提示',
									msg:'修改密码成功',
									timeout:5000,
									showType:'slide'
								});
							}else{
								$.messager.alert('提示', data.error);
							}
						});
					}
				}
			});
			
		}
		
		function loadData(){
			
		}
		
		function init(){
			initEvent();
			tabClose();
			tabCloseEven();
			loadData();
		}
		
		init();
		
	});
})();