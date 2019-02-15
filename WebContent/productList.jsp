<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/productList.css">
<link rel="stylesheet" href="./css/cyan.css">
<title>商品一覧</title>
</head>
<body>
<jsp:include page="header.jsp" />
<div id="contents">
<h1>商品一覧画面</h1>
<s:if test="!#session.keywordsErrorMessageList.isEmpty()">
	<div class="error">
	<div class="error-message">
		<s:iterator value="#session.keywordsErrorMessageList"><s:property /><br></s:iterator>
	</div>
	</div>
</s:if>
<s:elseif test="#session.productInfoDTOList == null">
<div class="info">
	検索結果がありません。
</div>
</s:elseif>
<div id="product-list">
<s:iterator value="#session.productInfoDTOList">
<div id="product-list-box">
<ul>
	<li>
	<div class="item-image-box-250">
	<a href='<s:url action="ProductDetailsAction">
	<s:param name="productId" value="%{productId}"/></s:url>'>
	<img src='<s:property value="imageFilePath"/>/<s:property value="imageFileName"/>' height="250px" width="250px"/></a></div>
	<div class="product-title"><s:property value="productName"/></div>
	<div class="product-title-kana"><s:property value="productNameKana"/></div>
	<div class="product-price"><s:property value="price"/>円</div><br>
	</li>
</ul>
</div>
</s:iterator>
</div>
</div>
</body>
</html>