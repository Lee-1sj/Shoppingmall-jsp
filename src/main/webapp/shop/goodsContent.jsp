<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<link rel="stylesheet"
	href="/my-shoppingmall/css/goodsContent.css?ver=2" />
<script src="/my-shoppingmall/shop/goodscontent.js?ver=5"></script>

<div id="cata" class="box2">
	<ul>
		<li><a href="/my-shoppingmall/list.do?goods_kind=100">상의</a></li>
		<li><a href="/my-shoppingmall/list.do?goods_kind=200">하의</a></li>
		<li><a href="/my-shoppingmall/list.do?goods_kind=all">전체</a></li>
	</ul>
</div>

<div id="showGoods">
	<table class="vhcenter">
		<tr>
			<td rowspan="6">
				<img src="/my-shoppingmall/goodsimage/${goods.getGoods_image()}" class="contentimage">
			</td>
			<td><b>${goods.getGoods_title()}</b></td>
		</tr>
		<tr>
			<td>사이즈 : ${goods.getGoods_size()}</td>
		</tr>
		<tr>
			<td>
				<c:set var="price" value="${goods.getGoods_price()}" /> 
				<c:set var="rate" value="${goods.getDiscount_rate()}" /> 
				정가 : <fmt:formatNumber value="${price}" type="number" pattern="#,##0" />원<br> 
				<strong class="bred">판매가:<c:set var="rPrice" value="${price*((100.0-rate)/100)}" /> 
				<fmt:formatNumber value="${rPrice}" type="number" pattern="#,##0" />원</strong>
		<tr>
			<td>
				<c:if test="${!empty sessionScope.id}">
					<c:if test="${goods.getGoods_count()==0}">
						<p>일시품절</p>
					</c:if>
					<c:if test="${goods.getGoods_count()>=1}">
          수량 : <input type="text" size="5" id="buy_count" value="1"> 개
          </c:if>
					<input type="hidden" id="goods_id" value="${goods_id}">
					<input type="hidden" id="goods_image" value="${goods.getGoods_image()}">
					<input type="hidden" id="goods_title" value="${goods.getGoods_title()}">
					<input type="hidden" id="buy_price" value="${rPrice}">
					<input type="hidden" id="goods_kind" value="${goods_kind}">
					<input type="hidden" id="buyer" value="${sessionScope.id}">
					<button id="insertCart">장바구니에 담기</button>
				</c:if> 
				<c:if test="${empty sessionScope.id}">
					<c:if test="${goods.getGoods_count()==0}">
						<p>일시품절</p>
					</c:if>
					<p>제품을 구매하시려면 로그인 하세요.</p>
				</c:if>
				<button id="list" onclick="list()">목록으로</button>
				<button id="shopMain" onclick="shopMain()">메인으로</button>
			</td>
		</tr>
		<tr class="ch">
			<td colspan="2" class="hleft" style="text-align: center;">${goods.getGoods_content()}</td>
		</tr>
	</table>
</div>

<div id="showQna">
	<p class="b">
		상품QnA
		<c:if test="${!empty sessionScope.id}">
			<button id="writeQna">상품QnA쓰기</button>
		</c:if>
		<c:if test="${empty sessionScope.id}">
			<p>상품QnA를 쓰실려면 로그인 하세요.</p>
		</c:if>
	</p>
	<c:if test="${count == 0}">
		<ul>
			<li>등록된 상품QnA가 없습니다.</li>
		</ul>
	</c:if>
	<c:if test="${count > 0}">
		<c:forEach var="qna" items="${qnaLists}">
			<ul>
				<li><c:set var="writer" value="${qna.getQna_writer()}" />${fn:substring(writer, 0, 4)}**** 
					<small class="date">(${qna.getReg_date()})</small>
				</li>
				<li>${qna.getQna_content()}</li>
				<li>
					<c:if test="${sessionScope.id==writer}">
						<button id="edit" name="${qna.getQna_id()},${goods_kind}"
							onclick="edit(this)">수정</button>
						<button id="delete"
							name="${qna.getQna_id()},${goods_id},${goods_kind}"
							onclick="del(this)">삭제</button>
					</c:if>
				</li>	
			</ul>
		</c:forEach>
	</c:if>
</div>
