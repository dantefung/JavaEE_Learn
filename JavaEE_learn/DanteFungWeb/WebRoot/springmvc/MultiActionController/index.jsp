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
    <form action="${pageContext.request.contextPath }/studentMultiAction.action?do=add" method="post">
        用户名：<input type="text" name="stuName"/><br>
        密码：<input type="text" name="stuPwd"/><br>
    	<input type="submit" value="add"/>
    </form>
    <hr>
    <form action="${pageContext.request.contextPath }/studentMultiAction.action?do=update" method="post">
        用户名：<input type="text" name="stuName"/><br>
        密码：<input type="text" name="stuPwd"/><br>
    	<input type="submit" value="update"/>
    </form>
    <hr>
    <form action="${pageContext.request.contextPath }/studentMultiAction.action?do=list" method="post">
        用户名：<input type="text" name="stuName"/><br>
        密码：<input type="text" name="stuPwd"/><br>
    	<input type="submit" value="list"/>
    </form>
    <hr>
  </body>
</html>
