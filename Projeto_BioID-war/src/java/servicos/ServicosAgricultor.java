/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import bo.BOFactory;
import dao.DAOAgricultor;
import dao.DAOEndereco;
import dao.DAOLogin;
import dao.DAOPessoa;
import dao.DAOPropriedade;
import dao.DAORelacaopa;
import fw.Criptografia;
import fw.VerificarSessao;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONObject;
import to.TOAgricultor;
import to.TOEndereco;
import to.TOLogin;
import to.TOPessoa;
import to.TOPropriedade;
import to.TORelacaopa;

/**
 * REST Web Service
 *
 * @author daniel
 */
@Path("pessoa")
public class ServicosAgricultor {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServicoPessoa
     */
    public ServicosAgricultor() {
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
            VerificarSessao vs = new VerificarSessao();
            String sessao = vs.VerificarSessao(k.getString("usuario"), k.getString("sessao"));
            
            if( sessao == null){
                j.put("sucesso", false);
                j.put("mensagem", "Sessao não encontrada!");
            }else{
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
                
                p = (TOPessoa) BOFactory.get(new DAOPessoa(), p, k.getString("metodo"));
                
                
                if(p == null){
                    j.put("sucesso", false);
                    j.put("mensagem", k.getString("metodo")+" não encontrado");
                    j.put("sessao", sessao);
                }else{
                    j.put("data", p.getJson(k.getString("metodo")));
                    j.put("sessao", sessao);
                    j.put("sucesso", true);
                }
      
                        
            }
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString(); 
        
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
            VerificarSessao vs = new VerificarSessao();
            String sessao = vs.VerificarSessao(k.getString("usuario"), k.getString("sessao"));
            
            if( sessao == null){
                j.put("sucesso", false);
                j.put("mensagem", "Sessao não encontrada!");
            }else{
                //comeca a requisicao
                
                TOPessoa to = new TOPessoa();
                
                
                to.setNome(k.getString("valor"));
                to.setIdunidade(k.getLong("idunidade"));
                        
                
                JSONArray ja = BOFactory.listar(new DAOPessoa(),to , k.getString("metodo")) ;

                if(ja.length() > 0){
                    j.put("data", ja);
                    j.put("sucesso", true);
                    j.put("sessao", sessao);
                }else{
                    j.put("sucesso", false);
                    j.put("mensagem", "Sem "+ k.getString("metodo"));
                    j.put("sessao", sessao);
                }
            }
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString();
    } 
    
    
//    @Path("procuraragricultor")
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public String procurar(String dataJson) throws Exception{
//        
//        JSONObject j = new JSONObject();
//        JSONObject k = new JSONObject(dataJson);
//        
//        try{
//                //verificar sessao
//            TOSessao ts = new TOSessao();
//            ts.setLogin_idlogin(k.getString("usuario"));
//            ts.setSessao(k.getString("sessao"));
//            
//            ts = (TOSessao) BOFactory.get(new DAOSessao(), ts, "get_sessao");
//            
//            
//            if(ts == null){
//                j.put("sucesso", false);
//                j.put("mensagem", "Sessao não encontrada!");
//            }else{
//                SecureRandom random = new SecureRandom();
//                String novaSessao = new BigInteger(130, random).toString(32);
//                ts.setSessao(novaSessao);
//                BOFactory.editar(new DAOSessao(), ts, "update_sessao");  
//                
//                //comeca a requisicao
//                
//                TOPessoa to = new TOPessoa();
//                
////                verifica se existe espaco na string enviada do input
//                String[] palabrasSeparadas = k.getString("valor").split(" ");
//                String metodo;
//                
//                    if(palabrasSeparadas.length > 1){
//                        to.setNome(palabrasSeparadas[0]);
//                        to.setSobrenome(palabrasSeparadas[1]);
//                        metodo = k.getString("metodo") + "2";
//                    }else{
//                        to.setNome(k.getString("valor"));
//                        metodo = k.getString("metodo");
//                    }
////                    to.setCpf(k.getString("valor"));
////                    to.setRg(k.getString("valor"));
//                
////                
//                to.setIdunidade(k.getLong("idunidade"));
//                
//                JSONArray ja = BOFactory.listar(new DAOPessoa(), to, metodo) ;
//
//                if(ja.length() > 0){
//                    j.put("data", ja);
//                    j.put("sucesso", true);
//                    j.put("sessao", novaSessao);
//                }else{
//                    j.put("sucesso", false);
//                    j.put("mensagem", "Sem "+ k.getString("metodo"));
//                    j.put("sessao", novaSessao);
//                }
//            }
//        }catch(Exception e){
//            j.put("sucesso", false);
//            j.put("mensagem", e.getMessage());
//        }
//
//        return j.toString();
//    } 
    
  
    
//    @Path("listarusuarios")
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public String listarUsuarios(
//                        @FormParam("id") long id,
//                        @FormParam("sessao") String sessao
//                        ) throws Exception{
//        
//        JSONObject j = new JSONObject();
// 
//        try{
//            //verificar sessao
//            JSONObject js = new VerificarSessao().VerificarSessao(id, sessao);
//            
//            
//            if((boolean) js.get("sucesso") == false){
//                j.put("sucesso", false);
//                j.put("mensagem", "Sessao não encontrada!");
//            }else{
//                
//                JSONArray ja = BOFactory.listar(new DAOPessoa(), "listarusuarios") ;
//
//                if(ja.length() > 0){
//                    j.put("usuarios", ja);
//                    j.put("sucesso", true);
//                    j.put("sessao", js.get("sessao"));
//                }else{
//                    j.put("sucesso", false);
//                    j.put("mensagem", "Sem usuarios");
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
    
//    //serviço de listar os usuarios
//    @Path("listar")
//    @GET
//    public String listar() throws Exception{
//    
//        JSONObject j = new JSONObject();
//        
//        try{
//            //cria um jsonarray e popula com uma consulta no banco
//            JSONArray ja = BOFactory.listar(new DAOPessoa());
//            
//            j.put("Data", ja);
//            j.put("sucesso", true);
//            
//        }catch(Exception e){
//            j.put("sucesso", false);
//            j.put("mensagem", e.getMessage());
//        }
//        
//        
//        return j.toString();
//    }
    
    
     //adicionar usuario no sistema
//    @Path("inserirusuario")
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public String inserirUsuario(String dataJson) throws Exception{
//
//        
//
//        JSONObject j = new JSONObject();
//        JSONObject k = new JSONObject(dataJson);
//        
//        try{
//            
//             //verificar sessao
//            JSONObject js = new VerificarSessao().VerificarSessao(k.getString("usuario"), k.getString("sessao"));
//            
//            if((boolean) js.get("sucesso") == false){
//                j.put("sucesso", false);
//                j.put("mensagem", "Sessao não encontrada!");
//            }else{
//                //cria objetos
//                TOPessoa t = new TOPessoa();
//                TOLogin tl = new TOLogin();
//
//                //popula objetos e verifica se existe o cpf e usuario cadastrados no banco
//                t.setCpf(k.getString("cpf"));
//                tl.setUsuario(k.getString("usuario"));
//
//                //se nao existe cpf nem usuario eh cadastrado no banco
//                if(BOFactory.get(new DAOPessoa(), t, null) == null ){
//                    if(BOFactory.get(new DAOLogin(), tl, null) == null ){
//
//                        //tabela endereco
//                        TOEndereco te = new TOEndereco();
//                        te.setCidade_idcidade(k.getLong("cidade_idcidade"));
//                        te.setRua(k.getString("rua"));
//                        te.setGps_lat(k.getInt("gps_lat"));
//                        te.setGps_long(k.getInt("gps_long"));
//                        te.setBairro(k.getString("bairro"));
//                        te.setComplemento(k.getString("complemento"));
//                        te.setCep(k.getString("cep"));
//                        te.setNumero(k.getInt("numero"));
//
//                        //tabela pessoa
//                        t.setEndereco_idendereco(BOFactory.inserir(new DAOEndereco(), te, null));
//                        t.setEscolaridade_idescolaridade(k.getLong("escolaridade_idescolaridade"));
//                        t.setEstadocivil_idestadocivil(k.getLong("estadocivil_idestadocivil"));
//                        t.setNome(k.getString("nome"));
//                        t.setSobrenome(k.getString("sobrenome"));
//                        t.setApelido(k.getString("apelido"));
//                        t.setRg(k.getString("rg"));
//                        t.setDatanascimento(k.getString("datanascimento"));
//                        t.setSexo(k.getString("sexo"));
//                        t.setTelefone1(k.getString("telefone1"));
//                        t.setTelefone2(k.getString("telefone2"));
//                        t.setEmail(k.getString("email"));
//
//
//                        //tabela login
//                        tl.setPessoa_idpessoa(BOFactory.inserir(new DAOPessoa(), t, null));
//                        tl.setUnidade_idunidade(k.getLong("unidade_idunidade"));
//                        tl.setSenha(k.getString("senha"));
//                        tl.setPapel(k.getString("papel"));
//                        //grava no banco de dados os dados da classe TOLogin
//                        BOFactory.inserir(new DAOLogin(), tl, null);
//
//
//
//                        j.put("sucesso", true);
//                        j.put("mensagem", "Usuário cadastrado!");
//                        j.put("sessao", js.get("sessao"));
//                    //mensagen de usuario ja existente
//                    }else{
//                        j.put("sucesso", false);
//                        j.put("mensagem", "Usuario já existe no sistema!");
//                        j.put("sessao", js.get("sessao"));
//                    }
//                //mensagen de cpf ja existente
//                }else{
//                    j.put("sucesso", false);
//                    j.put("mensagem", "CPF já cadastrado!");
//                    j.put("sessao", js.get("sessao"));
//                }
//            }
//        }catch(Exception e){
//            j.put("sucesso", false);
//            j.put("mensagem", e.getMessage());
//            
//        }
//        
//        return j.toString();
//    }
     
    //adicionar pessoa/usuario/agricultor no sistema
    @Path("inserir")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String inserirAgricultor(String dataJson) throws Exception{

        

        JSONObject j = new JSONObject();
        JSONObject k = new JSONObject(dataJson);
        
        try{
             //verifica  a sessao
            VerificarSessao vs = new VerificarSessao();
            String sessao = vs.VerificarSessao(k.getString("usuario"), k.getString("sessao"));
            
            if( sessao == null){
                j.put("sucesso", false);
                j.put("mensagem", "Sessao não encontrada!");
            }else{
                //comeca a requisicao
                
          
                TOPessoa t = new TOPessoa();

                //popula objetos e verifica se existe o cpf e usuario cadastrados no banco
                t.setCpf(k.getString("cpf"));

                //se nao existe cpf cadastrado no banco, prosegue o cadastro
                if(BOFactory.get(new DAOPessoa(), t, "get_pessoa_por_cpf") == null ){
                    TOLogin tl = new TOLogin();

                    tl.setUsuario(k.getString("usuario"));
                    //teste se existe o usuario cadastrado
                    if(BOFactory.get(new DAOLogin(), tl, "get_usuario") == null ){
                        long idGeradoPessoa;
                        long idGeradoEndereco;
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
                        idGeradoEndereco = BOFactory.inserir(new DAOEndereco(), te, null);

                        //tabela pessoa
                        t.setEndereco_idendereco(idGeradoEndereco);
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
                        idGeradoPessoa = BOFactory.inserir(new DAOPessoa(), t, null);

                        //tabela login
                        tl.setPessoa_idpessoa(idGeradoPessoa);
                        tl.setUnidade_idunidade(k.getLong("unidade_idunidade"));
                        tl.setSenha(Criptografia.md5(k.getString("senha")));
//                        tl.setPapel("a");
                        //grava no banco de dados os dados da classe TOLogin
                        BOFactory.inserir(new DAOLogin(), tl, null);

                        //cadastro tabela de agricultor
                        
                        TORelacaopa trpa = new TORelacaopa();
                        TOPropriedade tp = new TOPropriedade();
                        TOAgricultor ta = new TOAgricultor();
                        //tabela agricultores
                        ta.setPessoa_idpessoa(idGeradoPessoa);
                        ta.setQtdIntegrantes(k.getInt("qtdIntegrantes"));
                        ta.setQtdCriancas(k.getInt("qtdCriancas"));
                        ta.setQtdGravidas(k.getInt("qtdGravidas"));

                        BOFactory.inserir(new DAOAgricultor(), ta, null);

                        //tabela propriedade
                        tp.setEndereco_idendereco(idGeradoEndereco);
                        tp.setUnidade_idunidade(k.getLong("unidade_idunidade"));
                        tp.setNomepropriedade(k.getString("nomepropriedade"));
                        tp.setArea(k.getDouble("area"));
                        tp.setUnidadedemedida(k.getLong("unidadedemedida"));
                        tp.setAreautilizavel(k.getDouble("areautilizavel"));
                        tp.setUnidadedemedidaau(k.getLong("unidadedemedidaau"));

                        //tabela relacao agricultor propriedade
                        trpa.setPropriedade_idpropriedade(BOFactory.inserir(new DAOPropriedade(), tp, null));
                        trpa.setAgricultor_pessoa_idpessoa(idGeradoPessoa);
                        //grava no banco de dados a propriedade e popula a classe Relacaopa
                        BOFactory.inserir(new DAORelacaopa(), trpa, null);

                        
                        j.put("sucesso", true);
                        j.put("mensagem", "Agricultor cadastrado com sucesso!");
                        j.put("sessao", sessao);
                    //mensagen de usuario ja existente
                    }else{
                        j.put("sucesso", false);
                        j.put("mensagem", "Erro, usuário já cadastrado!");
                        j.put("sessao", sessao);
                    }  
                //mensagen de usuario ja existente
                }else{
                    j.put("sucesso", false);
                    j.put("mensagem", "Erro, cpf já cadastrado!");
                    j.put("sessao", sessao);
                }
            }
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
            
        }
        
        return j.toString();
    }
    
    
    //edita um usuario no banco de dados
    @Path("editar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public String editar(String dataJson) throws Exception{
        
        JSONObject j = new JSONObject();
        JSONObject k = new JSONObject(dataJson);
        
        try{
             //verifica  a sessao
            VerificarSessao vs = new VerificarSessao();
            String sessao = vs.VerificarSessao(k.getString("usuario"), k.getString("sessao"));
            
            if( sessao == null){
                j.put("sucesso", false);
                j.put("mensagem", "Sessao não encontrada!");
            }else{
                //comeca a requisicao
                TOPessoa t = new TOPessoa();

                t = (TOPessoa) BOFactory.get(new DAOPessoa(), t, k.getString("metodo"));
                if(t == null){
                    j.put("sucesso", false);
                    j.put("mensagem", "Usuário não encontrado");
                }else{
                    //t.setUsuario(usuario);
                   // t.setSenha(senha);
                    //t.setEmail(email);

                    BOFactory.editar(new DAOPessoa(), t, k.getString("metodo"));

                    j.put("sucesso", true);
                    j.put("sessao", sessao);
                }
            }
        }catch(Exception e){
            j.put("suceso", false);
            j.put("mensagem", e.getMessage());
        }
                
        return j.toString();
    }
    
    //exclui usuario do banco de dados
    @Path("excluir")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public String excluir(String dataJson) throws Exception{
        //objeto de retorno da requisicao
        JSONObject j = new JSONObject();
        JSONObject k = new JSONObject(dataJson);
        
        try{
            
            //verifica  a sessao
            VerificarSessao vs = new VerificarSessao();
            String sessao = vs.VerificarSessao(k.getString("usuario"), k.getString("sessao"));
            
            if( sessao == null){
                j.put("sucesso", false);
                j.put("mensagem", "Sessao não encontrada!");
            }else{
                
                //comeca a requisicao
                TOPessoa to = new TOPessoa();
                //to.setId(id);

                to = (TOPessoa) BOFactory.get(new DAOPessoa(), to, k.getString("metodo"));

                if(to == null){
                    j.put("sucesso", false);
                    j.put("messangem", "Usuário não encontrado");
                    j.put("sessao", sessao);
                }else{
                    BOFactory.excluir(new DAOPessoa(), to, k.getString("metodo"));
                    
                    
                    j.put("sessao", sessao);
                    j.put("sucesso", true);
                }
            }
        }catch (Exception e){
            j.put("sucesso", false);
            j.put("messangem", e.getMessage());
        }
        
        
        return j.toString();
        
    }
    

    
    
}