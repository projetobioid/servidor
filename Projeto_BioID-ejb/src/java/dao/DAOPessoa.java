/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import fw.Data;
import static fw.Mapeamento.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import to.TOBase;
import to.TOPessoa;
import to.TOUnidade;

/**
 *
 * @author Daniel
 */
public class DAOPessoa extends DAOBase{
    
    @Override
    public long inserir(Connection c, TOBase t) throws Exception {
        //string com o comando sql para editar o banco de dados
        String sql = null;
        
        //variavel com lista dos parametros
        List<Object> u = new ArrayList<Object>();
        
        sql = "INSERT INTO pessoa(estadocivil_idestadocivil, escolaridade_idescolaridade, endereco_idendereco, nome,"
        + " sobrenome, apelido, cpf, rg, datanascimento, sexo, telefone1, telefone2, email) "
        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        u.add(((TOPessoa)t).getEstadocivil_idestadocivil());
        u.add(((TOPessoa)t).getEscolaridade_idescolaridade());
        u.add(((TOPessoa)t).getEndereco_idendereco());
        u.add(((TOPessoa)t).getNome());
        u.add(((TOPessoa)t).getSobrenome());
        u.add(((TOPessoa)t).getApelido());
        u.add(((TOPessoa)t).getCpf());
        u.add(((TOPessoa)t).getRg());
        u.add(((TOPessoa)t).getDatanascimento());
        u.add(((TOPessoa)t).getSexo());
        u.add(((TOPessoa)t).getTelefone1());
        u.add(((TOPessoa)t).getTelefone2());
        u.add(((TOPessoa)t).getEmail());
                    
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        return Data.executeUpdate(c, sql, u);
    }

//    @Override
//    public JSONObject buscar(Connection c, TOBase t, String metodo) throws Exception {
//        
//        ResultSet rs = null;
//        
//        String sql = null;
//        
//        try{
//            List<Object> u = new ArrayList<Object>();
//            
//            switch(metodo){
//                case "GET_POR_ID":
//                    sql = "SELECT p.idpessoa, p.nome, p.sobrenome, p.apelido, p.cpf, p.rg, p.datanascimento, p.sexo, p.telefone1, p.telefone2, p.email, a.qtdintegrantes, a.qtdcriancas, a.qtdgravidas, est.descricao as estadocivil, esc.descricao as escolaridade "
//                        + "FROM pessoa p "
//                        + "INNER JOIN agricultor a ON(a.pessoa_idpessoa = p.idpessoa) "
//                        + "INNER JOIN estadocivil est ON(est.idestadocivil = p.estadocivil_idestadocivil) "
//                        + "INNER JOIN escolaridade esc ON(esc.idescolaridade = p.escolaridade_idescolaridade) "
//                        + "WHERE p.idpessoa IN(?)"; 
//                    u.add(((TOPessoa)t).getIdpessoa());
//                    break;
//                case "GET_MEMBRO":
//                    sql = "SELECT p.idpessoa, p.nome, p.sobrenome, p.apelido, p.cpf, p.rg, p.datanascimento, p.sexo, p.telefone1, p.telefone2, p.email, est.descricao as estadocivil, esc.descricao as escolaridade, g.grupo, u.nomeunidade "
//                        + "FROM pessoa p "
//                        + "INNER JOIN estadocivil est ON(est.idestadocivil = p.estadocivil_idestadocivil) "
//                        + "INNER JOIN escolaridade esc ON(esc.idescolaridade = p.escolaridade_idescolaridade) "
//                        + "INNER JOIN login l ON (l.pessoa_idpessoa = p.idpessoa)"
//                        + "INNER JOIN unidade u ON (u.idunidade = l.unidade_idunidade)"
//                        + "INNER JOIN grupos g ON (g.loginusuario = l.usuario)"
//                        + "WHERE p.idpessoa IN(?)";
//                    u.add(((TOPessoa)t).getIdpessoa());
//                    break;
//                case "GET_POR_CPF":
//                    sql = "SELECT * FROM pessoa WHERE cpf IN(?)";
//                    u.add(((TOPessoa)t).getCpf());
//                    break;
//            }
//         
//            rs = Data.executeQuery(c, sql, u);
//            
//            if(rs.next()){
//                return MapeamentoJson(rs);  
//            }else{
//                return null;
//            }
//        
//        }finally{
//            rs.close();
//        }
//    }

