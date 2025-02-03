package sample.geometry.coco;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
public class Annotation {

    @JsonProperty("id")
    private Long id = 0L;

    @JsonProperty("image_id")
    private Long imageId;

    @JsonProperty("category_id")
    private Long categoryId;

    @JsonProperty("segmentation")
    private Object[] segmentation = {};

    @JsonProperty("area")
    private Long area;

    @JsonProperty("bbox")
    private Float[] bBox;

    @JsonProperty("iscrowd")
    private Long isCrowd = 0L;

    @JsonProperty("attributes")
    private Attributes attributes;

    @lombok.Data
    public static class Attributes {

        @JsonProperty("occluded")
        private Boolean occluded = false;

        @JsonProperty("type")
        private String type;

        @JsonProperty("checkable")
        private String checkable;

        @JsonProperty("enableable")
        private String enabled;

        @JsonProperty("vscrolable")
        private String vScrollable;

        @JsonProperty("hscrolable")
        private String hScrollable;

        @JsonProperty("has_text")
        private String hasText;

        @JsonProperty("iconed")
        private String hasIcon;
    }
}
