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
    public long propriedade_idpropriedade;
    public long cultivar_idcultivar;
    public String idsafra;
    public Date datareceb;
    public double qtdrecebida;
    public boolean relatada;
    public Date datacolheita;
    public double qtdconsumida;
    public double qtdreplatar;
    public double qtddestinada;
    public String destino;

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

    public String getIdsafra() {
        return idsafra;
    }

    public void setIdsafra(String idsafra) {
        this.idsafra = idsafra;
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

    public TOSafra(long propriedade_idpropriedade, long cultivar_idcultivar, String idsafra, Date datareceb, double qtdrecebida, boolean relatada, Date datacolheita, double qtdconsumida, double qtdreplatar, double qtddestinada, String destino) {
        this.propriedade_idpropriedade = propriedade_idpropriedade;
        this.cultivar_idcultivar = cultivar_idcultivar;
        this.idsafra = idsafra;
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
        this.cultivar_idcultivar = rs.getLong("cultivar_idcultivar");
        this.idsafra = rs.getString("idsafra");
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
        j.put("cultivar_idcultivar", cultivar_idcultivar);
        j.put("idsafra", idsafra);
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
