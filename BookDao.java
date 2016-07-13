package cn.itcast.dao;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import cn.itcast.domain.Book;

public class BookDao {

	/**
	 * 获得所有的书籍对象
	 * 
	 * @return
	 */
	public List<Book> findAllBook() {

		try {
			// 模拟数据
			// List<Book> bookList = new ArrayList<Book>();
			// bookList.add(new Book("b001","java ","980"));
			// bookList.add(new Book("b001","java ","980"));
			// bookList.add(new Book("b001","java ","980"));

			// 将查询出来的数据元素的值，存放到bookList
			List<Book> bookList = new ArrayList<Book>();

			// 获得Document
			Document document = XmlUtil.getDocument();
			// 获得根元素
			Element rootElement = document.getRootElement();
			// 获得所有的book元素
			List<Element> bookElements = rootElement.elements();
			// 遍历所有的book元素
			for (Element bookEle : bookElements) {
				// * 获得每一个book元素
				// * 创建相应的book对象
				Book book = new Book();
				// * 将id、title、price的值封装到book对象中

				// 处理 id
				String id = bookEle.attributeValue("id");
				// 将获得id的值，添加到book对象中
				book.setId(id);

				// 获得所有的bookEle元素的子元素
				List<Element> childElement = bookEle.elements();
				// 遍历所有的子元素
				for (Element child : childElement) {
					// 处理 title
					if ("title".equals(child.getName())) {
						// 获得title的值
						String title = child.getText();
						// 设置title的到book对象中
						book.setTitle(title);
					}
					// 处理 price
					if ("price".equals(child.getName())) {
						book.setPrice(child.getText());
					}
				}

				// * 把创建的book对象添加到bookList中
				bookList.add(book);
			}
			return bookList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	/**
	 * 保存数据
	 * @param book 书籍对象
	 * @return 书籍的id，如果不存在返回null
	 */
	public String saveBook(Book book){
		
		try {
			//获得document
			Document document = XmlUtil.getDocument();
			//获得根元素
			Element rootElement = document.getRootElement();
			
			//创建book元素
			Element newBookElement = rootElement.addElement("book");
			// * 设置属性id
			newBookElement.addAttribute("id", book.getId());
			// * 处理title
			// * 创建title元素
			Element newTitleElement = newBookElement.addElement("title");
			// * 将新title元素添加到新book元素中(不需要)
			// * 给title设置文本内容
			newTitleElement.setText(book.getTitle());
			
			
			// * 处理price
			// * 创建price元素,并设置值
			newBookElement.addElement("price").setText(book.getPrice());
			// * 将新price元素添加到新book元素中(不需要)
			
			
			//将新book元素添加到根元素(不需要)
			
			
			//保存document
			XmlUtil.saveDocument(document);
			
			return book.getId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 通过id查询书籍
	 * @param bookId
	 * @return
	 */
	public Book findBookById(String bookId){
		try {
			//获得document
			Document document = XmlUtil.getDocument();
			//查询book元素 xpath
			Element bookElement = (Element)document.selectSingleNode("//book[@id='" + bookId + "']");
			//进行判断，是否查询成功
			if(bookElement == null){
				return null;
			}
			//将book元素的内容，封装到book对象中
			Book book = new Book();
			//id属性
			book.setId(bookElement.attributeValue("id"));
			//title元素
			book.setTitle(bookElement.element("title").getText());
			//price元素
			book.setPrice(bookElement.element("price").getText());
			
			return book;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 修改书籍
	 * @param book
	 * @return
	 */
	public String updateBook(Book book){
		
		try {
			//获得document
			Document document = XmlUtil.getDocument();
			//查询book
			Element bookElement = (Element) document.selectSingleNode("//book[@id='" + book.getId() + "']");
			//修改内容 title price
			bookElement.element("title").setText(book.getTitle());
			bookElement.element("price").setText(book.getPrice());
			//保存
			XmlUtil.saveDocument(document);
			return book.getId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 通过书籍编号删除书籍
	 * @param bookId
	 * @return
	 */
	public String deleteBookById(String bookId){
		
		try {
			//获得document
			Document document = XmlUtil.getDocument();
			//查询bookid元素
			Element bookNode = (Element)document.selectSingleNode("//book[@id='" + bookId + "']");
			//如果有删除
			if(bookNode != null){
				// * 获得父节点
				Element parentElement = bookNode.getParent();
				// * 通过父节点删除自己
				parentElement.remove(bookNode);
				//将内存中的数据，保存到硬盘中
				XmlUtil.saveDocument(document);
				return bookId;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}
