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
import java.util.Date;
import java.util.List;
import to.TOBase;
import to.TOSessao;

/**
 *
 * @author daniel
 */
public class DAOSessao extends DAOBase{

    @Override
    public long inserir(Connection c, TOBase t, String metodo) throws Exception {
       //string com o comando sql para editar o banco de dados
        String sql = null;
        
        //variavel sendo convertida para toUsuarios
        TOSessao to = ((TOSessao)t);
        //variavel com lista dos parametros
        List<Object> u = new ArrayList<Object>();
        //testa o metodo a ser executado
        
        switch(metodo){
            
            case "VALIDACAO":
                sql = "INSERT INTO sessao(login_usuario, sessao, datarequisicao) VALUES (?, ?, ?)";
                u.add(((TOSessao)t).getLogin_usuario());
                u.add(((TOSessao)t).getSessao());
                u.add(((TOSessao)t).getDatarequisicao()); 
                break;
                
        }
        
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        return Data.executeUpdate(c, sql, u);
    }

    @Override
    public void editar(Connection c, TOBase t, String metodo) throws Exception {
        String sql = null;
      
        
//        TOSessao to = (TOSessao)t;
        
        List<Object> p = new ArrayList<Object>();
        //testa o metodo a ser executado
        
        switch(metodo){
            case "update_sessao":
                sql =   "update sessao set sessao = ?, datarequisicao = ? where idsessao IN(?)";
                p.add(((TOSessao)t).getSessao());
                p.add(new Date().toString());
                p.add(((TOSessao)t).getIdsessao());
                break;
            
        }
       
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto        
        Data.executeUpdate(c, sql, p);
    }
    
    @Override
    public void excluir(Connection c, TOBase t, String metodo) throws Exception {
        super.excluir(c, t, metodo); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    @Override
    public TOBase buscar(Connection c, TOBase t, String metodo) throws Exception {
         //string com o comando sql para editar o banco de dados
        String sql = null;
        
        ResultSet rs = null;
        
        try{
            //variavel sendo convertida para toUsuarios
//            TOSessao to = (TOSessao)t;
            //variavel com lista dos parametros
            List<Object> u = new ArrayList<Object>();
            
            //testa o metodo a ser executado
            switch(metodo){
                case "get_sessao":
                    sql = "SELECT idsessao, login_usuario, sessao, datarequisicao FROM sessao where sessao IN(?) AND login_usuario IN(?)";
                    u.add(((TOSessao)t).getSessao());
                    u.add(((TOSessao)t).getLogin_usuario());
           
                    break;
            }
            
            rs = Data.executeQuery(c, sql, u);
            
            
            if(rs.next()){
                return new TOSessao(rs, metodo);
            }else{
                return null;
            }
        }finally{
            rs.close();
        }
    }
}
