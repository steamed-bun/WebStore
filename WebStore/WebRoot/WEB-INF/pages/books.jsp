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
				alert("����Ĳ��ǺϷ���ҳ��.");
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
			���Ѿ��� ${param.title} ���뵽���ﳵ��. 
			<br><br>
		</c:if>
		<c:if test="${!empty sessionScope.ShoppingCart.books }">
			���Ĺ��ﳵ���� ${sessionScope.ShoppingCart.bookNumber } ����, <a href="bookServlet?method=forwardPage&page=shoppingCar&pageNo=${bookpage.pageNo }">�鿴���ﳵ</a>
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
					<td><a href="bookServlet?method=addToCart&pageNo=${bookpage.pageNo }&id=${book.bookId}&title=${book.title}">���빺�ﳵ</a></td>
				</tr>
			</c:forEach>
		</table>
		<br><br>
		�� ${bookpage.totalPageNumber } ҳ
		��ǰ�� ${bookpage.pageNo } ҳ		
		<c:if test="${bookpage.hasPrev }">
			<a href="bookServlet?method=getBooks&pageNo=1">��ҳ</a>
			<a href="bookServlet?method=getBooks&pageNo=${bookpage.prevPage }">��һҳ</a>
		</c:if>
		<c:if test="${bookpage.hasNext }">
			<a href="bookServlet?method=getBooks&pageNo=${bookpage.nextPage }">��һҳ</a>
			&nbsp;&nbsp;
			<a href="bookServlet?method=getBooks&pageNo=${bookpage.totalPageNumber }">ĩҳ</a>
		</c:if>
		ת�� <input type="text" size="1" id="pageNo"/> ҳ		
	</center>
  </body>
</html>
