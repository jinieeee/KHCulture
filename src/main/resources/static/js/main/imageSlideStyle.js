$(document).ready(function(){
	$(".slider").bxSlider({
		auto: true,
		speed: 800,
		pause: 5000,
		mode: 'horizontal',
		autoControls: true,
		pager: true,
		touchEnabled : false
	});
	
	$("div.bx-wrapper").css({'width':'1320px', 'border' :'none', 'margin-top': '100px'});
	$(".bx-wrapper .bx-controls-direction a").css({'top' : '90%'});
	$(".bx-wrapper .bx-prev").css({'left' : '1100px'});
	$(".bx-wrapper .bx-next").css({'right' : '100px'});
	$(".bx-wrapper .bx-controls.bx-has-controls-auto.bx-has-pager .bx-controls-auto").css({'width': '50px', 'bottom' : '64px', 'right' : 'none', 'left' : '5%'});
	$(".bx-wrapper .bx-controls.bx-has-controls-auto.bx-has-pager .bx-pager").css({'width': 'auto', 'bottom' : '64px', 'left' : '10%'});
});
