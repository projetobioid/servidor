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
    
    private long statussafra_idstatussafra;
    
    private long unidademedida_idunidademedida;
    
    private long propriedade_idpropriedade;
     
    private long cultivar_idcultivar;
    
    private String safra;
    
    private String datareceb;
    
    private float qtdrecebida;
       
    //campos do result set
    
    private String grandeza_safra;
    
    private String grandeza_cultivar;
    
    private int tempodecolheita;
    
    private String nomecultivar;
    
    private String nomepropriedade;
    
    private String descricaostatus;
    
    //campos de consulta
    private String usuario;

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

    public String getGrandeza_safra() {
        return grandeza_safra;
    }

    public void setGrandeza_safra(String grandeza_safra) {
        this.grandeza_safra = grandeza_safra;
    }

    public String getGrandeza_cultivar() {
        return grandeza_cultivar;
    }

    public void setGrandeza_cultivar(String grandeza_cultivar) {
        this.grandeza_cultivar = grandeza_cultivar;
    }

    public int getTempodecolheita() {
        return tempodecolheita;
    }

    public void setTempodecolheita(int tempodecolheita) {
        this.tempodecolheita = tempodecolheita;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getDescricaostatus() {
        return descricaostatus;
    }

    public void setDescricaostatus(String descricaostatus) {
        this.descricaostatus = descricaostatus;
    }


    
    public TOSafra() {
    }

    public TOSafra(long idsafra, long statussafra_idstatussafra, long unidademedida_idunidademedida, long propriedade_idpropriedade, long cultivar_idcultivar, String safra, String datareceb, float qtdrecebida, String grandeza_safra, String grandeza_cultivar, int tempodecolheita, String nomecultivar, String nomepropriedade, String descricaostatus, String usuario) {
        this.idsafra = idsafra;
        this.statussafra_idstatussafra = statussafra_idstatussafra;
        this.unidademedida_idunidademedida = unidademedida_idunidademedida;
        this.propriedade_idpropriedade = propriedade_idpropriedade;
        this.cultivar_idcultivar = cultivar_idcultivar;
        this.safra = safra;
        this.datareceb = datareceb;
        this.qtdrecebida = qtdrecebida;
        this.grandeza_safra = grandeza_safra;
        this.grandeza_cultivar = grandeza_cultivar;
        this.tempodecolheita = tempodecolheita;
        this.nomecultivar = nomecultivar;
        this.nomepropriedade = nomepropriedade;
        this.descricaostatus = descricaostatus;
        this.usuario = usuario;
    }


         
    public TOSafra(ResultSet rs) throws Exception{
        this.idsafra = rs.getLong("idsafra");
        this.propriedade_idpropriedade = rs.getLong("propriedade_idpropriedade");
        this.cultivar_idcultivar = rs.getLong("cultivar_idcultivar");
        this.safra = rs.getString("safra");
        this.datareceb = rs.getString("datareceb");
        this.qtdrecebida = rs.getFloat("qtdrecebida");
        this.grandeza_safra = rs.getString("grandeza_safra");
        this.nomecultivar = rs.getString("nomecultivar");
        this.nomepropriedade = rs.getString("nomepropriedade");
        this.grandeza_cultivar = rs.getString("grandeza_cultivar");
        this.tempodecolheita = rs.getInt("tempodecolheita");
        this.descricaostatus = rs.getString("descricaostatus");
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
        j.put("statussafra_idstatussafra", statussafra_idstatussafra);
        j.put("grandeza_cultivar", grandeza_cultivar);
        j.put("grandeza_safra", grandeza_safra);
        j.put("nomecultivar", nomecultivar);
        j.put("nomepropriedade", nomepropriedade);
        j.put("descricaostatus", descricaostatus);
        
        return j;
    }



}
