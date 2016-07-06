/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import bo.BOFactory;
import dao.DAOCultivarRecebido;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * REST Web Service
 *
 * @author daniel
 */
@Path("cultivarrecebido")
public class ServicosCultivarrecebido {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServicosCultivarrecebido
     */
    public ServicosCultivarrecebido() {
    }

    //serviço de listar os usuarios
    @GET
    @Path("listar")
    public String listar() throws Exception{
    
        JSONObject j = new JSONObject();
        
        try{
            //cria um jsonarray e popula com uma consulta no banco
            JSONArray ja = BOFactory.listar(new DAOCultivarRecebido());
            j.put("data", ja);
            j.put("sucesso", true);
            
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        
        return j.toString();
    }
    
        //serviço de listar os usuarios
    @GET
    @Path("teste")
    public String teste() throws Exception{
    
        JSONObject j = new JSONObject();

        
        try{
            //cria um jsonarray e popula com uma consulta no banco
            JSONArray ja = BOFactory.listar(new DAOCultivarRecebido());
            
            
            
            //Criteria crit = session.createCriteria(Produto.class);  
            //crit.add(Restrictions.eq("nome","Teclado"));
            //List results = crit.list(); 
            
            
            
            j.put("data", ja);
            j.put("sucesso", true);
            
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        
        return j.toString();
    }
    
    
}
