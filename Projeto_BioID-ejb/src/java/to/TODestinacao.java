/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package to;

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

}
