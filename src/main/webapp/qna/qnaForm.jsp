<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<link rel="stylesheet" href="/my-shoppingmall/css/qnaForm.css" />
<script src="/my-shoppingmall/qna/write.js"></script>

<c:if test="${empty sessionScope.id}">
    <meta http-equiv="Refresh" content="0;url=/my-shoppingmall/index.do">
</c:if>

<input type="hidden" id="qna_writer" value="${sessionScope.id}">
<input type="hidden" id="goods_kind" value="${goods_kind}">
<input type="hidden" id="goods_id" value="${goods_id}">
<input type="hidden" id="goods_title" value="${goods_title}">
<input type="hidden" id="qora" value="${qora}">

<div id="writeForm" class="box">
    <ul>
        <li>[${goods_title}] 상품에 대한 QnA</li>
        <li>
            <label for="content">내용</label> 
            <textarea id="qnaCont" rows="13" cols="50"></textarea>
        </li>
        <li class="label2">
            <button id="regist">등록</button>
            <button id="cancel">취소</button>
        </li>
    </ul>
</div>
