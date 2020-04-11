

$(document).ready(function () {
    $('.icon-title').each(function (e) {
        $(this).mouseout(function () {
            $(this).next().removeClass('css-content-hover');
        });
        $(this).mouseover(function () {
            $(this).next().addClass('css-content-hover');
        });
    })
})