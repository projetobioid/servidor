///////*painel lateral*////////////
////painel escolha de opcoes de abrir pages
$(document).on("click", ".a", function(evt)
{
    
    chamarMenu($(this));
    
    return false;
});


function chamarMenu(escolha){
    var item = escolha.text();
    
    switch(item){
        case "Agricultores":
            preEventosPaginaCadastros(escolha, "fa-users", "Agricultores", "Listar todos os agricultores", "Adicionar um novo agricultor", "Editar informações de um agricultor", "Excluir um agricultor");
            break;
        case "Distribuir cultivares":
            preEventosPaginaGerenciamento(escolha, "fa-cart-arrow-down", "Distribuir cultivares");
            break;
        case "Relatar safra":
            preEventosPaginaGerenciamento(escolha, "fa-commenting", "Relatar safra");
            break;
        default:
            alerta("Erro!","Erro na chamada de menu.");
            break;
    }
    
    
}
//function preEventosPaginaGerenciamento(itemSelecionado, pagina, icone, titulo){
function preEventosPaginaGerenciamento(itemSelecionado, icone, titulo){
    var item;
    
    if($("#msgInicio").is(':visible')) {
        item = "#msgInicio";
    }else{
        item = "#page";
    }
     
   
    
    $(item).fadeOut(400, function(){
        $(".paginas").hide();
        $("#identificacaoPage").show();
        var inputFocus;
        
        switch(titulo){
            case "Distribuir cultivares":
                inputFocus = progDistCultivares();
                break;
            case "Relatar safra":
//                escolhaRelatarSafra();
//                $("#divItens").empty().append('<div class="col-lg-5" style="text-align: center;"><span class="fa fa-cube" style="font-size: 80px;"></span><h2>Colheita</h2><p> Informacão da quantidade de colhida na propriedade através dos cultivares recebidos no programa biofort!</p></div>        <div class="col-lg-5" style="text-align: center;"><span class="fa fa-truck" style="font-size: 80px;"></span><h2>Destinação</h2><p>Informação sobre a destinação da safra colhida de cultivares biofortificados.</p><br><br><p id="gAgricultores" hidden=""><a class="btn btn-secondary" href="paginas/agricultor/agricultor.html" role="button">Retomar <span class="fa fa-mail-forward" aria-hidden="true"></span></a></p></div>');
                $("#divItens").empty().append('<div id="iconRelatarColheita" class="col-lg-5 placeholder" style="text-align: center;"> <h1 class="laranja fontIcones"><span class="fa fa-cube"></span></h1><h2>Colheita</h2><h5> Informacão da quantidade de colhida na propriedade através dos cultivares recebidos no programa biofort!</h5></div>        <div  id="iconRelatarDestinacao" class="col-lg-5 placeholder" style="text-align: center;"><h1 class="amarelo fontIcones"><span class="fa fa-truck"></span></h1><h2>Destinação</h2><p>Informação sobre a destinação da safra colhida de cultivares biofortificados.</p><br><br><p id="gAgricultores" hidden=""><a class="btn btn-secondary" href="paginas/agricultor/agricultor.html" role="button">Retomar <span class="fa fa-mail-forward" aria-hidden="true"></span></a></p></div>');

                $("#divItens").show();
                break;
            case "Relatar colheita da safra":
                inputFocus = progRelatarColheita();
                break;
            case "Relatar destinação da safra":
                inputFocus = progRelatarDestinacao();
                break;
            default:
                alerta("Erro!", "Erro criação do progresso!");
                break;
        }
        
        
//        $("#corpoPagina").show();
//        $(pagina).show();
        //$("#pageDistribuir").show();
        
        $(".a").css("background", "rgba(255,255,255,0.6)");
        $(".backgroundVerde").css("background", "#007A61");
        $(itemSelecionado).css("background", "#FFCC00");
        $("#tituloIdPage").empty().append('<spam class="fa '+icone+'" aria-hidden="true">'+titulo+'</spam>');
        $("#page").fadeIn(400);
        $(inputFocus).focus();
    });
    
     
}

