/*
lista pais
lista estado
lista cidade
 */
package servicos;

import bo.BOFactory;
import dao.DAOOutrosIDNome;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONObject;
import to.TOOutrosIDNome;

/**
 * REST Web Service
 *
 * @author daniel
 */
@Path("outros")
public class ServicosOutros {

    @Context
    private UriInfo context;

   
    public ServicosOutros() {
    }

    
   @POST
   @Path("listarsp")  
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public String listar(
                        String dataJson
                        ) throws Exception{
        
        JSONObject j = new JSONObject();
        
        JSONObject k = new JSONObject(dataJson);
        
         
        
        try{ 


                JSONArray ja = BOFactory.listar(new DAOOutrosIDNome(), k.getString("metodo"));           


                if(ja.length() > 0){
                    j.put("sucesso", true);
                    j.put("data", ja);

                }else{
                    j.put("sucesso", false);
                    j.put("mensagem", "Sem dados na tabela" + k.getString("metodo"));
                }
            
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString();
   }
   
   @POST
   @Path("listar")  
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public String listarEstadoCidade(
                        String dataJson
                        ) throws Exception{
        
        JSONObject j = new JSONObject();
        
        JSONObject k = new JSONObject(dataJson);
        
         
        
        try{ 

                TOOutrosIDNome t = new TOOutrosIDNome();
                t.setId(k.getLong("id"));

                JSONArray ja = BOFactory.listar(new DAOOutrosIDNome(), t,  k.getString("metodo"));           


                if(ja.length() > 0){
                    j.put("sucesso", true);
                    j.put("data", ja);

                }else{
                    j.put("sucesso", false);
                    j.put("mensagem", "Sem dados na tabela" + k.getString("metodo"));
                }
            
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString();
   }
   
   
   @POST
   @Path("teste")  
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public String teste(String dataJson
                      ) throws Exception{
        

        
        JSONArray  ja = new JSONArray();
        
        ja.put(dataJson);
        ja.put("AppleScript");
        ja.put("Asp");
        ja.put("BASIC");
        ja.put("ColdFusion");
        ja.put("COBOL");
        ja.put("Clojure");
        ja.put("JavaScript");
        ja.put("Python");
        ja.put("daniel");
        
        return ja.toString();
   }

}
