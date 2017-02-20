

var ipServidor = 'localhost:8080'; //sistema em producao
//var ipServidor = "187.19.101.252:8082"; //sistema rodando fora
//var ipServidor = "10.1.2.52:8080"; //sistema teste interno




$(document).on("keypress", ".camposCPF", function(evt)
{

    $(this).mask('000.000.000-00');

});

$(document).on("keypress", ".camposRG", function(evt)
{
    $(this).mask('00000.000-0');

});


$(document).on("keypress", ".camposTelefone", function(evt)
{
    $(this).mask('(00) #0000-0000');

});

$(document).on("keypress", ".camposCEP", function(evt)
{
    $(this).mask('00000-000');

});

$(document).on("keypress", ".camposCNPJ", function(evt)
{
    $(this).mask('00.000.000/0000-00');

});

$(document).on("keypress", ".camposSafra", function(evt)
{
    $(this).mask('000/0000', {reverse: true});

});


function getSessao(){
    var logSessao = JSON.parse(localStorage.getItem("logSessao"));
    return logSessao;
}

function updateSessao(novaSessao){
    var logSessao = JSON.parse(localStorage.getItem("logSessao"));
    logSessao.sessao = novaSessao;
    localStorage.setItem("logSessao", JSON.stringify(logSessao));
}

//funcao modal de uma mensagem que o usuario terá, quando fechado será chamada a funcao alertFocus
function alerta(titulo, msg){
    $("#itensModal").empty().append(msg);
    $("#modalTitulo").text(titulo);
    $('#modalApresentacao').modal('toggle');
    
}

//funcao chamada quando se fecha o modal, e se tiver armazenado no itenFocus o id do compo será focado
function alertFocus(){
    var componente = $("#itenFocus").text();
    if(componente !== "null"){
        $('html,body').animate({scrollTop: $(componente).offset()}, 400, function() {
            $(componente).focus();
        });
    $("#itenFocus").text("null");
    }
}

//function requisicao(url, envio, metodo){
function requisicao(painelCarregando, url, envio, callback) {
  
  //testa se nescessita de painel de carregando
    if(painelCarregando){
        $(".painelCarregando").fadeIn(400);
    }  
        //alert(JSON.stringify(envio));
       $.ajax({
            type: 'POST',
            url: "http://"+ipServidor+"/Projeto_BioID-war/servico/"+url,
            data: JSON.stringify(envio),
            headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json'
            },
            success: function(retorno){
                callback(retorno);
            },
            error: function() {
                if(painelCarregando){
                    $(".painelCarregando").fadeOut(400);
                }
                alerta("Alerta!", "Sem conexão com o servidor!");
            }
        });
             
 
}