<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>

<c:choose>
    <c:when test="${check == 1}">
        <script>
            alert('QnA가 등록되었습니다.');
            window.location.href = '/my-shoppingmall/goodsContent.do?goods_id=${param.goods_id}&goods_kind=${param.goods_kind}';
        </script>
    </c:when>
    <c:when test="${check == 0}">
        <script>
            alert('QnA 등록 실패');
            window.history.back();
        </script>
    </c:when>
    <c:otherwise>
        <script>
            alert('알 수 없는 오류가 발생했습니다.');
            window.location.href = '/my-shoppingmall/index.do';
        </script>
    </c:otherwise>
</c:choose>
<p id="ck">${check}</p>
