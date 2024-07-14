<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
  #container {
    font-family: Arial, sans-serif;
    text-align: center;
    background-color: #fff;
    padding: 20px;
    border: 1px solid #ddd;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    width: 700px;
	  margin: 0 auto;
	  margin-top: 50px;
  }
  #updateResult {
    margin-bottom: 20px;
  }
  #updateResult p {
    font-size: 18px;
    color: #333;
    margin: 0;
  }
  #cartUpdateProForm input[type="submit"] {
    background-color: #4CAF50;
    color: white;
    border: none;
    padding: 10px 20px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
    border-radius: 4px;
    transition: background-color 0.3s ease;
  }
  #cartUpdateProForm input[type="submit"]:hover {
    background-color: #45a049;
  }
</style>
<c:if test="${empty sessionScope.id}">
  <meta http-equiv="Refresh" content="0;url=/my-shoppingmall/index.do">
</c:if>

<div id="container">
  <div id="updateResult">
    <p>수량이 수정되었습니다.</p>
  </div>
  <div id="cartUpdatePro">
    <form id="cartUpdateProForm" method="post" action="/my-shoppingmall/cartList.do">
      <input type="hidden" name="buyer" value="${sessionScope.id}">
      <input type="submit" value="장바구니로 되돌아가기">  
    </form>
  </div>
</div> 