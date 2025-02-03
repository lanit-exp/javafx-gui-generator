package sample.widget.child;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

@Data
@JsonRootName("root")
public class Root {
    private ParentChild root;
}
