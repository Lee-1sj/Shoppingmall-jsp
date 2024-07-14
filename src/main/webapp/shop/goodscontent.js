document.addEventListener('DOMContentLoaded', function() {
	// [장바구니에 담기] 버튼 클릭
	var insertCartButton = document.getElementById('insertCart');
	if (insertCartButton) { 
		insertCartButton.addEventListener('click', function() {
			var buyer = document.getElementById('buyer').value;
			var goods_kind = document.getElementById('goods_kind').value;
			var query = {
				goods_id: document.getElementById('goods_id').value,
				buy_count: document.getElementById('buy_count').value,
				goods_image: document.getElementById('goods_image').value,
				goods_title: document.getElementById('goods_title').value,
				buy_price: document.getElementById('buy_price').value,
				buyer: buyer
			};

			var xhr = new XMLHttpRequest();
			xhr.open('POST', '/my-shoppingmall/insertCart.do', true);
			xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
			xhr.onreadystatechange = function() {
				if (xhr.readyState === 4 && xhr.status === 200) {
					alert('장바구니에 담겼습니다.');
				} else if (xhr.readyState === 4) {
					console.error('Error:', xhr.statusText);
				}
			};

			xhr.onerror = function() {
				console.error('Request failed');
			};
			// 쿼리 문자열 생성
			var queryString = Object.keys(query)
				.map(function(key) {
					return encodeURIComponent(key) + '=' + encodeURIComponent(query[key]);
				})
				.join('&');

			xhr.send(queryString); // AJAX 요청 전송
		});
	}

	// [상품QnA쓰기] 버튼 클릭
	var writeQnaButton = document.getElementById('writeQna');
	if (writeQnaButton) { 
		writeQnaButton.addEventListener('click', function() {
			var goods_id = document.getElementById('goods_id').value;
			var goods_kind = document.getElementById('goods_kind').value;
			var query = '/my-shoppingmall/qnaForm.do?goods_id=' + goods_id + '&goods_kind=' + goods_kind;
			window.location.href = query;
		});
	}
});

// [목록으로] 버튼 클릭
function list() {
	window.location.href = '/my-shoppingmall/list.do?goods_kind=all';
}

// [메인으로] 버튼 클릭
function shopMain() {
	window.location.href = '/my-shoppingmall/index.do';
}

// [수정] 버튼 클릭
function edit(editBtn) {
	var rStr = editBtn.name;
	var arr = rStr.split(',');
	var query = '/my-shoppingmall/qnaUpdateForm.do?qna_id=' + arr[0] + '&goods_kind=' + arr[1];
	window.location.href = query;
}

// [삭제] 버튼 클릭
function del(delBtn) {
	alert("삭제진행2");
	var rStr = delBtn.name;
	var arr = rStr.split(',');
	var query = { qna_id: arr[0] };

	var xhr = new XMLHttpRequest();
	xhr.open('POST', '/my-shoppingmall/qnaDeletePro.do', true);
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4 && xhr.status === 200) {
			var data = xhr.responseText;
			var str1 = '<p id="ck">';
			var loc = data.indexOf(str1);
			var len = str1.length;
			var check = data.substr(loc + len, 1);
			if (check == '1') {
				alert('QnA가 삭제 되었습니다.');
				var query = '/my-shoppingmall/goodsContent.do?goods_id=' + arr[1] + '&goods_kind=' + arr[2];
				window.location.href = query;
			} else {
				alert('QnA 삭제 실패');
			}
		} else if (xhr.readyState === 4) {
			console.error('Error:', xhr.statusText);
		}
	};

	xhr.onerror = function() {
		console.error('Request failed');
	};

	// 쿼리 문자열 생성 전송
	xhr.send("qna_id=" + encodeURIComponent(query.qna_id));
}
