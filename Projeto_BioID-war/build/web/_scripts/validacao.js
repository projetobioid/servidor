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
   
    $.post("http://localhost:8080/Projeto_BioID-war/servicos/usuarios/login", data, function(data, status){
        alert("Data: " + data + "\nStatus: " + status);
    });
}

$("#botaoLogin").click(function(){
    validacaoLogin();
});




