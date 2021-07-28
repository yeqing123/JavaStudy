package com.yeqing._03_dom;




import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;



public class DomTest {
	private File f = new File("D:/eclipse-jee-2020-03-R-incubation-win32-x86_64" +
			"/eclipse/workspace/JavaFoundation/JUnit-XML-DOM-DOM4J/contacts.xml");
	
    @Test
	public void testGetDocument() throws Exception {
    	// 使用工厂类的静态方法，获得工厂类的实例
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    	// 依靠工厂类对象创建一个DocumentBuilder对象
    	DocumentBuilder builder = factory.newDocumentBuilder();
    	// 使用DocumentBuilder对象的parse()方法，解析指定的XML文件，获得一个Document对象
    	Document doc = builder.parse(f);
    	
    	System.out.println(doc);
	}
    
    @Test
	public void testGetText() throws Exception {
    	// 使用工厂类的静态方法，获得工厂类的实例
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    	// 依靠工厂类对象创建一个DocumentBuilder对象
    	DocumentBuilder builder = factory.newDocumentBuilder();
    	// 使用DocumentBuilder对象的parse()方法，解析指定的XML文件，获得一个Document对象
    	Document doc = builder.parse(f);
    	// 获得文档的根元素（contacts）
    	Element root = doc.getDocumentElement();
    	
    	//==============================================================
    	// 获得根元素中第二个联系人（linkeman子元素）
    	Element linkmanEl = (Element) root.getElementsByTagName("linkman").item(1);
    	// 获取linkeman元素的name子元素
    	Element nameEl = (Element) linkmanEl.getElementsByTagName("name").item(0);
    	String text = nameEl.getTextContent();
    	//===============================================================
    	
    	System.out.println(text);
    	Assert.assertEquals("李四", text);
	}
    
    @Test
	public void testSetText() throws Exception {
    	// 使用工厂类的静态方法，获得工厂类的实例
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    	// 依靠工厂类对象创建一个DocumentBuilder对象
    	DocumentBuilder builder = factory.newDocumentBuilder();
    	// 使用DocumentBuilder对象的parse()方法，解析指定的XML文件，获得一个Document对象
    	Document doc = builder.parse(f);
    	// 获得文档的根元素（contacts）
    	Element root = doc.getDocumentElement();
    	// 获得根元素中第二个联系人（linkeman子元素）
    	Element linkmanEl = (Element) root.getElementsByTagName("linkman").item(1);
    	// 获取linkeman元素的email子元素
    	Element emailEl = (Element) linkmanEl.getElementsByTagName("name").item(0);
    	
    	//==============================================================================
    	// 修改其文本内容
    	emailEl.setTextContent("88888888@qq.com");
    	
    	/*
    	 * 下来我们需要将内存中修改过的XML文档，同步到磁盘文件中，需要使用一个核心类：Transformer
    	 * 而这个是一个抽象类，需要使用工厂类TransformerFacotry才能获得，而工厂类实例又需要使用它自己的一个静态方法newInstance()来获得。
    	 */
    	
    	// 1、先获得工厂类TransformerFactory的实例
    	TransformerFactory factory2 = TransformerFactory.newInstance();
    	// 2、使用工厂类对象获得一个Transcormer类的实例
    	Transformer transformer = factory2.newTransformer();
    	// 3、使用Transformer对象，进行同步操作。在此之前，首先要创建两个参数对象，
    	// 一个是源（它包含内存中的Document对象），另一个是目标（它是磁盘中XML文件的输出流）
    	Source source = new DOMSource(doc);
    	Result result = new StreamResult(f);
    	transformer.transform(source, result);
    	//=============================================================================
	}
}
