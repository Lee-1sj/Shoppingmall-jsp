<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="/my-shoppingmall/css/cartList.css"/>
<script src="/my-shoppingmall/cart/cartList.js"></script>
    
<c:if test="${empty sessionScope.id}">
  <meta http-equiv="Refresh" content="0;url=/my-shoppingmall/index.do">
</c:if>

<div id="cata" class="box2">
  <ul>
    <li><a href="/my-shoppingmall/list.do?book_kind=100">상의</a>
    <li><a href="/my-shoppingmall/list.do?book_kind=200">하의</a>
    <li><a href="/my-shoppingmall/list.do?book_kind=all">전체</a>
  </ul>
</div>
<div id="goShop">
  <button id="continueShopping">쇼핑계속</button>
  <button id="shopMain">메인으로</button>
</div>
<div id="cartList">
<c:if test="${count == 0}">
   <ul>
      <li>장바구니에 담긴 물품이 없습니다.
   </ul>
</c:if>
<c:if test="${count > 0}">
  <table> 
  <tr> 
   <td>상품명</td> 
   <td>판매가격</td>
   <td>수량</td> 
   <td>금액</td>
  </tr>
  <c:set var="total" value="0"/>
  <c:forEach var="cart" items="${cartLists}">
    <tr> 
       <td>
         <img src="/my-shoppingmall/goodsimage/${cart.getGoods_image()}" 
             class="cartimage">${cart.getGoods_title()}
       </td> 
       <td>
          <fmt:formatNumber value="${cart.getBuy_price()}" type="number" pattern="#,##0"/>원
       </td>
       <td>
          <input type="text" name="buy_count" size="5" value="${cart.getBuy_count()}">
          <button id="updateSu" name="${cart.getCart_id()},${cart.getBuy_count()}" onclick="editSu(this)">수정</button>
       </td> 
       <td>
         <c:set var="amount" value="${cart.getBuy_count()*cart.getBuy_price()}"/>
         <c:set var="total" value="${total+amount}"/>
         <fmt:formatNumber value="${amount}" type="number" pattern="#,##0"/>원
         <button id="deleteList" name="${cart.getCart_id()}" onclick="delList(this)">삭제</button>
       </td>
     </tr>
  </c:forEach>
    <tr>
     <td colspan="4"  class="b">총 금액 : 
       <fmt:formatNumber value="${total}" type="number" pattern="#,##0"/>원
     </td>
    </tr>
    <tr>
      <td colspan="5">
         <div id="cinfo">
         <table>
         	<tr>
           <td>
            <form id="cartForm" method="post" action="/my-shoppingmall/buyForm.do">
	            <input type="hidden" name="buyer" value="${sessionScope.id}">
	            <input type="submit" value="구매하기">
            </form>
           </td>
           <td>
           	<form id="cartClearForm" method="post" action="/my-shoppingmall/deleteCart.do">
            	<input type="hidden" name="list" value="all">
            	<input type="hidden" name="buyer" value="${sessionScope.id}">
            	<input type="submit" value="장바구니비우기">
            </form>
            </td>
          </tr>
         </table>
         </div>
      </td>
    </tr>
  </table>
</c:if>
</div> 