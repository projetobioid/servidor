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
 * @author daniel
 */
public class TOPessoa extends TOBase{
    public String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TOPessoa() {
    }
    
    //retorna consulta do banco de dados tipo resultset
    public TOPessoa (ResultSet rs) throws Exception{
        
        this.nome = rs.getString("nome");
        
        
    }
    //classe sobrescrita de tobase
    @Override
    public JSONObject getJson() throws Exception {
        //variavel para retorno do json contendo as informacoes do produto
        JSONObject j = new JSONObject();
        
        //populando o objeto j
        
        j.put("nome", nome);
       
        
        return j;
    }
    
}
