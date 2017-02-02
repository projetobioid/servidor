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
import to.TOHistoricoColheita;

/**
 *
 * @author daniel
 */
public class DAOHistoricoColheita extends DAOBase{

    @Override
    public long inserir(Connection c, TOBase t, String metodo) throws Exception {
        String sql = null;
        
        
//        TOHistoricoColheita to = ((TOHistoricoColheita)t);
        
        List<Object> p = new ArrayList<Object>();
        
        switch(metodo){
            default:
                sql = "INSERT INTO historicocolheita(safra_idsafra, datacolheita, qtdcolhida) VALUES ( ?, ?, ?)";
                p.add(((TOHistoricoColheita)t).getSafra_idsafra());
                p.add(((TOHistoricoColheita)t).getDatacolheita());
                p.add(((TOHistoricoColheita)t).getQtdcolhida());
                break;
        }
        
        
        
        
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        return Data.executeUpdate(c, sql, p);
    }


    @Override
    public JSONArray listar(Connection c, TOBase t, String metodo) throws Exception {
        JSONArray  ja = new JSONArray();
        
        
           
        ResultSet rs = null;
        try{
            String sql = null;
            //variavel com lista dos parametros
            List<Object> u = new ArrayList<Object>();
            
            switch(metodo){
                default:
                    sql = "SELECT idcolheita, safra_idsafra, unidademedida_idunidademedida, datacolheita, qtdcolhida FROM colheita WHERE safra_idsafra IN(?)";
                    u.add(((TOHistoricoColheita) t).getSafra_idsafra());
                    break;
            }
            
            
            rs = Data.executeQuery(c, sql, u);
            
            while (rs.next()){
                TOHistoricoColheita ts = new TOHistoricoColheita(rs, metodo);
                ja.put(ts.getJson(metodo));
                
            }
            
                        
        }finally{
            rs.close();
        }
        return ja;
    }

    @Override
    public TOBase get(Connection c, TOBase t, String metodo) throws Exception {
         //string com o comando sql para editar o banco de dados
        
        
        ResultSet rs = null;
        
        try{
            String sql = null;
            //variavel sendo convertida para toUsuarios
            TOHistoricoColheita to = (TOHistoricoColheita)t;
            //variavel com lista dos parametros
            List<Object> u = new ArrayList<Object>();
            
            switch(metodo){
                default:
                    sql = "SELECT SUM(qtdcolhida) AS somaqtdcolhida FROM historicocolheita WHERE safra_idsafra IN(?)";
                    u.add(to.getSafra_idsafra());
                    break;
                
            }
            
            
            rs = Data.executeQuery(c, sql, u);
            
            
            if(rs.next()){
                return new TOHistoricoColheita(rs, metodo);
            }else{
                return null;
            }
        }finally{
            rs.close();
        }
    }


}
