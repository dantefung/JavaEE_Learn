<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>统计在线人数</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <form action="${pageContext.request.contextPath }/OnlineServlet" method="post">
      <table border="1" >
      	<tr>
      		<td>用户名：</td>
      		<td><input type="text" name="userName" /></td>
      	</tr>
      	<tr>
      		<td><input type="submit"  value="提交"/></td>
      	</tr>
      </table>
  </form>
    
  </body>
</html>
