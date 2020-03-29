$(document).ready(function(){
	
	//===Pagination process===
	
	/*$('#pagination').twbsPagination({
		totalPages : 35,
		visiblePages : 10,
		onPageClick : function(event, page) {
			console.info(page + ' (from options)');
			//Init data
			var data = {};
			data['page'] = page;
			
		}
	})*/
	
	$('#myTable').DataTable({
		 columnDefs: [
			    {
			        targets: -1,
			        className: 'dt-body-left'
			    }
		],
		
	});
	
	//click edit
	$('.j-btn-edit').on("click", function(event) {
		editNews(this, event);
	});
	
	//click delete
	$('.j-btn-delete').on("click", function(event){
		deleteNews(this, event); 
		
	});
	
	//click view
	$('.j-btn-detail').on("click", function(event){
		viewNews(this, event); 	
	});

});

function editNews(element, event){
	event.preventDefault();
	
	
	var row = $(element).parents('tr');
	var id = row.data('news-id');
	var url = BASE_URL + "admin/news/edit?id=" + id;
	
	ajaxRedirect(url);
}

function deleteNews(element, event){
	event.preventDefault();

	// Prepare data
	var row = $(element).parents('tr');
	var id = row.data('news-id');
	var url = BASE_URL + "admin/news/delete";
	var data = {};
	data["id"] = id;
	
	popupConfirm("Bạn có thật sự muốn xóa không", function(result){
		if(result){
			ajaxSubmit(url, data);
		}
	});
}

function viewNews(element, event){
	event.preventDefault();
	
	
	var row = $(element).parents('tr');
	var id = row.data('news-id');
	var url = BASE_URL + "admin/news/view?id=" + id;
	
	ajaxRedirect(url);
}




