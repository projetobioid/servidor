/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import bo.BOFactory;
import dao.DAOPessoa;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;
import to.TOPessoa;

/**
 * REST Web Service
 *
 * @author daniel
 */
@Path("pessoa")
public class ServicosPessoa {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServicosPessoa
     */
    public ServicosPessoa() {
    }

    //metodo que insere no banco de dados
    @POST
    @Path("inserir")
    public String inserir(
            @FormParam("nome") String nome) throws Exception{
        
                
        JSONObject j = new JSONObject();
        
        try{    
            
            TOPessoa t = new TOPessoa();
           
            t.setNome(nome);
            
            BOFactory.inserir(new DAOPessoa(), t);
            
            j.put("sucesso", true);
        
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString(); 
    }
    
}
