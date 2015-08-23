<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.dantefung.domain.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'sun_core.jsp' starting page</title>
    
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
	<br/>==========c:out==================<br/>
	escapeXml="false" 非转义输出:
	<br/>
	<c:out value="<a href=''>点点</a>" escapeXml="false"></c:out>  <br/><br/>
	
	普通输出：
	<br/>
	<c:out value="adddd<br/>" escapeXml="false"></c:out>  <br/>
	
	escapeXml="true" 转义输出：
	<br/>
	<c:out value="<a href=''>点点</a>" escapeXml="true"></c:out>
	<%-- escapeXml 是否转义输出--%>
	
	<br/>===============c:set---可操作各个域  javabean Map集合 ===================<br/>
	<c:set var="dd" value="ddvalue" scope="page"></c:set>
	${dd }<br/>
	
	<% 
		Map map = new HashMap();
	    request.setAttribute("map", map);
	%>
	<c:set property="dd" value="xxx" target="${map }"/>
	${map.dd } <br/>
	
	<%
	   Person p = new Person();
	   request.setAttribute("p", p);
	%>
	<c:set property="name" value="namevalue" target="${p }"></c:set>
  	<c:out value="${p.name }"></c:out>
  
    <br/>======================c:catch==============<br/>
     <c:catch var="myex">
      <%int i =10/0; %>   
     </c:catch>
     
     Exception: <c:out value="${myex }"></c:out><br/>
     Exception myex.getMessage: <c:out value="${myex.message }"></c:out><br/>
     Exception myex.cause: <c:out value="${myex.cause }"></c:out><br/>
     Exception myex.stackTrace: <c:out value="${myex.stackTrace}"></c:out>
  
  	<br/>===============c:if============<br/>
  	<c:if var="aaa" test="${user==null}" scope="page"></c:if>
  	${aaa }
  
    <br/>==============c:choose  ( if else)============<br/>
    <c:set value="2" var="count"></c:set>
    <c:choose>
    	<c:when test="${count == 0 }">
    	    sorry,there is not the records you wanted.
    	</c:when>
    	
    	<c:otherwise>
    	     符合您要求的记录共有${count }条
    	</c:otherwise>
    </c:choose>
  </body>
  
     <br/>==================c:foreach=============<br/>
    <c:forEach var="i" begin="1" end="9" step="1" >
    ${i }
  </c:forEach>
  <br/>============c:foreach 实现表格隔行变色===========

<%
  	List list = new ArrayList();
  	list.add("aaa");
  	list.add("bbb");
  	list.add("ccc");
  	list.add("ddd");
  	request.setAttribute("list", list);
  %>
<style>
.odd {
	background-color: blue;
}

.even {
	background-color: red;
}

tr:hover {
	background-color: green;
}
//
如果不支持鼠标放上去变色，可从tomact默认网页上拷头
<!DOCTYPE
 
html
>
</style>
<table>
	<c:forEach var="str" items="${list}" varStatus="status">
		<tr width="20%" height="10px" class="${status.count%2==0?'even':'odd' }">
			<td>${status} ::: ${str }</td>
		</tr>
	</c:forEach>
	
</table>

<br />___________________________________
<% 
String atts[] = new String [5]; 
atts[0]="hello"; 
atts[1]="this"; 
atts[2]="is"; 
atts[3]="a";
atts[4]="pen"; 
request.setAttribute("atts", atts);
%>
<c:forEach items="${atts}" var="item" varStatus="s">
	<h2>
		<c:out value="${item}" />
		的四种属性：
	</h2>
	index：${s.index}</br>
	count：${s.count}</br>
	first：${s.first}</br>
	last：${s.last}</br>
</c:forEach>
<br />
<br />=========c:url=============
<br />
<c:url var="url" value="/index.jsp">
	<c:param name="name" value="中国"></c:param>
</c:url>
<a href="${url }">购买</a>

<br />
<br />
<br />___c:forTokens __delimiter__字符串的分割__<br/>
<% String data="aa,bb,cc,dd";
      request.setAttribute("data",data);
      %>
<c:forTokens items="${data}" delims="\," var="c">${c}
      <%
      String ss=(String)pageContext.getAttribute("c");
      out.write("kkk_"+ss);
      %>
</c:forTokens>
</html>
