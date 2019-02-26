<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="./css/cyan.css">
		<title>登録内容確認</title>
	</head>
	<script>
		function goCreateUserAction(){
			document.getElementById("createUserForm").action="CreateUserAction";
		}
		function goCreateUserCompleteAction(){
			document.getElementById("createUserForm").action="CreateUserCompleteAction";
		}
	</script>
	<body>
		<jsp:include page="header.jsp" />
		<div id="contents">
			<h1>ユーザー情報入力確認画面</h1>
			<table class="vertical-list-table">
				<tr>
					<th scope="row"><s:label value="姓"/></th>
					<td><s:property value="#session.createUserFamilyName"/></td>
				</tr>
				<tr>
					<th scope="row"><s:label value="名"/></th>
					<td><s:property value="#session.createUserFirstName"/></td>
				</tr>
				<tr>
					<th scope="row"><s:label value="姓ふりがな"/></th>
					<td><s:property value="#session.createUserFamilyNameKana"/></td>
				</tr>
				<tr>
					<th scope="row"><s:label value="名ふりがな"/></th>
					<td><s:property value="#session.createUserFirstNameKana"/></td>
				</tr>
				<tr>
					<th scope="row"><s:label value="性別"/></th>
					<td><s:property value="#session.createUserSex"/></td>
				</tr>
				<tr>
					<th scope="row"><s:label value="メールアドレス"/></th>
					<td><s:property value="#session.createUserEmail"/></td>
				</tr>
				<tr>
					<th scope="row"><s:label value="ユーザーID"/>
					<td><s:property value="#session.createUserLoginId"/>
				</tr>
				<tr>
					<th scope="row"><s:label value="パスワード"/>
					<td><s:property value="#session.createUserPassword"/>
				</tr>
			</table>
			<s:form id="createUserForm" name="form">
				<div class="submit_btn_box">
					<div id=".contents-btn-set">
						<s:submit value="登録" class="submit_btn" onclick="goCreateUserCompleteAction();"/>
					</div>
				</div>
				<div class="submit_btn_box">
					<div id=".contents-btn-set">
						<s:submit value="戻る" class="submit_btn" onclick="goCreateUserAction();"/>
						<s:hidden name="createUserFlg" value="1" />
					</div>
				</div>

			</s:form>
		</div>
	</body>
</html>