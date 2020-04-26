$(document).ready(function () {

    $('.plus-collapse').click(function () {
        let valueColape = Math.random().toString(36).substring(7);
        let collape = 'collape'+valueColape;
        let panel_clone = $('#clone-panel').clone();
        $('.sys-courses-display').append(panel_clone);
        $(panel_clone).removeAttr('style');
        $(panel_clone).removeAttr('id');
        $(panel_clone).find('.panel-title a').attr('href', '#'+collape);
        $(panel_clone).find('.panel-collapse').attr('id', collape);
    });
    $(document).delegate('.btn-delete-collapse', 'click' , function (event) {
        event.preventDefault();
        $(this).parents('.panel-group').remove();
    });

    $(document).delegate('.btn-add-title', 'click', function (event) {
        event.preventDefault();
        let panel_group = $(this).parents('.panel-group');
        let input = $(this).prev().find('input').val();
        $(panel_group).find('.panel-title a').html(input);
        let courseId = $('#courseId').val();
        let json = {
            title: input,
            courseId: courseId,
        };
        let url = BASE_URL + 'courses/add/courseTitleOfClip';
        $.ajax({
            url : url,
            type : "POST",
            contentType : "application/json",
            data : JSON.stringify(json),
            dataType : "json",
            success : function (res) {
                if(res.result == 'true') {
                    $(panel_group).append(`<input type='hidden' value='${res.id}' class='courseTitleClipId'/>`);
                }
            }
        });
    });

    $(document).delegate('.btn-edit-title', 'click', function (event) {
        event.preventDefault();
        let panel_group = $(this).parents('.panel-group');
        let idTitle = $(this).prev().val();
        let txtTitle = $(this).parents('.form-inline').find('.title').val();
        $(panel_group).find('.panel-title a').html(txtTitle);
        let json = {
            id: idTitle,
            title: txtTitle
        }
        let url = BASE_URL + 'courses/edit/courseTitleOfClip';
        $.ajax({
            url : url,
            type : "POST",
            contentType : "application/json",
            data : JSON.stringify(json),
            dataType : "json",
            success : function (res) {
                console.log(res);
            }
        });
    });

    $(document).delegate('.btn-add-clip', 'click', function (event) {
        event.preventDefault();
        let form_inline = $(this).parents('.form-inline');
        let input_txt = $(form_inline).find('.title').val();
        $(form_inline).find('.text').html(input_txt);
        $(form_inline).find('.form-group').hide();
        $(this).hide();
        $(this).next().show();
        let courseTitleClipId = $(this).parents('.panel-group').find('.courseTitleClipId').val();
        let json = {
            title: input_txt,
            coursesTitleOfClipId: courseTitleClipId
        };
        let url = BASE_URL + 'courses/add/courseClip';
        $.ajax({
            url : url,
            type : "POST",
            contentType : "application/json",
            data : JSON.stringify(json),
            dataType : "json",
            success : function (res) {
                console.log(res);
            }
        });
    });

    $(document).delegate('.btn-edit-clip', 'click', function (event) {
        event.preventDefault();
        let form_inline = $(this).parent();
        let text = $(form_inline).find('.text').text();
        $(form_inline).find('.form-group').show();
        $(form_inline).find('.title').val(text);
        $(this).hide();
        $(this).prev().show();
        $(form_inline).find('.text').text('');
    });

    $(document).delegate('.btn-row-clip', 'click', function (event) {
        event.preventDefault();
        let list_group = $(this).parents('.list-group');
        $(list_group).find('.btn-row-clip').remove();
        let li = $('#clone-item').clone();
        $(list_group).append(li);
        $(li).removeAttr('style');
        $(li).removeAttr('id');
        $(li).find('.form-inline').append('<button class="btn btn-sm btn-default btn-delete-row-clip">Xóa dòng</button>');
    });

    $(document).delegate('.btn-delete-row-clip', 'click', function (event) {
        event.preventDefault();
        let form_inline = $(this).parents('.form-inline');
        let check_next_element = $(form_inline).parent();
        let list_item = $(form_inline).parent();
        if($(check_next_element).next().is('li.list-group-item')) {
            $(list_item).remove();
        } else {
            $(list_item).prev().find('.btn-delete-row-clip').before('<button class="btn btn-sm btn-default btn-row-clip">Thêm dòng</button>');
            $(list_item).remove();
        }
    });

})

