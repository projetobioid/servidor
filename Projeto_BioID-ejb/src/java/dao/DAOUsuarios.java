/*
classe de acesso ao banco de dados dos usuarios do sistema
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import to.TOBase;
import to.TOUsuarios;

/**
 *
 * @author daniel
 */
public class DAOUsuarios extends DAOBase {
    
    @Override
    public void inserir(Connection c, TOBase t) throws Exception {
        //string com o comando sql para editar o banco de dados
        String sql = "insert into usuarios (id, usuario, senha, email) values (?, ?, ?, ?)";
        //variavel sendo convertida para toUsuarios
        TOUsuarios to = (TOUsuarios)t;
        //variavel com lista dos parametros
        List<Object> u = new ArrayList<Object>();
        
        
        u.add(to.getId());
        u.add(to.getUsuario());
        u.add(to.getSenha());
        u.add(to.getEmail());
        
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        Data.executeUpdate(c, sql, u);
    }
    
    @Override
    public void editar(Connection c, TOBase t) throws Exception {
        //string com o comando sql para editar o banco de dados
        String sql = "update usuarios set usuario = ?, senha = ?, email = ? where id = ?";
        //variavel sendo convertida para toUsuarios
        TOUsuarios to = (TOUsuarios)t;
        //variavel com lista dos parametros
        List<Object> u = new ArrayList<Object>();
        
        //grava na lista os parametros recebidos do to
        u.add(to.getUsuario());
        u.add(to.getSenha());
        u.add(to.getEmail());
        u.add(to.getId());
        
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        Data.executeUpdate(c, sql, u);
        
    }
    
    @Override
    public void excluir(Connection c, TOBase t) throws Exception {
        //string com o comando sql para editar o banco de dados
        String sql = "delete from usuarios where id = ?";
        //variavel sendo convertida para toUsuarios
        TOUsuarios to = (TOUsuarios)t;
        //variavel com lista dos parametros
        List<Object> u = new ArrayList<Object>();
        
        //grava na lista os parametros recebidos do to
        u.add(to.getId());
        
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        Data.executeUpdate(c, sql, u); 
    }
    
    @Override
    public TOBase get(Connection c, TOBase t) throws Exception {
        //string com o comando sql para editar o banco de dados
        String sql = "select id, usuario, senha, email from usuarios where id = ? ";
        
        ResultSet rs = null;
        
        try{
            //variavel sendo convertida para toUsuarios
            TOUsuarios to = (TOUsuarios)t;
            
            rs = Data.executeQuery(c, sql, to.getId());
            
            if(rs.next()){
                return new TOUsuarios(rs);
            }else{
                return null;
            }
        }finally{
            rs.close();
        }
        
    }


    @Override
    public JSONArray listar(Connection c) throws Exception {
        //string com o comando sql para editar o banco de dados
        String sql = "select id, usuario, email, senha from usuarios order by usuario";
        
        JSONArray  ja = new JSONArray();
        
        ResultSet rs = null;
        
        try{
            rs = Data.executeQuery(c, sql);
            
            while (rs.next()){
                TOUsuarios t = new TOUsuarios(rs);
                ja.put(t.getJson());
            }
        }finally{
            rs.close();
        }
        return ja;
    }

    
}
