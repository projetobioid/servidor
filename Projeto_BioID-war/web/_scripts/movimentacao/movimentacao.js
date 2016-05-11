function passouMouse(campo){
   $(campo).css("background", "radial-gradient(#ff3333, rgba(0, 0, 0, 0.9))");
   $(campo).css("cursor", "all-scroll");
   $(campo +" .fa").css("color", "#fff");
   $(campo +" .fa").css("text-shadow", "-0.5px -1px #000");
   

}

function saiuMouse(campo){
    
   $(campo +" .fa").css("text-shadow", "0.5px 1px #000");
   
    if(campo === "#safraColheita"){
       $(campo +" .fa").css("color", "#C2FFC1");
       $(campo).css("background", "radial-gradient(#fff, rgba(23, 119, 105, 0.9))");
    }else if(campo === "#destinacao"){
       $(campo +" .fa").css("color", "#9ee4ff");
       $(campo).css("background", "radial-gradient(#fff, rgba(74, 74, 192, 0.9))");
    }else{
        $(campo +" .fa").css("color", "#ff4040");
        $(campo).css("background", "radial-gradient(#fff, rgba(221, 81, 116, 0.9))");
   }
}


//mouse em cima do distribuicao
$("#distribuicao").mouseenter(function(){
    passouMouse("#distribuicao");
}).mouseleave(function(){
    saiuMouse("#distribuicao");
});

//mouse em cima do safraColheita
$("#safraColheita").mouseenter(function(){
    passouMouse("#safraColheita");
}).mouseleave(function(){
    saiuMouse("#safraColheita");
});

//mouse em cima do destinacao
$("#destinacao").mouseenter(function(){
    passouMouse("#destinacao");
}).mouseleave(function(){
    saiuMouse("#destinacao");
});
