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
    public String buscar(
                        @FormParam("nomecultivar") String nomecultivar,
                        @FormParam("biofortificado") boolean biofortificado,
                        @FormParam("sessao") String sessao
                        ) throws Exception{
        
        JSONObject j = new JSONObject();
        
        try{               
             //verificar sessao
            JSONObject js = new VerificarSessao().VerificarSessao(sessao);
            
            
            if((boolean) js.get("sucesso") == false){
                j.put("sucesso", false);
                j.put("mensangem", "Sessao não encontrada!");
            }else{
                TOCultivar p = new TOCultivar();
                p.setNomecultivar(nomecultivar);
                p.setBiofortificado(biofortificado);
                p = (TOCultivar) BOFactory.get(new DAOCultivar(), p);

                if(p == null){
                    j.put("sucesso", false);
                    j.put("mensagem", "Cultivar não encontrado");
                }else{
                    j.put("cultivar", p.getJson());
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
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String listar(
            @FormParam("sessao") String sessao
            ) throws Exception{
        
        JSONObject j = new JSONObject();
        
        try{
            
            //verificar sessao
            JSONObject js = new VerificarSessao().VerificarSessao(sessao);
            
            
            if((boolean) js.get("sucesso") == false){
                j.put("sucesso", false);
                j.put("mensangem", "Sessao não encontrada!");
            }else{
//                TOUnidade t = new TOUnidade();
//                t.setIdunidade(idunidade);
                JSONArray ja = BOFactory.listar(new DAOCultivar());
        
                j.put("cultivares", ja);
                j.put("sessao", js.get("sessao"));
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
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String inserir(
            @FormParam("nomecultivar") String nomecultivar,
            @FormParam("imagem") String imagem,
            @FormParam("descricao") String descricao,
            @FormParam("biofortificado") boolean biofortificado,
            @FormParam("unidademedida_idunidademedida") long unidademedida_idunidademedida,
            @FormParam("valornutricional") String valornutricional,
            @FormParam("tempodecolheita") int tempodecolheita,
            @FormParam("sessao") String sessao
            ) throws Exception{
        
                
        JSONObject j = new JSONObject();
        
        try{    
            //cria um objeto
            TOCultivar t = new TOCultivar();
            t.setNomecultivar(nomecultivar);
            t.setBiofortificado(biofortificado);
            
            if(BOFactory.get(new DAOCultivar(), t)== null){
                t.setImagem(imagem);
                t.setDescricao(descricao);
                t.setUnidademedida_idunidademedida(unidademedida_idunidademedida);
                t.setValornutricional(valornutricional);
                t.setTempodecolheita(tempodecolheita);

                BOFactory.inserir(new DAOCultivar(), t);

                j.put("sucesso", true);
                j.put("mensagem", "Cultivar cadastrado!");
            }else{
               j.put("sucesso", false);
               j.put("erro", 1);
               j.put("mensagem", "Cultivar ja cadastrado!");
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
            @FormParam("sessao") String sessao
            ) throws Exception{
        
        JSONObject j = new JSONObject();
        
        try{ 
            //verificar sessao
            JSONObject js = new VerificarSessao().VerificarSessao(sessao);
            
            
            if((boolean) js.get("sucesso") == false){
                j.put("sucesso", false);
                j.put("mensangem", "Sessao não encontrada!");
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
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String listarrecebido(
                        @FormParam("idpessoa") long idpessoa,
                        @FormParam("sessao") String sessao
                        ) throws Exception{
        
        JSONObject j = new JSONObject();
        
        
        try{ 
            //verificar sessao
            JSONObject js = new VerificarSessao().VerificarSessao(sessao);
            
            
            if((boolean) js.get("sucesso") == false){
                j.put("sucesso", false);
                j.put("mensangem", "Sessao não encontrada!");
            }else{
                //lista os cultivares recebidos
                TOLogin t = new TOLogin();
                t.setPessoa_idpessoa(idpessoa);

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
            JSONArray ja = BOFactory.backupentrevista(new DAOSafra(), t);
            
            
       
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
