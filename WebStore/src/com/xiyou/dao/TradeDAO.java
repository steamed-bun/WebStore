package com.xiyou.dao;

import java.util.Set;

import com.xiyou.domain.Trade;

public interface TradeDAO {

	/**
	 * 对Trade表插入一条trade记录
	 * @param trade
	 */
	public abstract void insert(Trade trade);

	/**
	 * 根据userId得到对应的交易集合
	 * @param userId
	 * @return
	 */
	public abstract Set<Trade> getTradesWithUserId(Integer userId);
	
	/**
	 * 根据tradeId删除数据库中对应的Trade记录
	 * @param tradeId
	 */
	public abstract void deleteTradeWithTradeId(int tradeId);

}