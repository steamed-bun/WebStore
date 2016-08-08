package com.xiyou.dao.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import com.xiyou.dao.TradeDAO;
import com.xiyou.domain.Trade;



public class TradeDAOImpl extends BaseDAO<Trade> implements TradeDAO {

	@Override
	public void insert(Trade trade) {
		String sql = "insert into trade (USERID, TRADETIME) values (?,?)";
		long tradeId = insert(sql, trade.getUserId(), trade.getTradeTime());
		trade.setTradeId((int)tradeId);
	}

	@Override
	public Set<Trade> getTradesWithUserId(Integer userId) {
		String sql = "select TRADEID, USERID, TRADETIME from trade where USERID = ? order by TRADETIME desc";
		return new LinkedHashSet<Trade>(queryForList(sql, userId));
	}

	@Override
	public void deleteTradeWithTradeId(int tradeId) {
		String sql = "DELETE FROM trade WHERE TRADEID = ?";
		update(sql, tradeId);
	}

}
