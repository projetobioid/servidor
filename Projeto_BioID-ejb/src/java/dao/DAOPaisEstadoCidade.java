/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import fw.Data;
import static fw.Mapeamento.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import to.TOBase;
import to.TOPaisEstadoCidade;

/**
 *
 * @author Daniel
 */
public class DAOPaisEstadoCidade extends DAOBase{

    @Override
    public JSONArray listar(Connection c, TOBase t, String metodo) throws Exception {

        String sql = null;
        ResultSet rs = null;
        try{
            //variavel com lista dos parametros
            List<Object> u = new ArrayList<Object>();
            
            switch(metodo){
                case "PAIS":
                    sql = "SELECT idpais, nomepais FROM pais ORDER BY nomepais ASC";
                    break;
                case "ESTADOS":
                    sql = "SELECT idestado, nomeestado FROM estado WHERE pais_idpais IN(?) ORDER BY nomeestado ASC";
                    u.add(((TOPaisEstadoCidade) t).getId());
                    break;
                case "CIDADES":
                     sql = "SELECT idcidade, nomecidade FROM cidade WHERE estado_idestado IN(?) ORDER BY nomecidade ASC";
                     u.add(((TOPaisEstadoCidade) t).getId());
                    break;
                
            }
            
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
