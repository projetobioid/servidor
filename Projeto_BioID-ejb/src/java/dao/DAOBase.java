/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import org.json.JSONArray;
import to.TOBase;

/**
 *
 * @author daniel
 */
public interface DAOBase {
     public long inserir(Connection c, TOBase t) throws Exception;
    
    public void editar(Connection c, TOBase t) throws Exception;
        
    public void excluir(Connection c, TOBase t) throws Exception;
            
    public TOBase get(Connection c, TOBase t) throws Exception;
    
    public JSONArray listar(Connection c) throws Exception;
    
    //public JSONArray listarrecebidos(Connection c) throws Exception;
            
    public TOBase getLogin(Connection c, TOBase t) throws  Exception;
}
