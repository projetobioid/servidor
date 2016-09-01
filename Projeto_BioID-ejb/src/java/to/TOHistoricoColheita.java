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
public class TOHistoricoColheita extends TOBase{
    private long idhistoricocolheita;
    
    private long safra_idsafra;
    
    private String datacolheita;
    
    private float qtdcolhida;
    
    private float somaqtdcolhida;

    public long getIdhistoricocolheita() {
        return idhistoricocolheita;
    }

    public void setIdhistoricocolheita(long idhistoricocolheita) {
        this.idhistoricocolheita = idhistoricocolheita;
    }

    public long getSafra_idsafra() {
        return safra_idsafra;
    }

    public void setSafra_idsafra(long safra_idsafra) {
        this.safra_idsafra = safra_idsafra;
    }

    public String getDatacolheita() {
        return datacolheita;
    }

    public void setDatacolheita(String datacolheita) {
        this.datacolheita = datacolheita;
    }

    public float getQtdcolhida() {
        return qtdcolhida;
    }

    public void setQtdcolhida(float qtdcolhida) {
        this.qtdcolhida = qtdcolhida;
    }

    public float getSomaqtdcolhida() {
        return somaqtdcolhida;
    }

    public void setSomaqtdcolhida(float somaqtdcolhida) {
        this.somaqtdcolhida = somaqtdcolhida;
    }

        

    public TOHistoricoColheita() {
    }

    public TOHistoricoColheita(long idhistoricocolheita, long safra_idsafra, String datacolheita, float qtdcolhida, float somaqtdcolhida) {
        this.idhistoricocolheita = idhistoricocolheita;
        this.safra_idsafra = safra_idsafra;
        this.datacolheita = datacolheita;
        this.qtdcolhida = qtdcolhida;
        this.somaqtdcolhida = somaqtdcolhida;
    }

 


    public TOHistoricoColheita(ResultSet rs) throws Exception{
        //this.idhistoricocolheita = rs.getLong("idhistoricocolheita");
        //this.safra_idsafra = rs.getLong("safra_idsafra");
        //this.datacolheita = rs.getString("datacolheita");
        //this.qtdcolhida = rs.getFloat("qtdcolhida");
        this.somaqtdcolhida = rs.getFloat("somaqtdcolhida");

    }


    @Override
    public JSONObject getJson() throws Exception {
        //variavel para retorno do json contendo as informacoes do produto
        JSONObject j = new JSONObject();
        
        //populando o objeto j
        j.put("idhistoricocolheita", idhistoricocolheita);
        j.put("safra_idsafra", safra_idsafra);
        j.put("datacolheita", datacolheita);
        j.put("qtdcolhida", qtdcolhida);
        j.put("somaqtdcolhida", somaqtdcolhida);
        
        
        return j;
    }
    
}
