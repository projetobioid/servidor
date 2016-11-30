var ipServidor = 'localhost:8080';
//var ipServidor = '10.1.2.52:8080';
//var ipServidor = "187.19.101.252:8082";

function getSessao(){
    var logSessao = JSON.parse(localStorage.getItem("logSessao"));
    return logSessao;
}

function updateSessao(novaSessao){
    var logSessao = JSON.parse(localStorage.getItem("logSessao"));
    logSessao.sessao = novaSessao;
    localStorage.setItem("logSessao", JSON.stringify(logSessao));
}


//dois clicks
$(document).on("dblclick", "#divItens tr", function(evt)
{
    $('#modalApresentacao').modal('toggle');
    return false;
});





    
///////*painel lateral*////////////


//muda cor painel aonde esta a pagina carregada
function preEventosPaginaCadastros(itemSelecionado, icone, titulo, descricaoListar, descricaoNovo, descricaoEditar, descricaoExcluir){

    var item;
    
    if($("#msgInicio").is(':visible')) {
        item = "#msgInicio";
    }else{
        item = "#page";
    }
    
    
    
    $(item).fadeOut(400, function(){
        $(".itensPaginas").hide();
        $("#atalhosCadastro").show();
        
        $(".a").css("background", "rgba(255,255,255,0.6)");
        $(itemSelecionado).css("background", "#FFCC00");
        $("#identificacaoPage").empty().append('<spam class="fa '+icone+'" aria-hidden="true">&nbsp;'+titulo+'</spam>');
        $("#listar h5").text(descricaoListar);
        $("#novo h5").text(descricaoNovo);
        $("#editar h5").text(descricaoEditar);
        $("#excluir h5").text(descricaoExcluir);
        $("#page").fadeIn(400);
    });  
    
    
    
}

function preEventosPaginaGerenciamento(itemSelecionado, pagina, icone, titulo){
    var item;
    
    if($("#msgInicio").is(':visible')) {
        item = "#msgInicio";
    }else{
        item = "#page";
    }
     
   
    
    $(item).fadeOut(400, function(){
        $(".itensPaginas").hide();
        $(pagina).show();
        $("#pageDistribuir").show();
        
        $(".a").css("background", "rgba(255,255,255,0.6)");
        $(itemSelecionado).css("background", "#FFCC00");
        $("#identificacaoPage").empty().append('<spam class="fa '+icone+'" aria-hidden="true">&nbsp;'+titulo+'</spam>');
        $("#page").fadeIn(400);
    });
    
     
}

//painel escolha de opcoes de abrir pages
$(document).on("click", ".a", function(evt)
{
    
    var item = $(this).text();
    $("#divItens").fadeOut(400);
    
    if(item === "Distribuir cultivares"){
        preEventosPaginaGerenciamento($(this), "#distribuicaoCultivares", "fa-cart-arrow-down", "Distribuir cultivares");
    }else if(item === "Estoque da unidade"){
        preEventosPaginaGerenciamento($(this), "#estoqueUnidade", "fa-cubes", "Estoque da unidade");
    }else if(item === "Relatar safra"){
        preEventosPaginaGerenciamento($(this), "#relatarSafra", "fa-commenting", "Relatar safra");
    }else if(item === "Sincronizar app"){
        preEventosPaginaGerenciamento($(this), "#sincronizarApp", "fa-cloud-upload", "Sincronizar app");
    }else if(item === "Agricultores"){
        
        preEventosPaginaCadastros($(this), "fa-users", "Agricultores", "Listar todos os agricultores", "Adicionar um novo agricultor", "Editar informações de um agricultor", "Excluir um agricultor");
    
    }else if(item === "Cultivares"){
        preEventosPaginaCadastros($(this), "fa-leaf", "Cultivares", "Listar todos os cultivares", "Adicionar um novo cultivar", "Editar informações de um cultivar", "Excluir um cultivar");
    
    }else if(item === "Unidades"){
        preEventosPaginaCadastros($(this), "fa-university", "Unidades", "Listar todas as unidades", "Adicionar uma nova unidade", "Editar informações de uma unidade", "Excluir uma unidade");
    
    }else if(item === "Usuários"){
        preEventosPaginaCadastros($(this), "fa-user-secret","Usuários", "Listar todos os usuários", "Adicionar um novo usuário", "Editar um usuário", "Excluir um usuário");
    
    }
    
    return false;
});














/////////////menu///////////////

//listar
$(document).on("click", "#listar", function(evt)
{
  
    testeListar();
        
        
    return false;
});

