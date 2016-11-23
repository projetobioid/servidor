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
    
    //item lista dos cultivares
    $(document).on("click", "tbody > tr", function(evt)
    {
        //percorer a tabela e desmarcar os itens marcados
        var tabela = $("#tabelaCultivares div table");
        tabela.find('tr').each(function (i){
            if($(this).hasClass("warning")){
                $(this).removeClass("warning");
                return false;
            }
        });
        
        //marcar de laranja a row
        if(!$(this).hasClass("warning")){
            $(this).addClass("warning");
            $("#myAlert").addClass("hidden");
            
            
        }

         return false;
    });

    //botao novo cultivar
    $(document).on("click", "#novoCultivar", function(evt)
    {
        $("#myAlert").addClass("hidden");
        
        $("#nomeNovoCultivar").val("");
        $("#umNovoCultivar").val("Kilo(s)");
        $("#pesoSaca").val("");
        $("#tempoColheita").val("");
        $("#tempoDestinacao").val("");
        $("#valorNutricional").val("");
        $("#descNovoCultivar").val("");

        
        $("#tituloCultivar").text("Novo cultivar");
        $("#imgCultivarCarregada").addClass("hidden");
        fadeOpcaoSelecionadaCultivar("#formNovoCultivar");
        return false;
    });
    

   
    //botao editar cultivar
    $(document).on("click", "#editarCultivar", function(evt)
    {
        
        if(!$("#tabelaCultivares").is(':visible')){
            $("#myAlert").removeClass("hidden");
            listarCultivares();
        }else{
            var tabela = $("#tabelaCultivares div table");
            var marcado = false;
            tabela.find('tr').each(function (){
                if($(this).hasClass("warning")){
                    carregarDadosCultivar($(this));
                    $("#tituloCultivar").text("Editar cultivar");
                    fadeOpcaoSelecionadaCultivar("#formNovoCultivar");
                    marcado = true;
                    return false;
                }
            });
            
            if(!marcado){
                $("#myAlert").removeClass("hidden");
            }
            

        }
            
         return false;
    });
    
    //funcao carregar dados cultivar quando vai editar
    function carregarDadosCultivar(item){

//        $("#umNovoCultivar").val(item.find('td:eq(2)').html());

        var data = "nomecultivar="+item.find('td:eq(1)').html()+"&biofortificado=true&sessao="+getSessao();
        $.post("http://"+ipServidor+"/Projeto_BioID-war/servico/cultivar/buscar", data, function(dados){

            if(dados.sucesso){
                
                
                $("#nomeNovoCultivar").val(dados.cultivar.nomecultivar);
                $("#umNovoCultivar").val(dados.cultivar.grandeza);
                $("#pesoSaca").val(dados.cultivar.peso_saca);
                $("#tempoColheita").val(dados.cultivar.tempodecolheita);
                $("#tempoDestinacao").val(dados.cultivar.tempodestinacao);
                $("#valorNutricional").val(dados.cultivar.valornutricional);
                $("#descNovoCultivar").val(dados.cultivar.descricao);
                
                
                if(dados.cultivar.biofortificado){
                    $("#bio").prop('checked', true);
                    
                }else{
                    $("#bio").prop('checked', false);
                }
                
                $("#imgCultivarCarregada").attr("src", dados.cultivar.imagem);
                $("#imgCultivarCarregada").removeClass("hidden");
                
                //atualiza a sessao
                updateSessao(dados.sessao);
        
             }else{
                 alert("Erro na busca do cultivar :" +dados.mensagem);
             }
        }, "json")
        //Tratamento de erro da requisicao servico RESt login
        .fail(function(){
            alert("Erro na requisição com o servidor!");
        });
    }
    
    $(document).on("click", ".close", function(evt)
    {
        $("#myAlert").addClass("hidden");
    });
    
    function listarCultivares(){
        var data = 'sessao='+getSessao();

        $.post("http://"+ipServidor+"/Projeto_BioID-war/servico/cultivar/listar", data, function(dados){

            if(dados.sucesso){

             
                //armazena as imagens dos cultivares em um array
                cultivares = dados.cultivares;

                $("#listaTabelaCultivares").empty();
                //adiciona as propriedades e as safras em arrays
                $.each(cultivares, function(i, valor){

                    item = "<tr><td>"+valor.idcultivar+"</td><td>"+valor.nomecultivar+"</td><td>"+valor.grandeza+"</td></tr>";
                    $("#listaTabelaCultivares").append(item);

                });
                //guarda a nova sessao
                updateSessao(dados.sessao);
            }else{
                alert("Erro na busca do cultivar :" +dados.mensagem);
             }
        }, "json")
        //Tratamento de erro da requisicao servico RESt login
        .fail(function(){
            alert("Erro na requisição com o servidor!");
        });
        
        
        
      
        fadeOpcaoSelecionadaCultivar("#tabelaCultivares");
    }
        
    function fadeOpcaoSelecionadaCultivar(itemMostrar){
        var t = ["#formNovoCultivar","#tabelaCultivares"];
        var visivel;
        
        $.each(t, function(i, item){
            
            if($(item).is(':visible')){
                visivel = item;
                return false;
            }else{
                visivel = "nulo";
            }
            i++;
        });

        
//        if(!$("#imgInicial").attr(":hidden")){
//            $("#pageCadastroCultivar").fadeOut(200);
//            $("#imgInicial").fadeOut(200, function (){
//                $("#pageCadastroCultivar").fadeIn(200);
//
//            });
//        }
        
        if(visivel === "nulo"){
            $(itemMostrar).fadeIn(200);
        }else{
            $(visivel).fadeOut(200, function(){
               $(itemMostrar).fadeIn(200);
            });   
        }

    }
    
    function listarUnidades(){
        var data = 'sessao='+getSessao();

        $.post("http://"+ipServidor+"/Projeto_BioID-war/servico/unidade/listar", data, function(dados){

            if(dados.sucesso){

             
                //armazena as imagens dos cultivares em um array
                unidades = dados.unidades;

                $("#listaTabelaUnidades").empty();
                //adiciona as propriedades e as safras em arrays
                $.each(unidades, function(i, valor){

                    item = "<tr><td>"+valor.idunidade+"</td><td>"+valor.nomeunidade+"</td><td>"+valor.telefone1+"</td><td>"+valor.email+"</td><td>"+valor.nomeestado+"</td><td>"+valor.nomecidade+"</td></tr>";
                    $("#listaTabelaUnidades").append(item);
                        

                });
                //guarda a nova sessao
                updateSessao(dados.sessao);
            }else{
                alert("Erro na buscar a unidade :" +dados.mensagem);
             }
        }, "json")
        //Tratamento de erro da requisicao servico RESt login
        .fail(function(){
            alert("Erro na requisição com o servidor!");
        });
        
        
        
      
        fadeOpcaoSelecionadaUnidade("#tabelaUnidades");
    }
    
    function fadeOpcaoSelecionadaUnidade(itemMostrar){
        var t = ["#formNovaUnidade","#tabelaUnidades"];
        var visivel;
        
        $.each(t, function(i, item){
            
            if($(item).is(':visible')){
                visivel = item;
                return false;
            }else{
                visivel = "nulo";
            }
            i++;
        });

        
        if(visivel === "nulo"){
            $(itemMostrar).fadeIn(200);
        }else{
            $(visivel).fadeOut(200, function(){
               $(itemMostrar).fadeIn(200);
            });   
        }

    }
    
    //botao novo agricultor
    $(document).on("click", "#novoAgricultor", function(evt)
    {
        $("#myAlertAgricultores").addClass("hidden");
        
        $("#nomeNovoAgricultor").val("");

        
        $("#tituloAgricultor").text("Novo agricultor");
        fadeOpcaoSelecionadaAgricultor("#formNovoAgricultor");
        return false;
    });
    
    //botao nova unidade, vai mostar o form de cadastro
    $(document).on("click", "#novaUnidade", function(evt)
    {
        $("#myAlertUnidade").addClass("hidden");
        
        $("#nomeNovaUnidade").val("");
//        $("#umNovoCultivar").val("Kilo(s)");
//        $("#pesoSaca").val("");
//        $("#tempoColheita").val("");
//        $("#tempoDestinacao").val("");
//        $("#valorNutricional").val("");
//        $("#descNovoCultivar").val("");

        
        $("#tituloUnidade").text("Nova unidade");
        fadeOpcaoSelecionadaUnidade("#formNovaUnidade");
        return false;
    });
    
    //botao editar unidade
    $(document).on("click", "#editarUnidade", function(evt)
    {
        
        if(!$("#tabelaUnidades").is(':visible')){
            $("#myAlertUnidade").removeClass("hidden");
            listarUnidades();
        }else{
            var tabela = $("#tabelaUnidades div table");
            var marcado = false;
            tabela.find('tr').each(function (){
                if($(this).hasClass("warning")){
                    carregarDadosUnidade($(this));
                    $("#tituloUnidade").text("Editar unidade");
                    fadeOpcaoSelecionadaUnidade("#formNovaUnidade");
                    marcado = true;
                    return false;
                }
            });
            
            if(!marcado){
                $("#myAlertUnidade").removeClass("hidden");
            }
            

        }
            
         return false;
    });
    
    //funcao carregar dados cultivar quando vai editar
    function carregarDadosUnidade(item){

//        $("#umNovoCultivar").val(item.find('td:eq(2)').html());

        var data = "nomeunidade="+item.find('td:eq(1)').html()+"&sessao="+getSessao();
        $.post("http://"+ipServidor+"/Projeto_BioID-war/servico/unidade/buscar", data, function(dados){

            if(dados.sucesso){
                
                
                $("#nomeNovaUnidade").val(dados.unidade.nomeunidade);
                $("#cnpj").val(dados.unidade.cnpj);
//                $("#pesoSaca").val(dados.cultivar.peso_saca);
//                $("#tempoColheita").val(dados.cultivar.tempodecolheita);
//                $("#tempoDestinacao").val(dados.cultivar.tempodestinacao);
//                $("#valorNutricional").val(dados.cultivar.valornutricional);
//                $("#descNovoCultivar").val(dados.cultivar.descricao);
                
//                
//                if(dados.cultivar.biofortificado){
//                    $("#bio").prop('checked', true);
//                    
//                }else{
//                    $("#bio").prop('checked', false);
//                }
//                
//                $("#imgCultivarCarregada").attr("src", dados.cultivar.imagem);
//                $("#imgCultivarCarregada").removeClass("hidden");
                
                //atualiza a sessao
                updateSessao(dados.sessao);
        
             }else{
                 alert("Erro na busca da unidade :" +dados.mensagem);
             }
        }, "json")
        //Tratamento de erro da requisicao servico RESt login
        .fail(function(){
            alert("Erro na requisição com o servidor!");
        });
    }
    
    ///////*menu Cultivar*////////////
    //cadastrar cultivar
    $(document).on("click", "#listarCultivares", function(evt)
    {

        $("#myAlert").addClass("hidden");
        listarCultivares();   
        

         return false;
    });
    
    
