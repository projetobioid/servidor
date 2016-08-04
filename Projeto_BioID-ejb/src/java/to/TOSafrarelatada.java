/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package to;

import java.sql.ResultSet;
import java.util.Date;
import org.json.JSONObject;

/**
 *
 * @author daniel
 */
public class TOSafrarelatada extends TOBase{
    public long idsafrarelatada;
    
    public long safra_idsafra;
    
    public long destinacao_iddestinacao;
    
    public Date datacolheita;
    
    public float quantidade;
    
    public long unidademedida_idunidademedida;

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

    public long getDestinacao_iddestinacao() {
        return destinacao_iddestinacao;
    }

    public void setDestinacao_iddestinacao(long destinacao_iddestinacao) {
        this.destinacao_iddestinacao = destinacao_iddestinacao;
    }

    public Date getDatacolheita() {
        return datacolheita;
    }

    public void setDatacolheita(Date datacolheita) {
        this.datacolheita = datacolheita;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public long getUnidademedida_idunidademedida() {
        return unidademedida_idunidademedida;
    }

    public void setUnidademedida_idunidademedida(long unidademedida_idunidademedida) {
        this.unidademedida_idunidademedida = unidademedida_idunidademedida;
    }

    public TOSafrarelatada() {
    }

    public TOSafrarelatada(long idsafrarelatada, long safra_idsafra, long destinacao_iddestinacao, Date datacolheita, float quantidade, long unidademedida_idunidademedida) {
        this.idsafrarelatada = idsafrarelatada;
        this.safra_idsafra = safra_idsafra;
        this.destinacao_iddestinacao = destinacao_iddestinacao;
        this.datacolheita = datacolheita;
        this.quantidade = quantidade;
        this.unidademedida_idunidademedida = unidademedida_idunidademedida;
    }
    
    public TOSafrarelatada(ResultSet rs) throws Exception{
        this.idsafrarelatada = rs.getLong("idsafrarelatada");
        this.safra_idsafra = rs.getLong("safra_idsafra");
        this.destinacao_iddestinacao = rs.getLong("destinacao_iddestinacao");
        this.datacolheita = rs.getDate("datacolheita");
        this.quantidade = rs.getFloat("quantidade");
        this.unidademedida_idunidademedida = rs.getLong("unidademedida_idunidademedida");
        
    }

    @Override
    public JSONObject getJson() throws Exception {
        //variavel para retorno do json contendo as informacoes do produto
        JSONObject j = new JSONObject();
        
        //populando o objeto j
        j.put("idsafrarelatada", idsafrarelatada);
        j.put("safra_idsafra", safra_idsafra);
        j.put("destinacao_iddestinacao", destinacao_iddestinacao);
        j.put("datacolheita", datacolheita);
        j.put("quantidade", quantidade);
        j.put("unidademedida_idunidademedida", unidademedida_idunidademedida);
        
        return j;
    }
    
}
