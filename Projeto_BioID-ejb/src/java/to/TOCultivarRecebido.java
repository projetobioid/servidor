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
public class TOCultivarRecebido extends TOBase {
    public int id;
    
    public int quantidade;
    public String data_recebimento;
    public int cultivar;
    public int agricultor;
    public String grandeza;
    public boolean relatado;
    public String imgcultivar;
    public String nome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getData_recebimento() {
        return data_recebimento;
    }

    public void setData_recebimento(String data_recebimento) {
        this.data_recebimento = data_recebimento;
    }

    public int getCultivar() {
        return cultivar;
    }

    public void setCultivar(int cultivar) {
        this.cultivar = cultivar;
    }

    public int getAgricultor() {
        return agricultor;
    }

    public void setAgricultor(int agricultor) {
        this.agricultor = agricultor;
    }

    public String getGrandeza() {
        return grandeza;
    }

    public void setGrandeza(String grandeza) {
        this.grandeza = grandeza;
    }
//////////////////join
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
/////////////

    public boolean isRelatado() {
        return relatado;
    }

    public void setRelatado(boolean relatado) {
        this.relatado = relatado;
    }

    public String getImgcultivar() {
        return imgcultivar;
    }

    public void setImgcultivar(String imgcultivar) {
        this.imgcultivar = imgcultivar;
    }
    
    
    
    public TOCultivarRecebido() {
    }
    
    public TOCultivarRecebido(ResultSet rs) throws Exception{
        //this.id = rs.getInt("id");
        this.quantidade = rs.getInt("quantidade");
        this.data_recebimento = rs.getString("data_recebimento");
        //this.cultivar = rs.getInt("cultivar");
        //this.agricultor = rs.getInt("agricultor");
        this.grandeza = rs.getString("grandeza");
        this.nome = rs.getString("nome");
        this.relatado = rs.getBoolean("relatado");
        this.imgcultivar = rs.getString("imgcultivar");
        
        
    }

    @Override
    public JSONObject getJson() throws Exception {
        //variavel para retorno do json contendo as informacoes do produto
        JSONObject j = new JSONObject();
        
        //populando o objeto j
       // j.put("id", id);
        j.put("quantidade", quantidade);
        j.put("data_recebimento", data_recebimento);
        //j.put("cultivar", cultivar);
        //j.put("agricultor", agricultor);
        j.put("grandeza", grandeza);
        j.put("nome", nome);
        j.put("relatado", relatado);
        j.put("imgcultivar", imgcultivar);
        return j;
    }
    

     
   


}
