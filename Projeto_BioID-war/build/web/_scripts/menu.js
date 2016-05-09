
    $(".menu").mouseover(function(){
        $(".menu").css("width", 230);
        $(".menu").css("margin-left", -115);
        $(".menu").css("background", "#222");
        $("#home").css("font-size", 35);
    }).mouseleave(function(){
        $(".menu").css("width", 200);
        $(".menu").css("margin-left", -100);
        $("#home").css("font-size", 23);
        $(".menu").css("background", "#fff");
    });
