$(document).ready(function(){ 
	var urlCategory = BASE_URL + "admin/blog/ajax/getCategory";
	//Integrate CKEDITOR
	
	$("#categoryId").select2({
		placeholder: {
		    id: '-1', // the value of the option
		    text: 'Chọn loại danh mục'
		},
	    allowClear: true
	});
	
	$("#subCategoryId").select2({
		placeholder: {
		    id: '-1', // the value of the option
		    text: 'Chọn loại danh mục'
		},
		allowClear: true
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
	});
	
	$('.btnSave').on('click', function(event){
		event.preventDefault();
		
		var form = $('#formEdit').serializeArray();
		form[1].value = CKEDITOR.instances.body.getData();
		
		var formData = new FormData();
		$.each(form, function(index, item){
			formData.append(item['name'], item['value']);
		});
		
		if($('input[type=file]')[0].files[0]){
			formData.append('avatarFile', $('input[type=file]')[0].files[0]);
		}
		var urlEditEntry = BASE_URL + "admin/blog/edit";
		
		$.ajax({
			type : 'POST',
            url : urlEditEntry,
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
	
	/*$.ajax({
		type: 'GET',
		data: data,
		url: url,
		dataType: "json",
		success: function(result){
			var content = ``;
			
			$.each(result, function(index, item){
				content += `<option value="${item.categoryId}">${item.categoryName}</option>`;
			});
			//content += `<select/>`
				
			$('#subCategoryId').html(content);	
			
		},
		error: function(error){
			console.log(error);
		}
	});*/
}





