<html>
<head>
<title>添加待办项</title>

<script type="text/javascript">
	// 验证输入
	// 仅验证非空
	function validate(){
		var input = document.getElementById("content");
		var content = input.value;
		if(content.trim()!="") return true;
		input.placeholder="请填写内容";
		return false;
	}
</script>

</head>
<body>
<h5>添加待办项</h5>
<a href="#" onclick="history.go(-1)"><< back</a>

<form id="inputform" action="/addItem">

<label>事项内容：</label>
	<input type="text" id="content" name="content" value=""/><br/><br/>

<div class="form-actions">
	<input type="submit" onclick="return validate();" value="创建"/>
	</div>
</form>


</body>
</html>