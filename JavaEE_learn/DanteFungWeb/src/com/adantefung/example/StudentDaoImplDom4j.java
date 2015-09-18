package com.adantefung.example;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

public class StudentDaoImplDom4j implements StudentDao{
	
	private Student bean = new Student();
	
	public Student getBean() {
		return bean;
	}

	public void setBean(Student bean) {
		this.bean = bean;
	}

	/**
	 * Dom4j
	 * @param Student student 
	 */
	@Override
	public boolean addStudent(Student student) {
		boolean flag = false;
		try
		{
			// 获得当前xm文档对象
			Document document = Dom4jUtil.parse(Constants.XML_PATH);
			// 获得根节点exam
			Element rootNode = document.getRootElement();
			// 在根节点下创建二级节点Student
			Element secNode = rootNode.addElement("student");
			// 给二级节点Student添加属性
			secNode.addAttribute("examid", student.getExamid());
			secNode.addAttribute("idcard", student.getIdcard());
			// 在二级节点Student下添加字节点name locatioin grade
			Element name = secNode.addElement("name");
			Element location = secNode.addElement("location");
			Element grade = secNode.addElement("grade");
			// 分别在三级节点下添加文本内容
			name.setText(student.getName());
			location.setText(student.getLocation());
			grade.setText(student.getGrade());
			Dom4jUtil.writer(document, Constants.XML_PATH);
			
			flag = true;
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);		
		}
		
