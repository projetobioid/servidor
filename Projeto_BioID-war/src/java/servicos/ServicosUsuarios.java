/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import bo.BOFactory;
import dao.DAOUsuarios;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import org.json.JSONArray;
import org.json.JSONObject;
import to.TOUsuarios;

/**
 * REST Web Service
 *
 * @author daniel
 */
@Path("usuarios")
public class ServicosUsuarios {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServicosUsuarios
     */
    public ServicosUsuarios() {
    }

    //serviço de listar os usuarios
    @GET
    @Path("listar")
    public String listar() throws Exception{
    
        JSONObject j = new JSONObject();
        
        try{
            //cria um jsonarray e popula com uma consulta no banco
            JSONArray ja = BOFactory.listar(new DAOUsuarios());
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
    @Path("inserir")
    public String inserir(
            @FormParam("id") String id,
            @FormParam("usuario") String usuario,
            @FormParam("senha") String senha,
            @FormParam("email") String email) throws Exception{
        
        JSONObject j = new JSONObject();
        
        try{
            TOUsuarios t = new TOUsuarios();
            //popula a classe
            t.setId(id);
            t.setUsuario(usuario);
            t.setSenha(senha);
            t.setEmail(email);
            
            //grava no banco de dados os dados da classe TOUsuarios
            BOFactory.inserir(new DAOUsuarios(), t);
            
            j.put("sucesso", true);
            
            
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
            
        }
        
        return j.toString();
    }
    
    //edita um usuario no banco de dados
    @POST
    @Path("editar")
    public String editar(@FormParam("id") String id,
            @FormParam("usuario") String usuario,
            @FormParam("senha") String senha,
            @FormParam("email") String email) throws Exception{
        
        JSONObject j = new JSONObject();
        
        try{
            TOUsuarios t = new TOUsuarios();
            t.setId(id);
           
            t = (TOUsuarios) BOFactory.get(new DAOUsuarios(), t);
            if(t== null){
                j.put("sucesso", false);
                j.put("mensagem", "Usuário não encontrado");
            }else{
                t.setUsuario(usuario);
                t.setSenha(senha);
                t.setEmail(email);

                BOFactory.editar(new DAOUsuarios(), t);
            
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
    public String excluir(@FormParam("id") String id) throws Exception{
        //objeto de retorno da requisicao
        JSONObject j = new JSONObject();
        
        try{
            TOUsuarios to = new TOUsuarios();
            to.setId(id);
            
            to = (TOUsuarios) BOFactory.get(new DAOUsuarios(), to);
            
            if(to == null){
                j.put("sucesso", false);
                j.put("messangem", "Usuário não encontrado");
            }else{
                BOFactory.excluir(new DAOUsuarios(), to);
                j.put("sucesso", true);
            }
        }catch (Exception e){
            j.put("sucesso", false);
            j.put("messangem", e.getMessage());
        }
        
        
        return j.toString();
        
    }
    
    //login
    @POST
    @Path("login")
    public String login(@FormParam("usuario") String usuario,
                        @FormParam("senha") String senha) throws Exception{
        
        //objeto de retorno da requisicao
        JSONObject j = new JSONObject();
        try{
            TOUsuarios to = new TOUsuarios();
            to.setUsuario(usuario);
            to.setSenha(senha);
            
            to = (TOUsuarios) BOFactory.getLogin(new DAOUsuarios(), to);
            
            if(to == null){
                j.put("sucesso", false);
                j.put("messangem", "Usuário não encontrado");
            }else{
                j.put("sucesso", true);
                
                j.put("usuario", to.getUsuario());
                j.put("email", to.getEmail());
                
            }
        }catch (Exception e){
            j.put("sucesso", false);
            j.put("messangem", e.getMessage());
        }
        
        return j.toString();
    }
    
}
