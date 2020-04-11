
$(document).ready(function () {
    $('.entry-body').each(function () {
        var check_p_ckEditor = $(this).find('p')[0];
        var check_img = $(check_p_ckEditor).find('img');
        if(check_img.length > 0) {
            $(check_img).css({'display':'none'});
        }
        var getContentPTag = $(this).find('p');
        var setContentPTag = "";
        $(getContentPTag).each(function () {
            setContentPTag += $(this).text() + " ";
        });
        var setContentLimit = setContentPTag.substring(0, 200);
        console.log(setContentLimit.trim());
        $(this).html(setContentLimit + " ... ");
    });
})