        /* button  .uib_w_3 */
    $(document).on("click", "#entrar", function(evt)
    {
         /*global activate_page */
       
        window.location.href = 'home.html';


         return false;
    });
    
    //item lista dos cultivares
    $(document).on("click", "tbody > tr", function(evt)
    {
        
        if(!$(this).hasClass("warning")){
            $(this).addClass("warning");
            
        }else{
            $(this).removeClass("warning");
        }

         return false;
    });

    //botao novo cultivar
    $(document).on("click", "#novoCultivar", function(evt)
    {
        
       $("#modalAddCultivar").modal();
         return false;
    });
    
    

    //botao pesquisar cultivar
    $(document).on("click", "#pesquisarCultivar", function(evt)
    {
        
       $("#modalPesquisarCultivar").modal();
         return false;
    });
   
    //botao editar cultivar
    $(document).on("click", "#editarCultivar", function(evt)
    {
        
       $("#modalEditarCultivar").modal();
         return false;
    });