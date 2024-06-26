function login() {
	alert("로그인");
	var query = {
		id: document.getElementById("id").value,
		passwd: document.getElementById("passwd").value
	};

	var xhr = new XMLHttpRequest();
	xhr.open("POST", "/my-shoppingmall/mg/managerLoginPro.do", true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.onreadystatechange = function() {
		if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
			window.location.href = "/my-shoppingmall/mg/managerMain.do";
		}
	};
	xhr.send("id=" + encodeURIComponent(query.id) + "&passwd=" + encodeURIComponent(query.passwd));
}

function logout() {
	alert("로그아웃");
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "/my-shoppingmall/mg/managerLogout.do", true);
	xhr.onreadystatechange = function() {
		if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
			window.location.href = "/my-shoppingmall/mg/managerMain.do";
		}
	};
	xhr.send();
}