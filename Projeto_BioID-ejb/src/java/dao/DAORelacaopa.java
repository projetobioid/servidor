/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import fw.Data;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import to.TOBase;
import to.TORelacaopa;

/**
 *
 * @author Aimee
 */
public class DAORelacaopa extends DAOBase{

    @Override
    public long inserir(Connection c, TOBase t, String metodo) throws Exception {
        String sql = null;

//        TORelacaopa to = ((TORelacaopa)t);
        
        List<Object> p = new ArrayList<Object>();
        
        switch(metodo){
            default:
                sql = "INSERT INTO relacaopa(agricultor_pessoa_idpessoa, propriedade_idpropriedade) VALUES (?, ?)";
                p.add(((TORelacaopa)t).getAgricultor_pessoa_idpessoa());
                p.add(((TORelacaopa)t).getPropriedade_idpropriedade());
                break;
        }
        
        
        
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        return Data.executeUpdate(c, sql, p);
    }

}
