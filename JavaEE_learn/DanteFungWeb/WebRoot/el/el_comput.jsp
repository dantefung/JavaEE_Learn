<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'el_comput.jsp' starting page</title>
    
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
  <br/>=============�������߼������===================<br/>
    <%
        // �ؼ���- ����
    	request.setAttribute("username", "aaa");
        request.setAttribute("password", "123");
    %>
    ${username=='aaa' && password=='123' }
    
    <br/>==============empty�����================<br/>
    <%
        request.setAttribute("list", null);
        //request.setAttribute("list", new ArrayList());
    %>
    ${empty(list) }
    
    <br/>=============��Ԫ�����==================<br/>
   <%-- el ���ʽ��֧���ַ��������� 
        �����Զ���el���ʽ����������.
   --%>
    ${user !=null? user.name : '' }
    
    <br/>=============��Ԫ����������ݻ��ԣ�==============<br/>
    <%
        request.setAttribute("gender", "female");
    %>
    <input type="radio" name="gender" value="male" ${gender=='male'? 'checked' : '' }>��
    <input type="radio" name="gender" value="female" ${gender=='female'? 'checked' : '' }>Ů
    
    <br/>============��Ԫ���ʽ�����ݻ��ԣ� el����==============<br/>
    <%
      // request.setAttribute("likes", new String("dance", "sing"));
      request.setAttribute("likes", new String("dance"));
    %>
    
    <input type="checkbox" name="likes" value="">����
    <input type="checkbox" name="likes" value="">����
    <input type="checkbox" name="likes" value="">����
    <input type="checkbox" name="likes" value="">����
  </body>
</html>