function listarAgricultores(){
        var data = 'sessao='+getSessao();

//        $.post("http://"+ipServidor+"/Projeto_BioID-war/servico/unidade/listar", data, function(dados){
//
//            if(dados.sucesso){
//
//             
//                //armazena as imagens dos cultivares em um array
//                unidades = dados.unidades;
//
//                $("#listaTabelaUnidades").empty();
//                //adiciona as propriedades e as safras em arrays
//                $.each(unidades, function(i, valor){
//
//                    item = "<tr><td>"+valor.idunidade+"</td><td>"+valor.nomeunidade+"</td><td>"+valor.telefone1+"</td><td>"+valor.email+"</td><td>"+valor.nomeestado+"</td><td>"+valor.nomecidade+"</td></tr>";
//                    $("#listaTabelaUnidades").append(item);
//                        
//
//                });
//                //guarda a nova sessao
//                updateSessao(dados.sessao);
//            }else{
//                alert("Erro na buscar a unidade :" +dados.mensagem);
//             }
//        }, "json")
//        //Tratamento de erro da requisicao servico RESt login
//        .fail(function(){
//            alert("Erro na requisição com o servidor!");
//        });
        
        
        
      
        fadeOpcaoSelecionadaAgricultor("#tabelaAgricultores");
    }
    
    function fadeOpcaoSelecionadaAgricultor(itemMostrar){
        var t = ["#formNovoAgricultor","#tabelaAgricultores"];
        var visivel;
        
        $.each(t, function(i, item){
            
            if($(item).is(':visible')){
                visivel = item;
                return false;
            }else{
                visivel = "nulo";
            }
            i++;
        });

        if(visivel === "nulo"){
            $(itemMostrar).fadeIn(200);
        }else{
            $(visivel).fadeOut(200, function(){
               $(itemMostrar).fadeIn(200);
            });   
        }

    }

