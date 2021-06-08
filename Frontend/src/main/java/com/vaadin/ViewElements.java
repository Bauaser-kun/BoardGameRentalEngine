package com.vaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;

public class ViewElements {
    public TextField createFilterField(String placeholder) {
         TextField textField = new TextField();
         textField.setPlaceholder(placeholder);
         textField.setValueChangeMode(ValueChangeMode.EAGER);
         textField.setClearButtonVisible(true);

         return textField;
    }

    public TextField createSearchField(String placeholder) {
        TextField textField = new TextField();
        textField.setPlaceholder(placeholder);
        textField.setValueChangeMode(ValueChangeMode.ON_CHANGE);
        textField.setClearButtonVisible(true);

        return textField;
    }

    public Button createButton(String placeholder) {
        Button button = new Button();
        button.setText(placeholder);

        return button;
    }

    public VerticalLayout createCenteredVerticalLayout() {
        VerticalLayout layout = new VerticalLayout();
        layout.setAlignItems(FlexComponent.Alignment.CENTER);

        return layout;
    }

    public ViewElements() {
    }
}
