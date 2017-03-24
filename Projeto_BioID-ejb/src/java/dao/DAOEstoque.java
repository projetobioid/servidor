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
public class DAOEstoque extends DAOBase{

    
    @Override
    public JSONObject buscar(Connection c, List<Object> u, String metodo) throws Exception {

        ResultSet rs = null;
        
        try{
            String sql = null;
            
            
            switch(metodo){
                case "GET_CULTIVAR":
                    sql = "SELECT e.unidade_idunidade, e.cultivar_idcultivar, e.quantidade, um.grandeza FROM estoque e "
                        + "INNER JOIN cultivar c ON(c.idcultivar = e.cultivar_idcultivar) "
                        + "INNER JOIN unidademedida um ON(um.idunidademedida = c.unidademedida_idunidademedida) "
                        + "WHERE cultivar_idcultivar IN(?) AND unidade_idunidade IN(?)";

                    break;
                default:
                    sql = "SELECT * FROM estoque "
                        + "WHERE cultivar_idcultivar IN(?) AND unidade_idunidade IN(?)";
                    break;
            }
            
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

    @Override
    public long inserir(Connection c, List<Object> u) throws Exception {
        //string com o comando sql para editar o banco de dados
        String sql = null;
  
           
        sql = "INSERT INTO estoque(unidade_idunidade, cultivar_idcultivar, quantidade) VALUES (?, ?, ?)";
        
        //passa por parametros a conexao e a lista de objetos da insercao
        return Data.executeUpdate(c, sql, u);
    }

    @Override
    public void editar(Connection c, List<Object> u) throws Exception {
    
        List<Object> p = new ArrayList<Object>();
        
        String sql = "UPDATE estoque set quantidade = ? WHERE unidade_idunidade IN(?) AND cultivar_idcultivar IN(?)";
       
        Data.executeUpdate(c, sql, p);
    }

    @Override
    public JSONArray listar(Connection c, List<Object> u, String metodo) throws Exception {
        
        ResultSet rs = null;
        try{
            
            
            String sql = null;
            
            switch(metodo){
                case "TODOS":
                    sql = "SELECT e.unidade_idunidade, e.cultivar_idcultivar, u.grandeza, e.quantidade, c.nomecultivar FROM estoque e"
                        + " INNER JOIN cultivar c ON (c.idcultivar = e.cultivar_idcultivar)"
                        + " INNER JOIN unidademedida u ON (u.idunidademedida = c.unidademedida_idunidademedida)"
                        + " WHERE unidade_idunidade IN(?) AND e.quantidade > 0";
                    
                    break;
                case "estoqueunidade_select":
                    sql = "SELECT e.unidade_idunidade, e.cultivar_idcultivar, u.grandeza, e.quantidade, c.nomecultivar FROM estoque e"
                        + " INNER JOIN cultivar c ON (c.idcultivar = e.cultivar_idcultivar)"
                        + " INNER JOIN unidademedida u ON (u.idunidademedida = c.unidademedida_idunidademedida)"
                        + " WHERE unidade_idunidade IN(?) AND e.quantidade > 0";

                    break;
                default:
                    sql = "SELECT * FROM estoque";
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
    
}
