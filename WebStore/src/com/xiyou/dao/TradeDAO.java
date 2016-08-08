package com.xiyou.dao;

import java.util.Set;

import com.xiyou.domain.Trade;

public interface TradeDAO {

	/**
	 * ��Trade�����һ��trade��¼
	 * @param trade
	 */
	public abstract void insert(Trade trade);

	/**
	 * ����userId�õ���Ӧ�Ľ��׼���
	 * @param userId
	 * @return
	 */
	public abstract Set<Trade> getTradesWithUserId(Integer userId);
	
	/**
	 * ����tradeIdɾ�����ݿ��ж�Ӧ��Trade��¼
	 * @param tradeId
	 */
	public abstract void deleteTradeWithTradeId(int tradeId);

}