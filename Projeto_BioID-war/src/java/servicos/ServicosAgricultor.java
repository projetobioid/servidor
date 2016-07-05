/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import bo.BOFactory;
import dao.DAOAgricultor;
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
@Path("agricultor")
public class ServicosAgricultor {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServicosAgricultor
     */
    public ServicosAgricultor() {
    }

    //serviço de listar os usuarios
    @GET
    @Path("listar")
    public String listar() throws Exception{
    
        JSONObject j = new JSONObject();
        
        try{
            //cria um jsonarray e popula com uma consulta no banco
            JSONArray ja = BOFactory.listar(new DAOAgricultor());
            j.put("Data", ja);
            j.put("sucesso", true);
            
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        
        return j.toString();
    }
}
