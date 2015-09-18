<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
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
  <h3>当前在线人数：</h3>
    <table border="1">
    	<c:forEach items="${applicationScope.onLine }" var="entry" varStatus="status">
    	<tr>
    		<th>编号</th>
    		<th>用户名</th>
    		<th>IP地址</th>
    		<th>登录时间</th>
    		<th>最后一次访问时间</th>
    		<th>操作</th>
    		
    	</tr>
    	<tr>
    		<td>${status.count }</td>
    		<td>${entry.value.userName }</td>
    		<td>${entry.value.remoteHost}</td>
    		<td>${entry.value.loginTime}</td>
    		<td>${entry.value.lastTime }</td>
    		<td><a href="${pageContext.request.contextPath }/MyLogoutServlet?sessionId=${entry.key}">踢除</a></td>
    	</tr>
    </c:forEach>
    </table>
  </body>
</html>
