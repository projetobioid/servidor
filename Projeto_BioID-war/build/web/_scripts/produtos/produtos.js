function passouMouse(campo){
   $(campo).css("background", "radial-gradient(#ff3333, rgba(0, 0, 0, 0.9))");
   $(campo).css("cursor", "all-scroll");
   $(campo +" .fa").css("color", "#fff");
   $(campo +" .fa").css("text-shadow", "-0.5px -1px #000");

}

function saiuMouse(campo){
   $(campo +" .fa").css("text-shadow", "0.5px 1px #000");
   
    if(campo === "#excluir"){
        $(campo +" .fa").css("color", "#ff4040");
        $(campo).css("background", "radial-gradient(#fff, rgba(221, 81, 116, 0.9))");
    }else if(campo === "#novo"){
       $(campo +" .fa").css("color", "#C2FFC1");
       $(campo).css("background", "radial-gradient(#fff, rgba(23, 119, 105, 0.9))");
    }else if(campo === "#editar"){
       $(campo +" .fa").css("color", "#9ee4ff");
       $(campo).css("background", "radial-gradient(#fff, rgba(74, 74, 192, 0.9))");
    }else{
       $(campo +" .fa").css("color", "#fbfcca");
       $(campo).css("background", "radial-gradient(#fff, rgba(227, 208, 0, 0.9))");
   }
}


//mouse em cima do produto
$("#listar").mouseenter(function(){
    passouMouse("#listar");
}).mouseleave(function(){
    saiuMouse("#listar");
});

//mouse em cima do agricultores
$("#novo").mouseenter(function(){
    passouMouse("#novo");
}).mouseleave(function(){
    saiuMouse("#novo");
});

//mouse em cima do relatorios
$("#editar").mouseenter(function(){
    passouMouse("#editar");
}).mouseleave(function(){
    saiuMouse("#editar");
});

//mouse em cima do relatorios
$("#excluir").mouseenter(function(){
    passouMouse("#excluir");
}).mouseleave(function(){
    saiuMouse("#excluir");
});