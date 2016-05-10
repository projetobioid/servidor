$(".circulo").mouseenter(function(){
   $(".circulo").css("background", "radial-gradient(#ff3333, rgba(0, 36, 4, 0.8))");
   $(".circulo .fa").css("color", "#fff");
   $(".circulo .fa").css("text-shadow", "2px 2px #000");
}).mouseleave(function(){
   $(".circulo").css("background", "radial-gradient(#b8d2b8, rgba(0, 36, 4, 0.8))");
   $(".circulo .fa").css("color", "#000");
   $(".circulo .fa").css("text-shadow", "2px 2px #C3CFC4");
});