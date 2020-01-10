package org.pragmaticindustries.cockpit.resources;

import com.vaadin.flow.osgi.support.OsgiVaadinStaticResource;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServlet;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import java.io.InputStream;

@Component(immediate = true, service = OsgiVaadinStaticResource.class)
public class DesktopTemplateResource implements OsgiVaadinStaticResource {

    @Override
    public String getPath() {
        return "/META-INF/resources/frontend/custom/desktop-template.html";
    }

    @Override
    public String getAlias() {
        return "/frontend/custom/desktop-template.html";
    }

}


