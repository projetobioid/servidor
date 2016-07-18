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
public class TOLogin extends TOBase{

    private String usuario;
   
    private String senha;
    
    private String papel;
    
    private String sessao;


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }

    public String getSessao() {
        return sessao;
    }

    public void setSessao(String sessao) {
        this.sessao = sessao;
    }

    public TOLogin() {
    }

    public TOLogin(String usuario, String senha, String papel, String sessao) {

        this.usuario = usuario;
        this.senha = senha;
        this.papel = papel;
        this.sessao = sessao;
    }
    
    public TOLogin(ResultSet rs) throws Exception{
        this.usuario = rs.getString("usuario");
        this.senha = rs.getString("senha");
        this.papel = rs.getString("papel");
        this.sessao = rs.getString("sessao");
       
    }

    @Override
    public JSONObject getJson() throws Exception {
        //variavel para retorno do json contendo as informacoes do login
        JSONObject j = new JSONObject();
        
        //populando o objeto j
        j.put("usuario", usuario);
        j.put("senha", senha);
        j.put("papel", papel);
        j.put("sessao", sessao);
        
        return j;
    }
    
    
    
}


























/*
package to;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "login")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Login.findAll", query = "SELECT l FROM Login l"),
    @NamedQuery(name = "Login.findByIdlogin", query = "SELECT l FROM Login l WHERE l.idlogin = :idlogin"),
    @NamedQuery(name = "Login.findByUsuario", query = "SELECT l FROM Login l WHERE l.usuario = :usuario"),
    @NamedQuery(name = "Login.findBySenha", query = "SELECT l FROM Login l WHERE l.senha = :senha"),
    @NamedQuery(name = "Login.findByPapel", query = "SELECT l FROM Login l WHERE l.papel = :papel")})
public class Login implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlogin")
    private Integer idlogin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "senha")
    private String senha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "papel")
    private Character papel;

    public Login() {
    }

    public Login(Integer idlogin) {
        this.idlogin = idlogin;
    }

    public Login(Integer idlogin, String usuario, String senha, Character papel) {
        this.idlogin = idlogin;
        this.usuario = usuario;
        this.senha = senha;
        this.papel = papel;
    }

    public Integer getIdlogin() {
        return idlogin;
    }

    public void setIdlogin(Integer idlogin) {
        this.idlogin = idlogin;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Character getPapel() {
        return papel;
    }

    public void setPapel(Character papel) {
        this.papel = papel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlogin != null ? idlogin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Login)) {
            return false;
        }
        Login other = (Login) object;
        if ((this.idlogin == null && other.idlogin != null) || (this.idlogin != null && !this.idlogin.equals(other.idlogin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "to.Login[ idlogin=" + idlogin + " ]";
    }
    
}
*/