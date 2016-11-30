/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import bo.BOFactory;
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
    public String buscar(
                        @FormParam("metodo") String metodo,
                        @FormParam("idpessoa") long idpessoa,
                        @FormParam("id") long id,
                        @FormParam("sessao") String sessao
                        
                        
                        ) throws Exception{
        
        JSONObject j = new JSONObject();
        
        try{               
             //verificar sessao
            JSONObject js = new VerificarSessao().VerificarSessao(id, sessao);
            
            
            if((boolean) js.get("sucesso") == false){
                j.put("sucesso", false);
                j.put("mensangem", "Sessao não encontrada!");
            }else{
                

                TOPessoa p = new TOPessoa();
                p.setIdpessoa(idpessoa);
                
                p = (TOPessoa) BOFactory.get(new DAOPessoa(), p, metodo);
                
                
                if(p == null){
                    j.put("sucesso", false);
                    j.put("mensagem", metodo+" não encontrado");
                }else{
                    j.put("data", p.getJson(metodo));
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
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String listar(
                        @FormParam("metodo") String metodo,
                        @FormParam("id") long id,
                        @FormParam("sessao") String sessao
                        ) throws Exception{
        
        JSONObject j = new JSONObject();
 
        try{
            //verificar sessao
            JSONObject js = new VerificarSessao().VerificarSessao(id, sessao);
            
            
            if((boolean) js.get("sucesso") == false){
                j.put("sucesso", false);
                j.put("mensangem", "Sessao não encontrada!");
            }else{
                
                JSONArray ja = BOFactory.listar(new DAOPessoa(), metodo) ;

                if(ja.length() > 0){
                    j.put("data", ja);
                    j.put("sucesso", true);
                    j.put("sessao", js.get("sessao"));
                }else{
                    j.put("sucesso", false);
                    j.put("mensagem", "Sem "+ metodo);
                    j.put("sessao", js.get("sessao"));
                }
            }
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString();
    } 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Path("listarusuarios")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String listarUsuarios(
                        @FormParam("id") long id,
                        @FormParam("sessao") String sessao
                        ) throws Exception{
        
        JSONObject j = new JSONObject();
 
        try{
            //verificar sessao
            JSONObject js = new VerificarSessao().VerificarSessao(id, sessao);
            
            
            if((boolean) js.get("sucesso") == false){
                j.put("sucesso", false);
                j.put("mensangem", "Sessao não encontrada!");
            }else{
                
                JSONArray ja = BOFactory.listar(new DAOPessoa(), "listarusuarios") ;

                if(ja.length() > 0){
                    j.put("usuarios", ja);
                    j.put("sucesso", true);
                    j.put("sessao", js.get("sessao"));
                }else{
                    j.put("sucesso", false);
                    j.put("mensagem", "Sem usuarios");
                    j.put("sessao", js.get("sessao"));
                }
            }
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString();
    } 
    
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
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String inserir(
            //tabela endereco
            @FormParam("cidade_idcidade") long cidade_idcidade,
            @FormParam("rua") String rua,
            @FormParam("gps_lat") int gps_lat,
            @FormParam("gps_long") int gps_long,
            @FormParam("bairro") String bairro,
            @FormParam("complemento") String complemento,
            @FormParam("cep") String cep,
            @FormParam("numero") int numero,
            //tabela pessoa
            @FormParam("escolaridade_idescolaridade") long escolaridade_idescolaridade,
            @FormParam("estadocivil_idestadocivil") long estadocivil_idestadocivil,
            @FormParam("nome") String nome,
            @FormParam("sobrenome") String sobrenome,
            @FormParam("apelido") String apelido,
            @FormParam("cpf") String cpf,
            @FormParam("rg") String rg,
            @FormParam("datanascimento") String datanascimento,
            @FormParam("sexo") String sexo,
            @FormParam("telefone1") String telefone1,
            @FormParam("telefone2") String telefone2,
            @FormParam("email") String email,
  
            //tabela login
            @FormParam("usuario") String usuario,
            @FormParam("senha") String senha,
            @FormParam("papel") String papel,
            @FormParam("unidade_idunidade") long unidade_idunidade,
            @FormParam("id") long id,
            @FormParam("sessao") String sessao
            ) throws Exception{

        

        JSONObject j = new JSONObject();
        
        try{
            //verificar sessao
            JSONObject js = new VerificarSessao().VerificarSessao(id, sessao);
            
            
            if((boolean) js.get("sucesso") == false){
                j.put("sucesso", false);
                j.put("mensangem", "Sessao não encontrada!");
            }else{
                //cria objetos
                TOPessoa t = new TOPessoa();
                TOLogin tl = new TOLogin();

                //popula objetos e verifica se existe o cpf e usuario cadastrados no banco
                t.setCpf(cpf);
                tl.setUsuario(usuario);

                //se nao existe cpf nem usuario eh cadastrado no banco
                if(BOFactory.get(new DAOPessoa(), t) == null ){
                    if(BOFactory.get(new DAOLogin(), tl) == null ){

                        //tabela endereco
                        TOEndereco te = new TOEndereco();
                        te.setCidade_idcidade(cidade_idcidade);
                        te.setRua(rua);
                        te.setGps_lat(gps_lat);
                        te.setGps_long(gps_long);
                        te.setBairro(bairro);
                        te.setComplemento(complemento);
                        te.setCep(cep);
                        te.setNumero(numero);

                        //tabela pessoa
                        t.setEndereco_idendereco(BOFactory.inserir(new DAOEndereco(), te));
                        t.setEscolaridade_idescolaridade(escolaridade_idescolaridade);
                        t.setEstadocivil_idestadocivil(estadocivil_idestadocivil);
                        t.setNome(nome);
                        t.setSobrenome(sobrenome);
                        t.setApelido(apelido);
                        t.setRg(rg);
                        t.setDatanascimento(datanascimento);
                        t.setSexo(sexo);
                        t.setTelefone1(telefone1);
                        t.setTelefone2(telefone2);
                        t.setEmail(email);


                        //tabela login
                        tl.setPessoa_idpessoa(BOFactory.inserir(new DAOPessoa(), t));
                        tl.setUnidade_idunidade(unidade_idunidade);
                        tl.setSenha(senha);
                        tl.setPapel(papel);
                        //grava no banco de dados os dados da classe TOLogin
                        BOFactory.inserir(new DAOLogin(), tl);



                        j.put("sucesso", true);
                        j.put("mensagem", "Usuario cadastrado!");
                        j.put("sessao", js.get("sessao"));
                    //mensagen de cpf ja existente
                    }else{
                        j.put("sucesso", false);
                        j.put("erro", 1);
                        j.put("mensagem", "Usuario já existe no sistema!");
                        j.put("sessao", js.get("sessao"));
                    }
                //mensagen de usuario ja existente
                }else{
                    j.put("sucesso", false);
                    j.put("erro", 3);
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
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String inserir(
            
            //tabela endereco
            @FormParam("cidade_idcidade") long cidade_idcidade,
            @FormParam("rua") String rua,
            @FormParam("gps_lat") int gps_lat,
            @FormParam("gps_long") int gps_long,
            @FormParam("bairro") String bairro,
            @FormParam("complemento") String complemento,
            @FormParam("cep") String cep,
            @FormParam("numero") int numero,
            
            //tabela pessoa
            @FormParam("escolaridade_idescolaridade") long escolaridade_idescolaridade,
            @FormParam("estadocivil_idestadocivil") long estadocivil_idestadocivil,
            @FormParam("nome") String nome,
            @FormParam("sobrenome") String sobrenome,
            @FormParam("apelido") String apelido,
            @FormParam("cpf") String cpf,
            @FormParam("rg") String rg,
            @FormParam("datanascimento") String datanascimento,
            @FormParam("sexo") String sexo,
            @FormParam("telefone1") String telefone1,
            @FormParam("telefone2") String telefone2,
            @FormParam("email") String email,
            
            //tabela agricultor
            @FormParam("qtdedeintegrantes") int qtdedeintegrantes,
            @FormParam("qtdedecriancas") int qtdedecriancas,
            @FormParam("qtdedegravidas") int qtdedegravidas,
            
            //tabela login
            @FormParam("usuario") String usuario,
            @FormParam("senha") String senha,
            @FormParam("unidade_idunidade") long unidade_idunidade,
            
            //tabela propriedade
            @FormParam("nomepropriedade") String nomepropriedade,
            @FormParam("area") float area,
            @FormParam("unidadedemedida") long unidadedemedida,
            @FormParam("areautilizavel") float areautilizavel,
            @FormParam("unidadedemedidaau") long unidadedemedidaau
            ) throws Exception{

        

        JSONObject j = new JSONObject();
        
        try{
            
            //cria objetos
            TOPessoa t = new TOPessoa();

            //popula objetos e verifica se existe o cpf e usuario cadastrados no banco
            t.setCpf(cpf);

            //se nao existe cpf cadastrado no banco, prosegue o cadastro
            if(BOFactory.get(new DAOPessoa(), t) == null ){
                TOLogin tl = new TOLogin();
                
                tl.setUsuario(usuario);
                //teste se existe o usuario cadastrado
                if(BOFactory.get(new DAOLogin(), tl) == null ){
                    long idGeradoPessoa;
                    long idGeradoEndereco;
                    TOEndereco te = new TOEndereco();
                    TORelacaopa trpa = new TORelacaopa();
                    TOPropriedade tp = new TOPropriedade();
                    TOAgricultor ta = new TOAgricultor();
                    
                    //tabela endereco
                    
                    te.setCidade_idcidade(cidade_idcidade);
                    te.setRua(rua);
                    te.setGps_lat(gps_lat);
                    te.setGps_long(gps_long);
                    te.setBairro(bairro);
                    te.setComplemento(complemento);
                    te.setCep(cep);
                    te.setNumero(numero);
                    idGeradoEndereco = BOFactory.inserir(new DAOEndereco(), te);
                    
                    //tabela pessoa
                    t.setEndereco_idendereco(idGeradoEndereco);
                    t.setEscolaridade_idescolaridade(escolaridade_idescolaridade);
                    t.setEstadocivil_idestadocivil(estadocivil_idestadocivil);
                    t.setNome(nome);
                    t.setSobrenome(sobrenome);
                    t.setApelido(apelido);
                    t.setRg(rg);
                    t.setDatanascimento(datanascimento);
                    t.setSexo(sexo);
                    t.setTelefone1(telefone1);
                    t.setTelefone2(telefone2);
                    t.setEmail(email);
                    //grava informacoes no banco de dados
                    idGeradoPessoa = BOFactory.inserir(new DAOPessoa(), t);
                    
                    //tabela login
                    tl.setPessoa_idpessoa(idGeradoPessoa);
                    tl.setUnidade_idunidade(unidade_idunidade);
                    tl.setSenha(senha);
                    tl.setPapel("a");
                    //grava no banco de dados os dados da classe TOLogin
                    BOFactory.inserir(new DAOLogin(), tl);
                    
                    //tabela agricultores
                    ta.setPessoa_idpessoa(idGeradoPessoa);
                    ta.setQtdedeintegrantes(qtdedeintegrantes);
                    ta.setQtdedecriancas(qtdedecriancas);
                    ta.setQtdedegravidas(qtdedegravidas);
                    
                    BOFactory.inserir(new DAORelacaopa(), ta);
                    
                    //tabela propriedade
                    tp.setEndereco_idendereco(idGeradoEndereco);
                    tp.setUnidade_idunidade(unidade_idunidade);
                    tp.setNomepropriedade(nomepropriedade);
                    tp.setArea(area);
                    tp.setUnidadedemedida(unidadedemedida);
                    tp.setAreautilizavel(areautilizavel);
                    tp.setUnidadedemedidaau(unidadedemedidaau);
                    
                    //tabela relacao agricultor propriedade
                    trpa.setPropriedade_idpropriedade(BOFactory.inserir(new DAOPropriedade(), tp));
                    trpa.setAgricultor_pessoa_idpessoa(idGeradoPessoa);
                    //grava no banco de dados a propriedade e popula a classe Relacaopa
                    BOFactory.inserir(new DAORelacaopa(), trpa, "agricultor");
                    

                    j.put("sucesso", true);
                    j.put("mensagem", "Agricultor cadastrado!");
                
                //mensagen de usuario ja existente
                }else{
                    j.put("sucesso", false);
                    j.put("mensagem", "Erro, usuário já cadastrado!");
                }  
            //mensagen de usuario ja existente
            }else{
                j.put("sucesso", false);
                j.put("mensagem", "Erro, cpf já cadastrado!");
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
    public String login(@FormParam("usuario") String usuario,
                        @FormParam("senha") String senha
                        ) throws Exception{
        
        //objeto de retorno da requisicao
        JSONObject j = new JSONObject();
        
        try{
            TOLogin to = new TOLogin();
            to.setUsuario(usuario);
            to.setSenha(senha);
            
            
            to = (TOLogin) BOFactory.get(new DAOLogin(), to);
            
            if(to == null){
                j.put("sucesso", false);
                j.put("messangem", "Usuário não encontrado");
            }else{
                //gera um idsessao e cria um novo registro
                TOSessao ts = new TOSessao();
                SecureRandom random = new SecureRandom();     
        
                ts.setLogin_idlogin(to.getIdlogin());
                ts.setSessao(new BigInteger(130, random).toString(32));
                
             
                
                BOFactory.inserir(new DAOSessao(), ts);
                
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
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String listarPropriedades(
                        @FormParam("idpessoa") long idpessoa,
                        @FormParam("sessao") String sessao
                        ) throws Exception{
        
        JSONObject j = new JSONObject();
 
        try{
            TOLogin t = new TOLogin();
            t.setPessoa_idpessoa(idpessoa);
            
            
            JSONArray ja = BOFactory.listar(new DAOPropriedade(), t, "listarpropriedades") ;
            
            if(ja.length() > 0){
                j.put("propriedades", ja);
                j.put("sucesso", true);
            }else{
                j.put("sucesso", false);
                j.put("mensagem", "Usuario sem propriedade");
            }
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString();
    } 

    
    
}