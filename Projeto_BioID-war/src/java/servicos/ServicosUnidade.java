/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import bo.BOFactory;
import dao.DAOEndereco;
import dao.DAOUnidade;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
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
        List<Object> u = new ArrayList<Object>();
        
        try{
            
            u.add(k.getLong("idunidade"));
            
            JSONObject data = BOFactory.buscar(new DAOUnidade(), u, k.getString("metodo"));

            if(data == null){
                j.put("sucesso", false);
                j.put("mensagem", "Unidade não encontrada");
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
    
    //metodo que insere no banco de dados
    @Path("inserir")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String inserir(String dataJson) throws Exception{
        
        JSONObject j = new JSONObject();
        JSONObject k = new JSONObject(dataJson);
        List<Object> u = new ArrayList<Object>();
        
        
        try{ 
             
            u.add(k.getString("cnpj"));

            //teste se existe a unidade cadastrada, senao cadastra
            if(BOFactory.buscar(new DAOUnidade(), u, "GET_POR_CNPJ")== null){
                long idGerado; 
                
                //tabela endereco
                u.add(k.getLong("cidade_idcidade"));
                u.add(k.getString("rua"));
                u.add(k.getInt("gps_lat"));
                u.add(k.getInt("gps_long"));
                u.add(k.getString("bairro"));
                u.add(k.getString("complemento"));
                u.add(k.getString("cep"));
                u.add(k.getInt("numero"));
                idGerado = BOFactory.inserir(new DAOEndereco(), u);
                
                //grava no banco de dados os dados da classe TOLogin e retorna o id gerado
                u.clear();
                u.add(idGerado);
                u.add(k.getString("nomeunidade"));
                u.add(k.getString("telefone1"));
                u.add(k.getString("telefone2"));
                u.add(k.getString("email"));
                u.add(k.getString("razao_social"));
                u.add(k.getString("nome_fanta"));

                BOFactory.inserir(new DAOUnidade(), u);

                j.put("sucesso", true);
                j.put("mensagem", "Unidade cadastrada!");
            }else{
               j.put("sucesso", false);
               j.put("mensagem", "Unidade já cadastrada!");
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

            JSONArray data = BOFactory.listar(new DAOUnidade(), k.getString("metodo"));

            if(data == null){
                j.put("sucesso", false);
                j.put("mensagem", "Sem unidades cadastradas!");
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
