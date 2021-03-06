/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import bo.BOFactory;
import dao.DAOSafra;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * REST Web Service
 *
 * @author daniel
 */
@Path("safra")
public class ServicosSafra {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServicosSafra
     */
    public ServicosSafra() {
    }


    @Path("buscar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String buscar(String dataJson) throws Exception{
        
        JSONObject j = new JSONObject();
        JSONObject k = new JSONObject(dataJson);
        List<Object> u = new ArrayList<Object>();
        
        try{      
            
            u.add(k.getLong("idsafra"));
            
            JSONObject data = BOFactory.buscar(new DAOSafra(), u, k.getString("metodo"));
            
            if(data == null){
                j.put("sucesso", false);
                j.put("mensagem", "Safra não encontrado");
            }else{
                j.put("data", data);
                j.put("sucesso", true);
            }           
                

        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString(); 
        
    }
    
    @Path("listar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String listar(String dataJson) throws Exception{
        
        JSONObject j = new JSONObject();
        JSONObject k = new JSONObject(dataJson);
        List<Object> u = new ArrayList<Object>();
        
        try{
                
            u.add(k.getLong("idpropriedade"));
             
            JSONArray data = BOFactory.listar(new DAOSafra(), u, k.getString("metodo")) ;

            if(data == null){
                j.put("sucesso", false);
                j.put("mensagem", "Propriedade não contém distribuição de cultivares!");
            }else{
                j.put("data", data);
                j.put("sucesso", true);
            }  
            

        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString();
    } 
    
  
    
    
    
    
    
    @Path("backupentrevista")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String backupentrevista(String dataJson) throws Exception{
        
        JSONObject j = new JSONObject();
        JSONObject k = new JSONObject(dataJson);
        List<Object> u = new ArrayList<Object>();
        
        try{

            u.add(k.getLong("idpropriedade"));
             
            JSONArray data = BOFactory.listar(new DAOSafra(), u, k.getString("metodo")) ;

            if(data == null){
                j.put("sucesso", false);
                j.put("mensagem", "Propriedade não contém distribuição de cultivares!");
            }else{
                j.put("data", data);
                j.put("sucesso", true);
            }  

        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString();
    } 
    
    
    
    
    
}
