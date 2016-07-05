/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import bo.BOFactory;
import dao.DAOCultivar;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import org.json.JSONArray;
import org.json.JSONObject;
import to.TOCultivar;

/**
 * REST Web Service
 *
 * @author daniel
 */
@Path("cultivar")
public class ServicosCultivar {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServicoProdutos
     */
    public ServicosCultivar() {
    }

    //metodo que lista todos os produtos do banco de dados
    @GET
    @Path("listar")
    //@Produces({MediaType.APPLICATION_XML})//, MediaType.APPLICATION_JSON})
    public String listar() throws Exception {
        
        JSONObject j = new JSONObject();
        
        try{
            JSONArray ja = BOFactory.listar(new DAOCultivar()) ;
        
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
            @FormParam("id") int id,
            @FormParam("nome") String nome,
            @FormParam("descricao") String descricao,
            @FormParam("biofortificado") boolean biofortificado,
            @FormParam("tipo") String tipo) throws Exception{
        
                
        JSONObject j = new JSONObject();
        
        try{    
            
            TOCultivar t = new TOCultivar();
           //p.setId(Guid.getString());
            t.setId(id);
            t.setNome(nome);
            t.setDescricao(descricao);
            t.setBiofortificado(biofortificado);
            t.setTipo(tipo);
            
            BOFactory.inserir(new DAOCultivar(), t);
            
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
            @FormParam("id") int id,
            @FormParam("nome") String nome,
            @FormParam("descricao") String descricao,
            @FormParam("biofortificado") boolean biofortificado,
            @FormParam("tipo") String tipo) throws Exception{
        
                
        JSONObject j = new JSONObject();
        
        try{    
            
            TOCultivar t = new TOCultivar();
            t.setId(id);
            
            t = (TOCultivar) BOFactory.get(new DAOCultivar(), t);
            
            if(t == null){
                j.put("sucesso", false);
                j.put("mensagem", "Produto não encontrado");
            }else{
                t.setNome(nome);
                t.setDescricao(descricao);
                t.setBiofortificado(biofortificado);
                t.setTipo(tipo); 
                
                BOFactory.editar(new DAOCultivar(), t);
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
            @FormParam("id") int id)throws Exception{
        
        JSONObject j = new JSONObject();
        
        try{               
            
            TOCultivar p = new TOCultivar();
            p.setId(id);
            
            p = (TOCultivar) BOFactory.get(new DAOCultivar(), p);
            
            if(p == null){
                j.put("sucesso", false);
                j.put("mensagem", "Produto não encontrado");
            }else{
                BOFactory.excluir(new DAOCultivar(), p);
                j.put("sucesso", true);
            }
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString(); 
        
    }
    

    
}
