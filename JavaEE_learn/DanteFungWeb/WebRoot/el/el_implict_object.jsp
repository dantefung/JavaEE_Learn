<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>��ʽ����</title>
    
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
  
    <br/>====================ʹ��el���ʽ�������ָ������==============<br/>
    <% 
        pageContext.setAttribute("aa", "123");
    %>
    ${aa }<br/>
    ${pageScope.aa }<br/>
    ${sessionScope.user !=null }
    
    <br/>======================��ȡ�������������������Map����===============<br/>
    <%--${params.name}�ȼ���request.getParameterValues��"name"����������ڻ�ȡ�ͻ��˵��������ݣ���ҳ��ĸ�ѡ���ֵ��

                    ע�⣺${requestScope.name} �ȼ��� request.getAttribute("name")

        ${name}û��ָ�����ĸ���������ȡ���ݣ��ᰴ˳��pageScope��requestScope��sessionScope��applicationScope������С��������ʼ����Ϊname��ֵ��

                     ���ֵõ����Ե�EL���ʽ��

        ${scope.attribute}������scopeָpageSocpe��requestScope��sessionScope��applicationScope��attributeָ�ľ�������ĳ��scope�����õ������ˡ� --%>
    ${param.name }<br/><%--${param.name}�ȼ���request.getParameter("name")��{param[name]}Ҳ��һ���� --%>
    ${paramValues.name[0] }
    ${paramValues.name[1] }
    
    <br/>======================��ȡ����ͷ=====================<br/>
    ${header['Accept-Language'] }
    
    <br/>=======================��ȡcookies===============<br/>
    ${cookies.JSESSIONID.name }<br/>
    ${cookies.JSESSIONID.value }
    
    ${initParam.xx }
  </body>
</html>
