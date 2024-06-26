var status = true;

document.addEventListener("DOMContentLoaded", function() {
	  //[상품등록]버튼 클릭
    document.getElementById("registProduct").addEventListener("click", function() {
        alert("상품등록"); 
        
        window.location.href = "/shoppingmall/mg/bookRegisterForm.do";
        
    });
    //[상품수정/삭제]버튼 클릭
    document.getElementById("updateProduct").addEventListener("click", function() {
        window.location.href = "/shoppingmall/mg/bookList.do?book_kind=all";
    });
    //[전체구매목록 확인]버튼 클릭
    document.getElementById("orderedProduct").addEventListener("click", function() {
        window.location.href = "/shoppingmall/mg/orderList.do";
    });
    //[상품 QnA답변]버튼 클릭
    document.getElementById("qna").addEventListener("click", function() {
        window.location.href = "/shoppingmall/mg/qnaList.do";
    });
});