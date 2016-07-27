/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package to;

import java.sql.ResultSet;
import org.json.JSONObject;

/**
 *
 * @author Aimee
 */
public class TORelacaopa extends TOBase {
    private long agricultor_pessoa_idpessoa;
    
    private long propriedade_idpropriedade;

    public long getAgricultor_pessoa_idpessoa() {
        return agricultor_pessoa_idpessoa;
    }

    public void setAgricultor_pessoa_idpessoa(long agricultor_pessoa_idpessoa) {
        this.agricultor_pessoa_idpessoa = agricultor_pessoa_idpessoa;
    }

    public long getPropriedade_idpropriedade() {
        return propriedade_idpropriedade;
    }

    public void setPropriedade_idpropriedade(long propriedade_idpropriedade) {
        this.propriedade_idpropriedade = propriedade_idpropriedade;
    }

    public TORelacaopa() {
    }

    public TORelacaopa(long agricultor_pessoa_idpessoa, long propriedade_idpropriedade) {
        this.agricultor_pessoa_idpessoa = agricultor_pessoa_idpessoa;
        this.propriedade_idpropriedade = propriedade_idpropriedade;
    }
    
     public TORelacaopa(ResultSet rs) throws Exception{
        this.agricultor_pessoa_idpessoa = rs.getLong("agricultor_pessoa_idpessoa");
        this.propriedade_idpropriedade = rs.getLong("propriedade_idpropriedade");
        
    }
    @Override
    public JSONObject getJson() throws Exception {
         //variavel para retorno do json contendo as informacoes do produto
        JSONObject j = new JSONObject();
        
        //populando o objeto j
        j.put("agricultor_pessoa_idpessoa", agricultor_pessoa_idpessoa);
        j.put("propriedade_idpropriedade", propriedade_idpropriedade);
        
        return j;
    }
}