function testeListar(){
    
    var Sessao = getSessao();
    //listar agricultores
    if($("#identificacaoPage > spam").text() === "Agricultores"){
        requisicao("pessoa/listar", 'metodo=listaragricultores&id='+Sessao.idpessoa+'&sessao='+Sessao.sessao, "listaragricultores");
    //listar cultivares
    }else if($("#identificacaoPage > spam").text() === "Cultivares"){
        requisicao("cultivar/listar", 'metodo=listarcultivares&id='+Sessao.idpessoa+'&sessao='+Sessao.sessao, "listarcultivares");
    //listar unidades
    }else if($("#identificacaoPage > spam").text() === "Unidades"){
        requisicao("unidade/listar", 'id='+Sessao.idpessoa+'&sessao='+Sessao.sessao, "listarunidades");
    //listar usuarios
    }else if($("#identificacaoPage > spam").text() === "Usuários"){
        requisicao("pessoa/listar", 'metodo=listarusuarios&id='+Sessao.idpessoa+'&sessao='+Sessao.sessao, "listarusuarios");
    }else{
        alert("Erro de requisição de navegação!");
    }
}

//novo
$(document).on("click", "#novo", function(evt)
{
   
    $("#divItens").fadeOut(400, function(){
 
        if($("#identificacaoPage > spam").text() === "Agricultores"){
            $("#divItens").empty().append('<h2 class="sub-header">Novo agricultor</h2><form><div class="form-group"><label for="nomeAgricultor" class="control-label">Nome:</label><input type="text" class="form-control" id="nomeAgricultor" placeholder="Digite o nome do agricultor..." ></div><div class="form-group"><label for="sobrenomeAgricultor" class="control-label">Sobrenome:</label><input type="text" class="form-control" id="sobrenomeAgricultor" placeholder="Digite o sobrenome do agricultor..." ></div><div class="form-group"><label for="rgAgricultor" class="control-label">RG:</label><input type="text" class="form-control" id="rgAgricultor" placeholder="Digite o rg do agricultor..."></div><div class="form-group"><label for="cpfAgricultor" class="control-label">CPF:</label><input type="text" class="form-control" id="cpfAgricultor" placeholder="Digite o cpf do agricultor..." ></div><div class="form-group"><label for="apelidoAgricultor" class="control-label">Apelido:</label><input type="text" class="form-control" id="apelidoAgricultor" placeholder="Digite o apelido do agricultor..." ></div><div class="form-group"><label for="dataNascAgricultor" class="control-label">Data de nascimento:</label><input type="date" class="form-control" id="dataNascAgricultor" placeholder="Digite a data de nascimento do agricultor..." ></div><input type="radio" name="genero" value="m" checked> Masculino<br><input type="radio" name="genero" value="f"> Feminino<br> <br><div class="form-group"><label for="telefone1Agricultor" class="control-label">Telefone 1:</label><input type="text" class="form-control" id="telefone1Agricultor" placeholder="Digite o telefone 1 do agricultor..." ></div><div class="form-group"><label for="telefone2Agricultor" class="control-label">Telefone 2:</label><input type="text" class="form-control" id="telefone2Agricultor" placeholder="Digite o telefone 2 do agricultor..." ></div><div class="form-group"><label for="emailAgricultor" class="control-label">Email:</label><input type="text" class="form-control" id="emailAgricultor" placeholder="Digite o email do agricultor..." ></div><div class="form-group"><label for="escolaridadeAgricultor" class="control-label">Escolaridade:</label><select class="form-control " id="escolaridadeAgricultor"><option>Ensino fundamental</option><option>Ensino fundamental incompleto</option><option>Ensino médio</option><option>Ensino médio incompleto</option><option>Ensino superior</option><option>Ensino superior incompleto</option><option>Pós-graduação</option><option>Pós-graduação incompleta</option><option>Sem escolaridade</option></select></div><div class="form-group"><label for="estadocivilAgricultor" class="control-label">Estado civil:</label><select class="form-control " id="estadocivilAgricultor"><option>Solteiro(a)</option><option>Casado(a)</option><option>Divorciado(a)</option><option>Viúvo(a)</option><option>Separado(a)</option><option>Companheiro(a)</option></select></div></form><hr><button type="button" id="salvarNovoAgricultor" class="btn btn-warning">Salvar <span class="fa fa-save" aria-hidden="true"></span></button>');
        }else if($("#identificacaoPage > spam").text() === "Cultivares"){
            $("#divItens").empty().append('<h2 class="sub-header">Novo cultivar</h2><form><div class="form-group"><label for="nomeCultivar" class="control-label">Nome cultivar:</label><input type="text" class="form-control" id="nomeCultivar" placeholder="Digite o nome do cultivar..."></div><div class="form-group"><label for="umCultivar" class="control-label">Unidade de medida:</label><select class="form-control " id="umCultivar"><option>Kilo(s)</option><option>Maniva(s)</option><option>Rama(s)</option></select></div><div class="form-group"><label for="pesoSaca" class="control-label">Peso da saca:</label><input type="text" class="form-control" id="pesoSaca" placeholder="Digite o peso da saca..."></div><div class="form-group"><label for="tempoColheita" class="control-label">Dias para relatar a colheita:</label><input type="number" class="form-control" min="1" id="tempoColheita" placeholder="Digite o tempo de colheita..."></div><div class="form-group"><label for="tempoDestinacao" class="control-label">Dias para relatar a destinação:</label><input type="number" class="form-control" min="1" id="tempoDestinacao" placeholder="Digite o tempo da destinação..."></div><div class="form-group"><label for="valorNutricional" class="control-label">Valor nutricional</label><textarea class="form-control" id="valorNutricional" placeholder="Digite o valor nutricional ..."></textarea></div><div class="form-group"><label for="descNovoCultivar" class="control-label">Descrição:</label><textarea class="form-control" id="descNovoCultivar" placeholder="Digite uma descrição..."></textarea></div><div class="input-group"><input type="checkbox" aria-label="..." id="bio" checked="">&nbsp;<label for="bio" class="control-label">Biofortificado</label></div><br><label class="control-label">Selecione uma imagem:</label><input id="imgNovoCultivar" type="file" class=""><img id="imgCultivarCarregada" class="hidden img-responsive" src="" width="180" height="180" alt="imgCultivar"/></form><hr><button type="button" class="btn btn-warning">Salvar <span class="fa fa-save" aria-hidden="true"></span></button>');
        }else if($("#identificacaoPage > spam").text() === "Unidades"){
            $("#divItens").empty().append('<h2 class="sub-header">Nova unidade</h2><form><div class="form-group"><label for="nomeUnidade" class="control-label">Nome unidade:</label><input type="text" class="form-control" id="nomeUnidade" placeholder="Digite o nome da unidade..."></div><div class="form-group"><label for="cnpj" class="control-label">CNPJ:</label><input type="text" class="form-control" id="cnpj" placeholder="Digite o cnpj..."></div><div class="form-group"><label for="razaoSocial" class="control-label">Razão social:</label><input type="text" class="form-control" id="razaoSocial" placeholder="Digite a razão social..."></div><div class="form-group"><label for="telefone1" class="control-label">Telefone 1:</label><input type="text" class="form-control" id="telefone1" placeholder="Digite o telefone 1..."></div><div class="form-group"><label for="telefone2" class="control-label">Telefone 2:</label><input type="text" class="form-control" id="telefone2" placeholder="Digite o telefone 2..."></div><div class="form-group"><label for="email" class="control-label">Email:</label><input type="text" class="form-control" id="email" placeholder="Digite o email..."></div><div class="form-group"><label for="rua" class="control-label">Rua:</label><input type="text" class="form-control" id="rua" placeholder="Digite a rua..."></div><div class="form-group"><label for="bairro" class="control-label">Bairro:</label><input type="text" class="form-control" id="bairro" placeholder="Digite o bairro..."></div><div class="form-group"><label for="numero" class="control-label">Número:</label><input type="text" class="form-control" id="numero" placeholder="Digite o número..."></div><div class="form-group"><label for="complemento" class="control-label">Complemento:</label><input type="text" class="form-control" id="complemento" placeholder="Digite o complemento..."></div><div class="form-group"><label for="gps_lat" class="control-label">Latitude:</label><input type="text" class="form-control" id="gps_lat" placeholder="Digite a latitude..." value="0"></div><div class="form-group"><label for="gps_long" class="control-label">Longitude:</label><input type="text" class="form-control" id="gps_long" placeholder="Digite a longitude..." value="0"></div><div class="form-group"><label for="pais" class="control-label">País:</label><select class="form-control " id="pais"><option>Brasil</option></select></div><div class="form-group"><label for="estado" class="control-label">Estado:</label><select class="form-control " id="estado"><option>Paraná</option></select></div><div class="form-group"><label for="cidade" class="control-label">Cidade:</label><select class="form-control " id="cidade"><option>Cascavel</option></select></div></form><hr><button type="button" class="btn btn-warning">Salvar <span class="fa fa-save" aria-hidden="true"></span></button>');
        }else if($("#identificacaoPage > spam").text() === "Usuários"){
            $("#divItens").empty().append('<h2 class="sub-header">Novo usuário</h2><form><div class="form-group"><input type="checkbox" name="papel1" value="g"> Gerenciador <br><input type="checkbox" name="papel2" value="e"> Entrevistador <br><input type="checkbox" name="papel3" value="adm"> Administrador <br></div><div class="form-group"><label for="unidadeAtuacao" class="control-label">Unidade de atuação:</label><select class="form-control " id="unidadeAtuacao"><option>Fundetec</option></select></div><div class="form-group"><label for="nomeUsuario" class="control-label">Nome:</label><input type="text" class="form-control" id="nomeUsuario" placeholder="Digite o nome do usuário..." ></div><div class="form-group"><label for="sobrenomeUsuario" class="control-label">Sobrenome:</label><input type="text" class="form-control" id="sobrenomeUsuario" placeholder="Digite o sobrenome do usuário..." ></div><div class="form-group"><label for="rgUsuario" class="control-label">RG:</label><input type="text" class="form-control" id="rgUsuario" placeholder="Digite o rg do usuario..."></div><div class="form-group"><label for="cpfUsuario" class="control-label">CPF:</label><input type="text" class="form-control" id="cpfUsuario" placeholder="Digite o cpf do usuário..." ></div></div><div class="form-group"><label for="dataNascAgricultor" class="control-label">Data de nascimento:</label><input type="date" class="form-control" id="dataNascAgricultor" placeholder="Digite a data de nascimento do agricultor..." ></div><input type="radio" name="genero" value="m" checked> Masculino<br><input type="radio" name="genero" value="f"> Feminino<br> <br><div class="form-group"><label for="telefone1Agricultor" class="control-label">Telefone 1:</label><input type="text" class="form-control" id="telefone1Agricultor" placeholder="Digite o telefone 1 do agricultor..." ></div><div class="form-group"><label for="telefone2Agricultor" class="control-label">Telefone 2:</label><input type="text" class="form-control" id="telefone2Agricultor" placeholder="Digite o telefone 2 do agricultor..." ></div><div class="form-group"><label for="emailAgricultor" class="control-label">Email:</label><input type="text" class="form-control" id="emailAgricultor" placeholder="Digite o email do agricultor..." ></div><div class="form-group"><label for="escolaridadeAgricultor" class="control-label">Escolaridade:</label><select class="form-control " id="escolaridadeAgricultor"><option>Ensino fundamental</option><option>Ensino fundamental incompleto</option><option>Ensino médio</option><option>Ensino médio incompleto</option><option>Ensino superior</option><option>Ensino superior incompleto</option><option>Pós-graduação</option><option>Pós-graduação incompleta</option><option>Sem escolaridade</option></select></div><div class="form-group"><label for="estadocivilAgricultor" class="control-label">Estado civil:</label><select class="form-control " id="estadocivilAgricultor"><option>Solteiro(a)</option><option>Casado(a)</option><option>Divorciado(a)</option><option>Viúvo(a)</option><option>Separado(a)</option><option>Companheiro(a)</option></select></div></form><hr><button type="button" class="btn btn-warning">Salvar <span class="fa fa-save" aria-hidden="true"></span></button>');
        }else{
            alert("Erro de requisição de navegação!");
        }
        
        $("#divItens").fadeIn(400);
    });
    
    return false;
});


