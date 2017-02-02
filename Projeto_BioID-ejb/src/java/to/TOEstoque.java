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
public class TOEstoque extends TOBase{
    private long unidade_idunidade;
    
    private long cultivar_idcultivar;
    
    private double quantidade;
    
    
    //amostragem
    private String grandeza;
//    
//    private String imagem;
//    
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

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public String getGrandeza() {
        return grandeza;
    }

    public void setGrandeza(String grandeza) {
        this.grandeza = grandeza;
    }
//
//    public String getImagem() {
//        return imagem;
//    }
//
//    public void setImagem(String imagem) {
//        this.imagem = imagem;
//    }
//
    public String getNomecultivar() {
        return nomecultivar;
    }

    public void setNomecultivar(String nomecultivar) {
        this.nomecultivar = nomecultivar;
    }

    
    
    public TOEstoque() {
    }


    public TOEstoque (ResultSet rs, String metodo) throws Exception{
        switch (metodo) {
            case "estoqueunidade":
            case "estoqueunidade_select":
                this.quantidade = rs.getFloat("quantidade");
                this.nomecultivar = rs.getString("nomecultivar");
                this.cultivar_idcultivar = rs.getLong("cultivar_idcultivar");
                this.grandeza = rs.getString("grandeza");
                break;
            //retorna toda a classe
            default:
                this.unidade_idunidade = rs.getLong("unidade_idunidade");
                this.cultivar_idcultivar = rs.getLong("cultivar_idcultivar");
                this.quantidade = rs.getFloat("quantidade");
                break;
        }

    }

    
    
    @Override
    public JSONObject getJson(String metodo) throws Exception {
        JSONObject j = new JSONObject();
        switch (metodo) {
            case "estoqueunidade":
            case "estoqueunidade_select":
                j.put("grandeza", grandeza);
                BigDecimal bd = new BigDecimal(quantidade).setScale(2, RoundingMode.HALF_EVEN);
                j.put("quantidade", bd.doubleValue());
                j.put("nomecultivar", nomecultivar);
                j.put("idcultivar", cultivar_idcultivar);
                break;
            case "distribuircultivar":
                j.put("quantidade", quantidade);
                break;
            default:
                j.put("unidade_idunidade", unidade_idunidade);
                j.put("cultivar_idcultivar", cultivar_idcultivar);
                j.put("quantidade", quantidade);
                break;
        }
        
        return j;
                
    } 
    
    
}
