$(document).ready(function(){
	$('#addNew').on('click', function(event){
		
		event.preventDefault();
		var url = BASE_URL + "admin/blog/add";
		
		ajaxRedirect(url);
	});	
});




