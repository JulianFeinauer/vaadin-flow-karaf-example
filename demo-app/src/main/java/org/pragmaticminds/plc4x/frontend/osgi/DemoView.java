package org.pragmaticminds.plc4x.frontend.osgi;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class DemoView extends VerticalLayout {

  public DemoView() {
    add(new Label("Hello, I'm the admin view..."));
    add(new Button("Bring me Home...", event -> UI.getCurrent().navigate("")));
  }
}
