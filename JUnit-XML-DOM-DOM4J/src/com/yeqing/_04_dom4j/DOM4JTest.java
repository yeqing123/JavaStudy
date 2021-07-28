package com.yeqing._04_dom4j;


import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

public class DOM4JTest {
	private File f = new File("D:/eclipse-jee-2020-03-R-incubation-win32-x86_64" +
			"/eclipse/workspace/JavaFoundation/JUnit-XML-DOM-DOM4J/contacts.xml");
    // 需求1：获取XML文档中的所有元素信息
	@Test
	public void testGetAll() throws Exception {
		//1、获得指定的XML文档的Document对象
		SAXReader reader = new SAXReader();
		org.dom4j.Document doc = reader.read(f);
		//2、获取根元素
		org.dom4j.Element root = doc.getRootElement();
		//3、获得根元素下所有的联系人（linkman）的集合
		List<Element> linkmanElList = root.elements("linkman");
		//4、遍历集合获得每个联系人的详细信息
		for (Element linkmanEl : linkmanElList) {
			String id = linkmanEl.attributeValue("id");
			String name = linkmanEl.elementText("name");
			String email = linkmanEl.elementText("email");
			String address = linkmanEl.elementText("address");
			String group = linkmanEl.elementText("group");
			System.out.println("id:" + id + "[" + name + 
					", " + email + ", " + address + ", " + group + "]");
			System.out.println("---------------------------------------");
		}
		
		// 3、使用迭代器，直接遍历根元素中所有名称为linkman的子元素
		//for (Iterator i = root.elementIterator("linkman"); i.hasNext();) {
		//	Element linkmanEl = (Element)i.next();
		//	// 获得每个联系人的信息...
		//}
	}
	
	// 需求2：添加一个新的联系人
	@Test
	public void testAddElement() throws Exception {
		//1、依然是要获得XML文档的根元素
		SAXReader reader = new SAXReader();
		org.dom4j.Document doc = reader.read(f);
		org.dom4j.Element root = doc.getRootElement();
		//2、创建一个新的linkman节点，并将其变成根元素的子元素
		Element linkmanEl = root.addElement("linkman");
		//3、向linkman元素中添加name、email、address、group子元素，并设置它们的文本内容
		linkmanEl.addElement("name", "东方不败");
		linkmanEl.addElement("email", "东方不败@123.com");
		linkmanEl.addElement("address", "江湖");
		linkmanEl.addElement("group", "武侠");
		//4、执行同步操作，将内存中Document对象写入到磁盘文件中
//		FileWriter writer = new FileWriter(f);
//		doc.write(writer);
//		writer.close();  // 记住一定要关闭流
		
		// 如果想要得到一个漂亮的带缩进的效果，可以像下面这样：
		/* 注意：
		 * createCompactFormat();是压缩格式（默认方式）
		 * OutputFormat.createPrettyPrint();是漂亮的带缩进的格式
		 */
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter writer = new XMLWriter(new FileWriter(f), format);
		writer.write(doc);
		writer.close();
	}
}
