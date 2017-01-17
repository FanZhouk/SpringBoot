<html>
<head>
<title>Welcome!</title>
</head>
<body>
<h1>Welcome ${name}!</h1>

<table border=1>
	<tr><th>姓名<th>密码
	<#list data as u>
		<tr><td>${u.username}<td>${u.password}
	</#list>
</table>
</body>
</html>