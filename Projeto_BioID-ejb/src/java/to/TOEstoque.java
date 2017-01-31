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
    
    private double quantidade;
    
//    private String grandeza;
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

    public long getUnidademedida_idunidademedida() {
        return unidademedida_idunidademedida;
    }

    public void setUnidademedida_idunidademedida(long unidademedida_idunidademedida) {
        this.unidademedida_idunidademedida = unidademedida_idunidademedida;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

//    public String getGrandeza() {
//        return grandeza;
//    }
//
//    public void setGrandeza(String grandeza) {
//        this.grandeza = grandeza;
//    }
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


    
    public TOEstoque (ResultSet rs) throws Exception{
        this.unidade_idunidade = rs.getLong("unidade_idunidade");
        this.cultivar_idcultivar = rs.getLong("cultivar_idcultivar");
        this.quantidade = rs.getFloat("quantidade");
        this.unidademedida_idunidademedida = rs.getLong("unidademedida_idunidademedida");
        
//        this.grandeza = rs.getString("grandeza");
//        this.imagem = rs.getString("imagem");
//        this.nomecultivar = rs.getString("nomecultivar");
//        

    }
    public TOEstoque (ResultSet rs, String metodo) throws Exception{
        if(metodo.equals("listarestoqueunidade")){
//            this.quantidade = rs.getFloat("quantidade");
            this.nomecultivar = rs.getString("nomecultivar");
            this.cultivar_idcultivar = rs.getLong("cultivar_idcultivar");
        }else if(metodo.equals("distribuircultivar")){
            this.quantidade = rs.getDouble("quantidade");
        }

    }

    @Override
    public JSONObject getJson() throws Exception {
        JSONObject j = new JSONObject();
        
        j.put("unidade_idunidade", unidade_idunidade);
        j.put("cultivar_idcultivar", cultivar_idcultivar);
        j.put("unidademedida_idunidademedida", unidademedida_idunidademedida);
        j.put("quantidade", quantidade);
        
//        j.put("grandeza", grandeza);
        
//        j.put("imagem", imagem);
//        j.put("nomecultivar", nomecultivar);
        return j;
                
    } 
    
    @Override
    public JSONObject getJson(String metodo) throws Exception {
        JSONObject j = new JSONObject();
        if(metodo.equals("listarestoqueunidade")){
//            j.put("quantidade", quantidade);
            j.put("nomecultivar", nomecultivar);
            j.put("idcultivar", cultivar_idcultivar);
        }else if(metodo.equals("distribuircultivar")){
            j.put("quantidade", quantidade);
        }
        
        return j;
                
    } 
    
    
}
