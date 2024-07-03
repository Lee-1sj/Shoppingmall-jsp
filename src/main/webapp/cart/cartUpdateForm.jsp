<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/my-shoppingmall/css/cartUpdateForm.css"/>
<c:if test="${empty sessionScope.id}">
  <meta http-equiv="Refresh" content="0;url=/my-shoppingmall/index.do">
</c:if>

<div id="cartUpdate">
  <form id="cartUpdateForm" method="post" action="/my-shoppingmall/cartUpdatePro.do">
     <input type="text" name="buy_count" size="5" value="${buy_count}">
     <input type="hidden" name="cart_id" value="${cart_id}">
	 <input type="submit" value="변경" >  
  </form>
</div> 