//editar
    $(document).on("click", "#editar", function(evt)
    {
        var tabela = $("#divItens div table");
        var Sessao = getSessao();

        if(!tabela.is(':visible')){
            $("#Alertar").fadeIn(400).children("span").text("Selecione um item para editar.");
            testeListar();
        }else{
            var marcado = false;
            tabela.find('tr').each(function (){
                if($(this).hasClass("warning")){
                    //editar agricultores
                    if($("#identificacaoPage > spam").text() === "Agricultores"){
                        requisicao("pessoa/buscar", 'metodo=buscaragricultor&idpessoa='+$(this).find('td:eq(0)').html()+'&id='+Sessao.idpessoa+'&sessao='+Sessao.sessao, "buscaragricultor");
                    //editar cultivares
                    }else if($("#identificacaoPage > spam").text() === "Cultivares"){
                        requisicao("cultivar/buscar", 'metodo=buscarcultivar&idcultivar='+$(this).find('td:eq(0)').html()+'&id='+Sessao.idpessoa+'&sessao='+Sessao.sessao, "buscarcultivar");
                    //editar unidades
                    }else if($("#identificacaoPage > spam").text() === "Unidades"){
                        requisicao("unidade/buscar", 'metodo=buscarunidade&idunidade='+$(this).find('td:eq(0)').html()+'&id='+Sessao.idpessoa+'&sessao='+Sessao.sessao, "buscarunidade");
                    //editar usuarios
                    }else if($("#identificacaoPage > spam").text() === "Usuários"){
                        requisicao("pessoa/buscar", 'metodo=buscarusuario&idpessoa='+$(this).find('td:eq(0)').html()+'&id='+Sessao.idpessoa+'&sessao='+Sessao.sessao, "buscarusuario");
                    }else{
                        alert("Erro de requisição de navegação!");
                    }
                    marcado = true;
                    return false;
                }
            });
            
            if(!marcado){
                $("#Alertar").fadeIn(400);
            }
            

        }
            
         return false;
    });




