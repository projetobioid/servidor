

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
            url: ipServidor+"/servico/"+url,
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

$(document).on("click", "tbody > tr", function(evt)
{

    //marcar de laranja a row
    if($(this).hasClass("warning")){
        $(this).removeClass("warning");

    }else{
        //percorer a tabela e desmarcar os itens marcados
        var tabela = $(this).parent();
        tabela.find('tr').each(function (i){
            if($(this).hasClass("warning")){
                $(this).removeClass("warning");
                return false;
            }
        });
        $(this).addClass("warning");
        
    }

     return false;
});


//busca no servidor dados do cultivar para ser apresentado em um modal
function carregaCultivar(idClicado, opcao){
//    var Sessao = getSessao();
    var envio = {
        metodo: "GET_CULTIVAR",
        idcultivar: idClicado
//        usuario: Sessao.usuario,
//        sessao: Sessao.sessao
    };
    
    //chama a requisicao do servidor, o resultado é listado em uma tabela
    requisicao(true, "cultivar/buscar", envio, function(dadosRetorno) {
        if(dadosRetorno.sucesso){
            
            //testa qual opcao sera, editar ou mostrar
            if(opcao === "modal"){
                //carrega atributos no painel modal que sera exibido
                $("#modalTitulo").text("Cultivar");
                //carrega atributos no painel modal que sera exibido
                $("#itensModal").append('<h3>'+dadosRetorno.data.nomecultivar+'</h3><img class=" img-responsive" src="'+dadosRetorno.data.imagem+'" width="180" height="180" alt="imgCultivar"/><h4> Biofortificado: '+dadosRetorno.data.biofortificado+'</h4><h4> Unidade de medida: '+dadosRetorno.data.grandeza+'</h4><h4> Tempo de colheita: '+dadosRetorno.data.tempodecolheita+'</h4><h4> Tempo de destinação: '+dadosRetorno.data.tempodestinacao+'</h4><h4> Peso da saca: '+dadosRetorno.data.peso_saca+' kilo(s)</h4><h4> Valor nutricional: '+dadosRetorno.data.valornutricional+'</h4><h4> Descrição: '+dadosRetorno.data.descricao+'</h4>');
            }else if(opcao === "editar"){
                $("#divItens").empty().append('<h2 class="sub-header">Editar cultivar</h2><form  data-toggle="validator" role="form" id="salvarEditCulti"> <div class="form-group"><label for="nomeCultivar" class="control-label">Nome cultivar:</label><input type="text" class="form-control" id="nomeCultivar" placeholder="Digite o nome do cultivar..." value="'+dadosRetorno.data.nomecultivar+'"></div><div class="form-group"><label for="umCultivar" class="control-label">Unidade de medida:</label><select class="form-control " id="umCultivar" value="'+dadosRetorno.data.grandeza+'"><option value="7">Kilo(s)</option><option value="6">Maniva(s)</option><option value="5">Rama(s)</option></select></div><div class="form-group"><label for="pesoSaca" class="control-label">Peso da saca:</label><input type="text" class="form-control" id="pesoSaca" placeholder="Digite o peso da saca..." value="'+dadosRetorno.data.peso_saca+'"></div><div class="form-group"><label for="tempoColheita" class="control-label">Dias para relatar a colheita:</label><input type="number" class="form-control" min="1" id="tempoColheita" placeholder="Digite o tempo de colheita..." value="'+dadosRetorno.data.tempodecolheita+'"></div><div class="form-group"><label for="tempoDestinacao" class="control-label">Dias para relatar a destinação:</label><input type="number" class="form-control" min="1" id="tempoDestinacao" placeholder="Digite o tempo da destinação..." value="'+dadosRetorno.data.tempodestinacao+'"></div><div class="form-group"><label for="valorNutricional" class="control-label">Valor nutricional</label><textarea class="form-control" id="valorNutricional" placeholder="Digite o valor nutricional ...">'+dadosRetorno.data.valornutricional+'</textarea></div><div class="form-group"><label for="descCultivar" class="control-label">Descrição:</label><textarea class="form-control" id="descCultivar" placeholder="Digite uma descrição...">'+dadosRetorno.data.descricao+'</textarea></div><div class="input-group"><input type="checkbox" aria-label="..." id="bio" checked="'+dadosRetorno.data.biofortificado+'">&nbsp;<label for="bio" class="control-label">Biofortificado</label></div><br><label class="control-label">Selecione uma imagem:</label><input id="imgNovoCultivar" type="file" class=""><img id="imgCultivarCarregada" class="img-responsive" src="'+dadosRetorno.data.imagem+'" width="180" height="180" alt="imgCultivar"/><hr><button type="submit" class="btn btn-warning">Salvar <span class="fa fa-save" aria-hidden="true"></span></button></form>');
                $('#salvarEditCulti').validator();
            }
            //retira o painel loading
            $(".painelCarregando").fadeOut(400);
        }else{
            //retira o painel loading
            $(".painelCarregando").fadeOut(400);
            alerta("Alerta!", dadosRetorno.mensagem);
        }
        //atualiza a sessao
        updateSessao(dadosRetorno.sessao);
    });
}


