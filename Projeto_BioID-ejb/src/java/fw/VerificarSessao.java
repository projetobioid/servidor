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
import to.TOSessao;

/**
 *
 * @author daniel
 */
public class VerificarSessao {

    public String VerificarSessao(long id, String sessao) throws Exception {

        TOSessao ts = new TOSessao();
        ts.setLogin_idlogin(id);
        ts.setSessao(sessao);

        ts = (TOSessao) BOFactory.get(new DAOSessao(), ts, "get_sessao");



        if(ts == null){
            return null;
        }else{
            SecureRandom random = new SecureRandom();
                String novaSessao = new BigInteger(130, random).toString(32);
                ts.setSessao(novaSessao);
                BOFactory.editar(new DAOSessao(), ts, "update_sessao"); 
                return novaSessao;
        }
    }

}
