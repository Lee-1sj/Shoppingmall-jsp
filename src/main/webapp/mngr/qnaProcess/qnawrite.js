document.addEventListener('DOMContentLoaded', function() {
	var replyProButton = document.getElementById('replyPro');
	replyProButton.addEventListener('click', function() {
		var query = {
			qna_content: document.getElementById('rContent').value,
			qna_writer: document.getElementById('qna_writer').value,
			goods_title: document.getElementById('goods_title').value,
			goods_id: document.getElementById('goods_id').value,
			qna_id: document.getElementById('qna_id').value,
			qora: document.getElementById('qora').value
		};

		var xhr = new XMLHttpRequest();
		xhr.open('POST', '/my-shoppingmall/mg/qnaReplyPro.do', true);
		xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4 && xhr.status === 200) {
				window.location.href = '/my-shoppingmall/mg/qnaList.do';
			} else if (xhr.readyState === 4) {
				console.error('Error:', xhr.statusText);
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
		window.location.href = "/my-shoppingmall/mg/managerMain.do";
	});
});
