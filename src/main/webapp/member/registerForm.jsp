<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="/my-shoppingmall/css/registerForm.css"/>
<script src="/my-shoppingmall/member/register.js?ver=<%=new Date().getTime() %>"></script>

<div id="regForm" class="box">
   <form>
      <ul>
         <li>
             <label for="id">아이디</label>
             <input id="id" name="id" type="email" size="20" maxlength="50" autofocus>&nbsp;
             <button type="button" id="checkId">ID중복확인</button>
         </li>
         <li>
             <label for="passwd">비밀번호</label>
             <input id="passwd" name="passwd" type="password" size="20" maxlength="16">
         </li>
         <li>
             <label for="repass">비밀번호 재입력</label>
             <input id="repass" name="repass" type="password" size="20" maxlength="16">
         </li>
         <li>
             <label for="name">이름</label>
             <input id="name" name="name" type="text" size="20" maxlength="10">
         </li>
         <li>
             <label for="address">주소</label>
             <input id="address" name="address" type="text" size="30" maxlength="50">
         </li>
         <li>
             <label for="tel">전화번호</label>
             <input id="tel" name="tel" type="tel" size="20" maxlength="20">
         </li>
         <li class="label2">
             <button type="button" id="process">가입하기</button>
             <button type="button" id="cancel">취소</button>
         </li>
      </ul>
   </form>
</div>
