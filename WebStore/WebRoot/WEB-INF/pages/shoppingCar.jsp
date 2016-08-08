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
	<script type="text/javascript">
	$(function(){
		$(".delete").click(function(){
			var $tr = $(this).parent().parent();
			var title = $.trim($tr.find("td:first").text());
			var flag = confirm("ȷ��Ҫɾ��" + title + "����Ϣ��?");
			if(flag){
				return true;
			}
			return false;
		});
		
		//ajax �޸ĵ�����Ʒ������:
		$(":text").change(function(){
			var quantityVal = $.trim(this.value);
			var flag = false;
			var reg = /^\d+$/g;
			var quantity = -1;
			if(reg.test(quantityVal)){
				quantity = parseInt(quantityVal);
				if(quantity >= 0){
					flag = true;
				}
			}
			if(!flag){
				alert("������������Ϸ�!");
				$(this).val($(this).attr("class"));
				return;
			}
			var $tr = $(this).parent().parent();
			var title = $.trim($tr.find("td:first").text());
			if(quantity == 0){
				var flag2 = confirm("ȷ��Ҫɾ��" + title + "��?");
				if(flag2){
					//�õ��� a �ڵ�
					var $a = $tr.find("td:last").find("a");
					//ִ�� a �ڵ�� onclick ��Ӧ����. 
					$a[0].onclick();
					
					return;
				}
			}
			var flag = confirm("ȷ��Ҫ�޸�" + title + "��������?");
			if(!flag){
				$(this).val($(this).attr("class"));
				return;
			}
			var url = "bookServlet";
			var idVal = $.trim(this.name);
			var args = {"method":"updateItemQuantity", "id":idVal, "quantity":quantityVal, "time":new Date()};
			$.post(url, args, function(data){
				var bookNumber = data.bookNumber;
				var totalMoney = data.totalMoney;
				
				$("#totalMoney").text("�ܽ��: ��" + totalMoney);
				$("#bookNumber").text("���Ĺ��ﳵ�й���" + bookNumber + "����");
			},"JSON");
		});
	})
</script>
  </head>
  
  <body>
	<center>
		<a id="bookNumber">���Ĺ��ﳵ�й��� ${sessionScope.ShoppingCart.bookNumber } ����</a>
		<table cellpadding="10">
			<tr>
				<td>Title</td>
				<td>Quantity</td>
				<td>Price</td>
				<td>&nbsp;</td>
			</tr>
			
			<c:forEach items="${sessionScope.ShoppingCart.items }" var="item">
				<tr>
					<td>${item.book.title }</td>
					<td> 
						<input class="${item.quantity }" type="text" size="1" name="${item.book.bookId }" value="${item.quantity }"/>
					</td>
					<td>${item.book.price }</td>
					<td><a href="bookServlet?method=remove&pageNo=${param.pageNo }&id=${item.book.bookId }" class="delete">ɾ��</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="4" id="totalMoney">�ܽ��: �� ${ sessionScope.ShoppingCart.totalMoney}</td>
			</tr>
			<tr>
				<td colspan="4">
					<a href="bookServlet?method=getBooks&pageNo=${param.pageNo }">��������</a>
					<a href="bookServlet?method=clear">��չ��ﳵ</a>
					<a href="bookServlet?method=forwardPage&page=cash">����</a>
				</td>
			</tr>
		</table>
	</center>
  </body>
</html>
