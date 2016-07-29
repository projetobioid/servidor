/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import to.TOBase;
import to.TOUnidade;

/**
 *
 * @author daniel
 */
public class DAOUnidade implements DAOBase{

    @Override
    public long inserir(Connection c, TOBase t) throws Exception {
        String sql = "INSERT INTO unidade(endereco_idendereco, nomeunidade, telefone1, telefone2, email, cnpj, razao_social, nome_fanta) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        TOUnidade to = (TOUnidade)t;
        
        List<Object> p = new ArrayList<Object>();
        
        
        p.add(to.getEndereco_idendereco());
        p.add(to.getNomeunidade());
        p.add(to.getTelefone1());
        p.add(to.getTelefone2());
        p.add(to.getEmail());
        p.add(to.getCnpj());
        p.add(to.getRazao_social());
        p.add(to.getNome_fanta());
        
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
        String sql = "SELECT * FROM unidade where LOWER(nomeunidade) = LOWER(?) OR cnpj = ?";
        
        ResultSet rs = null;
        List<Object> p = new ArrayList<Object>();
        
        try{
            TOUnidade to = (TOUnidade)t;
            p.add(to.getNomeunidade());
            p.add(to.getCnpj());
            rs = Data.executeQuery(c, sql, p);
            
            if(rs.next()){
                return new TOUnidade(rs);
            }else{
                return null;
            }
        }finally{
            rs.close();
        }
    }

    @Override
    public JSONArray listar(Connection c) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TOBase getLogin(Connection c, TOBase t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JSONArray listar(Connection c, TOBase t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
