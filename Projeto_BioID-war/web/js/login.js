var ipServidor = 'localhost:8080'; //sistema em producao
//var ipServidor = "187.19.101.252:8082"; //sistema rodando fora
//var ipServidor = "10.1.2.52:8080"; //sistema teste interno

/* button carregar page entrar*/
$(document).on("click", "#goPagEntrar", function(evt)
{
    $("#username").val("admin");
    $("#password").val("admin");
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



function login()
{
    
    var envio = {usuario: $("#username").val(),
                    senha: $.sha256($("#password").val()),
                    metodo: "validacao"
                };
    
    $.when(

        $.ajax({
            type: 'POST',
            url: "http://"+ipServidor+"/Projeto_BioID-war/servico/outros/validacao",
            data: JSON.stringify(envio),
            headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json'
            }
        })

//	    $.post( 'http://localhost:8080/Projeto_BioID-war/j_security_check', $('.login').serialize())

	).then(function(dadosRetorno) {  
        
            if(dadosRetorno.sucesso){
                
                localStorage.setItem('logSessao', JSON.stringify(dadosRetorno.data));

                $.ajax({
                    type: 'POST',
                    url : 'http://'+ipServidor+'/Projeto_BioID-war/j_security_check',
                    data: $('.login').serialize()
                            
                    
                }).done(function (){
                    switch(dadosRetorno.data.grupo){
                        case "administradores":
                            location.href = "paginas/administrador/administrador.html";
                            break;
                        case "gerenciadores":
                            location.href = "paginas/gerenciador/gerenciadores.html";
                            break;
                        case "entrevistadores":
                            location.href = "paginas/entrevistador/entrevistadores.html";
                            break;
                        case "agricultores":
                            location.href = "paginas/agricultor/agricultores.html";
                            break;
                        default:
                            location.href = "seguranca/erro.html";
                            break;
                    }
                });
                
                
            }else{
                $("#alerta").empty().append('<a href="#" id="fecharAlert" class="close">&times;</a><strong>Erro!</strong> Usuário ou senha incorreta!').fadeIn();
            }
	});
    
 
 }
 
//cadastro de um novo agricultor parte de dados pessoais
$(document).on("submit", "#formLogin", function(e){

        
    if(!e.isDefaultPrevented()){
        login();

    }
    return false;
});