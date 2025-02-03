package sample.geometry;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.stage.Stage;
import sample.geometry.coco.*;
import sample.geometry.labelInputLink.Links;
import sample.geometry.labelInputLink.RootLinks;
import sample.geometry.screenshoter.Screenshoter;
import sample.json.Converter;
import sample.utils.ConfigFile;
import sample.utils.Tags;
import sample.utils.UtilsFile;
import sample.widget.child.ParentChild;
import sample.widget.descriptions.DescriptionsWidget;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static sample.utils.ConfigFile.outputDir;


public class Geometry implements Tags {
    StringBuilder stringBuilderLocation = new StringBuilder();
    RootCoco rootCoco = new RootCoco();
    RootLinks rootLinks = new RootLinks();
    static long idScreen = 1;
    static long idAnnotation = 1;
    Bounds bounds;
    Bounds boundsParent;
    double widthRoot;
    double heightRoot;
    String name;
    File fileDarkNet;
    File fileGeometry;
    File fileScreen;

    public void createGeometry(Stage stage, ParentChild rootWidget, Node rootPane) {
        name = new SimpleDateFormat("yyyyMMdd-HHmmss-SS").format(Calendar.getInstance().getTime());

        widthRoot = stage.getWidth();
        heightRoot = stage.getHeight();

        if (ConfigFile.isYoloGeometry) {
            fileDarkNet = new File(outputDir + "darknet.labels");
            fileGeometry = new File(outputDir + "img-" + name + ".txt");
            UtilsFile.write(fileDarkNet, DescriptionsWidget.widgetNameStringBuilder.toString());
            UtilsFile.write(fileGeometry, geometryYoloFormat(rootWidget));
        }
        if (ConfigFile.isCocoGeometry) {
            geometryCocoFormat(rootWidget);
        }

        fileScreen = new File(outputDir + "img-" + name + ".png");
        Screenshoter.screen(rootPane, fileScreen);

        stringBuilderLocation.setLength(0);
    }

    private String geometryYoloFormat(ParentChild widget) {
        if (widget.isAddToGeometry()) {
            bounds = widget.getWidget().getNode().localToScene(widget.getWidget().getNode().getBoundsInLocal());
            if (widget.getParent() != null) {
                boundsParent = widget.getParent().getWidget().getNode().localToScene(widget.getParent().getWidget().getNode().getBoundsInLocal());
            }

            if (widget.getName().equals(TEXT_ELEMENT)
                    && widget.getParent().getName().equals(TEXT_AREA)
                    && widget.getParent().getVScrollable() != null
                    && widget.getParent().getVScrollable().equals("vert_scrollable")
                    && boundsParent.getHeight() < bounds.getHeight()) {

                stringBuilderLocation
                        .append(DescriptionsWidget.getId(widget.getName())).append(" ")
                        .append(((bounds.getMaxX() + bounds.getMinX()) / 2) / widthRoot).append(" ")
                        .append(((boundsParent.getMaxY() + bounds.getMinY()) / 2) / heightRoot).append(" ")
                        .append(bounds.getWidth() / widthRoot).append(" ")
                        .append((boundsParent.getHeight() - 10) / heightRoot).append(" ")
                        .append("\n");
            } else {
                stringBuilderLocation
                        .append(DescriptionsWidget.getId(widget.getName())).append(" ")
                        .append(((bounds.getMaxX() + bounds.getMinX()) / 2) / widthRoot).append(" ")
                        .append(((bounds.getMaxY() + bounds.getMinY()) / 2) / heightRoot).append(" ")
                        .append(bounds.getWidth() / widthRoot).append(" ")
                        .append(bounds.getHeight() / heightRoot).append(" ")
                        .append("\n");
            }
        }
        for (ParentChild children : widget.getChildrenList()) {
            geometryYoloFormat(children);
        }
        return stringBuilderLocation.toString();
    }

    Image image;

