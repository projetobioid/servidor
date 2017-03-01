/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package to;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import org.json.JSONObject;

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
    
    
    //campo de amostragem
    private String grandeza;
    
    private String nomecultivar;
    
    private String nomepropriedade;
    
    private int tempodecolheita;
    
    private int tempodestinacao;
    
    private String prazo_colheita;
    
    private String prazo_destinacao;
    
    private double qtddestinada; 
    
    private TOEndereco TOEndereco;
    

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

    public void setQtdcolhida(float qtdcolhida) {
        this.qtdcolhida = qtdcolhida;
    }

    public String getGrandeza() {
        return grandeza;
    }

    public void setGrandeza(String grandeza) {
        this.grandeza = grandeza;
    }

    public String getNomecultivar() {
        return nomecultivar;
    }

    public void setNomecultivar(String nomecultivar) {
        this.nomecultivar = nomecultivar;
    }

    public String getNomepropriedade() {
        return nomepropriedade;
    }

    public void setNomepropriedade(String nomepropriedade) {
        this.nomepropriedade = nomepropriedade;
    }

    public int getTempodecolheita() {
        return tempodecolheita;
    }

    public void setTempodecolheita(int tempodecolheita) {
        this.tempodecolheita = tempodecolheita;
    }

    public int getTempodestinacao() {
        return tempodestinacao;
    }

    public void setTempodestinacao(int tempodestinacao) {
        this.tempodestinacao = tempodestinacao;
    }

    public String getPrazo_colheita() {
        return prazo_colheita;
    }

    public void setPrazo_colheita(String prazo_colheita) {
        this.prazo_colheita = prazo_colheita;
    }

    public String getPrazo_destinacao() {
        return prazo_destinacao;
    }

    public void setPrazo_destinacao(String prazo_destinacao) {
        this.prazo_destinacao = prazo_destinacao;
    }

    public double getQtddestinada() {
        return qtddestinada;
    }

    public void setQtddestinada(float qtddestinada) {
        this.qtddestinada = qtddestinada;
    }

    public long getStatus_entrevistador() {
        return status_entrevistador;
    }

    public void setStatus_entrevistador(long status_entrevistador) {
        this.status_entrevistador = status_entrevistador;
    }

    
    
    public TOSafra() {
    }


    public TOSafra(ResultSet rs, String metodo) throws Exception{
        
        switch(metodo){
            case "backup_entrevista":
                this.propriedade_idpropriedade = rs.getLong("propriedade_idpropriedade");
                this.idsafra = rs.getLong("idsafra");
                this.safra = rs.getString("safra");
                this.nomecultivar = rs.getString("nomecultivar");
                this.qtdrecebida = rs.getFloat("qtdrecebida");
                this.grandeza = rs.getString("grandeza_recebida");
                this.datareceb = rs.getString("datareceb");
                break;
            case "NAO_RELATADAS":
                this.idsafra = rs.getLong("idsafra");
                this.cultivar_idcultivar = rs.getLong("cultivar_idcultivar");
                this.nomecultivar = rs.getString("nomecultivar");
                this.safra = rs.getString("safra");
                this.datareceb = rs.getString("datareceb");
                this.qtdrecebida = rs.getFloat("qtdrecebida");
                this.status_entrevistador = rs.getLong("status_entrevistador");
                this.grandeza = rs.getString("grandeza");

                
                
                break;
            default:
                this.idsafra = rs.getLong("idsafra");
                this.statussafra_idstatussafra = rs.getLong("statussafra_idstatussafra");     
                this.propriedade_idpropriedade = rs.getLong("propriedade_idpropriedade");
                this.safra = rs.getString("safra");
                this.datareceb = rs.getString("datareceb");
                this.qtdrecebida = rs.getFloat("qtdrecebida");
                this.qtddestinada = rs.getFloat("qtddestinada");
                this.grandeza = rs.getString("grandeza_recebida");
                this.qtdcolhida = rs.getFloat("qtdcolhida");
                this.nomecultivar = rs.getString("nomecultivar");
                this.nomepropriedade = rs.getString("nomepropriedade");
                this.tempodecolheita = rs.getInt("tempodecolheita");
                this.tempodestinacao = rs.getInt("tempodestinacao");
                break;
        }
        
           
    }

    
    @Override
    public JSONObject getJson(String metodo) throws Exception {
         //variavel para retorno do json contendo as informacoes do produto
        JSONObject j = new JSONObject();

        switch(metodo){
            case "backup_entrevista":
                j.put("idcultivar", cultivar_idcultivar);
                j.put("nomecultivar", nomecultivar);
                j.put("safra", safra);
                
                j.put("qtdrecebida", qtdrecebida);
                j.put("datareceb", datareceb);
                j.put("grandeza_recebida", grandeza);
                
                break;
            case "NAO_RELATADAS":
                BigDecimal bd = new BigDecimal(qtdrecebida).setScale(2, RoundingMode.HALF_EVEN);
                
                j.put("idsafra", idsafra);
                j.put("idcultivar", cultivar_idcultivar);
                j.put("nomecultivar", nomecultivar);
                j.put("safra", safra);
                j.put("datareceb", datareceb);
                j.put("qtdrecebida", bd.doubleValue());
                j.put("status_entrevistador", status_entrevistador);
                j.put("grandeza", grandeza);
                
                break;
            default:
        
                //populando o objeto j
                j.put("idsafra", idsafra);
                j.put("statussafra_idstatussafra", statussafra_idstatussafra);
                j.put("propriedade_idpropriedade", propriedade_idpropriedade);
                j.put("safra", safra);
                j.put("datareceb", datareceb);
                j.put("qtddestinada", qtddestinada);
                j.put("qtdrecebida", qtdrecebida);
                j.put("grandeza_recebida", grandeza);
                j.put("qtdcolhida", qtdcolhida);
                j.put("nomecultivar", nomecultivar);
                j.put("nomepropriedade", nomepropriedade);
                j.put("prazo_colheita", prazo_colheita);
                j.put("prazo_destinacao", prazo_destinacao);
                break;
        }
        return j;
    }


}
