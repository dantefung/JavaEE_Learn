<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>title</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body><font color="red">${errors }</font>
    <form action="${pageContext.request.contextPath }/UploadServlet" method="post" enctype="multipart/form-data" >
    	<table border="1">                                                         <!-- encoding type -->                                                               
    	<thead id="thead">
    		<tr>
    		   <td><span>请选择上传的文件：</span></td>
    		   <td><input type="file" name="attach"/></td>
    		</tr>
    	</thead>	
    	    <tr>
    	        <td>文件描述：</td>
    	    	<!-- <td><textarea ></textarea></td> -->  <!-- 注意：这个是无法作为form field 上传内容到后台的 -->
    	    	<td><input type="text" name="desc"/></td>
    	    </tr>
    		<tr>
    			<td><input type="button" value="添加" onclick="addItems()"/></td>
    			<td><input type="button" value="删除" onclick="del()"/></td>
    		</tr>
    		<tr><td colspan="2"><input type="submit" value="提交"/></td></tr>
    	</table>
    </form>
    
  </body>
  <script>
       var count=1;
       function addItems()
       {
    	   var thead = document.getElementById("thead");
           var tr = document.createElement("tr");
           var td1 = document.createElement("td");
           var td2 = document.createElement("td");
           var input = document.createElement("input");
           input.setAttribute("type", "file");
           input.setAttribute("name","attach");
           var span = document.createElement("span");
           span.innerHTML="请选择上传的文件：";
           
           td1.appendChild(span);
           td2.appendChild(input);
           tr.appendChild(td1);
           tr.appendChild(td2);
           thead.appendChild(tr);
           
           count++;
       }
       
       function del()
       {
    	   var thead = document.getElementById("thead");
    	   if(count>1)
    	   {
    	       thead.removeChild(thead.lastChild);
    	       count--;
    	   }
       }
       
       function checkAll()
       {
    	   return true;
       }
  
  </script>
</html>
