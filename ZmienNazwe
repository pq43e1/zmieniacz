import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ZmienNazwe {

    String in, out, count;
    File plik, plik2;
    File[] tablicaPlikow, tablicaBackup;
    int licznik = 1;

    InputStream inputStream = null;
    OutputStream outputStream = null;

    public ZmienNazwe(){
        this.in = Zmieniacz.inSource;
        this.out = Zmieniacz.outSource+"backup\\";
        try {
            plik = new File(in);
            plik2 = new File(out);

            txt("Tworzenie folderu backup.");
            File backup = new File(out);
            if(backup.exists())
            {
                int ostrzezenieUsuniecieBackupu = JOptionPane.showConfirmDialog(null, "Folder backup istnieje w "+out+" \nCzy usunac jego zawartosc?",
                        "Ostrzezenie", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if(ostrzezenieUsuniecieBackupu == 0)
                {
                    usunZawartosc();
                }
            }
            else
            Files.createDirectories(Paths.get(out));



            txt("sprawdzam   :"+in+"backup");

            tablicaPlikow = plik.listFiles();

            txt(out);

            txt("Pliki w folderze: ");
            for (File file : tablicaPlikow) {
                if (!file.isDirectory()) {


                    String sciezka = in + file.getName();
                    txt(sciezka + " ");
                    Path source = Paths.get(sciezka);

                    count = Integer.toString(licznik);

                    String ext = " ";                                                   //pobiera rozszerzenie
                    int Iext = file.getName().lastIndexOf('.');
                    if (Iext > 0) ext = file.getName().substring(Iext + 1);

                    outputStream = new FileOutputStream(out+file.getName());
                    inputStream = new FileInputStream(sciezka);
                    byte[] buffer = new byte[1024];
                    int length = 0;
                    while((length = inputStream.read(buffer)) >0) {
                        outputStream.write(buffer, 0, length);
                    }
                    inputStream.close();
                    outputStream.close();

                    if (Zmieniacz.czyPrefixLiczy) {
                        if (licznik < 10)
                            Files.move(source, source.resolveSibling("0" + count + Zmieniacz.separator + Zmieniacz.infix + Zmieniacz.separator + Zmieniacz.postfix + "." + ext));
                        else
                            Files.move(source, source.resolveSibling(count + Zmieniacz.separator + Zmieniacz.infix + Zmieniacz.separator + Zmieniacz.postfix + "." + ext));
                    } else if (Zmieniacz.czyInfixLiczy) {
                        if (licznik < 10)
                            Files.move(source, source.resolveSibling(Zmieniacz.prefix + Zmieniacz.separator + "0" + count + Zmieniacz.separator + Zmieniacz.postfix + "." + ext));
                        else
                            Files.move(source, source.resolveSibling(Zmieniacz.prefix + Zmieniacz.separator + count + Zmieniacz.separator + Zmieniacz.postfix + "." + ext));
                    } else if (Zmieniacz.czyPostfixLiczy) {
                        if (licznik < 10)
                            Files.move(source, source.resolveSibling(Zmieniacz.prefix +  Zmieniacz.separator + Zmieniacz.infix + Zmieniacz.separator + "0" + count + "." + ext));
                        else
                            Files.move(source, source.resolveSibling(Zmieniacz.prefix +  Zmieniacz.separator + Zmieniacz.infix + Zmieniacz.separator + count + "." + ext));
                    }
                    licznik++;
                }
            }
            Zmieniacz.czyPostfixLiczy = false;
            Zmieniacz.czyInfixLiczy = false;
            Zmieniacz.czyPrefixLiczy = false;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    public void  usunZawartosc() throws IOException {
        tablicaBackup = plik2.listFiles();

        assert tablicaBackup != null;
        for(File f : tablicaBackup)
        {
            String sciezkaBack = out + f.getName();
            Path sourceBack = Paths.get(sciezkaBack);
            Files.delete(sourceBack);
        }
    }

    public void txt(String s)
    {
        System.out.println("info: "+s);
    }

}
