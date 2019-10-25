package org.pragmaticminds.plc4x.frontend.osgi;

import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;
import org.osgi.framework.wiring.BundleWiring;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true)
public class MyListener implements BundleListener {

  @Override public void bundleChanged(BundleEvent bundleEvent) {
    BundleWiring wiring = bundleEvent.getBundle().adapt(BundleWiring.class);
    // Now we could do something with the wiring...
    System.out.println("Classloader is " + wiring.getClassLoader());
  }

}
