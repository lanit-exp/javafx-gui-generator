package sample.geometry.labelInputLink;

import com.fasterxml.jackson.annotation.JsonProperty;
@lombok.Data
public class Links {
    @JsonProperty("image_id")
    private Long imageId = 0L;

    @JsonProperty("label_id")
    private Long labelId = 0L;

    @JsonProperty("input_id")
    private Long inputId = 0L;
}
