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
public class DAOBase {
    
    protected long idretorno;

    public long inserir(Connection c, TOBase t) throws Exception{
        return this.idretorno;
    }
    
    public void editar(Connection c, TOBase t) throws Exception{
    }
    
    public void excluir(Connection c, TOBase t) throws Exception{
    }
    
    public TOBase get(Connection c, TOBase t) throws Exception{
        return null;
    }
    
    public JSONArray listar(Connection c) throws Exception{
        return null;
    }
    
    public JSONArray listar(Connection c, TOBase t) throws Exception{
        return null;
    }
    
    public JSONArray listarAgricultoresUnidade(Connection c, TOBase t) throws Exception{
        return null;
    }
    
    public JSONArray backupentrevista(Connection c, TOBase t) throws Exception{
        return null;
    }
    
    
}
