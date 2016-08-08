package com.xiyou.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiyou.domain.User;
import com.xiyou.service.UserService;


public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UserService userService = new UserService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		
		User user = userService.getUserWithTrades(username);

		if(user == null){
			response.sendRedirect(request.getServletPath() + "/error-NotFound.jsp");
			return;
		}
		if(user.getTrades() == null || user.getTrades().size() == 0)
		{
			response.sendRedirect(request.getServletPath() + "/error-EmptyTrade.jsp");
			return;
		}
		
		request.setAttribute("user", user);

		request.getRequestDispatcher("/WEB-INF/pages/trades.jsp").forward(request, response);

	}

}
