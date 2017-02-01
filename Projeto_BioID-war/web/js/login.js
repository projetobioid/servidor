//var ipServidor = 'localhost:8080';
var ipServidor = "187.19.101.252:8082";
//var ipServidor = "10.1.2.52:8080";

/* button carregar page entrar*/
$(document).on("click", "#goPagEntrar", function(evt)
{
    $("#inputUsuario").val("");
    $("#inputSenha").val("");
    $("#formLogin").validator();
    
    $("#page_inicial").fadeOut(100, function(evt){

        $("#page_entrar").fadeIn(100);
        $("#inputUsuario").focus();
    });
    
    
    
     return false;
});

//button voltar inicio
$(document).on("click", "#voltarInicio", function(evt)
{
    
    $("#page_entrar").fadeOut(100, function(evt){

        $("#page_inicial").fadeIn(100);

    });

     return false;
});


//entar
$(document).on("submit", "#formLogin", function(evt)
{

    if(!evt.isDefaultPrevented()){
        enviarRequisicao();
    }

    return false;
});

$(document).on("click", "#fecharAlert", function(evt)
{
    $("#alerta").fadeOut(400);

    return false;
});

function enviarRequisicao(){
    var envio = {usuario: $("#inputUsuario").val(),
                    senha: $("#inputSenha").val(),
                    metodo: "validacao"
                };





    $.ajax({
        type: 'POST',
        url: "http://"+ipServidor+"/Projeto_BioID-war/servico/pessoa/validacao",
        data: JSON.stringify(envio),
        headers: { 
        'Accept': 'application/json',
        'Content-Type': 'application/json'
        },
        success: function(retorno){
            if(retorno.sucesso){
                //guarda dados do usuario no session storge
            var logSessao = JSON.stringify({
                idpessoa:  JSON.stringify(dados.idpessoa),
                sessao: dados.sessao,
               // tempoLogin: dados.tempoLogin,
                papel: dados.papel,
                idunidade: dados.idunidade,
                nome: dados.nome
            });



            localStorage.setItem("logSessao", logSessao);

            window.location.href = 'home.html';
            //$("#bemVindo").val(dados.nome);  
            }else{
                $("#alerta").empty().append('<a href="#" id="fecharAlert" class="close">&times;</a><strong>Erro!</strong> '+retorno.mensagem).fadeIn(400);
          
            }
            
            
        },
        error: function() {
//            $(".painelCarregando").fadeOut(400);
            $("#alerta").empty().append('<a href="#" id="fecharAlert" class="close">&times;</a><strong>Erro!</strong> Sem comunicação com o servidor!').fadeIn(400);
        }
    });



//    $.ajax({
//        type: 'POST',
//        url: "http://"+ipServidor+"/Projeto_BioID-war/servico/pessoa/validacao",
//        data: envio,
//        headers: { 
//        'Accept': 'application/json',
//        'Content-Type': 'application/json' 
//        }
//    }).done(function(dados) {
//        if(dados.sucesso){
//
//            //guarda dados do usuario no session storge
//            var logSessao = JSON.stringify({
//                idpessoa:  JSON.stringify(dados.idpessoa),
//                sessao: dados.sessao,
//               // tempoLogin: dados.tempoLogin,
//                papel: dados.papel,
//                idunidade: dados.idunidade,
//                nome: dados.nome
//            });
//
//
//
//            localStorage.setItem("logSessao", logSessao);
//
//            window.location.href = 'home.html';
//            //$("#bemVindo").val(dados.nome);
//
//            //informa se o usuario ou senha esta incorreta
//        }else{
//            
//            $("#alertaTitulo").text("Erro!");
//            $("#itensAlerta").empty().append('Usuário ou Senha incorretos!');
//            $("#modalAlerta").modal('toggle');
//            $("#inputUsuario").focus();
//        }
//    }).fail(function(){
//        
//        $("#alertaTitulo").text("Erro!");
//        $("#itensAlerta").empty().append("Erro de requisição!");
//        $("#modalAlerta").modal('toggle');
//    });
        
       
}