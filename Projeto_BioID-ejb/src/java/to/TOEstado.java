package to;

import java.sql.ResultSet;
import org.json.JSONObject;

/**
 *
 * @author Daniel
 */
public class TOEstado extends TOBase{
    
    private long idestado;
    
    private String nomeestado;

    public long getIdestado() {
        return idestado;
    }

    public void setIdestado(long idestado) {
        this.idestado = idestado;
    }

    public String getNomeestado() {
        return nomeestado;
    }

    public void setNomeestado(String nomeestado) {
        this.nomeestado = nomeestado;
    }


    public TOEstado() {
    }
    
}
