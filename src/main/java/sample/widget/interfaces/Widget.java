package sample.widget.interfaces;

import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import sample.widget.atomic.*;
import sample.widget.child.ParentChild;
import sample.widget.composite.CompositeWidget;
import sample.widget.container.ContainerWidget;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static sample.utils.Generation.getColor;
import static sample.utils.Generation.getRandom;
import static sample.utils.Tags.*;

public interface Widget {


    void createNode(ParentChild children);

    Node getNode();

    static Widget createWidget(ParentChild child) {
        String name = child.getName();

        if (name.equals(CHECK_BOX)) return new CheckBoxWidget();
        else if (name.equals(TEXT)) return new TextFieldWidget();
        else if (name.equals(BUTTON)) return new ButtonWidget();
        else if (name.equals(RADIO)) return new RadioWidget();
        else if (name.equals(LABEL)) return new LabelWidget();
        else if (name.equals(DATE_PICKER)) return new DatePickerWidget();
        else if (name.equals(TABLE)) return new TableWidget();
        else if (name.equals(LIST)) return new ListWidget();
        else if (name.equals(TEXT_AREA)) return new TextAreaWidget();
        else if (name.equals(TREE_VIEW)) return new TreeViewWidget();
        else if (name.equals(COMBO_BOX)) return new ComboBoxWidget();
        else if (child.getType().equals(COMP_WIDGET)) return new CompositeWidget();
        else if (child.getType().equals(CONT_WIDGET)) return new ContainerWidget();

        else throw new IllegalArgumentException("Виджет " + child.getName() + " не найден");
    }

    static void setPathAndTypeWidget(ParentChild children) {
        switch (children.getName()) {
            case WINDOW:
            case REV_DIV_ITEM:
            case DIV_ITEM:
            case TOP_DIV_ITEM:
            case INPUT:
                children.setPath(PATH_COMP_WIDGETS);
                children.setType(COMP_WIDGET);
                break;

            case DIV:
            case R_WINDOW:
            case L_WINDOW:
                children.setPath(PATH_CONT_WIDGETS);
                children.setType(CONT_WIDGET);
                break;

            default:
                children.setPath(PATH_ATOMIC_WIDGETS);
                children.setType(ATOMIC_WIDGET);
                break;
        }
    }

