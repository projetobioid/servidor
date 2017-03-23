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
import org.json.JSONObject;
import to.TOBase;
import to.TOAgricultor;
/**
 *
 * @author Daniel
 */
public class DAOAgricultor extends DAOBase{

    @Override
    public long inserir(Connection c, TOBase t) throws Exception {
        
        String sql = null;
        
        List<Object> u = new ArrayList<Object>();
        
        sql = "INSERT INTO agricultor(pessoa_idpessoa, qtdintegrantes, qtdcriancas, qtdgravidas)VALUES (?, ?, ?, ?)";
        
        u.add(((TOAgricultor)t).getPessoa_idpessoa());
        u.add(((TOAgricultor)t).getQtdIntegrantes());
        u.add(((TOAgricultor)t).getQtdCriancas());
        u.add(((TOAgricultor)t).getQtdGravidas());
 
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        return Data.executeUpdate(c, sql, u);
    }
    
    
    @Override
    public JSONObject buscar(Connection c, TOBase t, String metodo) throws Exception {
       
        ResultSet rs = null;
        
        try{
            
            String sql = "SELECT a.pessoa_idpessoa, a.qtdintegrantes, a.qtdcriancas, a.qtdgravidas, e.descricao as estadocivil, es.descricao as escolaridade, p.nome, p.sobrenome, p.apelido, p.cpf, p.rg, p.datanascimento, p.sexo, p.telefone1, p.telefone2, p.email "
            + "FROM agricultor a "
            + "INNER JOIN pessoa p ON(p.idpessoa = a.pessoa_idpessoa) "
            + "INNER JOIN estadocivil e ON(e.idestadocivil = p.estadocivil_idestadocivil) "
            + "INNER JOIN escolaridade es ON(es.idescolaridade = p.escolaridade_idescolaridade) "
            + "WHERE a.pessoa_idpessoa IN(?)";
            
            rs = Data.executeQuery(c, sql, ((TOAgricultor)t).getPessoa_idpessoa());
            
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
