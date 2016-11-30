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
import to.TOAgricultor;

/**
 *
 * @author Aimee
 */
public class DAOAgricultor extends DAOBase{

    @Override
    public long inserir(Connection c, TOBase t) throws Exception {
        String sql = "INSERT INTO agricultor(pessoa_idpessoa, qtdedeintegrantes, qtdedecriancas, qtdedegravidas)VALUES (?, ?, ?, ?)";
        
        TOAgricultor to = (TOAgricultor)t;
        
        List<Object> p = new ArrayList<Object>();
        
        
        p.add(to.getPessoa_idpessoa());
        p.add(to.getQtdedeintegrantes());
        p.add(to.getQtdedecriancas());
        p.add(to.getQtdedegravidas());
        
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        return Data.executeUpdate(c, sql, p);
    }
    
    
    @Override
    public TOBase get(Connection c, TOBase t) throws Exception {
        String sql = "SELECT a.pessoa_idpessoa, a.qtdedeintegrantes, a.qtdedecriancas, a.qtdedegravidas, e.descricao as estadocivil, es.descricao as escolaridade, p.nome, p.sobrenome, p.apelido, p.cpf, p.rg, p.datanascimento, p.sexo, p.telefone1, p.telefone2, p.email "
                + "FROM agricultor a "
                + "INNER JOIN pessoa p ON(p.idpessoa = a.pessoa_idpessoa) "
                + "INNER JOIN estadocivil e ON(e.idestadocivil = p.estadocivil_idestadocivil) "
                + "INNER JOIN escolaridade es ON(es.idescolaridade = p.escolaridade_idescolaridade) "
                + "WHERE a.pessoa_idpessoa IN(?)";
        
        ResultSet rs = null;
        
        try{
            TOAgricultor to = (TOAgricultor)t;
            rs = Data.executeQuery(c, sql, to.getPessoa_idpessoa());
            
            if(rs.next()){
                return null;//new TOAgricultor(rs);
            }else{
                return null;
            }
        }finally{
            rs.close();
        }
    }
}
