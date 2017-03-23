
package to;

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

    public TOBackupEntrevista() {
    }

}
