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
import to.TODestinacao;

/**
 *
 * @author Aimee
 */
public class DAODestinacao extends DAOBase{

    @Override
    public long inserir(Connection c, TOBase t, String metodo) throws Exception {
        String sql = "INSERT INTO destinacao(safra_idsafra, tipodestinacao_idtipodestinacao, datadestinada, qtddestinada) VALUES (?, ?, ?, ?)";
        
//        TODestinacao to = ((TODestinacao)t);
        
        List<Object> p = new ArrayList<Object>();
        
        
        p.add(((TODestinacao)t).getSafra_idsafra());
        p.add(((TODestinacao)t).getTipodestinacao_idtipodestinacao());
        p.add(((TODestinacao)t).getDatadestinada());
        p.add(((TODestinacao)t).getQtddestinada());
        
        
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        return Data.executeUpdate(c, sql, p);
    }

}
