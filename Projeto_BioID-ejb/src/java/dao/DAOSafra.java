/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bo.BOFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.json.JSONArray;
import to.TOBase;
import to.TOSafra;
import to.TOColheita;

/**
 *
 * @author Aimee
 */
public class DAOSafra implements DAOBase{

    @Override
    public long inserir(Connection c, TOBase t) throws Exception {
        String sql = "INSERT INTO safra(unidademedida_idunidademedida, propriedade_idpropriedade, cultivar_idcultivar, safra, datareceb, qtdrecebida) VALUES (?, ?, ?, ?, ?, ?)";
        
        TOSafra to = (TOSafra)t;
        List<Object> p = new ArrayList<Object>();

        p.add(to.getUnidademedida_idunidademedida());
        p.add(to.getPropriedade_idpropriedade());
        p.add(to.getCultivar_idcultivar());
        p.add(to.getSafra());
        p.add(to.getDatareceb());
        p.add(to.getQtdrecebida());
        
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        return Data.executeUpdate(c, sql, p);
    }

    @Override
    public void editar(Connection c, TOBase t) throws Exception {
        String sql = "UPDATE safra SET qtdconsumida=?, qtdreplatar=?, qtddestinada=?, destino=? WHERE propriedade_idpropriedade = ? and cultivar_idcultivar = ? and safra = ?";

        
        TOSafra to = (TOSafra)t;
        
        List<Object> p = new ArrayList<Object>();
        

        p.add(to.getPropriedade_idpropriedade());
        p.add(to.getCultivar_idcultivar());
        p.add(to.getSafra());
        
        
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto        
        Data.executeUpdate(c, sql, p);
        
    }

    @Override
    public void excluir(Connection c, TOBase t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TOBase get(Connection c, TOBase t) throws Exception {
        String sql = "select * from safra where LOWER(safra) = LOWER(?)";
        
        ResultSet rs = null;
        
        try{
            TOSafra to = (TOSafra)t;
            rs = Data.executeQuery(c, sql, to.getSafra());
            
            if(rs.next()){
                return new TOSafra(rs);
            }else{
                return null;
            }
        }finally{
            rs.close();
        }
    }

    @Override
    public JSONArray listar(Connection c) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TOBase getLogin(Connection c, TOBase t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JSONArray listar(Connection c, TOBase t) throws Exception {
        JSONArray  ja = new JSONArray();
        
        String sql = "SELECT s.idsafra, s.propriedade_idpropriedade, s.cultivar_idcultivar, s.safra, s.datareceb, s.qtdrecebida,"
                + " um.grandeza as grandeza_safra, c.nomecultivar, pr.nomepropriedade, c.tempodecolheita, c.tempodestinacao FROM login l"
                + " INNER JOIN pessoa p ON( p.idpessoa = l.pessoa_idpessoa) INNER JOIN relacaopa r ON( r.agricultor_pessoa_idpessoa = p.idpessoa)"
                + " INNER JOIN propriedade pr ON (pr.idpropriedade = r.propriedade_idpropriedade)"
                + " INNER JOIN safra s ON (s.propriedade_idpropriedade = pr.idpropriedade)"
                + " INNER JOIN cultivar c ON (c.idcultivar = s.cultivar_idcultivar)"
                + " INNER JOIN unidademedida um ON(um.idunidademedida = s.unidademedida_idunidademedida)"
                + " INNER JOIN unidademedida umc ON(umc.idunidademedida = c.unidademedida_idunidademedida) where l.usuario = ?";
           
        
        
        ResultSet rs = null;
        try{
            //variavel com lista dos parametros
            List<Object> u = new ArrayList<Object>();
            
            u.add(((TOSafra) t).getUsuario());
            
            rs = Data.executeQuery(c, sql, u);
            
            while (rs.next()){
                TOSafra ts = new TOSafra(rs);
   
                //verifica quantidade colhida
                ts.setQtdcolhida(verificaQtdColhida(ts.getIdsafra()));
                ts.setUm_colhida("kilo(s)");
                ts.setQtddestinada(verificaQtdDestinada(ts.getIdsafra()));
                ts.setUm_destinada("kilo(s)");
                
                ts.setPrazo_colheita(verificarPrazoColheita(ts.getTempodecolheita(), ts.getDatareceb()));
                ts.setPrazo_destinacao(verificarPrazoDestinacao(ts.getTempodestinacao(), ts.getDatareceb()));
                
                ja.put(ts.getJson());
            }
            
                        
        }finally{
            rs.close();
        }
        return ja;
    }


    private String verificarPrazoDestinacao(int tempodestinacao, String datareceb) {
        String teste = null;
        
        try{
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        
            Calendar datarecebimento = Calendar.getInstance();
            Calendar diaAtual = Calendar.getInstance();

            datarecebimento.setTime(format.parse(datareceb));

            datarecebimento.add(Calendar.DATE, +tempodestinacao);



            if(datarecebimento.getTimeInMillis() < diaAtual.getTimeInMillis()){
                teste = "expirada";
            }else{

                datarecebimento.add(Calendar.DATE, - diaAtual.get(Calendar.DAY_OF_MONTH));
                teste = datarecebimento.get(Calendar.DAY_OF_MONTH) +" dia(s) para relatadar"; 
            }
        }catch(Exception e){
            teste = "Erro em verificar a safra destinacao - "+ e;
        }
        return teste;
    }

    private String verificarPrazoColheita(int tempodecolheita, String datareceb) {
        String teste = null;
        
        try{
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        
                Calendar datarecebimento = Calendar.getInstance();
                Calendar diaAtual = Calendar.getInstance();
        
                datarecebimento.setTime(format.parse(datareceb));
                
                datarecebimento.add(Calendar.DATE, +tempodecolheita);
                
                
                
                if(datarecebimento.getTimeInMillis() < diaAtual.getTimeInMillis()){
                    teste = "expirada";
                }else{
                    
                    datarecebimento.add(Calendar.DATE, - diaAtual.get(Calendar.DAY_OF_MONTH));
                    teste = datarecebimento.get(Calendar.DAY_OF_MONTH) +" dia(s) para relatadar"; 
                }
        }catch(Exception e){
            teste = "Erro em verificar a safra colheita - "+ e;
        }
        return teste;  
    }

    private float verificaQtdColhida(long idsafra) {
        float qtdcolhida = 0f;
        TOColheita c = new TOColheita();
        c.setSafra_idsafra(idsafra);
        try{    
            JSONArray ja = BOFactory.listar(new DAOColheita(), c);
            System.out.println(ja);
            qtdcolhida = 350f;
            
        }catch(Exception e){
            
        }
        return qtdcolhida;
    }

    private float verificaQtdDestinada(long idsafra) {
        float qtddestinada = 0f;
        TOColheita c = new TOColheita();
        c.setSafra_idsafra(idsafra);
        try{    

          
            qtddestinada = 250f;
            
        }catch(Exception e){
            
        }
        return qtddestinada;
    }

}