//muda cor painel aonde esta a pagina carregada
function preEventosPaginaCadastros(itemSelecionado, icone, titulo, descricaoListar, descricaoNovo, descricaoEditar, descricaoExcluir){

    var item;
    
    //verifica se é a pagina inicial com o BEM vindo "Usuario" para usar o fadeOut
    if($("#msgInicio").is(':visible')) {
        item = "#msgInicio";
    }else{
        item = "#page";
    }
    
    
    //carrega os item do item selecionado no menu lateral com o fadeIN e muda as legendas referente o item selecionado
    $(item).fadeOut(400, function(){
        //oculta todas as paginas para carregar o de acordo
        $(".paginas").hide();
        $("#identificacaoPage").show();
        $("#atalhosCadastro").show();
        
        
        $(".a").css("background", "rgba(255,255,255,0.6)");
        $(itemSelecionado).css("background", "#FFCC00");
        $("#tituloIdPage").empty().append('<spam class="fa '+icone+'" aria-hidden="true">'+titulo+'</spam>');
        $("#listar h5").text(descricaoListar);
        $("#novo h5").text(descricaoNovo);
        $("#editar h5").text(descricaoEditar);
        $("#excluir h5").text(descricaoExcluir);
        $("#page").fadeIn(400);
    });  
    
    
    
}


//carrega o progresso de distribuir cultivares
function progDistCultivares(){

    //Adicionar agricultor, limpa o progresso e adiciona os itens
    $("#tituloProgresso").text("Distribuir cultivar para um agricultor");


    $("#qtdProgresso").empty().append('<li role="presentation" class="active"><a href="#progresso1" data-toggle="tab" aria-controls="progresso1" role="tab" title="Agricultor"><span class="round-tab"><i class="fa fa-user"></i></span></a></li>\n\
                                       <li role="presentation" class="disabled"><a href="#progresso2" data-toggle="tab" aria-controls="progresso2" role="tab" title="Cultivar"><span class="round-tab"><i class="fa fa-shopping-basket"></i></span></a></li>\n\
                                       <li role="presentation" class="disabled"><a href="#progresso3" data-toggle="tab" aria-controls="progresso3" role="tab" title="Quantidade"><span class="round-tab"><i class="fa fa-calendar-check-o "></i></span></a></li>\n\
                                       <li role="presentation" class="disabled"><a href="#completo" data-toggle="tab" aria-controls="completo" role="tab" title="Distribuir"><span class="round-tab"><i class="fa fa-save"></i></span></a></li>');

    $("#formProgresso").empty().append('<div class="tab-pane active" role="tabpanel" id="progresso1">       <form  data-toggle="validator" role="form" class="formProx" >    <div class="form-group"><label for="campoBuscaAgri" class="control-label" id="labelProcuraAgricultor">Nome do agricultor:</label><input type="text" class="form-control " id="campoBuscaAgri" value="" placeholder="Digite o nome do agricultor..." data-error="Por favor, informe algum dado do agricultor." required><div class="help-block with-errors"></div></div>      <div class="form-group"><label for="listaPropriedades" class="control-label">Propriedade:</label><select class="form-control" id="listaPropriedades" value="" data-error="Por favor, informe a propriedade do agricultor." required></select><div class="help-block with-errors"></div></div>                    <div class="form-group"> <button type="submit" style="float: right;" class="btn btn-warning next-step" >Continuar</button> </div>                          </form>     </div>\n\
                                        <div class="tab-pane"  id="progresso2">                             <form  data-toggle="validator" role="form" class="formProx" >    <div class="form-group"><label for="listaCultivares" class="control-label">Cultivar:</label><select class="form-control" id="listaCultivares" data-error="Por favor, informe algum dado do agricultor." required></select><div class="help-block with-errors"></div></div>   <div class="form-group"><label id ="labelQtdCultivarDist" for="qtdCultivarDist" class="control-label">Quantidade:</label><input type="number" class="form-control " min="0.01" id="qtdCultivarDist" placeholder="Digite a quantidade..." step="0.01" data-error="Por favor, informe a quantidade." required ><div class="help-block with-errors"></div></div>   <div class="form-group"> <button type="submit" style="float: right;" class="btn btn-warning next-step">Continuar</button> </div>  </form>       </div>\n\
                                        <div class="tab-pane"  id="progresso3">                             <form  data-toggle="validator" role="form" class="formProx" >    <div class="form-group"><label for="safraDist" class="control-label" >Safra:</label><input type="text" class="form-control camposSafra" id="safraDist" value="" placeholder="id/ano .... 01/2017" pattern=".{7,}" data-error="Por favor, informe a safra." required><div class="help-block with-errors"></div></div><div class="form-group"><label for="datarecebDist" class="control-label">Data do recebimento:</label><input type="text" class="form-control camposData" placeholder="dd/mm/aaaa" pattern=".{10,}" id="datarecebDist" data-error="Por favor, informe a data do recebimento." required ><div class="help-block with-errors"></div></div>                  <div class="form-group"> <button type="submit" style="float: right;" class="btn btn-warning next-step">Continuar</button> </div>  </form>       </div>\n\
                                        <div class="tab-pane"  id="completo"><h3>Deseja realmente distribuir este cultivar?</h3><div class="form-group"> <button type="submit" style="float: right;" id="distribuirCultivar" class="btn btn-warning">Distribuir</button> </div></div>                                         <div class="clearfix"></div>');

$("#datarecebDist").datepicker();
    //tamanho do icone do progresso de acordo com a quantidade
    $("#qtdProgresso li").css("width", 100/4  + "%");

    $('.formProx').validator();

           
    $("#iconesProgresso").fadeIn(400);

    return "#campoBuscaAgri";
}


