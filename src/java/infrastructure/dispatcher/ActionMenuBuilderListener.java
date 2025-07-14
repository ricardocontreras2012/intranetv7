/**
 *
 * @author Ricardo Contreras S.
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
                .getConfigurationManager()
                .getConfiguration()
                .getRuntimeConfiguration()
                .getActionConfigs();

        for (Map.Entry<String, Map<String, ActionConfig>> outerEntry : configs.entrySet()) {
            Map<String, ActionConfig> innerMap = outerEntry.getValue();

            for (Map.Entry<String, ActionConfig> innerEntry : innerMap.entrySet()) {
                ActionConfig actionConfig = innerEntry.getValue();
                System.out.println("action=" + actionConfig.getName() + "-->" + actionConfig.getPackageName());
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // No se necesita implementación específica en este caso
    }

    @Override
    public void dispatcherDestroyed(Dispatcher d) {
        // No se necesita implementación específica en este caso
    }
}
