<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="/my-shoppingmall/css/form.css"/>
<script src="/my-shoppingmall/member/login.js?ver=<%=new Date().getTime()%>"></script>

<c:if test="${empty sessionScope.id}">
  <div id="lStatus">
     <ul>
        <li><label for="cid">아이디</label>
            <input id="cid" name="cid" type="email" size="20" maxlength="50">
            <label for="cpasswd">비밀번호</label>
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
        <li>${sessionScope.id}님이 로그인 하셨습니다.
           <div id="info">
             <table>
               <tr height="10">
                 <td><button id="uLogout" onclick="uLogout()">로그아웃</button></td>
                 <td><button id="uUpdate" onclick="uUpdate()">회원 정보 변경</button></td>
                 <td><form id="cartForm" method="post" action="/my-shoppingmall/cartList.do">
                   <input type="hidden" name="buyer" value="${sessionScope.id}">
                   <input type="submit" name="cart" value="장바구니"></form>
                 </td>
                 <td><form id="buyForm" method="post" action="/my-shoppingmall/buyList.do">
                   <input type="hidden" name="buyer" value="${sessionScope.id}">
                   <input type="submit" name="buy" value="구매내역"></form>
                 </td>
                 </tr>
             </table>
        	</div>     
       </li>       
     </ul>
  </div>
</c:if> 