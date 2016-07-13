package cn.itcast.dao;

import java.io.FileOutputStream;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * 此类是dom4j的工具类
 */
public class XmlUtil {
	
	private static String path;
	
	/**
	 * 获得document对象
	 * @return
	 */
	public static Document getDocument() throws Exception{
		//创建流
		SAXReader reader = new SAXReader();
		//解析
		return reader.read(path);
	}
	
	/**
	 * 保存document对象
	 * @param docuemnt
	 */
	public static void saveDocument(Document document) throws Exception{
		//获得流
		XMLWriter writer = new XMLWriter(new FileOutputStream(path));
		writer.write(document);
		writer.close();
	}

	public static void setPath(String path) {
		XmlUtil.path = path;
	}
	
	

}
