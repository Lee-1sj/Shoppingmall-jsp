<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<%
    HttpSession currentSession = request.getSession(false);
    if (currentSession != null) {
        Integer check = (Integer) request.getAttribute("check");
        if (check != null && check == 1) {
            currentSession.removeAttribute("id");
        }
    }
%>

<meta name="viewport" content="width=device-width,initial-scale=1.0"/>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        var check = ${check};

        if (check === 1) {
            alert('회원 탈퇴가 완료되었습니다.');
            window.location.href = '/my-shoppingmall/index.do';
        } else {
            alert('회원 탈퇴에 실패했습니다.');
            window.location.href = '/my-shoppingmall/modify.do';
        }
    });
</script>