////////listar na tabela////////
//agricultores
function listarAgricultores(dadosRetorno){
    
    var item = "";

    $.each(dadosRetorno, function(i, valor){
        item += "<tr><td>"+valor.idpessoa+"</td><td>"+valor.nome+"</td><td>"+valor.sobrenome+"</td><td>"+valor.rg+"</td><td>"+valor.cpf+"</td><td>"+valor.telefone1+"</td><td>"+valor.nomeunidade+"</td></tr>";

    });

    $("#divItens").fadeOut(400, function(){
    
        $("#divItens").empty().append('<h2 class="sub-header">Lista de agricultores</h2><div class="table-responsive"><table class="table table-hover"><thead><tr><th>ID</th><th>Nome</th><th>Sobrenome</th><th>RG</th><th>CPF</th><th>Telefone</th><th>Unidade</th></tr></thead><tbody>'+item+'</tbody></table></div>');
        $("#divItens").fadeIn(400);
    });
}

//cultivares
function listarCultivares(dadosRetorno){
    
    var item = "";

    $.each(dadosRetorno, function(i, valor){
        item += "<tr><td>"+valor.idcultivar+"</td><td>"+valor.nomecultivar+"</td><td>"+valor.grandeza+"</td></tr>";

    });
    
    $("#divItens").fadeOut(400, function(){
    
        $("#divItens").empty().append('<h2 class="sub-header">Lista de cultivares</h2><div class="table-responsive"><table class="table table-hover"><thead><tr><th>ID</th><th>Nome do cultivar</th><th>Unidade de medida</th></tr></thead><tbody>'+item+'</tbody></table></div>');
        $("#divItens").fadeIn(400);
        
    });
}

