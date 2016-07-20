/*
 faz o crud de qualquer classe que herde de tobase e daobase
 */
package bo;

import dao.Data;
import to.TOBase;
import java.sql.Connection;
import org.json.JSONArray;
import dao.DAOIntBase;

/**
 *
 * @author Daniel
 */
public class BOFactory {
       
    public static TOBase getLogin(DAOIntBase d, TOBase t) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            
            return d.getLogin(c, t);
        }finally{
            c.close();
        }
    }
    
    public static void inserir(DAOIntBase d, TOBase t) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            d.inserir(c, t);
        }finally{
            c.close();
        }
    }
    
    public static void editar(DAOIntBase d, TOBase t) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            d.editar(c, t);
        }finally{
            c.close();
        }
    }

    public static void excluir(DAOIntBase d, TOBase t) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            d.excluir(c, t);
        }finally{
            c.close();
        }
    }

    
    public static TOBase get(DAOIntBase d, TOBase t) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            
            return d.get(c, t);
        }finally{
            c.close();
        }
    }
    
    public static JSONArray listar(DAOIntBase d) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            
            return d.listar(c);
        }finally{
            c.close();
        }
    }
    
    
    
    
    
    
    
    
    
    
    
}
