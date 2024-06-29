document.addEventListener('DOMContentLoaded', function() {
    var updateButton = document.getElementById('update');
    updateButton.addEventListener('click', function() {
        var query = {
            qna_content: document.getElementById('uRContent').value,
            qna_id: document.getElementById('qna_id').value
        };

        var xhr = new XMLHttpRequest();
        xhr.open('POST', '/my-shoppingmall/mg/qnaReplyUpdatePro.do', true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    window.location.href = '/my-shoppingmall/mg/qnaList.do';
                } else {
                    console.error('Error:', xhr.statusText);
                }
            }
        };

        xhr.onerror = function() {
            console.error('Request failed');
        };

        var queryString = Object.keys(query)
            .map(function(key) {
                return encodeURIComponent(key) + '=' + encodeURIComponent(query[key]);
            })
            .join('&');

        xhr.send(queryString);
    });

    // [취소] 버튼 클릭
    var cancelButton = document.getElementById('cancel'); 
    cancelButton.addEventListener('click', function() {
        window.location.href = "/my-shoppingmall/mg/qnaList.do";
    });
});
