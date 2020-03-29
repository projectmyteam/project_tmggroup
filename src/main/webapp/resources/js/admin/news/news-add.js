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
	
	$("#entryId").select2({
		disabled:true,
		placeholder: {
		    id: '-1', // the value of the option
		    text: 'Chọn bài viết liên kết'
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
			//Select the option with a value of '-1'
			$('#subCategoryId').val("-1").trigger('change');
			$('#entryId').val("-1").trigger('change');
		}		
	});
	
	$('#subCategoryId').change(function(event){	
		if($('#subCategoryId').val() != null){
			//init data
			var subCategoryId = $('#subCategoryId').val();
			var data = {};
			data['subCategoryId'] = subCategoryId;
			//init url
			var url = BASE_URL + "admin/blog/ajax/getEntry";
			initEntry('#entryId', url, data, event);
			//Select the option with a value of '-1'
			$('#entryId').val("-1").trigger('change');
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
		$('#entryId').val("-1").trigger('change');
		$("#entryId").select2({
			disabled:true,
			placeholder: {
			    id: '-1', // the value of the option
			    text: 'Chọn bài viết liên kết'
			}
		});
	 });
	
	$("#subCategoryId").on("select2:unselecting", function(e) {
		$('#entryId').val("-1").trigger('change');
		$("#entryId").select2({
			disabled:true,
			placeholder: {
			    id: '-1', // the value of the option
			    text: 'Chọn bài viết liên kết'
			}
		});
	 });
	
	
	
	$('.btnSave').on('click', function(event){
		event.preventDefault();
		
		var form = $('#formAdd').serializeArray();
		//form[1].value = CKEDITOR.instances.body.getData();
		
		var formData = new FormData();			
		formData.append('avatarFile', $('input[type=file]')[0].files[0]);
		
		$.map(form, function(n, i){
			formData.append(n['name'], n['value']);
		});
		
		var urlAddNewNews = BASE_URL + "admin/news/add";
		
		 $.ajax({
			 type : 'POST',
             url : urlAddNewNews,
             data : formData,
             //Prevent jQuery modify data (encoding data)
             processData : false,
             //contentType : false <==> multipart/form-data
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

function initEntry(element, url, data, event){
	event.preventDefault();
	
	$(element).select2({
		disabled:false,
		placeholder: {
		    id: '-1', // the value of the option
		    text: 'Chọn bài viết liên kết'
		},
		allowClear: true,
		ajax: {
	    	url: url,
	    	dataType: "json",
	    	type: "GET",
	    	data: data,
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
	
}



