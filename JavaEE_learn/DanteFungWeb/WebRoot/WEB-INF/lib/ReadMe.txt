<suncheng:out value="java.lang.Object"/>
<suncheng:out>${name}</suncheng:out>
	out  ：标签用户输出，目前只能输出String类型。
	value：如果没有标签体标识必须指定，支持${xx}el表达式。


<suncheng:href url="http://www.xx.com/">点我</suncheng:href>
	href：超链接地址。
	url ：必须指定，支持${xx}el表达式。
	异常：
		java.lang.NullPointerException：
		描述：标签体内容不能为空。


<suncheng:set/> 和 <suncheng:set var="user" property="id" target="${user}" scope="application">123</suncheng:set>
	set：实体类属性赋值。
	target：实体类，必须指定。
	property：实体类属性，必须指定。
	var：别名，必须指定。
	scope：范围，支持：page、request、session和application。
	value：值,如果没有标签体必须指定，有标签体可不指定(如指定按照标签体内容赋值)。
	
	
<suncheng:ip/>
	没有标签体，返回客户端的IP地址。
	
	
<suncheng:port/>
	没有标签体，返回客户端的端口号。
	
	
<suncheng:uri/>
	没有标签体，返回当前路径的HTTP相对路径。
	
	
<suncheng:url/>
	没有标签体，返回当前访问全路径。
	
	
<suncheng:basePath/>
	没有标签体，返回项目的根路径
	

<suncheng:remove var="user"/>
	没有标签体，移除page、request、session和application范围中内容
	提醒：目前只能移除对象、字符串，对象属性移除后期添加。
	
	

<suncheng:if test="true | false"> xx </suncheng:if>
	test：用户指定是否显示，必须指定。



<suncheng:choose>
	<suncheng:when test="false">
		adasdasd
	</suncheng:when>
	<suncheng:when test="false">
		3424
	</suncheng:when>
	<suncheng:when test="false">
		789
	</suncheng:when>
	<suncheng:othenwise>
		123
	</suncheng:othenwise>
</suncheng:choose>
	test：用户指定是否显示，必须指定。
	<suncheng:when test="true | false" 相当于if，多个when相当于else if
	<suncheng:othenwise> 表示否则,相当于else


<suncheng:forEach items="${arr}" var="u" varStatus="ss">
	<suncheng:out value="${u}"/>
	<suncheng:out>${ss.index}</suncheng:out>
	<br/>
</suncheng:forEach>
	items：要遍历的类型，目前支持：数组(包含基本类型)、List、Map、Set。
	var  ：别名。
  varStatus：如果要得到数组下标，定义这个参数。



/*
	使用方法：
		1、把suncheng-jstl.jar导入到web工程的lib下。
		2、JSP页面第二行键入(prefix="名字可以自己取")：
			<%@ taglib uri="/suncheng" prefix="suncheng" %> 
		3、<suncheng: JSP会提示标签。
		
	致用户：
		此jstl标签兼容JSP2.0+，请大家放心使用。
		后期标签继续添加，先暂且放一下，有bug请大家谅解，本人未严谨测试，欢迎各路英豪批评、指出！

			版权归开发者“孙成”所有，传播请注明作者，请勿用于交易、商业等其它用途！
*/