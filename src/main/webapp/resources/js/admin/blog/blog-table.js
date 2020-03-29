$(document).ready(function(){
	
	//===Pagination process===
	
	$('#pagination').twbsPagination({
		totalPages : 35,
		visiblePages : 10,
		onPageClick : function(event, page) {
			console.info(page + ' (from options)');
			//Init data
			var data = {};
			data['page'] = page;
		}
	})
	
	//click edit
	$('.j-btn-edit').on("click", function(event) {
		editEntry(this, event);
	});
	
	//click delete
	$('.j-btn-delete').on("click", function(event){
		deleteEntry(this, event); 
		
	});
	
	//click view
	$('.j-btn-detail').on("click", function(event){
		viewEntry(this, event); 
		
	});
	
	$('#myTable').DataTable({
		 columnDefs: [
			    {
			        targets: -1,
			        className: 'dt-body-right'
			    }
		],
		
	});

});

function editEntry(element, event){
	event.preventDefault();
	
	
	var row = $(element).parents('tr');
	var id = row.data('entry-id');
	var url = BASE_URL + "admin/blog/edit?id=" + id;
	
	ajaxRedirect(url);
}

function deleteEntry(element, event){
	event.preventDefault();

	// Prepare data
	var row = $(element).parents('tr');
	var id = row.data('entry-id');
	var url = BASE_URL + "admin/blog/delete";
	var data = {};
	data["id"] = id;
	
	popupConfirm("Bạn có thật sự muốn xóa không", function(result){
		if(result){
			ajaxSubmit(url, data);
		}
	});
}

function viewEntry(element, event){
	event.preventDefault();
	
	
	var row = $(element).parents('tr');
	var id = row.data('entry-id');
	var url = BASE_URL + "admin/blog/view?id=" + id;
	
	ajaxRedirect(url);
}




