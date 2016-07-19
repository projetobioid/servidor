/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author daniel
 */
@javax.ws.rs.ApplicationPath("servico")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(servicos.ServicosAgricultor.class);
        resources.add(servicos.ServicosCultivar.class);
        resources.add(servicos.ServicosCultivarrecebido.class);
        resources.add(servicos.ServicosLogin.class);
        resources.add(servicos.ServicosPessoa.class);
        resources.add(servicos.ServicosUsuario.class);
    }
    
}
