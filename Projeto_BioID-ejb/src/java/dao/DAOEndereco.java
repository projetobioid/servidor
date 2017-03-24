/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import fw.Data;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author daniel
 */
public class DAOEndereco extends DAOBase{

    @Override
    public long inserir(Connection c, List<Object> u) throws Exception {
        //string com o comando sql para editar o banco de dados
        String sql = null;
        
        sql = "INSERT INTO endereco(cidade_idcidade, rua, gps_lat, gps_long, bairro, complemento, cep, numero) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        return Data.executeUpdate(c, sql, u);
    }
}
