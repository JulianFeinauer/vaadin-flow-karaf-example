package org.pragmaticminds.plc4x.cdi;

import org.osgi.service.cdi.annotations.Service;

import javax.inject.Inject;

@Service
public class DemoConsumer {

  @Inject
  public DemoConsumer(DemoService service) {
    System.out.println(service.sayHello());
  }


}
