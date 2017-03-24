/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.util.List;
import javax.ejb.Stateless;
import org.json.JSONArray;
import org.json.JSONObject;
import to.TOBase;

/**
 *
 * @author Daniel
 */


@Stateless
public class DAOBase {
    

    //INSERIR//////////////
    public long inserir(Connection c, List<Object> u) throws Exception{
        return 0;
    }
    
    public void inserirIDString(Connection c, List<Object> u) throws Exception{
    }  

    
    
    //EDITAR///////////////
    public void editar(Connection c, List<Object> u) throws Exception{
    }
    
    
    
    //EXCLUIR//////////////
    public void excluir(Connection c, List<Object> u) throws Exception{
    }
    
    
    
    //BUSCAR///////////////
    public JSONObject buscar(Connection c, List<Object> u) throws Exception{
        return null;
    }
    
    public JSONObject buscar(Connection c, List<Object> u, String metodo) throws Exception{
        return null;
    }
    
    public TOBase buscarObj(Connection c, List<Object> u, String metodo) throws Exception{
        return null;
    }
    
    
    //LISTAR//////////////////
    public JSONArray listar(Connection c) throws Exception{
        return null;
    }
    
    public JSONArray listar(Connection c, String metodo) throws Exception{
        return null;
    }
    
    public JSONArray listar(Connection c, List<Object> t) throws Exception{
        return null;
    }
    
    public JSONArray listar(Connection c, List<Object> t, String metodo) throws Exception{
        return null;
    }

    
    
    
}
