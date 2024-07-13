<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="/my-shoppingmall/css/shopMain.css?ver=2"/>

<div id="cata" class="box2">
  <ul>
    <li><a href="/my-shoppingmall/list.do?goods_kind=100">상의</a></li>
    <li><a href="/my-shoppingmall/list.do?goods_kind=200">하의</a></li>
    <li><a href="/my-shoppingmall/list.do?goods_kind=all">전체</a></li>
  </ul>
</div>

<div id="shop" class="box2">
  <c:forEach var="goods" items="${goodsLists}">
    <div class="product-item">
      <c:choose>
        <c:when test="${goods.goods_kind == '100'}">
          <c:set var="goods_kindName" value="상의"/>
        </c:when>
        <c:when test="${goods.goods_kind == '200'}">
          <c:set var="goods_kindName" value="하의"/>
        </c:when>
      </c:choose>
      <p class="b">[${goods_kindName}] New 신상 입고
        <a class="more" href="/my-shoppingmall/list.do?goods_kind=${goods.goods_kind}">더보기</a>
      </p>
      <div class="product-details">
        <div class="product-image">
          <a href="/my-shoppingmall/goodsContent.do?goods_id=${goods.goods_id}&goods_kind=${goods.goods_kind}">
            <img src="/my-shoppingmall/goodsimage/${goods.goods_image}" class="listimage">
          </a>
        </div>
        <div class="product-info">
          <a href="/my-shoppingmall/goodsContent.do?goods_id=${goods.goods_id}&goods_kind=${goods.goods_kind}" class="b">
            ${goods.goods_title}
          </a>
          <p>사이즈 : ${goods.goods_size}</p>
          <p>
            <c:set var="price" value="${goods.goods_price}"/>
            <c:set var="rate" value="${goods.discount_rate}"/>
            정가 : <fmt:formatNumber value="${price}" type="number" pattern="#,##0"/>원<br>
            <strong class="bred">판매가: <fmt:formatNumber value="${price * ((100.0 - rate) / 100)}" type="number" pattern="#,##0"/>원</strong>
          </p>
          <p>
            <c:if test="${goods.goods_count == 0}">
              일시품절
            </c:if>
            <c:if test="${goods.goods_count != 0}">
              구매가능
            </c:if>
          </p>
        </div>
      </div>
    </div>
  </c:forEach>
</div>
