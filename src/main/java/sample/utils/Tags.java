package sample.utils;

public interface Tags {
    /**
     * Param
     */
    String TAG_V = "v",
    /**
     * Cont widget
     */
    PATH_CONT_WIDGETS = "descr/cont_widget_descr.json",
            CONT_WIDGET = "contWidget",
            L_WINDOW = "LWindow",
            R_WINDOW = "RWindow",
            DIV = "Div",
    /**
     * Atomic widget
     */
    PATH_ATOMIC_WIDGETS = "descr/atomic_widget_descr.json",
            ATOMIC_WIDGET = "atomicWidget",
            CHECK_BOX = "Checkbox",
            TEXT = "Text",
            TEXT_ELEMENT = "TextElement",
            BUTTON = "Button",
            RADIO = "Radio",
            LABEL = "Label",
            TABLE = "Table",
            LIST = "List",
            TEXT_AREA = "TextArea",
            TREE_VIEW = "TreeView",
            COMBO_BOX = "ComboBox",
            SCROLL_BAR = "ScrollBar",
            DATE_PICKER = "DatePicker",
            PROGRESS_BAR = "ProgressBar",
            SCROLL_BAR_BUTTON = "ScrollBarButton",
    /**
     * Composite widget
     */
    PATH_COMP_WIDGETS = "descr/comp_widget_descr.json",
            WINDOW = "Window",
            COMP_WIDGET = "compWidget",
            REV_DIV_ITEM = "RevDivItem",
            DIV_ITEM = "DivItem",
            TOP_DIV_ITEM = "TopDivItem",
            INPUT = "Input",
    /**
     * Path
     */
    PATH_TREE = "trees",
            PATH_STYLE = "styles",
            PATH_ICONS = "icons",
    /**
     * Text
     */
    DIG = "0123456789",
            ICON = "Картинка",
            WORD = "Слово",
            EQ_WORD = "Слова с одинаковой первой буквой",
            SYM = "^_.-'#№@»`«&,().!@#;:'<>/*()_@#$%&*!?|",
            cyr = "абвгдзикмнопрст",
            CYR = "АБВГДЕЁЖЗИКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ",
            lat = "abcdefghijklmnopqrstuvwxyz",
            LAT = "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
            ICONS = "⏰⏱⏲⏳⏴⏵⏶⏷⏸⏹⏺❌❍❎❐❑❒❓❔❕❖❗⛰⛱⛲⛳⛴⛵⛶⛷⛸⛹⛺⛻⛼⛽⛾⛿✀✁✂✃✄✅✆✇✈✉✊✋✌✍✎✏✐✑✒✓✔✕✖✗✘+-❶❷❸❹❺❻❼❽❾❿↕",
            Cyr = CYR + cyr,
            Lat = lat + LAT,
            ALL = DIG + Cyr + Lat + DIG + SYM;
}