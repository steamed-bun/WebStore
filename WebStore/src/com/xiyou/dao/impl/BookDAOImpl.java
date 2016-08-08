package com.xiyou.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.xiyou.dao.BookDAO;
import com.xiyou.domain.Book;
import com.xiyou.domain.ShoppingCartItem;
import com.xiyou.web.CriteriaBook;
import com.xiyou.web.Page;


public class BookDAOImpl extends BaseDAO<Book> implements BookDAO {

	@Override
	public Book getBook(int bookId) {
		String sql = "select BOOKID,AUTHOR,TITLE,PRICE,PUBLISHINGDATE,SALESAMOUNT,STORENUMBER,REMARK from book where BOOKID = ?";
		return query(sql, bookId);
	}
 
	@Override
	public Page<Book> getPage(CriteriaBook cb) {
		Page<Book> page = new Page<>(cb.getPageNo());
		page.setTotalItemNumber(getTotalBookNumber(cb));
		cb.setPageNo(page.getPageNo());
		page.setList(getPageList(cb, 3));
		return page;
		
		//更清楚的代码解释在java/笔记/JavaWeb/webStore 遇到的问题里面
	}

	@Override
	public long getTotalBookNumber(CriteriaBook cb) {
		String sql = "select count(BOOKID) from book where PRICE >= ? and PRICE <= ?";
		return getSingleVal(sql, cb.getMinPrice(), cb.getMaxPrice()); 
	}

	@Override
	public List<Book> getPageList(CriteriaBook cb, int pageSize) {
		String sql = "select BOOKID,AUTHOR,TITLE,PRICE,PUBLISHINGDATE,SALESAMOUNT,STORENUMBER,REMARK from book where PRICE >= ? and PRICE <= ? LIMIT ?, ?";
		return queryForList(sql, cb.getMinPrice(), cb.getMaxPrice(), (cb.getPageNo() - 1) * pageSize, pageSize);
	}

	@Override
	public int getStoreNumber(Integer bookId) {
		String sql = "select STORENUMBER from book where BOOKID = ?";
		return getSingleVal(sql, bookId);
	}

	@Override
	public void batchUpdateStoreNumberAndSalesAmount(
			Collection<ShoppingCartItem> items) {
		String sql = "update book set SALESAMOUNT = SALESAMOUNT + ?, STORENUMBER = STORENUMBER - ? where BOOKID = ?";
		Object [][] object = null;
		object = new Object[items.size()][3];
		List<ShoppingCartItem> shoppingCartItems = new ArrayList<>(items);
		for(int i = 0; i < items.size(); i++){
			object[i][0] = shoppingCartItems.get(i).getQuantity();
			object[i][1] = shoppingCartItems.get(i).getQuantity();
			object[i][2] = shoppingCartItems.get(i).getBook().getBookId();
		}
		batch(sql, object);
	}

}
