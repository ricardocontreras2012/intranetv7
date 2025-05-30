

/**
 *
 * @author Usach
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.dispatcher;

import com.opensymphony.xwork2.config.entities.ActionConfig;
import java.util.Map;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.struts2.dispatcher.Dispatcher;
import org.apache.struts2.dispatcher.DispatcherListener;

/**
 *
 * @author Administrador
 */
public class ActionMenuBuilderListener implements ServletContextListener, DispatcherListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Dispatcher.addDispatcherListener(this);
    }

    @Override
    public void dispatcherInitialized(Dispatcher du) {
        Map<String, Map<String, ActionConfig>> configs = du
                .getConfigurationManager().getConfiguration().getRuntimeConfiguration()
                .getActionConfigs();

        for (String name : configs.keySet()) {

            for (String n2 : configs.get(name).keySet()) {
                System.out.println("action=" + configs.get(name).get(n2).getName()+"-->"+configs.get(name).get(n2).getPackageName());
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
    }

    @Override
    public void dispatcherDestroyed(Dispatcher d) {
    }
}
