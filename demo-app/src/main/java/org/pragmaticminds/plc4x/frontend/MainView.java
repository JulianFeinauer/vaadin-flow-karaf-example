package org.pragmaticminds.plc4x.frontend;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The main view contains a button and a click listener.
 */
@Route("")
// Need to explicitly declare the Lumo until https://github.com/vaadin/flow/issues/4847 is fixed
@Theme(Lumo.class)
// @PWA(name = "Project Base for Vaadin Flow", shortName = "Project Base")
public class MainView extends VerticalLayout {

    private static final Logger logger = LoggerFactory.getLogger(MainView.class);

    public MainView() {
        logger.info("Showing Main View");
        Button button = new Button("Click me once again, Julian",
            event -> {
                Notification.show("Clicked!");
                UI.getCurrent().navigate("ViewTwo");
            });
        add(button);
    }
}
