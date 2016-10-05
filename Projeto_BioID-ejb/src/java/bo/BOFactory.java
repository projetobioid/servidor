/*
 faz o crud de qualquer classe que herde de tobase e daobase
 */
package bo;

import dao.Data;
import to.TOBase;
import java.sql.Connection;
import org.json.JSONArray;
import dao.DAOBase;
import dao.DAOLogin;
import dao.DAOPropriedade;
import to.TOLogin;

/**
 *
 * @author Daniel
 */
public class BOFactory {
       
    
    public static long inserir(DAOBase d, TOBase t) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            return d.inserir(c, t);
        }finally{
            c.close();
        }
    }
    
    public static void editar(DAOBase d, TOBase t) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            d.editar(c, t);
        }finally{
            c.close();
        }
    }

    public static void excluir(DAOBase d, TOBase t) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            d.excluir(c, t);
        }finally{
            c.close();
        }
    }

    
    public static TOBase get(DAOBase d, TOBase t) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            
            return d.get(c, t);
        }finally{
            c.close();
        }
    }
    
    public static JSONArray listar(DAOBase d) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            
            return d.listar(c);
        }finally{
            c.close();
        }
    }
    
    
    public static JSONArray listar(DAOBase d, TOBase t) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            
            return d.listar(c, t);
        }finally{
            c.close();
        }
    }   
      
    
    public static JSONArray listarAgricultoresUnidade(DAOBase d, TOBase t) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            
            return d.listarAgricultoresUnidade(c, t);
        }finally{
            c.close();
        }
    }

    public static JSONArray backupentrevista(DAOBase d, TOBase t) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            
            return d.backupentrevista(c, t);
        }finally{
            c.close();
        }
    }

    public static void sessao(DAOBase d, TOBase t) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            d.editar(c, t);
        }finally{
            c.close();
        }
    }

 
    
    
}
