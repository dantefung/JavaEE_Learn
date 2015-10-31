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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			// 给select id="country"注册事件
			$("#country").change(function()
			{
				// 获取选中的option的value[this:document.getElementById("country")]
			   var country = $(this).val();
				// 判断
				if( "请选择" != country )
				{
						showCities(country);
				}
				else// 请选择
				{
						// 清空城市列表
						$("#city").empty();
				}
			});
		});
			
		function showCities(country)
		{
			var dataType = "xml";
			var method = "post";
			//alert(country);
			// 传一个匿名的js对象
			// $.get(url, [data], [callback], [type])
			$.post(
				"${pageContext.request.contextPath}/cityServlet",
				{"country":country,"method":method,"dataType":dataType},
				function(data)
				{
					//alert(data);
					    if( data != null && data != "" && data != undefined )
					    {
					    	var $citySelect = $("#city");
					    	$citySelect.empty();
					    	
					    	if( "json" == dataType)
					    	{
						    	var cities = data.cities;
						    	$.each(cities, function(index, jso)
						    	{
						    		$citySelect.append("<option>" + jso.city +"</option>");
						    	});
					    	}
					    	else
					    	{
					    		var $cities = $(data);
					    		$cities.find("city").each(function(index, city)
					    		{
					    			// city 是dom对象	
					    			 $citySelect.append("<option>" + $(city).text() +"</option>");
					    		});
					    	}
					    	
					    }
				},
				dataType
			);

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
