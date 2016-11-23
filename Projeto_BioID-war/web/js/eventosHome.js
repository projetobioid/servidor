var ipServidor = 'localhost:8080';


function getSessao(){
    var logSessao = JSON.parse(localStorage.getItem("logSessao"));
    return logSessao.sessao;
}

function updateSessao(novaSessao){
    var logSessao = JSON.parse(localStorage.getItem("logSessao"));
    logSessao.sessao = novaSessao;
    localStorage.setItem("logSessao", JSON.stringify(logSessao));
}








    
///////*painel lateral*////////////


//muda cor painel aonde esta a pagina carregada
function preEventosPaginas(pagina, titulo, descricaoListar, descricaoNovo, descricaoEditar, descricaoExcluir){

    
    
    if($("#msgInicio").is(':visible')) {
        $("#msgInicio").fadeOut(400, function(){
            $("#page").fadeIn(400);
            $(".a").css("background", "rgba(255,255,255,0.6)");
            $(pagina).css("background", "#FFCC00");
            $("#identificacaoPage").text(titulo);
            $("#listar h5").text(descricaoListar);
            $("#novo h5").text(descricaoNovo);
            $("#editar h5").text(descricaoEditar);
            $("#excluir h5").text(descricaoExcluir);
           // $("#tabelaLista").hide();
        });  
    }else{
        $("#page").fadeOut(400, function(){
            $("#page").fadeIn(400);
            $(".a").css("background", "rgba(255,255,255,0.6)");
            $(pagina).css("background", "#FFCC00");
            $("#identificacaoPage").text(titulo);
            $("#listar h5").text(descricaoListar);
            $("#novo h5").text(descricaoNovo);
            $("#editar h5").text(descricaoEditar);
            $("#excluir h5").text(descricaoExcluir);
            $(".cadastros").hide();
        });  
    }
    
    
}
    

//pagina agricultores
$(document).on("click", "#painelAgricultores", function(evt)
{
    $("#divItens").fadeOut(400);
        preEventosPaginas($(this), "Agricultores", "Listar todos os agricultores", "Adicionar um novo agricultor", "Editar informações de um agricultor", "Excluir um agricultor");
    

    return false;
});

//pagina cultivares
$(document).on("click", "#painelCultivares", function(evt)
{
    $("#divItens").fadeOut(400);
        preEventosPaginas($(this), "Cultivares", "Listar todos os cultivares", "Adicionar um novo cultivar", "Editar informações de um cultivar", "Excluir um cultivar");
    

    return false;
});

//pagina unidades
$(document).on("click", "#painelUnidades", function(evt)
{
    $("#divItens").fadeOut(400);
        preEventosPaginas($(this), "Unidades", "Listar todas as unidades", "Adicionar uma nova unidade", "Editar informações de uma unidade", "Excluir uma unidade");
    
    return false;
});

