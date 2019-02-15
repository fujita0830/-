<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/cyan.css">
<title>商品購入情報</title>
</head>

<body>
<jsp:include page="header.jsp" />

<div id="contents">

<h1>商品購入履歴画面</h1>

<s:if test="purchaseHistoryInfoDTOList.size()>0">

<table class="horizontal-list-table">
<thead>
<tr>
	<th><s:label value="商品名"/></th>
	<th><s:label value="ふりがな"/></th>
	<th><s:label value="商品画像"/></th>
	<th><s:label value="発売会社名"/></th>
	<th><s:label value="発売年月日"/></th>
	<th><s:label value="値段"/></th>
	<th><s:label value="個数"/></th>
	<th><s:label value="合計金額"/></th>
</tr>

<tbody>
<s:iterator value="purchaseHistoryInfoDTOList">
<tr>
	<td><s:property value="productName"/></td>
	<td><s:property value="productNameKana"/></td>
	<td><img src='<s:property value="imageFilePath"/>/<s:property value="imageFileName"/>' width="50px" height="50px"/></td>
	<td><s:property value="releaseCompany"/></td>
	<td><s:property value="releaseDate"/></td>
	<td><s:property value="price"/>円</td>
	<td><s:property value="productCount"/>個</td>
	<td><s:property value="buyTotalprice"/>円</td>
</tr>
</s:iterator>
</tbody>
</table>

<div class="submit_btn_box">
<div id=".contents-btn-set">
<s:form action="DeletePurchaseHistoryAction">
	<s:submit value="履歴削除" class="submit_btn" />
</s:form>
</div>
</div>
</s:if>

<s:else>
<div class="info">
商品購入履歴情報がありません。
</div>
</s:else>

</div>
</body>
</html>