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
public class TOEstado extends TOBase{
    private long idestado;
    
    private long pais_idpais;
    
    private String nomeestado;

    public long getIdestado() {
        return idestado;
    }

    public void setIdestado(long idestado) {
        this.idestado = idestado;
    }

    public long getPais_idpais() {
        return pais_idpais;
    }

    public void setPais_idpais(long pais_idpais) {
        this.pais_idpais = pais_idpais;
    }

    public String getNomeestado() {
        return nomeestado;
    }

    public void setNomeestado(String nomeestado) {
        this.nomeestado = nomeestado;
    }

    public TOEstado() {
    }

    
    
    public TOEstado(long idestado, long pais_idpais, String nomeestado) {
        this.idestado = idestado;
        this.pais_idpais = pais_idpais;
        this.nomeestado = nomeestado;
    }
    
    
    
    public TOEstado (ResultSet rs) throws Exception{
        this.idestado = rs.getLong("idestado");
        this.pais_idpais = rs.getLong("pais_idpais");
        this.nomeestado = rs.getString("nomeestado");
    }
    
    @Override
    public JSONObject getJson() throws Exception {
               //variavel tipo json para retornar no metodo
        JSONObject j = new JSONObject();
        
        //populando o objeto j
        j.put("idestado", idestado);
        j.put("pais_idpais", pais_idpais);
        j.put("nomeestado", nomeestado);
        
        return j;
    }
}
