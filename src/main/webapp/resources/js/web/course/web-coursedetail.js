$(document).ready(function () {

    var valueColape = 0;
    $('.plus-collapse').click(function () {
        valueColape++;
        var collape = 'collape'+valueColape;
        var panel_clone = $('#clone-panel').clone();
        $('#list-clip').append(panel_clone);
        $(panel_clone).removeAttr('style');
        $(panel_clone).removeAttr('id');
        $(panel_clone).find('.panel-title a').attr('href', '#'+collape);
        $(panel_clone).find('.panel-collapse').attr('id', collape);
    });

    $(document).delegate('.btn-add-title', 'click', function (event) {
        event.preventDefault();
        var panel_group = $(this).parents('.panel-group');
        var input = $(this).prev().find('input').val();
        $(panel_group).find('.panel-title a').html(input);
        var courseId = $('#courseId').val();
        var json = {
            title: input,
            courseId: courseId,
        };
        var url = BASE_URL + 'courses/add/courseTitleOfClip';
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
    })

    $(document).delegate('.btn-add-clip', 'click', function (event) {
        event.preventDefault();
        var form_inline = $(this).parents('.form-inline');
        var input_txt = $(form_inline).find('.title').val();
        $(form_inline).find('.text').html(input_txt);
        $(form_inline).find('.form-group').hide();
        $(this).hide();
        $(this).next().show();
    });

    $(document).delegate('.btn-edit-clip', 'click', function (event) {
        event.preventDefault();
        var form_inline = $(this).parent();
        var text = $(form_inline).find('.text').text();
        $(form_inline).find('.form-group').show();
        $(form_inline).find('.title').val(text);
        $(this).hide();
        $(this).prev().show();
        $(form_inline).find('.text').text('');
    });

    $(document).delegate('.btn-row-clip', 'click', function (event) {
        event.preventDefault();
        var list_group = $(this).parents('.list-group');
        $(list_group).find('.btn-row-clip').remove();
        var li = $('#clone-item').clone();
        $(list_group).append(li);
        $(li).removeAttr('style');
        $(li).removeAttr('id');
        $(li).find('.form-inline').append('<button class="btn btn-sm btn-default btn-delete-row-clip">Xóa dòng</button>');
    });

    $(document).delegate('.btn-delete-row-clip', 'click', function (event) {
        event.preventDefault();
        var form_inline = $(this).parents('.form-inline');
        var check_next_element = $(form_inline).parent();
        var list_item = $(form_inline).parent();
        if($(check_next_element).next().is('li.list-group-item')) {
            $(list_item).remove();
        } else {
            $(list_item).prev().find('.btn-delete-row-clip').before('<button class="btn btn-sm btn-default btn-row-clip">Thêm dòng</button>');
            $(list_item).remove();
        }
    });

})

