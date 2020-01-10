package org.pragmaticindustries.cockpit;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

/**
 * A Designer generated component for the landing-page-app-emblem.html template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("app-emblem")
@HtmlImport("frontend://custom/app-emblem.html")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AppEmblem extends PolymerTemplate<AppEmblem.AppEmblemModel> {
    
    public static final String ATTRIBUTE_TITLE = "title";
    @Id("appEmblem")
    private Div emblem;
    
    @Id("imageFrame")
    private Div imageFrame;
    
    @Id("image")
    private Image image;
    
    @Id("textFrame")
    private Div textFrame;
    
    @SuppressWarnings("squid:CommentedOutCodeLine")
    public AppEmblem(DashboardIcon icon) {
        if(icon.getImage() != null){
            image.setSrc(icon.getImage().getSrc());
        }else{
            image.setSrc("frontend/images/brain-gears.png");
        }
        
        textFrame.setText(icon.getName());
        textFrame.getElement().setAttribute(ATTRIBUTE_TITLE, icon.getDescription());
        
        emblem.getElement().setAttribute(ATTRIBUTE_TITLE, icon.getDescription());
        
        emblem.addClickListener(event -> icon.buttonClicked(UI.getCurrent()));
    
        //if(icon.getSubIcons() != null && !icon.getSubIcons().isEmpty()){
        //    int count = 0;
        //    for(DashboardSubIcon subIcon : icon.getSubIcons()) {
        //        NoPropagationButton subButton;
        //        if(subIcon.getImage() != null){
        //            Image image = subIcon.getImage();
        //            // image.setHeight("16px");
        //            image.getStyle().set("max-width", "24px");
        //            image.getStyle().set("max-height", "24px");
        //            // image.setWidth("auto");
        //            subButton = new NoPropagationButton(image);
        //        }else{
        //            subButton = new NoPropagationButton(VaadinIcon.COG.create());
        //        }
        //        emblem.getStyle().set("position", "relative");
        //        subButton.getStyle().set("position", "absolute");
        //        subButton.getStyle().set("left", (75 - 45 * count) + "px");
        //        subButton.getStyle().set("top", "0px");
        //        subButton.getVaadinButton().addClickListener(event -> subIcon.buttonClicked(UI.getCurrent()));
        //        emblem.add(subButton);
        //        count++;
        //    }
        //}
    }
    
    /**
     * Creates a new LaempeAppEmblem.
     *
     * @param moduleInformation Information about a app do be displayed
     */
    public AppEmblem(ModuleInformation moduleInformation) {
        if(moduleInformation.hasImage()){
            image.setSrc(moduleInformation.getImage().getSrc());
        }else{
            image.setSrc("frontend/images/brain-gears.png");
        }
        
        textFrame.setText(moduleInformation.getShortName());
        textFrame.getElement().setAttribute(ATTRIBUTE_TITLE, moduleInformation.getLongName());
        
        emblem.getElement().setAttribute(ATTRIBUTE_TITLE, moduleInformation.getDescription() == null ? moduleInformation.getLongName() : moduleInformation.getDescription());
        
        emblem.addClickListener(event -> UI.getCurrent().navigate(moduleInformation.getNavigationPath()));
        
        if(moduleInformation.hasConfig()){
            
            NoPropagationButton configButton = new NoPropagationButton(VaadinIcon.COG.create());
            emblem.getStyle().set("position", "relative");
            configButton.getStyle().set("position", "absolute");
            configButton.getStyle().set("left", "75px");
            configButton.getStyle().set("top", "0px");
            
            
            configButton.getVaadinButton().addClickListener(
                    event -> UI.getCurrent().navigate(moduleInformation.getNavigationPath() + "/config")
            );
            
            emblem.add(configButton);
        }
    }

    /**
     * This model binds properties between LaempeAppEmblem and landing-page-app-emblem.html
     */
    public interface AppEmblemModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
