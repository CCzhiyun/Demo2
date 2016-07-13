package cn.itcast.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.dao.BookDao;
import cn.itcast.dao.XmlUtil;
import cn.itcast.domain.Book;

@SuppressWarnings("serial")
public class FindAllBookServlet extends HttpServlet {
	
	@Override
	public void init() throws ServletException {
		//获得books.xml,返回真实的路径，当前项目发布到tomcat所在的目录webapps
		String path = this.getServletContext().getRealPath("/books.xml");
		System.out.println(path);
		//设置路径
		XmlUtil.setPath(path);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//创建Dao
		BookDao bookDao = new BookDao();
		//调用方法，获得所有的书籍对象
		List<Book> bookList = bookDao.findAllBook();
		
		//需要将查询的结果放置到作用域中
		request.setAttribute("bookList", bookList);
		
		//使用转发选择视图 jsp  /page/findBooks.jsp
		// * 获得调度器  -- 确定path
		/*
		 * 当前页面： http://localhost:8080/day09_demo/findAllBook
		 * 目标页面：http://localhost:8080/day09_demo/page/findBooks.jsp
		 * *  /page/findBooks.jsp
		 * *  page/findBooks.jsp
		 */
		RequestDispatcher dispatcher = request.getRequestDispatcher("/page/findBooks.jsp");
		// * 转发
		dispatcher.forward(request, response);

	}

}
