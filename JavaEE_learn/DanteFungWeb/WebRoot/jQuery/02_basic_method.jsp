<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>basic_method</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.js"></script>
 <script type="text/javascript">
    	$(document).ready(function(){
    		
    		$("#btn1").click(function()
    		{
    			$("#select").empty();
    		});
    		
    		// each的实例调用
    		$("#btn2").click(function()
    		{
    			/* 查看jQuery的each()方法的源码：
    			 each: function( callback, args ) {
						return jQuery.each( this, callback, args );
					}
				 each: function( obj, callback, args ) {};
				 this 就是  obj
    			  ... ... 
    			value = callback.apply( obj[ i ], args );
    			  ... ...
    			 for ( i in obj ) {  
					value = callback.call( obj[ i ], i, obj[ i ] );
					if ( value === false ) {
						break;
					}
				 }
    			 ... ... 
    			*/
    			$("input[type='text']").each(function(index, obj)
    			{
	    				/*
	    				*  jQuery回调时，传回来两个参数：
	    				*   1.当前dom对象的索引值
	    				*   2.当前的dom对象
	    				*/
	    				alert($(obj).val());
    			});
    			
    			// jQuery的静态调用
    			$("#btn3").click(function()
    			{
    					var array = ["张三",1,false];
    					$(array).each(function(index, args)
    					{
    						alert(index + " == " + args);
    					});
    			});
    			
    			$("#btn4").click(function()
    			{
    				$("#input2").focus();
    			});
    			
    			$("#btn5").click(function()
    			{
	    				/*
	    				*  jQuery回调时，传回来两个参数：
	    				*   1.当前dom对象的索引值
	    				*   2.当前的dom对象
	    				*/
	    				alert($.trim($("#input1").val()));
    			});
    			
    			
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
  <div class="divClass">
    <br>
    <input type="text" id="input1" value="输入框1"><br>
    <input type="text" id="input2" value="输入框2"><br><br>
    <select id="select">
    	<option>Java</option>
    	<option>IOS</option>
    	<option>UI</option>
    </select>
    <input type="button" value="empty()清空select" id="btn1">
    <br><br>
    <input type="button" value="each()遍历所有输入框的值" id="btn2">
    <br><br>
    <input type="button" value="$.each()遍历数组" id="btn3">
    <br><br>
    <input type="button" value="focus()定焦到输入框2" id="btn4">
    <br><br>
    <input type="button" value="$.trim()输入框中是否空字符串" id="btn5">
    </div>
  </body>
</html>