<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>分页显示员工数据</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <h3>分页显示</h3>
    <table border="1" width="700px">
    	<tr>
    		<th>编号</th>
    		<th>员工姓名</th>
    		<th>性别</th>
    		<th>职位</th>
    		<th>邮箱</th>
    		<th>薪水</th>
    	</tr>
    	<c:forEach items="${requestScope.pageBean.data }" var="emp">
    	<tr>
    		<td>${emp.id }</td>
    		<td>${emp.name }</td>
    		<td>${emp.gender }</td>
    		<td>${emp.title }</td>
    		<td>${emp.email }</td>
    		<td>${emp.salary }</td>
    	</tr>
    	</c:forEach>
    	<tr>
    		<td colspan="6">
    		<%--
    		如果是当前页是首页，那么不能点击”首页 上一页“链接
    		如果当前页是尾页，那么不能点击”下一页 尾页“链接
    		 --%>
    		 <c:choose>
    		 	<c:when test="${pageBean.curPage==pageBean.firstPage }">
    		 		首页&nbsp;上一页
    		 	</c:when>
    		 	<c:otherwise>
    		 		<a href="${pageContext.request.contextPath }/PageEmpServlet?curPage=${pageBean.firstPage }&pageSize=${pageBean.pageSize}">首页</a>&nbsp;
    				<a href="${pageContext.request.contextPath }/PageEmpServlet?curPage=${pageBean.prePage }&pageSize=${pageBean.pageSize}">上一页</a>&nbsp;
    		 	</c:otherwise>
    		 </c:choose>
    		 <c:choose>
    		 	<c:when test="${pageBean.curPage==pageBean.totalPage }">
    		 		下一页&nbsp;尾页
    		 	</c:when>
    		 	<c:otherwise>
    		 		<a href="${pageContext.request.contextPath }/PageEmpServlet?curPage=${pageBean.nextPage }&pageSize=${pageBean.pageSize}">下一页</a>&nbsp;
    				<a href="${pageContext.request.contextPath }/PageEmpServlet?curPage=${pageBean.totalPage }&pageSize=${pageBean.pageSize}">尾页</a>&nbsp;
    		 	</c:otherwise>
    		 </c:choose>
    		当前为第${pageBean.curPage }页,
    		共${pageBean.totalPage }页&nbsp;
    		共${pageBean.totalCount }条记录&nbsp;
    		每页显示<input type="text" name="pageSize" id="pageSize" size="2" value="${pageBean.pageSize }" onblur="changePageSize()"/>条&nbsp;
    		跳到到第<input type="text" name="toPage" size="2" value="${pageBean.curPage }"  id="toPage" onblur="toPage()"/>页
    		</td>
    	</tr>
    </table>
    
    <script type="text/javascript">
    	//跳转到第几页
    	function toPage(){
    		var toPage = document.getElementById("toPage").value;
    		var reg = /^[1-9][0-9]*$/;
    		//数据合法性校验
    		if(!reg.test(toPage)){
    			alert("输入有误!请输入数字");
    			return;
    		}
    		//不能超过总页数
    		var totalPage = "${pageBean.totalPage}";
    		if(toPage>totalPage){
    			alert("不能超过总页数");
    			return;
    		}
    		
    		//跳转页面
    		var url ="${pageContext.request.contextPath}/PageEmpServlet?curPage="+toPage;
    		window.location.href=url;
    		
    	}
    	
    	//改变每页记录数
    	function changePageSize(){
    		var pageSize = document.getElementById("pageSize").value;
    		//数据合法性校验
    		var reg = /^[1-9][0-9]*$/;
    		if(!reg.test(pageSize)){
    			alert("输入有误!请输入数字");
    			return;
    		}
    		//跳转页面,并且传递pageSize参数后台
    		var url ="${pageContext.request.contextPath}/PageEmpServlet?pageSize="+pageSize;
    		window.location.href=url;
    		
    	}
    </script>
  </body>
</html>
