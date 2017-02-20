/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import bo.BOFactory;
import dao.DAOEndereco;
import dao.DAOUnidade;
import fw.VerificarSessao;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONObject;
import to.TOEndereco;
import to.TOUnidade;

/**
 * REST Web Service
 *
 * @author daniel
 */
@Path("unidade")
public class ServicosUnidade {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServicosUnidade
     */
    public ServicosUnidade() {
    }
    
    //metodo buscar uma unidade especifica
    @Path("buscar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String buscar(String dataJson) throws Exception{
        
        JSONObject j = new JSONObject();
        JSONObject k = new JSONObject(dataJson);
        
        try{
            
             //verifica  a sessao
            VerificarSessao vs = new VerificarSessao();
            String sessao = vs.VerificarSessao(k.getString("usuario"), k.getString("sessao"));
            
            if( sessao == null){
                j.put("sucesso", false);
                j.put("mensagem", "Sessao não encontrada!");
            }else{
                //comeca a requisicao
                TOUnidade p = new TOUnidade();
                p.setIdunidade(k.getLong("idunidade"));
                p = (TOUnidade) BOFactory.get(new DAOUnidade(), p, k.getString("metodo"));

                if(p == null){
                    j.put("sucesso", false);
                    j.put("sessao", sessao);
                    j.put("mensagem", "Unidade não encontrada");
                }else{
                    j.put("data", p.getJson(k.getString("metodo")));
                    j.put("sessao", sessao);
                    j.put("sucesso", true);
                }
            }
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString(); 
        
    }
    
    //metodo que insere no banco de dados
    @Path("inserir")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String inserir(String dataJson) throws Exception{
        
        JSONObject j = new JSONObject();
        JSONObject k = new JSONObject(dataJson);
        
        try{
             //verifica  a sessao
            VerificarSessao vs = new VerificarSessao();
            String sessao = vs.VerificarSessao(k.getString("usuario"), k.getString("sessao"));
            
            if( sessao == null){
                j.put("sucesso", false);
                j.put("mensagem", "Sessao não encontrada!");
            }else{
                //comeca a requisicao  
                //cria um objeto          
                TOUnidade t = new TOUnidade();
                t.setCnpj(k.getString("cnpj"));

                //teste se existe a unidade cadastrada, senao cadastra
                if(BOFactory.get(new DAOUnidade(), t, "default")== null){
                    //objeto TOEndereco
                    TOEndereco te = new TOEndereco();
                    te.setCidade_idcidade(k.getLong("cidade_idcidade"));
                    te.setRua(k.getString("rua"));
                    te.setGps_lat(k.getInt("gps_lat"));
                    te.setGps_long(k.getInt("gps_long"));
                    te.setBairro(k.getString("bairro"));
                    te.setComplemento(k.getString("complemento"));
                    te.setCep(k.getString("cep"));
                    te.setNumero(k.getInt("numero"));
                    //grava no banco de dados os dados da classe TOLogin e retorna o id gerado
                    t.setEndereco_idendereco(BOFactory.inserir(new DAOEndereco(), te, "default"));
                    t.setNomeunidade(k.getString("nomeunidade"));
                    t.setTelefone1(k.getString("telefone1"));
                    t.setTelefone2(k.getString("telefone2"));
                    t.setEmail(k.getString("email"));
                    t.setRazao_social(k.getString("razao_social"));
                    t.setNome_fanta(k.getString("nome_fanta"));

                    BOFactory.inserir(new DAOUnidade(), t, "default");

                    j.put("sucesso", true);
                    j.put("mensagem", "Unidade cadastrada!");
                    j.put("sessao", sessao);
                }else{
                   j.put("sucesso", false);
                   j.put("sessao", sessao);
                   j.put("mensagem", "Unidade já cadastrada!");
                }
            }
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString(); 
    }
    
    //metodo que lista todas as unidades do banco de dados
    @Path("listar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String listar(String dadosJson) throws Exception{
        
        JSONObject j = new JSONObject();
        JSONObject k = new JSONObject(dadosJson);
        
        try{
             //verifica  a sessao
            VerificarSessao vs = new VerificarSessao();
            String sessao = vs.VerificarSessao(k.getString("usuario"), k.getString("sessao"));
            
            if( sessao == null){
                j.put("sucesso", false);
                j.put("mensagem", "Sessao não encontrada!");
            }else{
                //comeca a requisicao
                TOUnidade t = new TOUnidade();
               
                JSONArray ja = null;
                
                switch(k.getString("metodo")){
                    case "todas": 
                    case "unidades": 
//                        t.setIdunidade(k.getLong("idunidade"));
                        ja = BOFactory.listar(new DAOUnidade(), t, k.getString("metodo"));
                        break;
                    default:
                        ja = BOFactory.listar(new DAOUnidade(), t, "default");
                        break;
                }
                
        
                j.put("data", ja);
                j.put("sessao", sessao);
                j.put("sucesso", true);
                
            }
            
        
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString();
    }
    

//    
//  //metodo listar cultivares que existem no estoque da unidade 
//    @Path("listarestoqueunidade")
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public String listarestoqueunidade(String dataJson) throws Exception{
//        
//        JSONObject j = new JSONObject();
//        JSONObject k = new JSONObject(dataJson);
//         
//        
//        try{ 
//            //verificar sessao
//            JSONObject js = new VerificarSessao().VerificarSessao(k.getString("usuario"), k.getString("sessao"));
//            
//            
//            if((boolean) js.get("sucesso") == false){
//                j.put("sucesso", false);
//                j.put("mensagem", "Sessao não encontrada!");
//            }else{
//                TOEstoque t = new TOEstoque();
//                t.setUnidade_idunidade(k.getLong("idunidade"));
//
//                //lista os cultivares do estoque
//                JSONArray ja = BOFactory.listar(new DAOEstoque(), t, k.getString("metodo") );
//
//
//
//                if(ja.length() > 0){
//                    j.put("sucesso", true);
//                    j.put("data", ja);
//                    j.put("sessao", js.get("sessao"));
//                }else{
//
//                    j.put("sucesso", false);
//                    j.put("sessao", js.get("sessao"));
//                    j.put("mensagem", "Sem cultivares no estoque!"); 
//                }
//            }
//
//        }catch(Exception e){
//            j.put("sucesso", false);
//            j.put("mensagem", e.getMessage());
//        }
//        
//        return j.toString();
//    }
//    
//    @Path("listaragricultoresunidade")
//    @POST
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    @Produces(MediaType.APPLICATION_JSON)
//    public String listarPropriedades(
//                        @FormParam("idunidade") long idunidade,
//                        @FormParam("id") long id,
//                        @FormParam("sessao") String sessao
//                        ) throws Exception{
//        
//        JSONObject j = new JSONObject();
// 
//        try{
//            //verificar sessao
//            JSONObject js = new VerificarSessao().VerificarSessao(id, sessao);
//            
//            
//            if((boolean) js.get("sucesso") == false){
//                j.put("sucesso", false);
//                j.put("mensagem", "Sessao não encontrada!");
//            }else{
//                
//                TOUnidade t = new TOUnidade();
//                t.setIdunidade(idunidade);
//
//
//                JSONArray ja = BOFactory.listar(new DAOPessoa(), t) ;
//
//                if(ja.length() > 0){
//                    j.put("listaAgricultores", ja);
//                    j.put("sucesso", true);
//                    j.put("sessao", js.get("sessao"));
//                }else{
//                    j.put("sucesso", false);
//                    j.put("mensagem", "Sem agricultores na unidade");
//                    j.put("sessao", js.get("sessao"));
//                }
//            }
//        }catch(Exception e){
//            j.put("sucesso", false);
//            j.put("mensagem", e.getMessage());
//        }
//        
//        return j.toString();
//    } 

    


}
