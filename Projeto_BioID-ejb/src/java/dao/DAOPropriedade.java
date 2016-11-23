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
import to.TOLogin;
import to.TOPropriedade;
import to.TOSafra;

/**
 *
 * @author Aimee
 */
public class DAOPropriedade extends DAOBase{

    @Override
    public long inserir(Connection c, TOBase t) throws Exception {
    String sql = "INSERT INTO propriedade(endereco_idendereco, unidade_idunidade, nomepropriedade, area, "
            + "unidadedemedida, areautilizavel, unidadedemedidaau) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        TOPropriedade to = (TOPropriedade)t;
        
        List<Object> p = new ArrayList<Object>();
        
        
        p.add(to.getEndereco_idendereco());
        p.add(to.getUnidade_idunidade());
        p.add(to.getNomepropriedade());
        p.add(to.getArea());
        p.add(to.getUnidadedemedida());
        p.add(to.getAreautilizavel());
        p.add(to.getUnidadedemedidaau());
        
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        return Data.executeUpdate(c, sql, p);    
    }


    @Override
    public TOBase get(Connection c, TOBase t) throws Exception {
        String sql = "SELECT * FROM propriedade pr "
                + "INNER JOIN relacaopa r ON(r.propriedade_idpropriedade = pr.idpropriedade)"
                + "INNER JOIN pessoa p ON(p.idpessoa = r.agricultor_pessoa_idpessoa)"
                + "WHERE LOWER(pr.nomepropriedade) = LOWER(?) and p.cpf = ?";
        
        ResultSet rs = null;
        List<Object> p = new ArrayList<Object>();
        
        try{
            
            TOPropriedade to = (TOPropriedade)t;
            p.add(to.getNomepropriedade());
            p.add(to.getCpf());
            
            rs = Data.executeQuery(c, sql, p);
            
            if(rs.next()){
                return new TOPropriedade(rs);
            }else{
                return null;
            }
        }finally{
            rs.close();
        }
    }

    @Override
    public JSONArray listar(Connection c, TOBase t) throws Exception {
        JSONArray  ja = new JSONArray();
                
                
        String sql = "SELECT cdd.nomecidade, e.rua, e.numero, e.bairro, e.cep, e.complemento, e.gps_lat, e.gps_long, pr.nomepropriedade, pr.idpropriedade "
                + "FROM propriedade pr INNER JOIN relacaopa rpa ON(pr.idPropriedade = rpa.propriedade_idpropriedade) "
                + "INNER JOIN pessoa p ON(p.idpessoa = rpa.agricultor_pessoa_idpessoa) "
                + "INNER JOIN endereco e ON(e.idendereco = pr.endereco_idendereco) "
                + "INNER JOIN cidade cdd ON(cdd.idcidade = e.cidade_idcidade) "
                + "WHERE p.idpessoa IN(?)";
        
        ResultSet rs = null;
        
        try{
            //variavel com lista dos parametros
            List<Object> u = new ArrayList<Object>();
            TOLogin to = (TOLogin)t;
            u.add(to.getPessoa_idpessoa());
            
            rs = Data.executeQuery(c, sql, u);
            
            while (rs.next()){
                TOPropriedade ts = new TOPropriedade().listarPropriedadeEndereco(rs);
                ja.put(ts.getJson());
            }
        }finally{
            rs.close();
        }
        return ja;
    
    }


}
