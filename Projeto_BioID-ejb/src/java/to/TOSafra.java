/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package to;

/**
 *
 * @author daniel
 */
public class TOSafra extends TOBase {
    private long idsafra;
    
    private long statussafra_idstatussafra;
    
    private long propriedade_idpropriedade;
     
    private long cultivar_idcultivar;
    
    private String safra;
    
    private String datareceb;
    
    private double qtdrecebida;
    
    private String ultimadatacolheita;
    
    private double qtdcolhida;
    
    private long status_entrevistador;
    

    public long getIdsafra() {
        return idsafra;
    }

    public void setIdsafra(long idsafra) {
        this.idsafra = idsafra;
    }

    public long getStatussafra_idstatussafra() {
        return statussafra_idstatussafra;
    }

    public void setStatussafra_idstatussafra(long statussafra_idstatussafra) {
        this.statussafra_idstatussafra = statussafra_idstatussafra;
    }

    public long getPropriedade_idpropriedade() {
        return propriedade_idpropriedade;
    }

    public void setPropriedade_idpropriedade(long propriedade_idpropriedade) {
        this.propriedade_idpropriedade = propriedade_idpropriedade;
    }

    public long getCultivar_idcultivar() {
        return cultivar_idcultivar;
    }

    public void setCultivar_idcultivar(long cultivar_idcultivar) {
        this.cultivar_idcultivar = cultivar_idcultivar;
    }

    public String getSafra() {
        return safra;
    }

    public void setSafra(String safra) {
        this.safra = safra;
    }

    public String getDatareceb() {
        return datareceb;
    }

    public void setDatareceb(String datareceb) {
        this.datareceb = datareceb;
    }

    public double getQtdrecebida() {
        return qtdrecebida;
    }

    public void setQtdrecebida(double qtdrecebida) {
        this.qtdrecebida = qtdrecebida;
    }

    public String getUltimadatacolheita() {
        return ultimadatacolheita;
    }

    public void setUltimadatacolheita(String ultimadatacolheita) {
        this.ultimadatacolheita = ultimadatacolheita;
    }

    public double getQtdcolhida() {
        return qtdcolhida;
    }

    public void setQtdcolhida(double qtdcolhida) {
        this.qtdcolhida = qtdcolhida;
    }

    public long getStatus_entrevistador() {
        return status_entrevistador;
    }

    public void setStatus_entrevistador(long status_entrevistador) {
        this.status_entrevistador = status_entrevistador;
    }

    
    public TOSafra() {
    }
    
}
