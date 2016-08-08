<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<form action="userServlet" method="post">
			用户名: <input type="text" name="username"/>
			<input type="submit" value="Submit"/>
		</form>
		
	</center>
  </body>
</html>
