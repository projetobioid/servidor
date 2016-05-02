//verifica se tem mudancas no tamanho da janela
window.addEventListener('resize', function(){
    //chama a funcao de verificacao do tamanho da janela
    tamanhoJanela();
});

//verifica o tamanho da janela
function tamanhoJanela(){
    //variaveis recebendo o tamanho da janela
    var largura =  window.innerWidth;
    var altura = window.innerHeight;
    

    //carrega imagem pequena
    if(largura < 801){
        $("img.logo").attr("src", "_imagens/LogoMarca/logo-pequena.png");
        $("img.logo").attr("width",100);
        $("img.logo").attr("height",100);
        //centraliza o logo
        $(".logo").css("left",((largura*40)/100)-50);
    //carrega imagem media
    }else if(largura < 1281){
        $("img.logo").attr("src", "_imagens/LogoMarca/logo-media.png");
        $("img.logo").attr("width",250);
        $("img.logo").attr("height",250);
        //centraliza o logo
        $(".logo").css("left",((largura*40)/100)-125);
        //chama a funcao de redimencionamento dos input text
        redimencionaInput(20);
    //carrega imagem grande
    }else{
        $("img.logo").attr("src", "_imagens/LogoMarca/logo-grande.png");
        $("img.logo").attr("width",400);
        $("img.logo").attr("height",400);
        //centraliza o logo
        $(".logo").css("left",((largura*40)/100)-200);
    }
         
    //chama a funcao de redimencionamento do background gradiente
    redimencionaBackground(largura, altura);
    
    alert(largura +" x " + altura);

    

}

//redimenciona o background de acordo com o tamanho da janela
 function redimencionaBackground(largura, altura){
     $("#interface").css("width", largura);
     $("#interface").css("height", altura);
 }

