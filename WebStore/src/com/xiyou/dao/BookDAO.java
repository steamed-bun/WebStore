package com.xiyou.dao;

import java.util.Collection;
import java.util.List;

import com.xiyou.domain.Book;
import com.xiyou.domain.ShoppingCartItem;
import com.xiyou.web.CriteriaBook;
import com.xiyou.web.Page;

public interface BookDAO {

	/**
	 * 根据bookId返回对应的Book对象
	 * @param bookId
	 * @return
	 */
	public abstract Book getBook(int bookId);

	/**
	 * 根据传入的CriteriaBook 返回 Page对象，以便于分页
	 * @param cb
	 * @return
	 */
	public abstract Page<Book> getPage(CriteriaBook cb);

	/**
	 * 根据传入的CriteriaBook 返回数的总数，以便于分页
	 * @param cb
	 * @return
	 */
	public abstract long getTotalBookNumber(CriteriaBook cb);

	/**
	 * 根据传入的CriteriaBook 返回当前页要显示的 Book集合
	 * @param cb
	 * @param pageSize
	 * @return
	 */
	public abstract List<Book> getPageList(CriteriaBook cb,int pageSize);

	/**
	 * 根据bookId 得到其对应的余额
	 * @param bookId
	 * @return
	 */
	public abstract int getStoreNumber(Integer bookId);

	/**
	 * 根据传入的ShoppingCartItem集合，即交易项集合，更新其交易的书的StoreNumber和SalesAmount
	 * StoreNumber = StoreNumber - quantitly
	 *  SalesAmount = SalesAmount + quantitly
	 * @param items
	 */
	public abstract void batchUpdateStoreNumberAndSalesAmount(
			Collection<ShoppingCartItem> items);

}