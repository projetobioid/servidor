function passouMouse(campo){
   $(campo).css("background", "radial-gradient(#ff3333, rgba(0, 0, 0, 0.9))");
   $(campo).css("cursor", "all-scroll");
   $(campo +" .fa").css("color", "#fff");
   $(campo +" .fa").css("text-shadow", "-2px -3px #000");
   $(campo+"F").css("font-size", "40px");
   

}

function saiuMouse(campo){
   $(campo).css("background", "radial-gradient(#b8d2b8, rgba(0, 0, 0, 0.9))");
   //$(campo +" .fa").css("color", "#000");
   $(campo +" .fa").css("text-shadow", "2px 3px #000");
   $(campo+"F").css("font-size", "30px");
   
      if(campo === "#agricultores"){
        $(campo +" .fa").css("color", "#ff4040");
        $(campo).css("background", "radial-gradient(#b8d2b8, rgba(149, 2, 39, 0.9))");
   }else if(campo === "#produtos"){
       $(campo +" .fa").css("color", "#06ff00");
       $(campo).css("background", "radial-gradient(#b8d2b8, rgba(15, 114, 12, 0.9))");
   }else{
       $(campo +" .fa").css("color", "#6975f8");
       $(campo).css("background", "radial-gradient(#b8d2b8, rgba(0, 0, 90, 0.9))");
   }
}


//mouse em cima do produto
$("#produtos").mouseenter(function(){
    passouMouse("#produtos");
}).mouseleave(function(){
    saiuMouse("#produtos");
});

//mouse em cima do agricultores
$("#agricultores").mouseenter(function(){
    passouMouse("#agricultores");
}).mouseleave(function(){
    saiuMouse("#agricultores");
});

//mouse em cima do relatorios
$("#relatorios").mouseenter(function(){
    passouMouse("#relatorios");
}).mouseleave(function(){
    saiuMouse("#relatorios");
});