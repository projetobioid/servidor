/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import bo.BOFactory;
import dao.DAOCultivar;
import dao.DAOSafra;
import dao.DAOEstoque;
import dao.DAOIOEstoque;
import dao.DAOSessao;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.security.SecureRandom;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONObject;
import to.TOCultivar;
import to.TOSafra;
import to.TOEstoque;
import to.TOIOEstoque;
import to.TOSessao;

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

    @Path("buscar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String buscar(String dataJson) throws Exception{
        
        JSONObject j = new JSONObject();
        JSONObject k = new JSONObject(dataJson);
        
        try{               
            //verificar sessao
            TOSessao ts = new TOSessao();
            ts.setLogin_idlogin(k.getLong("id"));
            ts.setSessao(k.getString("sessao"));
            
            ts = (TOSessao) BOFactory.get(new DAOSessao(), ts, "get_sessao");
            
            
            if(ts == null){
                j.put("sucesso", false);
                j.put("mensagem", "Sessao não encontrada!");
            }else{
                SecureRandom random = new SecureRandom();
                String novaSessao = new BigInteger(130, random).toString(32);
                ts.setSessao(novaSessao);
                BOFactory.editar(new DAOSessao(), ts, "update_sessao");  
                
                //comeca a requisicao
                switch(k.getString("metodo")){
                    case "get_cultivar":
                        TOCultivar p = new TOCultivar();
                        p.setIdcultivar(k.getLong("idcultivar"));
                        p = (TOCultivar) BOFactory.get(new DAOCultivar(), p, k.getString("metodo"));
                        if(p == null){
                            j.put("sucesso", false);
                            j.put("sessao", novaSessao);
                            j.put("mensagem", "Cultivar não encontrado");
                        }else{
                            j.put("data", p.getJson(k.getString("metodo")));
                            j.put("sessao", novaSessao);
                            j.put("sucesso", true);
                        }
                    
                        break;
                    case "get_cultivar_estoque":
                        TOEstoque te = new TOEstoque();
                        te.setCultivar_idcultivar(k.getLong("idcultivar"));
                        te.setUnidade_idunidade(k.getLong("idunidade"));
                        te = (TOEstoque) BOFactory.get(new DAOEstoque(), te, k.getString("metodo"));
                        
                        if(te == null){
                            j.put("sucesso", false);
                            j.put("sessao", novaSessao);
                            j.put("mensagem", "Cultivar no estoque não encontrado!");
                        }else{
                            j.put("data", te.getJson(k.getString("metodo")));
                            j.put("sessao", novaSessao);
                            j.put("sucesso", true);
                        }

                        break;
                }
                
            }
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString(); 
        
    }
    
    //metodo que lista todos os produtos do banco de dados
    @Path("listar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String listar(
            String dataJson
            
            ) throws Exception{
        
        JSONObject j = new JSONObject();
        JSONObject k = new JSONObject(dataJson);
        
        try{
            
            //verificar sessao
            TOSessao ts = new TOSessao();
            ts.setLogin_idlogin(k.getLong("id"));
            ts.setSessao(k.getString("sessao"));
            
            ts = (TOSessao) BOFactory.get(new DAOSessao(), ts, "get_sessao");
            
            
            if(ts == null){
                j.put("sucesso", false);
                j.put("mensagem", "Sessao não encontrada!");
            }else{
                SecureRandom random = new SecureRandom();
                String novaSessao = new BigInteger(130, random).toString(32);
                ts.setSessao(novaSessao);
                BOFactory.editar(new DAOSessao(), ts, "update_sessao");  
                
                //comeca a requisicao
                JSONArray ja = null;
                
                switch (k.getString("metodo")) {
                    case "bio":
                        ja = BOFactory.listar(new DAOCultivar(), null, k.getString("metodo"));
                    break;
                    
                    case "naobio":
//                        ja = BOFactory.listar(new DAOCultivar(), k.getString("metodo"));
                    break;
                    
                    case "recebidos":
//                        ja = BOFactory.listar(new DAOCultivar(), k.getString("metodo"));
                    break;
                    
                    case "estoqueunidade":
                    case "estoqueunidade_select":
                        TOEstoque t = new TOEstoque();
                        t.setUnidade_idunidade(k.getLong("idunidade"));
                        //lista os cultivares do estoque
                        ja = BOFactory.listar(new DAOEstoque(), t, k.getString("metodo") );
                        break;
                    default:
                        break;
                }
                
                
                
                
                if(ja.length() > 0){
                    j.put("data", ja);
                    j.put("sucesso", true);
                    j.put("sessao", novaSessao);
                }else{
                    j.put("sucesso", false);
                    j.put("mensagem", "Sem "+ k.getString("metodo"));
                    j.put("sessao", novaSessao);
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
    public String inserir(
            String dataJson
            ) throws Exception{
        
       
                
        JSONObject j = new JSONObject();
        JSONObject k = new JSONObject(dataJson);
        
        try{
            
            //verificar sessao
            TOSessao ts = new TOSessao();
            ts.setLogin_idlogin(k.getLong("id"));
            ts.setSessao(k.getString("sessao"));
            
            ts = (TOSessao) BOFactory.get(new DAOSessao(), ts, "get_sessao");
            
            
            if(ts == null){
                j.put("sucesso", false);
                j.put("mensagem", "Sessao não encontrada!");
            }else{
                SecureRandom random = new SecureRandom();
                String novaSessao = new BigInteger(130, random).toString(32);
                ts.setSessao(novaSessao);
                BOFactory.editar(new DAOSessao(), ts, "update_sessao");  
                
                //comeca a requisicao
            
            
                //cria um objeto
                TOCultivar t = new TOCultivar();
                t.setNomecultivar(k.getString("nomecultivar"));
                t.setBiofortificado(k.getBoolean("biofortificado"));

                //se nao existe o cultivar no sistema, pelo nome e por ser biofortificado
                if(BOFactory.get(new DAOCultivar(), t, null) == null){
                    t.setImagem(k.getString("imagem"));
                    t.setDescricao(k.getString("descricao"));
                    t.setUnidademedida_idunidademedida(k.getLong("unidademedida_idunidademedida"));
                    t.setValornutricional(k.getString("valornutricional"));
                    t.setTempodecolheita(k.getInt("tempodecolheita"));
                    t.setTempodestinacao(k.getInt("tempodestinacao"));
                    t.setPeso_saca(k.getDouble("pesoSaca"));

                    BOFactory.inserir(new DAOCultivar(), t, k.getString("metodo"));

                    j.put("sucesso", true);
                    j.put("mensagem", "Cultivar cadastrado com sucesso!");
                    j.put("sessao", novaSessao);
                }else{
                   j.put("sucesso", false);
                   j.put("sessao", novaSessao);
                   j.put("mensagem", "Cultivar já cadastrado!");
                }
            
            
            }
            
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString(); 
    }
    
//    //metodo que insere no banco de dados
//    @Path("editar")
//    @POST
//    public String editar(
//            @FormParam("id") int id,
//            @FormParam("nome") String nome,
//            @FormParam("descricao") String descricao,
//            @FormParam("biofortificado") boolean biofortificado,
//            @FormParam("tipo") String tipo,
//            @FormParam("sessao") String sessao
//            ) throws Exception{
//        
//                
//        JSONObject j = new JSONObject();
//        
//        try{    
//            
//            TOCultivar t = new TOCultivar();
//           // t.setIdCultivar(id);
//            
//            t = (TOCultivar) BOFactory.get(new DAOCultivar(), t,);
//            
//            if(t == null){
//                j.put("sucesso", false);
//                j.put("mensagem", "Produto não encontrado");
//            }else{
//                t.setNomecultivar(nome);
//                t.setDescricao(descricao);
//                t.setBiofortificado(biofortificado);
//                //t.setTipo(tipo); 
//                
//                BOFactory.editar(new DAOCultivar(), t);
//                j.put("sucesso", true); 
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
//    
//
//    @Path("excluir")
//    @POST
//    public String excluir(
//                        @FormParam("id") int id,
//                        @FormParam("sessao") String sessao
//                        ) throws Exception{
//        
//        JSONObject j = new JSONObject();
//        
//        try{               
//            
//            TOCultivar p = new TOCultivar();
//            //p.setIdCultivar(id);
//            
//            p = (TOCultivar) BOFactory.get(new DAOCultivar(), p);
//            
//            if(p == null){
//                j.put("sucesso", false);
//                j.put("mensagem", "Produto não encontrado");
//            }else{
//                BOFactory.excluir(new DAOCultivar(), p);
//                j.put("sucesso", true);
//            }
//        }catch(Exception e){
//            j.put("sucesso", false);
//            j.put("mensagem", e.getMessage());
//        }
//        
//        return j.toString(); 
//        
//    }
    
    @Path("distribuir")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String distribuir(String dataJson) throws Exception{
        
        JSONObject j = new JSONObject();
        JSONObject k = new JSONObject(dataJson);
        
        try{
            
            //verificar sessao
            TOSessao ts = new TOSessao();
            ts.setLogin_idlogin(k.getLong("id"));
            ts.setSessao(k.getString("sessao"));
            
            ts = (TOSessao) BOFactory.get(new DAOSessao(), ts, "get_sessao");
            
            
            if(ts == null){
                j.put("sucesso", false);
                j.put("mensagem", "Sessao não encontrada!");
            }else{
                SecureRandom random = new SecureRandom();
                String novaSessao = new BigInteger(130, random).toString(32);
                ts.setSessao(novaSessao);
                BOFactory.editar(new DAOSessao(), ts, "update_sessao");  
                
                //comeca a requisicao
                
                TOEstoque te = new TOEstoque();
                
                te.setUnidade_idunidade(k.getLong("idunidade"));
                te.setCultivar_idcultivar(k.getLong("idcultivar"));
                
                //verifica a quantidade e diminui quantidade do cultivar na unidade
                te  = (TOEstoque) BOFactory.get(new DAOEstoque(), te, k.getString("metodo"));
               
                //quantidade em precisao aredondada
                BigDecimal bd = new BigDecimal(te.getQuantidade()).setScale(2, RoundingMode.HALF_EVEN);

                //verifica se tem a quantidade menor ou igual de cultivares no estoque
                if(bd.doubleValue() >= k.getDouble("qtdrecebida")){
                    
                    te.setUnidade_idunidade(k.getLong("idunidade"));
                    te.setCultivar_idcultivar(k.getLong("idcultivar"));
                    te.setQuantidade(bd.doubleValue() - k.getDouble("qtdrecebida"));
                    //atualiza o estoque
                    BOFactory.editar(new DAOEstoque(), te, k.getString("metodo"));
                    //cria uma tabela de entrada e saida do estoque
                    TOIOEstoque tio = new TOIOEstoque();
                    tio.setCultivar_idcultivar(k.getLong("idcultivar"));
                    tio.setUnidade_idunidade(k.getLong("idunidade"));
                    tio.setData_io(k.getString("datareceb"));
                    // zero igual a saida
                    tio.setOperacao(0);
                    tio.setQuantidade(k.getDouble("qtdrecebida"));
                    tio.setLogin_idlogin(k.getLong("id"));
                    
                    BOFactory.inserir(new DAOIOEstoque(), tio, k.getString("metodo"));
                            
                   //cria um objeto
                    TOSafra tsf = new TOSafra();

    //                popula a classe para armazenar no banco de dados
                    tsf.setStatussafra_idstatussafra(1);
                    tsf.setPropriedade_idpropriedade(k.getLong("idpropriedade"));
                    tsf.setCultivar_idcultivar(k.getLong("idcultivar"));
                    tsf.setSafra(k.getString("safra"));
                    tsf.setDatareceb(k.getString("datareceb"));
                    tsf.setQtdrecebida(k.getDouble("qtdrecebida"));
                    tsf.setStatus_entrevistador(9);



                    BOFactory.inserir(new DAOSafra(), tsf, k.getString("metodo"));
                    
                    

                    j.put("sucesso", true);
                    j.put("mensagem", "Distribuição com sucesso!");
                    j.put("sessao", novaSessao); 
                }else{
                    j.put("sucesso", false);
                    j.put("sessao", novaSessao); 
                    j.put("mensagem", "Quantidade não equivalente no estoque da unidade!");
                }
                
                   
            }
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString(); 
    }
    
//    //metodo que lista todos os cultivares recebido
//    @Path("listarrecebidos")
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public String listarrecebido(
//                        String dataJson
//                        ) throws Exception{
//        
//        JSONObject j = new JSONObject();
//        
//        JSONObject k = new JSONObject(dataJson);
//        
//         
//        
//        try{ 
//            //verificar sessao
//            JSONObject js = new VerificarSessao().VerificarSessao(k.getLong("id"), k.getString("sessao"));
//            
//            
//            if((boolean) js.get("sucesso") == false){
//                j.put("sucesso", false);
//                j.put("mensagem", "Sessao não encontrada!");
//            }else{
//                //lista os cultivares recebidos
//                TOLogin t = new TOLogin();
//                t.setPessoa_idpessoa(k.getLong("idpessoa"));
//
//                //lista os cultivares recebidos
//                JSONArray ja = BOFactory.listar(new DAOSafra(), t);           
//
//
//                //lista dados dos cultivares
//                JSONArray jc = BOFactory.listar(new DAOCultivar(), t);
//
//
//
//                //lista as perguntas da propriedade
//                //JSONArray jper = BOFactory.listar(new DAOPerguntas(), t);
//
//                if(ja.length() > 0){
//                    j.put("sucesso", true);
//                    j.put("cultivaresrecebidos", ja);
//                    j.put("cultivares", jc);
//                    j.put("sessao", js.get("sessao"));
//
//                }else{
//                    j.put("sucesso", false);
//                    j.put("mensagem", "Nenhum cultivar recebido");
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
    
//    //metodo que relata a colheita da safra
//    @Path("relatarcolheita")
//    @POST
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    @Produces(MediaType.APPLICATION_JSON)
//    public String relatarcolheita(
//            @FormParam("idsafra") long idsafra,
//            @FormParam("ultimadatacolheita") String ultimadatacolheita,
//            @FormParam("qtdcolhida") float qtdcolhida,
//            @FormParam("statussafra_idstatussafra") long statussafra_idstatussafra,
//            @FormParam("sessao") String sessao
//            ) throws Exception{
//        
//        JSONObject j = new JSONObject();
//        
//        try{
//            //cria um historico da nova colheita
//            TOHistoricoColheita th = new TOHistoricoColheita();
//            th.setSafra_idsafra(idsafra);
//            th.setDatacolheita(ultimadatacolheita);
//            th.setQtdcolhida(qtdcolhida);
//            BOFactory.inserir(new DAOHistoricoColheita(), th);
//            
//            //tras o valor da soma das colheitas
//            th = (TOHistoricoColheita) BOFactory.get(new DAOHistoricoColheita(), th);
//            
//            //atualiza a tabela safra
//            TOSafra ts = new TOSafra();
//            ts.setIdsafra(idsafra);
//            ts.setUltimadatacolheita(ultimadatacolheita);
//            ts.setQtdcolhida(th.getSomaqtdcolhida());
//            ts.setStatussafra_idstatussafra(statussafra_idstatussafra);
//            BOFactory.editar(new DAOSafra(), ts);
//            
//            j.put("sucesso", true);
//            j.put("mensagem", "Colheita relatada!");
//            
//        }catch(Exception e){
//            j.put("sucesso", false);
//            j.put("mensagem", e.getMessage());
//        }
//        
//        
//        return j.toString();
//    }
//    
//    //metodo que relata a colheita da safra
//    @Path("relatardestinacao")
//    @POST
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    @Produces(MediaType.APPLICATION_JSON)
//    public String relatardestinacao(
//            @FormParam("idsafra") long idsafra,
//            @FormParam("tipodestinacao_idtipodestinacao") long tipodestinacao_idtipodestinacao,
//            @FormParam("datadestinada") String datadestinada,
//            @FormParam("qtddestinada") float qtddestinada,
//            @FormParam("statussafra_idstatussafra") long statussafra_idstatussafra,
//            @FormParam("sessao") String sessao
//            ) throws Exception{
//        
//        JSONObject j = new JSONObject();
//        
//        try{
//            //cria um historico da nova colheita
//            TODestinacao td = new TODestinacao();
//            td.setSafra_idsafra(idsafra);
//            td.setTipodestinacao_idtipodestinacao(tipodestinacao_idtipodestinacao);
//            td.setDatadestinada(datadestinada);
//            td.setQtddestinada(qtddestinada);
//            
//            BOFactory.inserir(new DAODestinacao(), td);
//            
//            //atualiza o status da safra
//            TOSafra ts = new TOSafra();
//            
//            ts.setIdsafra(idsafra);
//            //tras o valor da soma das colheitas
//            ts = (TOSafra) BOFactory.get(new DAOSafra(), ts);
//            
//            ts.setStatussafra_idstatussafra(statussafra_idstatussafra);
//            BOFactory.editar(new DAOSafra(), ts);
//            
//            j.put("sucesso", true);
//            j.put("mensagem", "Destinacao relatada!");
//            
//        }catch(Exception e){
//            j.put("sucesso", false);
//            j.put("mensagem", e.getMessage());
//        }
//        
//        
//        return j.toString();
//    }

   /* private JSONArray verificasSafra(JSONArray ja) throws JSONException {
                        
        JSONArray js = new JSONArray();

        js.put(ja.getJSONObject(0).getString("safra"));
        for(int i = 1; i < ja.length(); i++){
            if(!js.get(js.length()-1).equals(ja.getJSONObject(i).getString("safra"))){
                js.put(ja.getJSONObject(i).getString("safra"));
            }

        }
        return js;
    }*/
    
    
    
}
