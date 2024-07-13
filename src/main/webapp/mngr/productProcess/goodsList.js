document.addEventListener('DOMContentLoaded', function() {
    // [상품 등록] 버튼 클릭
    var registButton = document.getElementById('regist');
    registButton.addEventListener('click', function() {
        window.location.href = "/my-shoppingmall/mg/goodsRegisterForm.do";
    });

    // [관리자 메인으로] 버튼 클릭
    var mainButton = document.getElementById('goodsMain');
    mainButton.addEventListener('click', function() {
        window.location.href = "/my-shoppingmall/mg/managerMain.do";
    });
});

// [수정] 버튼을 클릭
function edit(editBtn) {
    var rStr = editBtn.name;
    var arr = rStr.split(",");
    var query = arr[0] + "번의 상품 수정";
    var query2 = "/my-shoppingmall/mg/goodsUpdateForm.do?goods_id=" + arr[0];
    query2 += "&goods_kind=" + arr[1];
    alert(query);
    window.location.href = query2; 
}

// [삭제] 버튼을 클릭
function del(delBtn) {
    var rStr = delBtn.name;
    var arr = rStr.split(",");
    var query2 = arr[0] + "번의 상품 삭제";
    var query = "/my-shoppingmall/mg/goodsDeletePro.do?goods_id=" + arr[0];
    query += "&goods_kind=" + arr[1];
    alert(query2);
    window.location.href = query; 
}
