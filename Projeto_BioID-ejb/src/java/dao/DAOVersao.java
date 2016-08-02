/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import to.TOBase;
import to.TOVersao;

/**
 *
 * @author Aimee
 */
public class DAOVersao implements DAOBase{

    @Override
    public long inserir(Connection c, TOBase t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editar(Connection c, TOBase t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir(Connection c, TOBase t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TOBase get(Connection c, TOBase t) throws Exception {
        String sql = "SELECT * FROM versao WHERE descricao = ?";
        
        ResultSet rs = null;
        List<Object> p = new ArrayList<Object>();

        try{
            TOVersao to = (TOVersao)t;
            p.add(to.getDescricao());
            
            rs = Data.executeQuery(c, sql, p);
            
            if(rs.next()){
                return new TOVersao(rs);
            }else{
                return null;
            }
        }finally{
            rs.close();
        }
 
    }

    @Override
    public JSONArray listar(Connection c) throws Exception {
           throw new UnsupportedOperationException("Not supported yet.");  
    }

    @Override
    public JSONArray listar(Connection c, TOBase t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TOBase getLogin(Connection c, TOBase t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
