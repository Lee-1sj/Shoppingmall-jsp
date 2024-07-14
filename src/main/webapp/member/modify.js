document.addEventListener('DOMContentLoaded', function() {
	// [수정] 버튼 클릭
    var modifyProcessButton = document.getElementById('modifyProcess');
    if (modifyProcessButton) {
        modifyProcessButton.addEventListener('click', function() {
            var id = document.getElementById('id');
            var passwd = document.getElementById('passwd');
            var name = document.getElementById('name');
            var address = document.getElementById('address');
            var tel = document.getElementById('tel');

            if (id && passwd && name && address && tel) {
                var query = {
                    id: id.value,
                    passwd: passwd.value,
                    name: name.value,
                    address: address.value,
                    tel: tel.value
                };

                var xhr = new XMLHttpRequest();
                xhr.open('POST', '/my-shoppingmall/modifyPro.do', true);
                xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

                xhr.onreadystatechange = function() {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        var data = xhr.responseText;
                        console.log('Server response:', data);

                        var str1 = '<p id="ck">';
                        var loc = data.indexOf(str1);
                        if (loc !== -1) {
                            var len = str1.length;
                            var check = data.substr(loc + len, 1);
                            if (check == '1') {
                                alert('회원정보가 수정되었습니다.');
                                window.location.href = '/my-shoppingmall/index.do';
                            } else {
                                alert('비밀번호 틀림.');
                                passwd.value = '';
                                passwd.focus();
                            }
                        } else {
                            console.error('Expected string not found in response');
                        }
                    } else if (xhr.readyState === 4) {
                        console.error('Error:', xhr.statusText);
                    }
                };

                xhr.onerror = function() {
                    console.error('Request failed');
                };
                xhr.send("id=" + encodeURIComponent(query.id) +
                    "&passwd=" + encodeURIComponent(query.passwd) +
                    "&name=" + encodeURIComponent(query.name) +
                    "&address=" + encodeURIComponent(query.address) +
                    "&tel=" + encodeURIComponent(query.tel)
                );
            } else {
                console.error('One or more form elements are missing');
            }
        });
    } else {
        console.error('modifyProcess button not found');
    }

	// [취소] 버튼 클릭
	var cancelButton = document.getElementById('cancel');
	cancelButton.addEventListener('click', function() {
		window.location.href = '/my-shoppingmall/index.do';
	});
});