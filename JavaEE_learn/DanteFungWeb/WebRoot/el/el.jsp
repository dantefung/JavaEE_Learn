<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ page import="com.dantefung.domain.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>el_��ȡ����</title>
    
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
  <br/>======================��ȡ���д������==========================<br/>
  ��ͨServlet�е����ݴ���el���ʽ:<br/>
    <% 
        String data = "abcd";
        request.setAttribute("data", data);
    %>
    ${data } <br/><%-- ��page-> request-> session-> application(ĳ������������sServletContext)-- %>
   <%-- pageContext.findAttribute("data") ���� ���ַ���--%>
   
   <br/>==================��ȡ����javabean�������========================<br/>
  <% 
      Person p = new Person();
      p.setName("aaaa");
      
      request.setAttribute("person", p);
  %>
  �Թؼ��ִ����в���:<br/>
  ${person.name }<%-- ����getName() --%>
  
  <br/>===============��ȡ����javabean�е�javabean������===================<br/>
  <%
      Person p1 = new Person();
      Address a = new Address();
      a.setCity("�Ϻ�");
      p1.setAddress(a);
      
      request.setAttribute("p1", p1);     
  %>
  ${p1.address.city}
  <%-- ���ȴ�4�������ҵ��ؼ���p1��Ȼ����Ӧ���ҵ�Person���󣬽���ȡ���ö��������address���󣬽���ȡ��address������city --%>
  
  <br/>===================��ȡ����list�е�����===================<br/>
      <%
          List list = new ArrayList();
          list.add(new Person("aaa"));
          list.add(new Person("bbb"));
          request.setAttribute("list", list);
      %>
      <br/> ${list['1'].name }<br/>
  
  <br/>==================��ȡ����map�е�����==================<br/>
     <%
         Map map = new HashMap();
         map.put("aa", new Person("aaaaaaaa"));
         map.put("bb", new Person("bbbbbbbb"));
         map.put("cc", new Person("ccccccccc"));
         map.put("dd", new Person("dddddddd"));
         map.put("ll", new Person("eeeeeeeeee"));
         request.setAttribute("mappp", map);
     %>    
     ${mappp.cc.name }
 <br/>=======================�����������õ�el���ʽ===============<br/>
 ${pageContext.request.contextPath }<%-- ��õ�ǰ���̵ĵ�ַ --%>
 <a href="${pageContext.request.contextPath }/index.jsp">���</a>
  </body>
  
</html>
