package servicos;

import bo.BOFactory;
import dao.DAOCultivar;
import dao.DAOSafra;
import dao.DAOEstoque;
import fw.RedimencionarImage;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONObject;
import to.TOEstoque;

/**
 * REST Web Service
 *
 * @author Daniel
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
        List<Object> u = new ArrayList<Object>();
        
        try{       
            
            u.add(k.getLong("idcultivar"));
            JSONObject data = BOFactory.buscar(new DAOCultivar(), u, k.getString("metodo"));
            
            if(data == null){
                j.put("sucesso", false);
                j.put("mensagem", "Cultivar não encontrado");
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
    
    //metodo que lista todos os produtos do banco de dados
    @Path("listar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String listar(String dataJson) throws Exception{
        
        JSONObject j = new JSONObject();
        JSONObject k = new JSONObject(dataJson);
        List<Object> u = new ArrayList<Object>();
        
        try{
            
            JSONArray data = BOFactory.listar(new DAOCultivar(), k.getString("metodo"));

            if(data == null){
                j.put("sucesso", false);
                j.put("mensagem", "Cultivar não encontrado");
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
            
            //cria um objeto
            u.add(k.getString("nomecultivar"));
//            u.add(k.getBoolean("biofortificado"));

            //se nao existe o cultivar no sistema, pelo nome e por ser biofortificado
            if(BOFactory.buscar(new DAOCultivar(), u, "GET_NOME") == null){
                
                 //chama a classe que redimenciona a imagem antes de atribuir ao TOProduto
                u.clear();
                RedimencionarImage r = new RedimencionarImage();   
                u.add(r.redimensionaImg(k.getString("imagem")));
                u.add(k.getString("nomecultivar"));
                u.add(k.getString("descricao"));
                u.add(k.getBoolean("biofortificado"));
                u.add(k.getLong("unidademedida_idunidademedida"));
                u.add(k.getString("valornutricional"));
                u.add(k.getInt("tempodecolheita"));
                u.add(k.getInt("tempodestinacao"));
                u.add(k.getDouble("pesoSaca"));

                BOFactory.inserir(new DAOCultivar(), u);

                j.put("sucesso", true);
                j.put("mensagem", "Cultivar cadastrado com sucesso!");

            }else{
               j.put("sucesso", false);

               j.put("mensagem", "Cultivar já cadastrado!");
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
        List<Object> u = new ArrayList<Object>();
        
        try{
                
                TOEstoque te = new TOEstoque();
                
                u.add(k.getLong("idcultivar"));
                u.add(k.getLong("idunidade"));
                
                //verifica a quantidade e diminui quantidade do cultivar na unidade
                te  = (TOEstoque) BOFactory.buscarObj(new DAOEstoque(), u, k.getString("metodo"));
               
                //quantidade em precisao aredondada
                BigDecimal bd = new BigDecimal(te.getQuantidade()).setScale(2, RoundingMode.HALF_EVEN);

                //verifica se tem a quantidade menor ou igual de cultivares no estoque
                if(bd.doubleValue() >= k.getDouble("qtdrecebida")){
                    
                    u.clear();
                    
                    u.add(bd.doubleValue() - k.getDouble("qtdrecebida"));
                    u.add(k.getLong("idunidade"));
                    u.add(k.getLong("idcultivar"));
                    
                    //atualiza o estoque
                    BOFactory.editar(new DAOEstoque(), u);
                    
                    
                    
                    //cria uma tabela de entrada e saida do estoque
//                    TOIOEstoque tio = new TOIOEstoque();
//                    tio.setCultivar_idcultivar(k.getLong("idcultivar"));
//                    tio.setUnidade_idunidade(k.getLong("idunidade"));
//                    tio.setData_io(k.getString("datareceb"));
//                    // zero igual a saida
//                    tio.setOperacao(0);
//                    tio.setQuantidade(k.getDouble("qtdrecebida"));
//                    tio.setLogin_usuario(k.getString("usuario"));
//                    
//                    BOFactory.inserir(new DAOIOEstoque(), tio, k.getString("metodo"));
                            




                   //cria um objeto

    //                popula a classe para armazenar no banco de dados
                    u.add(1);
                    u.add(k.getLong("idpropriedade"));
                    u.add(k.getLong("idcultivar"));
                    u.add(k.getString("safra"));
                    u.add(k.getString("datareceb"));
                    u.add(k.getDouble("qtdrecebida"));
                    u.add(9);
                    
                    BOFactory.inserir(new DAOSafra(), u);
                    
                    

                    j.put("sucesso", true);
                    j.put("mensagem", "Distribuição com sucesso!");
//                    j.put("sessao", sessao); 
                }else{
                    j.put("sucesso", false);
//                    j.put("sessao", sessao); 
                    j.put("mensagem", "Quantidade não equivalente no estoque da unidade!");
                }
                
                   
//            }
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
