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
    private long idcolheita;
    
    private long safra_idsafra;
    
    private long unidademedida_idunidademedida;
    
    private String datacolheita;
    
    private float qtdcolhida;
    
    //dados de amostragem
    private String um_colhida;
    

    public long getIdcolheita() {
        return idcolheita;
    }

    public void setIdcolheita(long idcolheita) {
        this.idcolheita = idcolheita;
    }

    public long getSafra_idsafra() {
        return safra_idsafra;
    }

    public void setSafra_idsafra(long safra_idsafra) {
        this.safra_idsafra = safra_idsafra;
    }

    public long getUnidademedida_idunidademedida() {
        return unidademedida_idunidademedida;
    }

    public void setUnidademedida_idunidademedida(long unidademedida_idunidademedida) {
        this.unidademedida_idunidademedida = unidademedida_idunidademedida;
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

    public String getUm_colhida() {
        return um_colhida;
    }

    public void setUm_colhida(String um_colhida) {
        this.um_colhida = um_colhida;
    }

    

    public TOHistoricoColheita() {
    }

    public TOHistoricoColheita(long idcolheita, long safra_idsafra, long unidademedida_idunidademedida, String datacolheita, float qtdcolhida, String um_colhida) {
        this.idcolheita = idcolheita;
        this.safra_idsafra = safra_idsafra;
        this.unidademedida_idunidademedida = unidademedida_idunidademedida;
        this.datacolheita = datacolheita;
        this.qtdcolhida = qtdcolhida;
        this.um_colhida = um_colhida;
    }
    

    public TOHistoricoColheita(ResultSet rs) throws Exception{
        this.idcolheita = rs.getLong("idcolheita");
        this.safra_idsafra = rs.getLong("safra_idsafra");
        this.datacolheita = rs.getString("datacolheita");
        this.qtdcolhida = rs.getFloat("qtdcolhida");
        this.unidademedida_idunidademedida = rs.getLong("unidademedida_idunidademedida");

    }

    @Override
    public JSONObject getJson() throws Exception {
        //variavel para retorno do json contendo as informacoes do produto
        JSONObject j = new JSONObject();
        
        //populando o objeto j
        j.put("idcolheita", idcolheita);
        j.put("safra_idsafra", safra_idsafra);
        j.put("datacolheita", datacolheita);
        j.put("qtdcolhida", qtdcolhida);
        j.put("unidademedida_idunidademedida", unidademedida_idunidademedida);
        
        
        return j;
    }
    
}
