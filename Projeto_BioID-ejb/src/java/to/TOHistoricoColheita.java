/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package to;

/**
 *
 * @author Daniel
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
    
}
