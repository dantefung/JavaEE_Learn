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
        欢迎回来，您的用户名为: ${loginInfo}
        <a href="${pageContext.request.contextPath }/online/onlineList.jsp">查看所有在线人员</a> <a href="${pageContext.request.contextPath }/MyLogoutServlet">注销</a>
    
  </body>
</html>
