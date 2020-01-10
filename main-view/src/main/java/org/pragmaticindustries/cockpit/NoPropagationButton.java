package org.pragmaticindustries.cockpit;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

/**
 * This Button can be placed over clickable areas without propagating clicks to the element below.
 *
 * @author erwin.wagasow
 * created by erwin.wagasow on 08.04.2019
 */
@Tag("no-propagation-button")
@HtmlImport("frontend://styles/no-propagation-button.html")
public class NoPropagationButton extends PolymerTemplate<NoPropagationButton.NoPropagationButtonModel> implements
    HasStyle, HasSize {
    
    @Id("vaadinButton")
    private Button vaadinButton;
    
    public NoPropagationButton(String text) {
        this.vaadinButton.setText(text);
    }
    
    public NoPropagationButton(Component icon) {
        this.vaadinButton.setIcon(icon);
    }
    
    public NoPropagationButton(String text, Component icon) {
        this.vaadinButton.setIcon(icon);
        this.vaadinButton.setText(text);
        this.vaadinButton.setThemeName("material");
    }
    
    public NoPropagationButton() {
    }
    
    public Button getVaadinButton() {
        return vaadinButton;
    }
    
    public interface  NoPropagationButtonModel extends TemplateModel {
    
    }
}
