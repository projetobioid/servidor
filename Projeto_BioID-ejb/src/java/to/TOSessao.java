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
    
    public long login_idlogin;
    
    public String sessao;
    
    //public long datalogin;
    
    public String newSessao;

    public long getIdsessao() {
        return idsessao;
    }

    public void setIdsessao(long idsessao) {
        this.idsessao = idsessao;
    }

    public long getLogin_idlogin() {
        return login_idlogin;
    }

    public void setLogin_idlogin(long login_idlogin) {
        this.login_idlogin = login_idlogin;
    }

    public String getSessao() {
        return sessao;
    }

    public void setSessao(String sessao) {
        this.sessao = sessao;
    }

//    public long getDatalogin() {
//        return datalogin;
//    }
//
//    public void setDatalogin(long datalogin) {
//        this.datalogin = datalogin;
//    }

    public String getNewSessao() {
        return newSessao;
    }

    public void setNewSessao(String newSessao) {
        this.newSessao = newSessao;
    }

    public TOSessao() {
    }

    public TOSessao(long idsessao, long login_idlogin, String sessao, String newSessao) {
        this.idsessao = idsessao;
        this.login_idlogin = login_idlogin;
        this.sessao = sessao;
        //this.datalogin = datalogin;
        this.newSessao = newSessao;
    }
    
    public TOSessao(ResultSet rs) throws Exception{
        this.idsessao = rs.getLong("idsessao");
        this.login_idlogin = rs.getLong("login_idlogin");
        this.sessao = rs.getString("sessao");
       // this.datalogin = rs.getLong("datalogin");
       
    }
    
    @Override
    public JSONObject getJson() throws Exception {
         //variavel para retorno do json contendo as informacoes do login
        JSONObject j = new JSONObject();
        
        //populando o objeto j
        j.put("idsessao", idsessao);
        j.put("login_idlogin", login_idlogin);
        j.put("sessao", sessao);
        //j.put("datalogin", datalogin);
        
        return j;
    }
    
}