//listar unidades
function listarUnidades(dadosRetorno){
    
    var item = "";

    $.each(dadosRetorno, function(i, valor){
        item += "<tr><td>"+valor.idunidade+"</td><td>"+valor.nomeunidade+"</td><td>"+valor.telefone1+"</td><td>"+valor.telefone2+"</td><td>"+valor.email+"</td><td>"+valor.nomepais+"</td><td>"+valor.nomeestado+"</td><td>"+valor.nomecidade+"</td></tr>";

    });

    $("#divItens").fadeOut(400, function(){
    
        $("#divItens").empty().append('<h2 class="sub-header">Lista de unidades</h2><div class="table-responsive"><table class="table table-hover"><thead><tr><th>ID</th><th>Nome da Unidade</th><th>Telefone1</th><th>Telefone2</th><th>E-mail</th><th>País</th><th>Estado</th><th>Cidade</th></tr></thead><tbody>'+item+'</tbody></table></div>');
        $("#divItens").fadeIn(400);
        
    });
}

//listar usuarios
function listarUsuarios(dadosRetorno){
    
    var item = "";

    $.each(dadosRetorno, function(i, valor){
        item += "<tr><td>"+valor.idpessoa+"</td><td>"+valor.nome+"</td><td>"+valor.sobrenome+"</td><td>"+valor.rg+"</td><td>"+valor.cpf+"</td><td>"+valor.telefone1+"</td><td>"+valor.nomeunidade+"</td></tr>";

    });

    $("#divItens").fadeOut(400, function(){
    
        $("#divItens").empty().append('<h2 class="sub-header">Lista de usuários</h2><div class="table-responsive"><table class="table table-hover"><thead><tr><th>ID</th><th>Nome</th><th>Sobrenome</th><th>RG</th><th>CPF</th><th>Telefone</th><th>Unidade</th></tr></thead><tbody>'+item+'</tbody></table></div>');
        $("#divItens").fadeIn(400);
    });
}

