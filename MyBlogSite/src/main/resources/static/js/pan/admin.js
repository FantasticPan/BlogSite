$(function () {

    $(".child_menu li").each(function (i, val) {
        $(val).click(function () {
            $(".child_menu li").each(function (i, val) {
                $(val).removeClass("active");
            })
            $(this).addClass("active");
            $(".right_col").each(function (i, val) {
                $(val).css("display", "none");
            })
            $(".right_col").eq(i).css("display", "block");
        });
    })


});