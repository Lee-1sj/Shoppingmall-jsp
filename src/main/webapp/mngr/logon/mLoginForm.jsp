<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="/my-shoppingmall/css/form.css?ver=<%=new Date().getTime()%>"/>
<script src="/my-shoppingmall/mngr/logon/mlogin.js?ver=<%=new Date().getTime()%>"></script>

<c:if test="${empty sessionScope.id}">
  <div id="status">
     <ul>
        <li><label for="id">아이디</label>
            <input id="id" name="id" type="email" size="20" 
              maxlength="50">
            <label for="passwd">비밀번호</label>
            <input id="passwd" name="passwd" type="password" 
              size="20" maxlength="16">
            <button id="login" onclick="login()">로그인</button>
     </ul>
  </div>
</c:if>
<c:if test="${!empty sessionScope.id}">
  <div id="status">
     <ul>
        <li>관리자 로그인 성공!!. 작업중...
           <button id="logout" onclick="logout()">로그아웃</button>
     </ul>
  </div>
</c:if> 