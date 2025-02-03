package sample.utils;

import org.ini4j.Wini;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ConfigFile {
    public static int timeSwitch;
    public static String inputDir;
    public static String outputDir;
    public static String outputFormats;
    public static String apiFonts;
    public static String widthInput;
    public static boolean markComposite;
    public static boolean isCocoGeometry = false;
    public static boolean isYoloGeometry = false;
    public static boolean equalLetter;
    public static double probabilityLongInput;
    public static int widthInputMin;
    public static int widthInputMax;
    public static String fontSize;
    public static int fontSizeMin;
    public static int fontSizeMax;
    public static double windowWidth;
    public static double windowHeight;
    public static List<String> listFonts;
    public static List<String> listFontsSize;
    public static List<String> listInputSettings;

    public static void getConfig() {

        Wini ini = null;
        try {
            ini = new Wini(new File("Config.ini"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert ini != null;
        inputDir = ini.get("path", "input_dir", String.class);
        outputDir = ini.get("path", "output_dir", String.class);

        outputFormats = ini.get("setting", "output_formats", String.class);
        markComposite = ini.get("setting", "mark_composite", boolean.class);
        windowWidth = ini.get("setting", "wight_window", double.class);
        windowHeight = ini.get("setting", "height_window", double.class);
        equalLetter = ini.get("setting", "equal_letter", boolean.class);
        timeSwitch = ini.get("setting", "time_switch", int.class);

        widthInput = ini.get("setting", "width_input", String.class);
        listInputSettings = Arrays.asList(widthInput.replaceAll(" ","").split(","));
        probabilityLongInput = Double.parseDouble(listInputSettings.get(0));
        widthInputMin = Integer.parseInt(listInputSettings.get(1));
        widthInputMax = Integer.parseInt(listInputSettings.get(2));

        apiFonts = ini.get("font", "apiFonts", String.class);
        String fonts = ini.get("font", "fonts", String.class);
        listFonts = Arrays.asList(fonts.split(";"));

        fontSize = ini.get("font", "font_size", String.class);
        listFontsSize = Arrays.asList(fontSize.replaceAll(" ","").split(","));
        fontSizeMin = Integer.parseInt(listFontsSize.get(0));
        fontSizeMax = Integer.parseInt(listFontsSize.get(1));


        if (outputFormats.contains("coco"))
            isCocoGeometry = true;
        if (outputFormats.contains("yolo"))
            isYoloGeometry = true;

    }

    public static String getRandomFonts() {
        return listFonts.get((int) Math.floor(Math.random() * listFonts.size()));
    }

    public static String getApiFonts() {
        return apiFonts;
    }
}
