
$("#findIdBtn").on('click', function(){
	var name = $("[name=name]").val();
	var namePattern = /^[가-힣]{2,6}$/;

	var email = $("[name=email]").val();
	var emailPattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	
	console.log(emailPattern.test(email));
	
	if(!namePattern.test(name)) {
		alert("이름은 2자 이상 실명으로 입력해주세요");
	} else if (!emailPattern.test(email)) {
		alert("이메일은 kh@khculture.kr 형식으로 입력해주세요");
	} else {
		$("#inputArea").submit();	
	}
});