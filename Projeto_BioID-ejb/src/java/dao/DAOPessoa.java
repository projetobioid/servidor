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
import to.TOPessoa;

/**
 *
 * @author daniel
 */
public class DAOPessoa extends DAOBase{

    @Override
    public void inserir(Connection c, TOBase t) throws Exception {
        String sql = "insert into pessoa (nome) values (?)";
        
        TOPessoa to = (TOPessoa)t;
        
        List<Object> p = new ArrayList<Object>();
        
       
        p.add(to.getNome());
        
        
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        Data.executeUpdate(c, sql, p);
    }
    
}
