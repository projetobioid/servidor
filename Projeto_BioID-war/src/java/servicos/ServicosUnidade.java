/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import bo.BOFactory;
import dao.DAOEndereco;
import dao.DAOUnidade;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;
import to.TOEndereco;
import to.TOUnidade;

/**
 * REST Web Service
 *
 * @author daniel
 */
@Path("unidade")
public class ServicosUnidade {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServicosUnidade
     */
    public ServicosUnidade() {
    }
    //metodo que insere no banco de dados
    @Path("inserir")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String inserir(
            //tabela endereco
            @FormParam("cidade_idcidade") long cidade_idcidade,
            @FormParam("rua") String rua,
            @FormParam("gps_lat") int gps_lat,
            @FormParam("gps_log") int gps_log,
            @FormParam("bairro") String bairro,
            @FormParam("complemento") String complemento,
            @FormParam("cep") String cep,
            @FormParam("numero") int numero,
            //tabela unidade
            @FormParam("nomeunidade") String nomeunidade,
            @FormParam("telefone1") String telefone1,
            @FormParam("telefone2") String telefone2,
            @FormParam("email") String email,
            @FormParam("cnpj") String cnpj,
            @FormParam("razao_social") String razao_social,
            @FormParam("nome_fanta") String nome_fanta
            
            ) throws Exception{
        
                
        JSONObject j = new JSONObject();
        
        try{    
            //cria um objeto          
            TOUnidade t = new TOUnidade();
            t.setCnpj(cnpj);
            
            if(BOFactory.get(new DAOUnidade(), t)== null){
                //objeto TOEndereco
                TOEndereco te = new TOEndereco();
                te.setCidade_idCidade(cidade_idcidade);
                te.setRua(rua);
                te.setGps_Lat(gps_lat);
                te.setGps_Log(gps_log);
                te.setBairro(bairro);
                te.setComplemento(complemento);
                te.setCep(cep);
                te.setNumero(numero);
                //grava no banco de dados os dados da classe TOLogin e retorna o id gerado
                t.setEndereco_idendereco(BOFactory.inserir(new DAOEndereco(), te));
                t.setNomeunidade(nomeunidade);
                t.setTelefone1(telefone1);
                t.setTelefone2(telefone2);
                t.setEmail(email);
                t.setRazao_social(razao_social);
                t.setNome_fanta(nome_fanta);

                BOFactory.inserir(new DAOUnidade(), t);

                j.put("sucesso", true);
                j.put("mensagem", "Unidade cadastrada!");
            }else{
               j.put("sucesso", false);
               j.put("erro", 1);
               j.put("mensagem", "Unidade ja cadastrada!");
            }
        }catch(Exception e){
            j.put("sucesso", false);
            j.put("mensagem", e.getMessage());
        }
        
        return j.toString(); 
    }
}
