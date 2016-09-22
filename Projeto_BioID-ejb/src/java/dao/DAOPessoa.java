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
import to.TOPessoa;
import to.TOUnidade;

/**
 *
 * @author daniel
 */
public class DAOPessoa extends DAOBase{


    @Override
    public long inserir(Connection c, TOBase t) throws Exception {
        //string com o comando sql para editar o banco de dados
        String sql = "INSERT INTO pessoa(estadocivil_idestadocivil, escolaridade_idescolaridade, endereco_idendereco, nome,"
                + " sobrenome, apelido, cpf, rg, datanascimento, sexo, telefone1, telefone2, email) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        //variavel sendo convertida para toUsuarios
        TOPessoa to = (TOPessoa)t;
        //variavel com lista dos parametros
        List<Object> u = new ArrayList<Object>();
        
        u.add(to.getEstadocivil_idestadocivil());
        u.add(to.getEscolaridade_idescolaridade());
        u.add(to.getEndereco_idendereco());
        u.add(to.getNome());
        u.add(to.getSobrenome());
        u.add(to.getApelido());
        u.add(to.getCpf());
        u.add(to.getRg());
        u.add(to.getDatanascimento());
        u.add(to.getSexo());
        u.add(to.getTelefone1());
        u.add(to.getTelefone2());
        u.add(to.getEmail());
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        return Data.executeUpdate(c, sql, u);
    }

    @Override
    public TOBase get(Connection c, TOBase t) throws Exception {
        String sql = "SELECT * FROM pessoa where cpf = ?";
        
        ResultSet rs = null;
        
        try{
            TOPessoa to = (TOPessoa)t;
            rs = Data.executeQuery(c, sql, to.getCpf());
            
            if(rs.next()){
                return new TOPessoa(rs);
            }else{
                return null;
            }
        }finally{
            rs.close();
        }
    }

    @Override
    public JSONArray listarAgricultoresUnidade(Connection c, TOBase t) throws Exception {
        JSONArray  ja = new JSONArray();
  
        ResultSet rs = null;
        try{
            //variavel com lista dos parametros
            List<Object> u = new ArrayList<Object>();
            
            String sql = "SELECT p.nome, p.sobrenome, l.usuario FROM pessoa p "
                     + "INNER JOIN login l ON(l.pessoa_idpessoa = p.idpessoa) "
                     + "INNER JOIN agricultor a ON(a.pessoa_idpessoa = p.idpessoa) "
                     + "where l.unidade_idunidade IN(?) AND a.pessoa_idpessoa IN(p.idpessoa)";
             
            u.add(((TOUnidade) t).getIdunidade());
            
            rs = Data.executeQuery(c, sql, u);
            
            while (rs.next()){
                TOPessoa ts = new TOPessoa().listarAgricultoresUnidade(rs);
                ja.put(ts.getJsonSimples());
            }
            
                        
        }finally{
            rs.close();
        }
        return ja;
    }

    
    
}