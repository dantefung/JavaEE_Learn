package com.adantefung.cookies_app3;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HistoryDemo extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("书本的详细信息如下：<br/>XXXXXXX<br/>");
		response.getWriter().write("<a href='"+request.getContextPath()+"/HistoryUIDemo'>返回购物页</a>");
		
		// 养成好习惯，防止用户传过来的参数是中文,因此，在获取数据前，要指定UTF-8的码表进行解码
		request.setCharacterEncoding("UTF-8");
		// 先判断客户机有没带着cookie过来
		Cookie[] cookies = request.getCookies();
		
		Cookie c = new Cookie("proHist", genHist(request, cookies, 3));
		c.setMaxAge(Integer.MAX_VALUE);
		c.setPath(request.getContextPath());
		response.addCookie(c);
	}

	// 我自己的算法.
	private String genHist(HttpServletRequest request, Cookie[] cookies, int histNum)
			throws UnsupportedEncodingException {
		
		String proHistValue = "";
		boolean isExist = false;
		String bookId = request.getParameter("bookId");
		System.out.println("cookies:" + cookies);
		// 情况一：客户机带来一个或多个cookie
		if(cookies != null)
		{
			// 在一个或多个cookie中筛选出name 为 proHist
			for(Cookie c : cookies)
			{
				// 存在的情况
				if("proHist".equals(c.getName()))
				{
					
					String[] srcValues = c.getValue().split("\\-");
					System.out.println("srcValue: " + srcValues[0]);
					// 将数组转成列表
					//List<String> srcList = Arrays.asList(srcValues);
					LinkedList<String> srcList = new LinkedList(Arrays.asList(srcValues));
					
					// 重复
					if(srcList.contains(bookId))
					{
						// 去掉原来与bookId重复的元素
						srcList.remove(bookId);
						// 把bookId新增到前面
						srcList.addFirst(bookId);
					}
					else
					{
						// 不重复
						srcList.addFirst(bookId);
					}
					
					// 拼接成我们要的数据格式,只截取前面三个元素拼接成 "1-2-3"的形式
					String result = "";
					int count = 0;
					for(int i=0; i<srcList.size(); i++)
					{
						count++;
						if(count<=histNum)
						{
							result = result  + srcList.get(i) + "-";
						}
						else
						{
							break;
						}
					}
					
					proHistValue = result.substring(0, result.length()-1);
					System.out.println(">>>>>>>>>>>>>"+ proHistValue);
					
				    isExist = true;
				}
			}
			
			// 不存在的情况(筛选完全部都还没有)
			if(isExist == false)
			{
				System.out.println("———————————isExist == false 为 proHist的Cookie不存在，给客户机发送一个 ———————————————");
				// 获取用户带过来的id号
				proHistValue = bookId;
				System.out.println("proHist:" + proHistValue);
			}
		}
		else//情况 二： 客户机一个cookie都没带过来
		{
			System.out.println("客户机一个cookie都没带过来");
			// 获取用户带过来的id号
			proHistValue = bookId;
			System.out.println("proHist:" + proHistValue);
		}
		return proHistValue;
	}
	
	
	//获取返回的id值
	/**
	 *      当前保存的cookie值                           传入的id              最终保存的cookie值
	 *          null或找不到对应的cookie    1                      1
	 *            1                     2                      2-1  （不大于3且id不重复：把传入的id放到最前面）
	 *            2-1                   1                      1-2 （不大于3且id重复：把重复去掉，把传入的id放到最前面）
	 *            3-2-1                 4                      4-3-2 （3个且id不重复：干掉最后一个，把传入的id放到最前面）
	 *            3-2-1                 2                      2-3-1 （3个且id重复：把重复去掉，把传入的id放到最前面 ）
	 * 
	 * @return
	 */
	private String getBookIdValue(HttpServletRequest request,String id) {
		Cookie[] cs = request.getCookies();
		String proHist = null;
		if(cs!=null){
			for(Cookie c:cs){
				if(	c.getName().equals("proHist")){
					proHist = c.getValue();
					break;
				}
			}
		}
		//null或找不到对应的cookie
		if(cs==null || proHist==null){
			return id;
		}
		
		String[] ids = proHist.split("\\-");
		LinkedList<String> list = new LinkedList(Arrays.asList(ids));
		
		if(list.size()<3 ){//小于3
			if(list.contains(id)){
				//把重复去掉，把传入的id放到最前面
				list.remove(id);
				list.addFirst(id);
			}else{
				//把传入的id放到最前面
				list.addFirst(id);
			}
		}else{ //3个
			if(list.contains(id)){
				//把重复去掉，把传入的id放到最前面
				list.remove(id);
				list.addFirst(id);
			}else{
				//干掉最后一个，把传入的id放到最前面
				list.removeLast();
				list.addFirst(id);
			}
		}
		//list- > String: 3-2-1
		StringBuffer sb = new StringBuffer();
		for(String s:list){
			sb.append(s+"-");
		}
		String result = sb.toString();// 多了一个“-”
		return result.substring(0,result.length()-1); //去掉 “-” 
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
