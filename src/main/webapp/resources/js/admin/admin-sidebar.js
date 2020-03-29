$(document).ready(function(){
	$('.menu .list li').click(function(event){
		
		event.preventDefault();
		$('.menu .list .active').each(function(){
			$(this).removeClass('active');
		})
		$(this).addClass('active');
		
		var url = $(this).find('a').attr('href');
		//var url = BASE_URL + "admin/blog/list";
		ajaxRedirect(url);
	});
});

