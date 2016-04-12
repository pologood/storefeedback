<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commonJsp.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户查询</title>
</head>
<script type="text/javascript" src="<%=basePath%>resources/jeui/jquery.easyui.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#submit").click(function(){
			$("#form").action = "<%=basePath%>n/checkSapFeedback/goToCategoryPage";
			$("#form").submit();
			
		})
	})
</script>
<body>
	
	<form id="form" name="form" method="post" action="<%=basePath%>n/checkSapFeedback/goToCategoryPage">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="30">
            <select name="year" style="height:30px;width:100px;font-size:1.2em;text-align:center;" >
              <!-- <option value="2010">2010</option>
              <option value="2011">2011</option>
              <option value="2012">2012</option>
              <option value="2013">2013</option>
              <option value="2014">2014</option> -->
			  <option value="2015">2015</option>
              <option value="2016">2016</option>
			  <!-- <option value="2017">2017</option>
              <option value="2018">2018</option>
			  <option value="2019">2019</option>
              <option value="2020">2020</option> -->
            </select>
            年
            <select name="month" style="height:30px;width:100px;font-size:1.2em;text-align:center;">
              <option value="1">1</option>
              <option value="2">2</option>
              <option value="3">3</option>
              <option value="4">4</option>
              <option value="5">5</option>
              <option value="6">6</option>
              <option value="7">7</option>
              <option value="8">8</option>
              <option value="9">9</option>
              <option value="10">10</option>
              <option value="11">11</option>
              <option value="12">12</option>
            </select>
            月
            <select name="weekCount" style="height:30px;width:100px;font-size:1.2em;text-align:center;">
              <option value="1">第一</option>
              <option value="2" >第二</option>
              <option value="3" >第三</option>
              <option value="4" >第四</option>
              <option value="5" >第五</option>
              <option value="6" >第六</option>
            </select>
            周
			</td>
		</tr>
	</table>
</form>	<input id="submit" type="submit" value="查询" style="height:30px;width:100px;font-size:1.2em;text-align:center;"/>
	
</body>
</html>