//busca no servidor dados do usuario para ser apresentado em um modal
function carregaUsuario(idClicado, opcao){
//    var Sessao = getSessao();
    var envio = {
        metodo: "get_usuario",
        idpessoa: idClicado
//        usuario: Sessao.usuario,
//        sessao: Sessao.sessao
    };
    
    //chama a requisicao do servidor, o resultado é listado em uma tabela
    requisicao(true, "pessoa/buscar", envio, function(dadosRetorno) {
        if(dadosRetorno.sucesso){
            
            //testa qual opcao sera, editar ou mostrar
            if(opcao === "modal"){
                //carrega atributos no painel modal que sera exibido
                $("#modalTitulo").text("Usuário");
                $("#itensModal").append("<h3>"+dadosRetorno.data.nome+"</h3><h4> Sobrenome: "+dadosRetorno.data.sobrenome+"</h4><h4>Apelido: "+dadosRetorno.data.apelido+"</h4><h4> CPF: "+dadosRetorno.data.cpf+"</h4><h4> RG: "+dadosRetorno.data.rg+"</h4><h4> Data de nascimento: "+dadosRetorno.data.datanascimento+"</h4><h4> Sexo: "+dadosRetorno.data.sexo+"</h4><h4> Telefone1: "+dadosRetorno.data.telefone1+"</h4><h4>Telefone2"+dadosRetorno.data.telefone2+"</h4><h4>Email: "+dadosRetorno.data.email+"</h4><h4>Estado civil: "+dadosRetorno.data.estadocivil+"</h4><h4>Escolaridade: "+dadosRetorno.data.escolaridade+"</h4>");
//                $("#modalTitulo").text("Usuário");
//                alerta("<h3>"+dadosRetorno.data.nome+"</h3><h4> Sobrenome: "+dadosRetorno.data.sobrenome+"</h4><h4>Apelido: "+dadosRetorno.data.apelido+"</h4><h4> CPF: "+dadosRetorno.data.cpf+"</h4><h4> RG: "+dadosRetorno.data.rg+"</h4><h4> Data de nascimento: "+dadosRetorno.data.datanascimento+"</h4><h4> Sexo: "+dadosRetorno.data.sexo+"</h4><h4> Telefone1: "+dadosRetorno.data.telefone1+"</h4><h4>Telefone2"+dadosRetorno.data.telefone2+"</h4><h4>Email: "+dadosRetorno.data.email+"</h4><h4>Estado civil: "+dadosRetorno.data.estadocivil+"</h4><h4>Escolaridade: "+dadosRetorno.data.escolaridade+"</h4>");
            }else if(opcao === "editar"){
                $("#divItens").empty().append('<h2 class="sub-header">Editar Usuário</h2><form  data-toggle="validator" role="form" id="salvarEditUser"><div class="form-group"><label for="nomeUsuario" class="control-label">Nome:</label><input type="text" class="form-control" id="nomeUsuario" placeholder="Digite o nome do agricultor..." value="'+dadosRetorno.data.nome+'"></div><div class="form-group"><label for="sobrenomeUsuario" class="control-label">Sobrenome:</label><input type="text" class="form-control" id="sobrenomeUsuario" placeholder="Digite o sobrenome do agricultor..." value="'+dadosRetorno.data.sobrenome+'"></div><div class="form-group"><label for="rgAgricultor" class="control-label">RG:</label><input type="text" class="form-control" id="rgAgricultor" placeholder="Digite o rg do agricultor..." value="'+dadosRetorno.data.rg+'"></div><div class="form-group"><label for="cpfAgricultor" class="control-label">CPF:</label><input type="text" class="form-control" id="cpfAgricultor" placeholder="Digite o cpf do agricultor..." value="'+dadosRetorno.data.cpf+'" ></div><div class="form-group"><label for="apelidoAgricultor" class="control-label">Apelido:</label><input type="text" class="form-control" id="apelidoAgricultor" placeholder="Digite o apelido do agricultor..." value="'+dadosRetorno.data.apelido+'" ></div><div class="form-group"><label for="dataNascAgricultor" class="control-label">Data de nascimento:</label><input type="text" class="form-control" id="dataNascAgricultor" placeholder="Digite a data de nascimento do agricultor..." value="'+dadosRetorno.data.datanascimento+'" ></div><input type="radio" name="genero" value="m" checked> Masculino<br><input type="radio" name="genero" value="f"> Feminino<br> <br><div class="form-group"><label for="telefone1Agricultor" class="control-label">Telefone 1:</label><input type="text" class="form-control" id="telefone1Agricultor" placeholder="Digite o telefone 1 do agricultor..." value="'+dadosRetorno.data.telefone1+'" ></div><div class="form-group"><label for="telefone2Agricultor" class="control-label">Telefone 2:</label><input type="text" class="form-control" id="telefone2Agricultor" placeholder="Digite o telefone 2 do agricultor..." value="'+dadosRetorno.data.telefone2+'" ></div><div class="form-group"><label for="emailAgricultor" class="control-label">Email:</label><input type="text" class="form-control" id="emailAgricultor" placeholder="Digite o email do agricultor..." value="'+dadosRetorno.data.email+'" ></div><div class="form-group"><label for="escolaridadeAgricultor" class="control-label">Escolaridade:</label><select class="form-control " id="escolaridadeAgricultor" value="'+dadosRetorno.data.escolaridade+'"><option>Ensino fundamental</option><option>Ensino fundamental incompleto</option><option>Ensino médio</option><option>Ensino médio incompleto</option><option>Ensino superior</option><option>Ensino superior incompleto</option><option>Pós-graduação</option><option>Pós-graduação incompleta</option><option>Sem escolaridade</option></select></div><div class="form-group"><label for="estadocivilAgricultor" class="control-label">Estado civil:</label><select class="form-control " id="estadocivilAgricultor" value="'+dadosRetorno.data.estadocivil+'"><option>Solteiro(a)</option><option>Casado(a)</option><option>Divorciado(a)</option><option>Viúvo(a)</option><option>Separado(a)</option><option>Companheiro(a)</option></select></div><hr><button type="submit" class="btn btn-warning">Salvar <span class="fa fa-save" aria-hidden="true"></span></button></form>');
                $('#salvarEditUser').validator();
            }
            $(".painelCarregando").fadeOut(400);
        }else{
            //retira o painel loading
            $(".painelCarregando").fadeOut(400);
            alerta("Alerta!", dadosRetorno.mensagem);
        }
        //atualiza a sessao
        updateSessao(dadosRetorno.sessao);
    });
}

