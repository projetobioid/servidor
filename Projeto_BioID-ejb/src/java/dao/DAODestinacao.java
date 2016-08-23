/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import to.TOBase;
import to.TODestinacao;

/**
 *
 * @author Aimee
 */
public class DAODestinacao implements DAOBase{

    @Override
    public long inserir(Connection c, TOBase t) throws Exception {
        String sql = "INSERT INTO destinacao(safrarelatada_safra_idsafra, safrarelatada_idsafrarelatada, unidademedida_idunidademedida, tipodestinacao_idtipodestinacao, datadestinada, qtddestinada) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        
        TODestinacao to = (TODestinacao)t;
        
        List<Object> p = new ArrayList<Object>();
        
        
        p.add(to.getSafrarelatada_safra_idsafra());
        p.add(to.getSafrarelatada_idsafrarelatada());
        p.add(to.getUnidademedida_idunidademedida());
        p.add(to.getTipodestinacao_idtipodestinacao());
        p.add(to.getDatadestinada());
        p.add(to.getQtddestinada());
        
        
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        return Data.executeUpdate(c, sql, p);
    }

    @Override
    public void editar(Connection c, TOBase t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir(Connection c, TOBase t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TOBase get(Connection c, TOBase t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public JSONArray listar(Connection c) throws Exception {
           throw new UnsupportedOperationException("Not supported yet.");  
    }

    @Override
    public JSONArray listar(Connection c, TOBase t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TOBase getLogin(Connection c, TOBase t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
