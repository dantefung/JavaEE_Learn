<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>隐式对象</title>
    
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
   
   ${pageContext }
  
    <br/>====================使用el表达式对象访问指定的域==============<br/>
    <% 
        pageContext.setAttribute("aa", "123");
    %>
    ${aa }<br/>
    ${pageScope.aa }<br/>
    ${sessionScope.user !=null }
    
    <br/>======================获取保存了所有请求参数的Map对象===============<br/>
    <%--${params.name}等价于request.getParameterValues（"name"），大多用于获取客户端的数组数据，如页面的复选框的值。

                    注意：${requestScope.name} 等价于 request.getAttribute("name")

        ${name}没有指定从哪个作用域中取数据，会按顺序pageScope、requestScope、sessionScope、applicationScope，从最小的作用域开始搜索为name的值。

                     各种得到属性的EL表达式：

        ${scope.attribute}，其中scope指pageSocpe、requestScope、sessionScope、applicationScope，attribute指的就是你在某个scope中设置的属性了。 --%>
    ${param.name }<br/><%--${param.name}等价于request.getParameter("name")，{param[name]}也是一样的 --%>
    ${paramValues.name[0] }
    ${paramValues.name[1] }
    
    <br/>======================获取请求头=====================<br/>
    ${header['Accept-Language'] }
    
    <br/>=======================获取cookies===============<br/>
    ${cookies.JSESSIONID.name }<br/>
    ${cookies.JSESSIONID.value }
    
    ${initParam.xx }
  </body>
</html>
