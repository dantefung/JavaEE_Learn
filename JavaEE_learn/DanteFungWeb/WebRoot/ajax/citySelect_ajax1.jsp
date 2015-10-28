<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>ajax异步选择城市</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript">
		// 这样写，起到解耦的作用
		window.onload = function()
		{
			var method = "get";
			var url = "${pageContext.request.contextPath}/cityServlet?country=";
			// 给select id="country"注册事件
			document.getElementById("country").onchange = function()
			{
				// 获取选中的option的value[this:document.getElementById("country")]
				var opt = this.options[this.selectedIndex].value;
				// 判断
				if( "中国" == opt )
				{
					// 显示中国的城市
					// 1.数据从哪里来？服务器
					// 1.1 发送请求
					// (1) 创建ajax对象
					var ajax = createAjax();
					// (2) 设置回调函数
					ajax.onreadystatechange = function()
					{
						// 2.数据到哪里去？jsp页面
						 if(ajax.readyState==4)
						    {
						    	// 2.1从服务器的response获得数据
						    	var jso = ajax.responseText;
						    	alert(jso);
						    }
					}
					// (3) 与服务器建立连接
					ajax.open(method, url+opt+"&method="+method, true);
					// (4)  发送请求
					ajax.send();
				}
				else// 美国
				{
					// 显示美国的城市
				}
			}
			
			function createAjax()
			{
				try
				{
					// Firefox, Opera 8.0+, Safari
					var ajax = new XMLHttpRequest();
				}
				catch(e)// IE7-
				{
					var ajax = new ActiveXObject("Microsoft.XMLHTTP");
				}
				return ajax;
			}
		}
	</script>
  </head>
  
  <body>
    <div style="width:100%;text-align: center;margin-top: 30px;">
    	国家：<select id="country" style="width:160px;">
    		<option>请选择</option>
    		<option value="中国">中国</option>
    		<option value="美国">美国</option>
    	</select>
    	&nbsp;&nbsp;---&nbsp;&nbsp;
    	城市：<select id="city"></select>
    </div>
  </body>
</html>
