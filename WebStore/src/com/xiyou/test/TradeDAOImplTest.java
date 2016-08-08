package com.xiyou.test;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.xiyou.dao.TradeDAO;
import com.xiyou.dao.impl.TradeDAOImpl;

public class TradeDAOImplTest {

	private TradeDAO tradeDAO = new TradeDAOImpl();
	
	@Test
	public void testInsertTrade() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTradesWithUserId() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteTradeWithTradeId() {
		tradeDAO.deleteTradeWithTradeId(12);
	}

}
