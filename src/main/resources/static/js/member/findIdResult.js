/* 아이디 일부 '*' 표기 */
$(function(){
	$("#idResult").text(id.replace(id.slice(-3), "***"));
});

/* ajax 이메일 전송 */
var authCode1 = "";
$("#sendAuth").on('click', function(){
	$.ajax({
		url: "/sendEmailAuth",
		type: "POST",
		data: {"email": $("#emailResult").text()},
		success: function(data) {
			if(data == null) {
				alert("오류가 발생했습니다. 인증번호를 다시 전송해주세요");
			} else {
				console.log(data);
				alert("이메일로 인증번호가 전송되었습니다");
				authCode1 = data;
				$("#sendAuth").css('background-color', 'var(--inactive-btn-color)');
				$("#sendAuth").attr('disabled', true);
				$("#inputAuthCode").attr('readonly', false);
				$("#authCodeCheckBtn").attr('disabled', false);
				$("#authCodeCheckBtn").css('background-color', 'var(--active-btn-color)');
			}
		},
		error: function(e) {
			console.log(e);
		}
	});
});

function authCodeCheck(){
	var authCode2 = $("#inputAuthCode").val();
	if(authCode1 == authCode2) {
		alert("인증이 완료되었습니다! 화면에 출력되는 아이디를 확인하세요");
		$("#fullIdResult").text(id);
	} else {
		alert("인증번호가 일치하지 않습니다. 다시 확인하세요");
	}
}