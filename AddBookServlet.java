package cn.itcast.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.dao.BookDao;
import cn.itcast.domain.Book;

@SuppressWarnings("serial")
public class AddBookServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		
		//从表单中获取用户提交的数据
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String price = request.getParameter("price");
		
		//将数据存放到JavaBean中
		Book newBook = new Book(id, title, price);
		
		//创建Dao
		BookDao bookDao = new BookDao();
		//执行保存功能
		String returnBookId = bookDao.saveBook(newBook);
		
		//处理操作结果：string表示成功，null表示失败
		//处理页面：tip.jsp，但显示内容不同，为当前servlet的内容可以显示到tip.jsp文件中，所以使用include
		/*
		 * 当前页面：http://localhost:8080/day10_demo/addBookServlet
		 * 目标页面：http://localhost:8080/day10_demo/page/tip.jsp
		 */
		RequestDispatcher dispatcher = request.getRequestDispatcher("/page/tip.jsp");
		//输出不同内容的
		//保存跳转页面url
		String refreshURL = request.getContextPath() + "/findAllBook";
		//获得输出流
		PrintWriter out = response.getWriter();
		if(returnBookId == null){
			out.print("未保存成功，请稍后重试<br>");
		} else {
			//成功
			out.print("保存成功<br/>");
		}
		out.print("3秒后自动跳转到查询页面 <a href='" + refreshURL + "'>手动跳转</a>");
		//包含
		dispatcher.include(request, response);
		//设置3秒后浏览器自动刷新,刷新到查询页面
		/*
		 * 当前页面：http://localhost:8080/day10_demo/addBookServlet
		 * 目标页面：http://localhost:8080/day10_demo/findAllBook
		 */
		
		response.setHeader("refresh", "3;url=" + refreshURL);
		

	}

}
