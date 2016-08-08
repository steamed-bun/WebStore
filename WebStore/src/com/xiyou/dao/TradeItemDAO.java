package com.xiyou.dao;

import java.util.Collection;
import java.util.Set;

import com.xiyou.domain.TradeItem;

public interface TradeItemDAO {

	/**
	 * ������� TradeItem �ļ��� ���浽trade_item����   һ���Բ���
	 * @param items
	 */
	public abstract void batchSave(Collection<TradeItem> items);

	/**
	 * ����tradeId�ĵ�������������н������TradeItem�ļ���
	 * @param tradeId
	 * @return
	 */
	public abstract Set<TradeItem> getTradeItemsWithTradeId(Integer tradeId);

}

