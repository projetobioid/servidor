/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import to.TOBase;
import to.TOIOEstoque;

/**
 *
 * @author daniel
 */
public class DAOIOEstoque extends DAOBase{

    @Override
    public long inserir(Connection c, TOBase t, String metodo) throws Exception {
        //string com o comando sql para editar o banco de dados
        String sql = null;
        
        //variavel sendo convertida para toUsuarios
//        TOIOEstoque to = ((TOIOEstoque)t);
        //variavel com lista dos parametros
        List<Object> u = new ArrayList<Object>();
        
        switch(metodo){
            default:
                sql = "INSERT INTO ioestoque(estoque_unidade_idunidade, estoque_cultivar_idcultivar, quantidade, data_io, operacao, login_idlogin) VALUES (?, ?, ?, ?, ?, ?)";
        
                u.add(((TOIOEstoque)t).getUnidade_idunidade());
                u.add(((TOIOEstoque)t).getCultivar_idcultivar());
                u.add(((TOIOEstoque)t).getQuantidade());
                u.add(((TOIOEstoque)t).getData_io());
                u.add(((TOIOEstoque)t).getOperacao());
                u.add(((TOIOEstoque)t).getLogin_idlogin());
                break;
        }
        //passa por parametros a conexao e a lista de objetos da insercao
        return Data.executeUpdate(c, sql, u);
    }
    
    
}
