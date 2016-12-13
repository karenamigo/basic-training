<%@ taglib prefix="ex" uri="WEB-INF/custom.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>A sample custom tag</title>
</head>
<body>
	<table>
		<tr bgcolor="pink">
			<th>學號</th>
			<th>姓名</th>
			<th>地址</th>
			<th>年齡</th>
		</tr>
		<ex:Hello />
	</table>
</body>
</html>