/* 아이디 공란 시 창닫기 */
$(function(){
	if($("#userId").text() == '') {
		window.close();
	} else {
		$("[name=id]").val($("#userId").text());
	}
});

/* 비밀번호 유효성 검사 */
document.querySelector("[name=pwd]").addEventListener('keyup', function(){
	var pwd = $("[name=pwd]").val();
	var pwdPattern = /^(?=.*[a-zA-Z])(?=.*[-!@#$%^*+=])(?=.*[0-9]).{8,14}$/;
	if(!pwdPattern.test(pwd)) {
		$("#pwdVerify").text("숫자, 영문자, 특수문자를 한 개 이상 포함하여 8~14자 입력하세요").css('color', 'red');
		$("#setNewPwd").attr("disabled", true);
		$("#setNewPwd").css('background-color', 'var(--inactive-btn-color)');	
	} else {
		$("#pwdVerify").text("사용 가능한 비밀번호입니다").css('color', 'green');
		$("#setNewPwd").attr('disabled', false);
		$("#setNewPwd").css('background-color', 'var(--active-btn-color)');	
	}
	if($("[name=pwd2]").val() != '') pwdDblChk();
});

/* 비밀번호 일치 확인 */
document.querySelector("[name=pwd2]").addEventListener('keyup', function(){
	pwdDblChk();
});

function pwdDblChk(){
	var pwd = $("[name=pwd]").val();
	var pwd2 = $("[name=pwd2]").val();
	if(pwd == pwd2) {
		$("#pwdDblChk").text("비밀번호가 일치합니다");
		$("#pwdDblChk").css('color', 'green');
	} else {
		$("#pwdDblChk").text("비밀번호가 일치하지 않습니다");
		$("#pwdDblChk").css('color', 'red');		
	}
}

$("#setNewPwd").on('click', function(){
	if($("#pwdVerify").css('color') == "rgb(0, 128, 0)"
		&& $("#pwdDblChk").css('color') == "rgb(0, 128, 0)") {
		$("#passwordReset").submit();
	}
});