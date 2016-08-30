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
     
    //campos do result set
    
    private String grandeza_safra;

    private String nomecultivar;
    
    private String nomepropriedade;
    
    private int tempodecolheita;
    
    private int tempodestinacao;
    
    //campos de consulta
    private String usuario;
    
    //campos de amostragem 
    private String prazo_colheita;
        
    private float qtdcolhida;
    
    private String um_colhida;
    
    private String prazo_destinacao;
    
    private float qtddestinada;
    
    private String um_destinada;

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

    public String getGrandeza_safra() {
        return grandeza_safra;
    }

    public void setGrandeza_safra(String grandeza_safra) {
        this.grandeza_safra = grandeza_safra;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPrazo_colheita() {
        return prazo_colheita;
    }

    public void setPrazo_colheita(String prazo_colheita) {
        this.prazo_colheita = prazo_colheita;
    }

    public float getQtdcolhida() {
        return qtdcolhida;
    }

    public void setQtdcolhida(float qtdcolhida) {
        this.qtdcolhida = qtdcolhida;
    }

    public String getUm_colhida() {
        return um_colhida;
    }

    public void setUm_colhida(String um_colhida) {
        this.um_colhida = um_colhida;
    }

    public String getPrazo_destinacao() {
        return prazo_destinacao;
    }

    public void setPrazo_destinacao(String prazo_destinacao) {
        this.prazo_destinacao = prazo_destinacao;
    }

    public float getQtddestinada() {
        return qtddestinada;
    }

    public void setQtddestinada(float qtddestinada) {
        this.qtddestinada = qtddestinada;
    }

    public String getUm_destinada() {
        return um_destinada;
    }

    public void setUm_destinada(String um_destinada) {
        this.um_destinada = um_destinada;
    }

    public TOSafra() {
    }

    public TOSafra(long idsafra, long unidademedida_idunidademedida, long propriedade_idpropriedade, long cultivar_idcultivar, String safra, String datareceb, float qtdrecebida, String grandeza_safra, String nomecultivar, String nomepropriedade, int tempodecolheita, int tempodestinacao, String usuario, String prazo_colheita, float qtdcolhida, String um_colhida, String prazo_destinacao, float qtddestinada, String um_destinada) {
        this.idsafra = idsafra;
        this.unidademedida_idunidademedida = unidademedida_idunidademedida;
        this.propriedade_idpropriedade = propriedade_idpropriedade;
        this.cultivar_idcultivar = cultivar_idcultivar;
        this.safra = safra;
        this.datareceb = datareceb;
        this.qtdrecebida = qtdrecebida;
        this.grandeza_safra = grandeza_safra;
        this.nomecultivar = nomecultivar;
        this.nomepropriedade = nomepropriedade;
        this.tempodecolheita = tempodecolheita;
        this.tempodestinacao = tempodestinacao;
        this.usuario = usuario;
        this.prazo_colheita = prazo_colheita;
        this.qtdcolhida = qtdcolhida;
        this.um_colhida = um_colhida;
        this.prazo_destinacao = prazo_destinacao;
        this.qtddestinada = qtddestinada;
        this.um_destinada = um_destinada;
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
        this.tempodecolheita = rs.getInt("tempodecolheita");
        this.tempodestinacao = rs.getInt("tempodestinacao");
        //this.qtdcolhida = rs.getFloat("qtdcolhida");
        //this.qtddestinada = rs.getFloat("qtddestinada");
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
        j.put("grandeza_safra", grandeza_safra);
        j.put("nomecultivar", nomecultivar);
        j.put("nomepropriedade", nomepropriedade);
        j.put("prazo_colheita", prazo_colheita);
        j.put("prazo_destinacao", prazo_destinacao);
        
        j.put("qtdcolhida", qtdcolhida);
        j.put("um_colhida", um_colhida);
        j.put("qtddestinada", qtddestinada);
        j.put("um_destinada", um_destinada);
        
        return j;
    }



}
