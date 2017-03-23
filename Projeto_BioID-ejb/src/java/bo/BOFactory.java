/*
 faz o crud de qualquer classe que herde de tobase e daobase
 */
package bo;

import fw.Data;
import to.TOBase;
import java.sql.Connection;
import org.json.JSONArray;
import dao.DAOBase;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author Daniel
 */
public class BOFactory {
    
    //inserir//////////////////////
    public static long inserir(DAOBase d, TOBase t) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            return d.inserir(c, t);
        }finally{
            c.close();
        }
    }
    
    public static void inserirIDString(DAOBase d, TOBase t) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            d.inserirIDString(c, t);
        }finally{
            c.close();
        }
    }

    //editar///////////////////////////
    public static void editar(DAOBase d, TOBase t) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            d.editar(c, t);
        }finally{
            c.close();
        }
    }

    //excluir/////////////////////////
    public static void excluir(DAOBase d, TOBase t) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            d.excluir(c, t);
        }finally{
            c.close();
        }
    }
    
    //buscar//////////////////////////////
    public static JSONObject buscar(DAOBase d, TOBase t) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            
            return d.buscar(c, t);
        }finally{
            c.close();
        }
    }

    public static JSONObject buscar(DAOBase d, TOBase t, String metodo) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            
            return d.buscar(c, t, metodo);
        }finally{
            c.close();
        }
    }
    
    public static JSONObject buscar(DAOBase d, List<Object> t, String metodo) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            
            return d.buscar(c, t, metodo);
        }finally{
            c.close();
        }
    }
    
    public static TOBase buscarObj(DAOBase d, TOBase t, String metodo) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            
            return d.buscarObj(c, t, metodo);
        }finally{
            c.close();
        }
    }
    
    //listar/////////////////////////////////
    public static JSONArray listar(DAOBase d, String metodo) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            return d.listar(c, metodo);
        }finally{
            c.close();
        }
    }   
    
//    public static JSONArray listar(DAOBase d, TOBase t, String metodo) throws Exception{
//        Connection c = null;
//        
//        try{
//            c =  Data.openConnection();
//            return d.listar(c, t, metodo);
//        }finally{
//            c.close();
//        }
//    }   
    
    public static JSONArray listar(DAOBase d, List<Object> t, String metodo) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            return d.listar(c, t, metodo);
        }finally{
            c.close();
        }
    }   

}
