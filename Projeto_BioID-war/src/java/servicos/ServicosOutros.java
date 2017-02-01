/*
lista pais
lista estado
lista cidade
 */
package servicos;

import bo.BOFactory;
import dao.DAOOutrosIDNome;
import javax.ws.rs.Consumes;
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
   @Path("listar")  
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public String listar(String dataJson) throws Exception{
        
        JSONObject j = new JSONObject();
        
        JSONObject k = new JSONObject(dataJson);
        
         
        
        try{ 
            JSONArray ja = null;
            switch(k.getString("metodo")){
                case "pais":
                    ja = BOFactory.listar(new DAOOutrosIDNome(), null, k.getString("metodo"));
                    break;
                case "estados":
                    TOOutrosIDNome tp = new TOOutrosIDNome();
                    tp.setId(k.getLong("idpais"));
                    ja = BOFactory.listar(new DAOOutrosIDNome(), tp, k.getString("metodo"));
                    break;
                case "cidades":
                    TOOutrosIDNome te = new TOOutrosIDNome();
                    te.setId(k.getLong("idestado"));
                    ja = BOFactory.listar(new DAOOutrosIDNome(), te, k.getString("metodo"));
                    break;
                case "nome_id_unidades":
                    ja = BOFactory.listar(new DAOOutrosIDNome(), null, k.getString("metodo"));
                    break;
            }

             
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
