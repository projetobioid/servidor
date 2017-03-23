/*
lista pais
lista estado
lista cidade
 */
package servicos;

import bo.BOFactory;
import dao.DAOLogin;
import dao.DAOPaisEstadoCidade;
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
import to.TOLogin;


/**
 * REST Web Service
 *
 * @author daniel
 */
@Path("outros")

public class ServicosOutros {

    @Context
    private UriInfo context;

//    @Inject
//    private OutletService outletService;
   
    public ServicosOutros() {
    }

    //login
    @Path("validacao")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String login(String dataJson) throws Exception{
        
        //objeto de retorno da requisicao
        JSONObject j = new JSONObject();
        JSONObject k = new JSONObject(dataJson);
        
        try{
            TOLogin to = new TOLogin();
            
            to.setUsuario(k.getString("usuario"));
            to.setSenha(k.getString("senha"));
            
            JSONObject data = BOFactory.buscar(new DAOLogin(), to, k.getString("metodo"));
            
            if(data == null){
                j.put("sucesso", false);
                j.put("mensagem", "Usuário ou senha incorretos!");
            }else{

                j.put("data", data);
                j.put("sucesso", true);

            }
        }catch (Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString();
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
//            switch(k.getString("metodo")){
//                case "PAIS":
//                    ja = BOFactory.listar(new DAOPaisEstadoCidade(), null, k.getString("metodo"));
//                    break;
//                case "ESTADOS":
//                    TOOutrosIDNome tp = new TOOutrosIDNome();
//                    tp.setId(k.getLong("idpais"));
//                    ja = BOFactory.listar(new DAOPaisEstadoCidade(), tp, k.getString("metodo"));
//                    break;
//                case "CIDADES":
//                    TOOutrosIDNome te = new TOOutrosIDNome();
//                    te.setId(k.getLong("idestado"));
//                    ja = BOFactory.listar(new DAOPaisEstadoCidade(), te, k.getString("metodo"));
//                    break;
//            }

             
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
   
  
   
   @GET
   @Path("testeget")  
//   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public String testeget() throws Exception{
        

        
        JSONArray  ja = new JSONArray();

        ja.put("GET");
        ja.put("serviço");
        ja.put("retorno");
        ja.put("teste");
        ja.put("bioid");
        
        return ja.toString();
   }
   
   @POST
   @Path("testepost")  
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public String testepost(String dataJson) throws Exception{
        
        JSONObject k = new JSONObject(dataJson);
        

        
        JSONArray  ja = new JSONArray();

        ja.put("POST");
        ja.put("serviço");
        ja.put("retorno");
        ja.put(k.get("valores"));

        
        return ja.toString();
   }
   

   
}
