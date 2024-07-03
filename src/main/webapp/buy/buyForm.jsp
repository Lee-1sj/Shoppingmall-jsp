<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<link rel="stylesheet" href="/my-shoppingmall/css/buyForm.css" />
<script src="/my-shoppingmall/buy/buyForm.js"></script>

<c:if test="${empty sessionScope.id}">
	<meta http-equiv="Refresh" content="0;url=/my-shoppingmall/index.do">
</c:if>

<div id="cartArea">
	<table id="cartTable">
		<tr class="cen">
			<td>상품명</td>
			<td>판매가격</td>
			<td>수량</td>
			<td>금액</td>
		</tr>
		<c:set var="total" value="0" />
		<c:forEach var="cart" items="${cartLists}">
			<tr>
				<td>
					<img src="/my-shoppingmall/goodsimage/${cart.getGoods_image()}" class="cartimage">${cart.getGoods_title()}
				</td>
				<td class="cen">
					<fmt:formatNumber value="${cart.getBuy_price()}" type="number" pattern="#,##0" />원
				</td>
				<td class="cen">${cart.getBuy_count()}</td>
				<td class="cen">
					<c:set var="amount" value="${cart.getBuy_count()*cart.getBuy_price()}" /> 
					<c:set var="total" value="${total+amount}" /> 
					<fmt:formatNumber value="${amount}" type="number" pattern="#,##0" />원
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="4" align="right" class="b">총 금액 : <fmt:formatNumber value="${total}" type="number" pattern="#,##0" />원
			</td>
		</tr>
	</table>
</div>

<div id="buyArea">
	<form name="buyForm" method="post" action="/my-shoppingmall/buyPro.do">
		<table id="ordererInfo">
			<tr>
				<td colspan="2" class="sectionTitle"><font size="+1"><b>주문자 정보</b></font></td>
			</tr>
			<tr>
				<td>성명</td>
				<td>${member.getName()}</td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td>${member.getTel()}</td>
			</tr>
			<tr>
				<td>주소</td>
				<td>${member.getAddress()}</td>
			</tr>
			<tr>
				<td>결제계좌</td>
				<td>
					<select name="account">
						<c:forEach var="accountList" items="${accountLists}">
							<option value="${accountList}">${accountList}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
		</table>
		<br>

		<table id="deliveryInfo">
			<tr>
				<td colspan="2" class="sectionTitle"><font size="+1"><b>배송지 정보</b></font></td>
			</tr>
			<tr>
				<td>성명</td>
				<td><input type="text" name="deliveryName" value="${member.getName()}"></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" name="deliveryTel" value="${member.getTel()}"></td>
			</tr>
			<tr>
				<td>주소</td>
				<td>
					<input type="text" name="deliveryAddress" value="${member.getAddress()}"> 
					<input type="hidden" name="buyer" value="${sessionScope.id}">
				</td>
			</tr>
			<tr>
				<td colspan="2" class="submitButtons">
					<input type="submit" value="주문"> 
					<input type="button" id="cancel" value="취소">
				</td>
			</tr>
		</table>
	</form>
</div>
