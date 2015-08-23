<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
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
    	<a href="/DanteFungWeb/i18/login.jsp?language=zh">ÖĞÎÄ(ÍøÒ³)</a>
  	    <a href="/DanteFungWeb/i18/login.jsp?language=en">english(pages)</a>
	<%
	    String language = request.getParameter("language");
	    if(language==null  || language.equals(""))
	    {
	    	language = "zh";
	    }
	    
	    Locale locale = new Locale(language);
	    ResourceBundle bundle = ResourceBundle.getBundle("com.dantefung.resource.MessageResource",locale);
	%>
	
     <form action="">
         <%=bundle.getString("username") %><input type="text" name="username"><br/>
         <%=bundle.getString("password") %><input type="text" name="password"><br/>
         <input type="submit" value="<%=bundle.getString("submit") %>">
     </form>	
  </body>
</html>
