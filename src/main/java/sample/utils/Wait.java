package sample.utils;

public class Wait {
    public static void sec(double second) {
        try {
            Thread.sleep((int) second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
