<%-- <%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>sun��˾��el������</title>
    
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
   <br/>================��Сдת���� AAAAAAAAAAAA----->aaaaaaaaaa=============<br/>
    ${fn:toLowerCase("AAAAAAAA") }
    
    <%
        request.setAttribute("arr", new String[5]);
    %>
    <br/>============�õ����е�����ĳ���===================<br/>
    ${fn:length(arr) }
    
    <br/>=============�������е�list������==========<br/>
    <%
    	List list = new ArrayList();
        list.add("aa");	
        list.add("bb");
        request.setAttribute("list", list);
    %>
    
    <c:forEach var="i" begin="0" end="${fn:length(list) }">
    ${list[i]}
    </c:forEach>
    
    <br/>================�ַ�����ƴ��===================<br/>
   
    
    ${fn:join(fn:split("www,dantefung,com",","),".") }
    
    <br/>===============ĳ���ַ��������Ƿ�������ַ���=============<br/>
    ${fn:contains("aaaaaaaaabbbbbbbbbbbbbb","aa") }
    
    <br/>=============ת�����==================<br/>
    ${fn:escapeXml("<a href=''>���</a>")}
     <br/>---------------ȡ���û���д�İ��û���------------------------<br/>
     <%
        request.setAttribute("likes", new String[]{"football","sing"});
    %>
     <input type="checkbox" name="likes" value="sing" ${fn:contains(fn:join(likes,","),"sing")?'checked':'' }>����
    <input type="checkbox" name="likes" value="dance" ${fn:contains(fn:join(likes,","),"dance")?'checked':'' }>����
    <input type="checkbox" name="likes" value="basketball" ${fn:contains(fn:join(likes,","),"basketball")?'checked':'' }>����
    <input type="checkbox" name="likes" value="football" ${fn:contains(fn:join(likes,","),"football")?'checked':'' }>����  </body>
</html>
 --%>