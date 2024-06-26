<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="/my-shoppingmall/css/goodsRegisterForm.css?ver=<%=new Date().getTime()%>"/>
<script src="/my-shoppingmall/mngr/productProcess/goodsregist.js?ver=<%=new Date().getTime()%>"></script>
<c:if test="${empty sessionScope.id}">
  <meta http-equiv="Refresh" content="0;url=/my-shoppingmall/mg/managerMain.do">
</c:if>

<div id="listHeader">
  <button id="goodsMain">관리자 메인으로</button>
  <button id="goodsList">목록으로</button>
</div>

<form id="upForm1" action="/my-shoppingmall/mg/goodsRegisterPro.do" method="post" enctype="multipart/form-data">
  <div id="goodsRegistForm" class="box">
    <ul>
      <li>
        <label for="goods_kind">분류선택</label>
        <select id="goods_kind" name="goods_kind">
          <option value="100">상의</option>
          <option value="200">하의</option>
        </select>
      </li>
      <li>
        <label for="goods_title">제목</label>
        <input id="goods_title" name="goods_title" type="text" size="50" placeholder="제목" maxlength="50">
      </li>
      <li>
        <label for="goods_price">가격</label>
        <input id="goods_price" name="goods_price" type="text" size="10" placeholder="가격" maxlength="9">원
      </li>
      <li>
        <label for="goods_count">수량</label>
        <input id="goods_count" name="goods_count" type="text" size="10" placeholder="수량" maxlength="5">개
      </li>
      <li>
        <label for="goods_size">사이즈</label>
        <select id="goods_size" name="goods_size">
          <option value="95">95</option>
          <option value="100">100</option>
          <option value="105">105</option>
        </select>
      </li>
      <li>
        <label for="goods_image">상품 이미지</label>
        <input id="goods_image" name="goods_image" type="file">
      </li>
      <li>
        <label for="goods_content">내용</label>
        <textarea id="goods_content" name="goods_content" rows="13" cols="50"></textarea>
      </li>
      <li>
        <label for="discount_rate">할인율</label>
        <input id="discount_rate" name="discount_rate" type="text" size="5" placeholder="10" maxlength="2">%
      </li>
      <li class="label2">
        <input type="submit" id="registGoods" value="상품 등록">
      </li>
    </ul>
  </div>
</form>
