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
		
	<h4>您的购物车为空</h4>
	
	<a href="bookServlet?method=getBooks&pageNo=${param.pageNo }">继续购物</a>
  </body>
</html>
