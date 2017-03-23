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
    public void inserirIDString(Connection c, TOBase t) throws Exception {
        //string com o comando sql para editar o banco de dados
        String sql = null;
        
        //variavel com lista dos parametros
        List<Object> u = new ArrayList<Object>();
        

        sql = "INSERT INTO grupos(loginusuario, grupo) VALUES (?, ?)";
        u.add(((TOGrupos)t).getLoginusuario());
        u.add(((TOGrupos)t).getGrupo());

        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        Data.executeUpdateString(c, sql, u);
    }
    
}
