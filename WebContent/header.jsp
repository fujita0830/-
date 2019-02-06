<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ヘッダー</title>

<script>
function goLoginAction(){
	document.getElementById("form").action="GoLoginAction";
}
function goMyPageAction(){
	document.getElementById("form").action="MyPageAction";
}
function goCartAction(){
	document.getElementById("form").action="CartAction";
}
function goProductListAction(){
	document.getElementById("categoryId").value=1;
	document.getElementById("form").action="ProductListAction";
}
function goLogoutAction(){
	document.getElementById("form").action="LogoutAction";
}
function goSearchItemAction(){
	document.getElementById("form").action="SearchItemAction";
}
</script>

</head>
<body>

<header>
<div id="header">
<div id="header-title">
お菓子の販売サイト
</div>
<div id="header-menu">
<ul>
<s:form id="form" name="form">
	<s:if test='#session.containsKey("mCategoryDTOList")'>
	<li><s:select name="categoryId" list="#session.mCategoryDTOList" listValue="categoryName" listKey="categoryId" class="cs-div" id="categoryId"/></li>
	</s:if>
	<li><s:textfield name="keywords" class="txt-keywords" placeholder="検索ワード" /></li>
	<li><s:submit value="商品検索" class="submit_btn" onclick="goSearchItemAction();"/><li>
	<s:if test="#session.logined==1">
	<li><s:submit value="ログアウト" class="submit_btn" onclick="goLogoutAction();"/></li>
	</s:if>
	<s:else>
		<li><s:submit value="ログイン" class="submit_btn" onclick="goLoginAction();"/></li>
	</s:else>
	<li><s:submit value="カート" class="submit_btn" onclick="goCartAction();"/><li>
	<li><s:submit value="商品一覧" class="submit_btn" onclick="goProductListAction();"/></li>
	<s:if test="#session.logined==1">
		<li><s:submit value="マイページ" class="submit_btn" onclick="goMyPageAction();"/></li>
	</s:if>
</s:form>
</ul>
</div>
</div>
</header>


</body>
</html>