/*$('.cartList .select').change(function(){
    let inputCnt = $('.cartList .select').length;
    let checkedCnt = $('.cartList .select:checked').length;
    
    if(inputCnt == checkedCnt) {
        $('.cartList .selectAll').prop('checked', true);
    } else {
        $('.cartList .selectAll').prop('checked', false);
    }
})*/

$('.cartList .selectAll').change(function(){
    let select = document.querySelectorAll('.select');
    if($('.cartList .selectAll').is(":checked")) {
        // select.forEach(item => {
        //     console.log(item);
        //     $(item).prop('checked', true);
        // })
        $(select).prop('checked', true);
    } else {
        $(select).prop('checked', false);
        // select.forEach(function(item){
        //     $(item).prop('checked', false);
        // })
    }
    
    selectedClass.innerHTML = $('.cartList .select:checked').length;
    let totalPrice = 0;
	  				   
	   let checkedSelect = document.querySelectorAll('.cartList .select:checked');
	 checkedSelect.forEach( select => {
		totalPrice += Number(select.parentElement.children[1].value.replace(",", ''));
	 })
	 totalAmount.innerHTML = String(totalPrice).replace(/(.)(?=(\d{3})+$)/g,'$1,');
})
    

