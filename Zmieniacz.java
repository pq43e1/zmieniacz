import java.awt.*;
import java.text.ParseException;
import java.util.Date;

public class Zmieniacz {

    public static int hei = 640;
    public static int wei = 400;
    public static String inSource, outSource, separator, prefix, infix, postfix, sprawdzacz;
    public static int licznikPlikow;
    public static boolean czyPrefixLiczy = false, czyInfixLiczy = false, czyPostfixLiczy = false;

    public static void main (String[] args){

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new MyFrame();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
