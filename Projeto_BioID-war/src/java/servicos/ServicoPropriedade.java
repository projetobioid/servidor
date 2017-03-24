/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import bo.BOFactory;
import dao.DAOBackupEntrevista;
import dao.DAOPropriedade;
import dao.DAOSafra;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import to.TOBackupEntrevista;
import to.TOPropriedade;
import to.TOSafra;

/**
 * REST Web Service
 *
 * @author daniel
 */
@Path("propriedade")
public class ServicoPropriedade {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServicoPropriedade
     */
    public ServicoPropriedade() {
    }

    @Path("inserir")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String inserir(String dataJson) throws Exception{
        
        JSONObject j = new JSONObject();
        JSONObject k = new JSONObject(dataJson);
        List<Object> u = new ArrayList<Object>();
        try{
             
            u.add(k.getString("nomepropriedade"));

            if(BOFactory.buscar(new DAOPropriedade(), u, "BUSCAR_POR_CPF_OU_NOME") == null ){

               //add componentes endereco e salvar
               
               //add componentes propriedade e salvar
               
               //add componentes relacaopa e salvar
   

                    j.put("sucesso", true);
                    j.put("messangem", "Propriedade cadastrada");
                }else{
                    j.put("sucesso", false);
                    j.put("mensagem", "Propriedade já existe no sistema!");
                }

        }catch(Exception e){
            j.put("sucesso", false);
            j.put("messangem", e.getMessage());
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

            //se tem o item id pesso a eh atribuido a classe
            if(k.has("idpessoa"))
                u.add(k.getLong("idpessoa"));

            if(k.has("usuario"))
                u.add(k.getString("usuario"));

            if(k.has("idunidade"))
                u.add(k.getLong("idunidade"));

            JSONArray data = BOFactory.listar(new DAOPropriedade(), u, k.getString("metodo"));

            if(data != null){
                j.put("data", data);
                j.put("sucesso", true);
            }else{
                j.put("sucesso", false);
                j.put("mensagem", "Usuário não contém propriedade(s) cadastrada(s)!");

            }

        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString();
    }
    
    
    
    
    @Path("buscar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String buscar(String dataJSON) throws JSONException{
        
        JSONObject j = new JSONObject();
        JSONObject k = new JSONObject(dataJSON);
        List<Object> u = new ArrayList<Object>();
         
        try{
            
            u.add(k.getLong("idpropriedade"));
            
            JSONObject data = BOFactory.buscar(new DAOPropriedade(), u, k.getString("metodo"));
            
            j.put("data", data);
            j.put("sucesso", true);
            
        }catch(Exception e){
            j.put("mensagem", e.toString());
            j.put("sucesso", false);
        }
        
        return j.toString();
    }
    
    @Path("backup")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String backup(String dataJSON) throws JSONException{
        
        JSONObject j = new JSONObject();
        JSONObject k = new JSONObject(dataJSON);
        List<Object> u = new ArrayList<Object>();
        try{
            
            u.add(k.getLong("idpropriedade"));
           
            if(BOFactory.buscar(new DAOBackupEntrevista(), u, k.getString("metodo")) == null){
                
                JSONObject data = BOFactory.buscar(new DAOPropriedade(), u, k.getString("metodo"));
                
                if(data != null){
                    JSONArray data_safra = BOFactory.listar(new DAOSafra(), u, "BACKUP_ENTREVISTA");
                    
                    ///cria um novo registro de download para entrevista
                    u.add(k.getString("usuario"));
                    u.add(getDataAtual());
                    u.add(true);
                    BOFactory.inserir(new DAOBackupEntrevista(), u);
                    
                    //lista de propriedades
                    j.put("data", data);
                    //lista de safra
                    j.put("data_safra", data_safra);
                    j.put("sucesso", true); 
                    
                }else{
                    j.put("mensagem", "Propriedade não contém distribuição de cultivares!");
                    j.put("sucesso", false);  
                }
            }else{
                j.put("mensagem", "A propriedade está ancorada à um entrevistador!");
                j.put("sucesso", false);  
            }
            
            
            
        }catch(Exception e){
            j.put("mensagem", e.getMessage());
            j.put("sucesso", false);
        }
        
        return j.toString();
    }
    
    private String getDataAtual() {
                
        Date d = new Date();
        
        return d.toString(); 
    }
}
