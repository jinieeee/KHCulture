$(document).ready(function(){
	var pagingUl = document.querySelector(".member_paging");
	let pagingHtml = `<li><a href="#" onclick="firstPage()">&lt;&lt;</a></li>
					  <li><a href="#" onclick="prevPage()">&lt;</a></li>`;
	
	for(let i = startPage; i <= endPage; i++) {
		if(i == currentPage) {
			pagingHtml += `<li><a href="#" class="current_page pagingNum">` + i + `</a></li>`;
		} else {
			pagingHtml += `<li><a onclick="goPage(` + i + `)" class="pagingNum">` + i + `</a></li>`;
		}
	}
	pagingHtml += `<li><a href="#" onclick="nextPage()">&gt;</a></li>
	   <li><a href="#" onclick="lastPage()">&gt;&gt;</a></li>`
	 							
	pagingUl.insertAdjacentHTML('beforeend', pagingHtml);
});

$(".searchForm .memberRoleSel").on('change', function(){
	console.log($(".searchForm .memberRoleSel").val());
	$(".searchForm [name=memberRole]").val($(".searchForm .memberRoleSel").val());
	$(".searchForm form").submit();
});

function firstPage(){
	if(currentPage == 1) {
		return;
	}
	$(".searchForm [name=page]").val(1);
	$(".searchForm form").submit();
}

function prevPage(){
	if(currentPage == 1) {
		return;
	}
	$(".searchForm [name=page]").val(currentPage * 1 - 1);
	$(".searchForm form").submit();
}

function nextPage(){
	if(currentPage == maxPage){
		return
	}
	$(".searchForm [name=page]").val(currentPage * 1 + 1);
	$(".searchForm form").submit();	
}

function lastPage(){
	if(currentPage == maxPage){
		return
	}
	$(".searchForm [name=page]").val(endPage);
	$(".searchForm form").submit();	
}

function goPage(i) {
	$(".searchForm [name=page]").val(i);
	$(".searchForm form").submit();	
} 