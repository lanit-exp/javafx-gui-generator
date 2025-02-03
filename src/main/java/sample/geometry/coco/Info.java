package sample.geometry.coco;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
public class Info {
    @JsonProperty("contributor")
    private String contributor = "";

    @JsonProperty("date_created")
    private String dateCreated= "";

    @JsonProperty("description")
    private String description= "";

    @JsonProperty("url")
    private String url= "";

    @JsonProperty("version")
    private String version= "";

    @JsonProperty("year")
    private String year= "";
}
