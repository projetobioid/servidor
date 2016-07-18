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
import to.TOBase;
import to.TOLogin;

/**
 *
 * @author daniel
 */
public class DAOLogin extends DAOBase{

    @Override
    public TOBase getLogin(Connection c, TOBase t) throws Exception {
        //string com o comando sql para editar o banco de dados
        String sql = "select usuario, senha, papel, sessao from login where usuario = ? and senha = ?";
        
        ResultSet rs = null;
        
        try{
            //variavel sendo convertida para toUsuarios
            TOLogin to = (TOLogin)t;
            //variavel com lista dos parametros
            List<Object> u = new ArrayList<Object>();
            
            u.add(to.getUsuario());
            u.add(to.getSenha());
            
            rs = Data.executeQuery(c, sql, u);
            
            
            if(rs.next()){
                return new TOLogin(rs);
            }else{
                return null;
            }
        }finally{
            rs.close();
        }
    }
    
}