$(document).on("keyup", "#campoBuscaAgri", function(){

    $('#campoBuscaAgri').autocomplete({
      source: retornoProcuraAgricultores,

      select: function (event, ui) {
//           $('#labelProcuraAgricultor').text("Nome, ...{ RG: "+ ui.item.rg +" / CPF: "+ui.item.cpf +" }...");
           $('#labelProcuraAgricultor').text("Nome do agricultor, "+ui.item.label);
           $('#campoBuscaAgri').attr('value', ui.item.idpessoa);
           $('#listaPropriedades').empty();
//           $('#listaPropriedades').attr('value', ui.item.idpessoa);
            
      },
      minLength: 3
    });
    
    return false;
});

retornoProcuraAgricultores = function( request, response ) {
    var Sessao = getSessao();
    var envio = {
        valor: request.term +"%",
        metodo: "INPUT_SELECT",
        idunidade: Sessao.idunidade,
        usuario: Sessao.usuario,
        sessao: Sessao.sessao
    };


    //chama a requisicao do servidor, o resultado é listado em um select
    requisicao(false, "agricultor/listar", envio, function(dadosRetorno) {
        if(dadosRetorno.sucesso){
            
            response( $.map(dadosRetorno.data , function( valor ) {
                return {
                    value: valor.nome +" "+valor.sobrenome,
                    label: valor.nome +" "+valor.sobrenome +"...{ RG: "+ valor.rg +" / CPF: "+valor.cpf +" }...",
                    idpessoa: valor.idpessoa
                };
            }));
            //retira o painel loading
            $(".painelCarregando").fadeOut(400);
        }else{
            
            //retira o painel loading
            $(".painelCarregando").fadeOut(400);
        }
        //atualiza a sessao
        updateSessao(dadosRetorno.sessao);
        
    });
};

//pesquisa a propriedade pegando o id do agricultor selecionado no input nome agricultor e o foco sai do input
$(document).on("blur", "#campoBuscaAgri", function(){
    if($(this).val()!== "" && $(this).attr('value') !== "" ){
        //pesquisa a unidade do agricultor
        var Sessao = getSessao();
        var envio = {
            metodo: "NOME_E_ID",
            idpessoa: $('#campoBuscaAgri').attr('value'),
            idunidade: Sessao.idunidade,
            usuario: Sessao.usuario,
            sessao: Sessao.sessao
        };

        //chama a requisicao do servidor, o resultado é listado em uma tabela
        requisicao(true, "propriedade/listar", envio, function(dadosRetorno) {
            if(dadosRetorno.sucesso){

                var items = "";
                    $.each(dadosRetorno.data, function(i, valor){
                        items +='<option value="'+valor.idpropriedade+'">'+valor.nomepropriedade+'</option>';
                    });


    //            $('#listaPropriedades').attr('value', envio.idpessoa).empty().append(items);
                $('#listaPropriedades').empty().append(items);
                          //retira o painel loading
                $(".painelCarregando").fadeOut(400);
            }else{

                //retira o painel loading
                $(".painelCarregando").fadeOut(400);
                alerta("Alerta!", dadosRetorno.mensagem);
                $('#listaPropriedades').attr('value', "");
            }
            //atualiza a sessao
            updateSessao(dadosRetorno.sessao);

        });
    
    }else{
        $(this).attr('value', "");
        $(this).val("");
        $("#listaPropriedades").empty();
        $("#labelProcuraAgricultor").text("Nome do agricultor:");
    }
    
 
    
});