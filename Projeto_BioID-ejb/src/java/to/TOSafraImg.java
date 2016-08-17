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
    
    private String descricao;
    
    private String valornutricional;


        
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getValornutricional() {
        return valornutricional;
    }

    public void setValornutricional(String valornutricional) {
        this.valornutricional = valornutricional;
    }
    
    
    public TOSafraImg() {
    }

    public TOSafraImg(String nomecultivar, String imagem, String usuario, String descricao, String valornutricional) {
        this.nomecultivar = nomecultivar;
        this.imagem = imagem;
        this.usuario = usuario;
        this.descricao = descricao;
        this.valornutricional = valornutricional;
    }

    
    
    
    public TOSafraImg(ResultSet rs) throws Exception{
        
        this.nomecultivar = rs.getString("nomecultivar");
        this.imagem = rs.getString("imagem");
        this.descricao = rs.getString("descricao");
        this.valornutricional = rs.getString("valornutricional");
    }

    @Override
    public JSONObject getJson() throws Exception {
         //variavel para retorno do json contendo as informacoes do produto
        JSONObject j = new JSONObject();
        
        //populando o objeto j
       
        j.put("nomecultivar", nomecultivar);
        j.put("imagem", imagem);
        j.put("descricao", descricao);
        j.put("valornutricional", valornutricional);
        return j;
    }
}
