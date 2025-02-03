package sample.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import lombok.Cleanup;
import sample.widget.child.ParentChild;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import static sample.utils.ConfigFile.equalLetter;

public class Generation implements Tags {

    static List<String> listWord;

    public static void createText() {
        try {
            String[] arrayWord = new String[0];
            URL url = new URL("https://fish-text.ru/get?format=html&number=200");
            @Cleanup BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                arrayWord = line.replaceAll(",", "").replaceAll("\\.", "").split(" ");
            }
            listWord = new ArrayList<>();
            for (String s : arrayWord) {
                if (s.length() > 5 && s.length() < 20)
                    listWord.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* Генерация текста для виджетов */
    public static String getText(String typeSymbol, int min, int max) {
        String text = "";
        int count = ((int) (min + Math.random() * max));


        switch (typeSymbol) {
            case (Cyr):
                text = generationSymbol(Cyr, count);
                break;
            case (Lat):
                text = generationSymbol(Lat, count);
                break;
            case (SYM):
                text = generationSymbol(SYM, count);
                break;
            case (DIG):
                text = generationSymbol(DIG, count);
                break;
            case (ALL):
                text = generationSymbol(ALL, count);
                break;
            case (WORD):
                text = generationWord(count);
                break;
            case (EQ_WORD):
                text = generationEqWord(count);
                break;
            case (ICON):
                text = generationSymbol(ICONS, count);
                break;
        }
        return text;
    }

    private static String generationSymbol(String symbols, int count) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < count; i++) {
            int randomNumber = (int) Math.floor(Math.random() * symbols.length());
            char c = symbols.charAt(randomNumber);
            text.append(c);
        }
        return text.toString();
    }

    private static String generationWord(int count) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < count; i++) {
            int randomNumber = (int) Math.floor(Math.random() * listWord.size());
            text.append(listWord.get(randomNumber)).append(" ");
        }
        return text.toString();
    }
    private static String generationEqWord(int count) {
        List<String> listEqualsWord = new ArrayList<>();
        for (String s : listWord) {
            if (s.substring(0, 1).equalsIgnoreCase(ParentChild.ch)) {
                listEqualsWord.add(s.substring(0, 1).toUpperCase() + s.substring(1));
            }
        }
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < count; i++) {
            int randomNumber = (int) Math.floor(Math.random() * listEqualsWord.size());
            text.append(listEqualsWord.get(randomNumber)).append(" ");
        }
        return text.toString();
    }

    public static ObservableList<Label> createObservableList(int col, int maxCountWord) {
        ObservableList<Label> details = null;
        Collection<Label> list = new ArrayList<>();
        for (int i = 0; i < col; i++) {
            if (equalLetter)
            list.add(new Label(Generation.getText(EQ_WORD, 1, maxCountWord)));
            else
            list.add(new Label(Generation.getText(WORD, 1, maxCountWord)));
            details = FXCollections.observableArrayList(list);
        }
        return details;
    }

    public static int getRandom(int min, int max) {
        return (int) (min + Math.random() * max);
    }

    public static String getColor() {
        Random obj = new Random();
        int rand_num = obj.nextInt(0xffffff + 1);
        return String.format("#%06x", rand_num);
    }


}