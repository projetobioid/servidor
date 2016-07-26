/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import org.json.JSONArray;
import to.TOBase;
import to.TOPropriedade;
import to.TOSafra;

/**
 *
 * @author Aimee
 */
public class DAOPropriedade implements DAOBase{

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
        String sql = "select * from propriedade where LOWER(nomepropriedade) = LOWER(?)";
        
        ResultSet rs = null;
        
        try{
            TOPropriedade to = (TOPropriedade)t;
            rs = Data.executeQuery(c, sql, to.getNomepropriedade());
            
            if(rs.next()){
                return new TOPropriedade(rs);
            }else{
                return null;
            }
        }finally{
            rs.close();
        }
    }

    @Override
    public JSONArray listar(Connection c) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TOBase getLogin(Connection c, TOBase t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JSONArray listarrecebidos(Connection c, TOSafra t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
