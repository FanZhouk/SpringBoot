
<html>
<head>

<title>待办事项列表</title>

<script type="text/javascript">
	// 跳转至添加页面
	function add(){
		location.href="/addItemPage";
	}

	// 完成项目
	function done(item){
		location.href="/finish?id="+item.name;
	}
	
	// 置顶项目
	function top(item){
		location.href="/top?id="+item.name;
	}
	
	// 跳转至待办项目页面
	function data(){
		location.href="/index";
	}
	
	// 跳转至所有项目页面
	function dataAll(){
		location.href="/dataAll";
	}
</script>

</head>
<body>
<h4>待办事项列表</h4>
<a href="#" onclick="data()"/>待办
<a href="#" onclick="dataAll()" style="margin-left:10px"/>全部
<a href="#" onclick="add()" style="margin-left:150px">创建事项</a>
<table border=0>
	<tr><th width="200px">事项</th><th>创建时间</th>
	<#list data as u>
		<tr>
			<td>${u.content}
			<td><font color="#AAAAAA">${u.createtime?date}&nbsp;${u.createtime?time}<font>
			<#if u.finishflag=0>
				<td><a name="${u.id}" href="#" onclick="done(this)">完成</a>
					<a name="${u.id}" href="#" onclick="top(this)" title="置顶" style="margin-left:10px">↑</a>
			</#if>
	</#list>
</table>
</body>
</html>