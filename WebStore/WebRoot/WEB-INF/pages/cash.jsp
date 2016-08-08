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
		��һ������ ${sessionScope.ShoppingCart.bookNumber } ����
		<br>
		Ӧ��: �� ${ sessionScope.ShoppingCart.totalMoney}
	
		<br><br>
		
		<c:if test="${requestScope.errors != null }">
			<font color="red">${requestScope.errors }</font>
		</c:if>
		
		<form action="bookServlet?method=cash" method="post">
		
			<table cellpadding="10"> 
				<tr>
					<td>���ÿ�����:</td>
					<td><input type="text" name="username"/></td>
				</tr>
				<tr>
					<td>���ÿ��˺�:</td>
					<td><input type="text" name="accountId"/></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Submit"/></td>
				</tr>
			</table>
		
		</form>
		
	</center>
	
  </body>
</html>
