/*
 classe recebe os valores do banco de dados de usuarios do sistema
 */
package to;

import java.sql.ResultSet;
import org.json.JSONObject;

/**
 *
 * @author daniel
 */
public class TOUsuario extends TOBase{
    
    //atributos
    //id do usuario
    public long id;
    //usuario
    public String usuario;
    //password
    public String senha;
    //email
    public String email;
    //papel do usuario
    public String tipo;
    
    //gets e sets
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    

    //construtor vazio
    public TOUsuario() {
    }
    
    //retorna consulta do banco de dados tipo resultset
    public TOUsuario (ResultSet rs) throws Exception{
        this.id = rs.getInt("id");
        this.usuario = rs.getString("usuario");
        this.senha = rs.getString("senha");
        this.email = rs.getString("email");
        this.tipo = rs.getString("tipo");
    }


    //retorna um json
    @Override
    public JSONObject getJson() throws Exception {
        //variavel tipo json para retornar no metodo
        JSONObject j = new JSONObject();
        
        //populando o objeto j
        j.put("id", id);
        j.put("usuario", usuario);
        j.put("senha", senha);
        j.put("email", email);
        j.put("tipo", tipo);
        
        return j;
   
    }
    

    
    
}
