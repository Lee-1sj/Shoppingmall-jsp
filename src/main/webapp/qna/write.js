document.addEventListener('DOMContentLoaded', function() {
	var registButton = document.getElementById('regist');
	registButton.addEventListener('click', function() {
		// [등록] 버튼 클릭
		var goods_kind = document.getElementById('goods_kind').value;
		var goods_id = document.getElementById('goods_id').value;

		var query = {
			qna_content: document.getElementById('qnaCont').value,
			qna_writer: document.getElementById('qna_writer').value,
			goods_title: document.getElementById('goods_title').value,
			goods_id: goods_id,
			qora: document.getElementById('qora').value
		};

		var xhr = new XMLHttpRequest();
		xhr.open('POST', '/my-shoppingmall/qnaPro.do', true);
		xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4 && xhr.status === 200) {
				var data = xhr.responseText;
				var str1 = '<p id="ck">';
				var loc = data.indexOf(str1);
				var len = str1.length;
				var check = data.substr(loc + len, 1);
				if (check == '1') {
					alert('QnA가 등록되었습니다.');
					var query = '/my-shoppingmall/goodsContent.do?goods_id=' + goods_id;
					query += '&goods_kind=' + goods_kind;
					window.location.href = query;
				} else {
					alert('QnA 등록 실패');
				}
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

	var cancelButton = document.getElementById('cancel');
	cancelButton.addEventListener('click', function() {
		// [취소] 버튼 클릭
		var goods_kind = document.getElementById('goods_kind').value;
		var goods_id = document.getElementById('goods_id').value;
		var query = '/my-shoppingmall/goodsContent.do?goods_id=' + goods_id;
		query += '&goods_kind=' + goods_kind;
		window.location.href = query;
	});
});
