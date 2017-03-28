/*
lista pais
lista estado
lista cidade
 */
package servicos;

import bo.BOFactory;
import dao.DAOCidade;
import dao.DAOEstado;
import dao.DAOLogin;
import dao.DAOPais;
import java.util.ArrayList;
import java.util.List;
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

    //login
    @Path("validacao")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String login(String dataJson) throws Exception{
        
        //objeto de retorno da requisicao
        JSONObject j = new JSONObject();
        JSONObject k = new JSONObject(dataJson);
        List<Object> u = new ArrayList<Object>();
        
        try{
            
            u.add(k.getString("usuario"));
            u.add(k.getString("senha"));
            JSONObject data = BOFactory.buscar(new DAOLogin(), u, k.getString("metodo"));
            
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
    @Path("listarpais")
    @Produces(MediaType.APPLICATION_JSON)
    public String listarPais() throws Exception{
        
        JSONObject j = new JSONObject();
        
        try{ 
            
            JSONArray data = BOFactory.listar(new DAOPais());
            
            if(data == null){
                j.put("sucesso", false);
                j.put("mensagem", "Tabela país vazia!");
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
    
    @POST
    @Path("listarestados")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String listarEstados(String dataJson) throws Exception{
        
        JSONObject j = new JSONObject();
        JSONObject k = new JSONObject(dataJson);
        List<Object> u = new ArrayList<Object>();
        
        try{
                
            u.add(k.getLong("idpais"));
            JSONArray data = BOFactory.listar(new DAOEstado(), u);

            if(data == null){
                j.put("sucesso", false);
                j.put("mensagem", "Tabela estado vazia!");
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
    
    @POST
    @Path("listarcidades")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String listarcidades(String dataJson) throws Exception{
           
        JSONObject j = new JSONObject();
        JSONObject k = new JSONObject(dataJson);
        List<Object> u = new ArrayList<Object>();
        
        try{
                
            u.add(k.getLong("idestado"));
            JSONArray data = BOFactory.listar(new DAOCidade(), u);

            if(data == null){
                j.put("sucesso", false);
                j.put("mensagem", "Tabela cidade vazia!");
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
