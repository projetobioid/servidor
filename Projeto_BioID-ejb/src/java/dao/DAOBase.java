/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import javax.ejb.Stateless;
import org.json.JSONArray;
import to.TOBase;

/**
 *
 * @author daniel
 */

@Stateless
public class DAOBase {
    
    protected long idretorno;

    
    public long inserir(Connection c, TOBase t, String metodo) throws Exception{
        return this.idretorno;
    }
    
    public void inserir(Connection c, TOBase t) throws Exception{
    }    
    
    public void inserirIDString(Connection c, TOBase t) throws Exception{
    }    
    
    public void editar(Connection c, TOBase t, String metodo) throws Exception{
    }
    
    public void excluir(Connection c, TOBase t, String metodo) throws Exception{
    }
    
    public TOBase buscar(Connection c, TOBase t, String metodo) throws Exception{
        return null;
    }
        
//    public JSONArray listar(Connection c, String metodo) throws Exception{
//        return null;
//    }

    public JSONArray listar(Connection c, TOBase t, String metodo) throws Exception{
        return null;
    }

//    public JSONArray listar(Connection c) throws Exception{
//        return null;
//    }
    
    
    
}
