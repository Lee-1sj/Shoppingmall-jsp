document.addEventListener('DOMContentLoaded', function() {
    // [관리자 메인으로] 버튼 클릭
    var goodsMainButton = document.getElementById('goodsMain');
    goodsMainButton.addEventListener('click', function() {
        window.location.href = "/my-shoppingmall/mg/managerMain.do";
    });
});
