$(document).ready(function () {

    $('.plus-collapse .btn').click(function () {
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
        let panel_group = $(this).parents('.panel-group');
        let courseTitleId = $(panel_group).find('.courseTitleClipId').val();
        let url = BASE_URL + `courses/remove/${courseTitleId}/courseTitleOfClip`;
        let confirmer = confirm("Bạn chắc chắn muốn xóa ?");
        if(confirmer) {
            $.ajax({
                url : url,
                type : "POST",
                contentType : "application/json",
                data : null,
                dataType : "json",
                success : function (res) {
                    if(res.result == 'true') {
                        $(panel_group).remove();
                    }
                }
            });
        }
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
                    $(panel_group).find('.btn-delete-collapse').before(`<button class="btn btn-sm btn-default btn-edit-title">Sửa</button>`);
                    $(panel_group).find('.btn-add-title').remove();
                }
            }
        });
    });

    $(document).delegate('.btn-edit-title', 'click', function (event) {
        event.preventDefault();
        let panel_group = $(this).parents('.panel-group');
        let idTitle = $(panel_group).find('.courseTitleClipId').val();
        let txtTitle = $(this).parents('.form-inline').find('.title').val();
        $(panel_group).find('.panel-title a').html(txtTitle);
        let json = {
            id: idTitle,
            title: txtTitle
        }
        // console.log(json);
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
        let input_clip = $(form_inline).find('.source').val();
        $(form_inline).find('.text').html(input_txt);
        $(form_inline).find('.form-group').hide();
        $(this).hide();
        // let btn_edit_show = $(form_inline).find('.btn-edit-clip')[1];
        // $(btn_edit_show).show();
        $(form_inline).find('.btn-delete-row-clip').hide();
        let courseTitleClipId = $(this).parents('.panel-group').find('.courseTitleClipId').val();
        let json = {
            title: input_txt,
            sourceLink: input_clip,
            coursesTitleOfClipId: courseTitleClipId
        };
        let checkCourseClipId = $(form_inline).find('.checkAddUpdate').val();
        if (checkCourseClipId == "") {
            //add course clip
            console.log(checkCourseClipId);
            let btn_edit_show = $(form_inline).find('.btn-edit-clip')[0];
            $(btn_edit_show).show();
            let url = BASE_URL + 'courses/add/courseClip';
            $.ajax({
                url: url,
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(json),
                dataType: "json",
                success: function (res) {
                    if (res.result == 'true') {
                        $(form_inline).find('.checkAddUpdate').val(res.id);
                    }
                }
            });
        } else {
            console.log(checkCourseClipId);
            //edit course clip
            let btn_edit_show = $(form_inline).find('.btn-edit-clip')[0];
            $(btn_edit_show).show();
            let url = BASE_URL + `courses/edit/${checkCourseClipId}/courseClip`;
            $.ajax({
                url: url,
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(json),
                dataType: "json",
                success: function (res) {
                    console.log(res);
                }
            });
        }
    });

    $(document).delegate('.btn-edit-clip', 'click', function (event) {
        event.preventDefault();
        let form_inline = $(this).parent();
        let text = $(form_inline).find('.text').text();
        $(form_inline).find('.form-group').show();
        $(form_inline).find('.title').val(text);
        $(form_inline).find('.title').show();
        $(form_inline).find('.source').show();
        $(this).hide();
        $(form_inline).find('.btn-add-clip').show();
        $(form_inline).find('.btn-delete-row-clip').show();
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
        let courseClipId = $(form_inline).find('.checkAddUpdate').val();
        if(courseClipId != "") {
            let url = BASE_URL + `courses/remove/${courseClipId}/courseClip`;
            $.ajax({
                url: url,
                type: "POST",
                contentType: "application/json",
                data: null,
                dataType: "json",
                success: function (res) {
                    console.log(res);
                }
            });
        }
    });



})