    static void searchTextElement(ParentChild parent) {
        ScrollBar scrollBarV = null;
        if (parent.isNodeHasScrollBar()) {
            for (ScrollBar scrollBarNode : parent.getWidget().getNode().lookupAll(".scroll-bar").toArray(new ScrollBar[0])) {
                if (scrollBarNode.isVisible() & scrollBarNode.getScaleX() > 0)
                    if (scrollBarNode.getOrientation() == Orientation.VERTICAL)
                        scrollBarV = scrollBarNode;
            }
        }

        switch (parent.getName()) {
            case TEXT_AREA: {
                TextArea textArea = (TextArea) parent.getWidget().getNode();
                addTextWidget(parent, textArea.lookup(".text"));
                break;
            }
            case BUTTON: {
                Button button = (Button) parent.getWidget().getNode();
                addTextWidget(parent, (Text) button.lookup(".text"));
                break;
            }
            case LABEL: {
                Label label = (Label) parent.getWidget().getNode();
                addTextWidget(parent, (Text) label.lookup(".text"));
                break;
            }
            case COMBO_BOX: {
                ComboBox comboBox = (ComboBox) parent.getWidget().getNode();
                HBox hBox = (HBox) comboBox.getValue();
                Label label = (Label) hBox.lookup(".label");
                addTextWidget(parent, (Text) label.lookup(".text"));
                break;
            }
//            case TEXT: {
//                CustomTextField textField = (CustomTextField) parent.getWidget().getNode();
//                Text text =(Text) textField.lookup(".text");
//                addTextWidget(parent, text);
//                break;
//            }
            case LIST: {
                StackPane pane = (StackPane) parent.getWidget().getNode();
                ListView listView = (ListView) pane.getChildren().stream().findFirst().get();

                Object[]cells = listView.lookupAll(".cell").toArray();
                ((Cell)cells[getRandom(0, cells.length)]).setStyle("-fx-background-color:" + getColor() + ";");

                ObservableList<Label> listNode = listView.getItems();
                for (Label label : listNode) {
                    if (scrollBarV != null)
                        label.setMaxWidth((pane.getWidth() - 20) - scrollBarV.getWidth());
                    else
                        label.setMaxWidth(pane.getWidth() - 20);

                    Bounds boundsLabel = label.localToScene(label.getBoundsInLocal());
                    Bounds bounds = pane.localToScene(pane.getBoundsInLocal());

                    if (boundsLabel.getMinX() >= bounds.getMinX() && boundsLabel.getMaxY() <= bounds.getMaxY() - 20)
                        addTextWidget(parent, (Text) label.lookup(".text"));
                    else
                        label.setVisible(false);
                }
                break;
            }
            case TABLE: {
                TableView tableView = (TableView) parent.getWidget().getNode();
                Bounds bounds = tableView.localToScene(tableView.getBoundsInLocal());

                Set<Node> tableTitleCell = tableView.lookupAll(".column-header");
                for (Node tableColumnName : tableTitleCell) {
                        Bounds boundsCell = tableColumnName.localToScene(tableColumnName.getBoundsInLocal());

                        if (boundsCell.getMaxX() < bounds.getMaxX() - 40 && boundsCell.getMaxY() < bounds.getMaxY() - 40 && tableColumnName.lookup(".text").getBoundsInLocal().getWidth() > 10)
                            addTextWidget(parent, tableColumnName.lookup(".text"));
                        else
                            tableColumnName.setVisible(false);
                    }

                Set<Node> tableRowCell = tableView.lookupAll(".table-row-cell");

                for (Node tableRow : tableRowCell) {
                    TableRow<?> row = (TableRow<?>) tableRow;
                    Set<Node> cells = row.lookupAll(".table-cell");

                    for (Node node : cells) {
                        TableCell<?, ?> cell = (TableCell<?, ?>) node;
                        Label label = (Label) cell.lookup(".label");

                        if (label != null) {
                            Bounds boundsCell = label.localToScene(label.getBoundsInLocal());

                            if (boundsCell.getMaxX() < bounds.getMaxX() - 40 && boundsCell.getMaxY() < bounds.getMaxY() - 40) {
                                if (!ICONS.contains(label.getText()))
                                    addTextWidget(parent, (Text) label.lookup(".text"));
                            }else
                                    label.setVisible(false);
                        }
                    }
                }
                break;
            }
            case TREE_VIEW: {
                TreeView<?> treeView = (TreeView<?>) parent.getWidget().getNode();
                int count = 0;

                while (treeView.getTreeItem(count) != null) {
                    TreeItem treeItem = treeView.getTreeItem(count++);

                    if (scrollBarV != null) {
                        if (treeItem.getChildren().isEmpty())
                            ((Label) treeItem.getValue()).setMaxWidth((treeView.getWidth() - 60) - scrollBarV.getWidth());
                        else
                            ((Label) treeItem.getValue()).setMaxWidth((treeView.getWidth() - 25) - scrollBarV.getWidth());

                    } else {
                        if (treeItem.getChildren().isEmpty())
                            ((Label) treeItem.getValue()).setMaxWidth((treeView.getWidth() - 40));
                        else
                            ((Label) treeItem.getValue()).setMaxWidth(treeView.getWidth() - 25);
                    }

                    Label label = (Label) treeItem.getValue();

                    Bounds bounds = treeView.localToScene(treeView.getBoundsInLocal());
                    Bounds boundsLabel = label.localToScene(label.getBoundsInLocal());

                    if (boundsLabel.getMinX() >= bounds.getMinX() && boundsLabel.getMaxY() <= bounds.getMaxY() - 30)
                        addTextWidget(parent, (Text) label.lookup(".text"));
                    else
                        ((Label) treeItem.getValue()).setVisible(false);
                }
                break;
            }
        }
        for (ParentChild parentChild : parent.getChildrenList()) {
            searchTextElement(parentChild);
        }

    }


    static void addTextWidget(ParentChild parent, Node textElement) {
        if (textElement != null) {
            ParentChild childLabel = new ParentChild();
            TextElementWidget textElementWidget = new TextElementWidget(childLabel);
            textElementWidget.setNode(textElement);
            childLabel.setChildrenList(new ArrayList<>());
            childLabel.setName(TEXT_ELEMENT);
            childLabel.setType(ATOMIC_WIDGET);
            childLabel.setWidget(textElementWidget);
            parent.getChildrenList().add(childLabel);
            childLabel.setParent(parent);
        }
    }

