/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package to;

/**
 *
 * @author daniel
 */
public class TOSessao extends TOBase{

    public long idsessao;
    
    public String login_usuario;
    
    public String sessao;
    
    public String datarequisicao;

    public long getIdsessao() {
        return idsessao;
    }

    public void setIdsessao(long idsessao) {
        this.idsessao = idsessao;
    }

    public String getLogin_usuario() {
        return login_usuario;
    }

    public void setLogin_usuario(String login_usuario) {
        this.login_usuario = login_usuario;
    }

    public String getSessao() {
        return sessao;
    }

    public void setSessao(String sessao) {
        this.sessao = sessao;
    }

    public String getDatarequisicao() {
        return datarequisicao;
    }

    public void setDatarequisicao(String datarequisicao) {
        this.datarequisicao = datarequisicao;
    }

    public TOSessao() {
    }

    
}