//carrega a lista de pais do banco de dados e lista em um select
$(document).on("focusin", ".carregaPais", function(){
    var envio = {
        metodo: "PAIS"
    };
    
    var idSelect = $(this).prop("id");

    //chama a requisicao do servidor, o resultado é listado em um select
    requisicao(true, "outros/listar", envio, function(dadosRetorno) {
        if(dadosRetorno.sucesso){

            $("#"+idSelect).empty();
            $.each(dadosRetorno.data, function(i, value){
                $("#"+idSelect).append('<option value="'+value.idpais+'">'+value.nomepais+'</option>');
            });
            
            //limpa os campos do estado e cidade
            if($(".carregaEstado").val() !== "" || $(".carregaCidade").val() !== ""){
                $(".carregaEstado").empty();
                $(".carregaCidade").empty();
            }
            
            $(".painelCarregando").fadeOut(400);
        }else{
            $(".painelCarregando").fadeOut(400);
            alerta("Alerta!", dadosRetorno.mensagem);
        }
 
    });

 
    return false;
});

//carrega do banco de dados os estados
$(document).on("focusin", ".carregaEstado", function(){
    var envio = {
        metodo: "ESTADOS",
        idpais: $(".carregaPais").prop("value")
    };
    
    var idSelect = $(this).prop("id");
//    alert(idSelect);
    //chama a requisicao do servidor, o resultado é listado em uma tabela
    requisicao(true, "outros/listar", envio, function(dadosRetorno) {
        if(dadosRetorno.sucesso){
            //
            //alerta("Alerta!", dadosRetorno.mensagem);
            $("#"+idSelect).empty();
            $.each(dadosRetorno.data, function(i, value){
                $("#"+idSelect).append('<option value="'+value.idestado+'">'+value.nomeestado+'</option>');
            });
            
            //limpa os campos do estado e cidade
            if($(".carregaCidade").val() !== ""){
                $(".carregaCidade").empty();
            }
            
            $(".painelCarregando").fadeOut(400);
        }else{
            $(".painelCarregando").fadeOut(400);
            alerta("Alerta!", dadosRetorno.mensagem);
        }
 
    });

 
    return false;
});

