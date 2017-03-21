/*
classe de acesso ao banco de dados
 */
package dao;

import fw.Data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import to.TOBackupEntrevista;
import to.TOBase;

/**
 *
 * @author daniel
 */

public class DAOBackupEntrevista extends DAOBase {

    @Override
    public void inserir(Connection c, TOBase t) throws Exception {
        String sql = "INSERT INTO backupentrevista(propriedade_idpropriedade, login_usuario, data_backup, status_backup) VALUES (?, ?, ?, ?)";
        
        
        List<Object> p = new ArrayList<Object>();
        
            p.add(((TOBackupEntrevista)t).getPropriedade_idpropriedade());
            p.add(((TOBackupEntrevista)t).getLogin_usuario());
            p.add(((TOBackupEntrevista)t).getData_backup());
            p.add(((TOBackupEntrevista)t).isStatus_backup());

        
        
        
        Data.executeUpdate(c, sql, p);
    }
    
    @Override
    public void editar(Connection c, TOBase t, String metodo) throws Exception {
        String sql = "update backupentrevista set status_backup = ? ";
        
//        TOCultivar to = (TOCultivar)t;
        
        List<Object> p = new ArrayList<Object>();
        
        
        p.add(((TOBackupEntrevista)t).isStatus_backup());
        
        //passa por parametros a conexao e a lista de objetos da insercao        
        Data.executeUpdate(c, sql, p);
    }
    

    @Override
    public TOBase buscar(Connection c, TOBase t, String metodo) throws Exception {
        String sql = null;
                
        ResultSet rs = null;
        List<Object> u = new ArrayList<Object>();
        
        
        try{
            
            switch(metodo){
                case "POR_IDPROPRIEDADE_BACKUP":
                    sql = "SELECT status_backup FROM backupentrevista where propriedade_idpropriedade IN(?)";
                    u.add(((TOBackupEntrevista)t).getPropriedade_idpropriedade());
                break;
                
            }
            
            rs = Data.executeQuery(c, sql, u);
            
            if(rs.next()){
                return new TOBackupEntrevista(rs, metodo);
            }else{
                return null;
            }
        }finally{
            rs.close();
        }
    }
    

    @Override
    public JSONArray listar(Connection c, TOBase t, String metodo) throws Exception {
        JSONArray  ja = new JSONArray();           
        ResultSet rs = null;
        
        try{
            //variavel com lista dos parametros
            List<Object> u = new ArrayList<Object>();
            String sql = null;
            
            switch (metodo) {
                
                default:
                    break;
            }
                
            
        }finally{
            rs.close();
        }
        return ja;
    }

}
