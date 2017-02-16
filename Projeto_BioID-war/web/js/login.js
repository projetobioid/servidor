var ipServidor = 'localhost:8080'; //sistema em producao
//var ipServidor = "187.19.101.252:8082"; //sistema rodando fora
//var ipServidor = "10.1.2.52:8080"; //sistema teste interno

/* button carregar page entrar*/
$(document).on("click", "#goPagEntrar", function(evt)
{
    $("#username").val("");
    $("#password").val("");
    $("#formLogin").validator();
    
    $("#page_inicial").fadeOut(100, function(evt){

        $("#page_entrar").fadeIn(100);
        $("#username").focus();
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


$(document).on("click", "#fecharAlert", function(evt)
{
    $("#alerta").fadeOut(400);

    return false;
});

$(document).on("click", "#logar", function(evt)
{
    enviarR().done(function() { 
        simularClick();
    
    });

    

    

    return false;
    
});

function simularClick(){
    $("#logar").trigger('click');
}


function enviarR(){
    var envio = {usuario: $("#username").val(),
                    senha: $.sha256($("#password").val()),
                    metodo: "validacao"
                };
                
    $.ajax({
        type: 'POST',
        url: "http://"+ipServidor+"/Projeto_BioID-war/servico/outros/validacao",
        data: JSON.stringify(envio),
        headers: { 
        'Accept': 'application/json',
        'Content-Type': 'application/json'
        },
        success: function(retorno){
            if(retorno.sucesso){

                localStorage.setItem("logSessao", JSON.stringify(retorno.data));
               

            }else{
                alert(retorno.mensagem);
            }
            
            
        },
        error: function() {
          alert("error");
        }
    });
}