//carrega do banco de dados as cidades
$(document).on("focusin", ".carregaCidade", function(){
    var envio = {
        metodo: "CIDADES",
        idestado: $(".carregaEstado").prop("value")
    };
    
    var idSelect = $(this).prop("id");
//    alert(idSelect);
    //chama a requisicao do servidor, o resultado é listado em uma tabela
    requisicao(true, "outros/listar", envio, function(dadosRetorno) {
        if(dadosRetorno.sucesso){
            //
            //alerta("Alerta!", dadosRetorno.mensagem);
            $("#"+idSelect).empty();
            $.each(dadosRetorno.data, function(i, value){
                $("#"+idSelect).append('<option value="'+value.idcidade+'">'+value.nomecidade+'</option>');
            });
            
            
            $(".painelCarregando").fadeOut(400);
        }else{
            $(".painelCarregando").fadeOut(400);
            alerta("Alerta!", dadosRetorno.mensagem);
        }
 
    });

 
    return false;
});

//carrega a lista de unidades do banco de dados e lista em um select
$(document).on("focusin", ".carregaUnidades", function(){
//    var Sessao = getSessao();
    var envio = {
        metodo: "ID_NOME_CIDADE"
//        usuario: Sessao.usuario,
//        sessao: Sessao.sessao
    };
    
    var idSelect = $(this).prop("id");

    //chama a requisicao do servidor, o resultado é listado em um select
    requisicao(true, "unidade/listar", envio, function(dadosRetorno) {
        if(dadosRetorno.sucesso){
            
            $("#"+idSelect).empty();
            $.each(dadosRetorno.data, function(i, value){
                $("#"+idSelect).append('<option value="'+value.idunidade+'">'+value.nomeunidade+' {'+value.nomecidade+'}</option>');
            });
//            
//            //limpa os campos do estado e cidade
//            if($(".carregaEstado").val() !== "" || $(".carregaCidade").val() !== ""){
//                $(".carregaEstado").empty();
//                $(".carregaCidade").empty();
//            }
            
            $(".painelCarregando").fadeOut(400);
        }else{
            $(".painelCarregando").fadeOut(400);
            alerta("Alerta!", dadosRetorno.mensagem);
        }
        //atualiza a sessao
        updateSessao(dadosRetorno.sessao);
    });

 
    return false;
});

//chama a requisicao para salvar a saida de estoque
$(document).on("click", "#deslogar", function(e){
    alerta("Alerta!", 'Deseja realmente deslogar? <br><br><button type="button" id="botaoDeslogar" class="btn btn-warning" data-dismiss="modal">Sim</button>      <button type="button" class="btn btn-warning" data-dismiss="modal">Não</button>');
    return false;
});
//chama a requisicao para salvar a saida de estoque
$(document).on("click", "#botaoDeslogar", function(e){

        
    window.location = "../../seguranca/logout.jsp";

    return false;
});


//chama a requisicao para salvar a saida de estoque
$(document).on("submit", ".formProx", function(e){
    if(!e.isDefaultPrevented()){
        $("#qtdProgresso li").each(function(i){

            var tabs = $('a[data-toggle="tab"]');

            if($(this).hasClass("active")){
  
                tabs.eq(i+1).tab('show');
                $(this).removeClass("active").next().removeClass("disabled").addClass("active");
                
                return false;
            }
        });
        
    }
    

    return false;
});
//
//function continuarProgresso(){
//    $("#qtdProgresso li").each(function(i){
//
//            var tabs = $('a[data-toggle="tab"]');
//
//            if($(this).hasClass("active")){
//  
//                tabs.eq(i+1).tab('show');
//                $(this).removeClass("active").next().removeClass("disabled").addClass("active");
//                
//                return false;
//            }
//        });
//}