<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="/my-shoppingmall/css/qnaReplyForm.css"/>
<script src="/my-shoppingmall/mngr/qnaProcess/qnawrite.js?ver=1"></script>

<c:if test="${empty sessionScope.id}">
  <meta http-equiv="Refresh" content="0;url=/my-shoppingmall/mg/managerMain.do" >
</c:if>

<input type="hidden" id="qna_writer" value="manager">
<input type="hidden" id="qna_id" value="${qna_id}">
<input type="hidden" id="goods_id" value="${goods_id}">
<input type="hidden" id="goods_title" value="${goods_title}">
<input type="hidden" id="qora" value="${qora}">

<div id="writeForm" class="box">
   <ul>
      <li>
          <p>[${goods_title}] 의 QnA </p>
          <p>QnA내용: ${qna_content}</p>
      </li>
      <li>
          <label for="rContent">답변</label>
          <textarea id="rContent" rows="13" cols="50"></textarea>
      </li>
      <li class="label2">
          <button id="replyPro">답변하기</button>
          <button id="cancle">취소</button> 
      </li>
   </ul>
</div>
