package sample.geometry.coco;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
public class Image {

    public Image() {
        id++;
    }

    @JsonProperty("id")
    private Long id = 0L;

    @JsonProperty("width")
    private Long width;

    @JsonProperty("height")
    private Long height;

    @JsonProperty("file_name")
    private String fileName;

    @JsonProperty("license")
    private Long license = 0L;

    @JsonProperty("flickr_url")
    private String flickrUrl = "";

    @JsonProperty("coco_url")
    private String cocoUrl = "";

    @JsonProperty("date_captured")
    private Long dateCaptured = 0L;
}
