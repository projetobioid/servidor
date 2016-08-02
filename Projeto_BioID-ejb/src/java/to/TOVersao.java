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
public class TOVersao extends TOBase{
    private long idversao;
    
    private String descricao;

    public long getIdversao() {
        return idversao;
    }

    public void setIdversao(long idversao) {
        this.idversao = idversao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TOVersao() {
    }

    public TOVersao(long idversao, String descricao) {
        this.idversao = idversao;
        this.descricao = descricao;
    }
    
     public TOVersao(ResultSet rs) throws Exception{
        this.idversao = rs.getLong("idversao");
        this.descricao = rs.getString("descricao");

    }

    @Override
    public JSONObject getJson() throws Exception {
         //variavel para retorno do json contendo as informacoes do produto
        JSONObject j = new JSONObject();
        
        //populando o objeto j
        j.put("idversao", idversao);
        j.put("descricao", descricao);

        return j;
    }
}
