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
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Daniel
 */
public class DAOPessoa extends DAOBase{
    
    @Override
    public long inserir(Connection c, List<Object> u) throws Exception {
        //string com o comando sql para editar o banco de dados
        String sql = null;
        
        sql = "INSERT INTO pessoa(estadocivil_idestadocivil, escolaridade_idescolaridade, endereco_idendereco, nome,"
        + " sobrenome, apelido, cpf, rg, datanascimento, sexo, telefone1, telefone2, email) "
        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        return Data.executeUpdate(c, sql, u);
    }

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
    @Override
    public JSONArray listar(Connection c, String metodo) throws Exception {
        
        String sql;
        
        ResultSet rs = null;
        
        try{
            
            switch (metodo) {
               
                case "USUARIOS":
                    sql = "SELECT p.idpessoa, p.nome, p.sobrenome, p.cpf, p.rg, p.telefone1, u.nomeunidade, g.grupo "
                            + "FROM pessoa p "
                            + "INNER JOIN login l ON (l.pessoa_idpessoa = p.idpessoa) "
                            + "INNER JOIN unidade u ON (u.idunidade = l.unidade_idunidade) "
                            + "INNER JOIN grupos g ON (g.loginusuario = l.usuario) "
                            + "WHERE g.grupo IN('administradores') OR g.grupo IN('entrevistadores') OR g.grupo IN('gerenciadores')";
                    break;
                
                default:
                    sql = "SELECT p.idpessoa, p.nome, p.sobrenome, p.cpf, p.rg FROM pessoa p "
                            + "INNER JOIN agricultor a ON (a.pessoa_idpessoa = p.idpessoa) "
                            + "INNER JOIN login l ON(l.pessoa_idpessoa = p.idpessoa) "
                            + "WHERE p.nome ILIKE ? AND p.sobrenome ILIKE ? AND l.unidade_idunidade IN(?)";

                    break;
            }
            
            rs = Data.executeQuery(c, sql);
            
            if(rs.next()){
                return MapeamentoJsonArray(rs);  
            }else{
                return null;
            }
            
                        
        }finally{
            rs.close();
        }
    }
@Override
    public JSONObject buscar(Connection c, List<Object> u, String metodo) throws Exception {

        ResultSet rs = null;
        
        try{
            String sql = null;
            
            switch(metodo){
                case "GET_MEMBRO":
                    sql = "SELECT p.nome, p.sobrenome, p.cpf, p.rg, p.datanascimento, p.apelido, p.sexo, p.telefone1, p.telefone2, p.email, e.descricao as estadocivil, g.grupo, es.descricao as escolaridade, u.nomeunidade FROM pessoa p "
                            + "INNER JOIN login l ON (l.pessoa_idpessoa = p.idpessoa) "
                            + "INNER JOIN estadocivil e ON (e.idestadocivil = p.estadocivil_idestadocivil) "
                            + "INNER JOIN unidade u ON (u.idunidade = l.unidade_idunidade) "
                            + "INNER JOIN grupos g ON (g.loginusuario = l.usuario) "
                            + "INNER JOIN escolaridade es ON (es.idescolaridade = p.escolaridade_idescolaridade) "
                            + "WHERE p.idpessoa IN(?)";
                    
                    break;
                case "BUSCA_POR_CPF":
                    sql = "SELECT p.nome, p.sobrenome, p.cpf, p.rg, p.datanascimento, p.apelido, p.sexo, p.telefone1, p.telefone2, p.email, e.descricao as estadocivil, g.grupo, es.descricao as escolaridade, u.nomeunidade FROM pessoa p "
                            + "INNER JOIN login l ON (l.pessoa_idpessoa = p.idpessoa) "
                            + "INNER JOIN estadocivil e ON (e.idestadocivil = p.estadocivil_idestadocivil) "
                            + "INNER JOIN unidade u ON (u.idunidade = l.unidade_idunidade) "
                            + "INNER JOIN grupos g ON (g.loginusuario = l.usuario) "
                            + "INNER JOIN escolaridade es ON (es.idescolaridade = p.escolaridade_idescolaridade) "
                            + "WHERE p.cpf IN(?)";
                    
                    break;
                case "BUSCA_POR_ID_AGRICULTOR":
                    sql = "SELECT p.nome, p.sobrenome, p.cpf, p.rg, p.datanascimento, p.apelido, p.sexo, p.telefone1, p.telefone2, p.email, e.descricao as estadocivil, es.descricao as escolaridade, u.nomeunidade, ag.qtdintegrantes, ag.qtdcriancas, qtdgravidas FROM pessoa p "
                            + "INNER JOIN login l ON (l.pessoa_idpessoa = p.idpessoa) "
                            + "INNER JOIN estadocivil e ON (e.idestadocivil = p.estadocivil_idestadocivil) "
                            + "INNER JOIN unidade u ON (u.idunidade = l.unidade_idunidade) "
                            + "INNER JOIN escolaridade es ON (es.idescolaridade = p.escolaridade_idescolaridade) "
                            + "INNER JOIN agricultor ag ON (ag.pessoa_idpessoa = p.idpessoa) "
                            + "WHERE p.idpessoa IN(?)";
                    
                    break;
                default:
                    sql = "SELECT * FROM pessoa WHERE idpessoa IN(?)";
                    break;
            }
                        
            rs = Data.executeQuery(c, sql, u);
            
            if(rs.next()){
                return MapeamentoJson(rs);
            }else{
                return null;
            }
            
        }finally{
            rs.close();
        }
    }

}