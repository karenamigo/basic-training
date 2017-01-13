<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resource/bootstrap-3.3.7/css/bootstrap.css">
<script
	src="${pageContext.servletContext.contextPath}/resource/bootstrap-3.3.7/jquery/jquery-3.1.1.min.js"></script>
<script
	src="${pageContext.servletContext.contextPath}/resource/bootstrap-3.3.7/js/bootstrap.js"></script>
<script language="javascript"
	src="${pageContext.servletContext.contextPath}/resource/bootstrap-3.3.7/prototype.js"></script>
<script language="javascript"
	src="${pageContext.servletContext.contextPath }/resource/bootstrap-3.3.7/ChangeModeObj.js"></script>
<script language="javascript"
	src="${pageContext.servletContext.contextPath}/resource/js/Student.js"></script>
<script language="javascript"
	src="${pageContext.servletContext.contextPath }/resource/js/Course.js"></script>
<script language="javascript"
	src="${pageContext.servletContext.contextPath}/resource/js/TableBuilder.js"></script>
<script language="javascript"
	src="${pageContext.servletContext.contextPath}/resource/js/App.js"></script>
<script language="javascript"
	src="${pageContext.servletContext.contextPath}/resource/js/BuildUIObj.js"></script>
<script language="javascript"
	src="${pageContext.servletContext.contextPath}/resource/js/CreateElement.js"></script>
<script language="javascript"
	src="${pageContext.servletContext.contextPath}/resource/js/Ajax.js"></script>

</head>
<h3>List of Students</h3>
<body onload="App.list()">
	<div id="listStudentDiv"></div>
	<p style="padding: 7px"></p>
	<div id="stdcourseTableTitle"></div>
	<div id="listCourseDiv"></div>
</body>
</html>