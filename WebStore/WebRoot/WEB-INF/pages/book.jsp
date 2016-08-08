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
		书名: ${book.title }
		<br><br>
		作者: ${book.author }
		<br><br>
		单价: ${book.price }
		<br><br>
		出版日期: ${book.publishingDate }
		<br><br>
		评价: ${book.remark }
		<br><br>
		<a href="bookServlet?method=getBooks&pageNo=${param.pageNo }">继续购物</a>
	</center>
  </body>
</html>
