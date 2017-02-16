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
//    window.location.href = 'home.html';
//    $("#page_inicial").fadeOut(100, function(evt){
//
//        $("#page_entrar").fadeIn(100);
//        $("#inputUsuario").focus();
//    });

    return false;
});
$(document).on("click", "#test", function(evt)
{
// deleteAllCookies();
alert(document.cookie);

    return false;
});



function deleteAllCookies() {
    var cookies = document.cookie.split(";");

    for (var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i];
        var eqPos = cookie.indexOf("=");
        var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
        document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
    }
}


//entar
//$(document).on("submit", "#formLogin", function(evt)
//{
//
//    if(!evt.isDefaultPrevented()){
//        enviarRequisicao();
//    }
//
//    return false;
//});



//
//
//$(document).on("click", "#esqueceuSenha", function(evt)
//{
//    
//    alert("Em construção!");
//
//    return false;
//});
//
//function enviarRequisicao(){
//
//
//    $.ajax({
//        type: 'POST',
//        url: "j_security_check",
//        data: {     j_username: $("#inputUsuario").val(),
//                    j_password:$("#inputSenha").val()
//              },
////        headers: { 
////        'Accept': 'application/json',
////        'Content-Type': 'application/json'
////        },
//        success: function(a, b, xhr){
////            alert(JSON.stringify(xhr));
//            window.location.href = 'home.html';
//            
//        },
//        error: function() {
//    
//        }
//    });

//}