//editar
function editar(dadosRetorno){
    $("#divItens").fadeOut(400, function(){
        
        //editar agricultor
        if($("#identificacaoPage > spam").text() === "Agricultores"){
            $("#divItens").empty().append('<h2 class="sub-header">Editar agricultor</h2><form><div class="form-group"><label for="nomeAgricultor" class="control-label">Nome:</label><input type="text" class="form-control" id="nomeAgricultor" placeholder="Digite o nome do agricultor..." value="'+dadosRetorno.nome+'"></div><div class="form-group"><label for="sobrenomeAgricultor" class="control-label">Sobrenome:</label><input type="text" class="form-control" id="sobrenomeAgricultor" placeholder="Digite o sobrenome do agricultor..." value="'+dadosRetorno.sobrenome+'"></div><div class="form-group"><label for="rgAgricultor" class="control-label">RG:</label><input type="text" class="form-control" id="rgAgricultor" placeholder="Digite o rg do agricultor..." value="'+dadosRetorno.rg+'"></div><div class="form-group"><label for="cpfAgricultor" class="control-label">CPF:</label><input type="text" class="form-control" id="cpfAgricultor" placeholder="Digite o cpf do agricultor..." value="'+dadosRetorno.cpf+'" ></div><div class="form-group"><label for="apelidoAgricultor" class="control-label">Apelido:</label><input type="text" class="form-control" id="apelidoAgricultor" placeholder="Digite o apelido do agricultor..." value="'+dadosRetorno.apelido+'" ></div><div class="form-group"><label for="dataNascAgricultor" class="control-label">Data de nascimento:</label><input type="date" class="form-control" id="dataNascAgricultor" placeholder="Digite a data de nascimento do agricultor..." value="'+dadosRetorno.datanascimento+'" ></div><input type="radio" name="genero" value="m" checked> Masculino<br><input type="radio" name="genero" value="f"> Feminino<br> <br><div class="form-group"><label for="telefone1Agricultor" class="control-label">Telefone 1:</label><input type="text" class="form-control" id="telefone1Agricultor" placeholder="Digite o telefone 1 do agricultor..." value="'+dadosRetorno.telefone1+'" ></div><div class="form-group"><label for="telefone2Agricultor" class="control-label">Telefone 2:</label><input type="text" class="form-control" id="telefone2Agricultor" placeholder="Digite o telefone 2 do agricultor..." value="'+dadosRetorno.telefone2+'" ></div><div class="form-group"><label for="emailAgricultor" class="control-label">Email:</label><input type="text" class="form-control" id="emailAgricultor" placeholder="Digite o email do agricultor..." value="'+dadosRetorno.email+'" ></div><div class="form-group"><label for="escolaridadeAgricultor" class="control-label">Escolaridade:</label><select class="form-control " id="escolaridadeAgricultor" value="'+dadosRetorno.escolaridade+'"><option>Ensino fundamental</option><option>Ensino fundamental incompleto</option><option>Ensino médio</option><option>Ensino médio incompleto</option><option>Ensino superior</option><option>Ensino superior incompleto</option><option>Pós-graduação</option><option>Pós-graduação incompleta</option><option>Sem escolaridade</option></select></div><div class="form-group"><label for="estadocivilAgricultor" class="control-label">Estado civil:</label><select class="form-control " id="estadocivilAgricultor" value="'+dadosRetorno.estadocivil+'"><option>Solteiro(a)</option><option>Casado(a)</option><option>Divorciado(a)</option><option>Viúvo(a)</option><option>Separado(a)</option><option>Companheiro(a)</option></select></div></form><hr><button type="button" class="btn btn-warning">Salvar <span class="fa fa-save" aria-hidden="true"></span></button>');
        //editar cultivar    
        }else if($("#identificacaoPage > spam").text() === "Cultivares"){
            $("#divItens").empty().append('<h2 class="sub-header">Editar cultivar</h2><form><div class="form-group"><label for="nomeCultivar" class="control-label">Nome cultivar:</label><input type="text" class="form-control" id="nomeCultivar" placeholder="Digite o nome do cultivar..." value="'+dadosRetorno.nomecultivar+'"></div><div class="form-group"><label for="umCultivar" class="control-label">Unidade de medida:</label><select class="form-control " id="umCultivar" value="'+dadosRetorno.grandeza+'"><option>Kilo(s)</option><option>Maniva(s)</option><option>Rama(s)</option></select></div><div class="form-group"><label for="pesoSaca" class="control-label">Peso da saca:</label><input type="text" class="form-control" id="pesoSaca" placeholder="Digite o peso da saca..." value="'+dadosRetorno.peso_saca+'"></div><div class="form-group"><label for="tempoColheita" class="control-label">Dias para relatar a colheita:</label><input type="number" class="form-control" min="1" id="tempoColheita" placeholder="Digite o tempo de colheita..." value="'+dadosRetorno.tempodecolheita+'"></div><div class="form-group"><label for="tempoDestinacao" class="control-label">Dias para relatar a destinação:</label><input type="number" class="form-control" min="1" id="tempoDestinacao" placeholder="Digite o tempo da destinação..." value="'+dadosRetorno.tempodestinacao+'"></div><div class="form-group"><label for="valorNutricional" class="control-label">Valor nutricional</label><textarea class="form-control" id="valorNutricional" placeholder="Digite o valor nutricional ...">'+dadosRetorno.valornutricional+'</textarea></div><div class="form-group"><label for="descCultivar" class="control-label">Descrição:</label><textarea class="form-control" id="descCultivar" placeholder="Digite uma descrição...">'+dadosRetorno.descricao+'</textarea></div><div class="input-group"><input type="checkbox" aria-label="..." id="bio" checked="'+dadosRetorno.biofortificado+'">&nbsp;<label for="bio" class="control-label">Biofortificado</label></div><br><label class="control-label">Selecione uma imagem:</label><input id="imgNovoCultivar" type="file" class=""><img id="imgCultivarCarregada" class="img-responsive" src="'+dadosRetorno.imagem+'" width="180" height="180" alt="imgCultivar"/></form><hr><button type="button" class="btn btn-warning">Salvar <span class="fa fa-save" aria-hidden="true"></span></button>');
            //$("#imgCultivarCarregada").removeClass("hidden");
        //editar unidade
        }else if($("#identificacaoPage > spam").text() === "Unidades"){
            
            $("#divItens").empty().append('<h2 class="sub-header">Editar unidade</h2><form><div class="form-group"><label for="nomeUnidade" class="control-label">Nome unidade:</label><input type="text" class="form-control" id="nomeUnidade" placeholder="Digite o nome da unidade..." value="'+dadosRetorno.nomeunidade+'"></div><div class="form-group"><label for="cnpj" class="control-label">CNPJ:</label><input type="text" class="form-control" id="cnpj" placeholder="Digite o cnpj..." value="'+dadosRetorno.cnpj+'"></div><div class="form-group"><label for="razaoSocial" class="control-label">Razão social:</label><input type="text" class="form-control" id="razaoSocial" placeholder="Digite a razão social..." value="'+dadosRetorno.razao_social+'"></div><div class="form-group"><label for="telefone1" class="control-label">Telefone 1:</label><input type="text" class="form-control" id="telefone1" placeholder="Digite o telefone 1..." value="'+dadosRetorno.telefone1+'"></div><div class="form-group"><label for="telefone2" class="control-label">Telefone 2:</label><input type="text" class="form-control" id="telefone2" placeholder="Digite o telefone 2..." value="'+dadosRetorno.telefone2+'"></div><div class="form-group"><label for="email" class="control-label">Email:</label><input type="text" class="form-control" id="email" placeholder="Digite o email..."value="'+dadosRetorno.email+'"></div><div class="form-group"><label for="rua" class="control-label">Rua:</label><input type="text" class="form-control" id="rua" placeholder="Digite a rua..." value="'+dadosRetorno.rua+'"></div><div class="form-group"><label for="bairro" class="control-label">Bairro:</label><input type="text" class="form-control" id="bairro" placeholder="Digite o bairro..." value="'+dadosRetorno.bairro+'"></div><div class="form-group"><label for="numero" class="control-label">Número:</label><input type="text" class="form-control" id="numero" placeholder="Digite o número..." value="'+dadosRetorno.numero+'"></div><div class="form-group"><label for="complemento" class="control-label">Complemento:</label><input type="text" class="form-control" id="complemento" placeholder="Digite o complemento..." value="'+dadosRetorno.complemento+'"></div><div class="form-group"><label for="gps_lat" class="control-label">Latitude:</label><input type="text" class="form-control" id="gps_lat" placeholder="Digite a latitude..." value="'+dadosRetorno.gps_lat+'"></div><div class="form-group"><label for="gps_long" class="control-label">Longitude:</label><input type="text" class="form-control" id="gps_long" placeholder="Digite a longitude..." value="'+dadosRetorno.gps_long+'"></div><div class="form-group"><label for="pais" class="control-label">País:</label><select class="form-control " id="pais" value="'+dadosRetorno.nomepais+'"><option>Brasil</option></select></div><div class="form-group"><label for="estado" class="control-label">Estado:</label><select class="form-control " id="estado value="'+dadosRetorno.nomeestado+'""><option>Paraná</option></select></div><div class="form-group"><label for="cidade" class="control-label">Cidade:</label><select class="form-control " id="cidade" value="'+dadosRetorno.nomecidade+'"><option>Cascavel</option></select></div></form><hr><button type="button" class="btn btn-warning">Salvar <span class="fa fa-save" aria-hidden="true"></span></button>');
        //editar usuario
        }else if($("#identificacaoPage > spam").text() === "Usuários"){
            $("#divItens").empty().append('<h2 class="sub-header">Editar agricultor</h2><form><div class="form-group"><label for="nomeAgricultor" class="control-label">Nome:</label><input type="text" class="form-control" id="nomeAgricultor" placeholder="Digite o nome do agricultor..." value="'+dadosRetorno.nome+'"></div><div class="form-group"><label for="sobrenomeAgricultor" class="control-label">Sobrenome:</label><input type="text" class="form-control" id="sobrenomeAgricultor" placeholder="Digite o sobrenome do agricultor..." value="'+dadosRetorno.sobrenome+'"></div><div class="form-group"><label for="rgAgricultor" class="control-label">RG:</label><input type="text" class="form-control" id="rgAgricultor" placeholder="Digite o rg do agricultor..." value="'+dadosRetorno.rg+'"></div><div class="form-group"><label for="cpfAgricultor" class="control-label">CPF:</label><input type="text" class="form-control" id="cpfAgricultor" placeholder="Digite o cpf do agricultor..." value="'+dadosRetorno.cpf+'" ></div><div class="form-group"><label for="apelidoAgricultor" class="control-label">Apelido:</label><input type="text" class="form-control" id="apelidoAgricultor" placeholder="Digite o apelido do agricultor..." value="'+dadosRetorno.apelido+'" ></div><div class="form-group"><label for="dataNascAgricultor" class="control-label">Data de nascimento:</label><input type="date" class="form-control" id="dataNascAgricultor" placeholder="Digite a data de nascimento do agricultor..." value="'+dadosRetorno.datanascimento+'" ></div><input type="radio" name="genero" value="m" checked> Masculino<br><input type="radio" name="genero" value="f"> Feminino<br> <br><div class="form-group"><label for="telefone1Agricultor" class="control-label">Telefone 1:</label><input type="text" class="form-control" id="telefone1Agricultor" placeholder="Digite o telefone 1 do agricultor..." value="'+dadosRetorno.telefone1+'" ></div><div class="form-group"><label for="telefone2Agricultor" class="control-label">Telefone 2:</label><input type="text" class="form-control" id="telefone2Agricultor" placeholder="Digite o telefone 2 do agricultor..." value="'+dadosRetorno.telefone2+'" ></div><div class="form-group"><label for="emailAgricultor" class="control-label">Email:</label><input type="text" class="form-control" id="emailAgricultor" placeholder="Digite o email do agricultor..." value="'+dadosRetorno.email+'" ></div><div class="form-group"><label for="escolaridadeAgricultor" class="control-label">Escolaridade:</label><select class="form-control " id="escolaridadeAgricultor" value="'+dadosRetorno.escolaridade+'"><option>Ensino fundamental</option><option>Ensino fundamental incompleto</option><option>Ensino médio</option><option>Ensino médio incompleto</option><option>Ensino superior</option><option>Ensino superior incompleto</option><option>Pós-graduação</option><option>Pós-graduação incompleta</option><option>Sem escolaridade</option></select></div><div class="form-group"><label for="estadocivilAgricultor" class="control-label">Estado civil:</label><select class="form-control " id="estadocivilAgricultor" value="'+dadosRetorno.estadocivil+'"><option>Solteiro(a)</option><option>Casado(a)</option><option>Divorciado(a)</option><option>Viúvo(a)</option><option>Separado(a)</option><option>Companheiro(a)</option></select></div></form><hr><button type="button" class="btn btn-warning">Salvar <span class="fa fa-save" aria-hidden="true"></span></button>');
        }else{
            alert("Erro de requisição de navegação!");
        }
        
    
        $("#divItens").fadeIn(400);
    });
    
}

