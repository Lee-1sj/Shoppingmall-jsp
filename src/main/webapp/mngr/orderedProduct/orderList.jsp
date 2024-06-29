<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<link rel="stylesheet" href="/my-shoppingmall/css/orderList.css" />
<script src="/my-shoppingmall/mngr/orderedProduct/orderlist.js"></script>

<c:if test="${empty sessionScope.id}">
	<meta http-equiv="Refresh" content="0;url=/my-shoppingmall/mg/managerMain.do">
</c:if>

<div id="listHeader">
	<p>
		주문 목록(전체 주문:${count})
		<button id="goodsMain">관리자 메인으로</button>
	</p>
</div>

<div id="orders">
	<c:if test="${count == 0}">
		<ul>
			<li>주문 목록이 없습니다.</li>
		</ul>
	</c:if>

	<c:if test="${count > 0}">
		<table>
			<tr class="title">
				<td>주문번호</td>
				<td>주문자</td>
				<td>상품명</td>
				<td>가격</td>
				<td>주문수량</td>
				<td>금액</td>
				<td>주문일</td>
				<td>결제계좌</td>
				<td>배송명</td>
				<td>배송지전화</td>
				<td>배송지주소</td>
				<td>배송지상황</td>
			</tr>
			<c:set var="total" value="0" />
			<c:forEach var="i" begin="0" end="${buyLists.size() - 1}">
				<c:set var="buyList" value="${buyLists[i]}" />
				<c:set var="pid" value="${buyList.buy_id}" />
				<c:choose>
					<c:when test="${i + 1 > buyLists.size() - 1}">
						<c:set var="nid" value="0" />
					</c:when>
					<c:otherwise>
						<c:set var="nid" value="${buyLists[i + 1].buy_id}" />
					</c:otherwise>
				</c:choose>
				<tr>
					<td>${buyList.buy_id}</td>
					<td>${buyList.buyer}</td>
					<td>${buyList.goods_title}</td>
					<td><fmt:formatNumber value="${buyList.buy_price}" type="number" pattern="#,##0" />원</td>
					<td>${buyList.buy_count}</td>
					<td>
						<c:set var="amount" value="${buyList.buy_count * buyList.buy_price}" />
						<c:set var="total" value="${total + amount}" />
						<fmt:formatNumber value="${amount}" type="number" pattern="#,##0" />원
					</td>
					<td>${buyList.buy_date}</td>
					<td>${buyList.account}</td>
					<td>${buyList.deliveryName}</td>
					<td>${buyList.deliveryTel}</td>
					<td>${buyList.deliveryAddress}</td>
					<td>${buyList.sanction}</td>
				</tr>

				<c:if test="${pid != nid}">
					<tr>
						<td colspan="12" class="b">주문금액: <fmt:formatNumber value="${total}" type="number" pattern="#,##0" />원</td>
					</tr>
					<c:set var="total" value="0" />
					<c:set var="pid" value="${nid}" />
				</c:if>
			</c:forEach>
		</table>
	</c:if>
</div>
