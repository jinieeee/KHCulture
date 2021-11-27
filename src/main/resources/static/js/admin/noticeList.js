// 페이징 바
$(document).ready(function(){
	var pagingUl = document.querySelector(".notice_paging");
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

$(".noticeListTbl #updateNotice").on('click', function(){
	var nno = $(this).siblings().last().val();
	location.href = "/notice/updateView?n_no=" + nno;
});

$(".noticeListTbl #deleteNotice").on('click', function(){
	var nno = $(this).siblings().last().val();
	if(!confirm(nno + '번 공지사항을 삭제하시겠습니까?')) {
		return;
	}
	$(".frm [name=n_no]").val(nno);
	$(".frm").attr('action', deleteNoticeUrl);
	$(".frm").attr('method', 'POST');
	$(".frm").submit();
});

$(".noticeListTbl #enrollMain").on('click', function(){
	var nno = $(this).siblings().last().val();
	$(".enrollForm [name=n_no]").val(nno);
	$(".enrollForm [type=file]").click();
});

$(".enrollForm [type=file]").on('change', function(){
	console.log($(this).val());
	$(".enrollForm").submit();
});

$(".noticeListTbl #deleteMain").on('click', function(){
	var nno = $(this).siblings().last().val();
	if(!confirm(nno + "번 공지사항을 메인화면에서 삭제하시겠습니까?")) {
		return;
	}
	$(".frm [name=n_no]").val(nno);
	$(".frm").attr('action', deleteMainUrl);
	$(".frm").submit();
});