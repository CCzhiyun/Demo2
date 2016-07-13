package cn.itcast.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.dao.BookDao;

public class DeleteBookServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		
		//获得需要删除的书籍的编号
		String bookId = request.getParameter("bookId");
		//通知dao，删除书籍
		BookDao bookDao = new BookDao();
		String returnBookId = bookDao.deleteBookById(bookId);
		
		//处理结果	
		if(returnBookId == null){
			
		} else {
			//删除成功
			//继续停留当前页面，但数据需要刷新
			//重定向
			
			/*
			 * 当前页面：http://localhost:8080/day10_demo/deleteBookServlet
			 * 目标页面：http://localhost:8080/day10_demo/findAllBook
			 */
			response.sendRedirect(request.getContextPath() + "/findAllBook");
		}
		
		
		
		

	}

}
