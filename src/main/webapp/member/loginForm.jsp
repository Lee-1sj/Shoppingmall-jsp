<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<style>
* { margin: 0; padding: 0; box-sizing: border-box; }
body { font-family: Arial, sans-serif; }
#lStatus { display: flex; justify-content: flex-end; align-items: center; background-color: #333; color: white; padding: 10px; }
#lStatus ul { list-style: none; display: flex; align-items: center; }
#lStatus li { margin-right: 10px; }
#lStatus label { margin-right: 5px; }
#lStatus input[type="email"], #lStatus input[type="password"], #lStatus button { padding: 5px; margin-right: 5px; border: none; border-radius: 4px; }
#lStatus button { background-color: #e9ecef; color: #333; cursor: pointer; }
#lStatus button:hover { background-color: #dee2e6; }
#info { display: flex; }
#info table { border-collapse: collapse; }
#info td { padding: 5px; }
#info form { display: inline; }
#loggedInButtons button { background-color: #e9ecef; color: #333; border: none; padding: 5px 10px; border-radius: 4px; margin-right: 5px; cursor: pointer; }
#loggedInButtons button:hover { background-color: #dee2e6; }
#loggedInButtons form input[type="submit"] { background-color: #e9ecef; color: #333; border: none; padding: 5px 10px; border-radius: 4px; cursor: pointer; }
#loggedInButtons form input[type="submit"]:hover { background-color: #dee2e6; }
</style>
<script src="/my-shoppingmall/member/login.js?ver=<%=new Date().getTime()%>"></script>

<c:if test="${empty sessionScope.id}">
  <div id="lStatus">
     <ul>
        <li><label for="cid">아이디&nbsp;</label>
            <input id="cid" name="cid" type="email" size="20" maxlength="50">
            <label for="cpasswd">비밀번호&nbsp;</label>
            <input id="cpasswd" name="cpasswd" type="password" size="20" maxlength="16">
            <button id="uLogin">로그인</button>
            <button id="uRes">회원가입</button>
        </li>    
     </ul>
  </div>
</c:if>
<c:if test="${!empty sessionScope.id}">
  <div id="lStatus">
     <ul>
        <li>
           <div id="info">
             <div id="loggedInButtons">
               <button id="uLogout" onclick="uLogout()">로그아웃</button>
               <button id="uUpdate" onclick="uUpdate()">회원 정보 변경</button>
               <form id="cartForm" method="post" action="/my-shoppingmall/cartList.do">
                 <input type="hidden" name="buyer" value="${sessionScope.id}">
                 <input type="submit" name="cart" value="장바구니">
               </form>
               <form id="buyForm" method="post" action="/my-shoppingmall/buyList.do">
                 <input type="hidden" name="buyer" value="${sessionScope.id}">
                 <input type="submit" name="buy" value="구매내역">
               </form>
             </div>
        	</div>     
       </li>       
     </ul>
  </div>
</c:if>
