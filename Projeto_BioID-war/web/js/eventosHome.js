var ipServidor = 'localhost:8080';
//var ipServidor = '10.1.2.52:8080';
//var ipServidor = "187.19.101.252:8082";
//var ipServidor = '10.1.2.52:8080';

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
function alerta(msg){
    $("#itensModal").empty().append(msg);
    $("#modalTitulo").text("Alerta!");
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


//dois clicks tabela aparece modal com atributos referentes ao item selecionado
$(document).on("dblclick", "#divItens tr", function(evt)
{

    var pagAberta = $("#identificacaoPage > spam").text();
    
    //chama a função correspondente a pagina aberta
    if(pagAberta === "Agricultores"){
        carregaAgricultor($(this).find('td:eq(0)').html(), "modal");
    //editar cultivares
    }else if(pagAberta === "Cultivares"){
        carregaCultivar($(this).find('td:eq(0)').html(), "modal");
    //editar unidades
    }else if(pagAberta === "Unidades"){
        carregaUnidade($(this).find('td:eq(0)').html(), "modal");
    //editar usuarios
    }else if(pagAberta === "Usuários"){
        carregaUsuario($(this).find('td:eq(0)').html(), "modal");
    }else{
        alerta("Erro de requisição de navegação!");
    }
    
    
    $('#modalApresentacao').modal('toggle');
    return false;
});

//busca no servidor dados do agricultor para ser apresentado em um modal
function carregaAgricultor(idClicado, opcao){
    var Sessao = getSessao();
    var envio = {
        metodo: "buscaragricultor",
        idpessoa: idClicado,
        id: Sessao.idpessoa,
        sessao: Sessao.sessao
    };
    
    
    //chama a requisicao do servidor, o resultado é listado em uma tabela
    requisicao("pessoa/buscar", envio, function(dadosRetorno) {
        //testa qual opcao sera, editar ou mostrar
        if(opcao === "modal"){
            //carrega atributos no painel modal que sera exibido
            $("#modalTitulo").text("Agricultor");
            $("#itensModal").empty().append("<h3>"+dadosRetorno.nome+"</h3><h4> Sobrenome: "+dadosRetorno.sobrenome+"</h4><h4>Apelido: "+dadosRetorno.apelido+"</h4><h4> CPF: "+dadosRetorno.cpf+"</h4><h4> RG: "+dadosRetorno.rg+"</h4><h4> Data de nascimento: "+dadosRetorno.datanascimento+"</h4><h4> Sexo: "+dadosRetorno.sexo+"</h4><h4> Telefone1: "+dadosRetorno.telefone1+"</h4><h4>Telefone2"+dadosRetorno.telefone2+"</h4><h4>Email: "+dadosRetorno.email+"</h4><h4>Quantidade de crianças: "+dadosRetorno.qtdcriancas+"</h4><h4>Quantidade de integrantes: "+dadosRetorno.qtdintegrantes+"</h4><h4>Quantidade de grávidas: "+dadosRetorno.qtdgravidas+"</h4><h4>Estado civil: "+dadosRetorno.estadocivil+"</h4><h4>Escolaridade: "+dadosRetorno.escolaridade+"</h4>");
        }else if(opcao === "editar"){
            $("#divItens").empty().append('<h2 class="sub-header">Editar agricultor</h2><form><div class="form-group"><label for="nomeAgricultor" class="control-label">Nome:</label><input type="text" class="form-control" id="nomeAgricultor" placeholder="Digite o nome do agricultor..." value="'+dadosRetorno.nome+'"></div><div class="form-group"><label for="sobrenomeAgricultor" class="control-label">Sobrenome:</label><input type="text" class="form-control" id="sobrenomeAgricultor" placeholder="Digite o sobrenome do agricultor..." value="'+dadosRetorno.sobrenome+'"></div><div class="form-group"><label for="rgAgricultor" class="control-label">RG:</label><input type="text" class="form-control" id="rgAgricultor" placeholder="Digite o rg do agricultor..." value="'+dadosRetorno.rg+'"></div><div class="form-group"><label for="cpfAgricultor" class="control-label">CPF:</label><input type="text" class="form-control" id="cpfAgricultor" placeholder="Digite o cpf do agricultor..." value="'+dadosRetorno.cpf+'" ></div><div class="form-group"><label for="apelidoAgricultor" class="control-label">Apelido:</label><input type="text" class="form-control" id="apelidoAgricultor" placeholder="Digite o apelido do agricultor..." value="'+dadosRetorno.apelido+'" ></div><div class="form-group"><label for="dataNascAgricultor" class="control-label">Data de nascimento:</label><input type="date" class="form-control" id="dataNascAgricultor" placeholder="Digite a data de nascimento do agricultor..." value="'+dadosRetorno.datanascimento+'" ></div><input type="radio" name="genero" value="m" checked> Masculino<br><input type="radio" name="genero" value="f"> Feminino<br> <br><div class="form-group"><label for="telefone1Agricultor" class="control-label">Telefone 1:</label><input type="text" class="form-control" id="telefone1Agricultor" placeholder="Digite o telefone 1 do agricultor..." value="'+dadosRetorno.telefone1+'" ></div><div class="form-group"><label for="telefone2Agricultor" class="control-label">Telefone 2:</label><input type="text" class="form-control" id="telefone2Agricultor" placeholder="Digite o telefone 2 do agricultor..." value="'+dadosRetorno.telefone2+'" ></div><div class="form-group"><label for="emailAgricultor" class="control-label">Email:</label><input type="text" class="form-control" id="emailAgricultor" placeholder="Digite o email do agricultor..." value="'+dadosRetorno.email+'" ></div><div class="form-group"><label for="escolaridadeAgricultor" class="control-label">Escolaridade:</label><select class="form-control " id="escolaridadeAgricultor" value="'+dadosRetorno.escolaridade+'"><option>Ensino fundamental</option><option>Ensino fundamental incompleto</option><option>Ensino médio</option><option>Ensino médio incompleto</option><option>Ensino superior</option><option>Ensino superior incompleto</option><option>Pós-graduação</option><option>Pós-graduação incompleta</option><option>Sem escolaridade</option></select></div><div class="form-group"><label for="estadocivilAgricultor" class="control-label">Estado civil:</label><select class="form-control " id="estadocivilAgricultor" value="'+dadosRetorno.estadocivil+'"><option>Solteiro(a)</option><option>Casado(a)</option><option>Divorciado(a)</option><option>Viúvo(a)</option><option>Separado(a)</option><option>Companheiro(a)</option></select></div></form><hr><button type="button" class="btn btn-warning">Salvar <span class="fa fa-save" aria-hidden="true"></span></button>');
//        
        }
    //retira o painel loading
    $(".painelCarregando").fadeOut(400);
    });
}

//busca no servidor dados do cultivar para ser apresentado em um modal
function carregaCultivar(idClicado, opcao){
    var Sessao = getSessao();
    var envio = {
        metodo: "buscarcultivar",
        idcultivar: idClicado,
        id: Sessao.idpessoa,
        sessao: Sessao.sessao
    };
    
    //chama a requisicao do servidor, o resultado é listado em uma tabela
    requisicao("cultivar/buscar", envio, function(dadosRetorno) {
        //testa qual opcao sera, editar ou mostrar
        if(opcao === "modal"){
            //carrega atributos no painel modal que sera exibido
            $("#modalTitulo").text("Cultivar");
            //carrega atributos no painel modal que sera exibido
            $("#itensModal").empty().append('<h3>'+dadosRetorno.nomecultivar+'</h3><img class=" img-responsive" src="'+dadosRetorno.imagem+'" width="180" height="180" alt="imgCultivar"/><h4> Descrição: '+dadosRetorno.descricao+'</h4><h4> Biofortificado: '+dadosRetorno.biofortificado+'</h4><h4> Unidade de medida: '+dadosRetorno.grandeza+'</h4><h4> Valor nutricional: '+dadosRetorno.valornutricional+'</h4><h4> Tempo de colheita: '+dadosRetorno.tempodecolheita+'</h4><h4> Tempo de destinação: '+dadosRetorno.tempodestinacao+'</h4><h4> Peso da saca: '+dadosRetorno.peso_saca+' kilo(s)</h4>');
        }else if(opcao === "editar"){
            $("#divItens").empty().append('<h2 class="sub-header">Editar cultivar</h2><form><div class="form-group"><label for="nomeCultivar" class="control-label">Nome cultivar:</label><input type="text" class="form-control" id="nomeCultivar" placeholder="Digite o nome do cultivar..." value="'+dadosRetorno.nomecultivar+'"></div><div class="form-group"><label for="umCultivar" class="control-label">Unidade de medida:</label><select class="form-control " id="umCultivar" value="'+dadosRetorno.grandeza+'"><option value="7">Kilo(s)</option><option value="6">Maniva(s)</option><option value="5">Rama(s)</option></select></div><div class="form-group"><label for="pesoSaca" class="control-label">Peso da saca:</label><input type="text" class="form-control" id="pesoSaca" placeholder="Digite o peso da saca..." value="'+dadosRetorno.peso_saca+'"></div><div class="form-group"><label for="tempoColheita" class="control-label">Dias para relatar a colheita:</label><input type="number" class="form-control" min="1" id="tempoColheita" placeholder="Digite o tempo de colheita..." value="'+dadosRetorno.tempodecolheita+'"></div><div class="form-group"><label for="tempoDestinacao" class="control-label">Dias para relatar a destinação:</label><input type="number" class="form-control" min="1" id="tempoDestinacao" placeholder="Digite o tempo da destinação..." value="'+dadosRetorno.tempodestinacao+'"></div><div class="form-group"><label for="valorNutricional" class="control-label">Valor nutricional</label><textarea class="form-control" id="valorNutricional" placeholder="Digite o valor nutricional ...">'+dadosRetorno.valornutricional+'</textarea></div><div class="form-group"><label for="descCultivar" class="control-label">Descrição:</label><textarea class="form-control" id="descCultivar" placeholder="Digite uma descrição...">'+dadosRetorno.descricao+'</textarea></div><div class="input-group"><input type="checkbox" aria-label="..." id="bio" checked="'+dadosRetorno.biofortificado+'">&nbsp;<label for="bio" class="control-label">Biofortificado</label></div><br><label class="control-label">Selecione uma imagem:</label><input id="imgNovoCultivar" type="file" class=""><img id="imgCultivarCarregada" class="img-responsive" src="'+dadosRetorno.imagem+'" width="180" height="180" alt="imgCultivar"/></form><hr><button type="button" class="btn btn-warning">Salvar <span class="fa fa-save" aria-hidden="true"></span></button>');
            //$("#imgCultivarCarregada").removeClass("hidden");
        }
    //retira o painel loading
    $(".painelCarregando").fadeOut(400);
    });
}

//busca no servidor dados da unidade para ser apresentado em um modal
function carregaUnidade(idClicado, opcao){
    var Sessao = getSessao();
    var envio = {
        metodo: "buscarunidade",
        idunidade: idClicado,
        id: Sessao.idpessoa,
        sessao: Sessao.sessao
    };
    
    //chama a requisicao do servidor, o resultado é listado em uma tabela
    requisicao("unidade/buscar", envio, function(dadosRetorno) {
        //testa qual opcao sera, editar ou mostrar
        if(opcao === "modal"){
            //carrega atributos no painel modal que sera exibido
            $("#modalTitulo").text("Unidade");
            $("#itensModal").empty().append('<h3>'+dadosRetorno.nomeunidade+'</h3><h4> Cidade: '+dadosRetorno.nomecidade+'</h4><h4> Estado: '+dadosRetorno.nomeestado+'</h4><h4> País: '+dadosRetorno.nomepais+'</h4><h4> Telefone1: '+dadosRetorno.telefone1+'</h4><h4> Telefone2: '+dadosRetorno.telefone2+'</h4><h4> Email: '+dadosRetorno.email+'</h4><h4> Cnpj: '+dadosRetorno.cnpj+'</h4><h4> Razão social: '+dadosRetorno.razao_social+'</h4><h4> Nome fantasia: '+dadosRetorno.nome_fanta+'</h4><h4> Rua: '+dadosRetorno.rua+'</h4><h4> Bairro: '+dadosRetorno.bairro+'</h4><h4> Complemento: '+dadosRetorno.complemento+'</h4><h4> Latitude: '+dadosRetorno.gps_lat+'</h4><h4> Longitude: '+dadosRetorno.gps_long+'</h4>');
        }else if(opcao === "editar"){
            $("#divItens").empty().append('<h2 class="sub-header">Editar unidade</h2><form><div class="form-group"><label for="nomeUnidade" class="control-label">Nome unidade:</label><input type="text" class="form-control" id="nomeUnidade" placeholder="Digite o nome da unidade..." value="'+dadosRetorno.nomeunidade+'"></div><div class="form-group"><label for="cnpj" class="control-label">CNPJ:</label><input type="text" class="form-control" id="cnpj" placeholder="Digite o cnpj..." value="'+dadosRetorno.cnpj+'"></div><div class="form-group"><label for="razaoSocial" class="control-label">Razão social:</label><input type="text" class="form-control" id="razaoSocial" placeholder="Digite a razão social..." value="'+dadosRetorno.razao_social+'"></div><div class="form-group"><label for="telefone1" class="control-label">Telefone 1:</label><input type="text" class="form-control" id="telefone1" placeholder="Digite o telefone 1..." value="'+dadosRetorno.telefone1+'"></div><div class="form-group"><label for="telefone2" class="control-label">Telefone 2:</label><input type="text" class="form-control" id="telefone2" placeholder="Digite o telefone 2..." value="'+dadosRetorno.telefone2+'"></div><div class="form-group"><label for="email" class="control-label">Email:</label><input type="text" class="form-control" id="email" placeholder="Digite o email..."value="'+dadosRetorno.email+'"></div><div class="form-group"><label for="rua" class="control-label">Rua:</label><input type="text" class="form-control" id="rua" placeholder="Digite a rua..." value="'+dadosRetorno.rua+'"></div><div class="form-group"><label for="bairro" class="control-label">Bairro:</label><input type="text" class="form-control" id="bairro" placeholder="Digite o bairro..." value="'+dadosRetorno.bairro+'"></div><div class="form-group"><label for="numero" class="control-label">Número:</label><input type="text" class="form-control" id="numero" placeholder="Digite o número..." value="'+dadosRetorno.numero+'"></div><div class="form-group"><label for="complemento" class="control-label">Complemento:</label><input type="text" class="form-control" id="complemento" placeholder="Digite o complemento..." value="'+dadosRetorno.complemento+'"></div><div class="form-group"><label for="gps_lat" class="control-label">Latitude:</label><input type="text" class="form-control" id="gps_lat" placeholder="Digite a latitude..." value="'+dadosRetorno.gps_lat+'"></div><div class="form-group"><label for="gps_long" class="control-label">Longitude:</label><input type="text" class="form-control" id="gps_long" placeholder="Digite a longitude..." value="'+dadosRetorno.gps_long+'"></div><div class="form-group"><label for="pais" class="control-label">País:</label><select class="form-control " id="pais" value="'+dadosRetorno.nomepais+'"><option>Brasil</option></select></div><div class="form-group"><label for="estado" class="control-label">Estado:</label><select class="form-control " id="estado value="'+dadosRetorno.nomeestado+'""><option>Paraná</option></select></div><div class="form-group"><label for="cidade" class="control-label">Cidade:</label><select class="form-control " id="cidade" value="'+dadosRetorno.nomecidade+'"><option>Cascavel</option></select></div></form><hr><button type="button" class="btn btn-warning">Salvar <span class="fa fa-save" aria-hidden="true"></span></button>');  
        }
    //retira o painel loading
    $(".painelCarregando").fadeOut(400);
    });
}

//busca no servidor dados do agricultor para ser apresentado em um modal
function carregaUsuario(idClicado, opcao){
    var Sessao = getSessao();
    var envio = {
        metodo: "buscarusuario",
        idpessoa: idClicado,
        id: Sessao.idpessoa,
        sessao: Sessao.sessao
    };
    
    //chama a requisicao do servidor, o resultado é listado em uma tabela
    requisicao("pessoa/buscar", envio, function(dadosRetorno) {
        //testa qual opcao sera, editar ou mostrar
        if(opcao === "modal"){
            //carrega atributos no painel modal que sera exibido
            $("#modalTitulo").text("Usuário");
            $("#itensModal").empty().append("<h3>"+dadosRetorno.nome+"</h3><h4> Sobrenome: "+dadosRetorno.sobrenome+"</h4><h4>Apelido: "+dadosRetorno.apelido+"</h4><h4> CPF: "+dadosRetorno.cpf+"</h4><h4> RG: "+dadosRetorno.rg+"</h4><h4> Data de nascimento: "+dadosRetorno.datanascimento+"</h4><h4> Sexo: "+dadosRetorno.sexo+"</h4><h4> Telefone1: "+dadosRetorno.telefone1+"</h4><h4>Telefone2"+dadosRetorno.telefone2+"</h4><h4>Email: "+dadosRetorno.email+"</h4><h4>Estado civil: "+dadosRetorno.estadocivil+"</h4><h4>Escolaridade: "+dadosRetorno.escolaridade+"</h4>");
        }else if(opcao === "editar"){
            $("#divItens").empty().append('<h2 class="sub-header">Editar agricultor</h2><form><div class="form-group"><label for="nomeUsuario" class="control-label">Nome:</label><input type="text" class="form-control" id="nomeUsuario" placeholder="Digite o nome do agricultor..." value="'+dadosRetorno.nome+'"></div><div class="form-group"><label for="sobrenomeUsuario" class="control-label">Sobrenome:</label><input type="text" class="form-control" id="sobrenomeUsuario" placeholder="Digite o sobrenome do agricultor..." value="'+dadosRetorno.sobrenome+'"></div><div class="form-group"><label for="rgAgricultor" class="control-label">RG:</label><input type="text" class="form-control" id="rgAgricultor" placeholder="Digite o rg do agricultor..." value="'+dadosRetorno.rg+'"></div><div class="form-group"><label for="cpfAgricultor" class="control-label">CPF:</label><input type="text" class="form-control" id="cpfAgricultor" placeholder="Digite o cpf do agricultor..." value="'+dadosRetorno.cpf+'" ></div><div class="form-group"><label for="apelidoAgricultor" class="control-label">Apelido:</label><input type="text" class="form-control" id="apelidoAgricultor" placeholder="Digite o apelido do agricultor..." value="'+dadosRetorno.apelido+'" ></div><div class="form-group"><label for="dataNascAgricultor" class="control-label">Data de nascimento:</label><input type="date" class="form-control" id="dataNascAgricultor" placeholder="Digite a data de nascimento do agricultor..." value="'+dadosRetorno.datanascimento+'" ></div><input type="radio" name="genero" value="m" checked> Masculino<br><input type="radio" name="genero" value="f"> Feminino<br> <br><div class="form-group"><label for="telefone1Agricultor" class="control-label">Telefone 1:</label><input type="text" class="form-control" id="telefone1Agricultor" placeholder="Digite o telefone 1 do agricultor..." value="'+dadosRetorno.telefone1+'" ></div><div class="form-group"><label for="telefone2Agricultor" class="control-label">Telefone 2:</label><input type="text" class="form-control" id="telefone2Agricultor" placeholder="Digite o telefone 2 do agricultor..." value="'+dadosRetorno.telefone2+'" ></div><div class="form-group"><label for="emailAgricultor" class="control-label">Email:</label><input type="text" class="form-control" id="emailAgricultor" placeholder="Digite o email do agricultor..." value="'+dadosRetorno.email+'" ></div><div class="form-group"><label for="escolaridadeAgricultor" class="control-label">Escolaridade:</label><select class="form-control " id="escolaridadeAgricultor" value="'+dadosRetorno.escolaridade+'"><option>Ensino fundamental</option><option>Ensino fundamental incompleto</option><option>Ensino médio</option><option>Ensino médio incompleto</option><option>Ensino superior</option><option>Ensino superior incompleto</option><option>Pós-graduação</option><option>Pós-graduação incompleta</option><option>Sem escolaridade</option></select></div><div class="form-group"><label for="estadocivilAgricultor" class="control-label">Estado civil:</label><select class="form-control " id="estadocivilAgricultor" value="'+dadosRetorno.estadocivil+'"><option>Solteiro(a)</option><option>Casado(a)</option><option>Divorciado(a)</option><option>Viúvo(a)</option><option>Separado(a)</option><option>Companheiro(a)</option></select></div></form><hr><button type="button" class="btn btn-warning">Salvar <span class="fa fa-save" aria-hidden="true"></span></button>');
        }
    //retira o painel loading
    $(".painelCarregando").fadeOut(400);
    });
}

    
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
        $("#identificacaoPage").empty().append('<spam class="fa '+icone+'" aria-hidden="true">'+titulo+'</spam>');
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
        //$("#pageDistribuir").show();
        
        $(".a").css("background", "rgba(255,255,255,0.6)");
        $(itemSelecionado).css("background", "#FFCC00");
        $("#identificacaoPage").empty().append('<spam class="fa '+icone+'" aria-hidden="true">'+titulo+'</spam>');
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

//testa qual aba esta e chama a função correspondente
function testeListar(){
      
    //lista conforme a pagina correspondente
    switch (verificaPagina()){
        case 1:
            listarAgricultores();  
        break;
        case 2:
            listarCultivares();    
        break;
        case 3:
            listarUnidades();
        break;
        case 4:
            listarUsuarios();
        break;
        default:
            alerta("Erro de requisição de navegação!"); 
        
    }
      
}

//novo
$(document).on("click", "#novo", function(evt)
{
   
    $("#divItens").fadeOut(400, function(){
    switch (verificaPagina()){
        case 1:           
            
            //Adicionar agricultor, limpa o progresso e adiciona os itens
            $("#tituloProgresso").text("Novo agricultor");
            $("#progressoRef").text("Dados pessoais");
            $("#qtdProgresso").empty().append('<li role="presentation" class="active"><a href="#step1" data-toggle="tab" aria-controls="step1" role="tab" title="Dados pessoais"><span class="round-tab"><i class="fa fa-address-card-o"></i></span></a></li>\n\
<li role="presentation" class="disabled"><a href="#step2" data-toggle="tab" aria-controls="step2" role="tab" title="Dados familiares"><span class="round-tab"><i class="fa fa-child"></i></span></a></li>\n\
<li role="presentation" class="disabled"><a href="#step3" data-toggle="tab" aria-controls="step3" role="tab" title="Dados da propriedade"><span class="round-tab"><i class="fa fa-home"></i></span></a></li>\n\
<li role="presentation" class="disabled"><a href="#complete" data-toggle="tab" aria-controls="complete" role="tab" title="Salvar"><span class="round-tab"><i class="fa fa-save"></i></span></a></li>');
            $("#formProgresso").empty().append('<div class="tab-pane active" role="tabpanel" id="step1"><div class="form-group"><label for="nomeAgricultor" class="control-label">Nome:</label><input type="text" class="form-control inputsDadosPessoais" id="nomeAgricultor" placeholder="Digite o nome do agricultor..." ></div><div class="form-group"><label for="sobrenomeAgricultor" class="control-label">Sobrenome:</label><input type="text" class="form-control inputsDadosPessoais" id="sobrenomeAgricultor" placeholder="Digite o sobrenome do agricultor..." ></div><div class="form-group"><label for="rgAgricultor" class="control-label">RG:</label><input type="text" class="form-control inputsDadosPessoais" id="rgAgricultor" placeholder="Digite o rg do agricultor..."></div><div class="form-group"><label for="cpfAgricultor" class="control-label">CPF:</label><input type="text" class="form-control" id="cpfAgricultor" placeholder="Digite o cpf do agricultor..." ></div><div class="form-group"><label for="apelidoAgricultor" class="control-label">Apelido:</label><input type="text" class="form-control" id="apelidoAgricultor" placeholder="Digite o apelido do agricultor..." ></div><div class="form-group"><label for="dataNascAgricultor" class="control-label">Data de nascimento:</label><input type="date" class="form-control" id="dataNascAgricultor" placeholder="Digite a data de nascimento do agricultor..." ></div><input type="radio" name="genero" value="m" checked> Masculino<br><input type="radio" name="genero" value="f"> Feminino<br> <br><div class="form-group"><label for="telefone1Agricultor" class="control-label">Telefone 1:</label><input type="text" class="form-control" id="telefone1Agricultor" placeholder="Digite o telefone 1 do agricultor..." ></div><div class="form-group"><label for="telefone2Agricultor" class="control-label">Telefone 2:</label><input type="text" class="form-control" id="telefone2Agricultor" placeholder="Digite o telefone 2 do agricultor..." ></div><div class="form-group"><label for="emailAgricultor" class="control-label">Email:</label><input type="text" class="form-control" id="emailAgricultor" placeholder="Digite o email do agricultor..." ></div><div class="form-group"><label for="escolaridadeAgricultor" class="control-label">Escolaridade:</label><select class="form-control " id="escolaridadeAgricultor"><option>Ensino fundamental</option><option>Ensino fundamental incompleto</option><option>Ensino médio</option><option>Ensino médio incompleto</option><option>Ensino superior</option><option>Ensino superior incompleto</option><option>Pós-graduação</option><option>Pós-graduação incompleta</option><option>Sem escolaridade</option></select></div><div class="form-group"><label for="estadocivilAgricultor" class="control-label">Estado civil:</label><select class="form-control " id="estadocivilAgricultor"><option>Solteiro(a)</option><option>Casado(a)</option><option>Divorciado(a)</option><option>Viúvo(a)</option><option>Separado(a)</option><option>Companheiro(a)</option></select></div><ul class="list-inline pull-right"><li><button type="button" class="btn btn-warning next-step" id="continuarDadosPessoais">Continuar</button></li></ul></div>\n\
<div class="tab-pane" role="tabpanel" id="step2"><div class="form-group"><label for="qtdIntegrantes" class="control-label">Quantidade de integrantes na família:</label><input type="number" class="form-control inputsDadosFamilia" id="qtdIntegrantes" placeholder="Digite a quantidade de integrantes..." min="0"></div><div class="form-group"><label for="qtdCriancas" class="control-label">Quantidade de crianças na família:</label><input type="number" class="form-control inputsDadosFamilia" id="qtdCriancas" placeholder="Digite a quantidade de crianças..." min="0"></div><div class="form-group"><label for="qtdGravidas" class="control-label">Quantidade de grávidas na familia:</label><input type="number" class="form-control inputsDadosFamilia" id="qtdGravidas" placeholder="Digite a quantidade de grávidas..." min="0"></div><ul class="list-inline pull-right"><li><button type="button" class="btn btn-warning prev-step">Voltar</button></li><li><button type="button" class="btn btn-warning next-step" id="continuarDadosFamilia">Continuar</button></li></ul></div>\n\
<div class="tab-pane" role="tabpanel" id="step3"><div class="form-group"><div class="form-group"><label for="nomePropriedade" class="control-label">Nome da propriedade:</label><input type="text" class="form-control" id="nomePropriedade" placeholder="Digite o nome da propriedade..." ></div><label for="rua" class="control-label">Rua:</label><input type="text" class="form-control" id="rua" placeholder="Digite o nome da rua..." ></div><div class="form-group"><label for="numero" class="control-label">Número:</label><input type="number" min="0" class="form-control" id="numero" placeholder="Digite o número..." ></div><div class="form-group"><label for="bairro" class="control-label">Bairro:</label><input type="text" class="form-control" id="bairro" placeholder="Digite o nome do bairro..." ></div><div class="form-group"><label for="complemento" class="control-label">Complemento:</label><input type="text" class="form-control" id="complemento" placeholder="Digite o complemento..." ></div><div class="form-group"><label for="paisAgricultor" class="control-label">País:</label><select class="form-control " id="paisAgricultor"><option></option><option>Brasil</option></select></div><div class="form-group"><label for="estadoAgricultor" class="control-label">Estado:</label><select class="form-control " id="estadoAgricultor"><option></option><option>Paraná</option></select></div><div class="form-group"><label for="cidadeAgricultor" class="control-label">Cidade:</label><select class="form-control " id="cidadeAgricultor"><option></option><option>Cascavel</option></select></div><div class="form-group"><label for="unidade" class="control-label">Unidade:</label><select class="form-control " id="unidade"><option></option><option>Fundetec</option></select></div><div class="form-group"><label for="area" class="control-label">Área da propriedade:</label><input type="number" min="0" class="form-control" id="area" placeholder="Digite a área..." ></div><div class="form-group"><label for="unidademedida" class="control-label">Unidade de medida:</label><select class="form-control " id="unidademedida"><option>Metros quadrados</option><option>Alqueire</option><option>Hectare</option></select></div><div class="form-group"><label for="au" class="control-label">Área utilizada:</label><input type="number" class="form-control" id="au" min="0" placeholder="Digite a área utilizada..." ></div><div class="form-group"><label for="unidademedidaau" class="control-label">Unidade de medida:</label><select class="form-control " id="unidademedidaau"><option>Metros quadrados</option><option>Alqueire</option><option>Hectare</option></select></div><ul class="list-inline pull-right"><li><button type="button" class="btn btn-warning prev-step">Voltar</button></li><li><button type="button" class="btn btn-warning btn-info-full next-step">Continuar e salvar</button></li></ul></div>\n\
<div class="tab-pane" role="tabpanel" id="complete"><h3>Completo</h3><p>Agricultor Cadastrado!</p></div><div class="clearfix"></div>');     
                
            //esconde os atalhos e aparece os icones progresso    
            $("#iconesProgresso").show();
            $("#atalhosCadastro").hide();
            
            //$("#iconesProgresso").append('<div class="row"><section><div class="wizard"><div class="wizard-inner"><div class="connecting-line"></div><h2 id="tituloProgresso"></h2><h4 id="progressoRef"></h4><ul class="nav nav-tabs" role="tablist" id="qtdProgresso"><li role="presentation" class="active"><a href="#step1" data-toggle="tab" aria-controls="step1" role="tab" title="Agricultor"><span class="round-tab"><i class="fa fa-user"></i></span></a></li><li role="presentation" class="disabled"><a href="#step2" data-toggle="tab" aria-controls="step2" role="tab" title="Cultivar"><span class="round-tab"><i class="fa fa-envira"></i></span></a></li><li role="presentation" class="disabled"><a href="#step3" data-toggle="tab" aria-controls="step3" role="tab" title="Quantidade"><span class="round-tab"><i class="fa fa-shopping-basket"></i></span></a></li><li role="presentation" class="disabled"><a href="#complete" data-toggle="tab" aria-controls="complete" role="tab" title="Distribuir"><span class="round-tab"><i class="fa fa-save"></i></span></a></li></ul></div><form role="form"><div class="tab-content" id="formProgresso" style="height: 600px; overflow-y: auto; overflow-x: hidden;"><div class="tab-pane active" role="tabpanel" id="step1"><h3>Agricultor</h3><div class="form-group"><label for="dadosAgricultor" class="control-label">Nome ou CPF ou RG...</label><input type="text" class="form-control" id="dadosAgricultor" placeholder="Digite os dados do agricultor..."></div><ul class="list-inline pull-right"><li><button type="button" class="btn btn-warning next-step">Continuar</button></li></ul></div><div class="tab-pane" role="tabpanel" id="step2"><h3>Cultivar</h3><div class="form-group"><label for="cultivares" class="control-label">Selecione um cultivar...</label><select class="form-control " id="cultivares" ><option>Batata doce</option><option>Mandioca</option><option>Milho</option><option>Trigo</option><option>Feijão</option></select></div><ul class="list-inline pull-right"><li><button type="button" class="btn btn-warning prev-step">Voltar</button></li><li><button type="button" class="btn btn-warning next-step">Continuar</button></li></ul></div><div class="tab-pane" role="tabpanel" id="step3"><h3>Quantidade</h3><div class="form-group"><label for="qtd" class="control-label">Informe a quantidade...</label><input type="number" class="form-control" id="qtd" placeholder="Digite a quantidade..." min="0"></div><div class="form-group"><label for="umCultivar" class="control-label">Selecione uma unidade de medida...</label><select class="form-control " id="umCultivar"><option>Kilo(s)</option><option>Maniva(s)</option><option>Rama(s)</option></select></div><ul class="list-inline pull-right"><li><button type="button" class="btn btn-warning prev-step">Voltar</button></li><li><button type="button" class="btn btn-warning next-step">Continuar</button></li><li><button type="button" class="btn btn-warning btn-info-full next-step">Continuar e salvar</button></li></ul></div><div class="tab-pane" role="tabpanel" id="complete"><h3>Completo</h3><p>A distribuição foi bem sucedida!</p></div><div class="clearfix"></div></div></form></div></section></div>');
            
        break;
        case 2:
            $("#divItens").empty().append('<h2 class="sub-header">Novo cultivar</h2><form><div class="form-group"><label for="nomeCultivar" class="control-label">Nome cultivar:</label><input type="text" class="form-control" id="nomeCultivar" placeholder="Digite o nome do cultivar..."></div><div class="form-group"><label for="umNovoCultivar" class="control-label">Unidade de medida:</label><select class="form-control " id="umNovoCultivar"><option value="7">Kilo(s)</option><option value="6">Maniva(s)</option><option value="5">Rama(s)</option></select></div><div class="form-group"><label for="pesoSaca" class="control-label">Peso da saca(kg):</label><input type="text" class="form-control" id="pesoSaca" placeholder="Digite o peso da saca..."></div><div class="form-group"><label for="tempoColheita" class="control-label">Dias para relatar a colheita:</label><input type="number" class="form-control" min="1" id="tempoColheita" placeholder="Digite o tempo de colheita..."></div><div class="form-group"><label for="tempoDestinacao" class="control-label">Dias para relatar a destinação:</label><input type="number" class="form-control" min="1" id="tempoDestinacao" placeholder="Digite o tempo da destinação..."></div><div class="form-group"><label for="valorNutricional" class="control-label">Valor nutricional</label><textarea class="form-control" id="valorNutricional" placeholder="Digite o valor nutricional ..."></textarea></div><div class="form-group"><label for="descNovoCultivar" class="control-label">Descrição:</label><textarea class="form-control" id="descNovoCultivar" placeholder="Digite uma descrição..."></textarea></div><div class="input-group"><input type="checkbox" aria-label="..." id="bioCheck" checked="">&nbsp;<label for="bioCheck" class="control-label">Biofortificado</label></div><br><label class="control-label">Selecione uma imagem:</label><input id="imgNovoCultivar" type="file" class="" accept="image/jpeg, image/png"><img id="imgCultivarCarregada" class="hidden img-responsive" src="" width="180" height="180" alt="imgCultivar"/></form><hr><button type="button" class="btn btn-warning" id="salvarNovoCultivar">Salvar <span class="fa fa-save" aria-hidden="true"></span></button>');
        break;
        case 3:
            $("#divItens").empty().append('<h2 class="sub-header">Nova unidade</h2><form><div class="form-group"><label for="nomeUnidade" class="control-label">Nome unidade:</label><input type="text" class="form-control" id="nomeUnidade" placeholder="Digite o nome da unidade..."></div><div class="form-group"><label for="cnpj" class="control-label">CNPJ:</label><input type="text" class="form-control" id="cnpj" placeholder="Digite o cnpj..."></div><div class="form-group"><label for="razaoSocial" class="control-label">Razão social:</label><input type="text" class="form-control" id="razaoSocial" placeholder="Digite a razão social..."></div><div class="form-group"><label for="telefone1" class="control-label">Telefone 1:</label><input type="text" class="form-control" id="telefone1" placeholder="Digite o telefone 1..."></div><div class="form-group"><label for="telefone2" class="control-label">Telefone 2:</label><input type="text" class="form-control" id="telefone2" placeholder="Digite o telefone 2..."></div><div class="form-group"><label for="email" class="control-label">Email:</label><input type="text" class="form-control" id="email" placeholder="Digite o email..."></div><div class="form-group"><label for="rua" class="control-label">Rua:</label><input type="text" class="form-control" id="rua" placeholder="Digite a rua..."></div><div class="form-group"><label for="bairro" class="control-label">Bairro:</label><input type="text" class="form-control" id="bairro" placeholder="Digite o bairro..."></div><div class="form-group"><label for="numero" class="control-label">Número:</label><input type="text" class="form-control" id="numero" placeholder="Digite o número..."></div><div class="form-group"><label for="complemento" class="control-label">Complemento:</label><input type="text" class="form-control" id="complemento" placeholder="Digite o complemento..."></div><div class="form-group"><label for="gps_lat" class="control-label">Latitude:</label><input type="text" class="form-control" id="gps_lat" placeholder="Digite a latitude..." value="0"></div><div class="form-group"><label for="gps_long" class="control-label">Longitude:</label><input type="text" class="form-control" id="gps_long" placeholder="Digite a longitude..." value="0"></div><div class="form-group"><label for="pais" class="control-label">País:</label><select class="form-control " id="pais"><option>Brasil</option></select></div><div class="form-group"><label for="estado" class="control-label">Estado:</label><select class="form-control " id="estado"><option>Paraná</option></select></div><div class="form-group"><label for="cidade" class="control-label">Cidade:</label><select class="form-control " id="cidade"><option>Cascavel</option></select></div></form><hr><button type="button" class="btn btn-warning" id="salvarNovaUnidade">Salvar <span class="fa fa-save" aria-hidden="true"></span></button>');
        break;
        case 4:
            $("#divItens").empty().append('<h2 class="sub-header">Novo usuário</h2><form><div class="form-group"><input type="checkbox" name="papel1" value="g"> Gerenciador <br><input type="checkbox" name="papel2" value="e"> Entrevistador <br><input type="checkbox" name="papel3" value="adm"> Administrador <br></div><div class="form-group"><label for="unidadeAtuacao" class="control-label">Unidade de atuação:</label><select class="form-control " id="unidadeAtuacao"><option>Fundetec</option></select></div><div class="form-group"><label for="nomeUsuario" class="control-label">Nome:</label><input type="text" class="form-control" id="nomeUsuario" placeholder="Digite o nome do usuário..." ></div><div class="form-group"><label for="sobrenomeUsuario" class="control-label">Sobrenome:</label><input type="text" class="form-control" id="sobrenomeUsuario" placeholder="Digite o sobrenome do usuário..." ></div><div class="form-group"><label for="rgUsuario" class="control-label">RG:</label><input type="text" class="form-control" id="rgUsuario" placeholder="Digite o rg do usuario..."></div><div class="form-group"><label for="cpfUsuario" class="control-label">CPF:</label><input type="text" class="form-control" id="cpfUsuario" placeholder="Digite o cpf do usuário..." ></div></div><div class="form-group"><label for="dataNascAgricultor" class="control-label">Data de nascimento:</label><input type="date" class="form-control" id="dataNascAgricultor" placeholder="Digite a data de nascimento do agricultor..." ></div><input type="radio" name="genero" value="m" checked> Masculino<br><input type="radio" name="genero" value="f"> Feminino<br> <br><div class="form-group"><label for="telefone1Agricultor" class="control-label">Telefone 1:</label><input type="text" class="form-control" id="telefone1Agricultor" placeholder="Digite o telefone 1 do agricultor..." ></div><div class="form-group"><label for="telefone2Agricultor" class="control-label">Telefone 2:</label><input type="text" class="form-control" id="telefone2Agricultor" placeholder="Digite o telefone 2 do agricultor..." ></div><div class="form-group"><label for="emailAgricultor" class="control-label">Email:</label><input type="text" class="form-control" id="emailAgricultor" placeholder="Digite o email do agricultor..." ></div><div class="form-group"><label for="escolaridadeAgricultor" class="control-label">Escolaridade:</label><select class="form-control " id="escolaridadeAgricultor"><option>Ensino fundamental</option><option>Ensino fundamental incompleto</option><option>Ensino médio</option><option>Ensino médio incompleto</option><option>Ensino superior</option><option>Ensino superior incompleto</option><option>Pós-graduação</option><option>Pós-graduação incompleta</option><option>Sem escolaridade</option></select></div><div class="form-group"><label for="estadocivilAgricultor" class="control-label">Estado civil:</label><select class="form-control " id="estadocivilAgricultor"><option>Solteiro(a)</option><option>Casado(a)</option><option>Divorciado(a)</option><option>Viúvo(a)</option><option>Separado(a)</option><option>Companheiro(a)</option></select></div></form><hr><button type="button" class="btn btn-warning">Salvar <span class="fa fa-save" aria-hidden="true"></span></button>');
        break;
        default:
            alerta("Erro de requisição de navegação!");
        
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

        //teste se a tabela com a lista esta visivel
        if(!tabela.is(':visible')){
            testeListar();
        }else{
            var marcado = false;
            var envio;
            tabela.find('tr').each(function (){
                if($(this).hasClass("warning")){
                    //editar conforme a pagina correspondente
                    switch (verificaPagina()){
                        case 1:
                            carregaAgricultor($(this).find('td:eq(0)').html(), "editar");
                        break;
                        case 2:
                            carregaCultivar($(this).find('td:eq(0)').html(), "editar");
                        break;
                        case 3:
                            carregaUnidade($(this).find('td:eq(0)').html(), "editar");
                        break;
                        case 4:
                            carregaUsuario($(this).find('td:eq(0)').html(), "editar");
                        break;
                        default:
                        alerta("Erro em carregar!");
                    }
                    
//                    
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

function verificaPagina(){
     if($("#identificacaoPage > spam").text() === "Agricultores"){
        return 1;
    }else if($("#identificacaoPage > spam").text() === "Cultivares"){
        return 2;
    }else if($("#identificacaoPage > spam").text() === "Unidades"){
        return 3;
    }else if($("#identificacaoPage > spam").text() === "Usuários"){
        return 4;
    }
}


////////listar na tabela////////
//agricultores
function listarAgricultores(){
    var Sessao = getSessao();
    var envio = {
        metodo: "listaragricultores",
        id: Sessao.idpessoa,
        sessao: Sessao.sessao
    };
    
    //chama a requisicao do servidor, o resultado é listado em uma tabela
    requisicao("pessoa/listar", envio, function(dadosRetorno) {
 
        var item = "";

        $.each(dadosRetorno, function(i, valor){
            item += "<tr><td>"+valor.idpessoa+"</td><td>"+valor.nome+"</td><td>"+valor.sobrenome+"</td><td>"+valor.rg+"</td><td>"+valor.cpf+"</td><td>"+valor.telefone1+"</td><td>"+valor.nomeunidade+"</td></tr>";

        });

        $("#divItens").fadeOut(400, function(){

            $("#divItens").empty().append('<h2 class="sub-header">Lista de agricultores</h2><div class="table-responsive"><table class="table table-hover"><thead><tr><th>ID</th><th>Nome</th><th>Sobrenome</th><th>RG</th><th>CPF</th><th>Telefone</th><th>Unidade</th></tr></thead><tbody>'+item+'</tbody></table></div>');
            $("#divItens").fadeIn(400);
            $(".painelCarregando").fadeOut(400);
        });
    
     //retira o painel loading
    $(".painelCarregando").fadeOut(400);
    });
}

//listar os cultivares em uma tabela
function listarCultivares(){
    
    var Sessao = getSessao();
    var envio = {
        metodo: "listarcultivares",
        id: Sessao.idpessoa,
        sessao: Sessao.sessao
    };
    
    //chama a requisicao do servidor, o resultado é listado em uma tabela
    requisicao("cultivar/listar", envio, function(dadosRetorno) {

        var item = "";

        $.each(dadosRetorno, function(i, valor){
            item += "<tr><td>"+valor.idcultivar+"</td><td>"+valor.nomecultivar+"</td><td>"+valor.grandeza+"</td></tr>";

        });

        $("#divItens").fadeOut(400, function(){

            $("#divItens").empty().append('<h2 class="sub-header">Lista de cultivares</h2><div class="table-responsive"><table class="table table-hover"><thead><tr><th>ID</th><th>Nome do cultivar</th><th>Unidade de medida</th></tr></thead><tbody>'+item+'</tbody></table></div>');
            $("#divItens").fadeIn(400);
            $(".painelCarregando").fadeOut(400); 
        });

    //retira o painel loading
    $(".painelCarregando").fadeOut(400);
    });
        
}

//listar unidades em uma tabela
function listarUnidades(){
    var Sessao = getSessao();
    var envio = {
        id: Sessao.idpessoa,
        sessao: Sessao.sessao
    };
    
    //chama a requisicao do servidor, o resultado é listado em uma tabela
    requisicao("unidade/listar", envio, function(dadosRetorno) {
 
        var item = "";

        $.each(dadosRetorno, function(i, valor){
            item += "<tr><td>"+valor.idunidade+"</td><td>"+valor.nomeunidade+"</td><td>"+valor.telefone1+"</td><td>"+valor.telefone2+"</td><td>"+valor.email+"</td><td>"+valor.nomepais+"</td><td>"+valor.nomeestado+"</td><td>"+valor.nomecidade+"</td></tr>";

        });

        $("#divItens").fadeOut(400, function(){

            $("#divItens").empty().append('<h2 class="sub-header">Lista de unidades</h2><div class="table-responsive"><table class="table table-hover"><thead><tr><th>ID</th><th>Nome da Unidade</th><th>Telefone1</th><th>Telefone2</th><th>E-mail</th><th>País</th><th>Estado</th><th>Cidade</th></tr></thead><tbody>'+item+'</tbody></table></div>');
            $("#divItens").fadeIn(400);
            $(".painelCarregando").fadeOut(400);
        });
    
    //retira o painel loading
    $(".painelCarregando").fadeOut(400);
    });
}

//listar usuarios
function listarUsuarios(){

    var Sessao = getSessao();
    var envio = {
        metodo: "listarusuarios",
        id: Sessao.idpessoa,
        sessao: Sessao.sessao
    };
    
    //chama a requisicao do servidor, o resultado é listado em uma tabela
    requisicao("pessoa/listar", envio, function(dadosRetorno) {

        var item = "";

        $.each(dadosRetorno, function(i, valor){
            item += "<tr><td>"+valor.idpessoa+"</td><td>"+valor.nome+"</td><td>"+valor.sobrenome+"</td><td>"+valor.rg+"</td><td>"+valor.cpf+"</td><td>"+valor.telefone1+"</td><td>"+valor.nomeunidade+"</td></tr>";

        });

        $("#divItens").fadeOut(400, function(){

            $("#divItens").empty().append('<h2 class="sub-header">Lista de usuários</h2><div class="table-responsive"><table class="table table-hover"><thead><tr><th>ID</th><th>Nome</th><th>Sobrenome</th><th>RG</th><th>CPF</th><th>Telefone</th><th>Unidade</th></tr></thead><tbody>'+item+'</tbody></table></div>');
            $("#divItens").fadeIn(400);
            $(".painelCarregando").fadeOut(400);
        });
        
    //retira o painel loading
    $(".painelCarregando").fadeOut(400);
    });
}


//function requisicao(url, envio, metodo){
function requisicao(url, envio, callback) {
  
    $(".painelCarregando").fadeIn(400);
        
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
                updateSessao(retorno.sessao);
                callback(retorno.data);
            },
            error: function() {
                $(".painelCarregando").fadeOut(400);
                alerta("Sem conexão com o servidor!");
            }
        });
             
 
}

///marcacao da tabela lista de agricultores/ cultivares/ unidades/ usuarios

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



//botao salvar novo agricultor
$(document).on("click", "#salvarNovoAgricultor", function(){
   
    if($("#nomeAgricultor").val() === ""){
        $("#itenFocus").text("#nomeAgricultor");
        alerta("O campo nome não pode ser vazio!");
    }else if($("#sobrenomeAgricultor").val() === ""){
        $("#itenFocus").text("#sobrenomeAgricultor");
        alerta("O campo sobrenome não pode ser vazio!");
    }else if($("#rgAgricultor").val() === ""){
        $("#itenFocus").text("#rgAgricultor");
        alerta("O campo RG não pode ser vazio!");
    }else if($("#cpfAgricultor").val() === ""){
        $("#itenFocus").text("#cpfAgricultor");
        alerta("O campo CPF não pode ser vazio!");
    }else if($("#dataNascAgricultor").val() === ""){
        $("#itenFocus").text("#dataNascAgricultor");
        alerta("O campo data de nascimento não pode ser vazio!");
    }else if($("#telefone1Agricultor").val() === ""){
        $("#itenFocus").text("#telefone1Agricultor");
        alerta("O campo telefone1 não pode ser vazio!");
    }else{
        //var envio = "cidade_idcidade="++"&rua="++"&gps_lat="++"&gps_long="++"&bairro="++"&complemento="++"&cep="++"&numero="++"&escolaridade_idescolaridade="++"&estadocivil_idestadocivil="++"&nome="+$("#nomeAgricultor").val()+"&sobrenome="+$("#sobrenomeAgricultor").val()+"&apelido="++"&cpf="+$("#cpfAgricultor").val()+"&rg="+$("#rgAgricultor").val()+"&datanascimento="+$("#dataNascAgricultor").val()+"&sexo="++"&telefone1="+$("#telefone1Agricultor").val()+"&telefone2="+$("#telefone2Agricultor").val()+"&email="++"&qtdedeintegrantes="++"&qtdedecriancas="++"&qtdedegravidas="++"&usuario="++"&senha="++"&unidade_idunidade="++"&nomepropriedade="++"&area="++"&unidadedemedida="++"&areautilizavel="++"&unidadedemedidaau=";
        alerta("Evento em construção");
    }

    return false;
});


//botao salvar novo cultivar
$(document).on("click", "#salvarNovoCultivar", function(){
   
   
    if($("#nomeCultivar").val() === ""){
        $("#itenFocus").text("#nomeCultivar");
        
    }else if($("#tempoColheita").val() === ""){
        alertFocus("#tempoColheita", "dias para relatar a colheita");
    }else if($("#tempoDestinacao").val() === ""){
        alertFocus("#tempoDestinacao", "dias para relatar a destinação");
    }else if($("#valorNutricional").val() === ""){
        alertFocus("#valorNutricional", "valor nutricional");
    }else if($("#imgNovoCultivar").val() === ""){
        alertFocus("#imgNovoCultivar", "imagem");
    
    }else{
        var Sessao = getSessao();
        
        var envio = {nomecultivar: $("#nomeCultivar").val(),
            imagem: $("#imgCultivarCarregada").prop("src"),
            descricao: $("#descNovoCultivar").val(),
            biofortificado: JSON.stringify($("#bioCheck").is(':checked')),
            unidademedida_idunidademedida: $("#umNovoCultivar").val(),
            valornutricional: $("#valorNutricional").val(),
            tempodecolheita: $("#tempoColheita").val(),
            tempodestinacao: $("#tempoDestinacao").val(),
            pesoSaca: $("#pesoSaca").val(),
            id: Sessao.idpessoa,
            sessao: Sessao.sessao
        };

        requisicao("cultivar/inserir", envio, "inserirCultivar");

    }
    
    return false;
});


//conversao imagem em base64
function readImage(inputElement) {
    var deferred = $.Deferred();

    var files = inputElement.get(0).files;
    if (files && files[0]) {
        var fr= new FileReader();
        fr.onload = function(e) {
            deferred.resolve(e.target.result);
        };
        fr.readAsDataURL( files[0] );
    } else {
        deferred.resolve(undefined);
    }

    return deferred.promise();
}


//botao salvar novo cultivar
$(document).on("click", "#salvarNovaUnidade", function(){

    //$(".painelCarregando").fadeIn(400);

        teste();
    
    return false;
});



$(document).on("change", "#imgNovoCultivar", function(){
    
      
      readImage($("#imgNovoCultivar")).done(function(base64Data){
          
          $("#imgCultivarCarregada").attr("src", base64Data);   
          $("#imgCultivarCarregada").removeClass("hidden");   
      });
      
//    alert("a");
    return false;
});


////////////validacao de formulario/////////////////////////////////////////////////////////////////////////////////////////////////////////

$(document).ready(function () {
    //Initialize tooltips
    $('.nav-tabs > li a[title]').tooltip();
    
    //Wizard
    $('a[data-toggle="tab"]').on('show.bs.tab', function (e) {

        var $target = $(e.target);
    
        if ($target.parent().hasClass('disabled')) {
            return false;
        }
    });


    $(".prev-step").click(function (e) {

        var $active = $('.wizard .nav-tabs li.active');
        prevTab($active);

    });
});

function nextTab(elem) {
    $(elem).next().find('a[data-toggle="tab"]').click();
}
function prevTab(elem) {
    $(elem).prev().find('a[data-toggle="tab"]').click();
}




//cadastro de um novo agricultor parte de dados pessoais
$(document).on("click", "#continuarDadosPessoais", function(){
    
     verificarInput('.inputsDadosPessoais');
    
    return false;
});


//cadastro de um novo agricultor parte de dados familiares
$(document).on("click", "#continuarDadosFamilia", function(){
    
    verificarInput('.inputsDadosFamilia');
    
    return false;
});


function verificarInput(classeInputs){
    var continuar = true;
    $(classeInputs).each(function () {
        
            if($(this).val() === ""){
                $("#itenFocus").text("#"+ $(this).prop("id"));
                alerta("O campo "+ $(this).parent("div").children("label").text()+" não pode ser vazio!");
                continuar = false;  
                return false;
            }

    });

    if(continuar){

        var $active = $('.wizard .nav-tabs li.active');
        $active.next().removeClass('disabled');
        nextTab($active);


    }
}












