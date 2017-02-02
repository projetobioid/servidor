/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import to.TOBase;
import to.TOEndereco;

/**
 *
 * @author daniel
 */
public class DAOEndereco extends DAOBase{

    @Override
    public long inserir(Connection c, TOBase t , String metodo) throws Exception {
        //string com o comando sql para editar o banco de dados
        String sql = null;
        
        //variavel sendo convertida para toUsuarios
//        TOEndereco to = ((TOEndereco)t);
        //variavel com lista dos parametros
        List<Object> u = new ArrayList<Object>();
        
        switch(metodo){
            default:
                sql = "INSERT INTO endereco(cidade_idcidade, rua, gps_lat, gps_long, bairro, complemento, cep, numero) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
                u.add(((TOEndereco)t).getCidade_idcidade());
                u.add(((TOEndereco)t).getRua());
                u.add(((TOEndereco)t).getGps_lat());
                u.add(((TOEndereco)t).getGps_long());
                u.add(((TOEndereco)t).getBairro());
                u.add(((TOEndereco)t).getComplemento());
                u.add(((TOEndereco)t).getCep());
                u.add(((TOEndereco)t).getNumero());
        
                break;
        }
        
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        return Data.executeUpdate(c, sql, u);
    }
}
