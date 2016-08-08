package com.xiyou.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import com.xiyou.dao.AccountDAO;
import com.xiyou.dao.BookDAO;
import com.xiyou.dao.TradeDAO;
import com.xiyou.dao.TradeItemDAO;
import com.xiyou.dao.UserDAO;
import com.xiyou.dao.impl.AccountDAOImpl;
import com.xiyou.dao.impl.BookDAOImpl;
import com.xiyou.dao.impl.TradeDAOImpl;
import com.xiyou.dao.impl.TradeItemDAOImpl;
import com.xiyou.dao.impl.UserDAOImpl;
import com.xiyou.domain.Book;
import com.xiyou.domain.ShoppingCart;
import com.xiyou.domain.ShoppingCartItem;
import com.xiyou.domain.Trade;
import com.xiyou.domain.TradeItem;
import com.xiyou.web.CriteriaBook;
import com.xiyou.web.Page;

public class BookService {
	
	private BookDAO bookDAO = new BookDAOImpl();
	
	public Page<Book> getPage(CriteriaBook criteriaBook){
		return bookDAO.getPage(criteriaBook);
	}

	public Book getBook(int id) {
		return bookDAO.getBook(id);
	}

	public boolean addToCart(int id, ShoppingCart shoppingCart) {
		Book book = bookDAO.getBook(id);
		
		if(book != null){
			shoppingCart.addBook(book);
			return true;
		}
		
		return false;
	}

	public void removeItemFromShoppingCart(ShoppingCart sc, int id) {
		sc.removeItem(id);
	}

	public void clearShoppingCart(ShoppingCart sc) {
		sc.clear();
	}

	public void updateItemQuantity(ShoppingCart sc, int id, int quantity) {
		sc.updateItemQuantity(id, quantity);
	}
	
	private AccountDAO accountDAO = new AccountDAOImpl();
	private TradeDAO tradeDAO = new TradeDAOImpl();
	private UserDAO userDAO = new UserDAOImpl();
	private TradeItemDAO tradeItemDAO = new TradeItemDAOImpl();

	public void cash(ShoppingCart shoppingCart, String username,
			String accountId) {
		/*
		 * 1、更新account表中的balance
		 * 2、给trade表中插入一条记录
		 * 3、给trade_item表中插入多条记录
		 * 4、同时对book表中的StoreNumber和SalesAmount进行更新
		 * 5、清空购物车
		 */
		accountDAO.updateBalance(Integer.parseInt(accountId), shoppingCart.getTotalMoney());

		Trade trade = new Trade();
		trade.setTradeTime(new Date(new java.util.Date().getTime()));
		trade.setUserId(userDAO.getUser(username).getUserId());
		tradeDAO.insert(trade);
		
		Collection<TradeItem> items = new ArrayList<>();
		for(ShoppingCartItem shoppingCartItem: shoppingCart.getItems()){
			TradeItem tradeItem = new TradeItem();
			tradeItem.setBookId(shoppingCartItem.getBook().getBookId());
			tradeItem.setQuantity(shoppingCartItem.getQuantity());
			tradeItem.setTradeId(trade.getTradeId());
			
			items.add(tradeItem);
		}
		tradeItemDAO.batchSave(items);
		
		bookDAO.batchUpdateStoreNumberAndSalesAmount(shoppingCart.getItems());

		shoppingCart.clear();
	}
	
}
