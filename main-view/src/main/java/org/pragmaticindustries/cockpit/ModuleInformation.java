package org.pragmaticindustries.cockpit;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Image;

public class ModuleInformation {
    public boolean hasImage() {
        return false;
    }

    public Image getImage() {
        return null;
    }

    public String getShortName() {
        return "short";
    }

    public String getLongName() {
        return "long";
    }

    public String getDescription() {
        return "description";
    }

    public String getNavigationPath() {
        return "";
    }

    public boolean hasConfig() {
        return false;
    }
}
