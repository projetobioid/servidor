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
import to.TOHistoricoColheita;
import to.TOLogin;

/**
 *
 * @author Aimee
 */
public class DAOSafra implements DAOBase{

    @Override
    public long inserir(Connection c, TOBase t) throws Exception {
        String sql = "INSERT INTO safra(statussafra_idstatussafra, unidademedida_idunidademedida, propriedade_idpropriedade, cultivar_idcultivar, safra,"
                + " datareceb, qtdrecebida) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        TOSafra to = (TOSafra)t;
        List<Object> p = new ArrayList<Object>();

        p.add(to.getStatussafra_idstatussafra());
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
        String sql = "UPDATE safra SET qtdcolhida=?, ultimadatacolheita=?, statussafra_idstatussafra=? WHERE idsafra = ?";

        
        TOSafra to = (TOSafra)t;
        
        List<Object> p = new ArrayList<Object>();
        

        p.add(to.getQtdcolhida());
        p.add(to.getUltimadatacolheita());
        p.add(to.getStatussafra_idstatussafra());
        p.add(to.getIdsafra());
        
        
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto        
        Data.executeUpdate(c, sql, p);
        
    }

    @Override
    public void excluir(Connection c, TOBase t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TOBase get(Connection c, TOBase t) throws Exception {
        String sql = "select * from safra where idsafra IN(?)";
        
        ResultSet rs = null;
        
        try{
            TOSafra to = (TOSafra)t;
            rs = Data.executeQuery(c, sql, to.getIdsafra());
            
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
        
        String sql = "SELECT s.idsafra, s.statussafra_idstatussafra, s.propriedade_idpropriedade, s.safra, s.datareceb, s.qtdrecebida,"
                + " (select SUM(d.qtddestinada) AS qtddestinada FROM destinacao d WHERE d.safra_idsafra IN(s.idsafra)),"
                + " um.grandeza as grandeza_recebida, s.qtdcolhida, c.nomecultivar, pr.nomepropriedade, c.tempodecolheita, c.tempodestinacao FROM login l"
                + " INNER JOIN pessoa p ON( p.idpessoa = l.pessoa_idpessoa)"
                + " INNER JOIN relacaopa r ON( r.agricultor_pessoa_idpessoa = p.idpessoa)"
                + " INNER JOIN propriedade pr ON (pr.idpropriedade = r.propriedade_idpropriedade)"
                + " INNER JOIN safra s ON (s.propriedade_idpropriedade = pr.idpropriedade)"
                + " INNER JOIN cultivar c ON (c.idcultivar = s.cultivar_idcultivar)"
                + " INNER JOIN unidademedida um ON(um.idunidademedida = s.unidademedida_idunidademedida) where l.usuario IN(?)";
  
        ResultSet rs = null;
        try{
            //variavel com lista dos parametros
            List<Object> u = new ArrayList<Object>();
            
            u.add(((TOLogin) t).getUsuario());
            
            rs = Data.executeQuery(c, sql, u);
            
            while (rs.next()){
                TOSafra ts = new TOSafra().retornoLista(rs);

                switch ((int)ts.getStatussafra_idstatussafra()) {
                    case 1:
                        ts.setPrazo_colheita(verificarPrazoColheita(ts));
                        ts.setPrazo_destinacao(verificarPrazoDestinacao(ts));
                        break;
                    case 2:
                        ts.setPrazo_colheita("Colheita relatada");
                        ts.setPrazo_destinacao("Destinação relatada");
                        break;
                    case 3:
                        ts.setPrazo_colheita("Colheita expirada");
                        ts.setPrazo_destinacao("Destinação expirada");
                        break;
                    case 4:
                        ts.setPrazo_colheita("Colheita relatada");
                        ts.setPrazo_destinacao(verificarPrazoDestinacao(ts));
                        break;
                    case 5:
                        ts.setPrazo_colheita("Colheita relatada");
                        ts.setPrazo_destinacao("Destinação expirada");
                        break;
                    
                }
                
                
                ja.put(ts.getJson());
            }
            
                        
        }finally{
            rs.close();
        }
        return ja;
    }


    private String verificarPrazoDestinacao(TOSafra ts) {
        String teste = null;

        try{
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        
            Calendar datarecebimento = Calendar.getInstance();
            Calendar diaAtual = Calendar.getInstance();

            datarecebimento.setTime(format.parse(ts.getDatareceb()));

            datarecebimento.add(Calendar.DATE, +ts.getTempodestinacao());



            if(datarecebimento.getTimeInMillis() < diaAtual.getTimeInMillis()){
                teste = "Destinação expirada";
                //atualiza o status da safra
                ts.setIdsafra(ts.getIdsafra());
                        
                if(ts.getStatussafra_idstatussafra() == 3){
                    teste = "Destinação expirada";
                }else{
                    ts.setStatussafra_idstatussafra(3);
                }
                BOFactory.editar(new DAOSafra(), ts);
                ////
            }else{

                datarecebimento.add(Calendar.DATE, - diaAtual.get(Calendar.DAY_OF_MONTH));
                teste = datarecebimento.get(Calendar.DAY_OF_MONTH) +" dia(s) para relatadar"; 
            }
        }catch(Exception e){
            teste = "Erro em verificar a safra destinacao - "+ e;
        
        }
        return teste;
    }

    private String verificarPrazoColheita(TOSafra ts) {
        String teste = null;
        
        try{
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                Calendar datarecebimento = Calendar.getInstance();
                Calendar diaAtual = Calendar.getInstance();

                datarecebimento.setTime(format.parse(ts.getDatareceb()));

                datarecebimento.add(Calendar.DATE, +ts.getTempodecolheita());


                if(datarecebimento.getTimeInMillis() < diaAtual.getTimeInMillis()){
                    teste = "Colheita expirada";
                    //atualiza o status da safra
                    ts.setIdsafra(ts.getIdsafra());
                    ts.setStatussafra_idstatussafra(4);
                    BOFactory.editar(new DAOSafra(), ts);
                    ////
                }else{

                    datarecebimento.add(Calendar.DATE, - diaAtual.get(Calendar.DAY_OF_MONTH));
                    teste = datarecebimento.get(Calendar.DAY_OF_MONTH) +" dia(s) para relatadar"; 
                }
        }catch(Exception e){
            teste = "Erro em verificar a safra colheita - "+ e;
        }

        return teste;  
    }


}






