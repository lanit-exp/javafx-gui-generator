package sample.geometry.coco;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@lombok.Data
public class RootCoco {
    @JsonProperty("licenses")
    private List<License> licenses = new ArrayList<>();

    @JsonProperty("info")
    private Info info = new Info();

    @JsonProperty("categories")
    private List<Category> categories = new ArrayList<>();

    @JsonProperty("images")
    private List<Image> images = new ArrayList<>();

    @JsonProperty("annotations")
    private List<Annotation> annotations = new ArrayList<>();
}
