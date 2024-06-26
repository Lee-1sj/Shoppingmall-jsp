<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="/my-shoppingmall/css/managerMain.css"/>
<script src="/my-shoppingmall/mngr/managermain.js?ver=<%=new Date().getTime()%>"></script>
    
<c:if test="${empty sessionScope.id}">
  <div id="mList"><p>로그인 하세요.
  </div>
</c:if>
<c:if test="${!empty sessionScope.id}">
  <div id="mList">
     <ul>
        <li>상품관련 작업
        <li><button id="registProduct" onclick="registProduct()">상품등록</button>
        <li><button id="updateProduct" onclick="updateProduct()">상품수정/삭제</button>
     </ul>
     <ul>
        <li>구매된 상품관련 작업
        <li><button id="orderedProduct" onclick="orderedProduct()">전체구매목록 확인</button>
     </ul>
     <ul>
        <li>상품 QnA 작업
        <li><button id="qna" onclick="qna()">상품 QnA답변</button>
     </ul>
  </div>
</c:if>   