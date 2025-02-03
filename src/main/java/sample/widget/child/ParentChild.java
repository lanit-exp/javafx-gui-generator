package sample.widget.child;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import sample.json.Parser;
import sample.widget.interfaces.Widget;

import java.util.List;

import static sample.utils.Tags.cyr;

@Getter
@Setter
public class ParentChild {
    @JsonProperty("name")
    private String name;
    @JsonProperty("children")
    private List<ParentChild> childrenList;

    private Widget widget;
    private String type;
    private String path;
    private String direction;
    private long col;
    private long row;
    private long nRows;
    private long nCols;
    private long rowSpan;
    private long colSpan;
    private long max_nWidgets;
    private long min_nWidgets;
    private Boolean sometimes = false;
    private List<String> style;
    private ParentChild parent;
    private int widthWidget = ((int) (120 + Math.random() * 170));
    private String checkable;
    private String enabled;
    private String vScrollable;
    private String hScrollable;
    private String hasText;
    private String hasIcon;
    private long IdAnnotation;
    private boolean addToGeometry = true;
    private boolean nodeHasScrollBar = false;
    private boolean nodeHasButton = false;
    private boolean nodeCheckbox = false;
    private boolean nodeRadioButton = false;
    private boolean nodeComboBox = false;
    private boolean nodeHasLabel = false;
    public static String ch;


    public void initialization() {
        ch = String.valueOf(cyr.charAt((int) Math.floor(Math.random() * cyr.length())));
        for (ParentChild child : this.getChildrenList())
            child.setParent(this);
        Widget.setPathAndTypeWidget(this);
        Parser.parseDescription(this);
        this.setWidget(Widget.createWidget(this));
        widget.createNode(this);
    }
}
