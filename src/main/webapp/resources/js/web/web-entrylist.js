
$(document).ready(function () {  
    $('#btnSearch').on('click', function(event){
    	onClickSearch(this, event);
    });
})

function onClickSearch(element, event){
	ajaxSearch("entrylist", setConditionSearch(), element, event);
}

function setConditionSearch(){
	var condition = {};
	condition['searchValue'] =  $('#searchValue').val();
	condition['subCategoryId'] = $('#subCategoryId').val();
	
	return condition;
}