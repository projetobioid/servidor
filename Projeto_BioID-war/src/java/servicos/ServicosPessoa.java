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
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class ServicosPessoa {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServicoPessoa
     */
    public ServicosPessoa() {
    }

    
    //serviço de listar os usuarios
    @Path("listar")
    @GET
    public String listar() throws Exception{
    
        JSONObject j = new JSONObject();
        
        try{
            //cria um jsonarray e popula com uma consulta no banco
            JSONArray ja = BOFactory.listar(new DAOPessoa());
            
            j.put("Data", ja);
            j.put("sucesso", true);
            
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        
        return j.toString();
    }
     //adicionar usuario no sistema
    @Path("inserir")
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
            @FormParam("unidade_idunidade") long unidade_idunidade
            

            ) throws Exception{

        

        JSONObject j = new JSONObject();
        
        try{
            
            //cria objetos
            TOPessoa t = new TOPessoa();
            TOLogin tl = new TOLogin();
            
            //popula objetos e verifica se existe o cpf e usuario cadastrados no banco
            t.setCpf(cpf);
            tl.setUsuario(usuario);
            
            //se nao existe cpf nem usuario eh cadastrado no banco
            if(BOFactory.get(new DAOPessoa(), t) == null ){
                if(BOFactory.get(new DAOLogin(), tl) == null ){
                   
                    //objeto TOEndereco
                    TOEndereco te = new TOEndereco();
                    te.setCidade_idcidade(cidade_idcidade);
                    te.setRua(rua);
                    te.setGps_lat(gps_lat);
                    te.setGps_long(gps_long);
                    te.setBairro(bairro);
                    te.setComplemento(complemento);
                    te.setCep(cep);
                    te.setNumero(numero);

                    //popula a classe TOPessoa
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


                    //popula a classe TOLogin
                    tl.setPessoa_idpessoa(BOFactory.inserir(new DAOPessoa(), t));
                    tl.setUnidade_idunidade(unidade_idunidade);
                    tl.setSenha(senha);
                    tl.setPapel(papel);
                    //grava no banco de dados os dados da classe TOLogin
                    BOFactory.inserir(new DAOLogin(), tl);

                        

                    j.put("sucesso", true);
                    j.put("mensagem", "Usuario cadastrado!");
                //mensagen de cpf ja existente
                }else{
                    j.put("sucesso", false);
                    j.put("erro", 1);
                    j.put("mensagem", "Usuario ja existe no sistema!");
                }
            //mensagen de usuario ja existente
            }else{
                j.put("sucesso", false);
                j.put("erro", 3);
                j.put("mensagem", "CPF ja cadastrado!");
            }
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
            
        }
        
        return j.toString();
    }
    
     //adicionar usuario no sistema
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
            @FormParam("papel") String papel,
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
            TOLogin tl = new TOLogin();
            TOAgricultor ta = new TOAgricultor();
            TOPropriedade tp = new TOPropriedade();
            TORelacaopa tr = new TORelacaopa();
            
            //popula objetos e verifica se existe o cpf e usuario cadastrados no banco
            t.setCpf(cpf);
            tl.setUsuario(usuario);
            tp.setNomepropriedade(nomepropriedade);
            
            //se nao existe cpf nem usuario eh cadastrado no banco
            if(BOFactory.get(new DAOPessoa(), t) == null ){
                if(BOFactory.get(new DAOLogin(), tl) == null ){
                    if(BOFactory.get(new DAOPropriedade(), tp) == null ){
                        long idGerado;
                        //objeto TOEndereco
                        TOEndereco te = new TOEndereco();
                        te.setCidade_idcidade(cidade_idcidade);
                        te.setRua(rua);
                        te.setGps_lat(gps_lat);
                        te.setGps_long(gps_long);
                        te.setBairro(bairro);
                        te.setComplemento(complemento);
                        te.setCep(cep);
                        te.setNumero(numero);
                        //grava no banco de dados os dados da classe TOLogin e retorna o id gerado
                        idGerado = BOFactory.inserir(new DAOEndereco(), te);
                        //grava o idGerado do endereco na TOPropriedade
                        tp.setEndereco_idendereco(idGerado);


                        //popula a classe TOPessoa
                        t.setEndereco_idendereco(idGerado);
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
                        //grava no banco de dados os dados da classe TOPessoa e retorna o id gerado
                        idGerado = BOFactory.inserir(new DAOPessoa(), t);
                        tr.setAgricultor_pessoa_idpessoa(idGerado);

                        //popula a classe TOAgricultor
                        ta.setPessoa_idpessoa(idGerado);
                        ta.setQtdedeintegrantes(qtdedeintegrantes);
                        ta.setQtdedecriancas(qtdedecriancas);
                        ta.setQtdedegravida(qtdedegravidas);                    
                        //grava no bando de dados os dados da classe TOAgricultor
                        BOFactory.inserir(new DAOAgricultor(), ta);

                        //popula a classe TOLogin
                        tl.setPessoa_idpessoa(idGerado);
                        tl.setUnidade_idunidade(unidade_idunidade);
                        tl.setSenha(senha);
                        tl.setPapel(papel);
                        //grava no banco de dados os dados da classe TOLogin
                        BOFactory.inserir(new DAOLogin(), tl);

                        //popula a classe TOPropriedade
                        tp.setUnidade_idunidade(unidade_idunidade);
                        tp.setNomepropriedade(nomepropriedade);
                        tp.setArea(area);
                        tp.setUnidadedemedida(unidadedemedida);
                        tp.setAreautilizavel(areautilizavel);
                        tp.setUnidadedemedidaau(unidadedemedidaau);
                        //grava no banco de dados a TOPropriedade
                        idGerado = BOFactory.inserir(new DAOPropriedade(), tp);

                        //popula a classe TORelacaopa
                        tr.setPropriedade_idpropriedade(idGerado);
                        BOFactory.inserir(new DAORelacaopa(), tr);

                        j.put("sucesso", true);
                        j.put("mensagem", "Usuario cadastrado!");
                    //mensagen de propriedade ja cadastrada
                    }else{
                        j.put("sucesso", false);
                        j.put("erro", 1);
                        j.put("mensagem", "Propriedade ja existe no sistema!");
                    }
                //mensagen de cpf ja existente
                }else{
                    j.put("sucesso", false);
                    j.put("erro", 2);
                    j.put("mensagem", "Usuario ja existe no sistema!");
                }
            //mensagen de usuario ja existente
            }else{
                j.put("sucesso", false);
                j.put("erro", 3);
                j.put("mensagem", "CPF ja cadastrado!");
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
            @FormParam("usuario") String usuario,
            @FormParam("senha") String senha,
            @FormParam("email") String email) throws Exception{
        
        JSONObject j = new JSONObject();
        
        try{
            TOPessoa t = new TOPessoa();
           // t.setId(id);
           
            t = (TOPessoa) BOFactory.get(new DAOPessoa(), t);
            if(t== null){
                j.put("sucesso", false);
                j.put("mensagem", "Usuário não encontrado");
            }else{
                //t.setUsuario(usuario);
               // t.setSenha(senha);
                t.setEmail(email);

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
    public String excluir(@FormParam("id") long id) throws Exception{
        //objeto de retorno da requisicao
        JSONObject j = new JSONObject();
        
        try{
            TOPessoa to = new TOPessoa();
            //to.setId(id);
            
            to = (TOPessoa) BOFactory.get(new DAOPessoa(), to);
            
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
                        @FormParam("senha") String senha) throws Exception{
        
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
                j.put("sucesso", true);
                
                j.put("usuario", to.getUsuario());
                j.put("papel", to.getPapel());
                j.put("idunidade", to.getUnidade_idunidade());
                //retorna uma senha
                SecureRandom random = new SecureRandom();
                j.put("idSession", new BigInteger(130, random).toString(32));
                //retorna a data de login que espirará em um tempo determinado
                j.put("logTempo", ((730 * Float.parseFloat(getData("M"))) - (730 - (Float.parseFloat(getData("d"))*24)))+168 );
            }
        }catch (Exception e){
            j.put("sucesso", false);
            j.put("messangem", e.getMessage());
        }
        
//        return j.toString();
        return j.toString();
    }
    

    private String getData(String modelo) {
	DateFormat dateFormat = new SimpleDateFormat(modelo);
	Date date = new Date();
	return dateFormat.format(date);

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
            @FormParam("cpf") String cpf
            
            )throws Exception{
        
        JSONObject j = new JSONObject();
        try{
            
            //objeto TOEndereco
            TOEndereco te = new TOEndereco();
            TOPropriedade tpd = new TOPropriedade();
            
            tpd.setNomepropriedade(nomepropriedade);
            tpd.setCpf(cpf);
            
            if(BOFactory.get(new DAOPropriedade(), tpd) == null ){
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
                ((TOPessoa) BOFactory.get(new DAOPessoa(), tp)).getIdpessoa();
                
                tr.setAgricultor_pessoa_idpessoa(((TOPessoa) BOFactory.get(new DAOPessoa(), tp)).getIdpessoa());

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
            @FormParam("usuario") String usuario)throws Exception{
        
        JSONObject j = new JSONObject();
 
        try{
            TOPropriedade t = new TOPropriedade();
            t.setUsuario(usuario);
            
            
            JSONArray ja = BOFactory.listar(new DAOPropriedade(), t) ;
            
            if(ja.length() > 0){
                j.put("data", ja);
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