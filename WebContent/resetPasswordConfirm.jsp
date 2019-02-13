<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/cyan.css">
<title>パスワード再設定確認</title>
</head>

<body>
<jsp:include page="header.jsp" />
<div id="contents">
<h1>パスワード再設定確認画面</h1>

<s:form action="ResetPasswordCompleteAction">
	<s:property value="#session.loginId" /><br>
	<s:property value="#session.concealedPassword" /><br>
	<s:submit value="パスワード再設定" class="submit_btn" />
</s:form>
<s:form action="ResetPasswordAction">
<s:submit value="戻る" class="submit_btn"/>
<s:hidden name="loginId" />
</s:form>
</div>

</body>
</html>