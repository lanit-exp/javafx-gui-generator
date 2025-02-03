package sample.geometry.labelInputLink;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@lombok.Data
public class RootLinks {

    @JsonProperty("links")
    private List<Links> linksList = new ArrayList<>();
}
