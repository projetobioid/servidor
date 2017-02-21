var ipServidor = 'localhost:8080'; //sistema em producao
//var ipServidor = "187.19.101.252:8082"; //sistema rodando fora
//var ipServidor = "10.1.2.52:8080"; //sistema teste interno







$(document).on("keypress", ".camposCPF", function(evt)
{
//        $('.numeroInt').mask('000000000000000');
        $(this).mask('000.000.000-00');//,{'translation': {'u': {pattern: /[0123456789.-]/, optional: true, recursive: true}}};

//        
//        $('#icep').mask('00000-000', {reverse: true});
//        $('.telefone').mask('(00) #0000-0000');
//        $('#inome').mask('aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa',{'translation': {'a': {pattern: /[A-Za-zçÇãÃâÂáÁàÀéÉíÍõÕôÔúÚ]/}}});
//        $(".palavras").mask('aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa',{'translation': {'a': {pattern: /[A-Za-zçÇãÃâÂáÁàÀéÉíÍõÕôÔúÚ\s]/}}});
//        $("#iusuario").mask('aaaaaaaaaaaaaaa',{'translation': {'a': {pattern: /[A-Za-z0-9_-]/, optional: true, recursive: true}}});
//        $("#irua").mask('aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa',{'translation': {'a': {pattern: /[A-Za-z0-9çÇãÃâÂáÁàÀéÉíÍõÕôÔúÚ'\s]/, optional: true, recursive: true}}});
//        //$("#iemail").mask('a',{'translation': {'a': {pattern: /[A-Za-z@-_.0-9]/}}});
//        $("#iemail").mask('aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa',{'translation': {'a': {pattern: /[A-Za-z@-_.0-9]/, optional: true, recursive: true}}});

     });

$(document).on("keypress", ".camposRG", function(evt)
{
    $(this).mask('00000.000-0');

});

