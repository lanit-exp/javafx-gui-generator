package sample.widget.atomic;

import javafx.scene.Node;
import org.controlsfx.control.textfield.CustomTextField;
import sample.utils.Tags;
import sample.utils.Generation;
import sample.widget.child.ParentChild;
import sample.widget.interfaces.Widget;

import java.util.ArrayList;

import static sample.utils.ConfigFile.*;

public class TextFieldWidget implements Widget, Tags {
    CustomTextField textFieldCustom;
    String textFieldHasText = "not_has_text";
    String textFieldIsEnabled = "disabled";
    ParentChild child;
    CheckBoxWidget checkBoxWidget;

    @Override
    public void createNode(ParentChild parentChild) {
        int parentNumb = parentChild.getParent().getParent().getWidthWidget();
        textFieldCustom = new CustomTextField();
        textFieldCustom.getStyleClass().add("textField");

        if (Math.random() < probabilityLongInput) {
            textFieldCustom.setMinWidth(Generation.getRandom(widthInputMin, widthInputMax));
        } else {
            textFieldCustom.setMinWidth(parentNumb / 1.5);
            textFieldCustom.setMaxWidth(parentNumb / 1.3);
        }

//        if (parentNumb % 2 == 0) {
//            textFieldCustom.setText();
//            textFieldHasText = "has_text";
//        }
        if (parentNumb < 130) {
            child = new ParentChild();
            child.setName(CHECK_BOX);
            child.setType(ATOMIC_WIDGET);
            child.setChildrenList(new ArrayList<>());

            checkBoxWidget = new CheckBoxWidget();
            checkBoxWidget.createNode(parentChild);
            child.setWidget(checkBoxWidget);
            parentChild.getChildrenList().add(child);
            textFieldCustom.setMinWidth(parentNumb / 1.2);
            textFieldCustom.setMaxWidth(parentNumb / 1.0);


            if (parentNumb % 2 == 0)
                textFieldCustom.setLeft(child.getWidget().getNode());
            else
                textFieldCustom.setRight(child.getWidget().getNode());
        }


        textFieldIsEnabled = "enabled";
        parentChild.getParent().setEnabled(textFieldIsEnabled);
        parentChild.getParent().setHasText(textFieldHasText);
        parentChild.setAddToGeometry(false);

    }

    @Override
    public Node getNode() {
        return textFieldCustom;
    }

}