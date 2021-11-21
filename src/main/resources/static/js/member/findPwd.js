/* 비밀번호 힌트 ajax 조회 */
$(function(){
	$.ajax({
		url:"/member/pwdHint",
		success: function(data){
			var hintNo = $("#hintNo");
			hintNo.append($("<option>").text("비밀번호 힌트 질문을 선택하세요").attr({"disabled": "true", "selected": "true", "hidden":"true"}));
			for(let index in data) {
				hintNo.append($("<option>").val(data[index].hintNo).text(data[index].hintQ));
			}
		},
		error: function(e){
			console.log(e);
		}
	});
});

$("#findPwdBtn").on('click', function(){
	var id = $("[name=id]").val();
	var hintNo = $("[name=hintNo]").val();
	var hintA = $("[name=hintA]").val();

	if(id.length < 4) {
		alert("아이디는 4자 이상 영문, 숫자로 입력해주세요");
		return false;
	} else if(hintNo < 1) {
		alert("질문 선택은 필수입니다");
		return false;
	} else if(hintA.length < 2) {
		alert("비밀번호 힌트에 대한 답은 2자 이상 작성 필수입니다");
		return false;
	} else {
		$("form").submit();	
	}
});
