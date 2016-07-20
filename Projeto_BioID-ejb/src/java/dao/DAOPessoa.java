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
import to.TOPessoa;

/**
 *
 * @author daniel
 */
public class DAOPessoa implements DAOBase{


    @Override
    public void inserir(Connection c, TOBase t) throws Exception {
        //string com o comando sql para editar o banco de dados
        String sql = "INSERT INTO pessoa(unidade_idunidade, endereco_idendereco, login_idlogin,escolaridade_idescolaridade,"
                + " estadocivil_idestadocivil, nome, sobrenome, apelido, cpf, rg, sexo, telefone1,"
                + " telefone2, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        //variavel sendo convertida para toUsuarios
        TOPessoa to = (TOPessoa)t;
        //variavel com lista dos parametros
        List<Object> u = new ArrayList<Object>();
        
        
        u.add(to.getUnidade_idunidade());
        u.add(to.getEndereco_idendereco());
        u.add(to.getLogin_idlogin());
        u.add(to.getEscolaridade());
        u.add(to.getEstadocivil_idestadocivil());
        u.add(to.getNome());
        u.add(to.getSobrenome());
        u.add(to.getApelido());
        u.add(to.getCpf());
        u.add(to.getRg());
       // u.add(to.getDatanascimento());
        u.add(to.getSexo());
        u.add(to.getTelefone1());
        u.add(to.getTelefone2());
        u.add(to.getEmail());
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        Data.executeUpdate(c, sql, u);
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
    
    

    
}