///////*menu Agricultor*////////////
    //listar agriculrores
    $(document).on("click", "#listarAgricultores", function(evt)
    { 
        $("#myAlertAgricultores").addClass("hidden");
        listarAgricultores();
        

         return false;
    });
    
///////*menu Unidades*////////////
    //listar unidades
    $(document).on("click", "#listarUnidades", function(evt)
    { 
        $("#myAlertUnidade").addClass("hidden");
        listarUnidades();
        

         return false;
    });
    
    ///////*painel lateral*////////////

    function preEventosPaginas(pagina){
        $(".paginas").addClass("hidden");
        $(".a").css("background", "rgba(255,255,255,0.6)");
        $(pagina).css("background", "#FFCC00");
    }
    
    //cadastro agricultor
    $(document).on("click", "#painelAgricultores", function(evt)
    {
        preEventosPaginas($(this));
        $("#pageCadastroAgricultor").removeClass("hidden");
        
        return false;
    });
    
    //cadastro cultivar
    $(document).on("click", "#painelCultivares", function(evt)
    {
        preEventosPaginas($(this));
        $("#pageCadastroCultivar").removeClass("hidden");
        
        return false;
    });
    
    //cadastro unidade
    $(document).on("click", "#painelUnidades", function(evt)
    {
        preEventosPaginas($(this));
        $("#pageCadastroUnidade").removeClass("hidden");
        return false;
    });