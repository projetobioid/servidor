/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import bo.BOFactory;
import dao.DAOEstoque;
import dao.DAOIOEstoque;
import java.util.Date;
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
import to.TOIOEstoque;

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
        
        try{       
            
//            VerificarSessao vs = new VerificarSessao();
//            String sessao = vs.VerificarSessao(k.getString("usuario"), k.getString("sessao"));
//            
//            if( sessao == null){
//                j.put("sucesso", false);
//                j.put("mensagem", "Sessao não encontrada!");
//            }else{
                //comeca a requisicao
    
                TOEstoque te = new TOEstoque();
                te.setCultivar_idcultivar(k.getLong("idcultivar"));
                te.setUnidade_idunidade(k.getLong("idunidade"));
                te = (TOEstoque) BOFactory.get(new DAOEstoque(), te, k.getString("metodo"));

                if(te == null){
                    j.put("sucesso", false);
//                    j.put("sessao", sessao);
                    j.put("mensagem", "Cultivar não encontrado no estoque!");
                }else{
                    j.put("data", te.getJson(k.getString("metodo")));
//                    j.put("sessao", sessao);
                    j.put("sucesso", true);
                }
      
//            }
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
            
            //verificar sessao
//            VerificarSessao vs = new VerificarSessao();
//            String sessao = vs.VerificarSessao(k.getString("usuario"), k.getString("sessao"));
//            
//            if( sessao == null){
//                j.put("sucesso", false);
//                j.put("mensagem", "Sessao não encontrada!");
//            }else{
                //comeca a requisicao  
                JSONArray ja = null;
                

                TOEstoque t = new TOEstoque();
                t.setUnidade_idunidade(k.getLong("idunidade"));
                //lista os cultivares do estoque
                ja = BOFactory.listar(new DAOEstoque(), t, k.getString("metodo") );

                if(ja.length() > 0){
                    j.put("data", ja);
                    j.put("sucesso", true);
//                    j.put("sessao", sessao);
                }else{
                    j.put("sucesso", false);
                    j.put("mensagem", "Estoque vazio!");
//                    j.put("sessao", sessao);
                }
                
//            }
            
        
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
                
                //cria um objeto          
                TOEstoque te = new TOEstoque();
                te.setUnidade_idunidade(k.getLong("idunidade"));
                te.setCultivar_idcultivar(k.getLong("idcultivar"));
                
                //entrada de estoque
                    
                //busca se existe o cultivar no estoque
                te = (TOEstoque) BOFactory.get(new DAOEstoque(), te, "DEFAULT");

                //se nao existe cria uma nova entrada
                if(te == null){
                    te = new TOEstoque();
                    te.setQuantidade(k.getDouble("qtd"));
                    te.setUnidade_idunidade(k.getLong("idunidade"));
                    te.setCultivar_idcultivar(k.getLong("idcultivar"));

                    BOFactory.inserir(new DAOEstoque(), te, "DEFAULT");

                    if(historicoIOEstoque(k)){
                        j.put("sucesso", true);
                        j.put("mensagem", "Entrada do estoque com sucesso!");
//                        j.put("sessao", sessao);  
                    }else{
                        j.put("sucesso", false);
                        j.put("mensagem", "Erro histórico estoque! Entrada do estoque com sucesso!");
//                        j.put("sessao", sessao);
                    }



                //senao atualiza o estoque
                }else{

                    te.setQuantidade(te.getQuantidade() + k.getDouble("qtd"));

                    BOFactory.editar(new DAOEstoque(), te, "DEFAULT");

                    if(historicoIOEstoque(k)){
                        j.put("sucesso", true);
                        j.put("mensagem", "Entrada do estoque com sucesso!");
//                        j.put("sessao", sessao);  
                    }else{
                        j.put("sucesso", false);
                        j.put("mensagem", "Erro histórico estoque! Entrada do estoque com sucesso!");
//                        j.put("sessao", sessao);
                    }
                }  
//            }
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
                
                //cria um objeto          
                TOEstoque te = new TOEstoque();
                te.setUnidade_idunidade(k.getLong("idunidade"));
                te.setCultivar_idcultivar(k.getLong("idcultivar"));
                
                
                //saida de estoque
                
                    
                te = (TOEstoque) BOFactory.get(new DAOEstoque(), te, k.getString("metodo"));

                te.setQuantidade(te.getQuantidade() - k.getDouble("qtd"));
                BOFactory.editar(new DAOEstoque(), te, "default");



                if(historicoIOEstoque(k)){
                    j.put("sucesso", true);
                    j.put("mensagem", "Saída do estoque com sucesso!");
//                    j.put("sessao", sessao);  
                }else{
                    j.put("sucesso", false);
                    j.put("mensagem", "Erro histórico estoque! Saída do estoque com sucesso!");
//                    j.put("sessao", sessao);
                }

//            }
                

        }catch(Exception e){
            j.put("sucesso", false);
            j.put("erro", "erro atualização estoque da unidade!");
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString(); 
    }

    private boolean historicoIOEstoque(JSONObject k) throws JSONException {

        try {
            //cria um historico de io do estoque
            TOIOEstoque tio = new TOIOEstoque();
            tio.setUnidade_idunidade(k.getLong("idunidade"));
            tio.setCultivar_idcultivar(k.getLong("idcultivar"));
            tio.setQuantidade(k.getDouble("qtd"));
            tio.setData_io(getDataAtual());
            tio.setOperacao(k.getInt("operacao"));
            tio.setLogin_usuario(k.getString("usuario"));

            BOFactory.inserir(new DAOIOEstoque(), tio, "DEFAULT");
            
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