//pagina usuarios
$(document).on("click", "#painelUsuarios", function(evt)
{
    $("#divItens").fadeOut(400);
        preEventosPaginas($(this), "Usuários", "Listar todos os usuários", "Adicionar um novo usuário", "Editar um usuário", "Excluir um usuário");
    
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
    
    if($("#identificacaoPage").text() === "Agricultores"){
        requisicao("pessoa/listaragricultores", 'sessao='+getSessao());
    }else if($("#identificacaoPage").text() === "Cultivares"){
        requisicao("cultivar/listar", 'sessao='+getSessao());
    }else if($("#identificacaoPage").text() === "Unidades"){
        requisicao("unidade/listar", 'sessao='+getSessao());
    }else if($("#identificacaoPage").text() === "Usuários"){
        listarUsuarios();
    }else{
        alert("Erro de requisição de navegação!");
    }
}

//novo
$(document).on("click", "#novo", function(evt)
{
   
    $("#divItens").fadeOut(400, function(){
 
        if($("#identificacaoPage").text() === "Agricultores"){
            $("#divItens").empty().append('<h2 class="sub-header">Novo agricultor</h2><form><div class="form-group"><label for="nomeNovoAgricultor" class="control-label">Nome:</label><input type="text" class="form-control" id="nomeNovoAgricultor" placeholder="Digite o nome do agricultor..."></div><div class="form-group"><label for="sobrenomeNovoAgricultor" class="control-label">Sobrenome:</label><input type="text" class="form-control" id="sobrenomeNovoAgricultor" placeholder="Digite o sobrenome do agricultor..."></div><div class="form-group"><label for="rgNovoAgricultor" class="control-label">RG:</label><input type="text" class="form-control" id="rgNovoAgricultor" placeholder="Digite o rg do agricultor..."></div><div class="form-group"><label for="cpfNovoAgricultor" class="control-label">CPF:</label><input type="text" class="form-control" id="cpfNovoAgricultor" placeholder="Digite o rg do agricultor..."></div></form><hr><button type="button" class="btn btn-warning">Salvar <span class="fa fa-save" aria-hidden="true"></span></button>');
        }else if($("#identificacaoPage").text() === "Cultivares"){
            $("#divItens").empty().append('<h2 class="sub-header">Novo cultivar</h2><form><div class="form-group"><label for="nomeNovoCultivar" class="control-label">Nome cultivar:</label><input type="text" class="form-control" id="nomeNovoCultivar" placeholder="Digite o nome do cultivar..."></div><div class="form-group"><label for="umNovoCultivar" class="control-label">Unidade de medida:</label><select class="form-control " id="umNovoCultivar"><option>Kilo(s)</option><option>Maniva(s)</option><option>Rama(s)</option></select></div><div class="form-group"><label for="pesoSaca" class="control-label">Peso da saca:</label><input type="text" class="form-control" id="pesoSaca" placeholder="Digite o peso da saca..."></div><div class="form-group"><label for="tempoColheita" class="control-label">Dias para relatar a colheita:</label><input type="number" class="form-control" min="1" id="tempoColheita" placeholder="Digite o tempo de colheita..."></div><div class="form-group"><label for="tempoDestinacao" class="control-label">Dias para relatar a destinação:</label><input type="number" class="form-control" min="1" id="tempoDestinacao" placeholder="Digite o tempo da destinação..."></div><div class="form-group"><label for="valorNutricional" class="control-label">Valor nutricional</label><textarea class="form-control" id="valorNutricional" placeholder="Digite o valor nutricional ..."></textarea></div><div class="form-group"><label for="descNovoCultivar" class="control-label">Descrição:</label><textarea class="form-control" id="descNovoCultivar" placeholder="Digite uma descrição..."></textarea></div><div class="input-group"><input type="checkbox" aria-label="..." id="bio" checked="">&nbsp;<label for="bio" class="control-label">Biofortificado</label></div><br><label class="control-label">Selecione uma imagem:</label><input id="imgNovoCultivar" type="file" class=""><img id="imgCultivarCarregada" class="hidden img-responsive" src="" width="180" height="180" alt="imgCultivar"/></form><hr><button type="button" class="btn btn-warning">Salvar <span class="fa fa-save" aria-hidden="true"></span></button>');
        }else if($("#identificacaoPage").text() === "Unidades"){
            $("#divItens").empty().append();
        }else if($("#identificacaoPage").text() === "Usuários"){
            $("#divItens").empty().append();
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
        
        if(!tabela.is(':visible')){
            $("#Alertar").fadeIn(400).children("span").text("Selecione um item para editar.");
            testeListar();
        }else{
            var marcado = false;
            tabela.find('tr').each(function (){
                if($(this).hasClass("warning")){

                    if($("#identificacaoPage").text() === "Agricultores"){
                        //requisicao("pessoa/listaragricultores", 'sessao='+getSessao());
                    }else if($("#identificacaoPage").text() === "Cultivares"){
                        requisicao("cultivar/buscar", "nomecultivar="+$(this).find('td:eq(1)').html()+"&biofortificado=true&sessao="+getSessao());
                    }else if($("#identificacaoPage").text() === "Unidades"){
                        //requisicao("unidade/listar", 'sessao='+getSessao());
                    }else if($("#identificacaoPage").text() === "Usuários"){
                        //listarUsuarios();
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
    
        $("#divItens").empty().append('<h2 class="sub-header">Agricultores</h2><div class="table-responsive"><table class="table table-hover"><thead><tr><th>ID</th><th>Nome</th><th>Sobrenome</th><th>RG</th><th>CPF</th><th>Telefone</th><th>Unidade</th></tr></thead><tbody>'+item+'</tbody></table></div>');
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
    
        $("#divItens").empty().append('<h2 class="sub-header">Cultivares</h2><div class="table-responsive"><table class="table table-hover"><thead><tr><th>ID</th><th>Nome do cultivar</th><th>Unidade de medida</th></tr></thead><tbody>'+item+'</tbody></table></div>');
        $("#divItens").fadeIn(400);
        
    });
}

//listar unidades
function listarUnidades(dadosRetorno){
    
    var item = "";

    $.each(dadosRetorno, function(i, valor){
        item += "<tr><td>"+valor.idunidade+"</td><td>"+valor.nomeunidade+"</td><td>"+valor.telefone1+"</td><td>"+valor.email+"</td><td>"+valor.nomeestado+"</td><td>"+valor.nomecidade+"</td></tr>";

    });

    $("#divItens").fadeOut(400, function(){
    
        $("#divItens").empty().append('<h2 class="sub-header">Unidades</h2><div class="table-responsive"><table class="table table-hover"><thead><tr><th>ID</th><th>Nome da Unidade</th><th>Telefone</th><th>E-mail</th><th>Estado</th><th>Cidade</th></tr></thead><tbody>'+item+'</tbody></table></div>');
        $("#divItens").fadeIn(400);
        
    });
}

//editar
function editar(dadosRetorno){
    $("#divItens").fadeOut(400, function(){
        
   
        if($("#identificacaoPage").text() === "Agricultores"){
            $("#divItens").empty().append('<h2 class="sub-header">Novo agricultor</h2><form><div class="form-group"><label for="nomeNovoAgricultor" class="control-label">Nome:</label><input type="text" class="form-control" id="nomeNovoAgricultor" placeholder="Digite o nome do agricultor..."></div><div class="form-group"><label for="sobrenomeNovoAgricultor" class="control-label">Sobrenome:</label><input type="text" class="form-control" id="sobrenomeNovoAgricultor" placeholder="Digite o sobrenome do agricultor..."></div><div class="form-group"><label for="rgNovoAgricultor" class="control-label">RG:</label><input type="text" class="form-control" id="rgNovoAgricultor" placeholder="Digite o rg do agricultor..."></div><div class="form-group"><label for="cpfNovoAgricultor" class="control-label">CPF:</label><input type="text" class="form-control" id="cpfNovoAgricultor" placeholder="Digite o rg do agricultor..."></div></form><hr><button type="button" class="btn btn-warning">Salvar <span class="fa fa-save" aria-hidden="true"></span></button>');
            
        }else if($("#identificacaoPage").text() === "Cultivares"){
            $("#divItens").empty().append('<h2 class="sub-header">Novo cultivar</h2><form><div class="form-group"><label for="nomeNovoCultivar" class="control-label">Nome cultivar:</label><input type="text" class="form-control" id="nomeNovoCultivar" placeholder="Digite o nome do cultivar..."></div><div class="form-group"><label for="umNovoCultivar" class="control-label">Unidade de medida:</label><select class="form-control " id="umNovoCultivar"><option>Kilo(s)</option><option>Maniva(s)</option><option>Rama(s)</option></select></div><div class="form-group"><label for="pesoSaca" class="control-label">Peso da saca:</label><input type="text" class="form-control" id="pesoSaca" placeholder="Digite o peso da saca..."></div><div class="form-group"><label for="tempoColheita" class="control-label">Dias para relatar a colheita:</label><input type="number" class="form-control" min="1" id="tempoColheita" placeholder="Digite o tempo de colheita..."></div><div class="form-group"><label for="tempoDestinacao" class="control-label">Dias para relatar a destinação:</label><input type="number" class="form-control" min="1" id="tempoDestinacao" placeholder="Digite o tempo da destinação..."></div><div class="form-group"><label for="valorNutricional" class="control-label">Valor nutricional</label><textarea class="form-control" id="valorNutricional" placeholder="Digite o valor nutricional ..."></textarea></div><div class="form-group"><label for="descNovoCultivar" class="control-label">Descrição:</label><textarea class="form-control" id="descNovoCultivar" placeholder="Digite uma descrição..."></textarea></div><div class="input-group"><input type="checkbox" aria-label="..." id="bio" checked="">&nbsp;<label for="bio" class="control-label">Biofortificado</label></div><br><label class="control-label">Selecione uma imagem:</label><input id="imgNovoCultivar" type="file" class=""><img id="imgCultivarCarregada" class="hidden img-responsive" src="" width="180" height="180" alt="imgCultivar"/></form><hr><button type="button" class="btn btn-warning">Salvar <span class="fa fa-save" aria-hidden="true"></span></button>');
            $("#nomeNovoCultivar").val(dadosRetorno.nomecultivar);
            $("#umNovoCultivar").val(dadosRetorno.grandeza);
            $("#pesoSaca").val(dadosRetorno.peso_saca);
            $("#tempoColheita").val(dadosRetorno.tempodecolheita);
            $("#tempoDestinacao").val(dadosRetorno.tempodestinacao);
            $("#valorNutricional").val(dadosRetorno.valornutricional);
            $("#descNovoCultivar").val(dadosRetorno.descricao);
            if(dadosRetorno.biofortificado){
                $("#bio").prop('checked', true);
            }else{
                $("#bio").prop('checked', false);
            }
            $("#imgCultivarCarregada").attr("src", dadosRetorno.imagem);
            $("#imgCultivarCarregada").removeClass("hidden");
        }else if($("#identificacaoPage").text() === "Unidades"){
            $("#divItens").empty().append();
        }else if($("#identificacaoPage").text() === "Usuários"){
            $("#divItens").empty().append();
        }else{
            alert("Erro de requisição de navegação!");
        }
        
    
        $("#divItens").fadeIn(400);
    });
    
}

function requisicao(url, data){
 
    var dadosRetorno = null;
    $.post("http://"+ipServidor+"/Projeto_BioID-war/servico/"+url, data, function(dados){
        
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
        if(url === "pessoa/listaragricultores"){
            listarAgricultores(dadosRetorno.listaAgricultores);
        }else if(url === "cultivar/listar"){
            listarCultivares(dadosRetorno.cultivares);
        }else if(url === "unidade/listar"){
            listarUnidades(dadosRetorno.unidades);
        }else if(url === "cultivar/buscar"){
            editar(dadosRetorno.cultivar);
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