<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>

<script>
    document.addEventListener('DOMContentLoaded', function() {
    	  console.log("modifyPro.jsp loaded");
    		var check = "<%= request.getAttribute("check") %>";
        console.log('Check value: ', check); // 디버깅을 위한 콘솔 출력
        if (check === 1) {
            alert("회원정보가 수정되었습니다.");
            window.location.href = "/my-shoppingmall/index.do";
        } else if (check === 0) {
            alert("비밀번호가 틀렸습니다.");
            window.location.href = "/my-shoppingmall/modifyForm.do";
        } else if (check === -1) {
            alert("입력 값이 부족합니다.");
            window.location.href = "/my-shoppingmall/modifyForm.do";
        } else {
            alert("오류가 발생했습니다. 다시 시도해 주세요.");
            window.location.href = "/my-shoppingmall/index.do";
        }
    });
</script>
