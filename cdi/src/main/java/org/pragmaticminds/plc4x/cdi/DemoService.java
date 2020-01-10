package org.pragmaticminds.plc4x.cdi;

import org.osgi.service.cdi.annotations.Service;

@Service
public class DemoService {

  public String sayHello() {
    return "Hello";
  }

}
