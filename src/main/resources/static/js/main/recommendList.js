/* ajax 추천 강좌 목록 호출 */
$(function(){
	$.ajax({
		url: "/main/recommendList",
		success: function(data) {
			// console.log(data);
			var html = '';
			var recommendClassArea = $(".recommendClassArea");
			var dataSize = 0;
			for(var key in data.recommendList) {
				var lecture = data.recommendList[key];
				console.log(lecture);
				html += `<li><div class="proItem"><div class="thum"><img src="/images/lecture/` + lecture.lecture.lthumbnail + `" alt=""></div>`
					  + `<div class="info"><div class="tagArea">`;
				if(lecture.lecture.ltNo == 1) {
					html += `<span class="targetTag">성인강좌</span>`;
				} else if(lecture.lecture.ltNo == 2) {
					html += `<span class="targetTag">아동강좌</span>`;
				} else {
					html += `<span class="targetTag">영유아강좌</span>`;			
				}
				
				if(lecture.lecture.lcNo == 1) {
					html += `<span class="categoryTag">정기강좌</span>`;
				} else if(lecture.lecture.lcNo == 2) {
					html += `<span class="categoryTag">단기강좌</span>`;
				} else {
					html += `<span class="categoryTag">무료강좌</span>`;
				}
				
				html += `</div><div class="txtBox"><span class="subject">[ ` + lecture.lrStartDate.substr(5,2) + `/` + lecture.lrStartDate.substr(8, 2) + ` ] ` + lecture.lecture.ltitle + `</span>`
					  + `<div class="info"><div class="subTxt"><span class="name">` + lecture.instructor.instructorName + `</span>`
					  + `<span class="time">(` + lecture.lrDay.substr(0,1) + `) ` + lecture.lrStartTime + ` ~ ` + lecture.lrEndTime + `</span></div>`
					  + `<div class="price"><span class="blind">판매가</span><b>` + lecture.lrFee + `</b>원<span> ( 정원 ` + lecture.lrCount + `명 )</span></div></div></div></div></div></li>`
				recommendClassArea.html(html);
				dataSize++;
			}
			addEvent();
			makeToggle(dataSize);
		},
		error: function(e) {
			console.log(e);
		}
	});
});

/* li 이벤트 추가 */
function addEvent(){
	$(".recommendClassArea li").on('mouseenter', function(){
		if($(this).children($(".proItem")).css("top") == "0px") {
			$(this).children($(".proItem")).stop().animate({top: "-60"}, 200);
		}
	});
	
	$(".recommendClassArea li").on('mouseleave', function(){
		if($(this).children($(".proItem")).css("top") == "-60px") {
			$(this).children($(".proItem")).stop().animate({top: "0"}, 200);
		}
	});
	
	$(".recommendClassArea").on('mouseleave', function(){
		$(".proItem").each(function(){
			if($(this).css("top") == "-60px"){
				$(this).stop().animate({top: "0"}, 200);
			}
		});
	});
}


/* toggle 생성 */
function makeToggle(dataSize){
	var html = '';
	var toggleBox = $(".toggle-box");
	for(var count = 0; count < Math.ceil(dataSize/4); count++){
		html += `<img class="circle circle` + (count + 1) + `">`
	}
	toggleBox.html(html);
}

/* 목록 좌우 이동버튼 */
document.querySelector(".rightDiv").addEventListener('click', function(){
	var currentPoint = $(".recommendClassArea").children("li").css("left").replace("px", "") * 1;
	var currentLi = $(".recommendClassArea").children("li").length;
	if(currentPoint > -990 && currentLi > 4) {
		var movePoint = currentPoint - 990;
		$(".recommendClassArea").children("li").animate({left: movePoint}, 500);
		$(".circle1").css('background-color', 'var(--main-border-color)');
		$(".circle2").css('background-color', 'var(--main-color)');
	}
});

document.querySelector(".leftDiv").addEventListener('click', function(){
	var currentPoint = $(".recommendClassArea").children("li").css("left").replace("px", "") * 1;
	if(currentPoint < 0) {
		var movePoint = currentPoint + 990;
		$(".recommendClassArea").children("li").animate({left: movePoint}, 500);
		$(".circle1").css('background-color', 'var(--main-color)');
		$(".circle2").css('background-color', 'var(--main-border-color)');
	}
});