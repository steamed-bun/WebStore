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
  <script type="text/javascript" src="script/jquery-1.7.2.min.js"></script>
  <%@ include file="/commons/queryCondition.jsp" %>
  <script type="text/javascript">
	$(function(){
		$("#pageNo").change(function(){
			var val = $(this).val();
			val = $.trim(val);
			
			var flag = false;
			var reg = /^\d+$/g;
			var pageNo = 0;
			
			if(reg.test(val)){
				pageNo = parseInt(val);
				if(pageNo >= 1 && pageNo <= parseInt("${bookpage.totalPageNumber }")){
					flag = true;
				}
			}
			if(!flag){
				alert("输入的不是合法的页码.");
				$(this).val("");
				return;
			}
			var href = "bookServlet?method=getBooks&pageNo=" + pageNo + "&" + $(":hidden").serialize();
			window.location.href = href;
		});
	})
</script>
    <base href="<%=basePath%>">
  </head>
  
  <body>
	<center>
		
		<c:if test="${param.title != null}">
			您已经将 ${param.title} 放入到购物车中. 
			<br><br>
		</c:if>
		<c:if test="${!empty sessionScope.ShoppingCart.books }">
			您的购物车中有 ${sessionScope.ShoppingCart.bookNumber } 本书, <a href="bookServlet?method=forwardPage&page=shoppingCar&pageNo=${bookpage.pageNo }">查看购物车</a>
		</c:if>
		<br><br>
		<form action="bookServlet?method=getBooks" method="post">
			Price: 
			<input type="text" size="1" name="minPrice"/> - 
			<input type="text" size="1" name="maxPrice"/>
			<input type="submit" value="Submit"/>
		</form>
		<br><br>
 		<table cellpadding="10">
			<c:forEach items="${bookpage.list }" var="book">
				<tr>
					<td>
						<a href="bookServlet?method=getBook&pageNo=${bookpage.pageNo }&id=${book.bookId}">${book.title }</a>
						${book.author }
					</td>
					<td>${book.price }</td>
					<td><a href="bookServlet?method=addToCart&pageNo=${bookpage.pageNo }&id=${book.bookId}&title=${book.title}">加入购物车</a></td>
				</tr>
			</c:forEach>
		</table>
		<br><br>
		共 ${bookpage.totalPageNumber } 页
		当前第 ${bookpage.pageNo } 页		
		<c:if test="${bookpage.hasPrev }">
			<a href="bookServlet?method=getBooks&pageNo=1">首页</a>
			<a href="bookServlet?method=getBooks&pageNo=${bookpage.prevPage }">上一页</a>
		</c:if>
		<c:if test="${bookpage.hasNext }">
			<a href="bookServlet?method=getBooks&pageNo=${bookpage.nextPage }">下一页</a>
			&nbsp;&nbsp;
			<a href="bookServlet?method=getBooks&pageNo=${bookpage.totalPageNumber }">末页</a>
		</c:if>
		转到 <input type="text" size="1" id="pageNo"/> 页		
	</center>
  </body>
</html>
