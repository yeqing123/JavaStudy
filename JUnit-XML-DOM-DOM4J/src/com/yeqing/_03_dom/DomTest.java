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
	
	// 需求：获得XML文档的Document对象
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
    
    // 需求：获得DOM树中根元素（contacts）下第二个（linkman）子元素
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
    
    // 需求：修改DOM树中根元素中第二个linkman资源中email元素的文本值，并执行同步操作保存到磁盘的XML文档中
    @Test
	public void testSetText() throws Exception {
    	// 获得文档的Document对象
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    	DocumentBuilder builder = factory.newDocumentBuilder();
    	Document doc = builder.parse(f);
    	// 获得文档的根元素（contacts）
    	Element root = doc.getDocumentElement();
    	// 获得根元素中第二个联系人（linkeman子元素）的email子元素
    	Element linkmanEl = (Element) root.getElementsByTagName("linkman").item(1);
    	Element emailEl = (Element) linkmanEl.getElementsByTagName("name").item(0);
    	
    	//==============================================================================
    	// 修改email元素的文本内容
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
    
    // 需求：向DOM树的根元素的所有子元素的末尾添加一个新的linkman子元素
    //（linkman元素中还有name、email、address、group子元素，这其实是一个XML片段），并执行同步操作
    @Test
	public void testAddElement() throws Exception {
		//1、获得文档的Document对象
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    	DocumentBuilder builder = factory.newDocumentBuilder();
    	Document doc = builder.parse(f);
    	//2、获得根元素
    	Element root = doc.getDocumentElement();
    	//=======================================================================
    	//3、创建一个新的XML片段
    	//3.1、创建新的元素linkman、name、email、address、group
    	Element linkmanEl = doc.createElement("linkman");
    	Element nameEl = doc.createElement("name");
    	Element emailEl = doc.createElement("email");
    	Element addressEl = doc.createElement("address");
    	Element groupEl = doc.createElement("group");
    	//3.2、给linkman设置id属性，并设置name、email、address、group的文本内容
    	linkmanEl.setAttribute("id", "3");
    	nameEl.setTextContent("Lucy");
    	emailEl.setTextContent("Lucy@y");
    	addressEl.setTextContent("广州");
    	groupEl.setTextContent("小码哥学院");
    	//3.3、在新元素之间建立关系，即让name、email、address、group成为linkman的子元素
    	linkmanEl.appendChild(nameEl);
    	linkmanEl.appendChild(emailEl);
    	linkmanEl.appendChild(addressEl);
    	linkmanEl.appendChild(groupEl);
    	//4、将新的XML片段加入到DOM树中，即让新的linkman元素成为根元素的子元素
    	root.appendChild(linkmanEl);
    	//=======================================================================
    	
    	//5、执行从Document对象到XML文档的同步操作
    	TransformerFactory factory2 = TransformerFactory.newInstance();
    	Transformer transformer = factory2.newTransformer();
    	Source source = new DOMSource(doc);
    	Result result = new StreamResult(f);
    	transformer.transform(source, result);
	}
}
