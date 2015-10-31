<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>获取服务器端时间</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	window.onload = function(){
		document.getElementsByTagName("input")[0].onclick = function(){
			//发送服务器请求
			location.href = "${pageContext.request.contextPath}/timeServlet";
		}
		document.getElementsByTagName("input")[1].onclick = function(){
			//发送服务器请求返回服务器时间并设置到页面中
			
			//1、创建一个ajax对象；
			var ajax = createAJax();
			//2、设置onreadystatechange回调方法；
			ajax.onreadystatechange = function(){
				if(ajax.readyState == 4 && ajax.status == 200){
					document.getElementsByTagName("span")[0].innerHTML = ajax.responseText;
				}
			}
			
			//3、调用open方法，建立与服务器的连接；
			//async默认为true表示异步通信，如果设置为false则标识同步通信（在这行代码及其之后的内容将在执行完回调方法之后再会执行）
			ajax.open("get", "${pageContext.request.contextPath}/timeServlet?async=true", true);
			
			//4、调用send方法，发送请求和数据
			ajax.send();
			
			
		}
		
		//创建ajax
		function createAJax(){
			var ajax;
			if(XMLHttpRequest){
				//现代浏览器
				ajax = new XMLHttpRequest();
			} else {
				//ie7- 或非现代浏览器
				ajax = new ActiveXObject("Microsoft.XMLHTTP");
			}
			return ajax;
		}
	}
	</script>
  </head>
  
  <body>
  <div style="width:100%; text-align: center;">
    	当前时间为：<span>${currentTime}</span> <br><br>
    	<input type="button" value="同步获取当前时间">
    	《《《《《《》》》》》》
    	<input type="button" value="异步获取当前时间">
  </div>
  </body>
</html>
