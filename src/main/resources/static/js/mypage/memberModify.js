function accUpdate(){
	$(".modifyForm").submit();
}

$(".accSecession").on('click', function(){
	$(".confirmPwd").toggle(1000);
});

/* 비밀번호 유효성 검사
document.querySelector(".confirmPwdTbl [name=password]").addEventListener('keyup', function(){
	var pwd = $(".confirmPwdTbl [name=password]").val();
	var pwdPattern = /^(?=.*[a-zA-Z])(?=.*[-!@#$%^*+=])(?=.*[0-9]).{8,14}$/;
	if(!pwdPattern.test(pwd)) {
		$(".confirmPwdTbl #pwdVerify").text("8~14자리의 숫자, 영문자, 특수문자 입력 필수입니다");
		$(".confirmPwdTbl #pwdVerify").css('color', 'red');		
		$(".confirmPwdTbl .modifyBtn").attr('disabled', true);
		$(".confirmPwdTbl .modifyBtn").css('background-color', 'var(--inactive-btn-color)');
	} else {
		$(".confirmPwdTbl #pwdVerify").text("탈퇴");
		$(".confirmPwdTbl #pwdVerify").css('color', 'green');		
		$(".confirmPwdTbl .modifyBtn").attr('disabled', false);
		$(".confirmPwdTbl .modifyBtn").css('background-color', 'var(--main-color)');
	}
}); */

$(".accSecessionBtn").on('click', function(){
	if(confirm("탈퇴를 진행합니다")) {
		$(".confirmPwd").submit();
	}
});

$(".modifyBtn").on('click', function(){
	var password = $(".ModifyTable [name=password]").val();
	console.log(password);
	$.ajax({
		url: "/member/confirmPwd",
		data: {password : password},
		type: "post",
		success: function(data) {
			if(data == 'true') {
				window.open('/member/pwdUpdate', '비밀번호 변경', 'width=600, height=600px, resizable=no');
			} else {
				alert("비밀번호가 일치하지 않습니다");
			}
		},
		error: function(e){
			console.log(e);
		}
	});
});