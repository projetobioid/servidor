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
import dao.DAOColheita;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONObject;
import to.TOCultivar;
import to.TOPropriedade;
import to.TOSafra;
import to.TOColheita;
import to.TODestinacao;

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

    //metodo que lista todos os produtos do banco de dados
    @Path("listar")
    @GET
    //@Produces({MediaType.APPLICATION_XML})//, MediaType.APPLICATION_JSON})
    public String listar() throws Exception {
        
        JSONObject j = new JSONObject();
        
        try{
            JSONArray ja = BOFactory.listar(new DAOCultivar());
        
            j.put("data", ja);
            j.put("sucesso", true);
        
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
            @FormParam("tempodecolheita") int tempodecolheita
            
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
            @FormParam("tipo") String tipo) throws Exception{
        
                
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
            @FormParam("id") int id)throws Exception{
        
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
            @FormParam("unidademedida_idunidademedida") long unidademedida_idunidademedida
            
            
            ) throws Exception{
        
        JSONObject j = new JSONObject();
        
        try{    
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
                    
                    ts.setUnidademedida_idunidademedida(unidademedida_idunidademedida);
                    ts.setPropriedade_idpropriedade(tpr.getIdpropriedade());
                    ts.setCultivar_idcultivar(tc.getIdcultivar());
                    ts.setSafra(safra);
                    ts.setDatareceb(datareceb);
                    ts.setQtdrecebida(qtdrecebida);

                    BOFactory.inserir(new DAOSafra(), ts);
                    
                    j.put("sucesso", true);
                    j.put("mensagem", "Distribuicao com sucesso!");
                }else{
                    j.put("sucesso", false);
                    j.put("erro", 1);
                    j.put("mensagem", "Cultivar nao encontrado!");
                }
            }else{
                j.put("sucesso", false);
                j.put("erro", 2);
                j.put("mensagem", "Propriedade e cpf nao encontrado!");
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
            @FormParam("usuario") String usuario
            ) throws Exception {
        
        JSONObject j = new JSONObject();
        
        
        try{          
            
            TOSafra t = new TOSafra();
            t.setUsuario(usuario);
            JSONArray ja = BOFactory.listar(new DAOSafra(), t);
            
            TOCultivar tc = new TOCultivar();
            tc.setUsuario(usuario);
            JSONArray jc = BOFactory.listar(new DAOCultivar(), tc);
            
            ///
            TOColheita c = new TOColheita();
            c.setSafra_idsafra(t.getIdsafra());
            JSONArray jco = BOFactory.listar(new DAOColheita(), c);
            ///
            
            
            if(ja.length() > 0){
                j.put("data", ja);
                j.put("cultivares", jc);
                j.put("colheita", jco);
                j.put("sucesso", true);
            }else{
                j.put("sucesso", false);
                j.put("mensagem", "Nenhum cultivar recebido");
            }
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString();
    }
    
     //metodo que relata os cultivares recebido
    @Path("relatar")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String relatar(
            @FormParam("safra_idsafra") long safra_idsafra,
            @FormParam("umsafra") int umsafra,
            @FormParam("datacolheita") String datacolheita,
            @FormParam("qtdcolhida") float qtdcolhida,
            
            //tabela destinacao
            @FormParam("qtddestinada") float qtddestinada,
            @FormParam("umdestinacao") int umdestinacao,
            @FormParam("datadestinacao") String datadestinacao,
            @FormParam("destino") int destino
            
            ) throws Exception {
        
        JSONObject j = new JSONObject();
        
        try{    
            //tabela safra relatada
            TOColheita ts = new TOColheita();
            
            ts.setSafra_idsafra(safra_idsafra);
            ts.setUnidademedida_idunidademedida(umsafra);
            ts.setDatacolheita(datacolheita);
            ts.setQtdcolhida(qtdcolhida);

            
            //tabela destinacao
            TODestinacao td = new TODestinacao();
            td.setSafrarelatada_idsafrarelatada(BOFactory.inserir(new DAOColheita(), ts));
            td.setSafrarelatada_safra_idsafra(safra_idsafra);
            
            td.setQtddestinada(qtddestinada);
            td.setUnidademedida_idunidademedida(umdestinacao);
            td.setDatadestinada(datadestinacao);
            td.setTipodestinacao_idtipodestinacao(destino);
            
            BOFactory.inserir(new DAODestinacao(), td);
            
            j.put("sucesso", true);
            j.put("mensagem", "Cultivar relatado!");
            
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        
        return j.toString();
    }
    
}
