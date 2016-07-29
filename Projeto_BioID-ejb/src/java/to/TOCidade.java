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
public class TOCidade  extends TOBase{
    private long idcidade;
    
    private long estado_idestado;
    
    private String nomecidade;

    public long getIdcidade() {
        return idcidade;
    }

    public void setIdcidade(long idcidade) {
        this.idcidade = idcidade;
    }

    public long getEstado_idestado() {
        return estado_idestado;
    }

    public void setEstado_idestado(long estado_idestado) {
        this.estado_idestado = estado_idestado;
    }

    public String getNomecidade() {
        return nomecidade;
    }

    public void setNomecidade(String nomecidade) {
        this.nomecidade = nomecidade;
    }

    public TOCidade() {
    }

    
    
    public TOCidade(long idcidade, long estado_idestado, String nomecidade) {
        this.idcidade = idcidade;
        this.estado_idestado = estado_idestado;
        this.nomecidade = nomecidade;
    }
    
    
    
    public TOCidade (ResultSet rs) throws Exception{
        this.idcidade = rs.getLong("idcidade");
        this.estado_idestado = rs.getLong("estado_idestado");
        this.nomecidade = rs.getString("nomecidade");
    }
    
    @Override
    public JSONObject getJson() throws Exception {
               //variavel tipo json para retornar no metodo
        JSONObject j = new JSONObject();
        
        //populando o objeto j
        j.put("idcidade", idcidade);
        j.put("estado_idestado", estado_idestado);
        j.put("nomecidade", nomecidade);
        
        return j;
    }
    
}
