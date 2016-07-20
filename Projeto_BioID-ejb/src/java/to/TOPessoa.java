/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package to;

import java.sql.ResultSet;
import java.util.Date;
import org.json.JSONObject;

/**
 *
 * @author daniel
 */
public class TOPessoa extends TOBase{
    public long idPessoa;
    public long endereco_idendereco;
    public long escolaridade_idescolaridade;
    public long estadocivil_idestadocivil;
    public String nome;
    public String sobrenome;
    public String apelido;
    public String cpf;
    public String rg;
    public String datanascimento;
    public String sexo;
    public String telefone1;
    public String telefone2;
    public String email;

 

    public long getEndereco_idendereco() {
        return endereco_idendereco;
    }

    public void setEndereco_idendereco(long endereco_idendereco) {
        this.endereco_idendereco = endereco_idendereco;
    }



    public long getEscolaridade_idescolaridade() {
        return escolaridade_idescolaridade;
    }

    public void setEscolaridade_idescolaridade(long escolaridade_idescolaridade) {
        this.escolaridade_idescolaridade = escolaridade_idescolaridade;
    }

    public long getEstadocivil_idestadocivil() {
        return estadocivil_idestadocivil;
    }

    public void setEstadocivil_idestadocivil(long estadocivil_idestadocivil) {
        this.estadocivil_idestadocivil = estadocivil_idestadocivil;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(String datanascimento) {
        this.datanascimento = datanascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return idPessoa;
    }

    public void setId(long idPessoa) {
        this.idPessoa = idPessoa;
    }

    
    
    public TOPessoa() {
    }

    public TOPessoa(long endereco_idendereco, long escolaridade_idescolaridade, long estadocivil_idestadocivil, String nome, String sobrenome, String apelido, String cpf, String rg, String datanascimento, String sexo, String telefone1, String telefone2, String email) {
        this.endereco_idendereco = endereco_idendereco;
        this.escolaridade_idescolaridade = escolaridade_idescolaridade;
        this.estadocivil_idestadocivil = estadocivil_idestadocivil;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.apelido = apelido;
        this.cpf = cpf;
        this.rg = rg;
        this.datanascimento = datanascimento;
        this.sexo = sexo;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
        this.email = email;
    }
    
     //retorna consulta do banco de dados tipo resultset
    public TOPessoa (ResultSet rs) throws Exception{
        this.idPessoa = rs.getLong("idPessoa");
        this.endereco_idendereco = rs.getLong("endereco_idendereco");
        this.escolaridade_idescolaridade = rs.getLong("escolaridade_idescolaridade");
        this.estadocivil_idestadocivil = rs.getLong("estadocivil_idestadocivil");
        this.nome = rs.getString("nome");
        this.sobrenome = rs.getString("sobrenome");
        this.apelido = rs.getString("apelido");
        this.cpf = rs.getString("cpf");
        this.rg = rs.getString("rg");
        this.datanascimento = rs.getString("datanascimento");
        this.sexo = rs.getString("sexo");
        this.telefone1 = rs.getString("telefone1");
        this.telefone2 = rs.getString("telefone2");
        this.email = rs.getString("email");
    }


    
    
    
    //retorna um json
    @Override
    public JSONObject getJson() throws Exception {
        //variavel tipo json para retornar no metodo
        JSONObject j = new JSONObject();
        
        //populando o objeto j
        j.put("idPessoa", idPessoa);
        j.put("endereco_idendereco", endereco_idendereco);
        j.put("escolaridade", escolaridade_idescolaridade);
        j.put("nome", nome);
        j.put("sobrenome", sobrenome);
        j.put("apelido", apelido);
        j.put("cpf", cpf);
        j.put("rg", rg);
        j.put("datanascimento", datanascimento);
        j.put("sexo", sexo);
        j.put("telefone1", telefone1);
        j.put("telefone2", telefone2);
        j.put("email", email);
        
        return j;
   
    }
}