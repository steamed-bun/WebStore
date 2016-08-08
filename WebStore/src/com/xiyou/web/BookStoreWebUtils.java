package com.xiyou.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.xiyou.domain.ShoppingCart;

public class BookStoreWebUtils {

	/**
	 * ��ȡShoppingCar �����session����ֱ�ӻ�ȡ�����û�����½�һ��
	 * @param request
	 * @return
	 */
	public static ShoppingCart getShoppingCart(HttpServletRequest request){
		HttpSession session = request.getSession();
		
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("ShoppingCart");
		if(shoppingCart == null){
			shoppingCart = new ShoppingCart();
			session.setAttribute("ShoppingCart", shoppingCart);
		}
		
		return shoppingCart;
	}
	
}
