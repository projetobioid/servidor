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
import to.TOColheita;

/**
 *
 * @author daniel
 */
public class DAOColheita implements DAOBase{

    @Override
    public long inserir(Connection c, TOBase t) throws Exception {
        String sql = "INSERT INTO safrarelatada(safra_idsafra, unidademedida_idunidademedida, datacolheita, qtdcolhida) VALUES (?, ?, ?, ?)";
        
        TOColheita to = (TOColheita)t;
        
        List<Object> p = new ArrayList<Object>();
        
        
        p.add(to.getSafra_idsafra());
        p.add(to.getUnidademedida_idunidademedida());
        p.add(to.getDatacolheita());
        p.add(to.getQtdcolhida());
        
        
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        return Data.executeUpdate(c, sql, p);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JSONArray listar(Connection c) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JSONArray listar(Connection c, TOBase t) throws Exception {
        JSONArray  ja = new JSONArray();
        
        String sql = "SELECT * FROM safrarelatada WHERE safra_idsafra = ?";
           
        
        
        ResultSet rs = null;
        try{
            //variavel com lista dos parametros
            List<Object> u = new ArrayList<Object>();
            
            u.add(((TOColheita) t).getSafra_idsafra());
            
            rs = Data.executeQuery(c, sql, u);
            
            while (rs.next()){
                TOColheita ts = new TOColheita(rs);
                ja.put(ts.getJson());
            }
            
                        
        }finally{
            rs.close();
        }
        return ja;
    }

    @Override
    public TOBase getLogin(Connection c, TOBase t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
