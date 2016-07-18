/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import bo.BOFactory;
import dao.DAOLogin;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import org.json.JSONObject;
import to.TOLogin;

/**
 * REST Web Service
 *
 * @author daniel
 */
@Path("login")
public class ServicosLogin {

 //login
    @POST
    @Path("validacao")
    public String login(@FormParam("usuario") String usuario,
                        @FormParam("senha") String senha) throws Exception{
        
        //objeto de retorno da requisicao
        JSONObject j = new JSONObject();
        
        try{
            TOLogin to = new TOLogin();
            to.setUsuario(usuario);
            to.setSenha(senha);
            
            
            to = (TOLogin) BOFactory.getLogin(new DAOLogin(), to);
            
            if(to == null){
                j.put("sucesso", false);
                j.put("messangem", "Usuário não encontrado");
            }else{
                j.put("sucesso", true);
                
                j.put("usuario", to.getUsuario());
                j.put("papel", to.getPapel());
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
        
        return j.toString();
    }
    

    private String getData(String modelo) {
	DateFormat dateFormat = new SimpleDateFormat(modelo);
	Date date = new Date();
	return dateFormat.format(date);

    }
    
}