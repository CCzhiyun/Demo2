package cn.itcast.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.dao.BookDao;
import cn.itcast.domain.Book;

@SuppressWarnings("serial")
public class PreUpdateBookServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//获得参数bookid
		String bookId = request.getParameter("bookId");
		
		//通知dao，查询数据
		BookDao bookDao = new BookDao();
		
		//通知dao，查询当前书籍 null 不存在
		Book book = bookDao.findBookById(bookId);
		//处理数据
		if(book == null){
			
		} else {
			//将结果存放到作用域
			request.setAttribute("book", book);
			//需要找到视图jsp，显示页面
			/*
			 * 当前页面：http://localhost:8080/day10_demo/preUpdateBookServlet
			 * 目标页面：http://localhost:8080/day10_demo/page/updateBook.jsp
			 */
			RequestDispatcher dispatcher = request.getRequestDispatcher("/page/updateBook.jsp");
			dispatcher.forward(request, response);
		}
		
		

	}

}
