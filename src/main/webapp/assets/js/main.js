$(document).ready(function () {
    $(".btn-title").on("click", function () {
        for (var i = 0; i < $(".btn-title").length; ++i) {
            if ($(this)[0].classList[2] === $(".dropdown-content")[i].classList[1]) {
                $(".dropdown-content")[i].show(200);
            } else {
                $(".dropdown-content")[i].hide(200);
            }
        }
    });
});