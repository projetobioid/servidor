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
public class TOSafraImg extends TOBase{
    
    private String nomecultivar;
    
    private String imagem;
    
    private String usuario;


        
    public String getNomecultivar() {
        return nomecultivar;
    }

    public void setNomecultivar(String nomecultivar) {
        this.nomecultivar = nomecultivar;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    
    public TOSafraImg() {
    }

    public TOSafraImg(String nomecultivar, String imagem, String usuario) {
        this.nomecultivar = nomecultivar;
        this.imagem = imagem;
        this.usuario = usuario;
    }
    
    
    public TOSafraImg(ResultSet rs) throws Exception{
        
        this.nomecultivar = rs.getString("nomecultivar");
        this.imagem = rs.getString("imagem");
        
        
    }

    @Override
    public JSONObject getJson() throws Exception {
         //variavel para retorno do json contendo as informacoes do produto
        JSONObject j = new JSONObject();
        
        //populando o objeto j
       
        j.put("nomecultivar", nomecultivar);
        j.put("imagem", imagem);
        
        return j;
    }
}
