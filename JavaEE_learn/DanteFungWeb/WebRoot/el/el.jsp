<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ page import="com.dantefung.domain.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>el_获取数据</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <br/>======================获取域中存的数据==========================<br/>
  普通Servlet中的数据传给el表达式:<br/>
    <% 
        String data = "abcd";
        request.setAttribute("data", data);
    %>
    ${data } <br/><%-- 域：page-> request-> session-> application(某个对象里面找sServletContext)-- %>
   <%-- pageContext.findAttribute("data") 返回 空字符串--%>
   
   <br/>==================获取域中javabean存的数据========================<br/>
  <% 
      Person p = new Person();
      p.setName("aaaa");
      
      request.setAttribute("person", p);
  %>
  以关键字从域中查找:<br/>
  ${person.name }<%-- 调用getName() --%>
  
  <br/>===============获取域中javabean中的javabean的数据===================<br/>
  <%
      Person p1 = new Person();
      Address a = new Address();
      a.setCity("上海");
      p1.setAddress(a);
      
      request.setAttribute("p1", p1);     
  %>
  ${p1.address.city}
  <%-- 首先从4个域中找到关键字p1，然后相应的找到Person对象，接着取出该对象里面的address对象，接着取出address的属性city --%>
  
  <br/>===================获取域中list中的数据===================<br/>
      <%
          List list = new ArrayList();
          list.add(new Person("aaa"));
          list.add(new Person("bbb"));
          request.setAttribute("list", list);
      %>
      <br/> ${list['1'].name }<br/>
  
  <br/>==================获取域中map中的数据==================<br/>
     <%
         Map map = new HashMap();
         map.put("aa", new Person("aaaaaaaa"));
         map.put("bb", new Person("bbbbbbbb"));
         map.put("cc", new Person("ccccccccc"));
         map.put("dd", new Person("dddddddd"));
         map.put("ll", new Person("eeeeeeeeee"));
         request.setAttribute("mappp", map);
     %>    
     ${mappp.cc.name }
 <br/>=======================其它几个常用的el表达式===============<br/>
 ${pageContext.request.contextPath }<%-- 获得当前工程的地址 --%>
 <a href="${pageContext.request.contextPath }/index.jsp">点点</a>
  </body>
  
</html>
