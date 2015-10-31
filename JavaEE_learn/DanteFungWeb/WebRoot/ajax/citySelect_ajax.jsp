<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		window.onload = function(){
			document.getElementById("country").onchange = function(){
				var sendType = "post";
				var dataType = "xml";
				//1、获取国家的值
				var country = document.getElementById("country").value;
				if("请选择"!=country){
					//2、根据国家获取该国家的城市列表并且将返回的城市列表设置到城市下拉框中
					//1、创建一个ajax对象；
					var ajax = createAJax();
					//2、设置onreadystatechange回调方法；
					ajax.onreadystatechange = function(){
						if(ajax.readyState == 4 && ajax.status == 200){
							var citySelect = document.getElementById("city");
							//清空城市列表
							citySelect.length = 0;
							if("xml" == dataType){
								var xmlDocument = ajax.responseXML;
								var cities = xmlDocument.getElementsByTagName("city");//<city>bj</city>
								//遍历城市列表
								for(var i = 0; i < cities.length; i++){
									var opt = document.createElement("option");
									opt.text = cities[i].firstChild.nodeValue;
									citySelect.appendChild(opt);
								}
							} else {
								//返回的数据格式是json，将json格式字符串转为json对象
								var jso = eval("(" + ajax.responseText + ")");
								
								//{"cities":[{"city":"北京"},{"city":"上海"}]}
								var cities = jso.cities;
								for(var j = 0; j < cities.length; j++){
									var opt = document.createElement("option");
									opt.text = cities[j].city;
									citySelect.appendChild(opt);
								}
							}
							
						}
					}
					//3、调用open方法，建立与服务器的连接；
					ajax.open(sendType, "${pageContext.request.contextPath}/cityServlet?sendType=" + sendType + "&dataType=" + dataType, true);
					//如果需要使用post传递比较多的参数需要设置content-type
					ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
					//4、调用send方法，发送请求和数据
					ajax.send("country=" + country+"&firstName=bill");
					
				} else {
					//清空城市列表
					document.getElementById("city").length = 0;
				}
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
