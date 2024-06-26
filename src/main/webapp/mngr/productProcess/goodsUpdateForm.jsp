<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="/my-shoppingmall/css/goodsUpdateForm.css"/>
<script src="/my-shoppingmall/mngr/productProcess/goodsupdate.js"></script>

<c:if test="${empty sessionScope.id}">
  <meta http-equiv="Refresh" content="0;url=/my-shoppingmall/mg/managerMain.do">
</c:if>

<div id="header">
  <button id="goodsMain" class="header-button">관리자 메인으로</button>
  <button id="goodsList" class="header-button">목록으로</button>
</div>

<form id="upForm1" action="/my-shoppingmall/mg/goodsUpdatePro.do" method="post" enctype="multipart/form-data">
  <div id="goodsUpdateForm" class="form-box">
    <ul class="form-list">
      <li class="form-item">
        <label for="goods_kind" class="form-label">분류선택</label>
        <select id="goods_kind" name="goods_kind" class="form-select">
          <option value="100" <c:if test="${goods_kind == 100}">selected</c:if>>상의</option>
          <option value="200" <c:if test="${goods_kind == 200}">selected</c:if>>하의</option>
        </select>
      </li>
      <li class="form-item">
        <label for="goods_title" class="form-label">이름</label>
        <input id="goods_title" name="goods_title" type="text" size="50" maxlength="50" value="${goods.goods_title}" class="form-input">
        <input type="hidden" name="goods_id" value="${goods_id}">
      </li>
      <li class="form-item">
        <label for="goods_price" class="form-label">가격</label>
        <input id="goods_price" name="goods_price" type="text" size="10" maxlength="9" value="${goods.goods_price}" class="form-input">원
      </li>
      <li class="form-item">
        <label for="goods_count" class="form-label">수량</label>
        <input id="goods_count" name="goods_count" type="text" size="10" maxlength="5" value="${goods.goods_count}" class="form-input">개
      </li>
      <li class="form-item">
        <label for="goods_size" class="form-label">사이즈</label>
        <input id="goods_size" name="goods_size" type="text" size="20" maxlength="30" value="${goods.goods_size}" class="form-input">
      </li>
      <li class="form-item">
        <label for="goods_image" class="form-label">이미지</label>
        <input id="goods_image" name="goods_image" type="file" class="form-input">${goods.goods_image}
      </li>
      <li class="form-item">
        <label for="goods_content" class="form-label">내용</label>
        <textarea id="goods_content" name="goods_content" rows="13" cols="50" class="form-textarea">${goods.goods_content}</textarea>
      </li>
      <li class="form-item">
        <label for="discount_rate" class="form-label">할인율</label>
        <input id="discount_rate" name="discount_rate" type="text" size="5" maxlength="2" value="${goods.discount_rate}" class="form-input">%
      </li>
      <li class="form-item form-submit">
        <input type="submit" id="updateGoods" value="수정" class="submit-button">
      </li>
    </ul>
  </div>
</form>
