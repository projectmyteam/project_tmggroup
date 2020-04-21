$(document).ready(function(){ 
	
	//Show image before upload
	$("#fullFile").change(function() {
		$('#fileIframe').attr('style', 'display:none');
	});
	
	$('.btnSave').on('click', function(event){
		event.preventDefault();
		
		var form = $('#formEdit').serializeArray();
		form[1].value = CKEDITOR.instances.body.getData();
		console.log(form);
		
		var formData = new FormData();
		$.each(form, function(index, item){
			formData.append(item['name'], item['value']);
		});
		
		if($('input[type=file]')[0].files[0]){
			formData.append('fullFile', $('input[type=file]')[0].files[0]);
		}
		var urlEditDoc = BASE_URL + "admin/docs/edit";
		
		$.ajax({
			type : 'POST',
            url : urlEditDoc,
            data : formData,
            //Prevent jQuery modify data (encoding data)
            processData : false,
            //contentType : false <==> multipart/form-data <==> default application/x-www-form-urlencoded 
            contentType : false,
            cache : false,
            success : function(data) {
           	var content = $(data).find('.body-content');
    			$(".main_content").html(content);
    			//Integrate again CKEDITOR after ajax call
    			CKEDITOR.replace( 'body', {
    				filebrowserUploadUrl : BASE_URL+'admin/blog/upload_ckeditor',
    				filebrowserBrowseUrl : BASE_URL+'admin/blog/filebrowse',
    			});
            },
            error : function(err) {
                console.log(err);
            }
        });	
	});
});






