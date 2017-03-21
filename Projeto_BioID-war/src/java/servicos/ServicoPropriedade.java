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
import to.TOBackupEntrevista;
import to.TOLogin;
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
    public String inserirPropriedade(String dataJson) throws Exception{
        
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
            
//                //objeto TOEndereco
//                TOEndereco te = new TOEndereco();
//                TOPropriedade tpd = new TOPropriedade();
//
//                tpd.setNomepropriedade(nomepropriedade);
//                tpd.setCpf(cpf);
//
//                if(BOFactory.get(new DAOPropriedade(), tpd, "") == null ){
//                    te.setCidade_idcidade(cidade_idcidade);
//                    te.setRua(rua);
//                    te.setGps_lat(gps_lat);
//                    te.setGps_long(gps_long);
//                    te.setBairro(bairro);
//                    te.setComplemento(complemento);
//                    te.setCep(cep);
//                    te.setNumero(numero);
//
//                    tpd.setEndereco_idendereco(BOFactory.inserir(new DAOEndereco(), te));
//                    tpd.setUnidade_idunidade(unidade_idunidade);
//
//                    tpd.setArea(area);
//                    tpd.setUnidadedemedida(unidadedemedida);
//                    tpd.setAreautilizavel(areautilizavel);
//                    tpd.setUnidadedemedidaau(unidadedemedidaau);
//
//                    TORelacaopa tr = new TORelacaopa();
//
//                    TOPessoa tp = new TOPessoa();
//                    tp.setCpf(cpf);
//                    tr.setPropriedade_idpropriedade(BOFactory.inserir(new DAOPropriedade(), tpd));
//                    ((TOPessoa) BOFactory.get(new DAOPessoa(), tp, "")).getIdpessoa();
//
//                    tr.setAgricultor_pessoa_idpessoa(((TOPessoa) BOFactory.get(new DAOPessoa(), tp, "")).getIdpessoa());
//
//                    BOFactory.inserir(new DAORelacaopa(), tr);
//
//                    j.put("sucesso", true);
//                    j.put("sessao", novaSessao);
//                    j.put("messangem", "Propriedade cadastrada");
//                }else{
//                    j.put("sucesso", false);
//                    j.put("sessao", novaSessao);
//                    j.put("mensagem", "Propriedade ja existe no sistema!");
//                }
//            }
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
    public String listarPropriedades(String dataJson) throws Exception{
        
        JSONObject j = new JSONObject();
        JSONObject k = new JSONObject(dataJson);
        
        try{

                TOLogin t = new TOLogin();
                //se tem o item id pesso a eh atribuido a classe
                if(k.has("idpessoa"))
                    t.setPessoa_idpessoa(k.getLong("idpessoa"));
                
                if(k.has("usuario"))
                    t.setUsuario(k.getString("usuario"));
                
                if(k.has("idunidade"))
                    t.setUnidade_idunidade(k.getLong("idunidade"));


                JSONArray ja = BOFactory.listar(new DAOPropriedade(), t, k.getString("metodo"));

                if(ja.length() > 0){
                    j.put("data", ja);
                    j.put("sucesso", true);
                }else{
                    j.put("sucesso", false);
                    j.put("mensagem", "Usuário não contém propriedade cadastrada!");

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
        
        try{
            
            TOPropriedade t = new TOPropriedade();
            t.setIdpropriedade(k.getLong("idpropriedade"));
            
            t = (TOPropriedade) BOFactory.buscar(new DAOPropriedade(), t, k.getString("metodo"));
            
            j.put("data", t.buscarJson(k.getString("metodo")));
            j.put("sucesso", true);
            
        }catch(Exception e){
            j.put("mensagem", e.getMessage());
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
        
        try{
            
            TOBackupEntrevista t = new TOBackupEntrevista();
            t.setPropriedade_idpropriedade(k.getLong("idpropriedade"));
            
            t = (TOBackupEntrevista) BOFactory.buscar(new DAOBackupEntrevista(), t, k.getString("metodo"));
            
            if(t == null){
                TOSafra to = new TOSafra();
                to.setPropriedade_idpropriedade(k.getLong("idpropriedade"));
                
                JSONArray ja = BOFactory.listar(new DAOSafra(),to , "BACKUP_ENTREVISTA");
                if(ja.length() > 0){
                    TOPropriedade tp = new TOPropriedade();
                    tp.setIdpropriedade(k.getLong("idpropriedade"));

                    tp = (TOPropriedade) BOFactory.buscar(new DAOPropriedade(), tp, k.getString("metodo"));

    
                    
                    ///cria um novo registro de download para entrevista
                    t = new TOBackupEntrevista();
                    t.setLogin_usuario(k.getString("usuario"));
                    t.setPropriedade_idpropriedade(tp.getIdpropriedade());
                    t.setStatus_backup(true);
                    t.setData_backup(getDataAtual());
                    BOFactory.inserir(new DAOBackupEntrevista(), t);
                    
                    //lista de propriedades
                    j.put("data", tp.buscarJson(k.getString("metodo")));
                    //lista de safra
                    j.put("data_safra", ja);
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
