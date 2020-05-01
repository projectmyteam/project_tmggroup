$(document).ready(function(){ 
	
	$('.btnSave').on('click', function(event){
		
		event.preventDefault();
		
		var form = $('#formUserPass').serializeArray();
		
		var formData = new FormData();
		$.each(form, function(index, item){
			formData.append(item['name'], item['value']);
		});
		console.log(form);
		var urlChangeUserPass = BASE_URL + "changePassword";
		
		$.ajax({
			type : 'POST',
            url : urlChangeUserPass,
            data : formData,
            //Prevent jQuery modify data (encoding data)
            processData : false,
            //contentType : false <==> multipart/form-data <==> default application/x-www-form-urlencoded 
            contentType : false,
            cache : false,
            success : function(data) {
            	var content = $(data).find('.body-content');
    			$(".main_content").html(content);
            },
            error : function(err) {
                console.log(err);
            }
        });	
	});
});