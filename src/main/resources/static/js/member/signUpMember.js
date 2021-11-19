/* 비밀번호 힌트 ajax 조회 */
$(function(){
	$.ajax({
		url:"/member/pwdHint",
		success: function(data){
			var hintNo = $("#hintNo");
			for(let index in data) {
				hintNo.append($("<option>").val(data[index].hintNo).text(data[index].hintQ));
			}
		},
		error: function(e){
			console.log(e);
		}
	});
});

/* 아이디 유효성 검사 */
document.querySelector("[name=id]").addEventListener('keyup', function(){
    var id = $("[name=id]").val();
    var idPattern = /^[0-9a-zA-Z]{4,12}$/;
    // console.log(idPattern.test(id));
    // console.log(id);
    if($("[name=id]").attr("readonly") == "readonly") return;
    if(!idPattern.test(id) && !$("[name=id]").attr("readonly")) {
        $("#idVerify").text("아이디는 4~12자의 영문 대소문자와 숫자로만 입력 가능합니다");
        $("#idVerify").css('color', 'red');
        $("#idCheck").attr('disabled', true);
        $("#idCheck").css('background-color', 'var(--inactive-btn-color)');
    } else {
        $("#idVerify").text("사용 가능한 아이디입니다. 아이디 중복 확인을 진행해주세요");
        $("#idVerify").css('color', 'green');
        $("#idCheck").attr('disabled', false);
        $("#idCheck").css('background-color', 'var(--active-btn-color)');
    }
});


$("#idCheck").on('click', function(){
	// DB 중복 검사 필요 - checkId
	var userId = $("[name=id]");
	$.ajax({
		url: "/member/checkId",
		data: { userId : userId.val() },
		type : "post",
		success : function(data) {
			if(data == "success") {
				alert("사용 가능한 아이디입니다");
				userId.attr('readonly', true);
				$("#idCheck").attr("disabled", true);
				$("#idCheck").css('background-color', 'var(--inactive-btn-color)');
				$("#joinBtn").attr('disabled', false);
        		$("#joinBtn").css('background-color', 'var(--active-btn-color)');
			} else {
				alert("사용할 수 없는 아이디입니다");
			}
		},
		error : function(e) {
			console.log(e);
		}
	});
});

