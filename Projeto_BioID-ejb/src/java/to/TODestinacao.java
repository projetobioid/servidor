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
    
    private double qtddestinada;

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

    public double getQtddestinada() {
        return qtddestinada;
    }

    public void setQtddestinada(double qtddestinada) {
        this.qtddestinada = qtddestinada;
    }

   
    
    public TODestinacao() {
    }


    public TODestinacao(ResultSet rs, String metodo)throws Exception{
        switch(metodo){
            default:
                this.datadestinada = rs.getString("");
                this.iddestinacao = rs.getLong("");
                this.qtddestinada = rs.getDouble("");
                this.safra_idsafra = rs.getLong("");
                this.tipodestinacao_idtipodestinacao = rs.getLong("");
                break;
            
        }
        
    }
    
    @Override
    public JSONObject getJson(String metodo) throws Exception {
         //variavel para retorno do json contendo as informacoes do produto
        JSONObject j = new JSONObject();
        
        switch(metodo){
            default:
            //populando o objeto j
            j.put("iddestinacao", iddestinacao);
            j.put("safrarelatada_safra_idsafra", safra_idsafra);
            j.put("tipodestinacao_idtipodestinacao", tipodestinacao_idtipodestinacao);
            j.put("datadestinada", datadestinada);
            j.put("qtddestinada", qtddestinada);
            break;
        }
        return j;
    }
}
