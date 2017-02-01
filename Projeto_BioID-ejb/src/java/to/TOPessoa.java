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
 * @author daniel
 */
public class TOPessoa extends TOBase{
    private long idpessoa;
    private long endereco_idendereco;
    private long escolaridade_idescolaridade;
    private long estadocivil_idestadocivil;
    private String nome;
    private String sobrenome;
    private String apelido;
    private String cpf;
    private String rg;
    private String datanascimento;
    private String sexo;
    private String telefone1;
    private String telefone2;
    private String email;
    
    
    private String usuario;
    private String papel;
    private String nomeunidade;
    private String escolaridade;
    private String estadocivil;
    private long idunidade;
    private TOAgricultor agricultor;
    

    public long getIdpessoa() {
        return idpessoa;
    }

    public void setIdpessoa(long idpessoa) {
        this.idpessoa = idpessoa;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }

    public long getIdunidade() {
        return idunidade;
    }

    public void setIdunidade(long idunidade) {
        this.idunidade = idunidade;
    }
    
    
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNomeunidade() {
        return nomeunidade;
    }

    public void setNomeunidade(String nomeunidade) {
        this.nomeunidade = nomeunidade;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public String getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(String estadocivil) {
        this.estadocivil = estadocivil;
    }

    

    
    
    public TOPessoa() {
    }

   
public TOPessoa(ResultSet rs, String metodo) throws Exception{
        
        switch (metodo) {
            case "buscaragricultor":
                this.agricultor = new TOAgricultor();
                this.idpessoa = rs.getLong("idpessoa");
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
                this.agricultor.setQtdCriancas(rs.getInt("qtdcriancas"));
                this.agricultor.setQtdIntegrantes(rs.getInt("qtdintegrantes"));
                this.agricultor.setQtdGravidas(rs.getInt("qtdgravidas"));
                this.estadocivil = rs.getString("estadocivil");
                this.escolaridade = rs.getString("escolaridade");
                break;
            case "buscarusuario":
                this.idpessoa = rs.getLong("idpessoa");
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
                this.estadocivil = rs.getString("estadocivil");
                this.escolaridade = rs.getString("escolaridade");
                break;
            case "listaragricultores":
                this.idpessoa = rs.getLong("idpessoa");
                this.nome = rs.getString("nome");
                this.sobrenome = rs.getString("sobrenome");
                this.cpf = rs.getString("cpf");
                this.rg = rs.getString("rg");
                this.telefone1 = rs.getString("telefone1");
                this.nomeunidade = rs.getString("nomeunidade");
                break;
            case "listarusuarios":
                this.idpessoa = rs.getLong("idpessoa");
                this.nome = rs.getString("nome");
                this.sobrenome = rs.getString("sobrenome");
                this.cpf = rs.getString("cpf");
                this.rg = rs.getString("rg");
                this.telefone1 = rs.getString("telefone1");
                this.nomeunidade = rs.getString("nomeunidade");
                this.papel = rs.getString("papel");
                break;
            case "procuraragricultor":
                this.idpessoa = rs.getLong("idpessoa");
                this.nome = rs.getString("nome");
                this.sobrenome = rs.getString("sobrenome");
                this.cpf = rs.getString("cpf");
                this.rg = rs.getString("rg");
//            this.idunidade = rs.getLong("idunidade");
                break;
            default:
                break;
        }
        
    }
    
    //retorna um json
    @Override
    public JSONObject getJson(String metodo) throws Exception {
        //variavel tipo json para retornar no metodo
        JSONObject j = new JSONObject();
        
        switch (metodo) {
            case "listaragricultores":
                //populando o objeto j
                j.put("idpessoa", idpessoa);
                j.put("nome", nome);
                j.put("sobrenome", sobrenome);
                j.put("cpf", cpf);
                j.put("rg", rg);
                j.put("telefone1", telefone1);
                j.put("nomeunidade", nomeunidade);
                break;
            case "listarusuarios":
                //populando o objeto j
                j.put("idpessoa", idpessoa);
                j.put("nome", nome);
                j.put("sobrenome", sobrenome);
                j.put("cpf", cpf);
                j.put("rg", rg);
                j.put("telefone1", telefone1);
                j.put("nomeunidade", nomeunidade);
                j.put("papel", papel);
                break;
            case "buscaragricultor":
                //populando o objeto j
                j.put("idpessoa", idpessoa);
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
                j.put("qtdintegrantes", this.agricultor.getQtdIntegrantes());
                j.put("qtdcriancas", this.agricultor.getQtdCriancas());
                j.put("qtdgravidas", this.agricultor.getQtdGravidas());
                j.put("estadocivil", estadocivil);
                j.put("escolaridade", escolaridade);
                break;
            case "buscarusuario":
                //populando o objeto j
                j.put("idpessoa", idpessoa);
                j.put("endereco_idendereco", endereco_idendereco);
                j.put("escolaridade", escolaridade);
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
                j.put("nomeunidade", nomeunidade);
                j.put("estadocivil", estadocivil);
                j.put("papel", papel);
                break;
            case "procuraragricultor":
                //populando o objeto j
                j.put("idpessoa", idpessoa);
                j.put("nome", nome);
                j.put("sobrenome", sobrenome);
                j.put("cpf", cpf);
                j.put("rg", rg);
//            j.put("idunidade", idunidade);
                break;
            default:
                break;
        }
        
        return j;
   
    }
 
    
}