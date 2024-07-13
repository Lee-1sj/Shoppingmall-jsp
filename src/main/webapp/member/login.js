document.addEventListener('DOMContentLoaded', function() {
	// [회원가입] 버튼 클릭
	var uResButton = document.getElementById('uRes');
	uResButton.addEventListener('click', function() {
		window.location.href = '/my-shoppingmall/registerForm.do';
	});

	// [로그인] 버튼 클릭
	var uLoginButton = document.getElementById('uLogin');
	uLoginButton.addEventListener('click', function() {
		alert("로그인");
		var query = {
			id: document.getElementById('cid').value,
			passwd: document.getElementById('cpasswd').value
		};

		var xhr = new XMLHttpRequest();
		xhr.open('POST', '/my-shoppingmall/loginPro.do', true);
		xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xhr.onreadystatechange = function() {
			if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
				var data = xhr.responseText;
				var str1 = '<p id="ck">';
				var loc = data.indexOf(str1);
				var len = str1.length;
				var check = data.substr(loc + len, 1);
				if (check == '1') {
					window.location.href = '/my-shoppingmall/index.do';
				} else if (check == '0') {
					alert('비밀번호 틀림');
					document.getElementById('cpasswd').value = '';
				} else {
					alert('아이디 틀림');
					document.getElementById('cid').value = '';
					document.getElementById('cpasswd').value = '';
				}
			} else if (xhr.readyState === 4) {
				console.error('Error:', xhr.statusText);
			}
		};

		xhr.onerror = function() {
			console.error('Request failed');
		};

		xhr.send("id=" + encodeURIComponent(query.id) +
			"&passwd=" + encodeURIComponent(query.passwd));
	});
});

// [회원 정보 변경] 버튼 클릭
function uUpdate() {
	alert("회원 정보 변경");
	window.location.href = '/my-shoppingmall/modify.do';
}

// [로그아웃] 버튼 클릭
function uLogout() {
	alert("로그아웃");
	fetch('/my-shoppingmall/logout.do', {
		method: 'POST'
	}).then(function(response) {
		return response.text();
	}).then(function(data) {
		window.location.href = '/my-shoppingmall/index.do';
	}).catch(function(error) {
		console.error('Error:', error);
	});
}