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

/**
 *
 * @author Daniel
 */
public class DAODestinacao extends DAOBase{

    @Override
    public long inserir(Connection c, List<Object> u) throws Exception {
        
        String sql = "INSERT INTO destinacao(safra_idsafra, tipodestinacao_idtipodestinacao, datadestinada, qtddestinada) VALUES (?, ?, ?, ?)";
        
        List<Object> p = new ArrayList<Object>();
        
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        return Data.executeUpdate(c, sql, p);
    }

}
