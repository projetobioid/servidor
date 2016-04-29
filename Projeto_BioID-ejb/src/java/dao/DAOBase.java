/*
 Classe base responsavel por acesso ao banco de dados

 */
package dao;

import to.TOBase;
import java.sql.Connection;
import org.json.JSONArray;

/**
 *
 * @author daniel
 */
public class DAOBase {
    public void inserir(Connection c, TOBase t) throws  Exception{
    }
    
    public void editar(Connection c, TOBase t) throws  Exception{  
    }
        
    public void excluir(Connection c, TOBase t) throws  Exception{      
    }
            
    public TOBase get(Connection c, TOBase t) throws  Exception{
        return null;
    }
    
    public JSONArray listar(Connection c) throws  Exception {
        return null;
    }
}
