package org.pragmaticminds.plc4x.frontend;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

/**
 * The Hello World View of the Servlet mapped to "/"
 */
@Route("")
// Need to explicitly declare the Lumo until https://github.com/vaadin/flow/issues/4847 is fixed
@Theme(Lumo.class)
// @PWA(name = "Project Base for Vaadin Flow", shortName = "Project Base")
public class DefaultView extends VerticalLayout {

    public DefaultView() {
        add(new Label("Welcome to the Vaadin Servlet!"));
    }
}
