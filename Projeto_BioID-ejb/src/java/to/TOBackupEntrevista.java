
package to;

import java.sql.ResultSet;
import org.json.JSONObject;

/**
 *
 * @author Daniel
 */

public class TOBackupEntrevista extends TOBase{
    
    private long propriedade_idpropriedade;
    
    private String login_usuario;
    
    private String data_backup;
    
    private boolean status_backup;
    
   

    public long getPropriedade_idpropriedade() {
        return propriedade_idpropriedade;
    }

    public void setPropriedade_idpropriedade(long propriedade_idpropriedade) {
        this.propriedade_idpropriedade = propriedade_idpropriedade;
    }

    public String getLogin_usuario() {
        return login_usuario;
    }

    public void setLogin_usuario(String login_usuario) {
        this.login_usuario = login_usuario;
    }

    public String getData_backup() {
        return data_backup;
    }

    public void setData_backup(String data_backup) {
        this.data_backup = data_backup;
    }

    public boolean isStatus_backup() {
        return status_backup;
    }

    public void setStatus_backup(boolean status_backup) {
        this.status_backup = status_backup;
    }


    //construtor vazio
    public TOBackupEntrevista() {
    }

 
    
    //retorna consulta do banco de dados tipo resultset
    public TOBackupEntrevista (ResultSet rs , String metodo) throws Exception{
        switch(metodo){
            case "POR_IDPROPRIEDADE_BACKUP":
                this.status_backup = rs.getBoolean("status_backup");
                break;
        }
    }
    //classe sobrescrita de tobase
    @Override
    public JSONObject buscarJson(String metodo) throws Exception {
        //variavel para retorno do json contendo as informacoes do produto
        JSONObject j = new JSONObject();
        
        switch(metodo){
            default:
                j.put("propriedade_idpropriedade", propriedade_idpropriedade);
                j.put("login_usuario", login_usuario);
                j.put("data_backup", data_backup);
                j.put("status_backup", status_backup);
                break;
        }
        
        return j;
    }    
    

}
