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
    public JSONArray listar(Connection c, String metodo) throws Exception {
        JSONArray  ja = new JSONArray();
        String sql = null;
        ResultSet rs = null;
        
        try{
            
            if(metodo.equals("listaragricultores")){
                sql = "SELECT p.idpessoa, p.nome, p.sobrenome, p.cpf, p.rg, p.telefone1, u.nomeunidade "
                    + "FROM pessoa p "
                    + "INNER JOIN agricultor a ON (a.pessoa_idpessoa = p.idpessoa) "
                    + "INNER JOIN relacaopa rpa ON (rpa.agricultor_pessoa_idpessoa = p.idpessoa) "
                    + "INNER JOIN propriedade pd ON (pd.idpropriedade = rpa.propriedade_idpropriedade) "
                    + "INNER JOIN unidade u ON (u.idunidade = pd.idpropriedade) "
                    + "INNER JOIN login l ON (l.pessoa_idpessoa = p.idpessoa) "
                    + "WHERE l.papel IN('a')";

            }else if(metodo.equals("listarusuarios")){
                sql = "SELECT p.idpessoa, p.nome, p.sobrenome, p.cpf, p.rg, p.telefone1, u.nomeunidade "
                    + "FROM pessoa p "
                    + "INNER JOIN login l ON (l.pessoa_idpessoa = p.idpessoa) "
                    + "INNER JOIN unidade u ON (u.idunidade = l.unidade_idunidade) "
                    + "WHERE l.papel IN('x') OR l.papel IN('e') OR l.papel IN('g')";
            }    
            
            rs = Data.executeQuery(c, sql);

            while (rs.next()){
                TOPessoa t = new TOPessoa(rs, metodo);
                ja.put(t.getJson(metodo));
            }
                
            
            
        }finally{
            rs.close();
        }
        return ja;
    }

    
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
    public TOBase get(Connection c, TOBase t, String metodo) throws Exception {
        ResultSet rs = null;
        String sql = null;
        TOPessoa to = (TOPessoa)t;
        
        try{
            
            if(metodo.equals("buscaragricultor")){
                sql = "SELECT p.idpessoa, p.nome, p.sobrenome, p.apelido, p.cpf, p.rg, p.datanascimento, p.sexo, p.telefone1, p.telefone2, p.email, a.qtdintegrantes, a.qtdcriancas, a.qtdgravidas, est.descricao as estadocivil, esc.descricao as escolaridade "
                        + "FROM pessoa p "
                        + "INNER JOIN agricultor a ON(a.pessoa_idpessoa = p.idpessoa) "
                        + "INNER JOIN estadocivil est ON(est.idestadocivil = p.estadocivil_idestadocivil) "
                        + "INNER JOIN escolaridade esc ON(esc.idescolaridade = p.escolaridade_idescolaridade) "
                        + "WHERE p.idpessoa IN(?)";  
                rs = Data.executeQuery(c, sql, to.getIdpessoa());

                
            }else if(metodo.equals("buscarusuario")){
                sql = "SELECT p.idpessoa, p.nome, p.sobrenome, p.apelido, p.cpf, p.rg, p.datanascimento, p.sexo, p.telefone1, p.telefone2, p.email, est.descricao as estadocivil, esc.descricao as escolaridade "
                        + "FROM pessoa p "
                        + "INNER JOIN estadocivil est ON(est.idestadocivil = p.estadocivil_idestadocivil) "
                        + "INNER JOIN escolaridade esc ON(esc.idescolaridade = p.escolaridade_idescolaridade) "
                        + "WHERE p.idpessoa IN(?)";  
                rs = Data.executeQuery(c, sql, to.getIdpessoa());

            }
            
            if(rs.next()){
                return new TOPessoa(rs, metodo);
            }else{
                return null;
            }
        
        
        
            
            
        }finally{
            rs.close();
        }
    }
//
//    @Override
//    public JSONArray listar(Connection c, TOBase t, String metodo) throws Exception {
//        JSONArray  ja = new JSONArray();
//        String sql;
//        ResultSet rs = null;
//        try{
//            //variavel com lista dos parametros
//            List<Object> u = new ArrayList<Object>();
//            
//                sql = "SELECT DISTINCT p.idpessoa, p.nome, p.sobrenome, p.cpf, p.rg, p.telefone1, u.nomeunidade "
//                    + "FROM pessoa p "
//                    + "INNER JOIN login l ON(l.pessoa_idpessoa = p.idpessoa) "
//                    + "INNER JOIN agricultor a ON(a.pessoa_idpessoa = p.idpessoa) "
//                    + "INNER JOIN relacaopa rpa ON(rpa.agricultor_pessoa_idpessoa = a.pessoa_idpessoa) "
//                    + "INNER JOIN propriedade pr ON(pr.idpropriedade = rpa.propriedade_idpropriedade) "
//                    + "INNER JOIN safra s ON(s.propriedade_idpropriedade = pr.idpropriedade) "
//                    + "INNER JOIN unidade u ON(u.idunidade = l.unidade_idunidade) "
//                    + "WHERE l.unidade_idunidade IN(?) AND a.pessoa_idpessoa IN(p.idpessoa) AND l.papel IN('a')";
//             
//            u.add(((TOUnidade) t).getIdunidade());
//            
//            rs = Data.executeQuery(c, sql, u);
//            
//            while (rs.next()){
//                TOPessoa ts = new TOPessoa(rs);
//                ja.put(ts.getJson());
//            }
//            
//                        
//        }finally{
//            rs.close();
//        }
//        return ja;
//    }

    
    
}