$(document).on("keypress", ".camposData", function(evt)
{
    $(this).mask('00/00/0000');

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



//dois clicks tabela aparece modal com atributos referentes ao item selecionado
$(document).on("dblclick", "#divItens tr", function(evt)
{
    $("#modalTitulo").text("");
    $("#itensModal").empty();
    
    

            switch (verificaPagina()){
                        case 1:
                            carregaAgricultor($(this).find('td:eq(0)').html(), "modal");  
                        break;
                        case 2:
                        case 5:
                            carregaCultivar($(this).find('td:eq(0)').html(), "modal");
                        break;
                        case 3:
                            carregaUnidade($(this).find('td:eq(0)').html(), "modal");
                        break;
                        case 4:
                            carregaUsuario($(this).find('td:eq(0)').html(), "modal");
                        break;
                //        case 5:
                //            carregaCultivar($(this).find('td:eq(0)').html(), "modal");
                //        break;
                        default:
                            alerta("Alerta!", "Erro de requisição de navegação!"); 
        
    }

    
    
    $('#modalApresentacao').modal('toggle');
    return false;
});

///marcacao da tabela lista de agricultores/ cultivares/ unidades/ usuarios

$(document).on("click", "tbody > tr", function(evt)
{
   

//    //marcar de laranja a row
    if($(this).hasClass("warning")){
        $(this).removeClass("warning");
        //se estiver na pagina estoque desativa o botao saida de estoque
        if(verificaPagina() === 5 ){
            $("#saidaEstoque").attr("disabled", true);
        }
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
        
        //verifica se esta na pagina estoque dai ativa o botao
        if(verificaPagina() === 5 ){
            $("#saidaEstoque").removeAttr("disabled");
        }else{
            $("#Alertar").fadeOut(400);
        }
    }

     return false;
});

//busca no servidor dados do agricultor para ser apresentado em um modal
function carregaAgricultor(idClicado, opcao){
    var Sessao = getSessao();
    var envio = {
        metodo: "get_agricultor",
        idpessoa: idClicado,
        id: Sessao.idlogin,
        sessao: Sessao.sessao
    };
    
    
    //chama a requisicao do servidor, o resultado é listado em uma tabela
    requisicao(true, "pessoa/buscar", envio, function(dadosRetorno) {
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

//busca no servidor dados do cultivar para ser apresentado em um modal
function carregaCultivar(idClicado, opcao){
    var Sessao = getSessao();
    var envio = {
        metodo: "get_cultivar",
        idcultivar: idClicado,
        id: Sessao.idlogin,
        sessao: Sessao.sessao
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

//busca no servidor dados da unidade para ser apresentado em um modal
function carregaUnidade(idClicado, opcao){
    var Sessao = getSessao();
    var envio = {
        metodo: "get_unidade",
        idunidade: idClicado,
        id: Sessao.idlogin,
        sessao: Sessao.sessao
    };
    
    //chama a requisicao do servidor, o resultado é listado em uma tabela
    requisicao(true, "unidade/buscar", envio, function(dadosRetorno) {
        if(dadosRetorno.sucesso){
            
            //testa qual opcao sera, editar ou mostrar
            if(opcao === "modal"){
                //carrega atributos no painel modal que sera exibido
                $("#modalTitulo").text("Unidade");
                $("#itensModal").append('<h3>'+dadosRetorno.data.nomeunidade+'</h3><h4> Cidade: '+dadosRetorno.data.nomecidade+'</h4><h4> Estado: '+dadosRetorno.data.nomeestado+'</h4><h4> País: '+dadosRetorno.data.nomepais+'</h4><h4> Telefone1: '+dadosRetorno.data.telefone1+'</h4><h4> Telefone2: '+dadosRetorno.data.telefone2+'</h4><h4> Email: '+dadosRetorno.data.email+'</h4><h4> Cnpj: '+dadosRetorno.data.cnpj+'</h4><h4> Razão social: '+dadosRetorno.data.razao_social+'</h4><h4> Nome fantasia: '+dadosRetorno.data.nome_fanta+'</h4><h4> Rua: '+dadosRetorno.data.rua+'</h4><h4> Bairro: '+dadosRetorno.data.bairro+'</h4><h4> Complemento: '+dadosRetorno.data.complemento+'</h4><h4> Latitude: '+dadosRetorno.data.gps_lat+'</h4><h4> Longitude: '+dadosRetorno.data.gps_long+'</h4>');
            }else if(opcao === "editar"){
                $("#divItens").empty().append('<h2 class="sub-header">Editar unidade</h2><form  data-toggle="validator" role="form" id="salvarEditUnidade"><div class="form-group"><label for="nomeUnidade" class="control-label">Nome unidade:</label><input type="text" class="form-control" id="nomeUnidade" placeholder="Digite o nome da unidade..." value="'+dadosRetorno.data.nomeunidade+'"></div><div class="form-group"><label for="cnpj" class="control-label">CNPJ:</label><input type="text" class="form-control" id="cnpj" placeholder="Digite o cnpj..." value="'+dadosRetorno.data.cnpj+'"></div><div class="form-group"><label for="razaoSocial" class="control-label">Razão social:</label><input type="text" class="form-control" id="razaoSocial" placeholder="Digite a razão social..." value="'+dadosRetorno.data.razao_social+'"></div><div class="form-group"><label for="telefone1" class="control-label">Telefone 1:</label><input type="text" class="form-control" id="telefone1" placeholder="Digite o telefone 1..." value="'+dadosRetorno.data.telefone1+'"></div><div class="form-group"><label for="telefone2" class="control-label">Telefone 2:</label><input type="text" class="form-control" id="telefone2" placeholder="Digite o telefone 2..." value="'+dadosRetorno.data.telefone2+'"></div><div class="form-group"><label for="email" class="control-label">Email:</label><input type="text" class="form-control" id="email" placeholder="Digite o email..."value="'+dadosRetorno.data.email+'"></div><div class="form-group"><label for="rua" class="control-label">Rua:</label><input type="text" class="form-control" id="rua" placeholder="Digite a rua..." value="'+dadosRetorno.data.rua+'"></div><div class="form-group"><label for="bairro" class="control-label">Bairro:</label><input type="text" class="form-control" id="bairro" placeholder="Digite o bairro..." value="'+dadosRetorno.data.bairro+'"></div><div class="form-group"><label for="numero" class="control-label">Número:</label><input type="text" class="form-control" id="numero" placeholder="Digite o número..." value="'+dadosRetorno.data.numero+'"></div><div class="form-group"><label for="complemento" class="control-label">Complemento:</label><input type="text" class="form-control" id="complemento" placeholder="Digite o complemento..." value="'+dadosRetorno.data.complemento+'"></div><div class="form-group"><label for="gps_lat" class="control-label">Latitude:</label><input type="text" class="form-control" id="gps_lat" placeholder="Digite a latitude..." value="'+dadosRetorno.data.gps_lat+'"></div><div class="form-group"><label for="gps_long" class="control-label">Longitude:</label><input type="text" class="form-control" id="gps_long" placeholder="Digite a longitude..." value="'+dadosRetorno.data.gps_long+'"></div><div class="form-group"><label for="pais" class="control-label">País:</label><select class="form-control " id="pais" value="'+dadosRetorno.data.nomepais+'"><option>Brasil</option></select></div><div class="form-group"><label for="estado" class="control-label">Estado:</label><select class="form-control " id="estado value="'+dadosRetorno.data.nomeestado+'""><option>Paraná</option></select></div><div class="form-group"><label for="cidade" class="control-label">Cidade:</label><select class="form-control " id="cidade" value="'+dadosRetorno.data.nomecidade+'"><option>Cascavel</option></select></div><hr><button type="submit" class="btn btn-warning">Salvar <span class="fa fa-save" aria-hidden="true"></span></button></form>');  
                $('#salveEditUnidade').validator();
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
function carregaUsuario(idClicado, opcao){
    var Sessao = getSessao();
    var envio = {
        metodo: "get_usuario",
        idpessoa: idClicado,
        id: Sessao.idlogin,
        sessao: Sessao.sessao
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

    
///////*painel lateral*////////////


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
        
        if(titulo === "Distribuir cultivares"){
            inputFocus = progDistCultivares();
        }else if(titulo === "Estoque da unidade"){
            progEstoqueUnidade();
        }else if(titulo === "Relatar safra"){
            alerta("Alerta!", titulo + " em construção");
        }else if(titulo === "Sincronizar app"){
            alerta("Alerta!", titulo + " em construção");
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

//painel escolha de opcoes de abrir pages
$(document).on("click", ".a", function(evt)
{
    
    chamarMenu($(this));
    
    return false;
});



function chamarMenu(escolha){
        var item = escolha.text();
   // $("#divItens").fadeOut(400);
    
    if(item === "Distribuir cultivares"){
        preEventosPaginaGerenciamento(escolha, "fa-cart-arrow-down", "Distribuir cultivares");
        
    }else if(item === "Estoque da unidade"){
        preEventosPaginaGerenciamento(escolha, "fa-cubes", "Estoque da unidade");
        
    }else if(item === "Relatar safra"){
        preEventosPaginaGerenciamento(escolha, "fa-commenting", "Relatar safra");
        
    }else if(item === "Sincronizar app"){
        preEventosPaginaGerenciamento(escolha, "fa-cloud-upload", "Sincronizar app");
        
    }else if(item === "Agricultores"){
        preEventosPaginaCadastros(escolha, "fa-users", "Agricultores", "Listar todos os agricultores", "Adicionar um novo agricultor", "Editar informações de um agricultor", "Excluir um agricultor");
    }else if(item === "Cultivares"){
        preEventosPaginaCadastros(escolha, "fa-leaf", "Cultivares", "Listar todos os cultivares", "Adicionar um novo cultivar", "Editar informações de um cultivar", "Excluir um cultivar");
    }else if(item === "Unidades"){
        preEventosPaginaCadastros(escolha, "fa-university", "Unidades", "Listar todas as unidades", "Adicionar uma nova unidade", "Editar informações de uma unidade", "Excluir uma unidade");
    }else if(item === "Usuários"){
        preEventosPaginaCadastros(escolha, "fa-user-secret","Usuários", "Listar todos os usuários", "Adicionar um novo usuário", "Editar um usuário", "Excluir um usuário");
    }
}










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
        case 2:
            listarUnidades();
        break;
        case 3:
            listarUsuarios();
        break;
        default:
            alerta("Alerta!", "Erro de requisição de navegação!"); 
        
    }
      
}

function formatandoCampo(mascara, documento){   
                var i = documento.value.length;   
                var saida = mascara.substring(0,1);   
                var texto = mascara.substring(i);     
             if (texto.substring(0,1) !== saida){
                documento.value += texto.substring(0,1);
             }
         }
 
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
                                                    <div class="tab-pane"  id="progresso3">                             <form  data-toggle="validator" role="form" class="formNovoAgricultor" >    <div class="form-group"><label for="nomePropriedade" class="control-label">Nome da propriedade:</label><input type="text" class="form-control " id="nomePropriedade" placeholder="Digite o nome da propriedade..." pattern="[a-zA-Z çÇãÃâÂáÁàÀéÉêÊíÍìÌõÕôÔóÓúÚ]+" data-error="Por favor, informe o nome da propriedade." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="rua" class="control-label">Rua:</label><input type="text" class="form-control " id="rua" placeholder="Digite o nome da rua..." pattern="[a-zA-Z çÇãÃâÂáÁàÀéÉêÊíÍìÌõÕôÔóÓúÚ]+" data-error="Por favor, informe a rua da propriedade." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="numero" class="control-label">Número:</label><input type="number" min="0" class="form-control " id="numero" placeholder="Digite o número..." ></div><div class="form-group"><label for="bairro" class="control-label">Bairro:</label><input type="text" class="form-control " id="bairro" placeholder="Digite o nome do bairro..." ></div><div class="form-group"><label for="complemento" class="control-label">Complemento:</label><input type="text" class="form-control" id="complemento" placeholder="Digite o complemento..." ></div><div class="form-group"><label for="cep" class="control-label">Cep:</label><input type="text" class="form-control  camposCEP" id="cep" placeholder="Digite o cep da propriedade..." ></div><div class="form-group"><label for="gps_lat" class="control-label">Latitude:</label><input type="text" class="form-control " id="gps_lat" placeholder="Digite a latitude..." value="0"></div><div class="form-group"><label for="gps_long" class="control-label">Longitude:</label><input type="text" class="form-control " id="gps_long" placeholder="Digite a longitude..." value="0"></div><div class="form-group"><label for="paisAgricultor" class="control-label">País:</label><select class="form-control carregaPais" id="paisAgricultor" data-error="Por favor, informe o país da propriedade." required ><option></option></select><div class="help-block with-errors"></div></div><div class="form-group"><label for="estadoAgricultor" class="control-label">Estado:</label><select class="form-control carregaEstado" id="estadoAgricultor" data-error="Por favor, informe o estado da propriedade." required ><option></option></select><div class="help-block with-errors"></div></div><div class="form-group"><label for="cidadeAgricultor" class="control-label">Cidade:</label><select class="form-control  carregaCidade" id="cidadeAgricultor" data-error="Por favor, informe a cidade da propriedade." required ><option></option></select><div class="help-block with-errors"></div></div><div class="form-group"><label for="unidade" class="control-label">Unidade:</label><select class="form-control  carregaUnidades" id="unidade" data-error="Por favor, informe a unidade mais próxima." required ><option></option></select><div class="help-block with-errors"></div></div><div class="form-group"><label for="area" class="control-label">Área da propriedade:</label><input type="number" min="0" step="0.01" class="form-control " id="area" placeholder="Digite a área..." ></div><div class="form-group"><label for="unidademedida" class="control-label">Unidade de medida:</label><select class="form-control " id="unidademedida"><option value="3">Metros quadrados</option><option value="1">Alqueire</option><option value="2">Hectare</option></select></div><div class="form-group"><label for="areautilizavel" class="control-label">Área utilizada:</label><input type="number" class="form-control " id="areautilizavel" min="0" step="0.01" placeholder="Digite a área utilizada..." ></div><div class="form-group"><label for="unidadedemedidaau" class="control-label">Unidade de medida:</label><select class="form-control " id="unidadedemedidaau"><option value="3">Metros quadrados</option><option value="1">Alqueire</option><option value="2">Hectare</option></select></div><div class="form-group"> <button type="submit" style="float: right;" class="btn btn-warning next-step">Continuar</button> </div>  </form>       </div>\n\
                                                    <div class="tab-pane"  id="progresso4">                             <form  data-toggle="validator" role="form" class="formNovoAgricultor" >          <div class="form-group"><label for="emailAgricultor" class="control-label">Email:</label><input class="form-control " id="emailAgricultor" placeholder="Digite o email do agricultor..." type="email" data-error="Por favor, informe um e-mail correto." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="usuarioAgricultor" class="control-label">Usuário:</label><input type="text" class="form-control " id="usuarioAgricultor" data-minlength="6" placeholder="Digite o usuário no mínimo 6 dígitos..." pattern="[a-zA-Z0-9]+" data-error="Por favor, informe o usuário no mínimo 6 dígitos." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="senhaAgricultor" class="control-label">Senha:</label><input type="password" class="form-control " id="senhaAgricultor" placeholder="Digite a senha..." data-minlength="6" data-error="Por favor, informe uma senha no minímo 6 dígitos." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="RsenhaAgricultor" class="control-label">Repita a senha:</label><input type="password" class="form-control " id="RsenhaAgricultor" placeholder="Digite novamente a senha..." data-match="#senhaAgricultor" data-error="Por favor, confirme sua senha." data-match-error="Ops, as senhas não correspondem." required ><div class="help-block with-errors"></div></div><div class="form-group"> <button type="submit" style="float: right;" class="btn btn-warning next-step">Continuar</button> </div>  </form>       </div>\n\
                                                    <div class="tab-pane"  id="completo"><h3>Deseja realmente salvar o agricultor?</h3><div class="form-group"> <button type="submit" style="float: right;" id="salvarNovoAgricultor" class="btn btn-warning">Salvar</button> </div></div>                                         <div class="clearfix"></div>');

                $("#dataNascAgricultor").datepicker();
                
                //tamanho do icone do progresso de acordo com a quantidade
                $("#qtdProgresso li").css("width", 100/5  + "%");
                
                inputFocus = "#nomeAgricultor";
                $('.formNovoAgricultor').validator();
            break;
            case 2:
                
                //Adicionar agricultor, limpa o progresso e adiciona os itens
                $("#tituloProgresso").text("Novo cultivar");
                $("#progressoRef").text("Dados do cultivar");
                
                $("#qtdProgresso").empty().append('<li role="presentation" class="active"><a href="#progresso1" data-toggle="tab" aria-controls="progresso1" role="tab" title="Dados cultivar"><span class="round-tab"><i class="fa fa-address-card-o"></i></span></a></li>\n\
                                                   <li role="presentation" class="disabled"><a href="#progresso2" data-toggle="tab" aria-controls="progresso2" role="tab" title="Imagem cultivar"><span class="round-tab"><i class="fa fa-child"></i></span></a></li>\n\
                                                   <li role="presentation" class="disabled"><a href="#completo" data-toggle="tab" aria-controls="completo" role="tab" title="Salvar"><span class="round-tab"><i class="fa fa-save"></i></span></a></li>');

                $("#formProgresso").empty().append('<div class="tab-pane active" role="tabpanel" id="progresso1">   <form  data-toggle="validator" role="form" class="formNovoCultivar" >     <div class="form-group"><label for="nomeCultivar" class="control-label">Nome cultivar:</label><input type="text" class="form-control inputDadosCultivar" id="nomeCultivar" placeholder="Digite o nome do cultivar..." pattern="[a-zA-Z çÇãÃâÂáÁàÀéÉêÊíÍìÌõÕôÔóÓúÚ]+" data-error="Por favor, informe o nome do cultivar." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="umNovoCultivar" class="control-label">Unidade de medida:</label><select class="form-control inputDadosCultivar" id="umNovoCultivar"><option value="7">Kilo(s)</option><option value="6">Maniva(s)</option><option value="5">Rama(s)</option></select></div><div class="form-group"><label for="pesoSaca" class="control-label">Peso da saca(kg):</label><input type="number" class="form-control inputDadosCultivar" id="pesoSaca" placeholder="Digite o peso da saca..." step="0.01" min="0.01" data-error="Por favor, informe o peso da saca do cultivar." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="tempoColheita" class="control-label">Dias para relatar a colheita:</label><input type="number" class="form-control inputDadosCultivar" min="1" id="tempoColheita" placeholder="Digite o tempo de colheita..." data-error="Por favor, informe o tempo de colheita do cultivar." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="tempoDestinacao" class="control-label">Dias para relatar a destinação:</label><input type="number" class="form-control inputDadosCultivar" min="1" id="tempoDestinacao" placeholder="Digite o tempo da destinação..." data-error="Por favor, informe o tempo de destinação do cultivar." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="valorNutricional" class="control-label">Valor nutricional</label><textarea class="form-control inputDadosCultivar" id="valorNutricional" placeholder="Digite o valor nutricional ..." data-error="Por favor, informe os valores nutricionais do cultivar." required ></textarea><div class="help-block with-errors"></div></div><div class="form-group"><label for="descNovoCultivar" class="control-label">Descrição:</label><textarea class="form-control inputDadosCultivar" id="descNovoCultivar" placeholder="Digite uma descrição..." data-error="Por favor, informe a descrição do cultivar." required ></textarea><div class="help-block with-errors"></div></div><div class="input-group"><input type="checkbox" aria-label="..." id="bioCheck" checked="">&nbsp;<label for="bioCheck" class="control-label">Biofortificado</label></div>  <div class="form-group"> <button type="submit" style="float: right;" class="btn btn-warning next-step" >Continuar</button> </div>  </form> </div>\n\
                                                   <div class="tab-pane"  id="progresso2">                          <form  data-toggle="validator" role="form" class="formNovoCultivar" >    <div class="form-group"><label class="control-label">Selecione uma imagem:</label><input id="imgNovoCultivar" type="file" class="inputImageCultivar" accept="image/jpeg, image/png" data-error="Por favor, selecione uma imagem para o cultivar." required ><div class="help-block with-errors"></div><img id="imgCultivarCarregada" class="hidden img-responsive" src="" width="180" height="180" alt="imgCultivar"/></div>     <div class="form-group"> <button type="submit" style="float: right;" class="btn btn-warning next-step" >Continuar</button> </div>  </form>     </div>\n\
                                                   <div class="tab-pane"  id="completo"><h3>Deseja realmente salvar o cultivar?</h3><div class="form-group"> <button type="submit" style="float: right;" id="salvarNovoCultivar" class="btn btn-warning">Salvar</button> </div></div><div class="clearfix"></div>');
  
                //tamanho do icone do progresso de acordo com a quantidade
                $("#qtdProgresso li").css("width", 100/3  + "%");
                inputFocus = "#nomeCultivar";
                $('.formNovoCultivar').validator();
                
            break;
            case 3:
                
                //Adiciona um novo form de uma nova unidade
                $("#tituloProgresso").text("Nova unidade");
                $("#progressoRef").text("Dados da unidade");
                
                //adiciona os icones do progresso, bolinhas redo
                $("#qtdProgresso").empty().append('<li role="presentation" class="active"><a href="#progresso1" data-toggle="tab" aria-controls="progresso1" role="tab" title="Dados unidade"><span class="round-tab"><i class="fa fa-university"></i></span></a></li>\n\
                                                   <li role="presentation" class="disabled"><a href="#progresso2" data-toggle="tab" aria-controls="progresso2" role="tab" title="Endereco unidade"><span class="round-tab"><i class="fa fa-map-marker"></i></span></a></li>\n\
                                                   <li role="presentation" class="disabled"><a href="#completo" data-toggle="tab" aria-controls="completo" role="tab" title="Salvar"><span class="round-tab"><i class="fa fa-save"></i></span></a></li>');

                $("#formProgresso").empty().append('<div class="tab-pane active" role="tabpanel" id="progresso1">       <form data-toggle="validator" role="form" class="formNovaUnidade" >     <div class="form-group"><label for="nomeNovaUnidade" class="control-label">Nome unidade:</label><input type="text" class="form-control " id="nomeNovaUnidade" placeholder="Digite da unidade..." pattern="[a-zA-Z çÇãÃâÂáÁàÀéÉêÊíÍìÌõÕôÔóÓúÚ]+" data-error="Por favor, informe o nome da unidade." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="cnpjNovaUnidade" class="control-label">CNPJ:</label><input type="text" class="form-control camposCNPJ" id="cnpjNovaUnidade" placeholder="Digite o cnpj..." data-error="Por favor, informe o CNPJ da unidade." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="nomeFantaNovaUnidade" class="control-label">Nome fantasia:</label><input type="text" class="form-control " id="nomeFantaNovaUnidade" placeholder="Digite o nome fantasia..."></div><div class="form-group"><label for="razaoSocialNovaUnidade" class="control-label">Razão social:</label><input type="text" class="form-control " id="razaoSocialNovaUnidade" placeholder="Digite a razão social..."></div><div class="form-group"><label for="telefone1NovaUnidade" class="control-label">Telefone 1:</label><input type="tel" class="form-control camposTelefone" placeholder="(xx) xxxxx-xxxx" pattern=".{14,}" id="telefone1NovaUnidade" placeholder="Digite o telefone 1..." data-error="Por favor, informe o telefone da unidade." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="telefone2NovaUnidade" class="control-label">Telefone 2:</label><input type="tel" class="form-control camposTelefone" placeholder="(xx) xxxxx-xxxx" pattern=".{14,}" id="telefone2NovaUnidade"></div><div class="form-group"><label for="emailNovaUnidade" class="control-label">Email:</label><input type="email" class="form-control " id="emailNovaUnidade" placeholder="Digite o email..." data-error="Por favor, informe o email da unidade." required ><div class="help-block with-errors"></div></div><div class="form-group"> <button type="submit" style="float: right;" class="btn btn-warning next-step">Continuar</button> </div>  </form>       </div>\n\
                                                    <div class="tab-pane"  id="progresso2">                             <form data-toggle="validator" role="form" class="formNovaUnidade" >     <div class="form-group"><label for="ruaNovaUnidade" class="control-label">Rua:</label><input type="text" class="form-control " id="ruaNovaUnidade" placeholder="Digite o nome da rua..." pattern="[a-zA-Z çÇãÃâÂáÁàÀéÉêÊíÍìÌõÕôÔóÓúÚ]+" data-error="Por favor, informe a rua da unidade." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="numeroNovaUnidade" class="control-label">Número:</label><input type="number" min="0" class="form-control " id="numeroNovaUnidade" placeholder="Digite o número..." data-error="Por favor, informe o numero da unidade." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="bairroNovaUnidade" class="control-label">Bairro:</label><input type="text" class="form-control " id="bairroNovaUnidade" placeholder="Digite o nome do bairro..." pattern="[a-zA-Z çÇãÃâÂáÁàÀéÉêÊíÍìÌõÕôÔóÓúÚ]+" data-error="Por favor, informe o bairro da unidade." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="complementoNovaUnidade" class="control-label">Complemento:</label><input type="text" class="form-control" id="complementoNovaUnidade" placeholder="Digite o complemento..." ></div><div class="form-group"><label for="cepNovaUnidade" class="control-label">Cep:</label><input type="text" class="form-control  camposCEP" id="cepNovaUnidade" placeholder="Digite o cep da propriedade..." ></div><div class="form-group"><label for="gps_latNovaUnidade" class="control-label">Latitude:</label><input type="number" class="form-control " id="gps_latNovaUnidade" placeholder="Digite a latitude..." value="0" ></div><div class="form-group"><label for="gps_longNovaUnidade" class="control-label">Longitude:</label><input type="number" class="form-control " id="gps_longNovaUnidade" value="0" placeholder="Digite a longitude..." ></div><div class="form-group"><label for="paisNovaUnidade" class="control-label">País:</label><select class="form-control  carregaPais" id="paisNovaUnidade" data-error="Por favor, selecione o país da unidade." required ><option></option></select><div class="help-block with-errors"></div></div><div class="form-group"><label for="estadoNovaUnidade" class="control-label">Estado:</label><select class="form-control carregaEstado" id="estadoNovaUnidade" data-error="Por favor, selecione o estado da unidade." required ><option></option></select><div class="help-block with-errors"></div></div><div class="form-group"><label for="cidadeNovaUnidade" class="control-label">Cidade:</label><select class="form-control carregaCidade" id="cidadeNovaUnidade" carregaPais" data-error="Por favor, selecione a cidade da unidade." required ><option></option></select><div class="help-block with-errors"></div></div><div class="form-group"> <button type="submit" style="float: right;" class="btn btn-warning next-step">Continuar</button> </div>  </form>       </div>\n\
                                                    <div class="tab-pane"  id="completo"><h3>Deseja realmente salvar a unidade?</h3><div class="form-group"> <button type="submit" style="float: right;" id="salvarNovaUnidade" class="btn btn-warning">Salvar</button> </div></div><div class="clearfix"></div>');
  
                //tamanho do icone do progresso de acordo com a quantidade
                $("#qtdProgresso li").css("width", 100/3  + "%");
                
                //muda os ids dos botoes de avancar o progresso
//                $(".salvarProgresso").attr("id", "salvarNovaUnidade");
//                $(".cancelarProgresso").attr("id", "cancelarNovaUnidade");
                inputFocus = "#nomeNovaUnidade";
                $('.formNovaUnidade').validator();
                
            break;
            case 4:
                
                //Adiciona um novo form de um novo usuario, entrevistador, gerenciador, adm
                $("#tituloProgresso").text("Novo usuário");
                $("#progressoRef").text("Dados pessoais");
                
                $("#qtdProgresso").empty().append('<li role="presentation" class="active"><a href="#progresso1" data-toggle="tab" aria-controls="progresso1" role="tab" title="Dados pessoais"><span class="round-tab"><i class="fa fa-address-card-o"></i></span></a></li>\n\
                                                   <li role="presentation" class="disabled"><a href="#progresso2" data-toggle="tab" aria-controls="progresso2" role="tab" title="Endereco usuario"><span class="round-tab"><i class="fa fa-map-marker"></i></span></a></li>\n\
                                                   <li role="presentation" class="disabled"><a href="#progresso3" data-toggle="tab" aria-controls="progresso3" role="tab" title="Dados da conta"><span class="round-tab"><i class="fa fa-sign-in"></i></span></a></li>\n\
                                                   <li role="presentation" class="disabled"><a href="#progresso4" data-toggle="tab" aria-controls="progresso4" role="tab" title="Papel"><span class="round-tab"><i class="fa fa-shield"></i></span></a></li>\n\
                                                   <li role="presentation" class="disabled"><a href="#completo" data-toggle="tab" aria-controls="completo" role="tab" title="Salvar"><span class="round-tab"><i class="fa fa-save"></i></span></a></li>');

                $("#formProgresso").empty().append('<div class="tab-pane active" role="tabpanel" id="progresso1">   <form class="formNovoUsuario" data-toggle="validator" role="form">    <div class="form-group"><label for="nomeNovoUsuario" class="control-label">Nome:</label><input type="text" class="form-control " id="nomeNovoUsuario" placeholder="Digite o nome do usuário..." pattern="[a-zA-ZçÇãÃâÂáÁàÀéÉêÊíÍìÌõÕôÔóÓúÚ]+" data-error="Por favor, informe o nome do usuário." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="sobrenomeNovoUsuario" class="control-label">Sobrenome:</label><input type="text" class="form-control " id="sobrenomeNovoUsuario" placeholder="Digite o sobrenome do usuário..." pattern="[a-zA-Z çÇãÃâÂáÁàÀéÉêÊíÍìÌõÕôÔóÓúÚ]+" data-error="Por favor, informe o sobrenome do usuário." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="rgNovoUsuario" class="control-label">RG:</label><input type="text" class="form-control camposRG" pattern=".{9,}"  placeholder="99999.999-9" id="rgNovoUsuario" data-error="Por favor, informe o RG do usuário." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="cpfNovoUsuario" class="control-label">CPF:</label><input type="text" class="form-control  camposCPF" pattern=".{14,}" placeholder="999.999.999-99" id="cpfNovoUsuario" data-error="Por favor, informe o CPF do usuário." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="apelidoNovoUsuario" class="control-label">Apelido:</label><input type="text" class="form-control" id="apelidoNovoUsuario" placeholder="Digite o apelido do usuário..." ></div><div class="form-group"><label for="dataNascNovoUsuario" class="control-label">Data de nascimento:</label><input type="text" class="form-control camposData" placeholder="dd/mm/aaaa" pattern=".{10,}" id="dataNascNovoUsuario" data-error="Por favor, informe a data de nascimento do usuário." required ><div class="help-block with-errors"></div></div><div class="form-group" id="generoNovoUsuario"><input type="radio" name="genero" value="m" checked> Masculino<br><input type="radio" name="genero" value="f"> Feminino<br> <br></div><div class="form-group"><label for="telefone1NovoUsuario" class="control-label">Telefone 1:</label><input type="tel" class="form-control  camposTelefone" placeholder="(xx) xxxxx-xxxx" pattern=".{14,}" id="telefone1NovoUsuario" data-error="Por favor, informe o telefone1 do usuário." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="telefone2NovoUsuario" class="control-label">Telefone 2:</label><input type="tel" class="form-control  camposTelefone" placeholder="(xx) xxxxx-xxxx" pattern=".{14,}" id="telefone2NovoUsuario" placeholder="Digite o telefone 2 do usuário..." ></div><div class="form-group"><label for="escolaridadeNovoUsuario" class="control-label">Escolaridade:</label><select class="form-control" id="escolaridadeNovoUsuario"><option>Ensino fundamental</option><option>Ensino fundamental incompleto</option><option>Ensino médio</option><option>Ensino médio incompleto</option><option>Ensino superior</option><option>Ensino superior incompleto</option><option>Pós-graduação</option><option>Pós-graduação incompleta</option><option>Sem escolaridade</option></select></div><div class="form-group"><label for="estadocivilNovoUsuario" class="control-label">Estado civil:</label><select class="form-control " id="estadocivilNovoUsuario"><option>Solteiro(a)</option><option>Casado(a)</option><option>Divorciado(a)</option><option>Viúvo(a)</option><option>Separado(a)</option><option>Companheiro(a)</option></select></div>   <div class="form-group"> <button type="submit" style="float: right;" class="btn btn-warning next-step">Continuar</button> </div>  </form>       </div>\n\
                                                   <div class="tab-pane"  id="progresso2">                          <form class="formNovoUsuario" data-toggle="validator" role="form">    <div class="form-group"><label for="ruaNovoUsuario" class="control-label">Rua:</label><input type="text" class="form-control " id="ruaNovoUsuario" placeholder="Digite o nome da rua..." pattern="[a-zA-Z çÇãÃâÂáÁàÀéÉêÊíÍìÌõÕôÔóÓúÚ]+" data-error="Por favor, informe a rua do usuário." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="numeroNovoUsuario" class="control-label">Número:</label><input type="number" min="0" class="form-control " id="numeroNovoUsuario" placeholder="Digite o número..." data-error="Por favor, informe o número do usuário." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="bairroNovoUsuario" class="control-label">Bairro:</label><input type="text" class="form-control " id="bairroNovoUsuario" placeholder="Digite o nome do bairro..." pattern="[a-zA-ZçÇãÃâÂáÁàÀéÉêÊíÍìÌõÕôÔóÓúÚ]+" data-error="Por favor, informe o bairro do usuário." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="complementoNovoUsuario" class="control-label">Complemento:</label><input type="text" class="form-control" id="complementoNovoUsuario" placeholder="Digite o complemento..." ></div><div class="form-group"><label for="cepNovoUsuario" class="control-label">Cep:</label><input type="text" class="form-control  camposCEP" id="cepNovoUsuario" placeholder="Digite o cep..." ></div><div class="form-group"><label for="gps_latNovoUsuario" class="control-label">Latitude:</label><input type="number" class="form-control " id="gps_latNovoUsuario" placeholder="Digite a latitude..." min="0" value="0"></div><div class="form-group"><label for="gps_longNovoUsuario" class="control-label">Longitude:</label><input type="number" class="form-control " id="gps_longNovoUsuario" placeholder="Digite a longitude..." min="0" value="0"></div><div class="form-group"><label for="paisNovoUsuario" class="control-label">País:</label><select class="form-control  carregaPais" id="paisNovoUsuario" data-error="Por favor, informe o país do usuário." required ><option></option></select><div class="help-block with-errors"></div></div><div class="form-group"><label for="estadoNovoUsuario" class="control-label">Estado:</label><select class="form-control carregaEstado" id="estadoNovoUsuario" data-error="Por favor, informe o estado do usuário." required ><option></option></select><div class="help-block with-errors"></div></div><div class="form-group"><label for="cidadeNovoUsuario" class="control-label">Cidade:</label><select class="form-control carregaCidade" id="cidadeNovoUsuario" data-error="Por favor, informe a cidade do usuário." required ><option></option></select><div class="help-block with-errors"></div></div><div class="form-group"> <button type="submit" style="float: right;" class="btn btn-warning next-step">Continuar</button> </div>  </form>       </div>\n\
                                                   <div class="tab-pane"  id="progresso3">                          <form class="formNovoUsuario" data-toggle="validator" role="form">    <div class="form-group"><label for="emailNovoUsuario" class="control-label">Email:</label><input type="email" class="form-control " id="emailNovoUsuario" placeholder="Digite o email do usuário..." data-error="Por favor, informe o email do usuário." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="usuarioNovoUsuario" class="control-label">Usuário:</label><input type="text" class="form-control " id="usuarioNovoUsuario" data-minlength="6" placeholder="Digite o usuário no mínimo 6 dígitos..." pattern="[a-zA-Z0-9]+" data-error="Por favor, informe o usuário no mínimo 6 dígitos." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="senhaNovoUsuario" class="control-label">Senha:</label><input type="password" class="form-control " id="senhaNovoUsuario" placeholder="Digite a senha..." data-minlength="6" data-error="Por favor, informe uma senha no minímo 6 dígitos." required ><div class="help-block with-errors"></div></div><div class="form-group"><label for="RsenhaNovoUsuario" class="control-label">Repita a senha:</label><input type="password" class="form-control " id="RsenhaNovoUsuario" placeholder="Digite novamente a senha..." data-match="#senhaNovoUsuario" data-error="Por favor, confirme sua senha." data-match-error="Ops, as senhas não correspondem." required ><div class="help-block with-errors"></div></div><div class="form-group"> <button type="submit" style="float: right;" class="btn btn-warning next-step">Continuar</button> </div>  </form>       </div>\n\
                                                   <div class="tab-pane"  id="progresso4">                          <form class="formNovoUsuario" data-toggle="validator" role="form">    <div class="form-group"><label for="unidadeAtuacaoNovoUsuario" class="control-label">Unidade de atuação:</label><select class="form-control carregaUnidades" id="unidadeAtuacaoNovoUsuario" data-error="Por favor, informe a unidade do usuário." required ><option></option></select><div class="help-block with-errors"></div></div>      <div class="form-group"><label for="papelNovoUsuario" class="control-label">Papel:</label><select class="form-control" id="papelNovoUsuario" data-error="Por favor, informe o papel do usuário." required ><option></option><option value="e">Entrevistador</option><option value="g">Gerenciador</option><option value="x">Administrador</option></select><div class="help-block with-errors"></div></div>      <div class="form-group"> <button type="submit" style="float: right;" class="btn btn-warning next-step">Continuar</button> </div>  </form>       </div>\n\
                                                   <div class="tab-pane"  id="completo"><h3>Deseja realmente salvar o usuário?</h3><div class="form-group"> <button type="submit" style="float: right;" id="salvarNovoUsuario" class="btn btn-warning">Salvar</button> </div></div><div class="clearfix"></div>');
                                                                           
                                
                $("#dataNascNovoUsuario").datepicker();
                //tamanho do icone do progresso de acordo com a quantidade
                $("#qtdProgresso li").css("width", 100/5  + "%");
                
                inputFocus = "#nomeNovoUsuario";
                $('.formNovoUsuario').validator();
               
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
                        case 2:
                            carregaUnidade($(this).find('td:eq(0)').html(), "editar");
                        break;
                        case 3:
                            carregaUsuario($(this).find('td:eq(0)').html(), "editar");
                        break;
                        default:
                        alerta("Alerta!", "Erro em carregar!");
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
    if($("#tituloIdPage > spam").text() === "Agricultores"){
        return 1;
    }else if($("#tituloIdPage > spam").text() === "Cultivares"){
        return 2;
    }else if($("#tituloIdPage > spam").text() === "Unidades"){
        return 3;
    }else if($("#tituloIdPage > spam").text() === "Usuários"){
        return 4;
    }else if($("#tituloIdPage > spam").text() === "Estoque da unidade"){
        return 5;
    }else{
        alert("Erro em identificar o item selecionado!");
    }
}


////////listar na tabela////////
//agricultores
function listarAgricultores(){
    var Sessao = getSessao();
    var envio = {
        metodo: "agricultores",
        id: Sessao.idlogin,
        sessao: Sessao.sessao,
        idunidade: Sessao.idunidade
    };

    //chama a requisicao do servidor, o resultado é listado em uma tabela
    requisicao(true, "pessoa/listar", envio, function(dadosRetorno) {
        
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

//listar os cultivares em uma tabela
function listarCultivares(){
    
    var Sessao = getSessao();
    var envio = {
        metodo: "bio",
        id: Sessao.idlogin,
        sessao: Sessao.sessao,
        idunidade: Sessao.idunidade
    };
    
    //chama a requisicao do servidor, o resultado é listado em uma tabela
    requisicao(true, "cultivar/listar", envio, function(dadosRetorno) {
        
        if(dadosRetorno.sucesso){
            var item = "";

            $.each(dadosRetorno.data, function(i, valor){
               
                item += "<tr><td>"+valor.idcultivar+"</td><td>"+valor.nomecultivar+"</td><td>"+valor.grandeza+"</td></tr>";

            });

            $("#page").fadeOut(400, function(){
                $("#divItens").empty().append('<h2 class="sub-header">Lista de cultivares</h2><div class="table-responsive"><table class="table table-hover"><thead><tr><th>ID</th><th>Nome do cultivar</th><th>Unidade de medida</th></tr></thead><tbody>'+item+'</tbody></table></div>');
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

//listar unidades em uma tabela
function listarUnidades(){
    var Sessao = getSessao();
    var envio = {
        metodo: "unidades",
        id: Sessao.idlogin,
        sessao: Sessao.sessao
    };
    
    //chama a requisicao do servidor, o resultado é listado em uma tabela
    requisicao(true, "unidade/listar", envio, function(dadosRetorno) {
        
        if(dadosRetorno.sucesso){
            
            var item = "";

            $.each(dadosRetorno.data, function(i, valor){
                item += "<tr><td>"+valor.idunidade+"</td><td>"+valor.nomeunidade+"</td><td>"+valor.telefone1+"</td><td>"+valor.cnpj+"</td><td>"+valor.email+"</td><td>"+valor.nomecidade+"</td><td>"+valor.nomeestado+"</td><td>"+valor.nomepais+"</td></tr>";

            });

            $("#page").fadeOut(400, function(){
                $("#divItens").empty().append('<h2 class="sub-header">Lista de unidades</h2><div class="table-responsive"><table class="table table-hover"><thead><tr><th>ID</th><th>Nome da Unidade</th><th>Telefone1</th><th>CNPJ</th><th>E-mail</th><th>Cidade</th><th>Estado</th><th>País</th></tr></thead><tbody>'+item+'</tbody></table></div>');
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

//listar usuarios
function listarUsuarios(){

    var Sessao = getSessao();
    var envio = {
        metodo: "usuarios",
        id: Sessao.idlogin,
        sessao: Sessao.sessao,
        idunidade: Sessao.idunidade
    };
    
    //chama a requisicao do servidor, o resultado é listado em uma tabela
    requisicao(true, "pessoa/listar", envio, function(dadosRetorno) {
        
        if(dadosRetorno.sucesso){
            var item = "";

            $.each(dadosRetorno.data, function(i, valor){
                item += "<tr><td>"+valor.idpessoa+"</td><td>"+valor.nome+"</td><td>"+valor.sobrenome+"</td><td>"+valor.rg+"</td><td>"+valor.cpf+"</td><td>"+valor.telefone1+"</td><td>"+valor.nomeunidade+"</td><td>"+valor.grupos+"</td></tr>";

            });

            $("#page").fadeOut(400, function(){
                $("#divItens").empty().append('<h2 class="sub-header">Lista de usuários</h2><div class="table-responsive"><table class="table table-hover"><thead><tr><th>ID</th><th>Nome</th><th>Sobrenome</th><th>RG</th><th>CPF</th><th>Telefone</th><th>Unidade</th><th>Papel</th></tr></thead><tbody>'+item+'</tbody></table></div>');
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

function mostrarPapel(papel){
    if(papel === "x"){
        return "Administrador";
    }else if(papel === "g"){
        return "Gerenciador";
    }else if(papel === "e"){
        return "Entrevistador";
    }else{
        return "Não identificado";
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
            url: "http://"+ipServidor+"/Projeto_BioID-war/servico/"+url,
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






//botao salvar novo cultivar
$(document).on("click", "#salvarNovoCultivar", function(){

    salvarNovoCultivar();
    
    return false;
});

//funcao que salva o cultivar novo pegando os valores dos inputs
function salvarNovoCultivar(){
    
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
//            metodo: "inserirCultivar",
            id: Sessao.idlogin,
            sessao: Sessao.sessao
        };
    
    //chama a requisicao do servidor, o resultado é listado em uma tabela
    requisicao(true, "cultivar/inserir", envio, function(dadosRetorno) {
        if(dadosRetorno.sucesso){
            
            $(".painelCarregando").fadeOut(400);
            alerta("Alerta!", dadosRetorno.mensagem);
            preEventosPaginaCadastros($(this), "fa-leaf", "Cultivares", "Listar todos os cultivares", "Adicionar um novo cultivar", "Editar informações de um cultivar", "Excluir um cultivar");
        }else{
            $(".painelCarregando").fadeOut(400);
            alerta("Alerta!", dadosRetorno.mensagem);
        }
        //atualiza a sessao
        updateSessao(dadosRetorno.sessao);
    });
}

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





$(document).on("change", "#imgNovoCultivar", function(){
    
      
      readImage($("#imgNovoCultivar")).done(function(base64Data){
          
          $("#imgCultivarCarregada").attr("src", base64Data);   
          $("#imgCultivarCarregada").removeClass("hidden");   
      });
      
//    alert("a");
    return false;
});


//cadastro de um novo agricultor parte de dados pessoais
$(document).on("submit", ".formNovoAgricultor", function(e){

        
    if(!e.isDefaultPrevented()){
        continuarProgresso();
    }
    return false;
});

//form do progresso distribuir cultivar
$(document).on("submit", ".formDistribuir", function(e){

        
    if(!e.isDefaultPrevented()){
        continuarProgresso();
    }
    return false;
});


////cancela um novo agricultor
//$(document).on("click", "#cancelarNovoAgricultor", function(){
//    
//    preEventosPaginaCadastros($(this), "fa-users", "Agricultores", "Listar todos os agricultores", "Adicionar um novo agricultor", "Editar informações de um agricultor", "Excluir um agricultor");
//
//    return false;
//});


$(document).on("click", "#salvarNovoAgricultor", function(){
    salvarNovoAgricultor();
    
    return false;
});


function salvarNovoAgricultor(){
    
    var Sessao = getSessao();
    

    var envio = {
//        metodo: "inseriragricultor",
        id: Sessao.idlogin,
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
        usuario: $("#usuarioAgricultor").val(),
        senha: $.md5($("#senhaAgricultor").val()),
        unidade_idunidade: $("#unidade").prop("value"),
        nomepropriedade: $("#nomePropriedade").val(),
        area: $("#area").val(),
        unidadedemedida: $("#unidademedida").prop("value"),
        areautilizavel: $("#areautilizavel").val(),
        unidadedemedidaau: $("#unidadedemedidaau").prop("value")
    };
    
  
    //chama a requisicao do servidor, o resultado é listado em uma tabela
    requisicao(true, "pessoa/inserir", envio, function(dadosRetorno) {
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


$(document).on("submit", ".formNovoCultivar", function(e){

        
    if(!e.isDefaultPrevented()){
        continuarProgresso();
    }
    return false;
});


////funcao que valida o campo que não podem ser nulos
//function verificarInput(classeInputs){
//    
//    var continuar = true;
//    $(classeInputs).each(function () {
//        
//            if($(this).val() === ""){
//                $("#itenFocus").text("#"+ $(this).prop("id"));
//                
//                alerta("Alerta!", "O campo "+ $(this).parent("div").children("label").text().replace(":", "")+" não pode ser vazio!");
//                
//                continuar = false;  
//                return false;
//            }
//
//    });
//
//    if(continuar){
//
//        continuarProgresso();        
//
//    }
//    return continuar;
//}


$(document).on("submit", ".formNovaUnidade", function(e){

        
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




//quando escolhido uma opçao é escondido o collapse e o icone muda, acontece quando o dispositivo é de proporção pequena
$(document).on("click", ".backgroundVerde", function(){


    $("#painelTelaPequena").collapse('hide');
    $("#botaoCollapse").children('i').addClass('fa-chevron-up');
    $("#botaoCollapse").children('i').removeClass('fa-chevron-down');

    chamarMenu($(this));
    return false;
});


//muda o icone de collapse quando o dispositivo esta em proporção pequena
$(document).on("click", "#botaoCollapse", function(){


    if($(this).children('i').hasClass('fa-chevron-up')){
        $(this).children('i').removeClass('fa-chevron-up');
        $(this).children('i').addClass('fa-chevron-down');
    }else{
        $(this).children('i').addClass('fa-chevron-up');
        $(this).children('i').removeClass('fa-chevron-down');
    }

 
    return false;
});




//cadastro de uma nova unidade, verifica se existe inputs vazios
$(document).on("click", "#salvarNovaUnidade", function(){

    salvarNovaUnidade();

    return false;
});




//manda a requisição para o servidor com os dados referentes a uma nova unidade
function salvarNovaUnidade(){
    var Sessao = getSessao();
    

    var envio = {
        id: Sessao.idlogin,
        sessao: Sessao.sessao,
        
        cidade_idcidade : $("#cidadeNovaUnidade").prop("value"),
        rua: $("#ruaNovaUnidade").val(),
        gps_lat: $("#gps_latNovaUnidade").val(),
        gps_long: $("#gps_longNovaUnidade").val(),
        bairro: $("#bairroNovaUnidade").val(),
        complemento: $("#complementoNovaUnidade").val(),
        cep: $("#cepNovaUnidade").val(),
        numero: $("#numeroNovaUnidade").val(),
        nomeunidade: $("#nomeNovaUnidade").val(),
        cnpj: $("#cnpjNovaUnidade").val(),
        telefone1: $("#telefone1NovaUnidade").val(),
        telefone2: $("#telefone2NovaUnidade").val(),
        email: $("#emailNovaUnidade").val(),
        razao_social: $("#razaoSocialNovaUnidade").val(),
        nome_fanta: $("#nomeFantaNovaUnidade").val()
    };
    
    
    //chama a requisicao do servidor, o resultado é listado em uma tabela
    requisicao(true, "unidade/inserir", envio, function(dadosRetorno) {
        if(dadosRetorno.sucesso){
            
            $(".painelCarregando").fadeOut(400);
            alerta("Alerta!", dadosRetorno.mensagem);
            preEventosPaginaCadastros($(this), "fa-university", "Unidades", "Listar todas as unidades", "Adicionar uma nova unidade", "Editar informações de uma unidade", "Excluir uma unidade");
        }else{
            $(".painelCarregando").fadeOut(400);
            alerta("Alerta!", dadosRetorno.mensagem);
        }
        //atualiza a sessao
        updateSessao(dadosRetorno.sessao);
    });
}

//cadastro de uma nova unidade, chama a funcao que fara o json
$(document).on("click", "#salvarNovoUsuarios", function(){

    salvarNovoUsuarios();

    return false;
});

////validacao dos dados de um novo usuario
//function validacaoNovoUsuario(){
//    if(verificaSenhas($("senhaNovoUsuario"), $("RsenhaNovoUsuario"))){
//        return true;
//    }else{
//        return false;
//    }
//}
//manda a requisição para o servidor com os dados referentes a uma novo usuario
function salvarNovoUsuarios(){
//    alert("mandar requisição");
    
var Sessao = getSessao();
    

    var envio = {
//        metodo: "inserirusuario",
        id: Sessao.idlogin,
        sessao: Sessao.sessao,
        metodo: "usuario",
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
        usuario: $("#usuarioNovoUsuario").val(),
        senha: $.md5($("#senhaNovoUsuario").val()),
        papel: $("#papelNovoUsuario").prop("value"),
        unidade_idunidade: $("#unidadeAtuacaoNovoUsuario").prop("value")
    };
    
    
    //chama a requisicao do servidor, o resultado é listado em uma tabela
    requisicao(true, "pessoa/inserir", envio, function(dadosRetorno) {
        if(dadosRetorno.sucesso){
            
            $(".painelCarregando").fadeOut(400);
            alerta("Alerta!", dadosRetorno.mensagem);
            preEventosPaginaCadastros($(this), "fa-user-secret","Usuários", "Listar todos os usuários", "Adicionar um novo usuário", "Editar um usuário", "Excluir um usuário");
        }else{
            $(".painelCarregando").fadeOut(400);
            alerta("Alerta!", dadosRetorno.mensagem);
        }
        //atualiza a sessao
        updateSessao(dadosRetorno.sessao);
    });

}


//manda a requisição para o servidor com os dados referentes a uma nova unidade
function salvarNovaUnidade(){
    var Sessao = getSessao();
    

    var envio = {
        id: Sessao.idlogin,
        sessao: Sessao.sessao,
        
        cidade_idcidade : $("#cidadeNovaUnidade").prop("value"),
        rua: $("#ruaNovaUnidade").val(),
        gps_lat: $("#gps_latNovaUnidade").val(),
        gps_long: $("#gps_longNovaUnidade").val(),
        bairro: $("#bairroNovaUnidade").val(),
        complemento: $("#complementoNovaUnidade").val(),
        cep: $("#cepNovaUnidade").val(),
        numero: $("#numeroNovaUnidade").val(),
        nomeunidade: $("#nomeNovaUnidade").val(),
        cnpj: $("#cnpjNovaUnidade").val(),
        telefone1: $("#telefone1NovaUnidade").val(),
        telefone2: $("#telefone2NovaUnidade").val(),
        email: $("#emailNovaUnidade").val(),
        razao_social: $("#razaoSocialNovaUnidade").val(),
        nome_fanta: $("#nomeFantaNovaUnidade").val()
    };
    
    
    //chama a requisicao do servidor, o resultado é listado em uma tabela
    requisicao(true, "unidade/inserir", envio, function(dadosRetorno) {
        if(dadosRetorno.sucesso){
            
            $(".painelCarregando").fadeOut(400);
            alerta("Alerta!", dadosRetorno.mensagem);
            preEventosPaginaCadastros($(this), "fa-university", "Unidades", "Listar todas as unidades", "Adicionar uma nova unidade", "Editar informações de uma unidade", "Excluir uma unidade");
        }else{
            $(".painelCarregando").fadeOut(400);
            alerta("Alerta!", dadosRetorno.mensagem);
        }
        //atualiza a sessao
        updateSessao(dadosRetorno.sessao);
    });
}

function testeRadioPapel(){
    if($("#radioEntrevistador").is(':checked') || $("#radioGerenciador").is(':checked') || $("#radioAdministrador").is(':checked')){
        return true;
    }else{
        return false;
    }
    
}

//verifica se os inputs de senha no cadastro corespondem
//function verificaSenhas(input1, input2){
//    if(input1.val() === input2.val()){
//        return true;
//    }else{
//        $("#itenFocus").text("#"+input1.prop("id"));
//        alerta("Alerta!", "As senhas não correspondem!");
//        return false;
//    }
//}







//carrega a lista de pais do banco de dados e lista em um select
$(document).on("focusin", ".carregaPais", function(){
    var envio = {
        metodo: "pais"
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
        metodo: "estados",
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

//carrega do banco de dados os estados
$(document).on("focusin", ".carregaCidade", function(){
    var envio = {
        metodo: "cidades",
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
    var envio = {
        metodo: "nome_id_unidades"
    };
    
    var idSelect = $(this).prop("id");

    //chama a requisicao do servidor, o resultado é listado em um select
    requisicao(true, "outros/listar", envio, function(dadosRetorno) {
        if(dadosRetorno.sucesso){
            
            $("#"+idSelect).empty();
            $.each(dadosRetorno.data, function(i, value){
                $("#"+idSelect).append('<option value="'+value.idunidade+'">'+value.nomeunidade+'</option>');
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
 
    });

 
    return false;
});





















































////cancela um novo agricultor
//$(document).on("click", "#ajuda", function(){
//
////    var teste = $("#cidadeNovaUnidade").prop("value");
////    
////    alert(teste);
//    alerta("Alerta!", "Ajuda em construção!");
// 
//    return false;
//});

$(document).on("click", "#configuracoes", function(){

    $("#itensModal").empty().append('<div class="form-group"><label for="idioma" class="control-label">Idioma:</label><select class="form-control" id="idioma" ><option>Português</option></select></div><div class="form-group"><label for="papeldeparede" class="control-label">Papel de parede:</label><select class="form-control" id="papeldeparede" ><option value="../images/background1.jpg">Papel de parede 01</option><option value="../images/background2.jpg">Papel de parede 02</option><option value="../images/background3.jpg">Papel de parede 03</option><option value="../images/background4.jpg">Papel de parede 04</option><option value="../images/background5.jpg">Papel de parede 05</option><option value="../images/background6.jpg">Papel de parede 06</option></select></div><br><button type="button" class="btn btn-warning" id="mudapapelparede">Salvar</button>');
    $("#modalTitulo").text("Configurações");
    $('#modalApresentacao').modal('toggle');
 
    return false;
});

$(document).on("click", "#deslogar", function(){


    $("#itensModal").empty().append('Deseja realmente deslogar?<br><button type="button" class="btn btn-warning" id="confirmaDeslogar">Deslogar</button>');
    $("#modalTitulo").text("Confirmação!");
    $('#modalApresentacao').modal('toggle');
//    $('#modalApresentacao .modal-dialog .modal-footer').modal('toggle');
    
//    window.location.href = 'home.html';
 
    return false;
});

$(document).on("click", "#confirmaDeslogar", function(){

    localStorage.clear();
//    $("#page").fadeOut(400, function(){
        window.location.href = 'index.html';  
//    });
    
 
    return false;
});

$(document).on("click", "#mudapapelparede", function(){


//var background = 'url('+$("#papeldeparede :selected").prop("value")+ ')';
//alert(background);
//$("#sidebarMenu").removeClass("back1").addClass("back2");
 
    return false;
});


//carrega o progresso de distribuir cultivares
function progDistCultivares(){

    //Adicionar agricultor, limpa o progresso e adiciona os itens
    $("#tituloProgresso").text("Distribuir cultivares");
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

//carrega o estoque da unidade
function progEstoqueUnidade(){
   
    //pesquisa a unidade do agricultor
        var Sessao = getSessao();
        var envio = {
            metodo: "estoqueunidade",
            idunidade: Sessao.idunidade,
            id: Sessao.idlogin,
            sessao: Sessao.sessao
        };
        
    //chama a requisicao do servidor, o resultado é listado em uma tabela
        requisicao(true, "cultivar/listar", envio, function(dadosRetorno) {
            if(dadosRetorno.sucesso){

                var items = "";
                    $.each(dadosRetorno.data, function(i, valor){
//                        items +='<option value="'+valor.idcultivar+'">'+valor.nomecultivar+'</option>';
                        items += "<tr><td>"+valor.idcultivar+"</td><td>"+valor.nomecultivar+"</td><td>"+valor.quantidade+"</td><td>"+valor.grandeza+"</td></tr>";
                    });



                $("#divItens").empty().append('<h2 class="sub-header">Cultivares no estoque</h2>  <button type="button" title="Entrada de estoque" class="btn btn-warning" id="entradaEstoque" ><span class="fa fa-plus-square" aria-hidden="true"></span></button><button type="button" title="Saída de estoque" class="btn btn-warning" disabled id="saidaEstoque" ><span class="fa fa-minus-square" aria-hidden="true"></span></button>               <div class="table-responsive"><table id="tabEstoqueUnidade" class="table table-hover"><thead><tr><th>ID</th><th>Nome cultivar</th><th>Quantidade</th><th>Unidade de medida</th></tr></thead><tbody>'+items+'</tbody></table></div>');
                $("#divItens").show();



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
        metodo: "agricultor_select",
        idunidade: Sessao.idunidade,
        id: Sessao.idlogin,
        sessao: Sessao.sessao
    };
//	$.ajax({
//		url: "http://"+ipServidor+"/Projeto_BioID-war/servico/pessoa/listar",
//		type: 'POST',
//		data: JSON.stringify(envio),
//                headers: { 
//                'Accept': 'application/json',
//                'Content-Type': 'application/json'
//                },	
//		success: function(dadosRetorno) {
//
//                    response( $.map(dadosRetorno.data , function( valor ) {
//                    return {
//                        value: valor.nome +" "+valor.sobrenome,
//                        label: valor.nome +" "+valor.sobrenome +"...{ RG: "+ valor.rg +" / CPF: "+valor.cpf +" }...",
//                        idpessoa: valor.idpessoa
//                    };
//                    }));
//		},
//                error: function() {
//                    alerta("Alerta!", "erro na requisição procura do agricultor!");
//                }
//	});



    //chama a requisicao do servidor, o resultado é listado em um select
    requisicao(false, "pessoa/listar", envio, function(dadosRetorno) {
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
            metodo: "listarpropriedades",
            idpessoa: $('#campoBuscaAgri').attr('value'),
            idunidade: Sessao.idunidade,
            id: Sessao.idlogin,
            sessao: Sessao.sessao
        };

        //chama a requisicao do servidor, o resultado é listado em uma tabela
        requisicao(true, "pessoa/listarpropriedades", envio, function(dadosRetorno) {
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


$(document).on("blur", "#listaCultivares", function(){
    var valores = JSON.parse($('#listaCultivares :selected').prop('value'));
    
    
    //muda para inteiro ou decimal os valores aceito pelo input

    $("#qtdCultivarDist").attr("min", get_nin_step(valores.grandeza).min);
    $("#qtdCultivarDist").attr("step", get_nin_step(valores.grandeza).step);

    
    $("#qtdCultivarDist").attr("max", valores.quantidade);
    //muda o label de acordo com a quantidade e grandeza do cultivar selecionado
    $("#labelQtdCultivarDist").text("Quantidade em "+valores.grandeza +"...{Qtd no estoque "+ valores.quantidade+"}...");
});


$(document).on("click", "#listaCultivares", function(){
    
    if($(this).children('option').length < 1){

        //pesquisa a unidade do agricultor
        var Sessao = getSessao();
        var envio = {
            metodo: "estoqueunidade_select",
            idunidade: Sessao.idunidade,
            id: Sessao.idlogin,
            sessao: Sessao.sessao
        };

        //chama a requisicao do servidor, o resultado é listado em uma tabela
        requisicao(true, "cultivar/listar", envio, function(dadosRetorno) {
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
            datareceb: $('#datarecebDist').val(),
            
            id: Sessao.idlogin,
            sessao: Sessao.sessao
        };
    
    //chama a requisicao do servidor, o resultado é listado em uma tabela
    requisicao(true, "cultivar/distribuir", envio, function(dadosRetorno) {
        if(dadosRetorno.sucesso){
            
            $(".painelCarregando").fadeOut(400);
            alerta("Alerta!", dadosRetorno.mensagem);
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


//chama a requisicao para salvar a edicao de agricultores
$(document).on("submit", "#salvarEditAgri", function(e){
    if(!e.isDefaultPrevented()){
        alerta("Alerta!", 'Em construção');
    }
    

    return false;
});

//chama a requisicao para salvar a edicao de cultivares
$(document).on("submit", "#salvarEditCulti", function(e){
    if(!e.isDefaultPrevented()){
        alerta("Alerta!", 'Em construção');
    }

    return false;
});

//chama a requisicao para salvar a edicao de cultivares
$(document).on("submit", "#salvarEditUnidade", function(e){
    if(!e.isDefaultPrevented()){
        alerta("Alerta!", 'Em construção');
    }

    return false;
});

//chama a requisicao para salvar a edicao de agricultores
$(document).on("submit", "#salvarEditUser", function(e){
    if(!e.isDefaultPrevented()){
        alerta("Alerta!", 'Em construção');
    }

    return false;
});



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
        operacao: 1,
        idunidade: Sessao.idunidade,
        id: Sessao.idlogin,
        sessao: Sessao.sessao
    };

    
    
    //chama a requisicao do servidor, o resultado é listado em uma tabela
    requisicao(true, "unidade/ioestoque", envio, function(dadosRetorno) {

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
            metodo: "bio",
            id: Sessao.idlogin,
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
        metodo: "get_cultivar_estoque",
        idcultivar: $('#tabEstoqueUnidade').find('.warning').find('td:eq(0)').html(),
        idunidade: Sessao.idunidade,
        id: Sessao.idlogin,
        sessao: Sessao.sessao
    };
    //chama a requisicao do servidor, o resultado a quantidade de cultivar no estoque
        requisicao(true, "cultivar/buscar", envio, function(dadosRetorno) {

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