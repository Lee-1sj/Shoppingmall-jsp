document.addEventListener('DOMContentLoaded', function() {
	var conShoppingButton = document.getElementById('continueShopping');
	// [쇼핑계속] 버튼 클릭
	conShoppingButton.addEventListener('click', function() {
		window.location.href = "/my-shoppingmall/list.do?goods_kind=all";
	});

	// [메인으로] 버튼 클릭
	var shopMainButton = document.getElementById('shopMain');
	shopMainButton.addEventListener('click', function() {
		window.location.href = "/my-shoppingmall/index.do";
	});
});


function editSu(editBtn) {//[수정]버튼 클릭
	var rStr = editBtn.name;
	var arr = rStr.split(",");
	var query = "/my-shoppingmall/cartUpdateForm.do?cart_id=" + arr[0];
	query += "&buy_count=" + arr[1];
	window.location.href = query;
}

function delList(delBtn) {//[삭제]버튼 클릭
	var rStr = delBtn.name;
	var query = "/my-shoppingmall/deleteCart.do?list=" + rStr;
	window.location.href = query;
}