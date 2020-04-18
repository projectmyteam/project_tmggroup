$(document).ready(function(){ 
	var urlCategory = BASE_URL + "admin/blog/ajax/getCategory";

	$("#categoryId").select2({
		placeholder: {
		    id: '-1', // the value of the option
		    text: 'Chọn loại danh mục'
		},
	    allowClear: true,
	    ajax: {
	    	url: urlCategory,
	    	dataType: "json",
	    	type: "GET",
	    	processResults: function(data){
	    		return {
	    			results: $.map(data, function(item, index){
	    				return {
	    					id: item.id,
	    					text: item.text
	    				}
	    			})
	    		}
	    	}
	    }
	});
	
	$("#subCategoryId").select2({
		disabled:true,
		placeholder: {
		    id: '-1', // the value of the option
		    text: 'Chọn loại danh mục'
		}
	});
	
	$('#categoryId').change(function(event){	
		if($('#categoryId').val() != null){
			//init data
			var categoryId = $('#categoryId').val();
			var data = {};
			data['categoryId'] = categoryId;
			//init url
			var url = BASE_URL + "admin/blog/ajax/getSubCategory";
			initSubCategory('#subCategoryId', url, data, event);
			/*Select the option with a value of '-1'*/
			$('#subCategoryId').val("-1").trigger('change');
		}	
		//clear validate message
		clearValidateMessage(this, '#categoryId-error');
		//$(this).parent('form').find('#categoryId-error').remove();
	});
	
	$('#subCategoryId').change(function(event){	
		//clear validate message
		clearValidateMessage(this, '#subCategoryId-error');
	});
	
	//Click allow clear
	$("#categoryId").on("select2:unselecting", function(e) {
		$('#subCategoryId').val("-1").trigger('change');
		$("#subCategoryId").select2({
			disabled:true,
			placeholder: {
			    id: '-1', // the value of the option
			    text: 'Chọn loại danh mục'
			}
		});
	 });
	
	//Show image before upload
	$("#avatarFile").change(function() {
		  readURL(this);
		//clear validate message
		clearValidateMessage(this, '#avatarFile-error');
	});
	
	$('.btnSave').on('click', function(event){
		
	});
	
	validateFormAndSubmit('#formAdd', "admin/blog/add", false);
	
	/*$('#formAdd').submit(function (event){
		
	});*/
	
	/*$('#formAdd').validate({
		rules: {
			subject: "required",
			categoryId: "required",
			subCategoryId: {
				required: {
					depends: function(element){
						if($('#categoryId').val() != 6){
							return true;
						}
					}
				}
			},
			avatarFile: {
				required: true,
				extension: "jpg|jpeg|png|ico|bmp"
			}
		},
		messages: {
			subject: "* Vui lòng nhập tiêu đề",
			categoryId: "* Vui lòng chọn loại danh mục",
			subCategoryId: "* Vui lòng chọn loại danh mục",
			avatarFile: {
				required: "Vui lòng upload hình.",
				extension: "Vui lòng upload hình với chỉ những định dạng sau (jpg, jpeg, png, ico, bmp)."
			}
		},
		submitHandler: function(form, event) {
			ajaxSubmitForm(form, event);
		}
		
	});*/
	
/*	$('#avatarFile').rules("add",{
		accept: "jpg|jpeg|png|ico|bmp"
	});*/
	
});

/*function ajaxSubmitForm(validatedForm, event){
	event.preventDefault();
	
	var form = $(validatedForm).serializeArray();
	form[1].value = CKEDITOR.instances.body.getData();
	
	var formData = new FormData();
	if($('input[type=file]')[0].files[0]){
		formData.append('avatarFile', $('input[type=file]')[0].files[0]);
	}
	
	
	var fileUpload = $('input[type=file]')[0].files[0];
	
	$.each(fileUpload, function( key, value ) {
		form.push({"name": key,"value": value});
	});
	
	$.map(form, function(n, i){
		formData.append(n['name'], n['value']);
	});
	
	// Display the key/value pairs
	for(var pair of formData.entries()) {
	   console.log(pair[0]+ ', '+ pair[1]); 
	}

	var urlAddNewEntry = BASE_URL + "admin/blog/add";
	
	 $.ajax({
		 type : 'POST',
         url : urlAddNewEntry,
         data : formData,
         //Prevent jQuery modify data (encoding data)
         processData : false,
         //contentType : false <==> multipart/form-data
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
}*/


function initSubCategory(element, url, data, event){
	event.preventDefault();
	
	$(element).select2({
		disabled:false,
		placeholder: {
		    id: '-1', // the value of the option
		    text: 'Chọn loại danh mục'
		},
		allowClear: true,
		ajax: {
	    	url: url,
	    	dataType: "json",
	    	type: "GET",
	    	data: data,
	    	processResults: function(data){
	    		return {
	    			//Mỗi lần lặp sẽ trả ra 1 phần tử của array
	    			results: $.map(data, function(item, index){
	    				if(item.children){
	    					return {
	    						text: item.text,
	    						children: $.map(item.children, function(child, index){
	    							return {
	    		    					id: child.id,
	    		    					text: child.text
	    		    				}
	    						})
	    					}
	    				}else{
	    					return {
		    					id: item.id,
		    					text: item.text
		    				}
	    				}    				
	    			})
	    		}
	    	}
	    }
	});
	
	
}



