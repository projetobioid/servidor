/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import bo.BOFactory;
import dao.DAOEndereco;
import dao.DAOGrupos;
import dao.DAOLogin;
import dao.DAOPessoa;
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
import to.TOEndereco;
import to.TOGrupos;
import to.TOLogin;
import to.TOPessoa;

/**
 * REST Web Service
 *
 * @author daniel
 */
@Path("usuario")
public class ServicoUsuario {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServicoUsuario
     */
    public ServicoUsuario() {
    }

    @Path("listar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String listar(String dataJson) throws Exception{
        
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
                
                TOPessoa to = new TOPessoa();
                
           
                to.setIdunidade(k.getLong("idunidade"));
           
                
                JSONArray ja = BOFactory.listar(new DAOPessoa(),to , k.getString("metodo")) ;

                if(ja.length() > 0){
                    j.put("data", ja);
                    j.put("sucesso", true);
//                    j.put("sessao", sessao);
                }else{
                    j.put("sucesso", false);
                    j.put("mensagem", "Sem "+ k.getString("metodo"));
//                    j.put("sessao", sessao);
                }
//            }
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString();
    } 
    
    @Path("inserir")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String inserirAgricultor(String dataJson) throws Exception{

        

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
                
          
                TOPessoa t = new TOPessoa();

                //popula objetos e verifica se existe o cpf e usuario cadastrados no banco
                t.setCpf(k.getString("cpf"));

                //se nao existe cpf cadastrado no banco, prosegue o cadastro
                if(BOFactory.buscar(new DAOPessoa(), t, "GET_POR_CPF") == null ){
                    TOLogin tl = new TOLogin();

                    tl.setUsuario(k.getString("usuario_login"));
                    //teste se existe o usuario cadastrado
                    if(BOFactory.buscar(new DAOLogin(), tl, "DEFAULT") == null ){
                        long idGerado;
                        TOEndereco te = new TOEndereco();
                        

                        //tabela endereco

                        te.setCidade_idcidade(k.getLong("cidade_idcidade"));
                        te.setRua(k.getString("rua"));
                        te.setGps_lat(k.getInt("gps_lat"));
                        te.setGps_long(k.getInt("gps_long"));
                        te.setBairro(k.getString("bairro"));
                        te.setComplemento(k.getString("complemento"));
                        te.setCep(k.getString("cep"));
                        te.setNumero(k.getInt("numero"));
                        //salva o endereco no banco de dados
                        idGerado = BOFactory.inserir(new DAOEndereco(), te, "DEFAULT");

                        //tabela pessoa
                        t.setEndereco_idendereco(idGerado);
                        t.setEscolaridade_idescolaridade(k.getLong("escolaridade_idescolaridade"));
                        t.setEstadocivil_idestadocivil(k.getLong("estadocivil_idestadocivil"));
                        t.setNome(k.getString("nome"));
                        t.setSobrenome(k.getString("sobrenome"));
                        t.setApelido(k.getString("apelido"));
                        t.setRg(k.getString("rg"));
                        t.setDatanascimento(k.getString("datanascimento"));
                        t.setSexo(k.getString("sexo"));
                        t.setTelefone1(k.getString("telefone1"));
                        t.setTelefone2(k.getString("telefone2"));
                        t.setEmail(k.getString("email"));
                        //grava informacoes no banco de dados
                        idGerado = BOFactory.inserir(new DAOPessoa(), t, "DEFAULT");

                        //tabela login
                        tl.setPessoa_idpessoa(idGerado);
                        tl.setUnidade_idunidade(k.getLong("unidade_idunidade"));
                        tl.setSenha(k.getString("senha"));
                        //grava no banco de dados os dados da classe TOLogin
                        BOFactory.inserirIDString(new DAOLogin(), tl);

                        TOGrupos tg = new TOGrupos();
                        //tabela grupos
                        tg.setLoginusuario(k.getString("usuario_login"));
                        tg.setGrupo(k.getString("grupo"));
                        BOFactory.inserirIDString(new DAOGrupos(), tg);
                        
                        j.put("sucesso", true);
                        j.put("mensagem", "Cadastro com sucesso!");
//                        j.put("sessao", sessao);
                    //mensagen de usuario ja existente
                    }else{
                        j.put("sucesso", false);
                        j.put("mensagem", "Erro, usuário já cadastrado!");
//                        j.put("sessao", sessao);
                    }  
                //mensagen de usuario ja existente
                }else{
                    j.put("sucesso", false);
                    j.put("mensagem", "Erro, cpf já cadastrado!");
//                    j.put("sessao", sessao);
                }
//            }
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
    public String buscar(String dataJson) throws Exception{
        
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
                

                TOPessoa p = new TOPessoa();
                
                //testa se a busca vai ser por id, ou cpf/nome/rg
                //if(k.getString("metodo").equals("buscaragricultorid")){
               p.setIdpessoa(k.getLong("idpessoa"));
                //}else{
                   // p.setNome(k.getString("campo"));
                    //p.setCpf(k.getString("campo"));
                   // p.setRg(k.getString("campo"));  
               // }
                
//                
                
                p = (TOPessoa) BOFactory.buscar(new DAOPessoa(), p, k.getString("metodo"));
                
                
                if(p == null){
                    j.put("sucesso", false);
                    j.put("mensagem", k.getString("metodo")+" não encontrado");
//                    j.put("sessao", sessao);
                }else{
                    j.put("data", p.buscarJson(k.getString("metodo")));
//                    j.put("sessao", sessao);
                    j.put("sucesso", true);
                }
      
                        
//            }
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString(); 
        
    }
}
