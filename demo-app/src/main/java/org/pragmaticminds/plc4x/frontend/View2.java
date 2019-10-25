package org.pragmaticminds.plc4x.frontend;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("ViewTwo")
public class View2  extends VerticalLayout {
  public View2() {
    add(new Label("Im view 2..."));
    Button button = new Button("Home",
        event -> UI.getCurrent().navigate(MainView.class));
    add(button);
  }
}
