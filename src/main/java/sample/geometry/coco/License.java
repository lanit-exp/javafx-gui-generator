package sample.geometry.coco;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
public class License {
    @JsonProperty("name")
    private String name = "";

    @JsonProperty("id")
    private Long id = 0L;

    @JsonProperty("url")
    private String url = "";
}
