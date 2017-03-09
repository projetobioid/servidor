/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import bo.BOFactory;
import dao.DAOSafra;
import fw.VerificarSessao;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONObject;
import to.TOSafra;

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
        
        try{      
                //comeca a requisicao
                TOSafra p = new TOSafra();
                p.setIdsafra(k.getLong("idsafra"));
                p = (TOSafra) BOFactory.get(new DAOSafra(), p, k.getString("metodo"));
                if(p == null){
                    j.put("sucesso", false);
                    j.put("mensagem", "Safra não encontrado");
                }else{
                    j.put("data", p.getJson(k.getString("metodo")));
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
        
        try{
             //verifica  a sessao
//            VerificarSessao vs = new VerificarSessao();
//            String sessao = vs.VerificarSessao(k.getString("usuario"), k.getString("sessao"));
//            
//            if( sessao == null){
//                j.put("sucesso", false);
//                j.put("mensagem", "Sessao não encontrada!");
//            }else{
                //comeca a requisicao
                
                TOSafra to = new TOSafra();
                
                
        
                to.setPropriedade_idpropriedade(k.getLong("idpropriedade"));
                        

                
                JSONArray ja = BOFactory.listar(new DAOSafra(),to , k.getString("metodo")) ;

                if(ja.length() > 0){
                    j.put("data", ja);
                    j.put("sucesso", true);

                }else{
                    j.put("sucesso", false);
                    j.put("mensagem", "Propriedade não contém distribuição de cultivares!");

                }

        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString();
    } 
    
    
    
    
    
    
    
    
    
    
}
