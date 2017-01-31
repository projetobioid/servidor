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
    public long inserir(Connection c, TOBase t) throws Exception {
        //string com o comando sql para editar o banco de dados
        String sql = "INSERT INTO ioestoque(estoque_unidade_idunidade, estoque_cultivar_idcultivar, unidademedida_idunidademedida, quantidade, data_io, operacao, login_idlogin) VALUES (?, ?, ?, ?, ?, ?, ?)";
        //variavel sendo convertida para toUsuarios
        TOIOEstoque to = (TOIOEstoque)t;
        //variavel com lista dos parametros
        List<Object> u = new ArrayList<Object>();
        
        u.add(to.getUnidade_idunidade());
        u.add(to.getCultivar_idcultivar());
        u.add(to.getUnidademedida_idunidademedida());
        u.add(to.getQuantidade());
        u.add(to.getData_io());
        u.add(to.getOperacao());
        u.add(to.getLogin_idlogin());
        
        //passa por parametros a conexao e a lista de objetos da insercao
        return Data.executeUpdate(c, sql, u);
    }
    
    
}
