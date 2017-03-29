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
 * @author Daniel
 */
public class DAORelacaopa extends DAOBase{

    @Override
    public long inserir(Connection c, List<Object> u) throws Exception {
        String sql = null;

        sql = "INSERT INTO relacaopa(propriedade_idpropriedade, agricultor_pessoa_idpessoa) VALUES (?, ?)";
       
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        return Data.executeUpdate(c, sql, u);
    }

}
