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

function accUpdate(){
	$(".modifyForm").submit();
}

function accSecession(){
	location.href='/member/accSecession';
}