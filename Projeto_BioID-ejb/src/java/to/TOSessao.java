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
public class TOSessao extends TOBase{

    public long idsessao;
    
    public String login_usuario;
    
    public String sessao;
    
    public String datarequisicao;

    public long getIdsessao() {
        return idsessao;
    }

    public void setIdsessao(long idsessao) {
        this.idsessao = idsessao;
    }

    public String getLogin_usuario() {
        return login_usuario;
    }

    public void setLogin_usuario(String login_usuario) {
        this.login_usuario = login_usuario;
    }

    public String getSessao() {
        return sessao;
    }

    public void setSessao(String sessao) {
        this.sessao = sessao;
    }

    public String getDatarequisicao() {
        return datarequisicao;
    }

    public void setDatarequisicao(String datarequisicao) {
        this.datarequisicao = datarequisicao;
    }


    public TOSessao() {
    }

    
    public TOSessao(ResultSet rs, String metodo) throws Exception{
        
        switch(metodo){
            case "get_sessao":
                //retorna sessao antiga
                this.idsessao = rs.getLong("idsessao");
                this.login_usuario = rs.getString("login_usuario");
                this.sessao = rs.getString("sessao");
                this.datarequisicao = rs.getString("datarequisicao");
                
                
                break;
        }
        
       
    }
    
//    @Override
//    public JSONObject getJson(String metodo) throws Exception {
//         //variavel para retorno do json contendo as informacoes do login
//        JSONObject j = new JSONObject();
//        
//        //populando o objeto j
//        j.put("idsessao", idsessao);
//        j.put("usuario", login_usuario);
//        j.put("sessao", sessao);
//        j.put("datarequisicao", datarequisicao);
//        
//        return j;
//    }

   
    
}
