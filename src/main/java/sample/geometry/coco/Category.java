package sample.geometry.coco;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
public class Category {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("supercategory")
    private String superCategory = "";
}
