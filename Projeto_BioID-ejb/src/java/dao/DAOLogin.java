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

/**
 *
 * @author daniel
 */
public class DAOLogin implements DAOBase{
    @Override
    public TOBase getLogin(Connection c, TOBase t) throws Exception {
        //string com o comando sql para editar o banco de dados
        String sql = "SELECT unidade_idunidade, pessoa_idpessoa, usuario, senha, papel, sessao FROM login where usuario = ? and senha = ?";
        
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

    @Override
    public long inserir(Connection c, TOBase t) throws Exception {
        //string com o comando sql para editar o banco de dados
        String sql = "INSERT INTO login(usuario, senha, papel) VALUES (?, ?, ?)";
        //variavel sendo convertida para toUsuarios
        TOLogin to = (TOLogin)t;
        //variavel com lista dos parametros
        List<Object> u = new ArrayList<Object>();
        

        u.add(to.getUsuario());
        u.add(to.getSenha());
        u.add(to.getPapel());
        
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        return Data.executeUpdate(c, sql, u);
    }

    @Override
    public void editar(Connection c, TOBase t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir(Connection c, TOBase t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TOBase get(Connection c, TOBase t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JSONArray listar(Connection c) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}