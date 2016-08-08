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
						//将book分装到对应的一个TradeItem内
						item.setBook(bookDAO.getBook(item.getBookId()));
					}
					//将一个TradeItem集合分装到对应的trade中
					if(items.size() != 0){
						trade.setItems(items);						
					}
				}else{
					//从数据库中删除当前Trade，根据tradeId
					System.out.println("从数据库中删除当前Trade" + trade.getTradeId());
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
