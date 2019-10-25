package org.pragmaticminds.plc4x.frontend.osgi;

import com.vaadin.flow.server.VaadinServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.http.whiteboard.HttpWhiteboardConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

/**
 * This class is a workaround for #4367. This will be removed after the
 * issue is fixed.
 */
@Component(immediate = true, service = {Servlet.class, VaadinServlet.class},
    property = {
        HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_ASYNC_SUPPORTED + "=true",
        HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_PATTERN + "=/*"
    })
// Works for OSGi cmpt 7.0.0
// @HttpWhiteboardServletAsyncSupported
// @HttpWhiteboardServletPattern("/*")
public class FixedVaadinServlet extends VaadinServlet {

  private static final Logger logger = LoggerFactory.getLogger(FixedVaadinServlet.class);

  @Override
  public void init(ServletConfig servletConfig) throws ServletException {
    logger.info("Initializing servlet!");
    super.init(servletConfig);

    getService().setClassLoader(getClass().getClassLoader());
  }

}