    private void geometryCocoFormat(ParentChild widget) {
        image = new Image();
        image.setId(idScreen);
        image.setWidth((long) widthRoot);
        image.setHeight((long) heightRoot);
        image.setFileName("img-" + name + ".png");

        rootCoco.getImages().add(image);

        getAnnotations(widget);
        idScreen++;
    }

    Bounds boundsAnnotations;
    Annotation annotation;
    Annotation.Attributes attributes;

    private void getAnnotations(ParentChild widget) {

        if (widget.isAddToGeometry()) {
            boundsAnnotations = widget.getWidget().getNode().localToScene(widget.getWidget().getNode().getBoundsInLocal());
            if (widget.getParent() != null) {
                boundsParent = widget.getParent().getWidget().getNode().localToScene(widget.getParent().getWidget().getNode().getBoundsInLocal());
            }
            annotation = new Annotation();
            annotation.setId(idAnnotation);
            annotation.setImageId(image.getId());
            annotation.setCategoryId((long) DescriptionsWidget.getId(widget.getName()) + 1);
            annotation.setArea((long) (boundsAnnotations.getWidth() * boundsAnnotations.getHeight()));

            if (widget.getName().equals("Label")) {
                widget.setIdAnnotation(idAnnotation);
            }

            if (widget.getName().equals("Input")){
                Links links = new Links();
                links.setImageId(idScreen);
                links.setInputId(idAnnotation);
                links.setLabelId(widget.getParent().getChildrenList().get(0).getIdAnnotation());
                rootLinks.getLinksList().add(links);
            }

            if (widget.getName().equals(TEXT_ELEMENT)
                    && widget.getParent().getName().equals(TEXT_AREA)
                    && widget.getParent().getVScrollable() != null
                    && widget.getParent().getVScrollable().equals("vert_scrollable")
                    && boundsParent.getHeight() < boundsAnnotations.getHeight()) {

                annotation.setBBox(new Float[]{
                        (float) boundsAnnotations.getMinX(),
                        (float) boundsAnnotations.getMinY(),
                        (float) boundsAnnotations.getWidth(),
                        (float) boundsParent.getHeight() - 10
                });
            } else {
                annotation.setBBox(new Float[]{
                        (float) boundsAnnotations.getMinX(),
                        (float) boundsAnnotations.getMinY(),
                        (float) boundsAnnotations.getWidth(),
                        (float) boundsAnnotations.getHeight()
                });
            }
            attributes = new Annotation.Attributes();

            if (widget.getName().equals("Input")) {
                String name = widget.getChildrenList().get(0).getName();
                switch (name) {
                    case (TEXT):
                        name = "text";
                        break;
                    case (DATE_PICKER):
                        name = "date_picker";
                        break;
                    case (COMBO_BOX):
                        name = "combobox";
                        break;
                }
                attributes.setType(name);
            }

            attributes.setCheckable(widget.getCheckable());
            attributes.setEnabled(widget.getEnabled());
            attributes.setHasText(widget.getHasText());
            attributes.setHScrollable(widget.getHScrollable());
            attributes.setVScrollable(widget.getVScrollable());
            attributes.setHasIcon(widget.getHasIcon());
            annotation.setAttributes(attributes);

            rootCoco.getAnnotations().add(annotation);
            idAnnotation++;
        }
        for (ParentChild children : widget.getChildrenList()) {
            getAnnotations(children);
        }
    }

    License license;
    Info info;
    Category category;
    File fileCoco;
    File fileLink;

    public void createCocoFile() {
        license = new License();
        rootCoco.getLicenses().add(license);
        info = new Info();
        rootCoco.setInfo(info);

        for (String nameWidget : DescriptionsWidget.listAllWidgetName) {
            category = new Category();
            category.setId((long) DescriptionsWidget.getId(nameWidget) + 1);
            category.setName(nameWidget);
            rootCoco.getCategories().add(category);
        }
        fileCoco = new File(outputDir + "coco.json");
        Converter.toJSON(rootCoco, fileCoco.getPath());

        fileLink = new File(outputDir + "label_input_link.json");
        Converter.toJSON(rootLinks, fileLink.getPath());
    }
}
