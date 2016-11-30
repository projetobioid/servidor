/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fw;

import bo.BOFactory;
import dao.DAOSessao;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;
import to.TOSessao;

/**
 *
 * @author daniel
 */
public class VerificarSessao {

    public JSONObject VerificarSessao(long id, String sessao) throws JSONException {
        
        JSONObject j = new JSONObject();
        try{ 
            //verificar sessao
            TOSessao ts = new TOSessao();
            ts.setSessao(sessao);
            ts.setLogin_idlogin(id);
            
            ts = (TOSessao) BOFactory.get(new DAOSessao(), ts);
            
            if(ts == null){
                j.put("sucesso", false);
            }else{
                SecureRandom random = new SecureRandom();
                
                ts.setNewSessao(new BigInteger(130, random).toString(32));
                BOFactory.editar(new DAOSessao(), ts);
                
                j.put("sucesso", true);
                j.put("sessao", ts.getNewSessao());
            }
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }   
        return j;
    }
    
}
