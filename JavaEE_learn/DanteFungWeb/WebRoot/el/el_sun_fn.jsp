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
    
    <title>sun公司的el函数库</title>
    
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
   <br/>================大小写转换： AAAAAAAAAAAA----->aaaaaaaaaa=============<br/>
    ${fn:toLowerCase("AAAAAAAA") }
    
    <%
        request.setAttribute("arr", new String[5]);
    %>
    <br/>============得到域中的数组的长度===================<br/>
    ${fn:length(arr) }
    
    <br/>=============遍历域中的list的内容==========<br/>
    <%
    	List list = new ArrayList();
        list.add("aa");	
        list.add("bb");
        request.setAttribute("list", list);
    %>
    
    <c:forEach var="i" begin="0" end="${fn:length(list) }">
    ${list[i]}
    </c:forEach>
    
    <br/>================字符串的拼接===================<br/>
   
    
    ${fn:join(fn:split("www,dantefung,com",","),".") }
    
    <br/>===============某个字符串里面是否包含子字符串=============<br/>
    ${fn:contains("aaaaaaaaabbbbbbbbbbbbbb","aa") }
    
    <br/>=============转义输出==================<br/>
    ${fn:escapeXml("<a href=''>点点</a>")}
     <br/>---------------取出用户填写的爱好回显------------------------<br/>
     <%
        request.setAttribute("likes", new String[]{"football","sing"});
    %>
     <input type="checkbox" name="likes" value="sing" ${fn:contains(fn:join(likes,","),"sing")?'checked':'' }>唱歌
    <input type="checkbox" name="likes" value="dance" ${fn:contains(fn:join(likes,","),"dance")?'checked':'' }>跳舞
    <input type="checkbox" name="likes" value="basketball" ${fn:contains(fn:join(likes,","),"basketball")?'checked':'' }>蓝球
    <input type="checkbox" name="likes" value="football" ${fn:contains(fn:join(likes,","),"football")?'checked':'' }>足球  </body>
</html>
 --%>