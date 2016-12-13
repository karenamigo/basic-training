<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="ex" uri="WEB-INF/custom.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>A sample custom tag</title>
</head>
<body>
	<form>
		<table bgcolor="white">
			<tr bgcolor="pink">
				<th>ID</th>
				<th>姓名</th>
				<th>年齡</th>
				<th>地址</th>
				<th>科系</th>
				<ex:std />
			</tr>
		</table>
	</form>
</body>
</html>