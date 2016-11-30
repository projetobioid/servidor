var ipServidor = "localhost:8080";
//var ipServidor = "10.1.2.52:8080";
//var ipServidor = "187.19.101.252:8082";


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

    var data = 'usuario='+$("#inputUsuario").val()+'&senha='+$("#inputSenha").val();
    $.post("http://"+ipServidor+"/Projeto_BioID-war/servico/pessoa/validacao", data, function(dados){

        if(dados.sucesso){

            //guarda dados do usuario no local storge
            var logSessao = JSON.stringify({
                idpessoa:  dados.idpessoa,
                sessao: dados.sessao,
               // tempoLogin: dados.tempoLogin,
                papel: dados.papel,
                idunidade: dados.idunidade,
                nome: dados.nome
            });
            
            
            
            localStorage.setItem("logSessao", logSessao);

            window.location.href = 'home.html';
//            $("#bemVindo").val(dados.nome);
            
            //informa se o usuario ou senha esta incorreta
            }else if($("#inputUsuario").val()=== ""){
                alert('O campo usuário não pode ser vazio!');
                $("#inputUsuario").focus();

            }else if($("#inputSenha").val()=== ""){
                alert('O campo senha não pode ser vazio!');
                $("#inputSenha").focus();
            }else{
                alert('Usuário ou Senha incorretos!');
                $("#inputUsuario").focus();
            }
        
        
    }, "json")
    //Tratamento de erro da requisicao servico RESt login
    .fail(function(){
        alert("Erro na requisição!");
    });


         return false;
    });