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
public class TOEstoque extends TOBase{
    private long unidade_idunidade;
    
    private long cultivar_idcultivar;
    
    private long unidademedida_idunidademedida;
    
    private float quantidade;
    
    private String grandeza;
    
    private String imagem;
    
    private String nomecultivar;
    

    public long getUnidade_idunidade() {
        return unidade_idunidade;
    }

    public void setUnidade_idunidade(long unidade_idunidade) {
        this.unidade_idunidade = unidade_idunidade;
    }

    public long getCultivar_idcultivar() {
        return cultivar_idcultivar;
    }

    public void setCultivar_idcultivar(long cultivar_idcultivar) {
        this.cultivar_idcultivar = cultivar_idcultivar;
    }

    public long getUnidademedida_idunidademedida() {
        return unidademedida_idunidademedida;
    }

    public void setUnidademedida_idunidademedida(long unidademedida_idunidademedida) {
        this.unidademedida_idunidademedida = unidademedida_idunidademedida;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public String getGrandeza() {
        return grandeza;
    }

    public void setGrandeza(String grandeza) {
        this.grandeza = grandeza;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getNomecultivar() {
        return nomecultivar;
    }

    public void setNomecultivar(String nomecultivar) {
        this.nomecultivar = nomecultivar;
    }

    
    
    public TOEstoque() {
    }

    public TOEstoque(long unidade_idunidade, long cultivar_idcultivar, long unidademedida_idunidademedida, float quantidade, String grandeza, String imagem, String nomecultivar) {
        this.unidade_idunidade = unidade_idunidade;
        this.cultivar_idcultivar = cultivar_idcultivar;
        this.unidademedida_idunidademedida = unidademedida_idunidademedida;
        this.quantidade = quantidade;
        this.grandeza = grandeza;
        this.imagem = imagem;
        this.nomecultivar = nomecultivar;
    }

    
    public TOEstoque (ResultSet rs) throws Exception{
        this.unidade_idunidade = rs.getLong("unidade_idunidade");
        this.cultivar_idcultivar = rs.getLong("cultivar_idcultivar");
        this.grandeza = rs.getString("grandeza");
        this.quantidade = rs.getFloat("quantidade");
        this.imagem = rs.getString("imagem");
        this.nomecultivar = rs.getString("nomecultivar");
        

    }

    @Override
    public JSONObject getJson() throws Exception {
        JSONObject j = new JSONObject();
        
        j.put("unidade_idunidade", unidade_idunidade);
        j.put("cultivar_idcultivar", cultivar_idcultivar);
        j.put("grandeza", grandeza);
        j.put("quantidade", quantidade);
        j.put("imagem", imagem);
        j.put("nomecultivar", nomecultivar);
        return j;
                
    } 
    
    
}