function requisicao(url, envio, metodo){
 
//alert(envio);
    var dadosRetorno = null;
    $.post("http://"+ipServidor+"/Projeto_BioID-war/servico/"+url, envio, function(dados){
        
        if(dados.sucesso){
            updateSessao(dados.sessao);
            dadosRetorno = dados;
        }else{
            alert("Erro em buscar :" +dados.mensagem);
        }
    }, "json")
    //Tratamento de erro da requisicao servico RESt login
    .fail(function(){
        alert("Erro na requisição com o servidor!");
    }).done(function(){
        
        //listar
        if(metodo === "listaragricultores"){
            listarAgricultores(dadosRetorno.data);
        }else if(metodo === "listarcultivares"){
            listarCultivares(dadosRetorno.data);
        }else if(metodo === "listarunidades"){
            listarUnidades(dadosRetorno.data);
        }else if(metodo === "listarusuarios"){
            listarUsuarios(dadosRetorno.data);
        
    
        //editar
        }else if(metodo === "buscarcultivar" || metodo === "buscaragricultor" || metodo === "buscarusuario" || metodo === "buscarunidade"){
            editar(dadosRetorno.data);

        }
        
        
    });
    
}

///marcacao da tabela

$(document).on("click", "tbody > tr", function(evt)
{
   

//    //marcar de laranja a row
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
        $("#Alertar").fadeOut(400);
    }

     return false;
});



