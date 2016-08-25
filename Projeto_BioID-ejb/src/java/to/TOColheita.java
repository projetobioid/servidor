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
public class TOColheita extends TOBase{
    public long idsafrarelatada;
    
    public long safra_idsafra;
    
    public long unidademedida_idunidademedida;
    
    public String datacolheita;
    
    public float qtdcolhida;
    
    //public long destinacao_iddestinacao;

    public long getIdsafrarelatada() {
        return idsafrarelatada;
    }

    public void setIdsafrarelatada(long idsafrarelatada) {
        this.idsafrarelatada = idsafrarelatada;
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


    public TOColheita() {
    }

    public TOColheita(long idsafrarelatada, long safra_idsafra, long unidademedida_idunidademedida, String datacolheita, float qtdcolhida) {
        this.idsafrarelatada = idsafrarelatada;
        this.safra_idsafra = safra_idsafra;
        this.unidademedida_idunidademedida = unidademedida_idunidademedida;
        this.datacolheita = datacolheita;
        this.qtdcolhida = qtdcolhida;
    }

    
    
    public TOColheita(ResultSet rs) throws Exception{
        this.idsafrarelatada = rs.getLong("idsafrarelatada");
        this.safra_idsafra = rs.getLong("safra_idsafra");
        this.datacolheita = rs.getString("datacolheita");
        this.qtdcolhida = rs.getFloat("qtdcolhida");
        this.unidademedida_idunidademedida = rs.getLong("unidademedida_idunidademedida");
        //this.destinacao_iddestinacao = rs.getLong("destinacao_iddestinacao");
    }

    @Override
    public JSONObject getJson() throws Exception {
        //variavel para retorno do json contendo as informacoes do produto
        JSONObject j = new JSONObject();
        
        //populando o objeto j
        j.put("idsafrarelatada", idsafrarelatada);
        j.put("safra_idsafra", safra_idsafra);
        j.put("datacolheita", datacolheita);
        j.put("qtdcolhida", qtdcolhida);
        j.put("unidademedida_idunidademedida", unidademedida_idunidademedida);
        
        return j;
    }
    
}
