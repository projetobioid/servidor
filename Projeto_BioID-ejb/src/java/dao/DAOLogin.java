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
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author Daniel
 */
public class DAOLogin extends DAOBase{

    @Override
    public void inserirIDString(Connection c, List<Object> u) throws Exception {
        //string com o comando sql para editar o banco de dados
        String sql = null;
        
        sql = "INSERT INTO login(pessoa_idpessoa, unidade_idunidade, usuario, senha) VALUES (?, ?, ?, ?)";

   
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        Data.executeUpdateString(c, sql, u);
    }

   
    @Override
    public JSONObject buscar(Connection c, List<Object> u, String metodo) throws Exception {
         //string com o comando sql para editar o banco de dados
        String sql = null;
        ResultSet rs = null;
        
        try{
            
            //testa o metodo a ser executado
            switch(metodo){
                case "VALIDACAO" :
                sql = "SELECT l.usuario, l.unidade_idunidade, p.nome, g.grupo "
                    + "FROM login l "
                    + "INNER JOIN pessoa p ON(p.idpessoa = l.pessoa_idpessoa) "
                    + "INNER JOIN grupos g ON(g.loginusuario = l.usuario) "
                    + "WHERE usuario IN(?) AND senha IN(?)";
                
                break;
                case "GET_POR_USUARIO" :
                    sql = "SELECT * FROM login WHERE usuario IN(?)";
                
                    break;
                default:
                    sql = "SELECT * FROM login";
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
    public void editar(Connection c, List<Object> u) throws Exception {
        
        String sql = null;
       
        sql = "update login set sessao = ? where idlogin = ? ";

        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto        
        Data.executeUpdate(c, sql, u);
    }

   
}
