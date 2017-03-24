/*
classe de acesso ao banco de dados
 */
package dao;

import fw.Data;
import static fw.Mapeamento.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Daniel
 */

public class DAOBackupEntrevista extends DAOBase {

    @Override
    public long inserir(Connection c, List<Object> u) throws Exception {
       
        String sql = "INSERT INTO backupentrevista(propriedade_idpropriedade, login_usuario, data_backup, status_backup) VALUES (?, ?, ?, ?)";
       
        return Data.executeUpdate(c, sql, u);
    }
    
    @Override
    public void editar(Connection c, List<Object> u) throws Exception {

        String sql = "update backupentrevista set status_backup = ? ";

        //passa por parametros a conexao e a lista de objetos da insercao        
        Data.executeUpdate(c, sql, u);
    }
    

    @Override
    public JSONObject buscar(Connection c, List<Object> u) throws Exception {
        String sql = null;
                
        ResultSet rs = null;
        
        try{
            
            sql = "SELECT status_backup FROM backupentrevista where propriedade_idpropriedade IN(?)";
   
            rs = Data.executeQuery(c, sql, u);
            
            if(rs.next()){
                return MapeamentoJson(rs);
            }else{
                return null;
            }
            
        }finally{
            rs.close();
        }
    }
    

    @Override
    public JSONArray listar(Connection c, List<Object> u, String metodo) throws Exception {
    
        ResultSet rs = null;
        
        try{
            
            String sql = null;
                
            
        }finally{
            rs.close();
        }
        return null;
    }

}
