<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/style.css">
<title>ログイン</title>
<script>
function goLoginAction() {
	document.getElementById("form").action="LoginAction";
}
function goCreateUserAction() {
	document.getElementById("form").action="CreateUserAction";
}
function goResetPasswordAction() {
	document.getElementById("form").action="ResetPasswordAction";
}
</script>
</head>
<body>
<jsp:include page="header.jsp" />
<div id="contents">
<h1>ログイン画面</h1>
<s:form id="form" action="LoginAction">
</div>
</body>
</html>