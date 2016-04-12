<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
	
		<table id="categoryList"  border="1px solid #000" cellpadding="2" cellspacing="1" width="80%"  align="left" style="background-color: #b9d8f3;" >
		
			<thead>
				<tr>
					<th height="100%">品类</th>
					<th height="100%">采购形式</th>
					<th height="100%">缺断货数量</th>
				</tr>
				<c:if test="${empty requestScope.list }">
					没有数据
				</c:if>
				<c:if test="${! empty requestScope.list }">
					<c:forEach items="${requestScope.list }" var="a">
						<tr>
							<td rowspan="2" align="center"  height="40px">${a.categoryName}</td>
							<td align="left"  height="40px">地采</td>
							<td align="left"  height="40px">${a.dcnum}</td>
						</tr>
						<tr>
							<td align="left"  height="40px">集采</td>
							<td align="left"  height="40px">${a.jcnum}</td>
						</tr>
					</c:forEach>
				</c:if>
			</thead>
		
		</table>
		
	</div>
</body>
</html>