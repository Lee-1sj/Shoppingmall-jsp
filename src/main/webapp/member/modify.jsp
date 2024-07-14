<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="/my-shoppingmall/css/modify.css"/>

<c:if test="${empty sessionScope.id}">
  <meta http-equiv="Refresh" content="0;url=/my-shoppingmall/index.do">
</c:if>

<div id="mStatus">
   <form id="uForm" method="post" action="/my-shoppingmall/modifyForm.do">
    <ul>
        <li><label for="passwd">비밀번호</label>
            <input id="passwd" name="passwd" type="password" size="20" placeholder="6~16자 숫자/문자" maxlength="16">
            <input id="id" name="id" type="hidden" value="${sessionScope.id}">
            <input type="submit" id="modify" value="정보수정">
        </li>
     </ul>
   </form>
   <form id="dForm" method="post" action="/my-shoppingmall/deletePro.do">
    <ul>
        <li><label for="passwd">비밀번호</label>
            <input id="passwd" name="passwd" type="password" size="20" placeholder="6~16자 숫자/문자" maxlength="16">
            <input id="id" name="id" type="hidden" value="${sessionScope.id}">
            <input type="submit" id="delete" value="탈퇴">
        </li>
     </ul>
  </form>
  <button id="shopMain" onclick="window.location.href='/my-shoppingmall/index.do'">메인으로</button>
</div>
