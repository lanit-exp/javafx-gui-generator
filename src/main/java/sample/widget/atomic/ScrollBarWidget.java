package sample.widget.atomic;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollBar;
import javafx.scene.Node;
import sample.widget.child.ParentChild;
import sample.widget.interfaces.Widget;


public class ScrollBarWidget implements Widget {
    ScrollBar scrollBar;

    public ScrollBarWidget(ParentChild parent) {
        parent.setEnabled("enabled");
    }

    @Override
    public void createNode(ParentChild parentChild) {
        throw new RuntimeException("Нельзя создать этот виджет");
    }

    @Override
    public Node getNode() {
        return scrollBar;
    }

    public void setNode(ScrollBar scrollBar) {
        this.scrollBar = scrollBar;
    }

    public static class ScrollBarButton implements Widget {
        Node scrollBarButton;

        public ScrollBarButton(ParentChild parent) {
            parent.setEnabled("enabled");
            parent.setHasIcon("not_iconed");
            parent.setHasText("not_has_text");
        }

        @Override
        public void createNode(ParentChild children) {
            throw new RuntimeException("Нельзя создать этот виджет");
        }

        @Override
        public Node getNode() {
            return (Parent) scrollBarButton;
        }

        public void setNode(Node scrollBarButton) {
            this.scrollBarButton = scrollBarButton;
        }
    }
}
