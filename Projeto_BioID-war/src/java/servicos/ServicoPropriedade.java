/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import bo.BOFactory;
import dao.DAOPropriedade;
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
import to.TOPessoa;

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
            VerificarSessao vs = new VerificarSessao();
            String sessao = vs.VerificarSessao(k.getLong("id"), k.getString("sessao"));
            
            if( sessao == null){
                j.put("sucesso", false);
                j.put("mensagem", "Sessao não encontrada!");
            }else{
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
    public String listarPropriedades(String dataJson) throws Exception{
        
        JSONObject j = new JSONObject();
        JSONObject k = new JSONObject(dataJson);
        
        try{
             //verifica  a sessao
            VerificarSessao vs = new VerificarSessao();
            String sessao = vs.VerificarSessao(k.getLong("id"), k.getString("sessao"));
            
            if( sessao == null){
                j.put("sucesso", false);
                j.put("mensagem", "Sessao não encontrada!");
            }else{
                //comeca a requisicao
                
            
                TOPessoa t = new TOPessoa();
                t.setIdpessoa(k.getLong("idpessoa"));
                t.setIdunidade(k.getLong("idunidade"));


                JSONArray ja = BOFactory.listar(new DAOPropriedade(), t, "listarpropriedades") ;

                if(ja.length() > 0){
                    j.put("data", ja);
                    j.put("sucesso", true);
                    j.put("sessao", sessao);
                }else{
                    j.put("sucesso", false);
                    j.put("mensagem", "Usuário não contém propriedade cadastrada!");
                    j.put("sessao", sessao);
                }
            }
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString();
    } 
}
