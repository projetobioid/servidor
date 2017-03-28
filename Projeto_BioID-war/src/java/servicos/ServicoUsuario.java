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
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONObject;

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
        List<Object> u = new ArrayList<Object>();
        
        try{
            JSONArray data = null;
            
            switch(k.getString("metodo")){
                case "TODOS_DA_UNIDADE":
                case "EQUIPE":
                    u.add(k.getLong("idunidade"));
                    data = BOFactory.listar(new DAOPessoa(), u, k.getString("metodo")) ; 
                    break;
                case "USUARIOS":
                    data = BOFactory.listar(new DAOPessoa(), k.getString("metodo")) ; 
                    break;
                default:
                    data = null;
                    break;
            }
            

            

            if(data == null){
                j.put("sucesso", false);
                j.put("mensagem", "Sem usuários cadastrados!");

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
    
    @Path("inserir")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String inserirAgricultor(String dataJson) throws Exception{
        
        JSONObject j = new JSONObject();
        JSONObject k = new JSONObject(dataJson);
        List<Object> u = new ArrayList<Object>();
        
        try{

            //popula objetos e verifica se existe o cpf e usuario cadastrados no banco
            u.add(k.getString("cpf"));

            //se nao existe cpf cadastrado no banco, prosegue o cadastro
            if(BOFactory.buscar(new DAOPessoa(), u, "BUSCA_POR_CPF") == null ){
                u.clear();
                u.add(k.getString("usuario_login"));
                
                //teste se existe o usuario cadastrado
                if(BOFactory.buscar(new DAOLogin(), u) == null ){
                    
                    long idGerado;
                    
                    //tabela endereco
                    u.clear();
                    u.add(k.getLong("cidade_idcidade"));
                    u.add(k.getString("rua"));
                    u.add(k.getInt("gps_lat"));
                    u.add(k.getInt("gps_long"));
                    u.add(k.getString("bairro"));
                    u.add(k.getString("complemento"));
                    u.add(k.getString("cep"));
                    u.add(k.getInt("numero"));
                    
                    //salva o endereco no banco de dados
                    idGerado = BOFactory.inserir(new DAOEndereco(), u);
                    
                    //tabela pessoa
                    u.clear();
                    u.add(k.getLong("estadocivil_idestadocivil"));
                    u.add(k.getLong("escolaridade_idescolaridade"));
                    u.add(idGerado);
                    u.add(k.getString("nome"));
                    u.add(k.getString("sobrenome"));
                    u.add(k.getString("apelido"));
                    u.add(k.getString("cpf"));
                    u.add(k.getString("rg"));
                    u.add(k.getString("datanascimento"));
                    u.add(k.getString("sexo"));
                    u.add(k.getString("telefone1"));
                    u.add(k.getString("telefone2"));
                    u.add(k.getString("email"));
                    //grava informacoes no banco de dados
                    idGerado = BOFactory.inserir(new DAOPessoa(), u);
                    //tabela login
                    u.clear();
                    u.add(idGerado);
                    u.add(k.getLong("unidade_idunidade"));
                    u.add(k.getString("usuario_login"));
                    u.add(k.getString("senha"));
                    //grava no banco de dados os dados da classe TOLogin
                    BOFactory.inserirIDString(new DAOLogin(), u);

                    //tabela grupos
                    u.clear();
                    u.add(k.getString("usuario_login"));
                    u.add(k.getString("grupo"));
                    BOFactory.inserirIDString(new DAOGrupos(), u);

                    j.put("sucesso", true);
                    j.put("mensagem", "Cadastro com sucesso!");
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
    
    @Path("buscar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String buscar(String dataJson) throws Exception{
        
        JSONObject j = new JSONObject();
        JSONObject k = new JSONObject(dataJson);
        List<Object> u = new ArrayList<Object>();
        
        try{

            u.add(k.getLong("idpessoa"));
          
            JSONObject data = BOFactory.buscar(new DAOPessoa(), u, k.getString("metodo"));
                
                
            if(data == null){
                j.put("sucesso", false);
                j.put("mensagem", " Usuário não encontrado!");

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
}
