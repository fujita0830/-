<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/cyan.css">
<title>パスワード再設定</title>
</head>
<body>
<jsp:include page="header.jsp" />
<div id="contents">
	<h1>パスワード再設定画面</h1>
	<s:if test="!#session.loginIdErrorMessageList.isEmpty()">
		<div class="error">
			<div class="error-message">
				<s:iterator value="#session.loginIdErrorMessageList"><s:property /><br></s:iterator>
			</div>
		</div>
	</s:if>
	<s:if test="!#session.passwordErrorMessageList.isEmpty()">
		<div class="error">
			<div class="error-message">
				<s:iterator value="#session.passwordErrorMessageList"><s:property /><br></s:iterator>
			</div>
		</div>
	</s:if>
	<s:if test="!#session.passwordIncorrectErrorMessageList.isEmpty()">
		<div class="error">
			<div class="error-message">
				<s:iterator value="#session.passwordIncorrectErrorMessageList"><s:property /><br></s:iterator>
			</div>
		</div>
	</s:if>
	<s:if test="!#session.newPasswordErrorMessageList.isEmpty()">
		<div class="error">
			<div class="error-message">
				<s:iterator value="#session.newPasswordErrorMessageList"><s:property /><br></s:iterator>
			</div>
		</div>
	</s:if>
	<s:if test="!#session.reConfirmationNewPasswordErrorMessageList.isEmpty()">
		<div class="error">
			<div class="error-message">
				<s:iterator value="#session.reConfirmationNewPasswordErrorMessageList"><s:property /><br></s:iterator>
			</div>
		</div>
	</s:if>
	<s:if test="!#session.newPasswordIncorrectErrorMessageList.isEmpty()">
		<div class="error">
			<div class="error-message">
				<s:iterator value="#session.newPasswordIncorrectErrorMessageList"><s:property /><br></s:iterator>
			</div>
		</div>
	</s:if>
	<s:form action="ResetPasswordConfirmAction">
		<table class="vertical-list-table">
			<tr>
				<th scope="row"><s:label value="ユーザーID"/></th>
				<td><s:textfield name="loginId" placeholder="ユーザーID" class="txt" value="%{#session.loginId}" /></td>
			</tr>
			<tr>
				<th scope="row"><s:label value="現在のパスワード"/></th>
				<td><s:password name="password" placeholder="現在のパスワード" class="txt" /></td>
			</tr>
			<tr>
				<th scope="row"><s:label value="新しいパスワード"/></th>
				<td><s:password name="newPassword" placeholder="新しいパスワード" class="txt" /></td>
			<tr>
				<th scope="row"><s:label value="新しいパスワード（再確認）"/></th>
				<td><s:password name="reConfirmationPassword" placeholder="新しいパスワード（再確認）" class="txt" /></td>
			</tr>
		</table>
		<div class="submit_btn_box">
			<s:submit value="確認" class="submit_btn" />
		</div>
	</s:form>
</div>

</body>
</html>