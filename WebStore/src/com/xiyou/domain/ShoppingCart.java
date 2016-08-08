package com.xiyou.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
	
	private Map<Integer, ShoppingCartItem> books = new HashMap<>();

	/**
	 * ���¹��ﳵ�и�id��Ӧ�Ľ��׵�quantity
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
	 * �ӹ��ﳵ��ɾ������id��Ӧ�Ľ�����
	 * @param id
	 */
	public void removeItem(Integer id){
		books.remove(id);
	}

	/**
	 * ��չ��ﳵ
	 */
	public void clear(){
		books.clear();
	}

	/**
	 * �жϹ��ﳵ�Ƿ�Ϊ��
	 * @return
	 */
	public boolean isEmpty(){
		return books.isEmpty();
	}

	/**
	 * ��ȡ��ǰ���ﳵ�����н��׵��ܽ��
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
	 * ��ȡ��ǰ���ﳵ�е����л�������
	 * @return
	 */
	public Collection<ShoppingCartItem> getItems(){
		return books.values();
	}

	/**
	 * ��ȡ��ǰ���ﳵ��������
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
	 * ��ȡ��ǰ���ﳵ�����н��׼�¼
	 * @return
	 */
	public Map<Integer, ShoppingCartItem> getBooks() {
		return books;
	}
	
	/**
	 * �жϵ�ǰ���ﳵ��û�и�id��Ӧ�Ľ��׼�¼
	 * @param id
	 * @return
	 */
	public boolean hasBook(Integer id){
		return books.containsKey(id);
	}		
	
	/**
	 * ���һ�����׵����ﳵ
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
