package com.xiyou.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.xiyou.dao.TradeItemDAO;
import com.xiyou.domain.TradeItem;

public class TradeItemDAOImpl extends BaseDAO<TradeItem> implements TradeItemDAO {

	@Override
	public void batchSave(Collection<TradeItem> items) {
		String sql = "insert into trade_item(BOOKID,QUANTITY,TRADEID) values(?,?,?);";
		Object [][] object = new Object[items.size()][3];
		
		List<TradeItem> tradeItems = new ArrayList<>(items);
		for(int i = 0; i < tradeItems.size(); i++){
			object[i][0] = tradeItems.get(i).getBookId();
			object[i][1] = tradeItems.get(i).getQuantity();
			object[i][2] = tradeItems.get(i).getTradeId(); 
		}
		batch(sql, object);
	}

	@Override
	public Set<TradeItem> getTradeItemsWithTradeId(Integer tradeId) {
		String sql = "select ITEMID, BOOKID, QUANTITY,TRADEID from trade_item where TRADEID = ?";
		return new HashSet<>(queryForList(sql, tradeId));
	}

}
