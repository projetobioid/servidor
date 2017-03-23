/*
Classe recebe os valores do banco de dados
 */
package to;

import java.sql.Blob;

/**
 *
 * @author Daniel
 */

public class TOCultivar extends TOBase{
    
    private long idcultivar;
    
    private String nomecultivar;
    
    private Blob imagem;
    
    private String string_imagem;
    
    private String descricao;
    
    private boolean biofortificado;
    
    private long unidademedida_idunidademedida;
    
    private String grandeza;

    private String valornutricional;
    
    private int tempodecolheita;
    
    private int tempodestinacao;
    
    private double peso_saca;
    
    public long getIdcultivar() {
        return idcultivar;
    }

    public void setIdcultivar(long idcultivar) {
        this.idcultivar = idcultivar;
    }

    public String getNomecultivar() {
        return nomecultivar;
    }

    public void setNomecultivar(String nomecultivar) {
        this.nomecultivar = nomecultivar;
    }

    public Blob getImagem() {
        return imagem;
    }

    public void setImagem(Blob imagem) {
        this.imagem = imagem;
    }

    public String getString_imagem() {
        return string_imagem;
    }

    public void setString_imagem(String string_imagem) {
        this.string_imagem = string_imagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isBiofortificado() {
        return biofortificado;
    }

    public void setBiofortificado(boolean biofortificado) {
        this.biofortificado = biofortificado;
    }

    public long getUnidademedida_idunidademedida() {
        return unidademedida_idunidademedida;
    }

    public void setUnidademedida_idunidademedida(long unidademedida_idunidademedida) {
        this.unidademedida_idunidademedida = unidademedida_idunidademedida;
    }

    public String getGrandeza() {
        return grandeza;
    }

    public void setGrandeza(String grandeza) {
        this.grandeza = grandeza;
    }

    public String getValornutricional() {
        return valornutricional;
    }

    public void setValornutricional(String valornutricional) {
        this.valornutricional = valornutricional;
    }

    public int getTempodecolheita() {
        return tempodecolheita;
    }

    public void setTempodecolheita(int tempodecolheita) {
        this.tempodecolheita = tempodecolheita;
    }

    public double getPeso_saca() {
        return peso_saca;
    }

    public void setPeso_saca(double peso_saca) {
        this.peso_saca = peso_saca;
    }

    public int getTempodestinacao() {
        return tempodestinacao;
    }

    public void setTempodestinacao(int tempodestinacao) {
        this.tempodestinacao = tempodestinacao;
    }

    public TOCultivar() {
    }

}
