package com.xiyou.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
	
	private Map<Integer, ShoppingCartItem> books = new HashMap<>();

	/**
	 * 更新购物车中该id对应的交易的quantity
	 * @param id
	 * @param quantity
	 */
	public void updateItemQuantity(Integer id, int quantity){
		ShoppingCartItem shoppingCartItem =books.get(id);
		if(shoppingCartItem != null){
			shoppingCartItem.setQuantity(quantity);
		}
	}

	/**
	 * 从购物车中删除掉改id对应的交易项
	 * @param id
	 */
	public void removeItem(Integer id){
		books.remove(id);
	}

	/**
	 * 清空购物车
	 */
	public void clear(){
		books.clear();
	}

	/**
	 * 判断购物车是否为空
	 * @return
	 */
	public boolean isEmpty(){
		return books.isEmpty();
	}

	/**
	 * 获取当前购物车中所有交易的总金额
	 * @return
	 */
	public float getTotalMoney(){
		float total = 0;
		
		for(ShoppingCartItem shoppingCartItem: getItems()){
			total += shoppingCartItem.getItemMoney();
		}
		
		return total;
	}

	/**
	 * 获取当前购物车中的所有或交易内容
	 * @return
	 */
	public Collection<ShoppingCartItem> getItems(){
		return books.values();
	}

	/**
	 * 获取当前购物车数的总数
	 * @return
	 */
	public int getBookNumber(){
		int total = 0;
		
		for(ShoppingCartItem shoppingCartItem: books.values()){
			total += shoppingCartItem.getQuantity();
		}
		
		return total;
	}
	
	/**
	 * 获取当前购物车的所有交易记录
	 * @return
	 */
	public Map<Integer, ShoppingCartItem> getBooks() {
		return books;
	}
	
	/**
	 * 判断当前购物车有没有该id对应的交易记录
	 * @param id
	 * @return
	 */
	public boolean hasBook(Integer id){
		return books.containsKey(id);
	}		
	
	/**
	 * 添加一条交易到购物车
	 * @param book
	 */
	public void addBook(Book book){

		ShoppingCartItem shoppingCartItem = books.get(book.getBookId());
		
		if(shoppingCartItem == null){
			shoppingCartItem = new ShoppingCartItem(book);
			books.put(book.getBookId(), shoppingCartItem);
		}else{
			shoppingCartItem.increment();
		}
	}
}
