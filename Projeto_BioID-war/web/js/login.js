var ipServidor = 'localhost:8080';
//var ipServidor = "187.19.101.252:8082";
//var ipServidor = "10.1.2.52:8080";

/* button carregar page entrar*/
$(document).on("click", "#goPagEntrar", function(evt)
{
    $("#inputUsuario").val("");
    $("#inputSenha").val("");
    
    
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
$(document).on("click", "#entrar", function(evt)
{

    
    
    if($("#inputUsuario").val()=== ""){
        alert('O campo usuário não pode ser vazio!');
        $("#inputUsuario").focus();

    }else if($("#inputSenha").val()=== ""){
        alert('O campo senha não pode ser vazio!');
        $("#inputSenha").focus();
    }else{

        var data = JSON.stringify({usuario: $("#inputUsuario").val(),
            senha: $("#inputSenha").val(),
            metodo: "validacao"
        });

        
        $.ajax({
            type: 'POST',
            url: "http://"+ipServidor+"/Projeto_BioID-war/servico/pessoa/validacao",
            data: data,
            headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
            }
        }).done(function(dados) {
            if(dados.sucesso){

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

                //informa se o usuario ou senha esta incorreta
                }else{
                alert('Usuário ou Senha incorretos!');
                   
                    $("#inputUsuario").focus();
                }
        }).fail(function(){
            alert("Erro de requisição!");
        });
        
       
    }

         return false;
    });
    
function alerta(msg){
    $("#itensModal").empty().append(msg);
    $('#modalApresentacao').modal('toggle');
}