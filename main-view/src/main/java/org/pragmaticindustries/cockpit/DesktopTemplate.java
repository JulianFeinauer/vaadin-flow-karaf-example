package org.pragmaticindustries.cockpit;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Main page of this application.
 *
 * @author jfeinauer
 *
 * ignore MaximumInheritanceDepth: too much inheritance
 */
@Route("")
@Theme(Lumo.class)
@Tag("desktop-template")
@HtmlImport("/frontend/custom/desktop-template.html")
@Uses(AppEmblem.class)
@SuppressWarnings("squid:MaximumInheritanceDepth")
public class DesktopTemplate extends PolymerTemplate<DesktopTemplate.DesktopTemplateModel> {

    private transient List<ModuleInformation> allApps;
    private transient List<DashboardIcon> allIcons;

    @Id("content-frame")
    private Div contentFrame;

    @Id("headline")
    private Div headline;

    @Reference(cardinality = ReferenceCardinality.MANDATORY)
    AppService appService;

    @Reference(cardinality = ReferenceCardinality.MANDATORY)
    IconService iconService;

    /**
     * Creates a new LandingPageTemplate.
     */
    public DesktopTemplate() {
        // Do Nothing
        System.out.println("Hallo");
    }

    @Activate
    public void init() {
        // You can initialise any data required for the connected UI components here.

        allApps                 = appService.getAllApps();
        allIcons                = iconService.getAllIcons();

        UI.getCurrent().access(() -> allApps.forEach(app -> {
            contentFrame.add(new AppEmblem(app));
        }));

        UI.getCurrent().access(() -> allIcons
            .stream()
            .sorted(Comparator.comparingLong(DashboardSubIcon::getSortPriority))
            .filter(DashboardSubIcon::isVisible)
            // .filter(icon1 -> accessCheckService.map(accessCheckService1 -> accessCheckService1.isVisible(icon1)).orElse(true))
            .forEach(icon -> {
                contentFrame.add(new AppEmblem(icon));
            }));

        headline.removeAll();
        headline.setText((getTranslation("lf.appheadline")));
    }

    public interface DesktopTemplateModel extends TemplateModel { }
}
