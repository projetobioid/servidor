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
public class TODestinacao extends TOBase{
    private long iddestinacao;
    
    private long safrarelatada_safra_idsafra;
    
    private long safrarelatada_idsafrarelatada;
    
    private long unidademedida_idunidademedida;
    
    private long tipodestinacao_idtipodestinacao;
    
    private String datadestinada;
    
    private float qtddestinada;

    public long getIddestinacao() {
        return iddestinacao;
    }

    public void setIddestinacao(long iddestinacao) {
        this.iddestinacao = iddestinacao;
    }

    public long getSafrarelatada_safra_idsafra() {
        return safrarelatada_safra_idsafra;
    }

    public void setSafrarelatada_safra_idsafra(long safrarelatada_safra_idsafra) {
        this.safrarelatada_safra_idsafra = safrarelatada_safra_idsafra;
    }

    public long getSafrarelatada_idsafrarelatada() {
        return safrarelatada_idsafrarelatada;
    }

    public void setSafrarelatada_idsafrarelatada(long safrarelatada_idsafrarelatada) {
        this.safrarelatada_idsafrarelatada = safrarelatada_idsafrarelatada;
    }

    public long getUnidademedida_idunidademedida() {
        return unidademedida_idunidademedida;
    }

    public void setUnidademedida_idunidademedida(long unidademedida_idunidademedida) {
        this.unidademedida_idunidademedida = unidademedida_idunidademedida;
    }

    public long getTipodestinacao_idtipodestinacao() {
        return tipodestinacao_idtipodestinacao;
    }

    public void setTipodestinacao_idtipodestinacao(long tipodestinacao_idtipodestinacao) {
        this.tipodestinacao_idtipodestinacao = tipodestinacao_idtipodestinacao;
    }

    public String getDatadestinada() {
        return datadestinada;
    }

    public void setDatadestinada(String datadestinada) {
        this.datadestinada = datadestinada;
    }

    public float getQtddestinada() {
        return qtddestinada;
    }

    public void setQtddestinada(float qtddestinada) {
        this.qtddestinada = qtddestinada;
    }

    
    public TODestinacao() {
    }

    public TODestinacao(long iddestinacao, long safrarelatada_safra_idsafra, long safrarelatada_idsafrarelatada, long unidademedida_idunidademedida, long tipodestinacao_idtipodestinacao, String datadestinada, float qtddestinada) {
        this.iddestinacao = iddestinacao;
        this.safrarelatada_safra_idsafra = safrarelatada_safra_idsafra;
        this.safrarelatada_idsafrarelatada = safrarelatada_idsafrarelatada;
        this.unidademedida_idunidademedida = unidademedida_idunidademedida;
        this.tipodestinacao_idtipodestinacao = tipodestinacao_idtipodestinacao;
        this.datadestinada = datadestinada;
        this.qtddestinada = qtddestinada;
    }

    
    
     public TODestinacao(ResultSet rs) throws Exception{
        this.iddestinacao = rs.getLong("iddestinacao");
        this.safrarelatada_safra_idsafra = rs.getLong("safrarelatada_safra_idsafra");
        this.safrarelatada_idsafrarelatada = rs.getLong("safrarelatada_idsafrarelatada");
        this.unidademedida_idunidademedida = rs.getLong("unidademedida_idunidademedida");
        this.tipodestinacao_idtipodestinacao = rs.getLong("tipodestinacao_idtipodestinacao");
        this.datadestinada = rs.getString("datadestinada");
        this.qtddestinada = rs.getFloat("qtddestinada");

    }

    @Override
    public JSONObject getJson() throws Exception {
         //variavel para retorno do json contendo as informacoes do produto
        JSONObject j = new JSONObject();
        
        //populando o objeto j
        j.put("iddestinacao", iddestinacao);
        j.put("safrarelatada_safra_idsafra", safrarelatada_safra_idsafra);
        j.put("safrarelatada_idsafrarelatada", safrarelatada_idsafrarelatada);
        j.put("unidademedida_idunidademedida", unidademedida_idunidademedida);
        j.put("tipodestinacao_idtipodestinacao", tipodestinacao_idtipodestinacao);
        j.put("datadestinada", datadestinada);
        j.put("qtddestinada", qtddestinada);

        return j;
    }
}
