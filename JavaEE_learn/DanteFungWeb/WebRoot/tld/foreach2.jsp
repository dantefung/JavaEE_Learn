<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/example" prefix="cc" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'foreach2.jsp' starting page</title>

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
	______list_____
	<br>
	<%
		List list = new ArrayList();
		list.add("nnn");
		list.add("bbb");
		list.add("ccc");
		list.add("ddd");
		request.setAttribute("list", list);
	%>
	<cc:foreach2 items="${list}" var="str">
 ${str }<br />
	</cc:foreach2>

	______map_____
	<br>
	<%
		Map map = new HashMap();
		map.put("aa", "111");
		map.put("bb", "222");
		map.put("cc", "333");
		request.setAttribute("map", map);
	%>
	<cc:foreach2 items="${map}" var="entry">
  ${entry.key } :${entry.value }<br />
	</cc:foreach2>


	______Integer_____
	<br>
	<%
		Integer num[] = { 1, 2, 3, 4 };
		request.setAttribute("num", num);
	%>
	<cc:foreach2 items="${num}" var="i">
${i }<br />
	</cc:foreach2>

	______String_____
	<br>
	<%
		String strs[] = { "sss", "mmm" };
		request.setAttribute("strs", strs);
	%>
	<cc:foreach2 items="${strs}" var="str">
${str }<br />
	</cc:foreach2>
	______int_____
	<br>
	<%
		int num2[] = { 11, 22, 33, 44 };
		request.setAttribute("num2", num2);
	%>
	<cc:foreach2 items="${num2}" var="i">
${i }
</cc:foreach2>
</body>
</html>
