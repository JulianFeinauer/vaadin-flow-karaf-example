package org.pragmaticminds.plc4x.frontend.osgi;

import com.vaadin.flow.osgi.support.OsgiVaadinStaticResource;
import org.osgi.service.component.annotations.Component;

/**
 * Registers icon as a web resource available via HTTP.
 *
 * @author Vaadin Ltd
 *
 */
@Component(immediate = true, service = OsgiVaadinStaticResource.class)
public class IconResource implements OsgiVaadinStaticResource {

    @Override
    public String getPath() {
        return "/META-INF/resources/icons/icon.png";
    }

    @Override
    public String getAlias() {
        return "/icons/icon.png";
    }

}
