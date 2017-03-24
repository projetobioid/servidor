/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import fw.Data;
import static fw.Mapeamento.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Daniel
 */
public class DAOHistoricoColheita extends DAOBase{

    @Override
    public long inserir(Connection c, List<Object> u) throws Exception {
        String sql = null;
  
        List<Object> p = new ArrayList<Object>();
     
        sql = "INSERT INTO historicocolheita(safra_idsafra, datacolheita, qtdcolhida) VALUES ( ?, ?, ?)";

        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        return Data.executeUpdate(c, sql, p);
    }


    @Override
    public JSONArray listar(Connection c, List<Object> u, String metodo) throws Exception {

        ResultSet rs = null;
        try{
            String sql = null;
            
            switch(metodo){
                default:
                    sql = "SELECT idcolheita, safra_idsafra, unidademedida_idunidademedida, datacolheita, qtdcolhida FROM colheita WHERE safra_idsafra IN(?)";

                    break;
            }
            
            
            rs = Data.executeQuery(c, sql, u);
            
            if(rs.next()){
                return MapeamentoJsonArray(rs);
            }else{
                return null;
            }
            
                        
        }finally{
            rs.close();
        }
    }

    @Override
    public JSONObject buscar(Connection c, List<Object> u) throws Exception {
         //string com o comando sql para editar o banco de dados

        ResultSet rs = null;
        
        try{

            String sql = "SELECT SUM(qtdcolhida) AS somaqtdcolhida FROM historicocolheita WHERE safra_idsafra IN(?)";

            rs = Data.executeQuery(c, sql, u);
            
            if(rs.next()){
                return MapeamentoJson(rs);
            }else{
                return null;
            }
            
        }finally{
            rs.close();
        }
    }


}
