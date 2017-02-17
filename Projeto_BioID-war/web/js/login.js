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

$(document).on("click", "#logars", function(evt)
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



$(document).on("click", "#aqui2", function(evt)
{
    
    
    $.when(

	    $.get( 'http://localhost:8080/Projeto_BioID-war/servico/outros/teste'), 

	    $.post( 'http://localhost:8080/Projeto_BioID-war/j_security_check', $('.login').serialize())

	).then(function(a) {  // or ".done"
            localStorage.setItem('validacao', a);
            location.href = 'paginas/home.html';
	});
    
 
    return false;
});


$(document).on("click", "#aqui", function(evt)
{
    //Send data to the other script
    $.post( 'http://localhost:8080/Projeto_BioID-war/j_security_check', $('.login').serialize(), function(data, textStatus, x) {
        //data is the result from the script
//        alert(data);
    $('html').html(data);
    });

    return false;
});