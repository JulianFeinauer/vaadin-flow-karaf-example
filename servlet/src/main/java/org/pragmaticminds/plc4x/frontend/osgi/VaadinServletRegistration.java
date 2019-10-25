package org.pragmaticminds.plc4x.frontend.osgi;

import com.vaadin.flow.server.VaadinServlet;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.http.whiteboard.HttpWhiteboardConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.util.Hashtable;

/**
 * Register a VaadinServlet via HTTP Whiteboard specification
 *
 * Needs jetty, http, karaf-feature, pax-war
 */
@Component(immediate = true)
public class VaadinServletRegistration {

    private static final Logger logger = LoggerFactory.getLogger(VaadinServletRegistration.class);

    /**
     * This class is a workaround for #4367. This will be removed after the
     * issue is fixed.
     */
    private static class FixedVaadinServlet extends VaadinServlet {
        @Override
        public void init(ServletConfig servletConfig) throws ServletException {
            logger.info("Initializing servlet!");
            super.init(servletConfig);

            getService().setClassLoader(getClass().getClassLoader());
        }
    }

    @Activate
    void activate(BundleContext ctx) {
        logger.info("Activating servlet!");
        Hashtable<String, Object> properties = new Hashtable<>();
        properties.put(
                HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_ASYNC_SUPPORTED,
                true);
        properties.put(HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_PATTERN,
                "/*");
        ctx.registerService(Servlet.class, new FixedVaadinServlet(),
                properties);
    }

}
