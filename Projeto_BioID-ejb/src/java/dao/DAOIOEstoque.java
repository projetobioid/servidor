/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import fw.Data;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author daniel
 */
public class DAOIOEstoque extends DAOBase{

    @Override
    public long inserir(Connection c, List<Object> u) throws Exception {
        
        String sql = "INSERT INTO ioestoque(estoque_unidade_idunidade, estoque_cultivar_idcultivar, quantidade, data_io, operacao, login_usuario) VALUES (?, ?, ?, ?, ?, ?)";
          
        //passa por parametros a conexao e a lista de objetos da insercao
        return Data.executeUpdate(c, sql, u);
    }
    
    
}
