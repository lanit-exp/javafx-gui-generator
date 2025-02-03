package sample.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import sample.utils.Tags;
import sample.widget.child.ParentChild;
import sample.widget.child.Root;
import sample.widget.descriptions.DescriptionsWidget;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static sample.utils.ConfigFile.inputDir;

public class Converter implements Tags {
    private static ObjectMapper mapper;

    public static void toJSON(Object o, String baseFile) {
        mapper = new ObjectMapper();
        try {
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            mapper.writeValue(new File(baseFile), o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    public static List toJavaObjectDescription(String baseFile) {
        mapper = new ObjectMapper();
        if (baseFile.equalsIgnoreCase(inputDir + PATH_CONT_WIDGETS))
            return mapper.readValue(new File(baseFile), new TypeReference<List<DescriptionsWidget.ContWidgetDescr>>() {
            });
        else if (baseFile.equalsIgnoreCase(inputDir + PATH_COMP_WIDGETS))
            return mapper.readValue(new File(baseFile), new TypeReference<List<DescriptionsWidget.CompWidgetDescr>>() {
            });
        else if (baseFile.equalsIgnoreCase(inputDir + PATH_ATOMIC_WIDGETS))
            return mapper.readValue(new File(baseFile), new TypeReference<List<DescriptionsWidget.AtomicWidgetDescr>>() {
            });
        else
            throw new RuntimeException("Неверный путь");
    }

    public static ParentChild toJavaObjectTree(String baseFile) {
        mapper = new ObjectMapper();
        ParentChild child = null;
        try {
            Root root = mapper.readValue(new File(baseFile), Root.class);
            child = root.getRoot();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return child;
    }
}
