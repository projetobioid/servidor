/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import bo.BOFactory;
import dao.DAOEndereco;
import dao.DAOEstoque;
import dao.DAOIOEstoque;
import dao.DAOPessoa;
import dao.DAOUnidade;
import fw.VerificarSessao;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONObject;
import to.TOEndereco;
import to.TOEstoque;
import to.TOIOEstoque;
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
                TOUnidade p = new TOUnidade();
                p.setIdunidade(k.getLong("idunidade"));
                p = (TOUnidade) BOFactory.get(new DAOUnidade(), p, k.getString("metodo"));

                if(p == null){
                    j.put("sucesso", false);
                    j.put("mensagem", "Unidade não encontrada");
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
                TOUnidade t = new TOUnidade();
                t.setCnpj(k.getString("cnpj"));

                if(BOFactory.get(new DAOUnidade(), t)== null){
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
                    t.setEndereco_idendereco(BOFactory.inserir(new DAOEndereco(), te));
                    t.setNomeunidade(k.getString("nomeunidade"));
                    t.setTelefone1(k.getString("telefone1"));
                    t.setTelefone2(k.getString("telefone2"));
                    t.setEmail(k.getString("email"));
                    t.setRazao_social(k.getString("razao_social"));
                    t.setNome_fanta(k.getString("nome_fanta"));

                    BOFactory.inserir(new DAOUnidade(), t);

                    j.put("sucesso", true);
                    j.put("mensagem", "Unidade cadastrada!");
                    j.put("sessao", js.get("sessao"));
                }else{
                   j.put("sucesso", false);
                   //j.put("erro", 1);
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
    public String listar(
            String dadosJson
            ) throws Exception{
        
        JSONObject j = new JSONObject();
        JSONObject k = new JSONObject(dadosJson);
        
        try{
            
            //verificar sessao
            JSONObject js = new VerificarSessao().VerificarSessao(k.getLong("id"), k.getString("sessao"));
            
            
            if((boolean) js.get("sucesso") == false){
                j.put("sucesso", false);
                j.put("mensagem", "Sessao não encontrada!");
            }else{
//                TOUnidade t = new TOUnidade();
//                t.setIdunidade(idunidade);
                JSONArray ja = BOFactory.listar(new DAOUnidade());
        
                j.put("data", ja);
                j.put("sessao", js.get("sessao"));
                j.put("sucesso", true);
                
            }
            
        
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString();
    }
    
    //inseri dados no estoque
    @Path("ioestoque")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String editarEstoque(
            //tabela endereco
            @FormParam("idunidade") long idunidade,
            @FormParam("idcultivar") long idcultivar,
            @FormParam("um") long um,
            @FormParam("qtd") float qtd,
            @FormParam("data_io") String data_io,
            @FormParam("operacao") int operacao,
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
                TOEstoque te = new TOEstoque();

                te.setUnidade_idunidade(idunidade);
                te.setCultivar_idcultivar(idcultivar);
                te.setUnidademedida_idunidademedida(um);
                te.setQuantidade(qtd);

                TOEstoque t = new TOEstoque();

                 t = (TOEstoque) BOFactory.get(new DAOEstoque(), te);

                //se nao existe, cria uma nova tabela
                if(t == null){

                    BOFactory.inserir(new DAOEstoque(), te);

                //update na tabela estoque
                }else{
                    //operacao de entrada de estoque
                    if(operacao == 1){
                        te.setQuantidade(t.getQuantidade() + qtd);
                    //operacao de saida de estoque    
                    }else{
                        te.setQuantidade(t.getQuantidade() - qtd);
                    }

                    BOFactory.editar(new DAOEstoque(), te);

                    j.put("sucesso", true);
                    j.put("mensagem", "Estoque atualizado!");
                    j.put("sessao", js.get("sessao"));
                }

                //cria um historico de io do estoque
                TOIOEstoque tio = new TOIOEstoque();
                tio.setUnidade_idunidade(idunidade);
                tio.setCultivar_idcultivar(idcultivar);
                tio.setUnidademedida_idunidademedida(um);
                tio.setQuantidade(qtd);
                tio.setData_io(data_io);
                tio.setOperacao(operacao);

                BOFactory.inserir(new DAOIOEstoque(), tio);
            }
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("erro", "erro atualização estoque da unidade!");
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString(); 
    }
    
        //metodo que lista todos os cultivares recebido
    @Path("listarestoque")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String listarestoque(
            @FormParam("idunidade") long idunidade,
            @FormParam("id") long id,
            @FormParam("sessao") String sessao
            
            ) throws Exception {
        
        JSONObject j = new JSONObject();
        
        
        try{ 
            //verificar sessao
            JSONObject js = new VerificarSessao().VerificarSessao(id, sessao);
            
            
            if((boolean) js.get("sucesso") == false){
                j.put("sucesso", false);
                j.put("mensagem", "Sessao não encontrada!");
            }else{
                
                //lista os cultivares recebidos
                TOEstoque t = new TOEstoque();
                t.setUnidade_idunidade(idunidade);
                //lista do estoque
                JSONArray ja = BOFactory.listar(new DAOEstoque(), t);

                if(ja.length() > 0){
                    j.put("sucesso", true);
                    j.put("estoque", ja);
                    j.put("sessao", js.get("sessao"));
                    

                }else{
                    j.put("sucesso", false);
                    j.put("mensagem", "Estoque vazio!");
                    j.put("sessao", js.get("sessao"));
                }
            }
            
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString();
    }
    
    @Path("listaragricultoresunidade")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String listarPropriedades(
                        @FormParam("idunidade") long idunidade,
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
                
                TOUnidade t = new TOUnidade();
                t.setIdunidade(idunidade);


                JSONArray ja = BOFactory.listar(new DAOPessoa(), t) ;

                if(ja.length() > 0){
                    j.put("listaAgricultores", ja);
                    j.put("sucesso", true);
                    j.put("sessao", js.get("sessao"));
                }else{
                    j.put("sucesso", false);
                    j.put("mensagem", "Sem agricultores na unidade");
                    j.put("sessao", js.get("sessao"));
                }
            }
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString();
    } 
}