		return flag;
	}
  
	/**
	 * 基于Dom4j
	 * 无聊，用反射写的查找方法.
	 * @param examid 准考证号
	 *//*
	@Override
	public Student find(String examid) {
		
		Document document = Dom4jUtil.parse(Constants.XML_PATH);

		// 一级节点为根节点
		Element rootNode = document.getRootElement();
		
		// 遍历二级节点
		for(int i=0, size=rootNode.nodeCount(); i<size; i++)
		{
			
			Node secChildNode = rootNode.node(i);
//			System.out.println(secChildNode.getName()+ "：二级节点");
			
			if(secChildNode instanceof Element)
			{
				System.out.println();
				
				// 检测二级节点是否有属性，有，则封装到Bean中
				nodeAttrFilter(secChildNode);
				
				// 遍历三级节点
				for(int j=0, length = ((Element)secChildNode).nodeCount(); j<length; j++)
				{
					try 
					{
						Node node = ((Element)secChildNode).node(j);
						if(node instanceof Element)
						{		
							// 检测三级节点是否有属性，有，则封装到Bean中
							nodeAttrFilter(node);
							
							// 获取当前节点的文本内容并封装到bean中
							System.out.println(node.getName()+ " : " + node.getText());
							char word = node.getName().charAt(0);
							Method method = getBean().getClass().getDeclaredMethod("set" + node.getName().replace(word+"", (word+"").toUpperCase()), String.class);
							//System.out.println(method);
							if(method != null)
							{
								method.invoke(getBean(), node.getText());
							}
						}
					}
					catch (Exception e) 
					{
						throw new RuntimeException(e);
					}
				}
			}
		}
		
		return getBean();
	}

	private void nodeAttrFilter(Node secChildNode) {
		System.out.println("====Student=====");
		// 与Bean中的成员变量做比较(因为Bean的部分成员变量作为了xml文档中节点的属性)
		Field[] fields = getBean().getClass().getDeclaredFields();
		
		try 
		{
			for(Field f : fields)
			{
				System.out.println(f.getName());
				Attribute attr = ((Element)secChildNode).attribute(f.getName());
				//System.out.println("=============" + attr);
				if( attr !=null)
				{
					//System.out.println( ">>>>>>>>>>>>>>>"+ attr.getName());
					char word = attr.getName().charAt(0);
					Method method;
					method = getBean().getClass().getDeclaredMethod("set" + attr.getName().replace(word+"", (word+"").toUpperCase()), String.class);
					method.invoke(getBean(), attr.getValue());
					System.out.println(attr.getName() + ": " + attr.getValue());
				}
			}
		} 
		catch (Exception e) 
		{
			throw new RuntimeException(e);
		}
	}*/
	
	/**
	 * 基于xpath的查找
	 * @param String examid 用户指定查找的准考证号
	 */
	@Override
	public Student find(String examid) {
		Student student = new Student();
		try
		{
			// 解析文档，基于SAX将文档读入内存构建出文档树
			Document document = Dom4jUtil.parse(Constants.XML_PATH);
			Element stuNode = (Element)document.selectSingleNode("/exam/student[@examid='"+examid+"']");
			student.setExamid(examid);
			student.setIdcard(stuNode.attributeValue("idcard"));
			// 遍历当前stuent下的所有子节点
			
			/*--------------------------方式一：反射机制------------------------------------------*/
/*			int len = stuNode.nodeCount();
			Field[] fields = getBean().getClass().getDeclaredFields();
			for(int i=0; i<len; i++)
			{
				Node node = stuNode.node(i);
				for(Field f : fields)
				{
					if(f.getName().equals(node.getName()))
					{
						System.out.println(f.getName() + "===========" + node.getName());
						char word = node.getName().charAt(0);
						Method method = getBean().getClass().getDeclaredMethod("set" + node.getName().replace(word+"", (word+"").toUpperCase()), String.class);
						method.invoke(student, node.getText());
					}
				}
			}*/
			
			/*---------------------------方式二：硬编码-------------------------------------*/
			Element name = stuNode.element("name");
			student.setName(name.getText());
			Element location = stuNode.element("location");
			student.setLocation(location.getText());
			Element grade = stuNode.element("grade");
			student.setGrade(grade.getText());
			/*---------------------------方式三：xpath--有bug待解决---------------------------------*/
		/*	Node name = stuNode.selectSingleNode("/exam/student/name");
			student.setName(name.getText());
			Node location = stuNode.selectSingleNode("/exam/student/location");
			student.setLocation(location.getText());
			Node grade = stuNode.selectSingleNode("/exam/student/grade");
			student.setGrade(grade.getText());*/
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
		return student;
	}
	
	/**
	 * 基于Dom4j
	 */

/*	@Override
	public boolean delStudent(String name) {
		boolean flag=false;
		try
		{
			// 解析文档，基于SAX将文档读入内存构建出文档树
			Document document = Dom4jUtil.parse(Constants.XML_PATH);
			// 获得文档的跟节点exam
			Element rootNode = document.getRootElement();
			// 获根节点级节点的所有字节点数目
			int length = rootNode.nodeCount();
			// 遍历所有的二级节点，找到与传进来的name匹配的节点
			Node secNode = null;
			Node thirdNode =null;
			for(int i=0; i<length; i++)
			{
				// 获得根节点下的二级节点
			    secNode = rootNode.node(i);
			    if(secNode instanceof Element) // 防止读取到空格换行，导致下面的转型错误
			    {
			    	// 遍历当前二级节点下的所有三级节点
			    	for(int j=0, len = ((Element)secNode).nodeCount(); j<len; j++)
			    	{
			    		// 获得三级节点
			    		thirdNode = ((Element)secNode).node(j);
			    		if(thirdNode instanceof Element)
			    		{
			    			if(thirdNode.getText().equals(name))
			    			{
			    				// 自杀
			    				secNode.detach();
			    				System.out.println(thirdNode.getName());
			    				break;
			    			}
			    		}
			    	}
			    }
			}
			
			Dom4jUtil.writer(document, Constants.XML_PATH);
			// 在根节点下删除该二级节点(他杀)
			// @TO-DO
			//rootNode.remove(secNode);
			
			flag = true;
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
		
		return flag;
	}*/
	
	/**
	 * 基于xpath
	 * @param String name 指定删除的用户名
	 */
	@Override
	public boolean delStudent(String name) {
		boolean flag=false;
		try
		{
			// 解析文档，基于SAX将文档读入内存构建出文档树
			Document document = Dom4jUtil.parse(Constants.XML_PATH);
			// 获得所有的name节点
			List<Element> nameNodes = document.selectNodes("/exam/student/name");
			// 遍历所有的name节点
			for(Element nameNode : nameNodes)
			{
				if(nameNode.getText().equals(name))
				{
					nameNode.detach();
					break;
				}
			}
			
			Dom4jUtil.writer(document, Constants.XML_PATH);
			flag = true;
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
		
		return flag;
	}

}
