<img id="code_123" alt="µãÎÒ¸ü»»" src="/crm/codeImg.action" onclick="change()">

function change(){
	var img1 = document.getElementById("code_123");
	img1.src="/crm/codeImg.action?i=" + new Date().getTime();
}
</script>