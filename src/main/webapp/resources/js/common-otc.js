$(document).ready(function(){
	
});

function ajaxRedirect(url){
	$.ajax({
		type: 'GET',
		url: url,
		success: function(data){
			var content = $(data).find('.body-content');
			$(".main_content").html(content);
			window.history.pushState('', '', url);
			if(url.indexOf("news") >= 0){
				console.log("do nothing")
			}else{
				//Integrate again CKEDITOR
				CKEDITOR.replace( 'body', {
	 				filebrowserUploadUrl : BASE_URL+'admin/blog/upload_ckeditor',
	 				filebrowserBrowseUrl : BASE_URL+'admin/blog/filebrowse',
	 			});
			}
			
		},
		error: function(error){
			console.log(error);
		}
	});
}

function ajaxSubmit(url, data, flagScrollTop){
	if(flagScrollTop === undefined){
		flagScrollTop = true;
	}
	
	$.ajax({
		type : "POST",
		url: url,
		data: data,
		success: function(data){
			var content = $(data).find('.body-content');
			$(".main_content").html(content);
			//Integrate again CKEDITOR
			/*CKEDITOR.replace( 'body', {
 				filebrowserUploadUrl : BASE_URL+'admin/blog/upload_ckeditor',
 				filebrowserBrowseUrl : BASE_URL+'admin/blog/filebrowse',
 			});*/
			
			if( flagScrollTop ) {
				goTopPage();
			}
		},
		error: function(request, error){
			var resp = request.responseText.split('||');
			var newsName = resp[0];
			var entryId = resp[1];
			popupConfirm('Bài viết hiện đang được liên kết với bản tin ' + '"'+ newsName + '"' + '. Bạn có thật sự muốn xóa cả ' +
					'bài viết và bản tin?' , function(result){
				if(result){
					//init data
					var data = {};
					data["id"] = entryId;
					data["forceDel"] = true;
					var url = BASE_URL + "admin/blog/delete";
					ajaxSubmit(url, data);
//					console.log("alright");
				}
			});
		}
	});
}

function ajaxSearch(url ,condition, element, event){
	event.preventDefault();
	
	var me = $(element);
	if(me.data('requestRunning')){
		return;
	}
	
	me.data('requestRunning', true);
	
	$.ajax({
		type: "POST",
		url: BASE_URL +  url,
		data: condition,
		success: function(data){
			var content = $(data).find('.body-content');
			$(".main_content").html(content);
		},
		complete: function(result){
			me.data('requestRunning', false);
		},
		error: function(error){
			console.log(error);
		}
	});
	
}

//Show file before upload
function readURL(input){
	if(input.files && input.files[0]){
		var reader = new FileReader();
		
		//call onload of reader to handle load event
		reader.onload = function(e){
			//e.target return which DOM element triggerd the event
			$('#avartaImage').css('display', 'inline');
			$('#avartaImage').attr('src', e.target.result);		
		}
		
		//read the contents of File ->create 'result' attribute contains URL of file (url path on machine)
		reader.readAsDataURL(input.files[0]);
	}
}


function goTopPage() {
	$("html, body").animate({ scrollTop: 0 }, "1");
}

function popupConfirm(msgConfirm, methodCallback){
	return bootbox.confirm( msgConfirm, methodCallback );
}