package br.com.marteleto.coursera.forum.listener;

import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import br.com.marteleto.coursera.forum.util.ConfigUtil;

@WebListener
public class ContextListener implements ServletContextListener {
	private static final Logger log = Logger.getLogger(ContextListener.class.getName());
 
    @Override
    public void contextInitialized(ServletContextEvent event) {
    	ConfigUtil.definirConfiguracao("forum.properties");
    	log.info("[FORUM Inicializado com sucesso]");
    }
     
    @Override
    public void contextDestroyed(ServletContextEvent event) {
    	log.info("[FORUM finalizado com sucesso]");
    }

}
