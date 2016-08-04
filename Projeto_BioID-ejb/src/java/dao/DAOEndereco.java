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
import to.TOEndereco;

/**
 *
 * @author daniel
 */
public class DAOEndereco implements DAOBase{

    @Override
    public long inserir(Connection c, TOBase t) throws Exception {
        //string com o comando sql para editar o banco de dados
        String sql = "INSERT INTO endereco(cidade_idcidade, rua, gps_lat, gps_long, bairro, complemento, cep, numero) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        //variavel sendo convertida para toUsuarios
        TOEndereco to = (TOEndereco)t;
        //variavel com lista dos parametros
        List<Object> u = new ArrayList<Object>();
        
        
        u.add(to.getCidade_idcidade());
        u.add(to.getRua());
        u.add(to.getGps_lat());
        u.add(to.getGps_long());
        u.add(to.getBairro());
        u.add(to.getComplemento());
        u.add(to.getCep());
        u.add(to.getNumero());
        
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        return Data.executeUpdate(c, sql, u);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
