/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import to.TOBase;
import to.TOSessao;

/**
 *
 * @author daniel
 */
public class DAOSessao extends DAOBase{

    @Override
    public long inserir(Connection c, TOBase t) throws Exception {
       //string com o comando sql para editar o banco de dados
        String sql = "INSERT INTO sessao(login_idlogin, sessao, datarequisicao) VALUES (?, ?, ?)";
        //variavel sendo convertida para toUsuarios
        TOSessao to = (TOSessao)t;
        //variavel com lista dos parametros
        List<Object> u = new ArrayList<Object>();
        
        u.add(to.getLogin_idlogin());
        u.add(to.getSessao());
        u.add(new Date().toString());
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        return Data.executeUpdate(c, sql, u);
    }

    
    @Override
    public JSONArray listar(Connection c, TOBase t) throws Exception {
        return super.listar(c, t); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TOBase get(Connection c, TOBase t) throws Exception {
         //string com o comando sql para editar o banco de dados
        String sql = "SELECT idsessao, login_idlogin, sessao, datarequisicao FROM sessao where sessao IN(?) AND login_idlogin IN(?)";
        
        ResultSet rs = null;
        
        try{
            //variavel sendo convertida para toUsuarios
            TOSessao to = (TOSessao)t;
            //variavel com lista dos parametros
            List<Object> u = new ArrayList<Object>();
            
            u.add(to.getSessao());
            u.add(to.getLogin_idlogin());
           
            
            rs = Data.executeQuery(c, sql, u);
            
            
            if(rs.next()){
                return new TOSessao(rs);
            }else{
                return null;
            }
        }finally{
            rs.close();
        }
    }

    @Override
    public void excluir(Connection c, TOBase t) throws Exception {
        super.excluir(c, t); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editar(Connection c, TOBase t) throws Exception {
        String sql = "update sessao set sessao = ?, datarequisicao = ? where idsessao IN(?)";
        
        TOSessao to = (TOSessao)t;
        
        List<Object> p = new ArrayList<Object>();
        
        p.add(to.getNewSessao());
        p.add(new Date().toString());
        p.add(to.getIdsessao());
        
       
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto        
        Data.executeUpdate(c, sql, p);
    }
    
}