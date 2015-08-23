<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'el_comput.jsp' starting page</title>
    
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
  <br/>=============基本的逻辑运算符===================<br/>
    <%
        // 关键字- 变量
    	request.setAttribute("username", "aaa");
        request.setAttribute("password", "123");
    %>
    ${username=='aaa' && password=='123' }
    
    <br/>==============empty运算符================<br/>
    <%
        request.setAttribute("list", null);
        //request.setAttribute("list", new ArrayList());
    %>
    ${empty(list) }
    
    <br/>=============二元运算符==================<br/>
   <%-- el 表达式不支持字符串的连接 
        可用自定义el表达式解决这个问题.
   --%>
    ${user !=null? user.name : '' }
    
    <br/>=============二元运算符（数据回显）==============<br/>
    <%
        request.setAttribute("gender", "female");
    %>
    <input type="radio" name="gender" value="male" ${gender=='male'? 'checked' : '' }>男
    <input type="radio" name="gender" value="female" ${gender=='female'? 'checked' : '' }>女
    
    <br/>============二元表达式（数据回显） el函数==============<br/>
    <%
      // request.setAttribute("likes", new String("dance", "sing"));
      request.setAttribute("likes", new String("dance"));
    %>
    
    <input type="checkbox" name="likes" value="">唱歌
    <input type="checkbox" name="likes" value="">跳舞
    <input type="checkbox" name="likes" value="">足球
    <input type="checkbox" name="likes" value="">篮球
  </body>
</html>
