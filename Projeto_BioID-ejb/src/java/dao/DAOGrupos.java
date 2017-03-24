/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import fw.Data;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import to.TOBase;
import to.TOGrupos;

/**
 *
 * @author Daniel
 */
public class DAOGrupos extends DAOBase{
        
    
    @Override
    public void inserirIDString(Connection c, List<Object> u) throws Exception {
        //string com o comando sql para editar o banco de dados
        String sql = null;       

        sql = "INSERT INTO grupos(loginusuario, grupo) VALUES (?, ?)";

        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        Data.executeUpdateString(c, sql, u);
    }
    
}
