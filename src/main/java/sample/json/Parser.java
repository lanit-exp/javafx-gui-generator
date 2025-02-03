package sample.json;

import sample.widget.child.ParentChild;
import sample.widget.descriptions.DescriptionsWidget;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static sample.utils.UtilsFile.getFilesTree;
import static sample.utils.Tags.*;

public class Parser {
    static DescriptionsWidget descriptions = new DescriptionsWidget();

    public static List<ParentChild> parseTree(String path) {
        List<ParentChild> rootWidgetsList = new ArrayList<>();
        for (File fileTree : getFilesTree(path))
            rootWidgetsList.add(Converter.toJavaObjectTree(fileTree.getAbsolutePath()));

        return rootWidgetsList;
    }

    public static void parseDescription(ParentChild widget) {
        if (widget.getPath().equals(PATH_CONT_WIDGETS)) {
            for (DescriptionsWidget.ContWidgetDescr contDescription : descriptions.getContList()) {

                if (contDescription.getName().equalsIgnoreCase(widget.getName())) {
                    widget.setNCols(contDescription.getNCols());
                    widget.setNRows(contDescription.getNRows());
                    widget.setDirection(contDescription.getDirection());
                    break;
                }
            }
        } else if (widget.getPath().equals(PATH_COMP_WIDGETS)) {
            for (DescriptionsWidget.CompWidgetDescr compDescription : descriptions.getCompList()) {

                if (compDescription.getName().equalsIgnoreCase(widget.getName())) {
                    widget.setNCols(compDescription.getNCols());
                    widget.setNRows(compDescription.getNRows());

                    for (ParentChild child : widget.getChildrenList()) {
                        for (DescriptionsWidget.CompWidgetDescr.Content content : compDescription.getContent()) {

                            if (child.getName().equalsIgnoreCase(content.getName())) {
                                child.setCol(content.getCol());
                                child.setRow(content.getRow());
                                child.setColSpan(content.getColSpan() + 1);
                                child.setRowSpan(content.getRowSpan() + 1);
                                child.setStyle(content.getStyle());
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