    static void searchScrollBars(ParentChild parent) {
        ParentChild childScrollBar;
        ParentChild childButton;
        Node[] scrollBars;
        List<Node> scrollBarButtons;
        ScrollBarWidget.ScrollBarButton scrollBarButton;
        ScrollBarWidget scrollBarWidget;
        String vScrollable = "vert_unscrollable";
        String hScrollable = "hor_unscrollable";

        if (parent.isNodeHasScrollBar()) {
            scrollBars = new ScrollBar[2];
            parent.getWidget().getNode().lookupAll(".scroll-bar").toArray(scrollBars);

            for (Node scrollBarNode : scrollBars) {
                if (scrollBarNode.isVisible() & scrollBarNode.getScaleX() > 0) {
                    scrollBarButtons = new ArrayList<>();
                    scrollBarButtons.add(scrollBarNode.lookup(".increment-button"));
                    scrollBarButtons.add(scrollBarNode.lookup(".decrement-button"));
                    ScrollBar scrollBar = (ScrollBar) scrollBarNode;

                    if (scrollBar.getOrientation() == Orientation.HORIZONTAL)
                        hScrollable = "hor_scrollable";
                    else
                        vScrollable = "vert_scrollable";

                    for (Node node : scrollBarButtons) {
                        childButton = new ParentChild();

                        scrollBarButton = new ScrollBarWidget.ScrollBarButton(childButton);
                        scrollBarButton.setNode(node);

                        childButton.setChildrenList(new ArrayList<>());
                        childButton.setName(SCROLL_BAR_BUTTON);
                        childButton.setType(ATOMIC_WIDGET);
                        childButton.setWidget(scrollBarButton);

                        parent.getChildrenList().add(childButton);
                    }
                    childScrollBar = new ParentChild();

                    scrollBarWidget = new ScrollBarWidget(childScrollBar);
                    scrollBarWidget.setNode(scrollBar);

                    childScrollBar.setChildrenList(new ArrayList<>());
                    childScrollBar.setName(SCROLL_BAR);
                    childScrollBar.setType(ATOMIC_WIDGET);
                    childScrollBar.setWidget(scrollBarWidget);

                    parent.getChildrenList().add(childScrollBar);
                    parent.setHScrollable(hScrollable);
                    parent.setVScrollable(vScrollable);
                }
            }
        }
        for (ParentChild parentChild : parent.getChildrenList()) {
            searchScrollBars(parentChild);
        }
    }

    static void searchButtons(ParentChild parent) {
        ParentChild childButton;
        Node[] stackPane;
        DatePickerWidget.ButtonDatePicker buttonDatePicker;

        if (parent.isNodeHasButton()) {
            stackPane = new StackPane[10];
            parent.getWidget().getNode().lookupAll(".arrow-button").toArray(stackPane);

            for (Node buttonNode : stackPane) {
                if (buttonNode != null) {
                    if (buttonNode.isVisible()) {
                        childButton = new ParentChild();

                        buttonDatePicker = new DatePickerWidget.ButtonDatePicker(childButton);
                        buttonDatePicker.setNode(buttonNode);

                        childButton.setChildrenList(new ArrayList<>());
                        childButton.setName(BUTTON);
                        childButton.setType(ATOMIC_WIDGET);
                        childButton.setWidget(buttonDatePicker);
                        parent.getChildrenList().add(childButton);
                    }
                }
            }
        }

        for (ParentChild parentChild : parent.getChildrenList())
            searchButtons(parentChild);
    }

    static void sizeCheckBox(ParentChild parent) {
        int sizeCheckBox = ((int) (2 + Math.random() * 8));
        if (parent.isNodeCheckbox()) {
            parent.getWidget().getNode().lookup(".box").setStyle("-fx-padding: " + sizeCheckBox + ";");
        }
        for (ParentChild parentChild : parent.getChildrenList())
            sizeCheckBox(parentChild);
    }

    static void sizeRadioButton(ParentChild parent) {
        int sizeRadioButton = ((int) (4 + Math.random() * 9));
        if (parent.isNodeRadioButton()) {
            parent.getWidget().getNode().lookup(".radio").setStyle("-fx-padding: " + sizeRadioButton + ";");
        }
        for (ParentChild parentChild : parent.getChildrenList())
            sizeRadioButton(parentChild);
    }

}