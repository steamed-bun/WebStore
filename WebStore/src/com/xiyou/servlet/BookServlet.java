package com.xiyou.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.xiyou.domain.Account;
import com.xiyou.domain.Book;
import com.xiyou.domain.ShoppingCart;
import com.xiyou.domain.ShoppingCartItem;
import com.xiyou.domain.User;
import com.xiyou.service.AccountService;
import com.xiyou.service.BookService;
import com.xiyou.service.UserService;
import com.xiyou.web.BookStoreWebUtils;
import com.xiyou.web.CriteriaBook;
import com.xiyou.web.Page;

public class BookServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	private BookService bookService = new BookService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String methodName = request.getParameter("method");
		
		try {
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			method.setAccessible(true);
			method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	private UserService userService = new UserService();
	
	protected void cash(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String accountId = request.getParameter("accountId");
		
		StringBuffer errors = validateForm(username, accountId);

		if(errors.toString().equals("")){
			errors = validateUser(username, accountId);
			if(errors.toString().equals("")){
				errors = validateBookStoreNumber(request);
				if(errors.toString().equals("")){
					errors = validateBalance(request, accountId);
				}
			}
		}
		
		if(!errors.toString().equals("")){
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/WEB-INF/pages/cash.jsp").forward(request, response);
			return;
		}
		
		//验证通过执行具体的逻辑操作
		bookService.cash(BookStoreWebUtils.getShoppingCart(request), username, accountId); 
		response.sendRedirect(request.getContextPath() + "/success.jsp");
	}
	
	//简单验证，即表单验证
	public StringBuffer validateForm(String username, String accountId){
		StringBuffer errors = new StringBuffer("");
		
		if(username == null || username.trim().equals("")){
			errors.append("用户名不能为空<br>");
		}
		
		if(accountId == null || accountId.trim().equals("")){
			errors.append("账号不能为空");			
		}
		
		return errors;
	}
	
	//验证库存
	public StringBuffer validateBookStoreNumber(HttpServletRequest request){
		
		StringBuffer errors = new StringBuffer("");
		ShoppingCart shoppingCart = BookStoreWebUtils.getShoppingCart(request);
		
		for(ShoppingCartItem shoppingCartItem: shoppingCart.getItems()){
			int quantity = shoppingCartItem.getQuantity();
			int storeNumber = bookService.getBook(shoppingCartItem.getBook().getBookId()).getStoreNumber();
			
			if(quantity > storeNumber){
				errors.append(shoppingCartItem.getBook().getTitle() + "库存不足<br>");
			}
		}
		
		return errors;
	}
		
	private AccountService accountService = new AccountService();
	
	//验证余额
	public StringBuffer validateBalance(HttpServletRequest request, String accountId){
		
		StringBuffer errors = new StringBuffer("");
		ShoppingCart shoppingCart = BookStoreWebUtils.getShoppingCart(request);
		
		Account account = accountService.getAccount(Integer.parseInt(accountId));
		if(shoppingCart.getTotalMoney() > account.getBalance()){
			errors.append("余额不足!");
		}
		return errors;
	}
	
	//验证用户名和账号是否匹配
	public StringBuffer validateUser(String username, String accountId){
		boolean flag = false;
		User user = userService.getUserByUserName(username);
		if(user != null){
			if(accountId.trim().equals("" + user.getAccountId())){
				flag = true;
			}
		}
		StringBuffer errors = new StringBuffer("");
		if(!flag){
			errors.append("用户名和账号不匹配");
		}
		return errors;
	}
	
	protected void updateItemQuantity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String idStr = request.getParameter("id");
		String quantityStr = request.getParameter("quantity");
		
		ShoppingCart shoppingCart = BookStoreWebUtils.getShoppingCart(request);
		
		int id = -1;
		int quantity = -1;
		
		try {
			id = Integer.parseInt(idStr);
			quantity = Integer.parseInt(quantityStr);
		} catch (Exception e) {}
		
		if(id > 0 && quantity > 0){
			bookService.updateItemQuantity(shoppingCart, id, quantity);
		}

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("bookNumber", shoppingCart.getBookNumber());
		result.put("totalMoney", shoppingCart.getTotalMoney());
		//这部分只能知道其实现的效果，即将result放入response中，用gson传出
		Gson gson = new Gson();
		String jsonStr = gson.toJson(result);
		response.setContentType("text/javascript");
		response.getWriter().print(jsonStr);
	}

	protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShoppingCart shoppingCart = BookStoreWebUtils.getShoppingCart(request);
		bookService.clearShoppingCart(shoppingCart);
		request.getRequestDispatcher("/WEB-INF/pages/emptyCar.jsp").forward(request, response);
	}
	
	protected void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		
		int id = -1;
		try {
			id = Integer.parseInt(idStr);
		} catch (Exception e) {}
		
		ShoppingCart shoppingCart = BookStoreWebUtils.getShoppingCart(request);
		bookService.removeItemFromShoppingCart(shoppingCart, id);
		
		if(shoppingCart.isEmpty()){
			request.getRequestDispatcher("/WEB-INF/pages/emptyCar.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("/WEB-INF/pages/shoppingCar.jsp").forward(request, response);
	}
	
	protected void forwardPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		request.getRequestDispatcher("/WEB-INF/pages/" + page + ".jsp").forward(request, response);
	}

	protected void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String idStr = request.getParameter("id");
		int id = -1;
		boolean flag = false;
		
		try {
			id = Integer.parseInt(idStr);
		} catch (Exception e) {}
		
		if(id > 0){
			ShoppingCart shoppingCart = BookStoreWebUtils.getShoppingCart(request);
			flag = bookService.addToCart(id, shoppingCart);
		}
		
		if(flag){
			getBooks(request, response);
			return;
		}
		
		response.sendRedirect(request.getContextPath() + "/error-NotFound.jsp");
	}

	protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		int id = -1;

		Book book = null;
		
		try {
			id = Integer.parseInt(idStr);
		} catch (NumberFormatException e) {}
		
		if(id > 0){
			book = bookService.getBook(id);
		}
		if(book == null){
			response.sendRedirect(request.getContextPath() + "/error-NotFound.jsp");
			return;
		}
		
		request.setAttribute("book", book);
		request.getRequestDispatcher("/WEB-INF/pages/book.jsp").forward(request, response);
	}
	
	protected void getBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageNoStr = request.getParameter("pageNo");
		String minPriceStr = request.getParameter("minPrice");
		String maxPriceStr = request.getParameter("maxPrice");
		
		int pageNo = 1;
		int minPrice= 0;
		int maxPrice = Integer.MAX_VALUE;
		
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		
		try {
			minPrice = Integer.parseInt(minPriceStr);
		} catch (NumberFormatException e) {}
		
		try {
			maxPrice = Integer.parseInt(maxPriceStr);
		} catch (NumberFormatException e) {}
		
		CriteriaBook criteriaBook = new CriteriaBook(minPrice, maxPrice, pageNo);
		Page<Book> page = bookService.getPage(criteriaBook);
		
		request.setAttribute("bookpage", page);
		
		request.getRequestDispatcher("/WEB-INF/pages/books.jsp").forward(request, response);
	}
	
	

}
