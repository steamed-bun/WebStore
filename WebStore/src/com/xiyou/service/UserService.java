package com.xiyou.service;

import java.util.HashSet;
import java.util.Set;

import com.xiyou.dao.BookDAO;
import com.xiyou.dao.TradeDAO;
import com.xiyou.dao.TradeItemDAO;
import com.xiyou.dao.UserDAO;
import com.xiyou.dao.impl.BookDAOImpl;
import com.xiyou.dao.impl.TradeDAOImpl;
import com.xiyou.dao.impl.TradeItemDAOImpl;
import com.xiyou.dao.impl.UserDAOImpl;
import com.xiyou.domain.Trade;
import com.xiyou.domain.TradeItem;
import com.xiyou.domain.User;

public class UserService {

	private UserDAO userDAO = new UserDAOImpl();
	
	public User getUserByUserName(String username){
		return userDAO.getUser(username);
	}
	
	private TradeDAO tradeDAO = new TradeDAOImpl();
	private TradeItemDAO tradeItemDAO = new TradeItemDAOImpl();
	private BookDAO bookDAO = new BookDAOImpl();
	
	public User getUserWithTrades(String username){

		User user = userDAO.getUser(username);
		if(user == null){
			return null;
		}
		Set<Trade> trades = tradeDAO.getTradesWithUserId(user.getUserId());
		Set<Trade> trades2 = new HashSet<Trade>();
		if(trades != null){
			for(Trade trade : trades){
				Set<TradeItem> items = tradeItemDAO.getTradeItemsWithTradeId(trade.getTradeId());
				if(items != null && items.size() != 0){
					for(TradeItem item: items){
						//��book��װ����Ӧ��һ��TradeItem��
						item.setBook(bookDAO.getBook(item.getBookId()));
					}
					//��һ��TradeItem���Ϸ�װ����Ӧ��trade��
					if(items.size() != 0){
						trade.setItems(items);						
					}
				}else{
					//�����ݿ���ɾ����ǰTrade������tradeId
					System.out.println("�����ݿ���ɾ����ǰTrade" + trade.getTradeId());
					tradeDAO.deleteTradeWithTradeId(trade.getTradeId());
					trades2.add(trade);
				}
			}
			if(trades2 != null){
				for(Trade trade2 : trades2){
					trades.remove(trade2);
				}
			}
		}
		
		if(trades != null && trades.size() != 0){
			user.setTrades(trades);
		}
		return user;
	}
	
}
