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
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                try {
                    var response = JSON.parse(xhr.responseText);
                    if (response.success) {
                        window.location.href = "/my-shoppingmall/mg/managerMain.do";
                    } else {
                        alert("로그인 실패: " + response.message);
                    }
                } catch (e) {
                    console.error("응답 파싱 오류:", e);
                    alert("로그인 처리 중 오류가 발생했습니다.");
                }
            } else {
                console.error("로그인 요청 실패:", xhr.status, xhr.statusText);
                alert("로그인 요청에 실패했습니다.");
            }
        }
    };
    xhr.send("id=" + encodeURIComponent(query.id) + "&passwd=" + encodeURIComponent(query.passwd));
}

function logout() {
    alert("로그아웃");
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/my-shoppingmall/mg/managerLogout.do", true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                window.location.href = "/my-shoppingmall/mg/managerMain.do";
            } else {
                console.error("로그아웃 요청 실패:", xhr.status, xhr.statusText);
                alert("로그아웃 요청에 실패했습니다.");
            }
        }
    };
    xhr.send();
}
