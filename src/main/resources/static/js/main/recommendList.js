

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

document.querySelector(".rightDiv").addEventListener('click', function(){
	var currentPoint = $(".recommendClassArea").children("li").css("left").replace("px", "") * 1;
	if(currentPoint >= -990) {
		var movePoint = currentPoint - 247.5;
		$(".recommendClassArea").children("li").animate({left: movePoint}, 300);
	} else {
		$(".recommendClassArea").children("li").animate({left: "0"}, 100);		
	}
});

document.querySelector(".leftDiv").addEventListener('click', function(){
	var currentPoint = $(".recommendClassArea").children("li").css("left").replace("px", "") * 1;
	if(currentPoint < 0) {
		var movePoint = currentPoint + 247.5;
		$(".recommendClassArea").children("li").animate({left: movePoint}, 300);
	}
});