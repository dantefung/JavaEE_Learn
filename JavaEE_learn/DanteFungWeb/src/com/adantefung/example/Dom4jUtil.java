package com.adantefung.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Dom4jUtil {

	/**
	 * Dom4j:��ȡxml�ļ�
	 * @param url
	 * @param bean
	 * @return
	 */
	public static Document parse(String url)
	{
		SAXReader reader = new SAXReader();
		Document document;
		try 
		{
			document = reader.read(url);
		} 
		catch (DocumentException e) 
		{
			throw new RuntimeException(e);
		}
		return document;
	}
	
	 /** 
     * Dom4j:��document����д���µ��ļ� 
     *  
     * @param document 
     * @throws Exception 
     */  
    public static void writer(Document document, String fileName){  
        // ���յĸ�ʽ  
        // OutputFormat format = OutputFormat.createCompactFormat();  
        // �Ű�����ĸ�ʽ  
        OutputFormat format = OutputFormat.createPrettyPrint();  
        // ���ñ���  
        format.setEncoding("UTF-8");  
        // ����XMLWriter����,ָ����д���ļ��������ʽ  
        // XMLWriter writer = new XMLWriter(new FileWriter(new  
        // File("src//a.xml")),format);  
        XMLWriter writer;
		try 
		{
			writer = new XMLWriter(new OutputStreamWriter(  
			        new FileOutputStream(new File(fileName)), "UTF-8"), format);
	        // д��  
	        writer.write(document);  
	        // ����д��  
	        writer.flush();  
	        // �رղ���  
	        writer.close();  
		} 
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}  

    }
}
