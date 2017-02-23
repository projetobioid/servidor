
///////*painel lateral*////////////
////painel escolha de opcoes de abrir pages
$(document).on("click", ".a", function(evt)
{
    
    chamarMenu($(this));
    
    return false;
});


function chamarMenu(escolha){
    var item = escolha.text();
    
    if(item === "Equipe"){
        preEventosPaginaCadastros(escolha, "fa-hand-peace-o", "Equipe", "Listar todos da equipe", "Adicionar um novo membro", "Editar informações de um membro", "Excluir um membro");
    }else if(item === "Estoque da unidade"){
        preEventosPaginaGerenciamento(escolha, "fa-cubes", "Estoque da unidade");
    }else if(item === "Agricultores"){
        preEventosPaginaCadastros(escolha, "fa-users", "Agricultores", "Listar todos os agricultores", "Adicionar um novo agricultor", "Editar informações de um agricultor", "Excluir um agricultor");
    }else if(item === "Distribuir cultivares/Agricultor"){
        preEventosPaginaGerenciamento(escolha, "fa-cart-arrow-down", "Distribuir cultivares/Agricultor");
        
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
        
        if(titulo === "Estoque da unidade"){
            
            $("#divItens").empty().append('<h2 class="sub-header">Cultivares no estoque</h2>  <button type="button" title="Entrada de estoque" class="btn btn-warning" id="entradaEstoque" ><span class="fa fa-plus-square" aria-hidden="true"></span></button><button type="button" title="Saída de estoque" class="btn btn-warning" disabled id="saidaEstoque" ><span class="fa fa-minus-square" aria-hidden="true"></span></button>               <div class="table-responsive"><table id="tabEstoqueUnidade" class="table table-hover"><thead><tr><th>ID</th><th>Nome cultivar</th><th>Quantidade</th><th>Unidade de medida</th></tr></thead><tbody id="itensTabelaEstoque"></tbody></table></div>');
            $("#divItens").show();
            progEstoqueUnidade();
        }else if(titulo === "Distribuir cultivares/Agricultor"){
            inputFocus = progDistCultivares();
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
    
    


//carrega o estoque da unidade
function progEstoqueUnidade(){
   
    //pesquisa a unidade do agricultor
        var Sessao = getSessao();
        var envio = {
            metodo: "TODOS",
            idunidade: Sessao.idunidade,
            usuario: Sessao.usuario,
            sessao: Sessao.sessao
        };
        
    //chama a requisicao do servidor, o resultado é listado em uma tabela
        requisicao(true, "estoque/listar", envio, function(dadosRetorno) {
            if(dadosRetorno.sucesso){

                var items = "";
                    $.each(dadosRetorno.data, function(i, valor){
//                        items +='<option value="'+valor.idcultivar+'">'+valor.nomecultivar+'</option>';
                        items += "<tr><td>"+valor.idcultivar+"</td><td>"+valor.nomecultivar+"</td><td>"+valor.quantidade+"</td><td>"+valor.grandeza+"</td></tr>";
                    });



                $("#itensTabelaEstoque").append(items);


                //retira o painel loading
                $(".painelCarregando").fadeOut(400);
            }else{
                //retira o painel loading
                $(".painelCarregando").fadeOut(400);
                alerta("Alerta!", dadosRetorno.mensagem);
                /////////////////
//                $("#itensTabelaEstoque").append("<tr><td>1</td><td>batata</td><td>50</td><td>rama(s)</td></tr>");
            }
            //atualiza a sessao
            updateSessao(dadosRetorno.sessao);

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




/////////////*menu icones*///////////////

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
            listarEquipe();
        break;
        case 3:
            listarAgricultores();
        break;
        default:
            alerta("Alerta!", "Erro de requisição de navegação!"); 
        
    }
      
}









//chama a requisicao para uma entrada no estoque da unidade, cria um modal com inputs
$(document).on("click", "#entradaEstoque", function(e){
    
    
    $("#divItens").fadeOut(400, function (){
       $("#divItens").hide(); 
    
    
//    Adicionar agricultor, limpa o progresso e adiciona os itens
                $("#tituloProgresso").text("Entrada de estoque");
                $("#progressoRef").text("");
                
                $("#qtdProgresso").empty().append('<li role="presentation" class="active"><a href="#progresso1" data-toggle="tab" aria-controls="progresso1" role="tab" title="Cultivar e quantidade"><span class="round-tab"><i class="fa fa-leaf"></i></span></a></li>\n\
                                                   <li role="presentation" class="disabled"><a href="#completo" data-toggle="tab" aria-controls="completo" role="tab" title="Salvar"><span class="round-tab"><i class="fa fa-save"></i></span></a></li>');

                $("#formProgresso").empty().append('<div class="tab-pane active" role="tabpanel" id="progresso1">       <form  data-toggle="validator" role="form" id="entradaCultiEstoque" >          <div class="form-group"><label for="cultEntEstoque" class="control-label">Cultivares:</label><select class="form-control " id="cultEntEstoque" data-error="Por favor, informe o cultivar." required ></select><div class="help-block with-errors"></div></div>      <div class="form-group"><label id="labQtdCultivarEnt" for="qtdCultivarEnt" class="control-label">Quantidade:</label><input type="number" class="form-control " min="0.01" id="qtdCultivarEnt" placeholder="Digite a quantidade de entrada..." step="0.01" data-error="Por favor, informe a quantidade de entrada." required ><div class="help-block with-errors"></div></div>            <div class="form-group"> <button type="submit" style="float: right;" class="btn btn-warning next-step">Continuar</button> </div>   </form>     </div>\n\
                                                    <div class="tab-pane"  id="completo"><h3>Deseja realmente dar entrada no estoque?</h3><div class="form-group"> <button type="submit" style="float: right;" id="salvarEntradaEstoque" class="btn btn-warning">Salvar</button> </div></div>                                         <div class="clearfix"></div>');

                
                //tamanho do icone do progresso de acordo com a quantidade
                $("#qtdProgresso li").css("width", 100/2  + "%");
                
                var inputFocus = "#cultEntEstoque";
                $('#entradaCultiEstoque').validator();
           
           
           
           
        $("#iconesProgresso").fadeIn(400);
        

            $(inputFocus).focus();    
    
    

    
    });
    
    return false;
});

//form de envio de entrada de cultivares na unidade, botao salvar a entrada de cultivares 
$(document).on("submit", "#entradaCultiEstoque", function(e){
    if(!e.isDefaultPrevented()){
        continuarProgresso();
    }

    return false;
});

//botao de salvar a entrada no estoque
$(document).on("click", "#salvarEntradaEstoque", function(e){
    
        submeterEntradaCult();

    return false;
});


//envia a requisicao para o servidor
function submeterEntradaCult(){
    
    var Sessao = getSessao();
    
    var valores = JSON.parse($('#cultEntEstoque').prop('value'));

    var envio = {    
        idcultivar: valores.idcultivar,
        qtd: $('#qtdCultivarEnt').val(),
        operacao: 1, //ENTRADA
        idunidade: Sessao.idunidade,
        usuario: Sessao.usuario,
        sessao: Sessao.sessao
    };

    
    
    //chama a requisicao do servidor, o resultado é listado em uma tabela
    requisicao(true, "estoque/entradaestoque", envio, function(dadosRetorno) {

        if(dadosRetorno.sucesso){

            //retira o painel loading
            $(".painelCarregando").fadeOut(400);
            preEventosPaginaGerenciamento($('#painelGerenciamento li:nth-child(3) a'), "fa-cubes", "Estoque da unidade");
            alerta("Sucesso", dadosRetorno.mensagem);
        }else{
            //retira o painel loading
            $(".painelCarregando").fadeOut(400);
            //alerta de erro
            alerta("Erro!", dadosRetorno.mensagem);
//            $("#itensModal").empty().append(dadosRetorno.mensagem);
//            $("#modalTitulo").text("Erro!");
        }
        //atualiza a sessao
        updateSessao(dadosRetorno.sessao);
    });
    

}
//lista em um select os cultivares para serem selecionado para a entrada
$(document).on("click", "#cultEntEstoque", function(e){
    
    if($(this).children('option').length < 1){
        var Sessao = getSessao();
        var envio = {
            metodo: "TODOS",
            usuario: Sessao.usuario,
            sessao: Sessao.sessao
        };

        //chama a requisicao do servidor, o resultado é listado em uma tabela
        requisicao(true, "cultivar/listar", envio, function(dadosRetorno) {

            if(dadosRetorno.sucesso){
                var item = "";
                var valores;
                $.each(dadosRetorno.data, function(i, valor){
                    valores = JSON.stringify({ idcultivar:valor.idcultivar,
                          grandeza: valor.grandeza
                    });
//                    item += '<option value="{"id":"'+valor.idcultivar+'", "um": "teste"}" >'+valor.nomecultivar+'</option>';
                    item += "<option value="+valores+">"+valor.nomecultivar+"</option>";

                });

                $("#cultEntEstoque").append(item);

                //retira o painel loading
                $(".painelCarregando").fadeOut(400);
            }else{
                //retira o painel loading
                $(".painelCarregando").fadeOut(400);
                alert("Alerta!", dadosRetorno.mensagem);
            }
            //atualiza a sessao
            updateSessao(dadosRetorno.sessao);
        });
    }
    return false;
});

$(document).on("blur", "#cultEntEstoque", function(e){
    //pega os valores selecionado
    var valores = JSON.parse($('#cultEntEstoque :selected').prop('value'));
    
    //muda o label
    $("#labQtdCultivarEnt").text("Quantidade em "+ valores.grandeza + " :");
    $("#qtdCultivarEnt").val("");
    


    $("#qtdCultivarEnt").attr("min", get_nin_step(valores.grandeza).min);
    $("#qtdCultivarEnt").attr("step", get_nin_step(valores.grandeza).step);
      

    return false;
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

//botao saida de estoque
$(document).on("click", "#saidaEstoque", function(e){
    

    var Sessao = getSessao();
    var envio = {
        metodo: "GET_CULTIVAR",
        idcultivar: $('#tabEstoqueUnidade').find('.warning').find('td:eq(0)').html(),
        idunidade: Sessao.idunidade,
        usuario: Sessao.usuario,
        sessao: Sessao.sessao
    };
    //chama a requisicao do servidor, o resultado a quantidade de cultivar no estoque
        requisicao(true, "estoque/buscar", envio, function(dadosRetorno) {

            if(dadosRetorno.sucesso){
                
                $("#divItens").fadeOut(400, function (){
                    $("#divItens").hide(); 

                    $("#tituloProgresso").text("Saída de estoque");
                    $("#progressoRef").text("");

                    $("#qtdProgresso").empty().append('<li role="presentation" class="active"><a href="#progresso1" data-toggle="tab" aria-controls="progresso1" role="tab" title="Cultivar e quantidade"><span class="round-tab"><i class="fa fa-bullhorn"></i></span></a></li>\n\
                                                       <li role="presentation" class="disabled"><a href="#completo" data-toggle="tab" aria-controls="completo" role="tab" title="Salvar"><span class="round-tab"><i class="fa fa-save"></i></span></a></li>');

                    $("#formProgresso").empty().append('<div class="tab-pane active" role="tabpanel" id="progresso1">       <form  data-toggle="validator" role="form" id="saidaCultiEstoque" >          <div class="form-group"><label for="motivoSaida" class="control-label">Motivo:</label><select class="form-control " id="motivoSaida" data-error="Por favor, informe o cultivar." required ><option></option><option>Perda</option><option>Outros</option></select><div class="help-block with-errors"></div></div>   <div class="form-group"><label for="obsmotivo" class="control-label">Observação:</label><textarea class="form-control" id="obsmotivo" placeholder="Digite uma observação do motivo..." ></textarea></div>    <div class="form-group"><label for="qtdCultivarSaida" class="control-label">Quantidade em '+ dadosRetorno.data.grandeza+':</label><input type="number" class="form-control" id="qtdCultivarSaida" max='+ dadosRetorno.data.quantidade +' min='+ get_nin_step(dadosRetorno.data.grandeza).min +' step='+ get_nin_step(dadosRetorno.data.grandeza).step +' placeholder="Digite a quantidade de entrada..."  data-error="Por favor, informe a quantidade de saída." required ><div class="help-block with-errors"></div></div>            <div class="form-group"> <button type="submit" style="float: right;" class="btn btn-warning next-step">Continuar</button> </div>   </form>     </div>\n\
                                                        <div class="tab-pane"  id="completo"><h3>Deseja realmente dar saída no estoque?</h3><div class="form-group"> <button type="click" style="float: right;" id="salvarSaidaEstoque" class="btn btn-warning">Salvar</button> </div></div>                                         <div class="clearfix"></div>');


                    //tamanho do icone do progresso de acordo com a quantidade
                    $("#qtdProgresso li").css("width", 100/2  + "%");

                    var inputFocus = "#cultSaidaEstoque";
                    $('#saidaCultiEstoque').validator();

                    $("#iconesProgresso").fadeIn(400);
                    $(inputFocus).focus();    

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

//chama a requisicao para salvar a saida de estoque
$(document).on("submit", "#saidaCultiEstoque", function(e){
    if(!e.isDefaultPrevented()){
        continuarProgresso();
    }
    

    return false;
});

//chama a requisicao para salvar a saida de estoque
$(document).on("click", "#salvarSaidaEstoque", function(e){
    alerta("Alerta","Em construcao");

    return false;
});

function continuarProgresso(){
    $("#qtdProgresso li").each(function(i){

            var tabs = $('a[data-toggle="tab"]');

            if($(this).hasClass("active")){
  
                tabs.eq(i+1).tab('show');
                $(this).removeClass("active").next().removeClass("disabled").addClass("active");
                
                return false;
            }
        });
}

//dois clicks tabela aparece modal com atributos referentes ao item selecionado
$(document).on("dblclick", "#divItens tr", function(evt)
{
    $("#modalTitulo").text("");
    $("#itensModal").empty();
    
    

            switch (verificaPagina()){
                        case 1:
                            carregaMembro($(this).find('td:eq(0)').html(), "modal");
                        break;
                        case 2:
                            carregaCultivar($(this).find('td:eq(0)').html(), "modal");
                        break;
                        case 3:
                            carregaAgricultor($(this).find('td:eq(0)').html(), "modal");  
                        break;

                        default:
                            alerta("Alerta!", "Erro de requisição de navegação!"); 
        
    }

    
    
    $('#modalApresentacao').modal('toggle');
    return false;
});

function verificaPagina(){
    
    if($("#tituloIdPage > spam").text() === "Equipe"){
        return 1;
    }else if($("#tituloIdPage > spam").text() === "Estoque da unidade"){
        return 2;
    }else if($("#tituloIdPage > spam").text() === "Agricultores"){
        return 3;
    }else{
        alert("Erro em identificar o item selecionado!");
    }
}

$(document).on("click", "tbody > tr", function(evt)
{
//se estiver na pagina estoque desativa o botao saida de estoque
    if($(this).hasClass("warning") && verificaPagina() === 2){
        
        $("#saidaEstoque").attr("disabled", true);
    
        //verifica se esta na pagina estoque dai ativa o botao
    }else if(verificaPagina() === 2 ){
            $("#saidaEstoque").removeAttr("disabled");
    }
    

     return false;
});


//listar usuarios
function listarEquipe(){

    var Sessao = getSessao();
    var envio = {
        metodo: "EQUIPE",
        usuario: Sessao.usuario,
        sessao: Sessao.sessao,
        idunidade: Sessao.idunidade
    };
    //chama a requisicao do servidor, o resultado é listado em uma tabela
    requisicao(true, "usuario/listar", envio, function(dadosRetorno) {

        if(dadosRetorno.sucesso){
            var item = "";
            
            $.each(dadosRetorno.data, function(i, valor){
                item += "<tr><td>"+valor.idpessoa+"</td><td>"+valor.nome+"</td><td>"+valor.sobrenome+"</td><td>"+valor.rg+"</td><td>"+valor.cpf+"</td><td>"+valor.telefone1+"</td><td>"+valor.nomeunidade+"</td><td>"+valor.grupo+"</td></tr>";

            });

            $("#page").fadeOut(400, function(){
                $("#divItens").empty().append('<h2 class="sub-header">Lista da equipe</h2><div class="table-responsive"><table class="table table-hover"><thead><tr><th>ID</th><th>Nome</th><th>Sobrenome</th><th>RG</th><th>CPF</th><th>Telefone</th><th>Unidade</th><th>Grupo</th></tr></thead><tbody>'+item+'</tbody></table></div>');
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
}





////////////////////////////////////NOVO/////////////////////////////////////////////

//novo
//icone novo, escolhe qual o item será adicionado atraves de qual aba esta aberta, novo agricultor, novo cultivar, nova unidade, novo usuario
$(document).on("click", "#novo", function(evt)
{
    
    $("#page").fadeOut(400, function(){
        //esconde os itens
        $(".paginas").hide(); 
        $("#identificacaoPage").fadeIn(400);
        
        var inputFocus;
        switch (verificaPagina()){
            


            case 1:
                
                //Adiciona um novo form de um novo usuario, entrevistador, gerenciador, adm
                $("#tituloProgresso").text("Novo membro");
                $("#progressoRef").text("Dados pessoais");
                
                $("#qtdProgresso").empty().append('<li role="presentation" class="active"><a href="#progresso1" data-toggle="tab" aria-controls="progresso1" role="tab" title="Dados pessoais"><span class="round-tab"><i class="fa fa-address-card-o"></i></span></a></li>\n\
                                                   <li role="presentation" class="disabled"><a href="#progresso2" data-toggle="tab" aria-controls="progresso2" role="tab" title="Endereco usuario"><span class="round-tab"><i class="fa fa-map-marker"></i></span></a></li>\n\
                                                   <li role="presentation" class="disabled"><a href="#progresso3" data-toggle="tab" aria-controls="progresso3" role="tab" title="Dados da conta"><span class="round-tab"><i class="fa fa-sign-in"></i></span></a></li>\n\
                                                   <li role="presentation" class="disabled"><a href="#progresso4" data-toggle="tab" aria-controls="progresso4" role="tab" title="Papel"><span class="round-tab"><i class="fa fa-shield"></i></span></a></li>\n\
                                                   <li role="presentation" class="disabled"><a href="#completo" data-toggle="tab" aria-controls="completo" role="tab" title="Salvar"><span class="round-tab"><i class="fa fa-save"></i></span></a></li>');

                $("#formProgresso").empty().append('<div class="tab-pane active" role="tabpanel" id="progresso1">   <form class="formNovoUsuario" data-toggle="validator" role="form">    <div class="form-group"><label for="nomeNovoUsuario" class="control-label">Nome:</label><input type="text" class="form-control " id="nomeNovoUsuario" placeholder="Digite o nome do usuário..." pattern="[a-zA-ZçÇãÃâÂáÁàÀéÉêÊíÍìÌõÕôÔóÓúÚ]+" data-error="Por favor, informe o nome do usuário." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="sobrenomeNovoUsuario" class="control-label">Sobrenome:</label><input type="text" class="form-control " id="sobrenomeNovoUsuario" placeholder="Digite o sobrenome do usuário..." pattern="[a-zA-Z çÇãÃâÂáÁàÀéÉêÊíÍìÌõÕôÔóÓúÚ]+" data-error="Por favor, informe o sobrenome do usuário." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="rgNovoUsuario" class="control-label">RG:</label><input type="text" class="form-control camposRG" pattern=".{9,}"  placeholder="99999.999-9" id="rgNovoUsuario" data-error="Por favor, informe o RG do usuário." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="cpfNovoUsuario" class="control-label">CPF:</label><input type="text" class="form-control  camposCPF" pattern=".{14,}" placeholder="999.999.999-99" id="cpfNovoUsuario" data-error="Por favor, informe o CPF do usuário." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="apelidoNovoUsuario" class="control-label">Apelido:</label><input type="text" class="form-control" id="apelidoNovoUsuario" placeholder="Digite o apelido do usuário..." ></div><div class="form-group"><label for="dataNascNovoUsuario" class="control-label">Data de nascimento:</label><input type="text" class="form-control camposData" placeholder="dd/mm/aaaa" pattern=".{10,}" id="dataNascNovoUsuario" data-error="Por favor, informe a data de nascimento do usuário." required ><div class="help-block with-errors"></div></div><div class="form-group" id="generoNovoUsuario"><input type="radio" name="genero" value="m" checked> Masculino<br><input type="radio" name="genero" value="f"> Feminino<br> <br></div><div class="form-group"><label for="telefone1NovoUsuario" class="control-label">Telefone 1:</label><input type="tel" class="form-control  camposTelefone" placeholder="(xx) xxxxx-xxxx" pattern=".{14,}" id="telefone1NovoUsuario" data-error="Por favor, informe o telefone1 do usuário." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="telefone2NovoUsuario" class="control-label">Telefone 2:</label><input type="tel" class="form-control  camposTelefone" placeholder="(xx) xxxxx-xxxx" pattern=".{14,}" id="telefone2NovoUsuario" placeholder="Digite o telefone 2 do usuário..." ></div><div class="form-group"><label for="escolaridadeNovoUsuario" class="control-label">Escolaridade:</label><select class="form-control" id="escolaridadeNovoUsuario"><option>Ensino fundamental</option><option>Ensino fundamental incompleto</option><option>Ensino médio</option><option>Ensino médio incompleto</option><option>Ensino superior</option><option>Ensino superior incompleto</option><option>Pós-graduação</option><option>Pós-graduação incompleta</option><option>Sem escolaridade</option></select></div><div class="form-group"><label for="estadocivilNovoUsuario" class="control-label">Estado civil:</label><select class="form-control " id="estadocivilNovoUsuario"><option>Solteiro(a)</option><option>Casado(a)</option><option>Divorciado(a)</option><option>Viúvo(a)</option><option>Separado(a)</option><option>Companheiro(a)</option></select></div>   <div class="form-group"> <button type="submit" style="float: right;" class="btn btn-warning next-step">Continuar</button> </div>  </form>       </div>\n\
                                                   <div class="tab-pane"  id="progresso2">                          <form class="formNovoUsuario" data-toggle="validator" role="form">    <div class="form-group"><label for="ruaNovoUsuario" class="control-label">Rua:</label><input type="text" class="form-control " id="ruaNovoUsuario" placeholder="Digite o nome da rua..." pattern="[a-zA-Z .çÇãÃâÂáÁàÀéÉêÊíÍìÌõÕôÔóÓúÚ]+" data-error="Por favor, informe a rua do usuário." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="numeroNovoUsuario" class="control-label">Número:</label><input type="number" min="0" class="form-control " id="numeroNovoUsuario" placeholder="Digite o número..." data-error="Por favor, informe o número do usuário." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="bairroNovoUsuario" class="control-label">Bairro:</label><input type="text" class="form-control " id="bairroNovoUsuario" placeholder="Digite o nome do bairro..." pattern="[a-zA-Z çÇãÃâÂáÁàÀéÉêÊíÍìÌõÕôÔóÓúÚ]+" data-error="Por favor, informe o bairro do usuário." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="complementoNovoUsuario" class="control-label">Complemento:</label><input type="text" class="form-control" id="complementoNovoUsuario" placeholder="Digite o complemento..." ></div><div class="form-group"><label for="cepNovoUsuario" class="control-label">Cep:</label><input type="text" class="form-control  camposCEP" id="cepNovoUsuario" placeholder="Digite o cep..." ></div><div class="form-group"><label for="gps_latNovoUsuario" class="control-label">Latitude:</label><input type="number" class="form-control " id="gps_latNovoUsuario" placeholder="Digite a latitude..." min="0" value="0"></div><div class="form-group"><label for="gps_longNovoUsuario" class="control-label">Longitude:</label><input type="number" class="form-control " id="gps_longNovoUsuario" placeholder="Digite a longitude..." min="0" value="0"></div><div class="form-group"><label for="paisNovoUsuario" class="control-label">País:</label><select class="form-control  carregaPais" id="paisNovoUsuario" data-error="Por favor, informe o país do usuário." required ><option></option></select><div class="help-block with-errors"></div></div><div class="form-group"><label for="estadoNovoUsuario" class="control-label">Estado:</label><select class="form-control carregaEstado" id="estadoNovoUsuario" data-error="Por favor, informe o estado do usuário." required ><option></option></select><div class="help-block with-errors"></div></div><div class="form-group"><label for="cidadeNovoUsuario" class="control-label">Cidade:</label><select class="form-control carregaCidade" id="cidadeNovoUsuario" data-error="Por favor, informe a cidade do usuário." required ><option></option></select><div class="help-block with-errors"></div></div><div class="form-group"> <button type="submit" style="float: right;" class="btn btn-warning next-step">Continuar</button> </div>  </form>       </div>\n\
                                                   <div class="tab-pane"  id="progresso3">                          <form class="formNovoUsuario" data-toggle="validator" role="form">    <div class="form-group"><label for="emailNovoUsuario" class="control-label">Email:</label><input type="email" class="form-control " id="emailNovoUsuario" placeholder="Digite o email do usuário..." data-error="Por favor, informe o email do usuário." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="usuarioNovoUsuario" class="control-label">Usuário:</label><input type="text" class="form-control " id="usuarioNovoUsuario" data-minlength="6" placeholder="Digite o usuário no mínimo 6 dígitos..." pattern="[a-zA-Z0-9]+" data-error="Por favor, informe o usuário no mínimo 6 dígitos." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="senhaNovoUsuario" class="control-label">Senha:</label><input type="password" class="form-control " id="senhaNovoUsuario" placeholder="Digite a senha..." data-minlength="6" data-error="Por favor, informe uma senha no minímo 6 dígitos." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="RsenhaNovoUsuario" class="control-label">Repita a senha:</label><input type="password" class="form-control " id="RsenhaNovoUsuario" placeholder="Digite novamente a senha..." data-match="#senhaNovoUsuario" data-error="Por favor, confirme sua senha." data-match-error="Ops, as senhas não correspondem." required ><div class="help-block with-errors"></div></div><div class="form-group"> <button type="submit" style="float: right;" class="btn btn-warning next-step">Continuar</button> </div>  </form>       </div>\n\
                                                   <div class="tab-pane"  id="progresso4">                          <form class="formNovoUsuario" data-toggle="validator" role="form">    <div class="form-group"><label for="grupoNovoMembro" class="control-label">Grupo:</label><select class="form-control" id="grupoNovoMembro" data-error="Por favor, informe o grupo do novo membro." required ><option></option><option value="entrevistadores">Entrevistadores</option><option value="gerenciadores">Gerenciadores</option></select><div class="help-block with-errors"></div></div>      <div class="form-group"> <button type="submit" style="float: right;" class="btn btn-warning next-step">Continuar</button> </div>  </form>       </div>\n\
                                                   <div class="tab-pane"  id="completo"><h3>Deseja realmente salvar o usuário?</h3><div class="form-group"> <button type="submit" style="float: right;" id="salvarNovoMembro" class="btn btn-warning">Salvar</button> </div></div><div class="clearfix"></div>');
                                                                           
                                
                $("#dataNascNovoUsuario").datepicker();
                //tamanho do icone do progresso de acordo com a quantidade
                $("#qtdProgresso li").css("width", 100/5  + "%");
                
                inputFocus = "#nomeNovoUsuario";
                $('.formNovoUsuario').validator();
               
                break;
            case 3:           

                //Adicionar agricultor, limpa o progresso e adiciona os itens
                $("#tituloProgresso").text("Novo agricultor");
                $("#progressoRef").text("Dados pessoais");
                
                $("#qtdProgresso").empty().append('<li role="presentation" class="active"><a href="#progresso1" data-toggle="tab" aria-controls="progresso1" role="tab" title="Dados pessoais"><span class="round-tab"><i class="fa fa-address-card-o"></i></span></a></li>\n\
                                                   <li role="presentation" class="disabled"><a href="#progresso2" data-toggle="tab" aria-controls="progresso2" role="tab" title="Dados familiares"><span class="round-tab"><i class="fa fa-child"></i></span></a></li>\n\
                                                   <li role="presentation" class="disabled"><a href="#progresso3" data-toggle="tab" aria-controls="progresso3" role="tab" title="Dados da propriedade"><span class="round-tab"><i class="fa fa-home"></i></span></a></li>\n\
                                                   <li role="presentation" class="disabled"><a href="#progresso4" data-toggle="tab" aria-controls="progresso4" role="tab" title="Dados da conta"><span class="round-tab"><i class="fa fa-sign-in"></i></span></a></li>\n\
                                                   <li role="presentation" class="disabled"><a href="#completo" data-toggle="tab" aria-controls="completo" role="tab" title="Salvar"><span class="round-tab"><i class="fa fa-save"></i></span></a></li>');

                $("#formProgresso").empty().append('<div class="tab-pane active" role="tabpanel" id="progresso1">       <form  data-toggle="validator" role="form" class="formNovoAgricultor" >       <div class="form-group"><label for="nomeAgricultor" class="control-label">Nome:</label>                                <input type="text" class="form-control " id="nomeAgricultor" placeholder="Digite o nome do agricultor..." pattern="[a-zA-ZçÇãÃâÂáÁàÀéÉêÊíÍìÌõÕôÔóÓúÚ]+" data-error="Por favor, informe o nome do agricultor." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="sobrenomeAgricultor" class="control-label">Sobrenome:</label><input type="text" class="form-control " id="sobrenomeAgricultor" placeholder="Digite o sobrenome do agricultor..." pattern="[a-zA-Z çÇãÃâÂáÁàÀéÉêÊíÍìÌõÕôÔóÓúÚ]+" data-error="Por favor, informe o sobrenome do agricultor." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="rgAgricultor" class="control-label">RG:</label><input type="text" class="form-control camposRG" pattern=".{9,}"  placeholder="99999.999-9" id="rgAgricultor" data-error="Por favor, informe o RG do agricultor." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="cpfAgricultor" class="control-label">CPF:</label><input type="text" class="form-control camposCPF" pattern=".{14,}" placeholder="999.999.999-99" id="cpfAgricultor" data-error="Por favor, informe o CPF do agricultor." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="apelidoAgricultor" class="control-label">Apelido:</label><input type="text" class="form-control" id="apelidoAgricultor" placeholder="Digite o apelido do agricultor..." ></div><div class="form-group"><label for="dataNascAgricultor" class="control-label">Data de nascimento:</label><input type="date" class="form-control camposData" placeholder="dd/mm/aaaa" pattern=".{10,}" id="dataNascAgricultor" data-error="Por favor, informe a data de nascimento do agricultor." required ><div class="help-block with-errors"></div></div><div class="form-group" id="genero"><input type="radio" name="genero" value="m" checked> Masculino<br><input type="radio" name="genero" value="f"> Feminino<br> <br></div><div class="form-group"><label for="telefone1Agricultor" class="control-label">Telefone 1:</label><input type="tel" class="form-control camposTelefone" placeholder="(xx) xxxxx-xxxx" pattern=".{14,}" id="telefone1Agricultor" data-error="Por favor, informe o telefone do agricultor." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="telefone2Agricultor" class="control-label">Telefone 2:</label><input type="tel" class="form-control camposTelefone" placeholder="(xx) xxxxx-xxxx" pattern=".{14,}" id="telefone2Agricultor" ></div><div class="form-group"><label for="escolaridadeAgricultor" class="control-label">Escolaridade:</label><select class="form-control" id="escolaridadeAgricultor"><option>Ensino fundamental</option><option>Ensino fundamental incompleto</option><option>Ensino médio</option><option>Ensino médio incompleto</option><option>Ensino superior</option><option>Ensino superior incompleto</option><option>Pós-graduação</option><option>Pós-graduação incompleta</option><option>Sem escolaridade</option></select></div><div class="form-group"><label for="estadocivilAgricultor" class="control-label">Estado civil:</label><select class="form-control" id="estadocivilAgricultor"><option>Solteiro(a)</option><option>Casado(a)</option><option>Divorciado(a)</option><option>Viúvo(a)</option><option>Separado(a)</option><option>Companheiro(a)</option></select></div>                    <div class="form-group"> <button type="submit" style="float: right;" class="btn btn-warning next-step" >Continuar</button> </div>                          </form>     </div>\n\
                                                    <div class="tab-pane"  id="progresso2">                             <form  data-toggle="validator" role="form" class="formNovoAgricultor" >     <div class="form-group"><label for="qtdIntegrantes" class="control-label">Quantidade de integrantes na família:</label><input type="number" class="form-control " id="qtdIntegrantes" placeholder="Digite a quantidade de integrantes..." min="0"                           data-error="Por favor, informe a quantidade de integrantes." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="qtdCriancas" class="control-label">Quantidade de crianças na família:</label><input type="number" class="form-control " id="qtdCriancas" placeholder="Digite a quantidade de crianças..." min="0" data-error="Por favor, informe a quantidade de crianças na família." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="qtdGravidas" class="control-label">Quantidade de grávidas na família:</label><input type="number" class="form-control " id="qtdGravidas" placeholder="Digite a quantidade de grávidas..." min="0" data-error="Por favor, informe a quantidade de grávidas na família." required ><div class="help-block with-errors"></div></div>   <div class="form-group"> <button type="submit" style="float: right;" class="btn btn-warning next-step">Continuar</button> </div>  </form>       </div>\n\
                                                    <div class="tab-pane"  id="progresso3">                             <form  data-toggle="validator" role="form" class="formNovoAgricultor" >    <div class="form-group"><label for="nomePropriedade" class="control-label">Nome da propriedade:</label><input type="text" class="form-control " id="nomePropriedade" placeholder="Digite o nome da propriedade..." pattern="[a-zA-Z çÇãÃâÂáÁàÀéÉêÊíÍìÌõÕôÔóÓúÚ]+" data-error="Por favor, informe o nome da propriedade." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="rua" class="control-label">Rua:</label><input type="text" class="form-control " id="rua" placeholder="Digite o nome da rua..." pattern="[a-zA-Z çÇãÃâÂáÁàÀéÉêÊíÍìÌõÕôÔóÓúÚ]+" data-error="Por favor, informe a rua da propriedade." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="numero" class="control-label">Número:</label><input type="number" min="0" class="form-control " id="numero" placeholder="Digite o número..." ></div><div class="form-group"><label for="bairro" class="control-label">Bairro:</label><input type="text" class="form-control " id="bairro" placeholder="Digite o nome do bairro..." ></div><div class="form-group"><label for="complemento" class="control-label">Complemento:</label><input type="text" class="form-control" id="complemento" placeholder="Digite o complemento..." ></div><div class="form-group"><label for="cep" class="control-label">Cep:</label><input type="text" class="form-control  camposCEP" id="cep" placeholder="Digite o cep da propriedade..." ></div><div class="form-group"><label for="gps_lat" class="control-label">Latitude:</label><input type="text" class="form-control " id="gps_lat" placeholder="Digite a latitude..." value="0"></div><div class="form-group"><label for="gps_long" class="control-label">Longitude:</label><input type="text" class="form-control " id="gps_long" placeholder="Digite a longitude..." value="0"></div><div class="form-group"><label for="paisAgricultor" class="control-label">País:</label><select class="form-control carregaPais" id="paisAgricultor" data-error="Por favor, informe o país da propriedade." required ><option></option></select><div class="help-block with-errors"></div></div><div class="form-group"><label for="estadoAgricultor" class="control-label">Estado:</label><select class="form-control carregaEstado" id="estadoAgricultor" data-error="Por favor, informe o estado da propriedade." required ><option></option></select><div class="help-block with-errors"></div></div><div class="form-group"><label for="cidadeAgricultor" class="control-label">Cidade:</label><select class="form-control  carregaCidade" id="cidadeAgricultor" data-error="Por favor, informe a cidade da propriedade." required ><option></option></select><div class="help-block with-errors"></div></div><div class="form-group"><label for="area" class="control-label">Área da propriedade:</label><input type="number" min="0" step="0.01" class="form-control " id="area" placeholder="Digite a área..." ></div><div class="form-group"><label for="unidademedida" class="control-label">Unidade de medida:</label><select class="form-control " id="unidademedida"><option value="3">Metros quadrados</option><option value="1">Alqueire</option><option value="2">Hectare</option></select></div><div class="form-group"><label for="areautilizavel" class="control-label">Área utilizada:</label><input type="number" class="form-control " id="areautilizavel" min="0" step="0.01" placeholder="Digite a área utilizada..." ></div><div class="form-group"><label for="unidadedemedidaau" class="control-label">Unidade de medida:</label><select class="form-control " id="unidadedemedidaau"><option value="3">Metros quadrados</option><option value="1">Alqueire</option><option value="2">Hectare</option></select></div><div class="form-group"> <button type="submit" style="float: right;" class="btn btn-warning next-step">Continuar</button> </div>  </form>       </div>\n\
                                                    <div class="tab-pane"  id="progresso4">                             <form  data-toggle="validator" role="form" class="formNovoAgricultor" >          <div class="form-group"><label for="emailAgricultor" class="control-label">Email:</label><input class="form-control " id="emailAgricultor" placeholder="Digite o email do agricultor..." type="email" data-error="Por favor, informe um e-mail correto." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="usuarioAgricultor" class="control-label">Usuário:</label><input type="text" class="form-control " id="usuarioAgricultor" data-minlength="6" placeholder="Digite o usuário no mínimo 6 dígitos..." pattern="[a-zA-Z0-9]+" data-error="Por favor, informe o usuário no mínimo 6 dígitos." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="senhaAgricultor" class="control-label">Senha:</label><input type="password" class="form-control " id="senhaAgricultor" placeholder="Digite a senha..." data-minlength="6" data-error="Por favor, informe uma senha no minímo 6 dígitos." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="RsenhaAgricultor" class="control-label">Repita a senha:</label><input type="password" class="form-control " id="RsenhaAgricultor" placeholder="Digite novamente a senha..." data-match="#senhaAgricultor" data-error="Por favor, confirme sua senha." data-match-error="Ops, as senhas não correspondem." required ><div class="help-block with-errors"></div></div><div class="form-group"> <button type="submit" style="float: right;" class="btn btn-warning next-step">Continuar</button> </div>  </form>       </div>\n\
                                                    <div class="tab-pane"  id="completo"><h3>Deseja realmente salvar o agricultor?</h3><div class="form-group"> <button type="submit" style="float: right;" id="salvarNovoAgricultor" class="btn btn-warning">Salvar</button> </div></div>                                         <div class="clearfix"></div>');

                $("#dataNascAgricultor").datepicker();
                
                //tamanho do icone do progresso de acordo com a quantidade
                $("#qtdProgresso li").css("width", 100/5  + "%");
                
                inputFocus = "#nomeAgricultor";
                $('.formNovoAgricultor').validator();
                break;
            default:
                alerta("Alerta!", "Erro de requisição de navegação!");

        }
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



/////////////////LISTAR
///////listar na tabela////////
//agricultores
function listarAgricultores(){
    var Sessao = getSessao();
    var envio = {
        metodo: "TODOS_DA_UNIDADE",
        usuario: Sessao.usuario,
        sessao: Sessao.sessao,
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
}


//carrega o progresso de distribuir cultivares
function progDistCultivares(){

    //Adicionar agricultor, limpa o progresso e adiciona os itens
    $("#tituloProgresso").text("Distribuir cultivares/Agricultor");
    $("#progressoRef").text("Agricultor");

    $("#qtdProgresso").empty().append('<li role="presentation" class="active"><a href="#progresso1" data-toggle="tab" aria-controls="progresso1" role="tab" title="Agricultor"><span class="round-tab"><i class="fa fa-user"></i></span></a></li>\n\
                                       <li role="presentation" class="disabled"><a href="#progresso2" data-toggle="tab" aria-controls="progresso2" role="tab" title="Cultivar"><span class="round-tab"><i class="fa fa-shopping-basket"></i></span></a></li>\n\
                                       <li role="presentation" class="disabled"><a href="#progresso3" data-toggle="tab" aria-controls="progresso3" role="tab" title="Quantidade"><span class="round-tab"><i class="fa fa-calendar-check-o "></i></span></a></li>\n\
                                       <li role="presentation" class="disabled"><a href="#completo" data-toggle="tab" aria-controls="completo" role="tab" title="Distribuir"><span class="round-tab"><i class="fa fa-save"></i></span></a></li>');

    $("#formProgresso").empty().append('<div class="tab-pane active" role="tabpanel" id="progresso1">       <form  data-toggle="validator" role="form" class="formDistribuir" >    <div class="form-group"><label for="campoBuscaAgri" class="control-label" id="labelProcuraAgricultor">Nome do agricultor:</label><input type="text" class="form-control " id="campoBuscaAgri" value="" placeholder="Digite o nome do agricultor..." data-error="Por favor, informe algum dado do agricultor." required><div class="help-block with-errors"></div></div>      <div class="form-group"><label for="listaPropriedades" class="control-label">Propriedade:</label><select class="form-control" id="listaPropriedades" value="" data-error="Por favor, informe a propriedade do agricultor." required></select><div class="help-block with-errors"></div></div>                    <div class="form-group"> <button type="submit" style="float: right;" class="btn btn-warning next-step" >Continuar</button> </div>                          </form>     </div>\n\
                                        <div class="tab-pane"  id="progresso2">                             <form  data-toggle="validator" role="form" class="formDistribuir" >    <div class="form-group"><label for="listaCultivares" class="control-label">Cultivar:</label><select class="form-control" id="listaCultivares" data-error="Por favor, informe algum dado do agricultor." required></select><div class="help-block with-errors"></div></div>   <div class="form-group"><label id ="labelQtdCultivarDist" for="qtdCultivarDist" class="control-label">Quantidade:</label><input type="number" class="form-control " min="0.01" id="qtdCultivarDist" placeholder="Digite a quantidade..." step="0.01" data-error="Por favor, informe a quantidade." required ><div class="help-block with-errors"></div></div>   <div class="form-group"> <button type="submit" style="float: right;" class="btn btn-warning next-step">Continuar</button> </div>  </form>       </div>\n\
                                        <div class="tab-pane"  id="progresso3">                             <form  data-toggle="validator" role="form" class="formDistribuir" >    <div class="form-group"><label for="safraDist" class="control-label" >Safra:</label><input type="text" class="form-control camposSafra" id="safraDist" value="" placeholder="id/ano .... 01/2017" pattern=".{7,}" data-error="Por favor, informe a safra." required><div class="help-block with-errors"></div></div><div class="form-group"><label for="datarecebDist" class="control-label">Data do recebimento:</label><input type="text" class="form-control camposData" placeholder="dd/mm/aaaa" pattern=".{10,}" id="datarecebDist" data-error="Por favor, informe a data do recebimento." required ><div class="help-block with-errors"></div></div>                  <div class="form-group"> <button type="submit" style="float: right;" class="btn btn-warning next-step">Continuar</button> </div>  </form>       </div>\n\
                                        <div class="tab-pane"  id="completo"><h3>Deseja realmente distribuir este cultivar?</h3><div class="form-group"> <button type="submit" style="float: right;" id="distribuirCultivar" class="btn btn-warning">Distribuir</button> </div></div>                                         <div class="clearfix"></div>');

$("#datarecebDist").datepicker();
    //tamanho do icone do progresso de acordo com a quantidade
    $("#qtdProgresso li").css("width", 100/4  + "%");

    $('.formDistribuir').validator();

           
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

//cadastro de um novo agricultor parte de dados pessoais
$(document).on("submit", ".formNovoAgricultor", function(e){

        
    if(!e.isDefaultPrevented()){
        continuarProgresso();
    }
    return false;
});

//cadastro de um novo agricultor parte de dados pessoais
$(document).on("submit", ".formNovoUsuario", function(e){

        
    if(!e.isDefaultPrevented()){
        continuarProgresso();
    }
    return false;
});

//cadastro de uma nova unidade, chama a funcao que fara o json
$(document).on("click", "#salvarNovoMembro", function(){

    salvarNovoMembro();

    return false;
});

//manda a requisição para o servidor com os dados referentes a uma novo usuario
function salvarNovoMembro(){
//    alert("mandar requisição");
    
var Sessao = getSessao();
    

    var envio = {
//        metodo: "inserirusuario",
        usuario: Sessao.usuario,
        sessao: Sessao.sessao,
        metodo: "NOVO_MEMBRO",
        cidade_idcidade : $("#cidadeNovoUsuario").prop("value"),
        rua: $("#ruaNovoUsuario").val(),
        gps_lat: $("#gps_latNovoUsuario").val(),
        gps_long: $("#gps_longNovoUsuario").val(),
        bairro: $("#bairroNovoUsuario").val(),
        complemento: $("#complementoNovoUsuario").val(),
        cep: $("#cepNovoUsuario").val(),
        numero: $("#numeroNovoUsuario").val(),
        escolaridade_idescolaridade: $("#escolaridadeNovoUsuario")[0].selectedIndex,
        estadocivil_idestadocivil: $("#estadocivilNovoUsuario")[0].selectedIndex,
        nome: $("#nomeNovoUsuario").val(),
        sobrenome: $("#sobrenomeNovoUsuario").val(),
        apelido: $("#apelidoNovoUsuario").val(),
        cpf: $("#cpfNovoUsuario").val(),
        rg: $("#rgNovoUsuario").val(),
        datanascimento: $("#dataNascNovoUsuario").val(),
        sexo: $("#generoNovoUsuario input:checked").prop("value"),
        telefone1: $("#telefone1NovoUsuario").val(),
        telefone2: $("#telefone2NovoUsuario").val(),
        email: $("#emailNovoUsuario").val(),
        usuario_login: $("#usuarioNovoUsuario").val(),
        senha: $.sha256($("#senhaNovoUsuario").val()),
        grupo: $("#grupoNovoMembro").prop("value"),
        unidade_idunidade: Sessao.idunidade
    };
    
    alert(JSON.stringify(envio));
    //chama a requisicao do servidor, o resultado é listado em uma tabela
    requisicao(true, "usuario/inserir", envio, function(dadosRetorno) {
        if(dadosRetorno.sucesso){
            
            $(".painelCarregando").fadeOut(400);
            alerta("Alerta!", dadosRetorno.mensagem);
                preEventosPaginaCadastros($(this), "fa-users", "Agricultores", "Listar todos os agricultores", "Adicionar um novo agricultor", "Editar informações de um agricultor", "Excluir um agricultor");
            }else{
            $(".painelCarregando").fadeOut(400);
            alerta("Alerta!", dadosRetorno.mensagem);
        }
        //atualiza a sessao
        updateSessao(dadosRetorno.sessao);
    });

}


$(document).on("click", "#salvarNovoAgricultor", function(){
    salvarNovoAgricultor();
    
    return false;
});


function salvarNovoAgricultor(){
    
    var Sessao = getSessao();
    

    var envio = {
//        metodo: "inseriragricultor",
        usuario: Sessao.usuario,
        sessao: Sessao.sessao,
        metodo: "agricultor",
        cidade_idcidade : $("#cidadeAgricultor").prop("value"),
        rua: $("#rua").val(),
        gps_lat: $("#gps_lat").val(),
        gps_long: $("#gps_long").val(),
        bairro: $("#bairro").val(),
        complemento: $("#complemento").val(),
        cep: $("#cep").val(),
        numero: $("#numero").val(),
        escolaridade_idescolaridade: $("#escolaridadeAgricultor")[0].selectedIndex,
        estadocivil_idestadocivil: $("#estadocivilAgricultor")[0].selectedIndex,
        nome: $("#nomeAgricultor").val(),
        sobrenome: $("#sobrenomeAgricultor").val(),
        apelido: $("#apelidoAgricultor").val(),
        cpf: $("#cpfAgricultor").val(),
        rg: $("#rgAgricultor").val(),
        datanascimento: $("#dataNascAgricultor").val(),
        sexo: $("#genero input:checked").prop("value"),
        telefone1: $("#telefone1Agricultor").val(),
        telefone2: $("#telefone2Agricultor").val(),
        email: $("#emailAgricultor").val(),
        qtdIntegrantes: $("#qtdIntegrantes").val(),
        qtdCriancas: $("#qtdCriancas").val(),
        qtdGravidas: $("#qtdGravidas").val(),
        usuario_login: $("#usuarioAgricultor").val(),
        senha: $.sha256($("#senhaAgricultor").val()),
        unidade_idunidade: Sessao.idunidade,
        nomepropriedade: $("#nomePropriedade").val(),
        area: $("#area").val(),
        unidadedemedida: $("#unidademedida").prop("value"),
        areautilizavel: $("#areautilizavel").val(),
        unidadedemedidaau: $("#unidadedemedidaau").prop("value")
    };
    
  alert(JSON.stringify(envio));
    //chama a requisicao do servidor, o resultado é listado em uma tabela
    requisicao(true, "agricultor/inserir", envio, function(dadosRetorno) {
        if(dadosRetorno.sucesso){
            $(".painelCarregando").fadeOut(400);
            alerta("Alerta!", dadosRetorno.mensagem);
            preEventosPaginaCadastros($(this), "fa-users", "Agricultores", "Listar todos os agricultores", "Adicionar um novo agricultor", "Editar informações de um agricultor", "Excluir um agricultor");
        }else{
            $(".painelCarregando").fadeOut(400);
            alerta("Alerta!", dadosRetorno.mensagem);
        }
        //atualiza a sessao
        updateSessao(dadosRetorno.sessao);
    });
}

//busca no servidor dados do agricultor para ser apresentado em um modal
function carregaMembro(idClicado, opcao){
    var Sessao = getSessao();
    var envio = {
        metodo: "GET_MEMBRO",
        idpessoa: idClicado,
        usuario: Sessao.usuario,
        sessao: Sessao.sessao
    };
    
    //chama a requisicao do servidor, o resultado é listado em uma tabela
    requisicao(true, "usuario/buscar", envio, function(dadosRetorno) {
        if(dadosRetorno.sucesso){
            
            //testa qual opcao sera, editar ou mostrar
            if(opcao === "modal"){
                //carrega atributos no painel modal que sera exibido
                $("#modalTitulo").text("Equipe");
                $("#itensModal").append("<h3>"+dadosRetorno.data.nome+"</h3><h4> Sobrenome: "+dadosRetorno.data.sobrenome+"</h4><h4>Apelido: "+dadosRetorno.data.apelido+"</h4><h4> CPF: "+dadosRetorno.data.cpf+"</h4><h4> RG: "+dadosRetorno.data.rg+"</h4><h4> Data de nascimento: "+dadosRetorno.data.datanascimento+"</h4><h4> Sexo: "+dadosRetorno.data.sexo+"</h4><h4> Telefone1: "+dadosRetorno.data.telefone1+"</h4><h4>Telefone2:"+dadosRetorno.data.telefone2+"</h4><h4>Email: "+dadosRetorno.data.email+"</h4><h4>Estado civil: "+dadosRetorno.data.estadocivil+"</h4><h4>Escolaridade: "+dadosRetorno.data.escolaridade+"</h4><h4>Grupo: "+dadosRetorno.data.grupo+"</h4><h4>Unidade: "+dadosRetorno.data.nomeunidade+"</h4>");
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

//busca no servidor dados do agricultor para ser apresentado em um modal
function carregaAgricultor(idClicado, opcao){
    var Sessao = getSessao();
    var envio = {
        metodo: "GET_POR_ID",
        idpessoa: idClicado,
        usuario: Sessao.usuario,
        sessao: Sessao.sessao
    };
    
    
    //chama a requisicao do servidor, o resultado é listado em uma tabela
    requisicao(true, "agricultor/buscar", envio, function(dadosRetorno) {
        if(dadosRetorno.sucesso){
            //testa qual opcao sera, editar ou mostrar
            if(opcao === "modal"){
                //carrega atributos no painel modal que sera exibido
                $("#modalTitulo").text("Agricultor");
                $("#itensModal").append("<h3>"+dadosRetorno.data.nome+"</h3><h4> Sobrenome: "+dadosRetorno.data.sobrenome+"</h4><h4>Apelido: "+dadosRetorno.data.apelido+"</h4><h4> CPF: "+dadosRetorno.data.cpf+"</h4><h4> RG: "+dadosRetorno.data.rg+"</h4><h4> Data de nascimento: "+dadosRetorno.data.datanascimento+"</h4><h4> Sexo: "+dadosRetorno.data.sexo+"</h4><h4> Telefone1: "+dadosRetorno.data.telefone1+"</h4><h4>Telefone2"+dadosRetorno.data.telefone2+"</h4><h4>Email: "+dadosRetorno.data.email+"</h4><h4>Quantidade de crianças: "+dadosRetorno.data.qtdcriancas+"</h4><h4>Quantidade de integrantes: "+dadosRetorno.data.qtdintegrantes+"</h4><h4>Quantidade de grávidas: "+dadosRetorno.data.qtdgravidas+"</h4><h4>Estado civil: "+dadosRetorno.data.estadocivil+"</h4><h4>Escolaridade: "+dadosRetorno.data.escolaridade+"</h4>");
            }else if(opcao === "editar"){
                $("#divItens").empty().append('<h2 class="sub-header">Editar agricultor</h2>         <form  data-toggle="validator" role="form" id="salvarEditAgri" >           <div class="form-group"><label for="nomeAgricultor" class="control-label">Nome:</label><input type="text" class="form-control" id="nomeAgricultor" placeholder="Digite o nome do agricultor..." value="'+dadosRetorno.data.nome+'" data-error="Por favor, informe o nome correto do agricultor." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="sobrenomeAgricultor" class="control-label">Sobrenome:</label><input type="text" class="form-control" id="sobrenomeAgricultor" placeholder="Digite o sobrenome do agricultor..." value="'+dadosRetorno.data.sobrenome+'"></div><div class="form-group"><label for="rgAgricultor" class="control-label">RG:</label><input type="text" class="form-control" id="rgAgricultor" placeholder="Digite o rg do agricultor..." value="'+dadosRetorno.data.rg+'"></div><div class="form-group"><label for="cpfAgricultor" class="control-label">CPF:</label><input type="text" class="form-control" id="cpfAgricultor" placeholder="Digite o cpf do agricultor..." value="'+dadosRetorno.data.cpf+'" ></div><div class="form-group"><label for="apelidoAgricultor" class="control-label">Apelido:</label><input type="text" class="form-control" id="apelidoAgricultor" placeholder="Digite o apelido do agricultor..." value="'+dadosRetorno.data.apelido+'" ></div><div class="form-group"><label for="dataNascAgricultor" class="control-label">Data de nascimento:</label><input type="text" class="form-control" id="dataNascAgricultor" placeholder="Digite a data de nascimento do agricultor..." value="'+dadosRetorno.data.datanascimento+'" ></div><div class="form-group" id="genero"><input type="radio" name="genero" value="m" checked> Masculino<br><input type="radio" name="genero" value="f"> Feminino<br> <br></div><div class="form-group"><label for="telefone1Agricultor" class="control-label">Telefone 1:</label><input type="text" class="form-control" id="telefone1Agricultor" placeholder="(xx) xxxxx-xxxx" value="'+dadosRetorno.data.telefone1+'" ></div><div class="form-group"><label for="telefone2Agricultor" class="control-label">Telefone 2:</label><input type="text" class="form-control" id="telefone2Agricultor" placeholder="Digite o telefone 2 do agricultor..." value="'+dadosRetorno.data.telefone2+'" ></div><div class="form-group"><label for="emailAgricultor" class="control-label">Email:</label><input type="text" class="form-control" id="emailAgricultor" placeholder="Digite o email do agricultor..." value="'+dadosRetorno.data.email+'" ></div><div class="form-group"><label for="escolaridadeAgricultor" class="control-label">Escolaridade:</label><select class="form-control " id="escolaridadeAgricultor" value="'+dadosRetorno.data.escolaridade+'"><option>Ensino fundamental</option><option>Ensino fundamental incompleto</option><option>Ensino médio</option><option>Ensino médio incompleto</option><option>Ensino superior</option><option>Ensino superior incompleto</option><option>Pós-graduação</option><option>Pós-graduação incompleta</option><option>Sem escolaridade</option></select></div><div class="form-group"><label for="estadocivilAgricultor" class="control-label">Estado civil:</label><select class="form-control " id="estadocivilAgricultor" value="'+dadosRetorno.data.estadocivil+'"><option>Solteiro(a)</option><option>Casado(a)</option><option>Divorciado(a)</option><option>Viúvo(a)</option><option>Separado(a)</option><option>Companheiro(a)</option></select></div><hr><button type="submit" class="btn btn-warning">Salvar <span class="fa fa-save" aria-hidden="true"></span></button></form>');
                $('#salvarEditAgri').validator();
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