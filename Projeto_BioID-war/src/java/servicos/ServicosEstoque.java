/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import bo.BOFactory;
import dao.DAOEstoque;
import dao.DAOIOEstoque;
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
import to.TOEstoque;

/**
 * REST Web Service
 *
 * @author daniel
 */
@Path("estoque")
public class ServicosEstoque {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServicosEstoque
     */
    public ServicosEstoque() {
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

            u.add(k.getLong("idcultivar"));
            u.add(k.getLong("idunidade"));
            
            JSONObject data = BOFactory.buscar(new DAOEstoque(), u, k.getString("metodo"));

            if(data == null){
                j.put("sucesso", false);
                j.put("mensagem", "Cultivar não encontrado no estoque!");
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
            
            u.add(k.getLong("idunidade"));
            //comeca a requisicao  
            JSONArray data = BOFactory.listar(new DAOEstoque(), u, k.getString("metodo") );

            if(data != null){
                j.put("data", data);
                j.put("sucesso", true);
            }else{
                j.put("sucesso", false);
                j.put("mensagem", "Estoque vazio!");
            }

            
        
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString();
    }
    
    //inseri dados no estoque
    @Path("entradaestoque")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String entradaEstoque( String dataJson) throws Exception{
        
                
        JSONObject j = new JSONObject();
        JSONObject k = new JSONObject(dataJson);
        List<Object> u = new ArrayList<Object>();
        
        try{
            //cria um objeto          
            TOEstoque te = new TOEstoque();
            
            u.add(k.getLong("idunidade"));
            u.add(k.getLong("idcultivar"));

            //entrada de estoque

            //busca se existe o cultivar no estoque
            JSONObject data = BOFactory.buscar(new DAOEstoque(), u, "DEFAULT");

            //se nao existe cria uma nova entrada
            if(data == null){
                
                u.clear();
                u.add(k.getLong("unidade_idunidade"));
                u.add(k.getLong("cultivar_idcultivar"));
                u.add(k.getDouble("qtd"));
                
                BOFactory.inserir(new DAOEstoque(), u);

                if(historicoIOEstoque(k)){
                    j.put("sucesso", true);
                    j.put("mensagem", "Entrada do estoque com sucesso!");
                }else{
                    j.put("sucesso", false);
                    j.put("mensagem", "Erro histórico estoque! Entrada do estoque com sucesso!");
                }

            //senao atualiza o estoque
            }else{

                u.clear();
                
                u.add(te.getQuantidade() + k.getDouble("qtd"));
                u.add(k.getLong("unidade_idunidade"));
                u.add(k.getLong("cultivar_idcultivar"));
                
                BOFactory.editar(new DAOEstoque(), u);

                if(historicoIOEstoque(k)){
                    j.put("sucesso", true);
                    j.put("mensagem", "Entrada do estoque com sucesso!");
 
                }else{
                    j.put("sucesso", false);
                    j.put("mensagem", "Erro histórico estoque! Entrada do estoque com sucesso!");

                }
            }  
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("erro", "erro atualização estoque da unidade!");
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString(); 
    }
    
    //inseri dados no estoque
    @Path("saidaestoque")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String saidaEstoque( String dataJson) throws Exception{
        
                
        JSONObject j = new JSONObject();
        JSONObject k = new JSONObject(dataJson);
        List<Object> u = new ArrayList<Object>();
        
        try{  
            
            TOEstoque te = new TOEstoque();
            u.add(k.getLong("idunidade"));
            u.add(k.getLong("idcultivar"));

            te = (TOEstoque) BOFactory.buscarObj(new DAOEstoque(), u, k.getString("metodo"));

            te.setQuantidade(te.getQuantidade() - k.getDouble("qtd"));
            
            u.clear();
            u.add(te.getQuantidade() - k.getDouble("qtd"));
            u.add(k.getLong("idunidade"));
            u.add(k.getLong("idcultivar"));
            
            BOFactory.editar(new DAOEstoque(), u);
            
            if(historicoIOEstoque(k)){
                j.put("sucesso", true);
                j.put("mensagem", "Saída do estoque com sucesso!");
            }else{
                j.put("sucesso", false);
                j.put("mensagem", "Erro histórico estoque! Saída do estoque com sucesso!");
            }

        }catch(Exception e){
            j.put("sucesso", false);
            j.put("erro", "erro atualização estoque da unidade!");
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString(); 
    }

    private boolean historicoIOEstoque(JSONObject k) throws JSONException {

        try {
            List<Object> u = new ArrayList<Object>();
            //cria um historico de io do estoque
            u.add(k.getLong("idunidade"));
            u.add(k.getLong("idcultivar"));
            u.add(k.getDouble("qtd"));
            u.add(getDataAtual());
            u.add(k.getInt("operacao"));
            u.add(k.getString("usuario"));

            BOFactory.inserir(new DAOIOEstoque(), u);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    private String getDataAtual() {
                
        Date d = new Date();
        
        return d.toString(); 
    }
}
