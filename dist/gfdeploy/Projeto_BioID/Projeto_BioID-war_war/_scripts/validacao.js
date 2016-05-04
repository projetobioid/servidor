//funcao para o suporte
function suporte(){
    alert("suporte@funtedec.org.br");
}


//function validacaoLogin() {
//    
//    var data = "usuario="+$("#usuario").val()+"&senha="+$("#senha").val();
//    $.ajax({
//      type: "post",
//      url: 'http://localhost:8080/Projeto_BioID-war/servicos/usuarios/login',
//      data: data,
//      dataType: "json",
//      success: function(D){
//        if(D.sucesso){
//            alert("SUCESSSSSU!");
//        }
//      }
//    });
//    
//
//}
//
//$("#botaoLogin").click(function(){
//    validacaoLogin();
//});
//$("input").keypress(function(e){
//    if(e.which == 13){
//        validacaoLogin();
//    }
//});

function validacaoLogin(){
    
    var data = "usuario="+$("#usuario").val()+"&senha="+$("#senha").val();
   
    $.post("http://localhost:8080/Projeto_BioID-war/servicos/usuarios/login", data, function(dados){
        //teste da requisicao no banco esta correta
        if(dados.sucesso){
            //abrir home
            $("html").animate({ opacity: 0, backgroundColor: '#000' }, function() {
                window.location = "home.html";
            });
        //informa se o usuario ou senha esta incorreta
        }else{
            alert("Usuário ou senha incorreta!");
            //focus campo usuario
            $("#usuario").focus();
            //limpa o campo senha
            $("#senha").val("");
            
        }
        
    },"json");
}


$("#botaoLogin").click(function(){
    validacaoLogin();
});

$("input").keypress(function(e){
    if(e.which === 13){
        validacaoLogin();
    }
});


