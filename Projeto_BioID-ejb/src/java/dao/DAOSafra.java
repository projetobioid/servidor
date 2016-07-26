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
import to.TOSafra;

/**
 *
 * @author Aimee
 */
public class DAOSafra implements DAOBase{

    @Override
    public long inserir(Connection c, TOBase t) throws Exception {
        String sql = "INSERT INTO safra(propriedade_idpropriedade, cultivar_idcultivar, idsafra, datareceb,qtdrecebida, relatada, datacolheita, qtdconsumida, qtdreplatar,qtddestinada, destino) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?);";
        
        TOSafra to = (TOSafra)t;
        
        List<Object> p = new ArrayList<Object>();
        
        
        p.add(to.getPropriedade_idpropriedade());
        p.add(to.getCultivar_idcultivar());
        p.add(to.getIdsafra());
        p.add(to.getDatareceb());
        p.add(to.getQtdrecebida());
        p.add(to.isRelatada());
        p.add(to.getDatacolheita());
        p.add(to.getQtdconsumida());
        p.add(to.getQtdreplatar());
        p.add(to.getQtddestinada());
        p.add(to.getDestino());
        
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        return Data.executeUpdate(c, sql, p);
    }

    @Override
    public void editar(Connection c, TOBase t) throws Exception {
        String sql = "UPDATE safra SET relatada=?, datacolheita=?, qtdconsumida=?, qtdreplatar=?, qtddestinada=?, destino=? WHERE propriedade_idpropriedade = ? and cultivar_idcultivar = ? and idsafra = ?";

        
        TOSafra to = (TOSafra)t;
        
        List<Object> p = new ArrayList<Object>();
        
        
        p.add(to.isRelatada());
        p.add(to.getDatacolheita());
        p.add(to.getQtdconsumida());
        p.add(to.getQtdreplatar());
        p.add(to.getQtddestinada());
        p.add(to.getDestino());
        p.add(to.getPropriedade_idpropriedade());
        p.add(to.getCultivar_idcultivar());
        p.add(to.getIdsafra());
        
        
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto        
        Data.executeUpdate(c, sql, p);
        
    }

    @Override
    public void excluir(Connection c, TOBase t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TOBase get(Connection c, TOBase t) throws Exception {
        String sql = "select * from safra where LOWER(idsafra) = LOWER(?)";
        
        ResultSet rs = null;
        
        try{
            TOSafra to = (TOSafra)t;
            rs = Data.executeQuery(c, sql, to.getIdsafra());
            
            if(rs.next()){
                return new TOSafra(rs);
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
        JSONArray  ja = new JSONArray();
        
        String sql = "SELECT * FROM safra where propriedade_idpropriedade = ?";
        
        ResultSet rs = null;
        
        try{
            //variavel com lista dos parametros
            List<Object> u = new ArrayList<Object>();
            
            u.add(t.getPropriedade_idpropriedade());
            
            rs = Data.executeQuery(c, sql, u);
            
            while (rs.next()){
                TOSafra ts = new TOSafra(rs);
                ja.put(ts.getJson());
            }
        }finally{
            rs.close();
        }
        return ja;
    }
    
}
