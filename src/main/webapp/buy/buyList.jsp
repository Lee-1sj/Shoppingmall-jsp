<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<link rel="stylesheet" href="/my-shoppingmall/css/buyList.css" />
<script src="/my-shoppingmall/buy/buyList.js"></script>

<c:if test="${empty sessionScope.id}">
	<meta http-equiv="Refresh" content="0;url=/my-shoppingmall/index.do">
</c:if>

<div id="goShop">
	<button id="continueShopping">쇼핑계속</button>
	<button id="shopMain">메인으로</button>
</div>
<div id="buyList">
	<c:if test="${count == 0}">
		<ul>
			<li>구매 목록이 없습니다.
		</ul>
	</c:if>

	<c:if test="${count > 0}">
		<c:set var="total" value="0" />
		<c:forEach var="i" begin="0" end="${buyLists.size()-1}">
			<c:set var="buylist" value="${buyLists.get(i)}" />
			<c:set var="pid" value="${buylist.getBuy_id()}" />
			<c:if test="${i+1 > buyLists.size()-1 }">
				<c:set var="nid" value="0" />
			</c:if>
			<c:if test="${i+1 <= buyLists.size()-1 }">
				<c:set var="nid" value="${buyLists.get(i+1).getBuy_id()}" />
			</c:if>
			<table>
				<tr>
					<td>주문번호</td>
					<td>상품명</td>
					<td>판매가격</td>
					<td>수량</td>
					<td>금액</td>
					<td>배송상황</td>
				</tr>
				<tr>
					<td>${buylist.getBuy_id()}</td>
					<td>
						<img src="/my-shoppingmall/goodsimage/${buylist.getBook_image()}"
						class="cartimage">${buylist.getBook_title()}
					</td>
					<td><fmt:formatNumber
							value="${buylist.getBuy_price()}" type="number" pattern="#,##0" />원</td>
					<td>${buylist.getBuy_count()}</td>
					<td>
						<c:set var="amount" value="${buylist.getBuy_count()*buylist.getBuy_price()}" /> 
						<c:set var="total" value="${total+amount}" /> 
						<fmt:formatNumber value="${amount}" type="number" pattern="#,##0" />원
					</td>
					<td>${buylist.getSanction()}</td>
				</tr>
			</table>
			<c:if test="${pid != nid}">
				<p class="b">
					주문금액 :
					<fmt:formatNumber value="${total}" type="number" pattern="#,##0" />
					원
				</p>	
				<c:set var="total" value="0" />
				<c:set var="pid" value="${nid}" />
			</c:if>
		</c:forEach>
	</c:if>
</div>