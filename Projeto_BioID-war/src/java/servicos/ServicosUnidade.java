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
    //metodo que insere no banco de dados
    @Path("inserir")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String inserir(
            //tabela endereco
            @FormParam("cidade_idcidade") long cidade_idcidade,
            @FormParam("rua") String rua,
            @FormParam("gps_lat") int gps_lat,
            @FormParam("gps_log") int gps_log,
            @FormParam("bairro") String bairro,
            @FormParam("complemento") String complemento,
            @FormParam("cep") String cep,
            @FormParam("numero") int numero,
            //tabela unidade
            @FormParam("nomeunidade") String nomeunidade,
            @FormParam("telefone1") String telefone1,
            @FormParam("telefone2") String telefone2,
            @FormParam("email") String email,
            @FormParam("cnpj") String cnpj,
            @FormParam("razao_social") String razao_social,
            @FormParam("nome_fanta") String nome_fanta,
            @FormParam("sessao") String sessao
            ) throws Exception{
        
                
        JSONObject j = new JSONObject();
        
        try{    
            //cria um objeto          
            TOUnidade t = new TOUnidade();
            t.setCnpj(cnpj);
            
            if(BOFactory.get(new DAOUnidade(), t)== null){
                //objeto TOEndereco
                TOEndereco te = new TOEndereco();
                te.setCidade_idcidade(cidade_idcidade);
                te.setRua(rua);
                te.setGps_lat(gps_lat);
                te.setGps_long(gps_log);
                te.setBairro(bairro);
                te.setComplemento(complemento);
                te.setCep(cep);
                te.setNumero(numero);
                //grava no banco de dados os dados da classe TOLogin e retorna o id gerado
                t.setEndereco_idendereco(BOFactory.inserir(new DAOEndereco(), te));
                t.setNomeunidade(nomeunidade);
                t.setTelefone1(telefone1);
                t.setTelefone2(telefone2);
                t.setEmail(email);
                t.setRazao_social(razao_social);
                t.setNome_fanta(nome_fanta);

                BOFactory.inserir(new DAOUnidade(), t);

                j.put("sucesso", true);
                j.put("mensagem", "Unidade cadastrada!");
            }else{
               j.put("sucesso", false);
               j.put("erro", 1);
               j.put("mensagem", "Unidade ja cadastrada!");
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
            @FormParam("sessao") String sessao
            
            ) throws Exception {
        
        JSONObject j = new JSONObject();
        
        
        try{ 
            //verificar sessao
            JSONObject js = new VerificarSessao().VerificarSessao(sessao);
            
            
            if((boolean) js.get("sucesso") == false){
                j.put("sucesso", false);
                j.put("mensangem", "Sessao não encontrada!");
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
    
    @Path("listarAgricultoresUnidade")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String listarPropriedades(
                        @FormParam("idunidade") long idunidade,
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
                
                TOUnidade t = new TOUnidade();
                t.setIdunidade(idunidade);


                JSONArray ja = BOFactory.listarAgricultoresUnidade(new DAOPessoa(), t) ;

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
