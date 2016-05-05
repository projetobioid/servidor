//funcao para o suporte
function suporte(){
    //alert("suporte@funtedec.org.br");
    $("#msgSuporte").show();
}


function validacaoLogin(){
    
    var data = "usuario="+$("#usuario").val()+"&senha="+$("#senha").val();
   
    $.post("http://localhost:8080/Projeto_BioID-war/servicos/usuarios/login", data, function(dados){
        //teste da requisicao no banco esta correta
        if(dados.sucesso){
            $("#msgLogado").show();
            //abrir home
            $("html").fadeOut("show",function(){
                window.location = "home.html";
            });
            
        //informa se o usuario ou senha esta incorreta
        }else{
            $("#erroLogin").show();
            //focus campo usuario
            $("#usuario").focus();
            //limpa o campo senha
            $("#senha").val("");
            
        }
        
    },"json");
}

$("#formulario").submit(function() {
  validacaoLogin();
  return false;
});

$("input").keypress(function(e){
    if(e.which === 13 && $("#usuario").val()!== "" && $("#senha").val()!== ""){
        validacaoLogin();
    }
    $(".alert").hide();
});

$("input").click(function(){
    $(".alert").hide();
});

$(".close").click(function () {
    $(".alert").hide();
});
    
