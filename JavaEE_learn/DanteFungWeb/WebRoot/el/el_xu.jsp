<%@page import="com.dantefung.domain.Student"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>title</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body> 
  
           <%

               	Map<String,Student> map = new HashMap<String,Student>();
                map.put("101",new Student("谢位雄","男",18));
                map.put("p",new Student("林伟杰","女",19));
                map.put("103",new Student("候睿","未知",20));
                pageContext.setAttribute("map",map);

           %>
   
   		  <%--
   		      ${map.103.name}   错的
   		      ${map.'103'.name}  错的
   		      ${map."102".name}  错的
   		              虽然 字符串 102 是key ，但由于这样写会有歧义
   		              因此，有中括号的取值的方式
   		    --%> 
   		   ${map."101".name}<%-- 这个浏览器没报错，但是找不到值 --%>
   		   ${map.p.name }<%-- 这个是对的 --%><br/>
           ${map['103'].name }
  <br/>
  ====================el表达式的内置对象,从域中取数据=============  
  <br/>
    <%
    	pageContext.setAttribute("username", "rose");
    	pageContext.setAttribute("password", "123");
     %>
     <!-- param 是Map<paramName,paramValue>类型的对象 -->
     ${param.username }-${param.password }
     <hr/>
     ==============判空:empty=============================<br/>
     <%
     	String name = null;
     	pageContext.setAttribute("name", name);
      %>
      
      <%-- 既能判断null也能判断空字符串 --%>${name==null || name=="" }<br/>
      <%-- 既能判断null也能判断空字符串 --%> ${empty name }
      <hr/>
      ====================pageContex可以拿到jsp其他8大内置对象=====================<br/>
      <%=((HttpServletRequest)pageContext.getRequest()).getContextPath()%>
 	<br/>
 	${pageContext.request.contextPath }
 	<%-- 内置对象只能得到无参的getter属性 --%>
 	<br/>
 	<%
 		pageContext.setAttribute("p", "pp");
 		pageContext.setAttribute("p", "rp",PageContext.REQUEST_SCOPE); 
 		pageContext.setAttribute("p", "sp",PageContext.SESSION_SCOPE);
 		pageContext.setAttribute("p", "ap",PageContext.APPLICATION_SCOPE);
 	 %>
 	 <%-- jsp表达式 --%>
 	 <%=pageContext.getAttribute("p") %><br/>
 	 ${pageScope['p']}
 	 <br/>
 	
 	 <%=pageContext.getAttribute("p",PageContext.REQUEST_SCOPE) %><br/>
 	 ${requestScope['p']}
 	 
 	 <%=pageContext.getAttribute("p",PageContext.SESSION_SCOPE) %><br/>
 	 ${sessionScope['p']}
 	 
 	 <%=pageContext.getAttribute("p",PageContext.APPLICATION_SCOPE) %><br/>
 	 ${applicationScope['p'] }
 	 
 	 <hr/>
 	 =============Map<paramName, paramValue>类型对象param， 通过键取得值=====<br/>
 	 <%=request.getParameter("name") %>
 	 ${param.name }<%-- Map<paramName, paramValue>类型对象param， 通过键取得值 --%>
 	 <br/>
 	 ===============Map<headerName,headerValue(String)>类型对象header，通过键取得值=========
 	 <%=request.getHeader("user-agent") %>
 	 ${header['user-agent'] }
 	 <br/> 	 
 	 <hr/>
 	 ===============Map<cookieName,Cookie对象>类型对象====================
 	 <br/>
 	<%--  <%=request.getCookies()[0].getName() %>
 	 <%=request.getCookies()[0].getValue() %>
 	 ${cookie.JSESSIONID.name }-${cookie.JSESSIONID.value }
 	 
 	 <hr/>
 	 <%=application.getInitParameter("encoding") %>
 	 ${initParam.encoding } --%>
  </body>
</html>
