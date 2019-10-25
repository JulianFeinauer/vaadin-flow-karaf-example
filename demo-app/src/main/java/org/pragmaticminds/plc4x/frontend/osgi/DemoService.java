package org.pragmaticminds.plc4x.frontend.osgi;

import com.vaadin.flow.server.VaadinServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import java.util.Collections;

@Component(immediate = true)
public class DemoService {

  private static final Logger logger = LoggerFactory.getLogger(DemoService.class);

  @Reference(cardinality = ReferenceCardinality.AT_LEAST_ONE, policy = ReferencePolicy.DYNAMIC, service = Servlet.class)
  private void setServlet(Servlet servlet) {
    logger.info("Binding DemoService to Servlet...");

    if (servlet instanceof VaadinServlet) {
      ((VaadinServlet) servlet).getService().getRouter().getRegistry().setRoute("admin", DemoView.class, Collections.emptyList());
    } else {
      logger.info("Found non-vaadin Servlet {}, skipping", servlet);
    }
  }

  private void unsetServlet(Servlet servlet) {
    logger.info("Unbinding DemoService to Servlet...");

    if (servlet instanceof VaadinServlet) {
      ((VaadinServlet) servlet).getService().getRouter().getRegistry().removeRoute("admin");
    } else {
      logger.info("Found non-vaadin Servlet {}, skipping", servlet);
    }
  }

}
