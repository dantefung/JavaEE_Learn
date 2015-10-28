<%
	request.setAttribute("path", request.getContextPath());
 %>

  <img src="${path }/image.action"  id="imageCode"  title="点我更换" onclick="change()"/>
function change()
{
		var imageCode = document.getElementById("imageCode");
		imageCode.setAttribute("src","${path}/image.action?i_="+new Date().getTime());
}