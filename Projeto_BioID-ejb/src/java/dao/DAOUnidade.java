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
public class DAOUnidade extends DAOBase{

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
    public TOBase get(Connection c, TOBase t, String metodo) throws Exception {
        String sql = "SELECT u.idunidade, c.nomecidade, u.nomeunidade, e.rua, e.bairro, e.numero, e.complemento, e.gps_lat, e.gps_long, es.nomeestado, p.nomepais, u.telefone1, u.telefone2, u.email, u.cnpj, u.razao_social, u.nome_fanta "
                + "FROM unidade u "
                + "INNER JOIN endereco e ON (e.idendereco = u.endereco_idendereco) "
                + "INNER JOIN cidade c ON (c.idcidade= e.cidade_idcidade) "
                + "INNER JOIN estado es ON (es.idestado = c.estado_idestado) "
                + "INNER JOIN pais p ON (p.idpais= es.pais_idpais) "
                + "WHERE idunidade IN(?)";
                //+ "where LOWER(nomeunidade) = LOWER(?) OR cnpj = ?";
        
        ResultSet rs = null;
        List<Object> p = new ArrayList<Object>();
        
        try{
            TOUnidade to = (TOUnidade)t;
            p.add(to.getIdunidade());
            rs = Data.executeQuery(c, sql, p);
            
            if(rs.next()){
                return new TOUnidade(rs, metodo);
            }else{
                return null;
            }
        }finally{
            rs.close();
        }
    }
    
    @Override
    public JSONArray listar(Connection c) throws Exception {
        JSONArray  ja = new JSONArray();
        
        String sql = "SELECT u.idunidade, c.nomecidade, u.nomeunidade, es.nomeestado, p.nomepais, u.telefone1, u.telefone2, u.email, u.cnpj "
                + "FROM unidade u "
                + "INNER JOIN endereco e ON (e.idendereco = u.endereco_idendereco) "
                + "INNER JOIN cidade c ON (c.idcidade= e.cidade_idcidade) "
                + "INNER JOIN estado es ON (es.idestado = c.estado_idestado) "
                + "INNER JOIN pais p ON (p.idpais= es.pais_idpais)";
        
        ResultSet rs = null;
        
        try{
            rs = Data.executeQuery(c, sql);
            
            while (rs.next()){
                TOUnidade t = new TOUnidade(rs);
                ja.put(t.getJson("todas"));
            }
        }finally{
            rs.close();
        }
        return ja;
    }

    
}