//botoes salvar
$(document).on("click", "#salvarNovoAgricultor", function(){
    
    if($("#nomeAgricultor").val() === ""){
        alertFocus("#nomeAgricultor", "nome");
    }else if($("#sobrenomeAgricultor").val() === ""){
        alertFocus("#sobrenomeAgricultor", "sobrenome");
    }else if($("#rgAgricultor").val() === ""){
        alertFocus("#rgAgricultor", "RG");
    }else if($("#cpfAgricultor").val() === ""){
        alertFocus("#cpfAgricultor", "CPF");
    }else if($("#dataNascAgricultor").val() === ""){
        alertFocus("#dataNascAgricultor", "data de nascimento");
    }else if($("#telefone1Agricultor").val() === ""){
        alertFocus("#telefone1Agricultor", "Telefone 1");
    }else{
        //var envio = "cidade_idcidade="++"&rua="++"&gps_lat="++"&gps_long="++"&bairro="++"&complemento="++"&cep="++"&numero="++"&escolaridade_idescolaridade="++"&estadocivil_idestadocivil="++"&nome="+$("#nomeAgricultor").val()+"&sobrenome="+$("#sobrenomeAgricultor").val()+"&apelido="++"&cpf="+$("#cpfAgricultor").val()+"&rg="+$("#rgAgricultor").val()+"&datanascimento="+$("#dataNascAgricultor").val()+"&sexo="++"&telefone1="+$("#telefone1Agricultor").val()+"&telefone2="+$("#telefone2Agricultor").val()+"&email="++"&qtdedeintegrantes="++"&qtdedecriancas="++"&qtdedegravidas="++"&usuario="++"&senha="++"&unidade_idunidade="++"&nomepropriedade="++"&area="++"&unidadedemedida="++"&areautilizavel="++"&unidadedemedidaau=";
        alert("Evento em construção");
    }

    return false;
});

function alertFocus(componente, msg){
    alert("O campo "+msg+" não pode ser vazio!");
        $(componente).focus();
}

