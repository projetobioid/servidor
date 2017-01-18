/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import bo.BOFactory;
import dao.DAOCultivar;
import dao.DAOPropriedade;
import dao.DAOSafra;
import dao.DAODestinacao;
import dao.DAOHistoricoColheita;
import fw.VerificarSessao;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONObject;
import to.TOCultivar;
import to.TOPropriedade;
import to.TOSafra;
import to.TOHistoricoColheita;
import to.TODestinacao;
import to.TOLogin;

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
    public String buscar(
            String dataJson
                        ) throws Exception{
        
        JSONObject j = new JSONObject();
        JSONObject k = new JSONObject(dataJson);
        
        try{               
             //verificar sessao
            JSONObject js = new VerificarSessao().VerificarSessao(k.getLong("id"), k.getString("sessao"));
            
            
            if((boolean) js.get("sucesso") == false){
                j.put("sucesso", false);
                j.put("mensagem", "Sessao não encontrada!");
            }else{
                TOCultivar p = new TOCultivar();
                p.setIdcultivar(k.getLong("idcultivar"));
                p = (TOCultivar) BOFactory.get(new DAOCultivar(), p, k.getString("metodo"));

                if(p == null){
                    j.put("sucesso", false);
                    j.put("mensagem", "Cultivar não encontrado");
                }else{
                    j.put("data", p.getJson(k.getString("metodo")));
                    j.put("sessao", js.get("sessao"));
                    j.put("sucesso", true);
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
            JSONObject js = new VerificarSessao().VerificarSessao(k.getLong("id"), k.getString("sessao"));
            
            
            if((boolean) js.get("sucesso") == false){
                j.put("sucesso", false);
                j.put("mensagem", "Sessao não encontrada!");
            }else{

                JSONArray ja = BOFactory.listar(new DAOCultivar(), k.getString("metodo"));
                
                
                if(ja.length() > 0){
                    j.put("data", ja);
                    j.put("sucesso", true);
                    j.put("sessao", js.get("sessao"));
                }else{
                    j.put("sucesso", false);
                    j.put("mensagem", "Sem "+ k.getString("metodo"));
                    j.put("sessao", js.get("sessao"));
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
            JSONObject js = new VerificarSessao().VerificarSessao(k.getLong("id"), k.getString("sessao"));
            
            
            if((boolean) js.get("sucesso") == false){
                j.put("sucesso", false);
                j.put("mensagem", "Sessao não encontrada!");
            }else{
            
            
                //cria um objeto
                TOCultivar t = new TOCultivar();
                t.setNomecultivar(k.getString("nomecultivar"));
                t.setBiofortificado(k.getBoolean("biofortificado"));

                if(BOFactory.get(new DAOCultivar(), t)== null){
                    t.setImagem(k.getString("imagem"));
                    t.setDescricao(k.getString("descricao"));
                    t.setUnidademedida_idunidademedida(k.getLong("unidademedida_idunidademedida"));
                    t.setValornutricional(k.getString("valornutricional"));
                    t.setTempodecolheita(k.getInt("tempodecolheita"));
                    t.setTempodestinacao(k.getInt("tempodestinacao"));
                    t.setPeso_saca(k.getDouble("pesoSaca"));

                    BOFactory.inserir(new DAOCultivar(), t);

                    j.put("sucesso", true);
                    j.put("mensagem", "Cultivar cadastrado com sucesso!");
                    j.put("sessao", js.get("sessao"));
                }else{
                   j.put("sucesso", false);
                   j.put("mensagem", "Cultivar já cadastrado!");
                }
            
            
            }
            
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString(); 
    }
    
    //metodo que insere no banco de dados
    @Path("editar")
    @POST
    public String editar(
            @FormParam("id") int id,
            @FormParam("nome") String nome,
            @FormParam("descricao") String descricao,
            @FormParam("biofortificado") boolean biofortificado,
            @FormParam("tipo") String tipo,
            @FormParam("sessao") String sessao
            ) throws Exception{
        
                
        JSONObject j = new JSONObject();
        
        try{    
            
            TOCultivar t = new TOCultivar();
           // t.setIdCultivar(id);
            
            t = (TOCultivar) BOFactory.get(new DAOCultivar(), t);
            
            if(t == null){
                j.put("sucesso", false);
                j.put("mensagem", "Produto não encontrado");
            }else{
                t.setNomecultivar(nome);
                t.setDescricao(descricao);
                t.setBiofortificado(biofortificado);
                //t.setTipo(tipo); 
                
                BOFactory.editar(new DAOCultivar(), t);
                j.put("sucesso", true); 
            }
           
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString(); 
    }
    
    

    @Path("excluir")
    @POST
    public String excluir(
                        @FormParam("id") int id,
                        @FormParam("sessao") String sessao
                        ) throws Exception{
        
        JSONObject j = new JSONObject();
        
        try{               
            
            TOCultivar p = new TOCultivar();
            //p.setIdCultivar(id);
            
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
    
    @Path("distribuir")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String distribuir(
            @FormParam("cpf") String cpf,
            @FormParam("nomecultivar") String nomecultivar,
            @FormParam("biofortificado") boolean biofortificado,
            @FormParam("nomepropriedade") String nomepropriedade,
            @FormParam("safra") String safra,
            @FormParam("datareceb") String datareceb,
            @FormParam("qtdrecebida") float qtdrecebida,
            @FormParam("unidademedida_idunidademedida") long unidademedida_idunidademedida,
            @FormParam("id") long id,
            @FormParam("sessao") String sessao
            ) throws Exception{
        
        JSONObject j = new JSONObject();
        
        try{ 
            //verificar sessao
            JSONObject js = new VerificarSessao().VerificarSessao(id, sessao);
            
            
            if((boolean) js.get("sucesso") == false){
                j.put("sucesso", false);
                j.put("mensagem", "Sessao não encontrada!");
            }else{
                //cria um objeto
                TOCultivar tc = new TOCultivar();
                TOPropriedade tpr = new TOPropriedade();


                tc.setNomecultivar(nomecultivar);
                tc.setBiofortificado(biofortificado);
                tpr.setNomepropriedade(nomepropriedade);
                tpr.setCpf(cpf);


                tc = (TOCultivar) BOFactory.get(new DAOCultivar(), tc);
                tpr = (TOPropriedade) BOFactory.get(new DAOPropriedade(), tpr);


                if(tpr != null){
                    if(tc != null){
                        TOSafra ts = new TOSafra();
                        ts.setStatussafra_idstatussafra(1);
                        ts.setUnidademedida_idunidademedida(unidademedida_idunidademedida);
                        ts.setPropriedade_idpropriedade(tpr.getIdpropriedade());
                        ts.setCultivar_idcultivar(tc.getIdcultivar());
                        ts.setSafra(safra);
                        ts.setDatareceb(datareceb);
                        ts.setQtdrecebida(qtdrecebida);

                        BOFactory.inserir(new DAOSafra(), ts);

                        j.put("sucesso", true);
                        j.put("mensagem", "Distribuicao com sucesso!");
                        j.put("sessao", js.get("sessao"));
                    }else{
                        j.put("sucesso", false);
                        j.put("erro", 1);
                        j.put("mensagem", "Cultivar nao encontrado!");
                        j.put("sessao", js.get("sessao"));
                    }
                }else{
                    j.put("sucesso", false);
                    j.put("erro", 2);
                    j.put("mensagem", "Propriedade e cpf nao encontrado!");
                    j.put("sessao", js.get("sessao"));
                }
            }
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString(); 
    }
    
    //metodo que lista todos os cultivares recebido
    @Path("listarrecebidos")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String listarrecebido(
                        String dataJson
                        ) throws Exception{
        
        JSONObject j = new JSONObject();
        
        JSONObject k = new JSONObject(dataJson);
        
         
        
        try{ 
            //verificar sessao
            JSONObject js = new VerificarSessao().VerificarSessao(k.getLong("id"), k.getString("sessao"));
            
            
            if((boolean) js.get("sucesso") == false){
                j.put("sucesso", false);
                j.put("mensagem", "Sessao não encontrada!");
            }else{
                //lista os cultivares recebidos
                TOLogin t = new TOLogin();
                t.setPessoa_idpessoa(k.getLong("idpessoa"));

                //lista os cultivares recebidos
                JSONArray ja = BOFactory.listar(new DAOSafra(), t);           


                //lista dados dos cultivares
                JSONArray jc = BOFactory.listar(new DAOCultivar(), t);



                //lista as perguntas da propriedade
                //JSONArray jper = BOFactory.listar(new DAOPerguntas(), t);

                if(ja.length() > 0){
                    j.put("sucesso", true);
                    j.put("cultivaresrecebidos", ja);
                    j.put("cultivares", jc);
                    j.put("sessao", js.get("sessao"));

                }else{
                    j.put("sucesso", false);
                    j.put("mensagem", "Nenhum cultivar recebido");
                    j.put("sessao", js.get("sessao"));
                }
            }
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString();
    }
    
    //metodo que relata a colheita da safra
    @Path("relatarcolheita")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String relatarcolheita(
            @FormParam("idsafra") long idsafra,
            @FormParam("ultimadatacolheita") String ultimadatacolheita,
            @FormParam("qtdcolhida") float qtdcolhida,
            @FormParam("statussafra_idstatussafra") long statussafra_idstatussafra,
            @FormParam("sessao") String sessao
            ) throws Exception{
        
        JSONObject j = new JSONObject();
        
        try{
            //cria um historico da nova colheita
            TOHistoricoColheita th = new TOHistoricoColheita();
            th.setSafra_idsafra(idsafra);
            th.setDatacolheita(ultimadatacolheita);
            th.setQtdcolhida(qtdcolhida);
            BOFactory.inserir(new DAOHistoricoColheita(), th);
            
            //tras o valor da soma das colheitas
            th = (TOHistoricoColheita) BOFactory.get(new DAOHistoricoColheita(), th);
            
            //atualiza a tabela safra
            TOSafra ts = new TOSafra();
            ts.setIdsafra(idsafra);
            ts.setUltimadatacolheita(ultimadatacolheita);
            ts.setQtdcolhida(th.getSomaqtdcolhida());
            ts.setStatussafra_idstatussafra(statussafra_idstatussafra);
            BOFactory.editar(new DAOSafra(), ts);
            
            j.put("sucesso", true);
            j.put("mensagem", "Colheita relatada!");
            
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        
        return j.toString();
    }
    
    //metodo que relata a colheita da safra
    @Path("relatardestinacao")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String relatardestinacao(
            @FormParam("idsafra") long idsafra,
            @FormParam("tipodestinacao_idtipodestinacao") long tipodestinacao_idtipodestinacao,
            @FormParam("datadestinada") String datadestinada,
            @FormParam("qtddestinada") float qtddestinada,
            @FormParam("statussafra_idstatussafra") long statussafra_idstatussafra,
            @FormParam("sessao") String sessao
            ) throws Exception{
        
        JSONObject j = new JSONObject();
        
        try{
            //cria um historico da nova colheita
            TODestinacao td = new TODestinacao();
            td.setSafra_idsafra(idsafra);
            td.setTipodestinacao_idtipodestinacao(tipodestinacao_idtipodestinacao);
            td.setDatadestinada(datadestinada);
            td.setQtddestinada(qtddestinada);
            
            BOFactory.inserir(new DAODestinacao(), td);
            
            //atualiza o status da safra
            TOSafra ts = new TOSafra();
            
            ts.setIdsafra(idsafra);
            //tras o valor da soma das colheitas
            ts = (TOSafra) BOFactory.get(new DAOSafra(), ts);
            
            ts.setStatussafra_idstatussafra(statussafra_idstatussafra);
            BOFactory.editar(new DAOSafra(), ts);
            
            j.put("sucesso", true);
            j.put("mensagem", "Destinacao relatada!");
            
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        
        return j.toString();
    }

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
    
    //metodo listar cultivares para relatar da propriedade 
    @Path("backupentrevista")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String backupentrevista(
                        @FormParam("idpropriedade") long idpropriedade,
                        @FormParam("sessao") String sessao
                        ) throws Exception{
        
        JSONObject j = new JSONObject();
        
        
        try{          
            //lista os cultivares recebidos
            //TOLogin t = new TOLogin();
            //t.setPessoa_idpessoa(idpropriedade);
            TOSafra t = new TOSafra();
            t.setPropriedade_idpropriedade(idpropriedade);
            
            //lista as propriedades
            //JSONArray jp = BOFactory.listar(new DAOPropriedade(), t);
            
            //lista os cultivares recebidos
            JSONArray ja = BOFactory.listar(new DAOSafra(), t);
            
            
       
            if(ja.length() > 0){
                j.put("sucesso", true);
                j.put("cultivaresarelatar", ja);
            }else{

                j.put("sucesso", false);
                j.put("mensagem", "Nenhuma cultivar para relatar!"); 
            }

        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString();
    }
    
}
