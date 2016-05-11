function passouMouse(campo){
   $(campo).css("background", "radial-gradient(#ff3333, rgba(0, 0, 0, 0.9))");
   $(campo).css("cursor", "all-scroll");
   $(campo +" .fa").css("color", "#fff");
   $(campo +" .fa").css("text-shadow", "-0.5px -1px #000");
    //sombra letras
    $(campo +"F").css("text-shadow", "0px -2px #C3CFC4");
    $(campo +"L").css("box-shadow", "0px -2px #C3CFC4");

}

function saiuMouse(campo){
    $(campo +" .fa").css("text-shadow", "0.5px 1px #000");
    //muda a sombra das letras em baixo do circulo
    $(campo +"F").css("text-shadow", "0px 2px #C3CFC4");
    $(campo +"L").css("box-shadow", "0px 2px #C3CFC4");
    if(campo === "#sementes"){
        $(campo +" .fa").css("color", "#ff4040");
        $(campo).css("background", "radial-gradient(#fff, rgba(221, 81, 116, 0.9))");
    }else if(campo === "#mudas"){
       $(campo +" .fa").css("color", "#C2FFC1");
       $(campo).css("background", "radial-gradient(#fff, rgba(23, 119, 105, 0.9))");
    }else if(campo === "#ramas"){
       $(campo +" .fa").css("color", "#9ee4ff");
       $(campo).css("background", "radial-gradient(#fff, rgba(74, 74, 192, 0.9))");
    }else{
       $(campo +" .fa").css("color", "#fbfcca");
       $(campo).css("background", "radial-gradient(#fff, rgba(227, 208, 0, 0.9))");
   }
}


//mouse em cima do produto
$("#todos").mouseenter(function(){
    passouMouse("#todos");
}).mouseleave(function(){
    saiuMouse("#todos");
});

//mouse em cima do agricultores
$("#mudas").mouseenter(function(){
    passouMouse("#mudas");
}).mouseleave(function(){
    saiuMouse("#mudas");
});

//mouse em cima do relatorios
$("#ramas").mouseenter(function(){
    passouMouse("#ramas");
}).mouseleave(function(){
    saiuMouse("#ramas");
});

//mouse em cima do relatorios
$("#sementes").mouseenter(function(){
    passouMouse("#sementes");
}).mouseleave(function(){
    saiuMouse("#sementes");
});