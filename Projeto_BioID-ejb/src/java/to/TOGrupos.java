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
public class TOGrupos extends TOBase{
    private String grupo;
    private String loginusuario;

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getLoginusuario() {
        return loginusuario;
    }

    public void setLoginusuario(String loginusuario) {
        this.loginusuario = loginusuario;
    }

    public TOGrupos() {
    }
    
    
    
    public TOGrupos(ResultSet rs, String metodo) throws Exception{
        
        switch(metodo){
            default:
                this.grupo = rs.getString("grupo");
                this.loginusuario = rs.getString("login_usuario");
                
                break;
        }
        
    }

    @Override
    public JSONObject getJson(String metodo) throws Exception {
        //variavel para retorno do json contendo as informacoes do login
        JSONObject j = new JSONObject();
        
        switch(metodo){
            default:
                //populando o objeto j
                j.put("grupo", grupo);
                j.put("login_usuario", loginusuario);
                break;
        }
        
        
        return j;
    }
}
