package to;

import java.sql.ResultSet;
import org.json.JSONObject;

/**
 *
 * @author Daniel
 */
public class TOCidade extends TOBase{
    
    private long idcidade;
    
    private String nomecidade;

    public long getIdcidade() {
        return idcidade;
    }

    public void setIdcidade(long idcidade) {
        this.idcidade = idcidade;
    }

    public String getNomecidade() {
        return nomecidade;
    }

    public void setNomecidade(String nomecidade) {
        this.nomecidade = nomecidade;
    }


    public TOCidade() {
    }
    
}
