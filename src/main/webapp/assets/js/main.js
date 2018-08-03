$(document).ready(function () {
    /*$(".btn-title").on("click", function () {
        for (var i = 0; i < $(".btn-title").length; ++i) {
            if ($(this)[0].classList[2] === $(".dropdown-content")[i].classList[1]) {
               $("#"+$(".dropdown-content")[i].id).toggle(200)
            } else {
                $("#"+$(".dropdown-content")[i].id).hide(200)
            }
        }
    });*/

    $("#estado").on("click", function () {
       $.ajax({
           url: "/views/estado.jsp",
           type: "GET",
           success: function (data) {
               $("#table-content").html(data).fadeIn(500)
           }
       })
    });
});