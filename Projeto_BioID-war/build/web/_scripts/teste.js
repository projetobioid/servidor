/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */






function suporte() {
  
    alert("Entre em contato: suporte@fundetec.org.br");
    
}

function verificacao(){
    //teste campos usuario e senha
    if(document.getElementById("tUsuario").value !== "" && document.getElementById("tSenha").value !== ""){
        
        
        
        ///
        if(document.getElementById("tUsuario").value == "1" && document.getElementById("tSenha").value == "1"){
            window.location = "home.html";
        }else{
            alert("Usuário ou senha incorreta!");  
        }
        
        
        /////
    
    }else if(document.getElementById("tUsuario").value === "" && document.getElementById("tSenha").value === ""){
        alert("Os campos usuário e senha não pode ser vazios!");
    //testa usuario vazio
    }else if(document.getElementById("tUsuario").value === ""){
        alert("O campo usuário não pode ser vazio!");
    //campo senha vazio
    }else{
        alert("O campo senha não pode ser vazio!");
    }
}
    


//funcao rastrear tecla enter
function rastrearEnter(e) {
   if((e && e.keyCode === 13) || e === 0) {
     verificacao();
   }
}


function teste(){
    
    $("p.nha").hide();
}

function inicio(){
    
    alert("nhaa");
}

function cor(){
    $("p.corV").css("color","red");

}

function bot(){
    //cria um thead
$(document).ready(function(){
    $("button").click(function(){
        $("p").hide();
    });
});

}

//////////////////////////////////////////////////////////////////////////////////
/////////////////////
////////////////////
////////////////////
////////////////////
//////////////
//
//



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
    //chama a funcao que redimenciona o background 
    redimencionaBackground(largura, altura);
    //chama a funcao que carrega o tamanho da imagem
    redimencionaLogo(largura, altura);    
    //chama a funcao de redimenciona o header
    
}

//redimenciona o background de acordo com o tamanho da janela
 function redimencionaBackground(largura, altura){
     $("div#interface").attr("width", largura);
     $("div#interface").attr("height", altura);
 }

//carega a imagem do tamanho especifico
function redimencionaLogo(largura, altura){
    
    //testes para o carregamento da imagem de acordo com o tamanha da janela
    //tamanho smartPhones
    if(altura < 250){
        
        if(altura > 90){
            $("img.logo").attr("src", "_imagens/LogoMarca/logo-pequena.png");
            $("img.logo").attr("width",altura/2);
            $("img.logo").attr("height",altura/2);
        }else{
            $("img.logo").attr("src", "_imagens/LogoMarca/logo-pequena.png");
            $("img.logo").attr("width",45);
            $("img.logo").attr("height",45);
        }
    //tamanho pcs
    }else if(altura > 250 && altura < 650){
        $("img.logo").attr("src", "_imagens/LogoMarca/logo-media.png");
        $("img.logo").attr("width",altura/2);
        $("img.logo").attr("height",altura/2);
        
    //tamanho telas grandes
    }else if( altura > 650){
        $("img.logo").attr("src", "_imagens/LogoMarca/logo-grande.png");
        if(altura < 800){
            $("img.logo").attr("width",altura/2);
            $("img.logo").attr("height",altura/2);
        }else{
            $("img.logo").attr("width","400");
            $("img.logo").attr("height","400");
        }
    }
      
    document.querySelector('.tamanhoJanela').innerHTML = largura + 'x' + altura;
    
    
}

function redimencionaHeader( altura){


    $("header#cabecalho").css("width", altura);
    $("header#cabecalho").css("height",altura );
}

