package sample.widget.atomic;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import sample.utils.Generation;
import sample.widget.child.ParentChild;
import sample.widget.interfaces.Widget;

import java.time.LocalDate;

import static sample.utils.ConfigFile.*;

public class DatePickerWidget implements Widget {
    DatePicker datePicker;
    String datePickerIsEnabled = "disabled";
    String datePickerHasText = "not_has_text";

    @Override
    public void createNode(ParentChild parentChild) {
        int parentNumb = parentChild.getParent().getParent().getWidthWidget();
        datePicker = new DatePicker(LocalDate.now());
        datePicker.getStyleClass().add("datePicker");

        if (Math.random() < probabilityLongInput) {
            datePicker.setMinWidth(Generation.getRandom(widthInputMin, widthInputMax));
        } else {
            datePicker.setMinWidth(parentNumb / 1.5);
            datePicker.setMaxWidth(parentNumb / 1.5);
        }
        datePickerHasText = "has_text";
        datePickerIsEnabled = "enabled";

        parentChild.getParent().setEnabled(datePickerIsEnabled);
        parentChild.getParent().setHasText(datePickerHasText);
        parentChild.setAddToGeometry(false);
        parentChild.setNodeHasButton(true);
    }

    @Override
    public Node getNode() {
        return datePicker;
    }


    public static class ButtonDatePicker implements Widget {
        Node buttonDatePicker;
        String buttonDatePickerHasIcon = "not_iconed";
        String buttonDatePickerHasText = "not_has_text";
        String buttonDatePickerEnabled = "disabled";

        public ButtonDatePicker(ParentChild parent) {
            buttonDatePickerEnabled = "enabled";
            parent.setEnabled(buttonDatePickerEnabled);
            parent.setHasIcon(buttonDatePickerHasIcon);
            parent.setHasText(buttonDatePickerHasText);
        }

        @Override
        public void createNode(ParentChild children) {
            throw new RuntimeException("Нельзя создать этот виджет");
        }

        @Override
        public Node getNode() {
            return (Parent) buttonDatePicker;
        }

        public void setNode(Node buttonDatePicker) {
            this.buttonDatePicker = buttonDatePicker;
        }


    }
}