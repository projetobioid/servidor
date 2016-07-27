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
public class TOSafra extends TOBase {
    private long propriedade_idpropriedade;
    
    private String nomepropriedade;
    
    private long cultivar_idcultivar;
    
    private String nomecultivar;
    
    private String safra;
    
    private Date datareceb;
    
    private double qtdrecebida;
    
    private boolean relatada;
    
    private Date datacolheita;
    
    private double qtdconsumida;
    
    private double qtdreplatar;
    
    private double qtddestinada;
    
    private String destino;
    

    public long getPropriedade_idpropriedade() {
        return propriedade_idpropriedade;
    }

    public void setPropriedade_idpropriedade(long propriedade_idpropriedade) {
        this.propriedade_idpropriedade = propriedade_idpropriedade;
    }

    public String getNomepropriedade() {
        return nomepropriedade;
    }

    public void setNomepropriedade(String nomepropriedade) {
        this.nomepropriedade = nomepropriedade;
    }

    
    public long getCultivar_idcultivar() {
        return cultivar_idcultivar;
    }

    public void setCultivar_idcultivar(long cultivar_idcultivar) {
        this.cultivar_idcultivar = cultivar_idcultivar;
    }

    public String getNomecultivar() {
        return nomecultivar;
    }

    public void setNomecultivar(String nomecultivar) {
        this.nomecultivar = nomecultivar;
    }
 
    
    public String getSafra() {
        return safra;
    }

    public void setSafra(String safra) {
        this.safra = safra;
    }

    public Date getDatareceb() {
        return datareceb;
    }

    public void setDatareceb(Date datareceb) {
        this.datareceb = datareceb;
    }

    public double getQtdrecebida() {
        return qtdrecebida;
    }

    public void setQtdrecebida(double qtdrecebida) {
        this.qtdrecebida = qtdrecebida;
    }

    public boolean isRelatada() {
        return relatada;
    }

    public void setRelatada(boolean relatada) {
        this.relatada = relatada;
    }

    public Date getDatacolheita() {
        return datacolheita;
    }

    public void setDatacolheita(Date datacolheita) {
        this.datacolheita = datacolheita;
    }

    public double getQtdconsumida() {
        return qtdconsumida;
    }

    public void setQtdconsumida(double qtdconsumida) {
        this.qtdconsumida = qtdconsumida;
    }

    public double getQtdreplatar() {
        return qtdreplatar;
    }

    public void setQtdreplatar(double qtdreplatar) {
        this.qtdreplatar = qtdreplatar;
    }

    public double getQtddestinada() {
        return qtddestinada;
    }

    public void setQtddestinada(double qtddestinada) {
        this.qtddestinada = qtddestinada;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    
    
    
    public TOSafra() {
    }

    public TOSafra(long propriedade_idpropriedade, String nomepropriedade, long cultivar_idcultivar, String nomecultivar, String safra, Date datareceb, double qtdrecebida, boolean relatada, Date datacolheita, double qtdconsumida, double qtdreplatar, double qtddestinada, String destino) {
        this.propriedade_idpropriedade = propriedade_idpropriedade;
        this.nomepropriedade = nomepropriedade;
        this.cultivar_idcultivar = cultivar_idcultivar;
        this.nomecultivar = nomecultivar;
        this.safra = safra;
        this.datareceb = datareceb;
        this.qtdrecebida = qtdrecebida;
        this.relatada = relatada;
        this.datacolheita = datacolheita;
        this.qtdconsumida = qtdconsumida;
        this.qtdreplatar = qtdreplatar;
        this.qtddestinada = qtddestinada;
        this.destino = destino;
    }

    
      
    public TOSafra(ResultSet rs) throws Exception{
        this.propriedade_idpropriedade = rs.getLong("propriedade_idpropriedade");
        this.nomepropriedade = rs.getString("nomepropriedade");
        this.cultivar_idcultivar = rs.getLong("cultivar_idcultivar");
        this.nomecultivar = rs.getString("nomecultivar");
        this.safra = rs.getString("safra");
        this.datareceb = rs.getDate("datareceb");
        this.qtdrecebida = rs.getDouble("qtdrecebida");
        this.relatada = rs.getBoolean("relatada");
        this.datacolheita = rs.getDate("datacolheita");
        this.qtdconsumida = rs.getDouble("qtdconsumida");
        this.qtdreplatar = rs.getDouble("qtdreplatar");
        this.qtddestinada = rs.getDouble("qtddestinada");
        this.destino = rs.getString("destino");
        
    }

    @Override
    public JSONObject getJson() throws Exception {
        //variavel para retorno do json contendo as informacoes do produto
        JSONObject j = new JSONObject();
        
        //populando o objeto j
        j.put("propriedade_idpropriedade", propriedade_idpropriedade);
        j.put("nomepropriedade", nomepropriedade);
        j.put("cultivar_idcultivar", cultivar_idcultivar);
        j.put("nomecultivar", nomecultivar);
        j.put("safra", safra);
        j.put("datareceb", datareceb);
        j.put("qtdrecebida", qtdrecebida);
        j.put("relatada", relatada);
        j.put("datacolheita", datacolheita);
        j.put("qtdconsumida", qtdconsumida);
        j.put("qtdreplatar", qtdreplatar);
        j.put("qtddestinada", qtddestinada);
        j.put("destino", destino);
        return j;
    }
    

     
   


}
