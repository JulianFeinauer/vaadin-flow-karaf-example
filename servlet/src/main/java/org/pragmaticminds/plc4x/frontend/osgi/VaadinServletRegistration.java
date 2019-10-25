package org.pragmaticminds.plc4x.frontend.osgi;

import com.vaadin.flow.server.VaadinServlet;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.http.whiteboard.HttpWhiteboardConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import java.util.Hashtable;

/**
 * Register a VaadinServlet via HTTP Whiteboard specification
 *
 * Needs jetty, http, karaf-feature, pax-war
 *
 * This is probably no longer needed, as {@link FixedVaadinServlet}
 * is loaded now via DS
 */
// @Component(immediate = true)
@Deprecated
public class VaadinServletRegistration {

    private static final Logger logger = LoggerFactory.getLogger(VaadinServletRegistration.class);

    // Store the registration for unregistration
    private ServiceRegistration<?> registration;

    // @Activate
    void activate(BundleContext ctx) {
        logger.info("Activating Vaadin Servlet!");
        Hashtable<String, Object> properties = new Hashtable<>();
        properties.put(
                HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_ASYNC_SUPPORTED,
                true);
        properties.put(HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_PATTERN,
                "/*");
        registration = ctx.registerService(
            new String[]{
                Servlet.class.getTypeName(),
                VaadinServlet.class.getTypeName()
            },
            new FixedVaadinServlet(),
            properties);
    }

    // @Deactivate
    void deactivate() {
        logger.info("Deactivating Vaadin servlet!");
        if (registration != null) {
            registration.unregister();
        }
    }

}
