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
function goLoginAction(){
	document.getElementById("form").action="createUserAction";
}
</script>
<body>
<jsp:include page="header.jsp" />
<div id="contents">
<h1>ユーザー情報入力確認画面</h1>

<table class="vertical-list-table">
<tr>
	<th scope="row"><s:label value="姓"/></th>
	<td><s:property value="familyName"/></td>
</tr>
<tr>
	<th scope="row"><s:label value="名"/></th>
	<td><s:property value="firstName"/></td>
</tr>
<tr>
	<th scope="row"><s:label value="姓ふりがな"/></th>
	<td><s:property value="familyNameKana"/></td>
</tr>
<tr>
	<th scope="row"><s:label value="名ふりがな"/></th>
	<td><s:property value="firstNameKana"/></td>
</tr>
<tr>
	<th scope="row"><s:label value="性別"/></th>
	<td><s:property value="sex"/></td>
</tr>
<tr>
	<th scope="row"><s:label value="メールアドレス"/></th>
	<td><s:property value="email"/></td>
</tr>
<tr>
	<th scope="row"><s:label value="ユーザーID"/>
	<td><s:property value="loginId"/>
</tr>
<tr>
	<th scope="row"><s:label value="パスワード"/>
	<td><s:property value="password"/>
</tr>
</table>
<s:form action="CreateUserAction">
<s:submit value="戻る" class="submit_btn" onclick="goLoginAction();"/>
<s:hidden name="loginId" value="%{loginId}"/>
<s:hidden name="password" value="%{password}"/>
<s:hidden name="familyName" value="%{familyName}"/>
<s:hidden name="firstName" value="%{firstName}"/>
<s:hidden name="familyNameKana" value="%{familyNameKana}"/>
<s:hidden name="firstNameKana" value="%{firstNameKana}"/>
<s:if test='sex.equals("男性")'>
<s:hidden name="sex" value="0"/>
</s:if>
<s:if test='sex.equals("女性")'>
<s:hidden name="sex" value="1"/>
</s:if>
<s:hidden name="email" value="%{email}"/>
</s:form>

<s:form action="CreateUserCompleteAction">
<div class="submit_btn_box">
<div id=".contents-btn-set">

<s:submit value="登録" class="submit_btn" />
</div>
</div>
<s:hidden name="loginId" value="%{loginId}"/>
<s:hidden name="password" value="%{password}"/>
<s:hidden name="familyName" value="%{familyName}"/>
<s:hidden name="firstName" value="%{firstName}"/>
<s:hidden name="familyNameKana" value="%{familyNameKana}"/>
<s:hidden name="firstNameKana" value="%{firstNameKana}"/>
<s:if test='sex.equals("男性")'>
<s:hidden name="sex" value="0"/>
</s:if>
<s:if test='sex.equals("女性")'>
<s:hidden name="sex" value="1"/>
</s:if>
<s:hidden name="email" value="%{email}"/>
</s:form>
</div>

</body>
</html>