package br.com.marteleto.coursera.forum.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import br.com.marteleto.coursera.forum.util.ConfigUtil;

@WebListener
public class ContextListener implements ServletContextListener {
 
    @Override
    public void contextInitialized(ServletContextEvent event) {
    	ConfigUtil.definirConfiguracao("forum.properties");
        System.out.println("[FORUM Inicializado com sucesso]");
    }
     
    @Override
    public void contextDestroyed(ServletContextEvent event) {
    	System.out.println("[FORUM finalizado com sucesso]");
    }

}
