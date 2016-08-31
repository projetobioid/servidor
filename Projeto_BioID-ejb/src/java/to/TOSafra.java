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
public class TOSafra extends TOBase {
    private long idsafra;
    
    private long unidademedida_idunidademedida;
    
    private long propriedade_idpropriedade;
     
    private long cultivar_idcultivar;
    
    private String safra;
    
    private String datareceb;
    
    private float qtdrecebida;
    
    private String ultimadatacolheita;
    
    private float qtdcolhida;
    
    //campo de amostragem
    private String grandeza_recebida;
    
    private String nomecultivar;
    
    private String nomepropriedade;
    
    private int tempodecolheita;
    
    private int tempodestinacao;

    public long getIdsafra() {
        return idsafra;
    }

    public void setIdsafra(long idsafra) {
        this.idsafra = idsafra;
    }

    public long getUnidademedida_idunidademedida() {
        return unidademedida_idunidademedida;
    }

    public void setUnidademedida_idunidademedida(long unidademedida_idunidademedida) {
        this.unidademedida_idunidademedida = unidademedida_idunidademedida;
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

    public float getQtdrecebida() {
        return qtdrecebida;
    }

    public void setQtdrecebida(float qtdrecebida) {
        this.qtdrecebida = qtdrecebida;
    }

    public String getUltimadatacolheita() {
        return ultimadatacolheita;
    }

    public void setUltimadatacolheita(String ultimadatacolheita) {
        this.ultimadatacolheita = ultimadatacolheita;
    }

    public float getQtdcolhida() {
        return qtdcolhida;
    }

    public void setQtdcolhida(float qtdcolhida) {
        this.qtdcolhida = qtdcolhida;
    }

    public String getGrandeza_recebida() {
        return grandeza_recebida;
    }

    public void setGrandeza_recebida(String grandeza_recebida) {
        this.grandeza_recebida = grandeza_recebida;
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
    
    
    public TOSafra() {
    }

    public TOSafra(long idsafra, long unidademedida_idunidademedida, long propriedade_idpropriedade, long cultivar_idcultivar, String safra, String datareceb, float qtdrecebida, String ultimadatacolheita, float qtdcolhida, String grandeza_recebida, String nomecultivar, String nomepropriedade, int tempodecolheita, int tempodestinacao) {
        this.idsafra = idsafra;
        this.unidademedida_idunidademedida = unidademedida_idunidademedida;
        this.propriedade_idpropriedade = propriedade_idpropriedade;
        this.cultivar_idcultivar = cultivar_idcultivar;
        this.safra = safra;
        this.datareceb = datareceb;
        this.qtdrecebida = qtdrecebida;
        this.ultimadatacolheita = ultimadatacolheita;
        this.qtdcolhida = qtdcolhida;
        this.grandeza_recebida = grandeza_recebida;
        this.nomecultivar = nomecultivar;
        this.nomepropriedade = nomepropriedade;
        this.tempodecolheita = tempodecolheita;
        this.tempodestinacao = tempodestinacao;
    }

    
    public TOSafra(ResultSet rs) throws Exception{
        this.idsafra = rs.getLong("idsafra");
        this.propriedade_idpropriedade = rs.getLong("propriedade_idpropriedade");
        this.cultivar_idcultivar = rs.getLong("cultivar_idcultivar");
        this.safra = rs.getString("safra");
        this.datareceb = rs.getString("datareceb");
        this.qtdrecebida = rs.getFloat("qtdrecebida");
        this.grandeza_recebida = rs.getString("grandeza_recebida");
        this.ultimadatacolheita = rs.getString("ultimadatacolheita");
        this.qtdcolhida = rs.getFloat("qtdcolhida");
        this.nomecultivar = rs.getString("nomecultivar");
        this.nomepropriedade = rs.getString("nomepropriedade");
        this.tempodecolheita = rs.getInt("tempodecolheita");
        this.tempodestinacao = rs.getInt("tempodestinacao");

    }

    @Override
    public JSONObject getJson() throws Exception {
         //variavel para retorno do json contendo as informacoes do produto
        JSONObject j = new JSONObject();

        //populando o objeto j
        j.put("idsafra", idsafra);
        j.put("propriedade_idpropriedade", propriedade_idpropriedade);
        j.put("cultivar_idcultivar", cultivar_idcultivar);
        j.put("safra", safra);
        j.put("datareceb", datareceb);
        j.put("qtdrecebida", qtdrecebida);
        j.put("grandeza_recebida", grandeza_recebida);
        j.put("ultimadatacolheita", ultimadatacolheita);
        j.put("qtdcolhida", qtdcolhida);
        j.put("nomecultivar", nomecultivar);
        j.put("nomepropriedade", nomepropriedade);
        j.put("tempodecolheita", tempodecolheita);
        j.put("tempodestinacao", tempodestinacao);
        
        return j;
    }



}
