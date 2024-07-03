document.addEventListener('DOMContentLoaded', function() {
	var cancleButton = document.getElementById('cancel');

	// [취소] 버튼 클릭
	cancleButton.addEventListener('click', function(event) {
		alert('cancel');
		event.preventDefault();
		window.location.href = "/my-shoppingmall/index.do";
	});
});


