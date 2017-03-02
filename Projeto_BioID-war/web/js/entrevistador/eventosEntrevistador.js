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
        idunidade: Sessao.idunidade
//        usuario: Sessao.usuario,
//        sessao: Sessao.sessao
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
            idunidade: Sessao.idunidade
//            usuario: Sessao.usuario,
//            sessao: Sessao.sessao
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


$(document).on("click", "#listaCultivares", function(){
    
    if($(this).children('option').length < 1){

        //pesquisa a unidade do agricultor
        var Sessao = getSessao();
        var envio = {
            metodo: "TODOS",
            idunidade: Sessao.idunidade
//            usuario: Sessao.usuario,
//            sessao: Sessao.sessao
        };

        //chama a requisicao do servidor, o resultado é listado em uma tabela
        requisicao(true, "estoque/listar", envio, function(dadosRetorno) {
            if(dadosRetorno.sucesso){

                var items = "";
                var values = {};
                    $.each(dadosRetorno.data, function(i, valor){
                        values = {idcultivar: valor.idcultivar,
                                  quantidade: valor.quantidade,
                                  grandeza: valor.grandeza
                        };
                        items +='<option value='+JSON.stringify(values)+'>'+valor.nomecultivar+'</option>';
                    });


                $('#listaCultivares').empty().append(items);

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
    //limpa o valor da quantidade
    $("#qtdCultivarDist").val("");
    return false;
});

$(document).on("blur", "#listaCultivares", function(){
    var valores = JSON.parse($('#listaCultivares :selected').prop('value'));
    
    
    //muda para inteiro ou decimal os valores aceito pelo input

    $("#qtdCultivarDist").attr("min", get_nin_step(valores.grandeza).min);
    $("#qtdCultivarDist").attr("step", get_nin_step(valores.grandeza).step);

    
    $("#qtdCultivarDist").attr("max", valores.quantidade);
    //muda o label de acordo com a quantidade e grandeza do cultivar selecionado
    $("#labelQtdCultivarDist").text("Quantidade em "+valores.grandeza +"...{Qtd no estoque "+ valores.quantidade+"}...");
});

function get_nin_step(grandeza){
    //muda para inteiro ou decimal os valores aceito pelo input

    var min_step = {};
    //kilos =7 
    if(grandeza === "Kilo(s)"){
        min_step = {
            min: "0.01",
            step: "0.01"
        };
    
    //ramas =5 e maniva =6      
    }else{
        min_step = {
            min: "1",
            step: "1"
        };
    }

    return min_step;
}

//enviar a requisicao para o servidor de distribuir cultivares
$(document).on("click", "#distribuirCultivar", function(){

        //pesquisa a unidade do agricultor
        var Sessao = getSessao();
        var dadosCultivar = JSON.parse($('#listaCultivares :selected').prop('value'));
        var envio = {
            metodo: "distribuir",
            idunidade: Sessao.idunidade,
            idpropriedade: $('#listaPropriedades :selected').prop('value'),
            idcultivar: dadosCultivar.idcultivar,
            qtdrecebida: $('#qtdCultivarDist').val(),
            safra: $('#safraDist').val(),
            datareceb: $('#datarecebDist').val()
            
            
//            usuario: Sessao.usuario,
//            sessao: Sessao.sessao
        };
    
    //chama a requisicao do servidor, o resultado é listado em uma tabela
    requisicao(true, "cultivar/distribuir", envio, function(dadosRetorno) {
        if(dadosRetorno.sucesso){
            
            $(".painelCarregando").fadeOut(400);
            alerta("Alerta!", dadosRetorno.mensagem);
//            preEventosPaginaGerenciamento($(this), "fa-cart-arrow-down", "Distribuir cultivares");
            preEventosPaginaGerenciamento($('#painelGerenciamento li:nth-child(2) a'), "fa-cart-arrow-down", "Distribuir cultivares");
        }else{
            //retira o painel loading
            $(".painelCarregando").fadeOut(400);
//            seta o id do input que tera o focus apos fechar o modal
            $("#itenFocus").text("#qtdCultivarDist");
            alerta("Alerta!", dadosRetorno.mensagem);

            //ativa o form de quantidade de cultivares para distribuir e da focus no input 
            $('#qtdProgresso li:nth-child(2)').addClass('active');
            $('#qtdProgresso li:nth-child(4)').removeClass('active');
            $('#progresso3').addClass('active');
            $('#completo').removeClass('active');
            
            
        }
        //atualiza a sessao
        updateSessao(dadosRetorno.sessao);
        
    });
 
    
 
    return false;
});

/////////////*menu icones*///////////////

//listar
$(document).on("click", "#listar", function(evt)
{
  
    var Sessao = getSessao();
    var envio = {
        metodo: "TODOS_DA_UNIDADE",
//        usuario: Sessao.usuario,
//        sessao: Sessao.sessao,
        idunidade: Sessao.idunidade
    };

    //chama a requisicao do servidor, o resultado é listado em uma tabela
    requisicao(true, "agricultor/listar", envio, function(dadosRetorno) {
        
        if(dadosRetorno.sucesso){
            var item = "";

            $.each(dadosRetorno.data, function(i, valor){
                item += "<tr><td>"+valor.idpessoa+"</td><td>"+valor.nome+"</td><td>"+valor.sobrenome+"</td><td>"+valor.rg+"</td><td>"+valor.cpf+"</td><td>"+valor.telefone1+"</td><td>"+valor.nomeunidade+"</td></tr>";

            });

            $("#page").fadeOut(400, function(){
                $("#divItens").empty().append('<h2 class="sub-header">Lista de agricultores</h2><div class="table-responsive"><table class="table table-hover"><thead><tr><th>ID</th><th>Nome</th><th>Sobrenome</th><th>RG</th><th>CPF</th><th>Telefone</th><th>Unidade</th></tr></thead><tbody>'+item+'</tbody></table></div>');
                $("#divItens").show();
                $("#page").fadeIn(400);
            });
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
    
    return false;
});

//novo
//icone novo, escolhe qual o item será adicionado atraves de qual aba esta aberta, novo agricultor, novo cultivar, nova unidade, novo usuario
$(document).on("click", "#novo", function(evt)
{
    
    $("#page").fadeOut(400, function(){
        //esconde os itens
        $(".paginas").hide(); 
        $("#identificacaoPage").fadeIn(400);
        
        var inputFocus;       

        //Adicionar agricultor, limpa o progresso e adiciona os itens
        $("#tituloProgresso").text("Novo agricultor");


        $("#qtdProgresso").empty().append('<li role="presentation" class="active"><a href="#progresso1" data-toggle="tab" aria-controls="progresso1" role="tab" title="Dados pessoais"><span class="round-tab"><i class="fa fa-address-card-o"></i></span></a></li>\n\
                                           <li role="presentation" class="disabled"><a href="#progresso2" data-toggle="tab" aria-controls="progresso2" role="tab" title="Dados familiares"><span class="round-tab"><i class="fa fa-child"></i></span></a></li>\n\
                                           <li role="presentation" class="disabled"><a href="#progresso3" data-toggle="tab" aria-controls="progresso3" role="tab" title="Dados da propriedade"><span class="round-tab"><i class="fa fa-home"></i></span></a></li>\n\
                                           <li role="presentation" class="disabled"><a href="#progresso4" data-toggle="tab" aria-controls="progresso4" role="tab" title="Dados da conta"><span class="round-tab"><i class="fa fa-sign-in"></i></span></a></li>\n\
                                           <li role="presentation" class="disabled"><a href="#completo" data-toggle="tab" aria-controls="completo" role="tab" title="Salvar"><span class="round-tab"><i class="fa fa-save"></i></span></a></li>');

        $("#formProgresso").empty().append('<div class="tab-pane active" role="tabpanel" id="progresso1">       <form  data-toggle="validator" role="form" class="formProx" >       <div class="form-group"><label for="nomeAgricultor" class="control-label">Nome:</label>                                <input type="text" class="form-control " id="nomeAgricultor" placeholder="Digite o nome do agricultor..." pattern="[a-zA-ZçÇãÃâÂáÁàÀéÉêÊíÍìÌõÕôÔóÓúÚ]+" data-error="Por favor, informe o nome do agricultor." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="sobrenomeAgricultor" class="control-label">Sobrenome:</label><input type="text" class="form-control " id="sobrenomeAgricultor" placeholder="Digite o sobrenome do agricultor..." pattern="[a-zA-Z çÇãÃâÂáÁàÀéÉêÊíÍìÌõÕôÔóÓúÚ]+" data-error="Por favor, informe o sobrenome do agricultor." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="rgAgricultor" class="control-label">RG:</label><input type="text" class="form-control camposRG" pattern=".{9,}"  placeholder="99999.999-9" id="rgAgricultor" data-error="Por favor, informe o RG do agricultor." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="cpfAgricultor" class="control-label">CPF:</label><input type="text" class="form-control camposCPF" pattern=".{14,}" placeholder="999.999.999-99" id="cpfAgricultor" data-error="Por favor, informe o CPF do agricultor." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="apelidoAgricultor" class="control-label">Apelido:</label><input type="text" class="form-control" id="apelidoAgricultor" placeholder="Digite o apelido do agricultor..." ></div><div class="form-group"><label for="dataNascAgricultor" class="control-label">Data de nascimento:</label><input type="date" class="form-control camposData" placeholder="dd/mm/aaaa" pattern=".{10,}" id="dataNascAgricultor" data-error="Por favor, informe a data de nascimento do agricultor." required ><div class="help-block with-errors"></div></div><div class="form-group" id="genero"><input type="radio" name="genero" value="m" checked> Masculino<br><input type="radio" name="genero" value="f"> Feminino<br> <br></div><div class="form-group"><label for="telefone1Agricultor" class="control-label">Telefone 1:</label><input type="tel" class="form-control camposTelefone" placeholder="(xx) xxxxx-xxxx" pattern=".{14,}" id="telefone1Agricultor" data-error="Por favor, informe o telefone do agricultor." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="telefone2Agricultor" class="control-label">Telefone 2:</label><input type="tel" class="form-control camposTelefone" placeholder="(xx) xxxxx-xxxx" pattern=".{14,}" id="telefone2Agricultor" ></div><div class="form-group"><label for="escolaridadeAgricultor" class="control-label">Escolaridade:</label><select class="form-control" id="escolaridadeAgricultor"><option>Ensino fundamental</option><option>Ensino fundamental incompleto</option><option>Ensino médio</option><option>Ensino médio incompleto</option><option>Ensino superior</option><option>Ensino superior incompleto</option><option>Pós-graduação</option><option>Pós-graduação incompleta</option><option>Sem escolaridade</option></select></div><div class="form-group"><label for="estadocivilAgricultor" class="control-label">Estado civil:</label><select class="form-control" id="estadocivilAgricultor"><option>Solteiro(a)</option><option>Casado(a)</option><option>Divorciado(a)</option><option>Viúvo(a)</option><option>Separado(a)</option><option>Companheiro(a)</option></select></div>                    <div class="form-group"> <button type="submit" style="float: right;" class="btn btn-warning next-step" >Continuar</button> </div>                          </form>     </div>\n\
                                            <div class="tab-pane"  id="progresso2">                             <form  data-toggle="validator" role="form" class="formProx" >     <div class="form-group"><label for="qtdIntegrantes" class="control-label">Quantidade de integrantes na família:</label><input type="number" class="form-control " id="qtdIntegrantes" placeholder="Digite a quantidade de integrantes..." min="0"                           data-error="Por favor, informe a quantidade de integrantes." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="qtdCriancas" class="control-label">Quantidade de crianças na família:</label><input type="number" class="form-control " id="qtdCriancas" placeholder="Digite a quantidade de crianças..." min="0" data-error="Por favor, informe a quantidade de crianças na família." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="qtdGravidas" class="control-label">Quantidade de grávidas na família:</label><input type="number" class="form-control " id="qtdGravidas" placeholder="Digite a quantidade de grávidas..." min="0" data-error="Por favor, informe a quantidade de grávidas na família." required ><div class="help-block with-errors"></div></div>   <div class="form-group"> <button type="submit" style="float: right;" class="btn btn-warning next-step">Continuar</button> </div>  </form>       </div>\n\
                                            <div class="tab-pane"  id="progresso3">                             <form  data-toggle="validator" role="form" class="formProx" >    <div class="form-group"><label for="nomePropriedade" class="control-label">Nome da propriedade:</label><input type="text" class="form-control " id="nomePropriedade" placeholder="Digite o nome da propriedade..." pattern="[a-zA-Z çÇãÃâÂáÁàÀéÉêÊíÍìÌõÕôÔóÓúÚ]+" data-error="Por favor, informe o nome da propriedade." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="rua" class="control-label">Rua/Estrada:</label><input type="text" class="form-control " id="rua" placeholder="Digite o nome da rua ou estrada..." pattern="[a-zA-Z çÇãÃâÂáÁàÀéÉêÊíÍìÌõÕôÔóÓúÚ]+" data-error="Por favor, informe a rua da propriedade." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="numero" class="control-label">Número:</label><input type="number" min="0" class="form-control " id="numero" placeholder="Digite o número..." ></div><div class="form-group"><label for="bairro" class="control-label">Bairro/Distrito:</label><input type="text" class="form-control " id="bairro" placeholder="Digite o bairro ou distrito..." pattern="[a-zA-Z0-9 çÇãÃâÂáÁàÀéÉêÊíÍìÌõÕôÔóÓúÚ]+" ></div><div class="form-group"><label for="complemento" class="control-label">Complemento/Comunidade:</label><input type="text" class="form-control" id="complemento" placeholder="Digite o complemento ou comunidade..." ></div><div class="form-group"><label for="cep" class="control-label">Cep:</label><input type="text" class="form-control  camposCEP" id="cep" placeholder="Digite o cep da propriedade..." ></div><div class="form-group"><label for="gps_lat" class="control-label">Latitude:</label><input type="text" class="form-control " id="gps_lat" placeholder="Digite a latitude..." value="0"></div><div class="form-group"><label for="gps_long" class="control-label">Longitude:</label><input type="text" class="form-control " id="gps_long" placeholder="Digite a longitude..." value="0"></div><div class="form-group"><label for="paisAgricultor" class="control-label">País:</label><select class="form-control carregaPais" id="paisAgricultor" data-error="Por favor, informe o país da propriedade." required ><option></option></select><div class="help-block with-errors"></div></div><div class="form-group"><label for="estadoAgricultor" class="control-label">Estado:</label><select class="form-control carregaEstado" id="estadoAgricultor" data-error="Por favor, informe o estado da propriedade." required ><option></option></select><div class="help-block with-errors"></div></div><div class="form-group"><label for="cidadeAgricultor" class="control-label">Cidade:</label><select class="form-control  carregaCidade" id="cidadeAgricultor" data-error="Por favor, informe a cidade da propriedade." required ><option></option></select><div class="help-block with-errors"></div></div><div class="form-group"><label for="area" class="control-label">Área da propriedade:</label><input type="number" min="0" step="0.01" class="form-control " id="area" placeholder="Digite a área..." ></div><div class="form-group"><label for="unidademedida" class="control-label">Unidade de medida:</label><select class="form-control " id="unidademedida"><option value="3">Metros quadrados</option><option value="1">Alqueire</option><option value="2">Hectare</option></select></div><div class="form-group"><label for="areautilizavel" class="control-label">Área utilizada:</label><input type="number" class="form-control " id="areautilizavel" min="0" step="0.01" placeholder="Digite a área utilizada..." ></div><div class="form-group"><label for="unidadedemedidaau" class="control-label">Unidade de medida:</label><select class="form-control " id="unidadedemedidaau"><option value="3">Metros quadrados</option><option value="1">Alqueire</option><option value="2">Hectare</option></select></div><div class="form-group"> <button type="submit" style="float: right;" class="btn btn-warning next-step">Continuar</button> </div>  </form>       </div>\n\
                                            <div class="tab-pane"  id="progresso4">                             <form  data-toggle="validator" role="form" class="formProx" >          <div class="form-group"><label for="emailAgricultor" class="control-label">Email:</label><input class="form-control " id="emailAgricultor" placeholder="Digite o email do agricultor..." type="email" data-error="Por favor, informe um e-mail correto." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="usuarioAgricultor" class="control-label">Usuário:</label><input type="text" class="form-control " id="usuarioAgricultor" data-minlength="6" placeholder="Digite o usuário no mínimo 6 dígitos..." pattern="[a-zA-Z0-9]+" data-error="Por favor, informe o usuário no mínimo 6 dígitos." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="senhaAgricultor" class="control-label">Senha:</label><input type="password" class="form-control " id="senhaAgricultor" placeholder="Digite a senha..." data-minlength="6" data-error="Por favor, informe uma senha no minímo 6 dígitos." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="RsenhaAgricultor" class="control-label">Repita a senha:</label><input type="password" class="form-control " id="RsenhaAgricultor" placeholder="Digite novamente a senha..." data-match="#senhaAgricultor" data-error="Por favor, confirme sua senha." data-match-error="Ops, as senhas não correspondem." required ><div class="help-block with-errors"></div></div><div class="form-group"> <button type="submit" style="float: right;" class="btn btn-warning next-step">Continuar</button> </div>  </form>       </div>\n\
                                            <div class="tab-pane"  id="completo"><h3>Deseja realmente salvar o agricultor?</h3><div class="form-group"> <button type="submit" style="float: right;" id="salvarNovoAgricultor" class="btn btn-warning">Salvar</button> </div></div>                                         <div class="clearfix"></div>');

        $("#dataNascAgricultor").datepicker();

        //tamanho do icone do progresso de acordo com a quantidade
        $("#qtdProgresso li").css("width", 100/5  + "%");

        inputFocus = "#nomeAgricultor";
        $('.formProx').validator();
          

        
        $("#iconesProgresso").fadeIn(400);
        
        $("#page").fadeIn(400, function(){
            $(inputFocus).focus(); 
        });
        

    }).promise().done(function(){
//        alert($(".navbar").height());
//        alert($("#tamanhoSessao").height());
//        alert($("#tamanhoIdentificacao").height());
   
       var a = $("#formProgresso").offset().top;
        $("#formProgresso").css("height", $( window ).height() - a );


//        $("#formProgresso").css("height", $( window ).height() );
    });
    
 
        
          
    return false;
});

//icone relatar colheita dentro do relatar safra
$(document).on("click", "#iconRelatarColheita", function(evt)
{
  
    preEventosPaginaGerenciamento($('#painelGerenciamento li:nth-child(4) a'), "fa-cube", "Relatar colheita da safra");
    
    return false;
});

//carrega o progresso de relatar colheita
function progRelatarColheita(){

    //Adicionar agricultor, limpa o progresso e adiciona os itens
    $("#tituloProgresso").text("Relatar colheita");


    $("#qtdProgresso").empty().append('<li role="presentation" class="active"><a href="#progresso1" data-toggle="tab" aria-controls="progresso1" role="tab" title="Agricultor"><span class="round-tab"><i class="fa fa-user"></i></span></a></li>\n\
                                       <li role="presentation" class="disabled"><a href="#progresso2" data-toggle="tab" aria-controls="progresso2" role="tab" title="Cultivar"><span class="round-tab"><i class="fa fa-leaf"></i></span></a></li>\n\
                                       <li role="presentation" class="disabled"><a href="#completo" data-toggle="tab" aria-controls="completo" role="tab" title="Relatar"><span class="round-tab"><i class="fa fa-save"></i></span></a></li>');

    $("#formProgresso").empty().append('<div class="tab-pane active" role="tabpanel" id="progresso1">       <form  data-toggle="validator" role="form" class="formProx" >    <div class="form-group"><label for="campoBuscaAgri" class="control-label" id="labelProcuraAgricultor">Nome do agricultor:</label><input type="text" class="form-control " id="campoBuscaAgri" value="" placeholder="Digite o nome do agricultor..." data-error="Por favor, informe algum dado do agricultor." required><div class="help-block with-errors"></div></div>      <div class="form-group"><label for="listaPropriedades" class="control-label">Propriedade:</label><select class="form-control" id="listaPropriedades" value="" data-error="Por favor, informe a propriedade do agricultor." required></select><div class="help-block with-errors"></div></div>                    <div class="form-group"> <button type="submit" style="float: right;" class="btn btn-warning next-step" >Continuar</button> </div>                          </form>     </div>\n\
                                        <div class="tab-pane"  id="progresso2">                             <form  data-toggle="validator" role="form" class="formProx" >    <div class="form-group"><label for="listaCultivaresReceb" class="control-label">Cultivar:</label><select class="form-control" id="listaCultivaresReceb" data-error="Por favor, selecione o cultivar a ser relatado." required></select><div class="help-block with-errors"></div></div>   <div class="form-group"><label id ="labelQtdCultivarRelatar" for="qtdCultivarRelatar" class="control-label">Quantidade:</label><input type="number" class="form-control " min="0.01" id="qtdCultivarRelatar" placeholder="Digite a quantidade..." step="0.01" data-error="Por favor, informe a quantidade." required ><div class="help-block with-errors"></div></div>   <div class="form-group"> <button type="submit" style="float: right;" class="btn btn-warning next-step">Continuar</button> </div>  </form>       </div>\n\
                                        <div class="tab-pane"  id="completo"><h3>Deseja realmente relatar está colheita?</h3><div class="form-group"> <button type="submit" style="float: right;" id="relatarColheita" class="btn btn-warning">Relatar</button> </div></div>                                         <div class="clearfix"></div>');

$("#datarecebDist").datepicker();
    //tamanho do icone do progresso de acordo com a quantidade
    $("#qtdProgresso li").css("width", 100/3  + "%");

    $('.formProx').validator();

           
    $("#iconesProgresso").fadeIn(400);

    return "#campoBuscaAgri";
}