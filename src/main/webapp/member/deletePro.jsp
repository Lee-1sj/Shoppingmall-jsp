<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        var check = ${check};

        if (check === 1) {
            // 세션에서 id 제거
            <c:remove var="id" scope="session"/>
            alert('회원 탈퇴가 완료되었습니다.');
        } else {
            alert('회원 탈퇴에 실패했습니다.');
        }
        // 모든 경우에 대해 index.do로 리다이렉션
        window.location.href = '/my-shoppingmall/index.do';
    });
</script>
