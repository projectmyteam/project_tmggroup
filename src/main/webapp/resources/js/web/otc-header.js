

buildDynamicMenu();


function buildDynamicMenu(){
	//init data
	var url = BASE_URL + "ajax/menu";
	
	$.ajax({
		type : "GET",
		url: url,
		success: function(resp){
			createMenu(resp);
		},
		error: function(request, error){
			console.log(error);
		}
	});
}

function createMenu(resp){
	var menuContent = ``;
	menuContent += `<ul>`;
	menuContent += `<li><a href="${BASE_URL}" class="text-uppercase">Trang chủ</a></li>`;
	$.each(resp, function(index, item){	
		menuContent += `<li><a href="${BASE_URL + "determineUrl/" + item.categoryId}" class="text-uppercase">${item.categoryName}</a>`;
		menuContent += createChildMenu(item.childsCategory);
		menuContent += `</li>`;
	});
	menuContent += `</ul>`;
	$('#dynamic-menu').html(menuContent);
}

function createChildMenu(childsCategory){
	if(childsCategory == null || (childsCategory != null && childsCategory.length == 0) ){
		return ``;
	}
	var childMenuContent = ``;
	childMenuContent += `<ul class="thired-level">`;
	$.each(childsCategory, function(index, item){
		if(item.childsCategory != null && item.childsCategory.length > 0){
			childMenuContent += `<li class="has-child-menu"><a href="${BASE_URL + "determineUrl/" + item.categoryId}">${item.categoryName}</a>`;
			childMenuContent += createChildMenu(item.childsCategory);
			childMenuContent += `</li>`;
		}else{
			childMenuContent += ` <li><a href="${BASE_URL + "determineUrl/" + item.categoryId}">${item.categoryName}</a></li>`;
		}
	})
	childMenuContent += `</ul>`;
	return childMenuContent;
}