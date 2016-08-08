<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="script/jquery-1.7.2.min.js"></script>
	<%@ include file="/commons/queryCondition.jsp" %>
  </head>
  
  <body>
    <center>
		<br><br>
		<h4>User: ${user.username }</h4>
		
		<br><br>
			<table>
				<c:forEach items="${user.trades }" var="trade">
					
					<tr>
					<td>
					<table cellpadding="10">
					
						<tr>
							<td colspan="3">TradTime: ${trade.tradeTime }</td>
						</tr>
		
						<c:forEach items="${trade.items }" var="item">
							
							<tr>
								<td>${item.book.title }</td>
								<td>${item.book.price }</td>
								<td>${item.quantity }</td>
							</tr>
							
						</c:forEach>
						
					</table>
					<br><br>
					</td>
					</tr>
					
				</c:forEach>
			</table>
		
	</center>
  </body>
</html>
