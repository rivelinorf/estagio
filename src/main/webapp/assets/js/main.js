var validString = function (text) {
    if (!isNaN(text) && text != ' ')
        return false;
    else
        return true;
}

$(document).ready(function () {
    $(".olho i").on("mousedown", function () {
        $(this).prev(".senha").attr("type", "text");
    });

    $(".olho i").on("mouseup", function () {
        $(this).prev(".senha").attr("type", "password");
    });
})
