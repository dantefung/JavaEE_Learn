<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>basic</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.js"></script>
 	<script type="text/javascript">
 		// 类似window.onload = function(){}
    	$(document).ready(function()
    	{
	    	$("#btn1").click(function()
	    	{
	    		alert($("#div1").html());
	    	});
	
	        $("#btn2").click(function()
	        {
	            alert($(".divClass").text());
	        });

            $("#btn3").click(function()
            {
                //#获取div里面的title属性的值
                alert($("#div1").attr("title"));
            });

            $("#btn4").click(function()
            {
                //获取input的个数
                alert($("input").length);
            });

            $("#btn5").click(function()
            {
            	// 改变div里面的值
                alert($("#div1").html("改变了div里面的值了").text());
            });

            $("#btn6").click(function()
            {
				 alert($("#div1") + " ---- " + $("span"));
            });

    	});
    	
    </script>
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
	<style>
		.divClass{
			text-align: center;
			width: 100%;
		}
	</style>
  </head>
  
  <body>
    <div id="div1" class="divClass" title="div 的 title属性值">
    	<p>div 的内容</p>
    </div><br><br>
    <input type="text" value="输入框1"><br>
    <input type="text" value="输入框2"><br><br>
    <span>这是span的信息</span><br>
	
	<input type="button" value="#获取div里面的Html内容" id="btn1">
    <br><br>
	<input type="button" value=".获取div里面的纯文本内容" id="btn2">
    <br><br>
	<input type="button" value="#获取div里面的title属性的值" id="btn3">
    <br><br>
	<input type="button" value="获取input的个数" id="btn4">
    <br><br>
	<input type="button" value="改变div里面的值" id="btn5">
    <br><br>
	<input type="button" value="同时获取div和span" id="btn6">
    <br><br>
  </body>
</html>