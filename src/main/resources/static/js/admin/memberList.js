// 페이징 바
$(document).ready(function(){
	var pagingUl = document.querySelector(".member_paging");
	let pagingHtml = `<li><a href="#" onclick="firstPage()">&lt;&lt;</a></li>
					  <li><a href="#" onclick="prevPage()">&lt;</a></li>`;
	
	for(let i = startPage; i <= endPage; i++) {
		if(i == currentPage) {
			pagingHtml += `<li><a href="#" class="current_page pagingNum">` + i + `</a></li>`;
		} else {
			pagingHtml += `<li><a href="#" onclick="goPage(` + i + `)" class="pagingNum">` + i + `</a></li>`;
		}
	}
	pagingHtml += `<li><a href="#" onclick="nextPage()">&gt;</a></li>
	   <li><a href="#" onclick="lastPage()">&gt;&gt;</a></li>`
	 							
	pagingUl.insertAdjacentHTML('beforeend', pagingHtml);
});

// 페이징 이벤트
function firstPage(){
	if(currentPage == 1) {
		return;
	}
	$(".searchForm [name=page]").val(1);
	$(".searchForm").submit();
}

function prevPage(){
	if(currentPage == 1) {
		return;
	}
	$(".searchForm [name=page]").val(currentPage * 1 - 1);
	$(".searchForm").submit();
}

function nextPage(){
	if(currentPage == maxPage){
		return
	}
	$(".searchForm [name=page]").val(currentPage * 1 + 1);
	$(".searchForm").submit();	
}

function lastPage(){
	if(currentPage == maxPage){
		return
	}
	$(".searchForm [name=page]").val(endPage);
	$(".searchForm").submit();	
}

function goPage(i) {
	$(".searchForm [name=page]").val(i);
	$(".searchForm").submit();	
}

// 회원등급 체크박스
$("[name=memberRole]").click(function(){
	if($(this).prop('checked')){
		$('[name=memberRole]').prop('checked', false);
		$(this).prop('checked', true);
	}
});

// 회원전체 체크
$(".memberListTbl .allChecked").click(function(){
	var chk = $(this).is(':checked');
	if(chk) {
		$(".memberListTbl .oneChecked").prop('checked', true);
	} else {
		$(".memberListTbl .oneChecked").prop('checked', false);		
	}
});

// 개별 체크 & 해제
$(".oneChecked").change(function(){
	var count = 0;
	document.querySelectorAll(".oneChecked").forEach((e) => {
		if(e.checked == true) {
			count++;
		}
	});
	if(count == document.querySelectorAll(".oneChecked").length){
		$(".memberListTbl .allChecked").prop('checked', true);
	} else {
		$(".memberListTbl .allChecked").prop('checked', false);	
	}
});

// 선택된 회원번호 리스트
function confirmChk(){
	var arr = [];
	document.querySelectorAll(".oneChecked:checked").forEach((e) => {
		arr.push(e.value);
	});
	return arr;
}

// 비밀번호 초기화
function pwdReset(){
	var arr = confirmChk();
	// console.log(arr.length);
	if(arr.length > 0){
		$(".editForm").attr("action", pwdResetUrl);
		$(".editForm input[type=hidden]").val(arr);
		$(".editForm").submit();
	} else {
		alert("비밀번호를 초기화할 회원을 선택하세요");
	}
}

// 계정삭제
function deleteAcc(){
	var count = 0;
	var arr = [];
	document.querySelectorAll(".oneChecked:checked").forEach((e) => {
		var accSecessionYN = e.parentNode.parentNode.lastElementChild.textContent;
		if(accSecessionYN == 'N') {
			count++;
		} else {
			arr = confirmChk();
		}
	});
	if(count > 0) {
		alert("탈퇴하지 않은 회원은 계정을 삭제할 수 없습니다");
	} else {
		$(".editForm").attr("action", deleteAccUrl);
		$(".editForm input[type=hidden]").val(arr);
		$(".editForm").submit();
	}
}

// 등급 변경
function roleUpate(){
	var arr = confirmChk();
	$(".editForm").attr("action", roleUpateUrl);
	$(".editForm input[type=hidden]").val(arr);
	$(".editForm").submit();
}