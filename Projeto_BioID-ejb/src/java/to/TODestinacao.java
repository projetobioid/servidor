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
    
    private long safra_idsafra;
        
    private long tipodestinacao_idtipodestinacao;
    
    private String datadestinada;
    
    private float qtddestinada;

    public long getIddestinacao() {
        return iddestinacao;
    }

    public void setIddestinacao(long iddestinacao) {
        this.iddestinacao = iddestinacao;
    }

    public long getSafra_idsafra() {
        return safra_idsafra;
    }

    public void setSafra_idsafra(long safra_idsafra) {
        this.safra_idsafra = safra_idsafra;
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

    public TODestinacao(long iddestinacao, long safra_idsafra, long unidademedida_idunidademedida, String datadestinada, float qtddestinada) {
        this.iddestinacao = iddestinacao;
        this.safra_idsafra = safra_idsafra;
        this.tipodestinacao_idtipodestinacao = tipodestinacao_idtipodestinacao;
        this.datadestinada = datadestinada;
        this.qtddestinada = qtddestinada;
    }

    
    
     public TODestinacao(ResultSet rs) throws Exception{
        this.iddestinacao = rs.getLong("iddestinacao");
        this.safra_idsafra = rs.getLong("safrarelatada_safra_idsafra");
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
        j.put("safrarelatada_safra_idsafra", safra_idsafra);
        j.put("tipodestinacao_idtipodestinacao", tipodestinacao_idtipodestinacao);
        j.put("datadestinada", datadestinada);
        j.put("qtddestinada", qtddestinada);

        return j;
    }
}
