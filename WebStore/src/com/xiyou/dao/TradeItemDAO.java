package com.xiyou.dao;

import java.util.Collection;
import java.util.Set;

import com.xiyou.domain.TradeItem;

public interface TradeItemDAO {

	/**
	 * 将传入的 TradeItem 的集合 保存到trade_item表中   一次性操作
	 * @param items
	 */
	public abstract void batchSave(Collection<TradeItem> items);

	/**
	 * 根据tradeId的到与其关联的所有交易项，即TradeItem的集合
	 * @param tradeId
	 * @return
	 */
	public abstract Set<TradeItem> getTradeItemsWithTradeId(Integer tradeId);

}

