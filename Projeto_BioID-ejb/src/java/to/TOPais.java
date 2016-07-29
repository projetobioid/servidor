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
public class TOPais extends TOBase{
    private long idpais;
    
    private String nomepais;

    public long getIdpais() {
        return idpais;
    }

    public void setIdpais(long idpais) {
        this.idpais = idpais;
    }

    public String getNomepais() {
        return nomepais;
    }

    public void setNomepais(String nomepais) {
        this.nomepais = nomepais;
    }

    public TOPais() {
    }

    public TOPais(long idpais, String nomepais) {
        this.idpais = idpais;
        this.nomepais = nomepais;
    }

    public TOPais  (ResultSet rs) throws Exception{
        this.idpais = rs.getLong("idpais");
        this.nomepais = rs.getString("nomepais");
    }
    @Override
    public JSONObject getJson() throws Exception {
               //variavel tipo json para retornar no metodo
        JSONObject j = new JSONObject();
        
        //populando o objeto j
        j.put("idpais", idpais);
        j.put("nomepais", nomepais);
        
        return j;
    }
    
    
}
