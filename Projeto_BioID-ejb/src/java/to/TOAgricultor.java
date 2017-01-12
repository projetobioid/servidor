/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package to;

import java.sql.ResultSet;
import org.json.JSONObject;

/**
 *
 * @author Aimee
 */
public class TOAgricultor extends TOBase{
    private long pessoa_idpessoa;
    
    private int qtdIntegrantes;
    
    private int qtdCriancas;
    
    private int qtdGravidas;
  
    
    //private TOEndereco endereco;

    public long getPessoa_idpessoa() {
        return pessoa_idpessoa;
    }

    public void setPessoa_idpessoa(long pessoa_idpessoa) {
        this.pessoa_idpessoa = pessoa_idpessoa;
    }

    public int getQtdIntegrantes() {
        return qtdIntegrantes;
    }

    public void setQtdIntegrantes(int qtdIntegrantes) {
        this.qtdIntegrantes = qtdIntegrantes;
    }

    public int getQtdCriancas() {
        return qtdCriancas;
    }

    public void setQtdCriancas(int qtdCriancas) {
        this.qtdCriancas = qtdCriancas;
    }

    public int getQtdGravidas() {
        return qtdGravidas;
    }

    public void setQtdGravidas(int qtdGravidas) {
        this.qtdGravidas = qtdGravidas;
    }

    public TOAgricultor() {
    }

//    
////    
////    //retorna consulta do banco de dados tipo resultset
////    public TOAgricultor (ResultSet rs) throws Exception{
////        this.pessoa = new TOPessoa();
////        //this.endereco = new TOEndereco();
////        
////        this.pessoa_idpessoa = rs.getLong("pessoa_idpessoa");
////        this.qtdedeintegrantes = rs.getInt("qtdedeintegrantes");
////        this.qtdedecriancas = rs.getInt("qtdedecriancas");
////        this.qtdedegravidas = rs.getInt("qtdedegravidas");
////        
////        this.pessoa.setNome(rs.getString("nome"));
////        this.pessoa.setSobrenome(rs.getString("sobrenome"));
////        this.pessoa.setApelido(rs.getString("apelido"));
////        this.pessoa.setCpf(rs.getString("cpf"));
////        this.pessoa.setRg(rs.getString("rg"));
////        this.pessoa.setDatanascimento(rs.getString("datanascimento"));
////        this.pessoa.setSexo(rs.getString("sexo"));
////        this.pessoa.setTelefone1(rs.getString("telefone1"));
////        this.pessoa.setTelefone2(rs.getString("telefone2"));
////        this.pessoa.setEmail(rs.getString("email"));
////        this.pessoa.setEscolaridade(rs.getString("escolaridade"));
////        this.pessoa.setEstadocivil(rs.getString("estadocivil"));
//        
////        this.endereco.setRua(rs.getString("rua"));
////        this.endereco.setNumero(rs.getInt("numero"));
////        this.endereco.setBairro(rs.getString("bairro"));
////        this.endereco.setComplemento(rs.getString("complemento"));
////        this.endereco.setCep(rs.getString("cep"));
////        this.endereco.setGps_lat(rs.getInt("gps_lat"));
////        this.endereco.setGps_long(rs.getInt("gps_long"));
////        this.endereco.setNomecidade(rs.getString("nomecidade"));
////        this.endereco.setNomeestado(rs.getString("nomeestado"));
////        this.endereco.setNomepais(rs.getString("nomepais"));
//        
//       
//    }
//    //classe sobrescrita de tobase
//    @Override
//    public JSONObject getJson() throws Exception {
//        //variavel para retorno do json contendo as informacoes do produto
//        JSONObject j = new JSONObject();
//        
//        //populando o objeto j
//        j.put("pessoa_idpessoa", pessoa_idpessoa);
//        j.put("qtdedeintegrantes", qtdedeintegrantes);
//        j.put("qtdedecriancas", qtdedecriancas);
//        j.put("qtdedegravida", qtdedegravidas);
//  
//        j.put("nome", pessoa.getNome());
//        j.put("sobrenome", pessoa.getSobrenome());
//        j.put("apelido", pessoa.getApelido());
//        j.put("cpf", pessoa.getCpf());
//        j.put("rg", pessoa.getRg());
//        j.put("datanascimento", pessoa.getDatanascimento());
//        j.put("sexo", pessoa.getSexo());
//        j.put("telefone1", pessoa.getTelefone1());
//        j.put("telefone2", pessoa.getTelefone2());
//        j.put("email", pessoa.getEmail());        
//        j.put("escolaridade", pessoa.getEscolaridade());
//        j.put("estadocivil", pessoa.getEstadocivil()); 
//        
//        return j;
//    }
}
