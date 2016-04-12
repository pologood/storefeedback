<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commonJsp.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>用户登录</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	overflow:hidden;
}
.STYLE3 {color: #528311; font-size: 12px; }
.STYLE4 {
	color: #42870a;
	font-size: 12px;
}
-->
</style>
    <script type="text/javascript">
    $(function(){
     
    	$("#my_tr_1").hide(); 
    	$("#my_tr_2").hide(); 
    	$("#my_tr_3").hide(); 
    	document.getElementById("mySelect")[1].selected=true;
     	$('#mySelect').change(function(){
        	var s = document.getElementById("mySelect").value;
		        if(s=="A"){
		        	$("#my_tr_1").hide(); 
		        	$("#my_tr_2").hide();
		        	$("#my_tr_3").hide(); 
		        	for(var i = 1; i < 4; i++){
		        			$("#tr_"+i).show();
		        	}
        		}else{
        			for(var i = 1; i < 4; i++){
	        			$("#tr_"+i).hide(); 
	        		}
        			$("#my_tr_1").show(); 
        	    	$("#my_tr_2").show(); 
        	    	$("#my_tr_3").show(); 
        			}
        	});
     	
     	
        //登陆
        $("#login").click(function(){
        	if( $("#username").val()==''){
        		 $.messager.alert('提示','请输入用户名!');
        		return;
        	}else if( $("#password").val()==''){
                $.messager.alert('提示','请输入密码!');
                return;
            }
        	
            //$("#form1").submit();
            var data = {
                        "username": $("#username").val(),
                        "password":$("#password").val()
                };
            
        	$.post(basePath + "n/user/checkLogin", data, function(data){
                data = $.parseJSON(data);
                if(data.result){
                    window.location.href = basePath + "n/navigation/goToMainPage";
                }else{
                    $.messager.alert('提示',data.error);
                }
            });
        });
        //回车登录
        $('body').keydown(function(event) {
        	if (event.keyCode == 13) {
        		var s = document.getElementById("mySelect").value;
        		if(s == "A"){
	        		if( $("#username").val()==''){
		           		 $.messager.alert('提示','请输入用户名!');
		           		return;
		           	}else if( $("#password").val()==''){
	                   $.messager.alert('提示','请输入密码!');
	                   return;
	               }
	           	
	               //$("#form1").submit();
	               var data = {
	                   "username": $("#username").val(),
	                   "password":$("#password").val()
	               };
	               
	          		$.post(basePath + "n/user/checkLogin", data, function(data){
	                  data = $.parseJSON(data);
	                  if(data.result){
	                      window.location.href = basePath + "n/navigation/goToMainPage";
	                  }else{
	                      $.messager.alert('提示',data.error);
	                  }
	              	});
        		}else {
        			if( $("#username_new").val()==''){
        	       		 $.messager.alert('提示','请输入手机号!');
        	       		return;
        	       		}else if( $("#password_new").val()==''){
        	                $.messager.alert('提示','请输入密码!');
        	                return;
        	            }
        	        	
        	        	var data = {
        	                    "username_new": $("#username_new").val(),
        	                    "password_new":$("#password_new").val()
        	            };
        	        	
        	        	$.post(basePath + "n/user/checkLoginnew", data, function(data){
        	                data = $.parseJSON(data);
        	                if(data.result){
        	                    window.location.href = basePath + "n/navigation/goToMainPagenew";
        	                }else{
        	                    $.messager.alert('提示',data.error);
        	                }
        	            });
        		}
        	}
        });
        
        //重置
        $("#reset").click(function(){
        	 $("#username").val('');
        	 $("#password").val('');
        });
        
        $("#login_new").click(function(){
        	if( $("#username_new").val()==''){
       		 $.messager.alert('提示','请输入手机号!');
       		return;
       		}else if( $("#password_new").val()==''){
                $.messager.alert('提示','请输入密码!');
                return;
            }
        	
        	var data = {
                    "username_new": $("#username_new").val(),
                    "password_new":$("#password_new").val()
            };
        	
        	$.post(basePath + "n/user/checkLoginnew", data, function(data){
                data = $.parseJSON(data);
                if(data.result){
                    window.location.href = basePath + "n/navigation/goToMainPagenew";
                }else{
                    $.messager.alert('提示',data.error);
                }
            });
        	
        });
        $("#reset_new").click(function(){
        	 $("#username_new").val('');
        	 $("#password_new").val('');
       });
        $("#password").keyup(function(event){
            var code = event.which;
            if(code == 13){
                $("#submit").trigger("click");
            }
        });
    });
    </script>
</head>

<body>
<form id="form1" action="<%=basePath%>n/navigation/goToMainPage" method="post">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td bgcolor="#e5f6cf">&nbsp;</td>
  </tr>
  <tr>
    <td height="608" background="<%=basePath%>resources/images/login_03.gif"><table width="862" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="266" background="<%=basePath%>resources/images/login_04.gif">&nbsp;</td>
      </tr>
      <tr>
        <td height="95">
        
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="424" height="95" background="<%=basePath%>resources/images/login_06.gif">&nbsp;</td>
            <td width="183" background="<%=basePath%>resources/images/login_07.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td width="29%" height="20"><div align="right"><span class="STYLE3">类型：</span></div></td>
                <td width="79%" height="20">
	                <select  id="mySelect" style="height:18px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;" >
					    <option value="B" >查询用户</option >
			            <option value="A" selected="selected" >配置用户</option >
	          		</select>
          		</td>
              </tr>
              <tr id="my_tr_1">
                <td width="29%" height="20"><div align="right"><span class="STYLE3">手机号：</span></div></td>
                <td width="79%" height="20"><input type="text" id="username_new" name="username_new"  style="height:18px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;"></td>
              </tr>
              <tr id="my_tr_2">
                <td height="20"><div align="right"><span class="STYLE3">密码：</span></div></td>
                <td height="20"><input type="password" id="password_new" name=password_new  style="height:18px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;"></td>
              </tr>
               <tr id="my_tr_3">
                <td height="20">&nbsp;</td>
                <td height="20"><img src="<%=basePath%>resources/images/dl.gif" width="81" height="22" border="0" usemap="#Map_new"></td>
              </tr>
              
              <tr id="tr_1">
                <td width="29%" height="20"><div align="right"><span class="STYLE3">用户名：</span></div></td>
                <td width="79%" height="20"><input type="text" id="username" name="username"  style="height:18px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;"></td>
              </tr>
              <tr id="tr_2">
                <td height="20"><div align="right"><span class="STYLE3">密码：</span></div></td>
                <td height="20"><input type="password" id="password" name="password"  style="height:18px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;"></td>
              </tr>
              <tr id="tr_3">
                <td height="20">&nbsp;</td>
                <td height="20"><img src="<%=basePath%>resources/images/dl.gif" width="81" height="22" border="0" usemap="#Map"></td>
              </tr>
            </table></td>
            <td width="255" background="<%=basePath%>resources/images/login_08.gif">&nbsp;</td>
          </tr>
        </table>
        
        </td>
      </tr>
      <tr>
        <td height="247" valign="top" background="<%=basePath%>resources/images/login_09.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="22%" height="30">&nbsp;</td>
            <td width="56%">&nbsp;</td>
            <td width="22%">&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="44%" height="20">&nbsp;</td>
                <td width="56%" class="STYLE4">版本 2015v1.1 </td>
              </tr>
            </table></td>
            <td>&nbsp;</td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td bgcolor="#a2d962">&nbsp;</td>
  </tr>
</table>
</form>
<map name="Map"><area shape="rect" id="login" coords="3,3,36,19"><area shape="rect" coords="40,3,78,18" id="reset"></map></body>
<map name="Map_new"><area shape="rect" id="login_new" coords="3,3,36,19" "><area shape="rect" coords="40,3,78,18" id="reset_new"></map></body>
</html>