/* 비밀번호 유효성 검사 */
document.querySelector("[name=pwd]").addEventListener('keyup', function(){
	var pwd = $("[name=pwd]").val();
	var pwdPattern = /^(?=.*[a-zA-Z])(?=.*[-!@#$%^*+=])(?=.*[0-9]).{8,14}$/;
	if(!pwdPattern.test(pwd)) {
		$("#pwdVerify").text("8~14자리의 숫자, 영문자, 특수문자 입력 필수입니다");
		$("#pwdVerify").css('color', 'red');
	} else {
		$("#pwdVerify").text("사용 가능한 비밀번호입니다");
		$("#pwdVerify").css('color', 'green');		
	}
	// 기존 비밀번호가 변경되면 비밀번호 확인란도 다시 검사
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

/* 이름 유효성 검사 */
$("[name=name]").keyup(function(){
	var name = $("[name=name]").val();
	var namePattern = /^[가-힣]{2,6}$/;
	if(!namePattern.test(name)) {
		$("#nameVerify").text("한글명으로 2~6자 입력 가능합니다");
		$("#nameVerify").css('color', 'red');
	} else {
		$("#nameVerify").text("사용 가능한 한글명입니다");
		$("#nameVerify").css('color', 'green');			
	}
});

/* 전화번호 유효성 검사 + 문자 인증 */
$(".sendAuth").click(function(){
	var phone = $("[name=phone]").val();
	var phonePattern = /^[0-9]{11}$/;
	if(!phonePattern.test(phone)) {
		alert("전화번호는 숫자만 입력 가능합니다 !");
		$("[name=phone]").focus();
	} else {
		// 문자 인증 진행
		sendAuth(phone);
	}
});

/* 이메일 유효성 검사 */
$("#inputEmail").keyup(function(){
	var email = $("#inputEmail").val();
	var emailPattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	if(!emailPattern.test(email)){
		$("#emailVerify").text("이메일 형식에 맞지 않습니다");
		$("#emailVerify").css('color', 'red');
	} else {
		$("#emailVerify").text("올바른 이메일 형식입니다");
		$("#emailVerify").css('color', 'green');
	}
});

/* 비밀번호 힌트 답 유효성 검사 */
$("[name=hintA]").keyup(function(){
	var hintA = $("[name=hintA]").val();
	var hintAPattern = /^[0-9a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣]{2,}$/;
	if(!hintAPattern.test(hintA)){
		$("#hintAVerify").text("영문 대소문자, 숫자, 한글로만 2자 이상 입력해야 합니다");
		$("#hintAVerify").css('color', 'red');
	} else {
		$("#hintAVerify").text("올바른 비밀번호 힌트에 대한 답 형식입니다");
		$("#hintAVerify").css('color', 'green');	
	}
});

/* ajax 문자 전송 */
var authCode1 = "";
function sendAuth(phone) {
	$.ajax({
		type: "GET",
		url: "sendAuth?phone=" + phone,
		success: function(data) {
			if(data == "error") {
				alert("휴대폰 번호가 올바르지 않습니다. 유효한 번호를 입력해주세요")
			} else {
				alert("인증번호가 전송되었습니다");
				$("[name=phone]").attr("readonly", true);
				$("#sendAuth").attr("disabled", true);
				$("#sendAuth").css('background-color', 'var(--inactive-btn-color)');
				$("#inputAuthCode").attr("disabled", false);
				$("#authCodeCheckBtn").attr("disabled", false);
				$("#authCodeCheckBtn").css('background-color', 'var(--active-btn-color)');
				console.log(data);
				authCode1 = data;
			}
		},
		error: function(e) {
			console.log(e);
		}
	});
};

/* 인증번호 일치 확인 */
function authCodeCheck() {
	var authCode2 = $("#inputAuthCode").val();
	if(authCode2 == authCode1) {
		alert("인증이 완료되었습니다");
		$("#inputAuthCode").attr("disabled", true);
		$("#authCodeCheckBtn").attr("disabled", true);
		$("#authCodeCheckBtn").css('background-color', 'var(--inactive-btn-color)');
	} else {
		alert("인증번호가 일치하지 않습니다");
	}
}

/* 약관 전체 동의 체크 & 해제 */
$("#alljoin").change(function(){
	var chk = $("#alljoin").is(":checked");
	// console.log(chk);
	if(chk) {
		// console.log($(".chkbox"));
		$(".chkbox").find('input').prop("checked", true);
	} else {
		$(".chkbox").find('input').prop("checked", false);
	}
});

/* 개별 동의 체크 & 해제 */
$(".chkbox").find('input').change(function(){
	var count = 0;
	document.querySelectorAll(".chkbox").forEach((elem) => {
		// console.log(elem.children[0].checked);
		if(elem.children[0].checked == true) {
			count++;
		}
	});
	
	if(count == 3) {
		// console.log(count);
		$("#alljoin").prop("checked", true);
	} else {
		$("#alljoin").prop("checked", false);
	}
});

/* 가입하기 클릭 */
$("#joinBtn").on('click', function(){
	var submitBool = false;
	if( $("#chkjoin1").is(":checked")							// 필수 동의 1
		&& $("#chkjoin2").is(":checked")						// 필수 동의 2
		&& $("#idVerify").css('color') == "rgb(0, 128, 0)"		// id 형식
		&& $("#pwdVerify").css('color') == "rgb(0, 128, 0)"		// pwd 형식
		&& $("#pwdDblChk").css('color') == "rgb(0, 128, 0)"		// pwd 일치 확인
		&& $("#nameVerify").css('color') == "rgb(0, 128, 0)"	// 회원명 형식
		&& $("#emailVerify").css('color') == "rgb(0, 128, 0)"	// 이메일 형식
		&& $("#hintAVerify").css('color') == "rgb(0, 128, 0)"){	// 힌트답 형식
		submitBool = true;
	} else {
		alert("형식에 맞지 않는 입력이 존재합니다 ! 다시 한번 확인하세요");
	}
	return submitBool;
});