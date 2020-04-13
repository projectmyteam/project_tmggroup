
$(document).ready(function () {
    $('#submit-comment').submit(function (event) {
        event.preventDefault();
        var value_submit = $(this).serializeArray();
        var json_value = {};
        $.each(value_submit, function(i, field){
            json_value[field.name] = field.value;
        });
        var url = BASE_URL + "entrydetail/comment/add";
        $.ajax({
            url : url,
            type : "POST",
            contentType : "application/json",
            data : JSON.stringify(json_value),
            dataType : "json",
            success : function (res) {
                console.log(res);
                var html_comment = '';
                if(res.result == "true") {
                    console.log(res);
                    for (let i = 0; i < res.threeCommentNewest.length; i++) {
                        var comment = res.threeCommentNewest[i];
                        html_comment += `
                            <div class="media media-${res.id}">
                                <a href="#" class="pull-left"> <img
                                        alt="Generic placeholder image"
                                        src="img/news/news-details-comments.jpg" class="media-object"/>
                                </a>
                                <div class="media-body">
                                    <h3>
                                        <a href="#">${comment.user.userName}</a>
                                    </h3>
                                    <p>${comment.createdDate}</p>
                                    <p>${comment.comment}</p>                                   
                                </div>
                        `;
                    }
                    $('#loadding-comment').html('<i class="fa fa-circle-o-notch fa-spin fa-3x fa-fw margin-bottom" style="font-size: 20px"></i>');
                    setTimeout(function() {
                        $('#loadding-comment').html('');
                        $('#comments').html(html_comment);
                        if(res.countComment > 3) {
                            $('#more-comment').html('<strong style="cursor: pointer;">Đọc Thêm ...</strong>');
                        }
                    }, 500);
                    $('.txtComment').val('');
                }
            }
        })
    });

    $('.page-news-details-comments').on('click', '#more-comment strong', function () {
        var countCurrentComment = $('#comments .media').length;
        var entryId = $('#entryId').val();
        var url = BASE_URL + "entrydetail/comment/more?first="+countCurrentComment+"&entryId="+entryId;
        $.ajax({
            url : url,
            type : "GET",
            contentType : "application/json",
            dataType : "json",
            success : function (res) {
                console.log(res);
                var countComment = 0;
                var html_comment = '';
                if(res.length > 0) {
                    for (let i = 0; i < res.length; i++) {
                        var comment = res[i];
                        html_comment += `
                            <div class="media media-${res.id}">
                                <a href="#" class="pull-left"> <img
                                        alt="Generic placeholder image"
                                        src="img/news/news-details-comments.jpg" class="media-object"/>
                                </a>
                                <div class="media-body">
                                    <h3>
                                        <a href="#">${comment.user.userName}</a>
                                    </h3>
                                    <p>${comment.createdDate}</p>
                                    <p>${comment.comment}</p>                                   
                                </div>
                        `;
                        countComment = comment.countComment;
                    }
                    $('#loadding-comment').html('<i class="fa fa-circle-o-notch fa-spin fa-3x fa-fw margin-bottom" style="font-size: 20px"></i>');
                    setTimeout(function() {
                        $('#loadding-comment').html('');
                        $('#comments').append(html_comment);
                        var countCurrentComment = $('#comments .media').length;
                        if(countComment == countCurrentComment) {
                            $('#more-comment').html('');
                        }
                    }, 500);
                }
            }
        });
    });

})