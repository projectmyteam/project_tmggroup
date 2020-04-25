$(document).ready(function(){ 
	
	//Show image before upload
	$("#avatar").change(function() {
		readURL(this);
	});
	
	$('.btnSave').on('click', function(event){
		event.preventDefault();
		
		var form = $('#formInfoUser').serializeArray();
		
		var formData = new FormData();
		$.each(form, function(index, item){
			formData.append(item['name'], item['value']);
		});
		
		if($('input[type=file]')[0].files[0]){
			formData.append('avatar', $('input[type=file]')[0].files[0]);
		}
		var urlChangeInfoUser = BASE_URL + "user";
		
		$.ajax({
			type : 'POST',
            url : urlChangeInfoUser,
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






