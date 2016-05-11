
    $(".menu").mouseover(function(){
        $(".menu").css("width", 230);
        $(".menu").css("margin-left", -115);
        $(".menu").css("background", "#222");
        $("#home").css("font-size", 35);
    }).mouseleave(function(){
        $(".menu").css("width", 220);
        $(".menu").css("margin-left", -110);
        $("#home").css("font-size", 30);
        $(".menu").css("background", "#222");
    });
