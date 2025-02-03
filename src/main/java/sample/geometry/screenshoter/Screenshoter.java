package sample.geometry.screenshoter;


import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import lombok.SneakyThrows;
import sample.utils.Tags;

import javax.imageio.ImageIO;
import java.io.File;

public class Screenshoter implements Tags {

    @SneakyThrows
    public static void screen(Node root, File file) {
        WritableImage image = root.snapshot(new SnapshotParameters(), null);
        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
    }

}
