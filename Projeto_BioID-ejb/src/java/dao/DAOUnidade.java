/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import fw.Data;
import fw.Mapeamento;
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
public class DAOUnidade extends DAOBase{

    @Override
    public long inserir(Connection c, List<Object> u) throws Exception {
        String sql = null;
        
        sql = "INSERT INTO unidade(endereco_idendereco, nomeunidade, telefone1, telefone2, email, cnpj, razao_social, nome_fanta) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        return Data.executeUpdate(c, sql, u);
    }

    @Override
    public JSONObject buscar(Connection c, List<Object> u, String metodo) throws Exception {
         
        ResultSet rs = null;
        
        try{
            String sql = null;
             
            switch(metodo){
                case "GET_POR_ID":
                    sql = "SELECT u.idunidade, c.nomecidade, u.nomeunidade, e.rua, e.bairro, e.numero, e.complemento, e.gps_lat, e.gps_long, es.nomeestado, p.nomepais, u.telefone1, u.telefone2, u.email, u.cnpj, u.razao_social, u.nome_fanta "
                        + "FROM unidade u "
                        + "INNER JOIN endereco e ON (e.idendereco = u.endereco_idendereco) "
                        + "INNER JOIN cidade c ON (c.idcidade= e.cidade_idcidade) "
                        + "INNER JOIN estado es ON (es.idestado = c.estado_idestado) "
                        + "INNER JOIN pais p ON (p.idpais= es.pais_idpais) "
                        + "WHERE idunidade IN(?)";
                    break;
                case "GET_POR_CNPJ":
                    sql = "SELECT * FROM unidade where cnpj IN(?)";
                    break;
                default:
                    sql = "SELECT * FROM unidade WHERE idunidade IN(?)";
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
    


    @Override
    public JSONArray listar(Connection c, String metodo) throws Exception {
        
        ResultSet rs = null;
        
        try{
            
            String sql = null;
            
            switch(metodo){
                case "ID_NOME_CIDADE":
                    sql = "SELECT u.idunidade, c.nomecidade, u.nomeunidade "
                        + "FROM unidade u "
                        + "INNER JOIN endereco e ON (e.idendereco = u.endereco_idendereco) "
                        + "INNER JOIN cidade c ON (c.idcidade= e.cidade_idcidade) "
                        + "ORDER BY nomeunidade ASC";
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
            
            if(rs.next()){
                return Mapeamento.MapeamentoJsonArray(rs);
            }else{
                return null;
            }
            
        }finally{
            rs.close();
        }
    }

    
}
