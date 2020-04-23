$(document).ready(function(){ 
	
	$('.btnSave').on('click', function(event){
		event.preventDefault();
		
		var form = $('#formAdd').serializeArray();
		form[1].value = CKEDITOR.instances.body.getData();
		
		var formData = new FormData();			
		formData.append('fullFile', $('input[type=file]')[0].files[0]);
		
		$.map(form, function(n, i){
			formData.append(n['name'], n['value']);
		});
		console.log(formData);
		var urlAddNewDocs = BASE_URL + "admin/docs/add";
		
		 $.ajax({
			 type : 'POST',
             url : urlAddNewDocs,
             data : formData,
             //Prevent jQuery modify data (encoding data)
             processData : false,
             //contentType : false <==> multipart/form-data
             contentType : false,
             cache : false,
             success : function(data) {
            	var content = $(data).find('.body-content');
     			$(".main_content").html(content);
     			/*CKEDITOR.replace( 'body', {
     				filebrowserUploadUrl : BASE_URL+'admin/blog/upload_ckeditor',
     				filebrowserBrowseUrl : BASE_URL+'admin/blog/filebrowse',
     			});*/
             },
             error : function(err) {
                 console.log(err);
             }
         });
	});
});



