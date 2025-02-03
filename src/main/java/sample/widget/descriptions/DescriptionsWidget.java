package sample.widget.descriptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import sample.json.Converter;
import sample.utils.Tags;

import java.util.ArrayList;
import java.util.List;

import static sample.utils.ConfigFile.inputDir;
import static sample.utils.ConfigFile.markComposite;

@Value
public class DescriptionsWidget implements Tags {
    public static List<String> listAllWidgetName;
    public static StringBuilder widgetNameStringBuilder;

    List<ContWidgetDescr> contList;
    List<CompWidgetDescr> compList;
    List<AtomicWidgetDescr> atomicList;

    public DescriptionsWidget() {
        contList = Converter.toJavaObjectDescription(  inputDir + PATH_CONT_WIDGETS);
        compList = Converter.toJavaObjectDescription(  inputDir + PATH_COMP_WIDGETS);
        atomicList = Converter.toJavaObjectDescription(inputDir + PATH_ATOMIC_WIDGETS);
        createListWidgetName();
    }

    @lombok.Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ContWidgetDescr {
        @JsonProperty("name")
        private String name;

        @JsonProperty("solo")
        private Boolean solo;

        @JsonProperty("prob")
        private double prob;

        @JsonProperty("direction")
        private String direction;

        @JsonProperty("nrows")
        private Long nRows = 30L;

        @JsonProperty("ncols")
        private Long nCols = 30L;

        @JsonProperty("children")
        private List<String> children;
    }

    @lombok.Data
    public static class CompWidgetDescr {
        @JsonProperty("name")
        private String name;

        @JsonProperty("solo")
        private Boolean solo;

        @JsonProperty("style")
        private List<String> style;

        @JsonProperty("prob")
        private double prob;

        @JsonProperty("nrows")
        private long nRows;

        @JsonProperty("ncols")
        private long nCols;

        @JsonProperty("content")
        private List<Content> content;

        @lombok.Data
        public static class Content {
            @JsonProperty("name")
            private String name;

            @JsonProperty("group")
            private long group;

            @JsonProperty("row")
            private long row;

            @JsonProperty("col")
            private long col;

            @JsonProperty("style")
            private List<String> style;

            @JsonProperty("row_span")
            private Long rowSpan = 0L;

            @JsonProperty("col_span")
            private Long colSpan = 0L;

            @JsonProperty("prob")
            private Double prob;
        }
    }

    @lombok.Data
    public static class AtomicWidgetDescr {
        @JsonProperty("name")
        private String name;

        @JsonProperty("prob")
        private double prob;

        @JsonProperty("style")
        @JsonIgnoreProperties(value = {""})
        private List<String> style;
    }

    private void createListWidgetName() {
        widgetNameStringBuilder = new StringBuilder();
        listAllWidgetName = new ArrayList<>();

        if (markComposite) {
            for (ContWidgetDescr contWidget : contList)
                listAllWidgetName.add(contWidget.getName());
            for (CompWidgetDescr compWidget : compList)
                listAllWidgetName.add(compWidget.getName());
        } else
            listAllWidgetName.add(INPUT);

        for (AtomicWidgetDescr atomicWidget : atomicList)
            listAllWidgetName.add(atomicWidget.getName());

        listAllWidgetName.add(SCROLL_BAR);
        listAllWidgetName.add(SCROLL_BAR_BUTTON);
        listAllWidgetName.add(TEXT_ELEMENT);

//        listAllWidgetName.add(BUTTON);
//        listAllWidgetName.add(CHECK_BOX);
//        listAllWidgetName.add(LABEL);
//        listAllWidgetName.add(LIST);
//        listAllWidgetName.add(RADIO);
//        listAllWidgetName.add(TABLE);
//        listAllWidgetName.add(TEXT_AREA);
//        listAllWidgetName.add(TREE_VIEW);

        listAllWidgetName.sort(String.CASE_INSENSITIVE_ORDER);

        for (String name : listAllWidgetName) {
            widgetNameStringBuilder.append(name).append("\n");
        }
    }

    public static int getId(String name) {
        int id = -1;

        for (int i = 0; i < listAllWidgetName.size(); i++) {
            if (listAllWidgetName.get(i).equals(name))
                id = i;
        }
        if (id == -1)
            throw new RuntimeException("id для " + name + " не найдено");

        return id;
    }
}


