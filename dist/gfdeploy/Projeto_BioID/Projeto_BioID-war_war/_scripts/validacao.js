//funcao para o suporte
function suporte(){
    alert("suporte@funtedec.org.br");
}

function validacaoLogins(formulario){
    alert(formulario.elements["usuario"].value + "+" + formulario.elements["senha"].value);

    $.getJSON('http://localhost:8080/Projeto_BioID-war/servicos/usuarios/listar', function(resultado){
        alert(resultado.senha);
    });

    return "nha";
}

function validacaoLogin(formulario) {
    
    name=formulario.elements["usuario"].value;
    password=formulario.elements["senha"].value;
    alert(name +"+" + password);
    var userData = {
        name: name,
        password: password
    };

    $.ajax({
        url: "http://localhost:8080/Projeto_BioID-war/servicos/usuarios/login",
        type: "POST",
        dataType: "json",
        data: userData,
        error: function (xhr, status) {
            alert("Sorry, there was a problem!");
            
        },
        complete: function (xhr, status) {
            alert(xhr);
        }
    });
}