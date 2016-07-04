/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import bo.BOFactory;
import dao.DAOProdutos;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import org.json.JSONArray;
import org.json.JSONObject;
import to.TOProdutos;

/**
 * REST Web Service
 *
 * @author daniel
 */
@Path("produtos")
public class ServicoProdutos {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServicoProdutos
     */
    public ServicoProdutos() {
    }

    //metodo que lista todos os produtos do banco de dados
    @GET
    @Path("listar")
    //@Produces({MediaType.APPLICATION_XML})//, MediaType.APPLICATION_JSON})
    public String listar() throws Exception {
        
        JSONObject j = new JSONObject();
        
        try{
            JSONArray ja = BOFactory.listar(new DAOProdutos()) ;
        
            j.put("data", ja);
            j.put("sucesso", true);
        
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString();
    }
    
    //metodo que insere no banco de dados
    @POST
    @Path("inserir")
    public String inserir(
            @FormParam("id") String id,
            @FormParam("nome") String nome,
            @FormParam("descricao") String descricao,
            @FormParam("biofortificado") boolean biofortificado,
            @FormParam("tipo") String tipo) throws Exception{
        
                
        JSONObject j = new JSONObject();
        
        try{    
            
            TOProdutos t = new TOProdutos();
           //p.setId(Guid.getString());
            t.setId(id);
            t.setNome(nome);
            t.setDescricao(descricao);
            t.setBiofortificado(biofortificado);
            t.setTipo(tipo);
            
            BOFactory.inserir(new DAOProdutos(), t);
            
            j.put("sucesso", true);
        
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString(); 
    }
    
    //metodo que insere no banco de dados
    @POST
    @Path("editar")
    public String editar(
            @FormParam("id") String id,
            @FormParam("nome") String nome,
            @FormParam("descricao") String descricao,
            @FormParam("biofortificado") boolean biofortificado,
            @FormParam("tipo") String tipo) throws Exception{
        
                
        JSONObject j = new JSONObject();
        
        try{    
            
            TOProdutos t = new TOProdutos();
            t.setId(id);
            
            t = (TOProdutos) BOFactory.get(new DAOProdutos(), t);
            
            if(t == null){
                j.put("sucesso", false);
                j.put("mensagem", "Produto não encontrado");
            }else{
                t.setNome(nome);
                t.setDescricao(descricao);
                t.setBiofortificado(biofortificado);
                t.setTipo(tipo); 
                
                BOFactory.editar(new DAOProdutos(), t);
                j.put("sucesso", true); 
            }
           
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString(); 
    }
    
    

    
    @POST
    @Path("excluir")
    public String excluir(
            @FormParam("id") String id)throws Exception{
        
        JSONObject j = new JSONObject();
        
        try{               
            
            TOProdutos p = new TOProdutos();
            p.setId(id);
            
            p = (TOProdutos) BOFactory.get(new DAOProdutos(), p);
            
            if(p == null){
                j.put("sucesso", false);
                j.put("mensagem", "Produto não encontrado");
            }else{
                BOFactory.excluir(new DAOProdutos(), p);
                j.put("sucesso", true);
            }
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString(); 
        
    }
    

    
}
