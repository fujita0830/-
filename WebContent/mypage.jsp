<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/style.css">
<title>マイページ</title>
</head>
<body>
<jsp:include page="header.jsp" />
<div id="contents">
<h1>マイページ画面</h1>
	<s:form action="PurchaseHistoryAction">
	<table class="verticallist-table">
	<tr>
		<th scope="row"><s:label value="姓"/></th>
		<td><s:property value="#session.familyName"/><td>
	</tr>
	<tr>
		<th scope="row"><s:label value="名"/></th>
		<td><s:property value="#session.firstName"/><td>
	</tr>

	<tr>
		<th scope="row"><s:label value="ふりがな"/></th>
		<td><s:property value="#session.familyNameKana"/></span>　</span><s:propaty value="#session.firstNameKana"/></td>
	</tr>

	<tr>
		<th scope="row"><s:label value="性別"/></th>
		<td><s:if test="#session.sex==0">男性</s:if><s:if test="#session.sex==1">女性</s:if></td>
	</tr>

	<tr>
		<th scope="row"><s:label value="メールアドレス"/></th>
		<td><s:property value="#session.email"></td>
	</tr>
	</table>
		<div class="submit btn_box">
		<s:submit value="購入履歴" class="submit_btn" />
		</div>
		</s:form>
</div>
	<s:include value="footerjsp">
</body>
</html>