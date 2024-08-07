<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="/my-shoppingmall/css/modifyForm.css"/>

<c:if test="${empty sessionScope.id}">
  <meta http-equiv="Refresh" content="0;url=/my-shoppingmall/index.do">
</c:if>

<div id="regForm" class="box">
   <form id="modifyForm" method="post" action="/my-shoppingmall/modifyUpdate.do">
       <ul class="m">
           <li><p class="center">회원 정보 수정</p></li>
           <li><label for="id">아이디</label>
               <input id="id" name="id" type="email" size="20" maxlength="50" value="${id}" readonly></li>
           <li><label for="passwd">수정할 비밀번호</label>
               <input id="passwd" name="passwd" type="password" size="20" placeholder="6~16자" maxlength="16">
                <small class="cau">반드시 입력하세요.</small></li>
           <li><label for="name">이름</label>
               <input id="name" name="name" type="text" size="20" maxlength="10" value="${m.name}"></li>
           <li><label for="address">주소</label>
               <input id="address" name="address" type="text" size="30" maxlength="50" value="${m.address}"></li>
           <li><label for="tel">전화번호</label>
               <input id="tel" name="tel" type="tel" size="20" maxlength="20" value="${m.tel}"></li>
           <li class="label2">
               <button type="submit" id="modifyProcess">수정</button>
               <button type="button" id="cancel">취소</button></li>
       </ul>
   </form>
</div>

<c:if test="${not empty result}">
    <script>
        alert('회원 정보가 수정되었습니다.');
        window.location.href = '/my-shoppingmall/index.do';
    </script>
</c:if>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        document.getElementById('cancel').addEventListener('click', function() {
            window.location.href = '/my-shoppingmall/index.do';
        });
    });
</script>
