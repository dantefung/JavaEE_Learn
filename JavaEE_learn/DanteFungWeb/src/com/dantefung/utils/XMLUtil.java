package com.dantefung.utils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;


/**
 *操作xml文件的工具类
 * @author APPle
 *
 */
public class XMLUtil {
	/**
	 *  jaxp:读取xml文件
	 */
	public static Document getDocument() throws Exception{ // 自己能不能处理，如果不能处理，需要抛出
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		return builder.parse("./src/exam.xml");
	}
	
	/**
	 *  jaxp:写出xml文件
	 * */
	public static void write2xml(Document doc) throws Exception{
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer ts = factory.newTransformer();
		ts.transform(new DOMSource(doc), new StreamResult("./src/exam.xml"));
	}
	
  
}
