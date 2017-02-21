/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import fw.Data;
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
    public long inserir(Connection c, TOBase t, String metodo) throws Exception {
        String sql = null;
     
//        TOUnidade to = ((TOUnidade)t);
        
        List<Object> p = new ArrayList<Object>();
        
        switch(metodo){
            default:
                sql = "INSERT INTO unidade(endereco_idendereco, nomeunidade, telefone1, telefone2, email, cnpj, razao_social, nome_fanta) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                p.add(((TOUnidade)t).getEndereco_idendereco());
                p.add(((TOUnidade)t).getNomeunidade());
                p.add(((TOUnidade)t).getTelefone1());
                p.add(((TOUnidade)t).getTelefone2());
                p.add(((TOUnidade)t).getEmail());
                p.add(((TOUnidade)t).getCnpj());
                p.add(((TOUnidade)t).getRazao_social());
                p.add(((TOUnidade)t).getNome_fanta());
                break;
        }
        
        
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        return Data.executeUpdate(c, sql, p);
    }

    @Override
    public TOBase get(Connection c, TOBase t, String metodo) throws Exception {
         
                //+ "where LOWER(nomeunidade) = LOWER(?) OR cnpj = ?";
        
        ResultSet rs = null;
        List<Object> u = new ArrayList<Object>();
        
        try{
//            TOUnidade to = (TOUnidade)t;
             String sql = null;
             
            switch(metodo){
                case "get_unidade":
                    sql = "SELECT u.idunidade, c.nomecidade, u.nomeunidade, e.rua, e.bairro, e.numero, e.complemento, e.gps_lat, e.gps_long, es.nomeestado, p.nomepais, u.telefone1, u.telefone2, u.email, u.cnpj, u.razao_social, u.nome_fanta "
                        + "FROM unidade u "
                        + "INNER JOIN endereco e ON (e.idendereco = u.endereco_idendereco) "
                        + "INNER JOIN cidade c ON (c.idcidade= e.cidade_idcidade) "
                        + "INNER JOIN estado es ON (es.idestado = c.estado_idestado) "
                        + "INNER JOIN pais p ON (p.idpais= es.pais_idpais) "
                        + "WHERE idunidade IN(?)";
                    u.add(((TOUnidade)t).getIdunidade());
                    break;
                case "GET_POR_CNPJ":
                    sql = "SELECT * FROM unidade where cnpj IN(?)";
                    u.add(((TOUnidade)t).getCnpj());
                    break;
                default:
                    sql = "SELECT * FROM unidade";
                    break;
                
            }
            
            
            rs = Data.executeQuery(c, sql, u);
            
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
    public JSONArray listar(Connection c, TOBase t, String metodo) throws Exception {
        JSONArray  ja = new JSONArray();
  
        ResultSet rs = null;
        
        try{
            
            String sql = null;
            
            switch(metodo){
                case "ID_NOME_CIDADE":
                    sql = "SELECT u.idunidade, c.nomecidade, u.nomeunidade "
                        + "FROM unidade u "
                        + "INNER JOIN endereco e ON (e.idendereco = u.endereco_idendereco) "
                        + "INNER JOIN cidade c ON (c.idcidade= e.cidade_idcidade) ";
                    break;
                case "TODAS":
                    sql = "SELECT u.idunidade, c.nomecidade, u.nomeunidade, es.nomeestado, p.nomepais, u.telefone1, u.email, u.cnpj "
                        + "FROM unidade u "
                        + "INNER JOIN endereco e ON (e.idendereco = u.endereco_idendereco) "
                        + "INNER JOIN cidade c ON (c.idcidade= e.cidade_idcidade) "
                        + "INNER JOIN estado es ON (es.idestado = c.estado_idestado) "
                        + "INNER JOIN pais p ON (p.idpais= es.pais_idpais)";
                    break;
                default:
                    sql = "SELECT * FROM unidade";
                    break;
            }
            rs = Data.executeQuery(c, sql);
            
            while (rs.next()){
                
                TOUnidade tu = new TOUnidade(rs, metodo);
                
                ja.put(tu.getJson(metodo));
            }
        }finally{
            rs.close();
        }
        return ja;
    }

    
}
