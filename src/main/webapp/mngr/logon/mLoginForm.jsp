<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<style>
  * { margin: 0; padding: 0; box-sizing: border-box; }
  body { font-family: Arial, sans-serif; }
  #status { display: flex; justify-content: flex-end; align-items: center; background-color: #333; color: white; padding: 10px; }
  #status ul { list-style: none; display: flex; align-items: center; }
  #status li { margin-right: 10px; }
  #status label { margin-right: 5px; }
  #status input[type="email"], #status input[type="password"], #status button { padding: 5px; margin-right: 5px; border: none; border-radius: 4px; }
  #status button { background-color: #e9ecef; color: #333; cursor: pointer; }
  #status button:hover { background-color: #dee2e6; }
</style>
<script src="/my-shoppingmall/mngr/logon/mlogin.js?ver=<%=new Date().getTime()%>"></script>

<c:if test="${empty sessionScope.id}">
  <div id="status">
     <ul style="list-style: none;">
        <li><label for="id">아이디&nbsp;</label>
            <input id="id" name="id" type="email" size="20" 
              maxlength="50">
            <label for="passwd">비밀번호&nbsp;</label>
            <input id="passwd" name="passwd" type="password" 
              size="20" maxlength="16">
            <button id="login" onclick="login()">로그인</button>
     </ul>
  </div>
</c:if>
<c:if test="${!empty sessionScope.id}">
  <div id="status">
     <ul>
        <li>관리자님 환영합니다&nbsp;
           <button id="logout" onclick="logout()">로그아웃</button>
     </ul>
  </div>
</c:if> 