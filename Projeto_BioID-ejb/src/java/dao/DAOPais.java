
package dao;

import fw.Data;
import static fw.Mapeamento.MapeamentoJsonArray;
import java.sql.Connection;
import java.sql.ResultSet;
import org.json.JSONArray;

/**
 *
 * @author Daniel
 */
public class DAOPais extends DAOBase{



 @Override
    public JSONArray listar(Connection c) throws Exception {

        String sql = null;
        ResultSet rs = null;
        
        try{
            
            sql = "SELECT idpais, nomepais FROM pais ORDER BY nomepais ASC";
              
            
            rs = Data.executeQuery(c, sql);
           
            if(rs.next()){
                return MapeamentoJsonArray(rs);
            }else{
                return null;
            }
                  
        }finally{
            rs.close();
        }
        
    
    }
    
}
