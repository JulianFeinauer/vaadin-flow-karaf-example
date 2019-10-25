package org.pragmaticminds.plc4x.frontend.osgi;

import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinServlet;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;
import org.osgi.framework.wiring.BundleWiring;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.Optional;

/**
 * This is not needed as OSGi-Extender does all the loading automatically.
 */
// @Component(immediate = true)
@Deprecated
public class MyListener implements BundleListener {

  private static final Logger logger = LoggerFactory.getLogger(MyListener.class);

  @Reference(cardinality = ReferenceCardinality.MANDATORY, policy = ReferencePolicy.DYNAMIC, service = VaadinServlet.class)
  private volatile VaadinServlet servlet;

//  @Reference(cardinality = ReferenceCardinality.AT_LEAST_ONE, policy = ReferencePolicy.DYNAMIC, service = Servlet.class)
//  private void setServlet(Servlet servlet) {
//    logger.info("New Servlet found: {}", servlet);
//
//    if (servlet instanceof VaadinServlet) {
//      this.servlet = ((VaadinServlet) servlet);
//    } else {
//      logger.info("Found non-vaadin Servlet {}, skipping", servlet);
//    }
//  }
//
//  private void unsetServlet(Servlet servlet) {
//    logger.info("Unload Servlet: {}", servlet);
//
//    if (this.servlet == servlet) {
//      this.servlet = null;
//    } else {
//      logger.info("The removed Servlet was not the Vaadin servlet used");
//    }
//  }

  @Activate
  void activate(BundleContext ctx) {
    logger.info("Adding Bundle Listener");
    ctx.addBundleListener(this);
  }

  @Deactivate
  void deactivate(BundleContext ctx) {
    logger.info("Removing Bundle Listener");
    ctx.removeBundleListener(this);
  }

  String typeToString(int type) {
    switch (type) {
      case BundleEvent.INSTALLED:
        return "INSTALLED";
      case BundleEvent.STARTED:
        return "STARTED";
      default:
        return "UNKNOWN - " + type;
    }
  }

  @Override public void bundleChanged(BundleEvent bundleEvent) {
    logger.debug("Received Bundle Changed Event {} for Bundle {}", typeToString(bundleEvent.getType()), bundleEvent.getBundle().getSymbolicName());
    if (bundleEvent.getType() != BundleEvent.STARTED) {
      logger.info("Bundle {} started, looking for routes now...", bundleEvent.getBundle().getSymbolicName());
    }
    BundleWiring wiring = bundleEvent.getBundle().adapt(BundleWiring.class);
    // Now we could do something with the wiring...
    // Use newer Resources API
    for (String ressource : wiring.listResources("/", "*", BundleWiring.FINDENTRIES_RECURSE)) {
      // Extracting the Package name
      if (ressource.endsWith(".class")) {
        final String packageName = ressource.substring(0, ressource.lastIndexOf("/")).replace("/", ".");
        final String className = ressource.substring(ressource.lastIndexOf("/") + 1, ressource.lastIndexOf("."));
        logger.debug("Found class {} in package {}", className, packageName);

        // Try to get the Class in our hands
        try {
          final Class<?> clazz = Class.forName(packageName + "." + className, false, wiring.getClassLoader());
          // Now check if we have the @Route Annotation there
          final Route route = clazz.getDeclaredAnnotation(Route.class);
          if (route != null) {
            logger.info("Found Route {} on clazz {}, trying to wire it...", route, className);
            if (com.vaadin.flow.component.Component.class.isAssignableFrom(clazz) == false) {
              logger.info("All classes annotated with @Route have to extend Component and {} does not", className);
              continue;
            }
            if (this.servlet == null) {
              logger.warn("Unnable to install View {} to route {} as there is no Vaadin Servlet present...", className, route.value());
            }
            // Check if route is already wired
            final Optional<Class<? extends com.vaadin.flow.component.Component>> registeredTarget = servlet.getService().getRouter().getRegistry().getNavigationTarget(route.value());
            if (registeredTarget.isPresent()) {
              logger.warn("There is already a route registered for '{}' to Component {}, will be removed and replaced by {}", route.value(), registeredTarget.get(), className);
              servlet.getService().getRouter().getRegistry().removeRoute(route.value());
            }
            servlet.getService().getRouter().getRegistry().setRoute(route.value(), ((Class<? extends com.vaadin.flow.component.Component>) clazz), Collections.emptyList());

          }
          // Debug
          if (logger.isDebugEnabled()) {
            for (Annotation annotation : clazz.getDeclaredAnnotations()) {
              logger.debug("\tFound Annotation {} on class {}", annotation, className);
            }
          }
        } catch (ClassNotFoundException e) {
          e.printStackTrace();
        }
      }
    }
  }

}
