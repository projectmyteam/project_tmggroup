$(document).ready(function(){
	$('#addNew').on('click', function(event){
		
		event.preventDefault();
		var url = BASE_URL + "admin/docs/add";
		
		ajaxRedirect(url);
	});	
});




