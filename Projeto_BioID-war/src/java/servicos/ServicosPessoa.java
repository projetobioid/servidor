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
import dao.DAOSessao;
import fw.VerificarSessao;
import java.math.BigInteger;
import java.security.SecureRandom;
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
import to.TOAgricultor;
import to.TOEndereco;
import to.TOLogin;
import to.TOPessoa;
import to.TOPropriedade;
import to.TORelacaopa;
import to.TOSessao;

/**
 * REST Web Service
 *
 * @author daniel
 */
@Path("pessoa")
public class ServicosPessoa {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServicoPessoa
     */
    public ServicosPessoa() {
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
                
                TOPessoa to = new TOPessoa();
                to.setIdunidade(k.getLong("idunidade"));
                JSONArray ja = BOFactory.listar(new DAOPessoa(),to , k.getString("metodo")) ;

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
    @Path("procuraragricultor")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String procurar(
                        String dataJson
                        ) throws Exception{
        JSONObject j = new JSONObject();
        JSONObject k = new JSONObject(dataJson);
        
        try{
                
                
                TOPessoa to = new TOPessoa();
                
//                verifica se existe espaco na string enviada do input
                String[] palabrasSeparadas = k.getString("valor").split(" ");
                String metodo;
                
                    if(palabrasSeparadas.length > 1){
                        to.setNome(palabrasSeparadas[0]);
                        to.setSobrenome(palabrasSeparadas[1]);
                        metodo = k.getString("metodo") + "2";
                    }else{
                        to.setNome(k.getString("valor"));
                        metodo = k.getString("metodo");
                    }
//                    to.setCpf(k.getString("valor"));
//                    to.setRg(k.getString("valor"));
                
//                
                to.setIdunidade(k.getLong("idunidade"));
                
                JSONArray ja = BOFactory.listar(new DAOPessoa(), to, metodo) ;

                if(ja.length() > 0){
                    j.put("data", ja);
                    j.put("sucesso", true);
                }else{
                    j.put("sucesso", false);
                    j.put("mensagem", "Sem "+ k.getString("metodo"));
                }
            
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
//        JSONArray  A = new JSONArray();
//        
//        A.put("DANIEL");
//        A.put("DANIEL1");
//        A.put("DANIEL2");
//        A.put("DANIEL3");
//        
//        return A.toString();
        return j.toString();
    } 
    
  
    
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
    @Path("inserirusuario")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String inserirUsuario(
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
                //cria objetos
                TOPessoa t = new TOPessoa();
                TOLogin tl = new TOLogin();

                //popula objetos e verifica se existe o cpf e usuario cadastrados no banco
                t.setCpf(k.getString("cpf"));
                tl.setUsuario(k.getString("usuario"));

                //se nao existe cpf nem usuario eh cadastrado no banco
                if(BOFactory.get(new DAOPessoa(), t, null) == null ){
                    if(BOFactory.get(new DAOLogin(), tl, null) == null ){

                        //tabela endereco
                        TOEndereco te = new TOEndereco();
                        te.setCidade_idcidade(k.getLong("cidade_idcidade"));
                        te.setRua(k.getString("rua"));
                        te.setGps_lat(k.getInt("gps_lat"));
                        te.setGps_long(k.getInt("gps_long"));
                        te.setBairro(k.getString("bairro"));
                        te.setComplemento(k.getString("complemento"));
                        te.setCep(k.getString("cep"));
                        te.setNumero(k.getInt("numero"));

                        //tabela pessoa
                        t.setEndereco_idendereco(BOFactory.inserir(new DAOEndereco(), te, null));
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


                        //tabela login
                        tl.setPessoa_idpessoa(BOFactory.inserir(new DAOPessoa(), t, null));
                        tl.setUnidade_idunidade(k.getLong("unidade_idunidade"));
                        tl.setSenha(k.getString("senha"));
                        tl.setPapel(k.getString("papel"));
                        //grava no banco de dados os dados da classe TOLogin
                        BOFactory.inserir(new DAOLogin(), tl, null);



                        j.put("sucesso", true);
                        j.put("mensagem", "Usuário cadastrado!");
                        j.put("sessao", js.get("sessao"));
                    //mensagen de usuario ja existente
                    }else{
                        j.put("sucesso", false);
                        j.put("mensagem", "Usuario já existe no sistema!");
                        j.put("sessao", js.get("sessao"));
                    }
                //mensagen de cpf ja existente
                }else{
                    j.put("sucesso", false);
                    j.put("mensagem", "CPF já cadastrado!");
                    j.put("sessao", js.get("sessao"));
                }
            }
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
            
        }
        
        return j.toString();
    }
     
