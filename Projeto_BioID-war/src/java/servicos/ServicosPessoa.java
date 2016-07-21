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
import to.TOEndereco;
import to.TOLogin;
import to.TOPessoa;

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
    @GET
    @Path("listar")
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
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("inserir")
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
            //tabela pessoa
            @FormParam("escolaridade_idescolaridade") long escolaridade_idescolaridade,
            @FormParam("estadocivil_idestadocivil") long estadocivil_idestadocivil,
            @FormParam("nome") String nome,
            @FormParam("sobrenome") String sobrenome,
            @FormParam("apelido") String apelido,
            @FormParam("cpf") String cpf,
            @FormParam("rg") String rg,
            //@FormParam("datanascimento") String datanascimento,
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
                    long idGerado;
                    //objeto TOEndereco
                    TOEndereco te = new TOEndereco();
                    te.setCidade_idCidade(cidade_idcidade);
                    te.setRua(rua);
                    te.setGps_Lat(gps_lat);
                    te.setGps_Log(gps_log);
                    te.setBairro(bairro);
                    te.setComplemento(complemento);
                    te.setCep(cep);
                    te.setNumero(numero);
                    //grava no banco de dados os dados da classe TOLogin e retorna o id gerado
                    idGerado = BOFactory.inserir(new DAOEndereco(), te);

                    //popula a classe
                    t.setEndereco_idendereco(idGerado);
                    t.setEscolaridade_idescolaridade(escolaridade_idescolaridade);
                    t.setEstadocivil_idestadocivil(estadocivil_idestadocivil);
                    t.setNome(nome);
                    t.setSobrenome(sobrenome);
                    t.setApelido(apelido);
                    t.setRg(rg);
                    //t.setDatanascimento(datanascimento);
                    t.setSexo(sexo);
                    t.setTelefone1(telefone1);
                    t.setTelefone2(telefone2);
                    t.setEmail(email);
                    //grava no banco de dados os dados da classe TOPessoa e retorna o id gerado
                    idGerado = BOFactory.inserir(new DAOPessoa(), t);

                    //popula a classe

                    tl.setUnidade_idunidade(unidade_idunidade);
                    tl.setPessoa_idpessoa(idGerado);
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
                j.put("erro", 2);
                j.put("mensagem", "CPF ja cadastrado!");
            }
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
            
        }
        
        return j.toString();
    }
    
    //edita um usuario no banco de dados
    @POST
    @Path("editar")
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
    @POST
    @Path("excluir")
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
    
    
    
    
    
    
    
    
    
     //adicionar usuario no sistema
    @POST
    @Path("teste")
    public String teste(
            @FormParam("id") int id, @FormParam("nome") String nome) throws Exception{
        

        
        JSONObject j = new JSONObject();
        
        try{
            TOPessoa t = new TOPessoa();
            //popula a classe
            t.setId(id);
            t.setNome(nome);
            
            
            //grava no banco de dados os dados da classe TOUsuario
            BOFactory.inserir(new DAOPessoa(), t);
            
            j.put("sucesso", true);
            
            
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
            
        }
        
        return j.toString();
    }
    
    
    
    
    
    
    
}