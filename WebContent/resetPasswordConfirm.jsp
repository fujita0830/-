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
	<table class="vertical-list-table">
		<tr>
		<th scope="row"><s:label value="ユーザーID"/></th>
			<td><s:property value="#session.loginId" /></td>
		</tr>
		<tr>
			<th scope="row"><s:label value="新しいパスワード"/></th>
			<td><s:property value="#session.concealedPassword" /></td>
	</table><br>
	<div class="submit_btn_box"><s:submit value="パスワード再設定" class="submit_btn" /></div>
</s:form>
<s:form action="ResetPasswordAction">
<div class="submit_btn_box"><s:submit value="戻る" class="submit_btn"/></div>
<s:hidden name="loginId"/>
</s:form>
</div>

</body>
</html>