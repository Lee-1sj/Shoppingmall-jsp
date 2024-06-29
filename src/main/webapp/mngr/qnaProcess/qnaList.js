document.addEventListener('DOMContentLoaded', function() {
    // [관리자 메인으로] 버튼 클릭
    var goodsMainButton = document.getElementById('goodsMain');
    goodsMainButton.addEventListener('click', function() { 
        window.location.href = "/my-shoppingmall/mg/managerMain.do";
    });
});

function reply(replyBtn){//[답변하기]버튼 클릭
    var rStr = replyBtn.name;
    var query = "/my-shoppingmall/mg/qnaReplyForm.do?qna_id=" + rStr;
    window.location.href = query;
}

function edit(editBtn){//[수정]버튼 클릭
    var rStr = editBtn.name;
    var query = "/my-shoppingmall/mg/qnaReplyUpdateForm.do?qna_id=" + rStr;
    window.location.href = query;
}