    //adicionar pessoa/usuario/agricultor no sistema
    @Path("inseriragricultor")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String inserirAgricultor(
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
                
          
                TOPessoa t = new TOPessoa();

                //popula objetos e verifica se existe o cpf e usuario cadastrados no banco
                t.setCpf(k.getString("cpf"));

                //se nao existe cpf cadastrado no banco, prosegue o cadastro
                if(BOFactory.get(new DAOPessoa(), t, k.) == null ){
                    TOLogin tl = new TOLogin();

                    tl.setUsuario(k.getString("usuario"));
                    //teste se existe o usuario cadastrado
                    if(BOFactory.get(new DAOLogin(), tl) == null ){
                        long idGeradoPessoa;
                        long idGeradoEndereco;
                        TOEndereco te = new TOEndereco();
                        TORelacaopa trpa = new TORelacaopa();
                        TOPropriedade tp = new TOPropriedade();
                        TOAgricultor ta = new TOAgricultor();

                        //tabela endereco

                        te.setCidade_idcidade(k.getLong("cidade_idcidade"));
                        te.setRua(k.getString("rua"));
                        te.setGps_lat(k.getInt("gps_lat"));
                        te.setGps_long(k.getInt("gps_long"));
                        te.setBairro(k.getString("bairro"));
                        te.setComplemento(k.getString("complemento"));
                        te.setCep(k.getString("cep"));
                        te.setNumero(k.getInt("numero"));
                        idGeradoEndereco = BOFactory.inserir(new DAOEndereco(), te);

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
                        idGeradoPessoa = BOFactory.inserir(new DAOPessoa(), t);

                        //tabela login
                        tl.setPessoa_idpessoa(idGeradoPessoa);
                        tl.setUnidade_idunidade(k.getLong("unidade_idunidade"));
                        tl.setSenha(k.getString("senha"));
                        tl.setPapel("a");
                        //grava no banco de dados os dados da classe TOLogin
                        BOFactory.inserir(new DAOLogin(), tl);

                        //tabela agricultores
                        ta.setPessoa_idpessoa(idGeradoPessoa);
                        ta.setQtdIntegrantes(k.getInt("qtdIntegrantes"));
                        ta.setQtdCriancas(k.getInt("qtdCriancas"));
                        ta.setQtdGravidas(k.getInt("qtdGravidas"));

                        BOFactory.inserir(new DAOAgricultor(), ta);

                        //tabela propriedade
                        tp.setEndereco_idendereco(idGeradoEndereco);
                        tp.setUnidade_idunidade(k.getLong("unidade_idunidade"));
                        tp.setNomepropriedade(k.getString("nomepropriedade"));
                        tp.setArea(k.getDouble("area"));
                        tp.setUnidadedemedida(k.getLong("unidadedemedida"));
                        tp.setAreautilizavel(k.getDouble("areautilizavel"));
                        tp.setUnidadedemedidaau(k.getLong("unidadedemedidaau"));

                        //tabela relacao agricultor propriedade
                        trpa.setPropriedade_idpropriedade(BOFactory.inserir(new DAOPropriedade(), tp));
                        trpa.setAgricultor_pessoa_idpessoa(idGeradoPessoa);
                        //grava no banco de dados a propriedade e popula a classe Relacaopa
                        BOFactory.inserir(new DAORelacaopa(), trpa);


                        j.put("sucesso", true);
                        j.put("mensagem", "Agricultor cadastrado!");
                        j.put("sessao", js.get("sessao"));
                    //mensagen de usuario ja existente
                    }else{
                        j.put("sucesso", false);
                        j.put("mensagem", "Erro, usuário já cadastrado!");
                        j.put("sessao", js.get("sessao"));
                    }  
                //mensagen de usuario ja existente
                }else{
                    j.put("sucesso", false);
                    j.put("mensagem", "Erro, cpf já cadastrado!");
                    j.put("sessao", js.get("sessao"));
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
    @POST
    public String editar(@FormParam("id") long id,
            @FormParam("metodo") String metodo,
            @FormParam("usuario") String usuario,
            @FormParam("senha") String senha,
            @FormParam("email") String email,
            @FormParam("sessao") String sessao
            ) throws Exception{
        
        JSONObject j = new JSONObject();
        
        try{
            TOPessoa t = new TOPessoa();
           // t.setId(id);
           
            t = (TOPessoa) BOFactory.get(new DAOPessoa(), t, metodo);
            if(t== null){
                j.put("sucesso", false);
                j.put("mensagem", "Usuário não encontrado");
            }else{
                //t.setUsuario(usuario);
               // t.setSenha(senha);
                //t.setEmail(email);

                BOFactory.editar(new DAOPessoa(), t);
            
                j.put("sucesso", true);
            }
        }catch(Exception e){
            j.put("suceso", false);
            j.put("mensagem", e.getMessage());
        }
                
        return j.toString();
    }
    
    //exclui usuario do banco de dados
    @Path("excluir")
    @POST
    public String excluir(@FormParam("id") long id,
            @FormParam("metodo") String metodo,
            @FormParam("sessao") String sessao
            ) throws Exception{
        //objeto de retorno da requisicao
        JSONObject j = new JSONObject();
        
        try{
            TOPessoa to = new TOPessoa();
            //to.setId(id);
            
            to = (TOPessoa) BOFactory.get(new DAOPessoa(), to, metodo);
            
            if(to == null){
                j.put("sucesso", false);
                j.put("messangem", "Usuário não encontrado");
            }else{
                BOFactory.excluir(new DAOPessoa(), to);
                j.put("sucesso", true);
            }
        }catch (Exception e){
            j.put("sucesso", false);
            j.put("messangem", e.getMessage());
        }
        
        
        return j.toString();
        
    }
    
    //login
    @Path("validacao")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String login(
            String dataJson
                        ) throws Exception{
        
        //objeto de retorno da requisicao
        JSONObject j = new JSONObject();
        JSONObject k = new JSONObject(dataJson);
        
        try{
            TOLogin to = new TOLogin();
            to.setUsuario(k.getString("usuario"));
            to.setSenha(k.getString("senha"));
            
            
            to = (TOLogin) BOFactory.get(new DAOLogin(), to, k.getString("metodo"));
            
            if(to == null){
                j.put("sucesso", false);
                j.put("messangem", "Usuário não encontrado");
            }else{
                //gera um idsessao e cria um novo registro
                TOSessao ts = new TOSessao();
                SecureRandom random = new SecureRandom();     
        
                ts.setLogin_idlogin(to.getIdlogin());
                ts.setSessao(new BigInteger(130, random).toString(32));
                
             
                //salva uma nova sessao no banco de dados
                BOFactory.inserir(new DAOSessao(), ts, k.getString("metodo"));
                
                //retorna valores do login
     
                j.put("idpessoa", to.getPessoa_idpessoa());
                j.put("papel", to.getPapel());
                j.put("idunidade", to.getUnidade_idunidade());
                j.put("nome", to.getNome());
                j.put("sessao", ts.getSessao());
                
                
                //j.put("tempoLogin", ts.getDatalogin());
                
                j.put("sucesso", true);
                //retorna a data de login que espirará em um tempo determinado
                //j.put("logTempo", ((730 * Float.parseFloat(getData("M"))) - (730 - (Float.parseFloat(getData("d"))*24)))+168 );
                
                
            }
        }catch (Exception e){
            j.put("sucesso", false);
            j.put("messangem", e.getMessage());
        }
        
        return j.toString();
    }
    

    
    
    @Path("inserirpropriedade")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String inserirPropriedade(
            //tabela endereco
            @FormParam("cidade_idcidade") long cidade_idcidade,
            @FormParam("rua") String rua,
            @FormParam("gps_lat") int gps_lat,
            @FormParam("gps_long") int gps_long,
            @FormParam("bairro") String bairro,
            @FormParam("complemento") String complemento,
            @FormParam("cep") String cep,
            @FormParam("numero") int numero,
            //tabela propriedade
            @FormParam("unidade_idunidade") long unidade_idunidade,
            @FormParam("nomepropriedade") String nomepropriedade,
            @FormParam("area") float area,
            @FormParam("unidadedemedida") long unidadedemedida,
            @FormParam("areautilizavel") float areautilizavel,
            @FormParam("unidadedemedidaau") long unidadedemedidaau,
            @FormParam("cpf") String cpf,
            @FormParam("sessao") String sessao
            ) throws Exception{
        
        JSONObject j = new JSONObject();
        try{
            
            //objeto TOEndereco
            TOEndereco te = new TOEndereco();
            TOPropriedade tpd = new TOPropriedade();
            
            tpd.setNomepropriedade(nomepropriedade);
            tpd.setCpf(cpf);
            
            if(BOFactory.get(new DAOPropriedade(), tpd, "") == null ){
                te.setCidade_idcidade(cidade_idcidade);
                te.setRua(rua);
                te.setGps_lat(gps_lat);
                te.setGps_long(gps_long);
                te.setBairro(bairro);
                te.setComplemento(complemento);
                te.setCep(cep);
                te.setNumero(numero);

                tpd.setEndereco_idendereco(BOFactory.inserir(new DAOEndereco(), te));
                tpd.setUnidade_idunidade(unidade_idunidade);
                
                tpd.setArea(area);
                tpd.setUnidadedemedida(unidadedemedida);
                tpd.setAreautilizavel(areautilizavel);
                tpd.setUnidadedemedidaau(unidadedemedidaau);

                TORelacaopa tr = new TORelacaopa();

                TOPessoa tp = new TOPessoa();
                tp.setCpf(cpf);
                tr.setPropriedade_idpropriedade(BOFactory.inserir(new DAOPropriedade(), tpd));
                ((TOPessoa) BOFactory.get(new DAOPessoa(), tp, "")).getIdpessoa();
                
                tr.setAgricultor_pessoa_idpessoa(((TOPessoa) BOFactory.get(new DAOPessoa(), tp, "")).getIdpessoa());

                BOFactory.inserir(new DAORelacaopa(), tr);

                j.put("sucesso", true);
                j.put("messangem", "Propriedade cadastrada");
            }else{
                j.put("sucesso", false);
                j.put("erro", 1);
                j.put("mensagem", "Propriedade ja existe no sistema!");
            }
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("messangem", e.getMessage());
        }
        
        return j.toString();
    } 
    
    @Path("listarpropriedades")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String listarPropriedades(
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
                
            
                TOPessoa t = new TOPessoa();
                t.setIdpessoa(k.getLong("idpessoa"));
                t.setIdunidade(k.getLong("idunidade"));


                JSONArray ja = BOFactory.listar(new DAOPropriedade(), t, "listarpropriedades") ;

                if(ja.length() > 0){
                    j.put("data", ja);
                    j.put("sucesso", true);
                    j.put("sessao", js.get("sessao"));
                }else{
                    j.put("sucesso", false);
                    j.put("mensagem", "Usuário não contém propriedade cadastrada!");
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