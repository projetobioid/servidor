/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package to;

/**
 *
 * @author Daniel
 */
public class TOAgricultor extends TOBase{
    private long pessoa_idpessoa;
    
    private int qtdIntegrantes;
    
    private int qtdCriancas;
    
    private int qtdGravidas;

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

}
