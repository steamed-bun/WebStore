package com.xiyou.dao;

import java.util.Collection;
import java.util.List;

import com.xiyou.domain.Book;
import com.xiyou.domain.ShoppingCartItem;
import com.xiyou.web.CriteriaBook;
import com.xiyou.web.Page;

public interface BookDAO {

	/**
	 * ����bookId���ض�Ӧ��Book����
	 * @param bookId
	 * @return
	 */
	public abstract Book getBook(int bookId);

	/**
	 * ���ݴ����CriteriaBook ���� Page�����Ա��ڷ�ҳ
	 * @param cb
	 * @return
	 */
	public abstract Page<Book> getPage(CriteriaBook cb);

	/**
	 * ���ݴ����CriteriaBook ���������������Ա��ڷ�ҳ
	 * @param cb
	 * @return
	 */
	public abstract long getTotalBookNumber(CriteriaBook cb);

	/**
	 * ���ݴ����CriteriaBook ���ص�ǰҳҪ��ʾ�� Book����
	 * @param cb
	 * @param pageSize
	 * @return
	 */
	public abstract List<Book> getPageList(CriteriaBook cb,int pageSize);

	/**
	 * ����bookId �õ����Ӧ�����
	 * @param bookId
	 * @return
	 */
	public abstract int getStoreNumber(Integer bookId);

	/**
	 * ���ݴ����ShoppingCartItem���ϣ���������ϣ������佻�׵����StoreNumber��SalesAmount
	 * StoreNumber = StoreNumber - quantitly
	 *  SalesAmount = SalesAmount + quantitly
	 * @param items
	 */
	public abstract void batchUpdateStoreNumberAndSalesAmount(
			Collection<ShoppingCartItem> items);

}