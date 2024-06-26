<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="/my-shoppingmall/css/goodsList.css?ver=<%=new Date().getTime()%>"/>
<script src="/my-shoppingmall/mngr/productProcess/goodsList.js?ver=<%=new Date().getTime()%>"></script>

<c:if test="${empty sessionScope.id}">
    <meta http-equiv="Refresh" content="0;url=/my-shoppingmall/mg/managerMain.do">
</c:if>

<div id="listHeader">
    <p class="headerText">등록된 상품 목록(전체 상품:${count})</p>
    <button id="regist">상품 등록</button>
    <button id="goodsMain">관리자 메인으로</button>
</div>

<div id="goods">
    <c:if test="${count == 0}">
        <ul>
            <li>등록된 상품이 없습니다.</li>
        </ul>
    </c:if>
    <c:if test="${count > 0}">
        <table class="goodsTable">
            <tr class="titleRow">
                <th>번호</th>
                <th>분류</th>
                <th>이름</th>
                <th>가격</th>
                <th>수량</th>
                <th>사이즈</th>
                <th>이미지</th>
                <th>할인율</th>
                <th>등록일</th>
                <th>수정</th>
                <th>삭제</th>
            </tr>
            <c:set var="number" value="0"/>
            <c:forEach var="goods" items="${goodsList}">
                <c:set var="number" value="${number + 1}"/>
                <tr class="dataRow">
                    <td class="number"><c:out value="${number}"/></td>
                    <td class="kind"><c:out value="${goods.goods_kind}"/></td>
                    <td class="title"><c:out value="${goods.goods_title}"/></td>
                    <td class="price"><c:out value="${goods.goods_price}"/></td>
                    <td class="count">
                        <c:choose>
                            <c:when test="${goods.goods_count == 0}">
                                <span class="outOfStock">일시품절</span>
                            </c:when>
                            <c:otherwise>
                                <c:out value="${goods.goods_count}"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td class="size"><c:out value="${goods.goods_size}"/></td>
                    <td class="image"><c:out value="${goods.goods_image}"/></td>
                    <td class="discount"><c:out value="${goods.discount_rate}"/>%</td>
                    <td class="date"><fmt:formatDate pattern="yyyy-MM-dd" value="${goods.reg_date}"/></td>
                    <td class="edit">
                        <button class="editButton" name="${goods.goods_id},${goods.goods_kind}" onclick="edit(this)">수정</button>
                    </td>
                    <td class="delete">
                        <button class="deleteButton" name="${goods.goods_id},${goods.goods_kind}" onclick="del(this)">삭제</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
