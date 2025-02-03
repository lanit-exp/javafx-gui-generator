package sample.widget.atomic;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.utils.Generation;
import sample.utils.Tags;
import sample.utils.UtilsFile;
import sample.widget.child.ParentChild;
import sample.widget.interfaces.Widget;

import static sample.utils.ConfigFile.inputDir;

public class ButtonWidget implements Widget, Tags {
    Button button;
    ImageView imageView;
    Image image;
    String buttonHasIcon = "not_iconed";
    String buttonHasText = "not_has_text";
    String buttonEnabled = "disabled";

    @Override
    public void createNode(ParentChild parentChild) {
        button = new Button();
        button.getStyleClass().add("button");

        image = new Image(String.valueOf(UtilsFile.getRandomFile(inputDir + PATH_ICONS).toURI()));
        imageView = new ImageView(image);
        button.setGraphic(imageView);

        buttonHasIcon = "iconed";
        buttonEnabled = "enabled";

        int parentNumber = parentChild.getParent().getWidthWidget();

        if (parentNumber > 130) {
            button.setMinWidth(parentChild.getParent().getWidthWidget());
            button.setMaxWidth(parentChild.getParent().getWidthWidget());
            button.setText(Generation.getText(WORD, 1, 1));
            button.setAlignment(Pos.CENTER_LEFT);

            imageView.setFitHeight(25);
            imageView.setFitWidth(25);

            buttonHasText = "has_text";
            parentChild.setHasText(buttonHasText);
            parentChild.setNodeHasLabel(true);
        } else {
            button.setStyle("-fx-background-color: transparent;");
            imageView.setFitHeight(parentNumber / 4.0);
            imageView.setFitWidth(parentNumber / 4.0);
        }
        parentChild.setHasIcon(buttonHasIcon);
        parentChild.setEnabled(buttonEnabled);
    }

    @Override
    public Node getNode() {
        return button;
    }

    public void setNode(Button button) {
        this.button = button;
    }
}
