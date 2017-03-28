/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import fw.Data;
import static fw.Mapeamento.MapeamentoJsonArray;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import org.json.JSONArray;

/**
 *
 * @author daniel
 */
public class DAOEstado extends DAOBase{


@Override
    public JSONArray listar(Connection c, List<Object> u) throws Exception {

        String sql = null;
        ResultSet rs = null;
        
        try{
            
            sql = "SELECT idestado, nomeestado FROM estado WHERE pais_idpais IN(?) ORDER BY nomeestado ASC";

    
            rs = Data.executeQuery(c, sql, u);
           
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