    @Override
    public JSONArray listar(Connection c, List<Object> u, String metodo) throws Exception {
        
        String sql;
        
        ResultSet rs = null;
        
        try{
            
            switch (metodo) {
                case "TODOS_DA_UNIDADE":
                    sql = "SELECT p.idpessoa, p.nome, p.sobrenome, p.cpf, p.rg, p.telefone1, u.nomeunidade"
                            + " FROM pessoa p"
                            + " INNER JOIN login l ON (l.pessoa_idpessoa = p.idpessoa)"
                            + " INNER JOIN unidade u ON (u.idunidade = l.unidade_idunidade)"
                            + " INNER JOIN grupos g ON (g.loginusuario = l.usuario)"
                            + " WHERE g.grupo IN('agricultores') AND l.unidade_idunidade IN(?)";
                    break;
                case "USUARIOS":
                    sql = "SELECT p.idpessoa, p.nome, p.sobrenome, p.cpf, p.rg, p.telefone1, u.nomeunidade, g.grupo "
                            + "FROM pessoa p "
                            + "INNER JOIN login l ON (l.pessoa_idpessoa = p.idpessoa) "
                            + "INNER JOIN unidade u ON (u.idunidade = l.unidade_idunidade) "
                            + "INNER JOIN grupos g ON (g.loginusuario = l.usuario) "
                            + "WHERE g.grupo IN('administradores') OR g.grupo IN('entrevistadores') OR g.grupo IN('gerenciadores')";
                    break;
                case "EQUIPE":
                    sql = "SELECT p.idpessoa, p.nome, p.sobrenome, p.cpf, p.rg, p.telefone1, u.nomeunidade, g.grupo "
                            + "FROM pessoa p "
                            + "INNER JOIN login l ON (l.pessoa_idpessoa = p.idpessoa) "
                            + "INNER JOIN unidade u ON (u.idunidade = l.unidade_idunidade) "
                            + "INNER JOIN grupos g ON (g.loginusuario = l.usuario) "
                            + "WHERE (g.grupo IN('administradores') OR g.grupo IN('entrevistadores') OR g.grupo IN('gerenciadores')) AND l.unidade_idunidade IN(?)";
                    break;
                case "INPUT_SELECT":
                    sql = "SELECT p.idpessoa, p.nome, p.sobrenome, p.cpf, p.rg FROM pessoa p "
                            + "INNER JOIN agricultor a ON (a.pessoa_idpessoa = p.idpessoa) "
                            + "INNER JOIN login l ON(l.pessoa_idpessoa = p.idpessoa) "
                            + "WHERE p.nome ILIKE ? AND l.unidade_idunidade IN(?)";
                    break;
                default:
                    sql = "SELECT p.idpessoa, p.nome, p.sobrenome, p.cpf, p.rg FROM pessoa p "
                            + "INNER JOIN agricultor a ON (a.pessoa_idpessoa = p.idpessoa) "
                            + "INNER JOIN login l ON(l.pessoa_idpessoa = p.idpessoa) "
                            + "WHERE p.nome ILIKE ? AND p.sobrenome ILIKE ? AND l.unidade_idunidade IN(?)";

                    break;
            }
            
            rs = Data.executeQuery(c, sql, u);
            
            if(rs.next()){
                return MapeamentoJsonArray(rs);  
            }else{
                return null;
            }
            
                        
        }finally{
            rs.close();
        }
    }

    
}