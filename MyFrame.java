import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class MyFrame extends JFrame implements ActionListener{

    JButton przegladaj, przegladaj2, wczytaj, wczytaj2, zatwierdzNazwe, wykonaj;
    JTextField wprowadzSciezke, wprowadzSciezke2, prefixPoleTxt, infixPoleTxt, postfixPoleTxt;
    JFormattedTextField prefixPoleDaty, separatorPole, infixPoleDaty, postfixPoleDaty;
    JLabel inSour, inSourWysw, outSour, separatorLabel, resault, myslnik, prefix, infix, postfix;
    JRadioButton prefixData, prefixNazwa, prefixLicznik, prefixBrak, infixNazwa,
            infixData, infixLicznik, infixBrak, postfixNazwa, postfixData, postfixLicznik, postfixBrak;
    Date aktualnaData = new Date();
    boolean czyPrefix = false, czyInfix = false, czyPoprawnaNazwa = false;
    SimpleDateFormat formatDaty = new SimpleDateFormat("yyyy-MM-dd");

    public MyFrame() throws ParseException {
        super("Super program do zmieniania nazw na jakies fajne uwu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);                                         //zablokuj rozciaganie
        int width = getWidth();
        int height = getHeight();
        setLocation(width/3, height/3);
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(Zmieniacz.hei, Zmieniacz.wei));

//--------------------PANEL WPROWADZ------------------------------------------------------------------------------------

        JPanel sekcjaWprowadz = new JPanel();
        //sekcjaWprowadz.setSize(Zmieniacz.hei, Zmieniacz.wei);
        sekcjaWprowadz.setLayout(new BorderLayout());
        inSour = new JLabel("Wprowadz sciezke katalogu lub wskaz dowolny plik z tego folderu.");
        wprowadzSciezke = new JTextField("C:\\", 30);
        przegladaj = new JButton("Przegladaj");
        przegladaj.addActionListener(this);
        inSourWysw = new JLabel();
        wczytaj = new JButton("Wczytaj");
        wczytaj.addActionListener(this);

        sekcjaWprowadz.add(inSour, BorderLayout.NORTH);
        sekcjaWprowadz.add(wprowadzSciezke, BorderLayout.WEST);
        sekcjaWprowadz.add(wczytaj, BorderLayout.CENTER);
        sekcjaWprowadz.add(przegladaj, BorderLayout.EAST);
        sekcjaWprowadz.add(inSourWysw, BorderLayout.SOUTH);

//--------------------PANEL ZAPISZ--------------------------------------------------------------------------------------

        JPanel sekcjaZapisz = new JPanel();
        sekcjaZapisz.setLayout(new BorderLayout());
        outSour = new JLabel("Wprowadz sciezke dla kopii zapasowej lub wskaz dowolny plik z tego folderu.");
        wprowadzSciezke2 = new JTextField("C:\\", 30);
        przegladaj2 = new JButton("Przegladaj");
        przegladaj2.addActionListener(this);
        wczytaj2 = new JButton("Wczytaj");
        wczytaj2.addActionListener(this);

        sekcjaZapisz.add(outSour, BorderLayout.NORTH);
        sekcjaZapisz.add(wprowadzSciezke2, BorderLayout.WEST);
        sekcjaZapisz.add(wczytaj2, BorderLayout.CENTER);
        sekcjaZapisz.add(przegladaj2, BorderLayout.EAST);

//--------------------PANEL SEPARATOR-----------------------------------------------------------------------------------

        JPanel seksjaSepatator = new JPanel();
        seksjaSepatator.setLayout(new BorderLayout());
        MaskFormatter separatorMask = new MaskFormatter("*");
        separatorPole = new JFormattedTextField(separatorMask);
        separatorPole.setColumns(1);
        separatorPole.setText("_");
        separatorLabel = new JLabel("Wybierz separator oddzielajacy infix od prefixu i postfixu lub zostaw puste ");
        seksjaSepatator.add(separatorLabel, BorderLayout.WEST);
        seksjaSepatator.add(separatorPole, BorderLayout.EAST);

//-----------PREFIX-----------------------------------------------------------------------------------------------------
        JPanel sekcjaPrefix = new JPanel();
        SpringLayout prefixLayout = new SpringLayout();
        prefix = new JLabel("  Prefix: ");
        prefixNazwa = new JRadioButton("nazwa");
        prefixData = new JRadioButton("data");
        prefixLicznik = new JRadioButton("licznik");
        prefixBrak = new JRadioButton("brak", true);

        ButtonGroup prefixGroup = new ButtonGroup();
        prefixGroup.add(prefixNazwa);
        prefixGroup.add(prefixData);
        prefixGroup.add(prefixLicznik);
        prefixGroup.add(prefixBrak);

        prefixPoleTxt = new JTextField("tekst", 10);
        prefixPoleTxt.setEnabled(false);

        MaskFormatter prefixDataMask = new MaskFormatter("****"+"-"+"**"+"-"+"**");
        prefixPoleDaty = new JFormattedTextField(prefixDataMask);
        prefixPoleDaty.setColumns(8);
        prefixPoleDaty.setText(formatDaty.format(aktualnaData));
        prefixPoleDaty.setEnabled(false);

//-----------PREFIX: LISTENER----------------
        prefixNazwa.addActionListener(this);
        prefixData.addActionListener(this);
        prefixLicznik.addActionListener(this);
        prefixBrak.addActionListener(this);

//-----------PREFIX: ADD----------------

        prefixLayout.putConstraint(SpringLayout.WEST, prefix, 5, SpringLayout.WEST, sekcjaPrefix);
        sekcjaPrefix.add(prefix);
        sekcjaPrefix.add(prefixNazwa);
        sekcjaPrefix.add(prefixPoleTxt);
        sekcjaPrefix.add(prefixData);
        sekcjaPrefix.add(prefixPoleDaty);
        sekcjaPrefix.add(prefixLicznik);
        sekcjaPrefix.add(prefixBrak);

//-----------INFIX------------------------------------------------------------------------------------------------------
        JPanel sekcjaInfix = new JPanel();
        SpringLayout infixLayout = new SpringLayout();
        infix = new JLabel("    Infix: ");
        infixNazwa = new JRadioButton("nazwa");
        infixData = new JRadioButton("data");
        infixLicznik = new JRadioButton("licznik");
        infixBrak = new JRadioButton("brak", true);

        ButtonGroup infixGroup = new ButtonGroup();
        infixGroup.add(infixNazwa);
        infixGroup.add(infixData);
        infixGroup.add(infixLicznik);
        infixGroup.add(infixBrak);

        infixPoleTxt = new JTextField("tekst", 10);
        infixPoleTxt.setEnabled(false);

        MaskFormatter infixDataMask = new MaskFormatter("****"+"-"+"**"+"-"+"**");
        infixPoleDaty = new JFormattedTextField(infixDataMask);
        infixPoleDaty.setColumns(8);
        infixPoleDaty.setText(formatDaty.format(aktualnaData));
        infixPoleDaty.setEnabled(false);

//-----------INFIX: LISTENER----------------
        infixNazwa.addActionListener(this);
        infixData.addActionListener(this);
        infixLicznik.addActionListener(this);
        infixBrak.addActionListener(this);

//-----------INFIX: ADD----------------

        infixLayout.putConstraint(SpringLayout.WEST, infix, 5, SpringLayout.WEST, sekcjaInfix);
        sekcjaInfix.add(infix);
        sekcjaInfix.add(infixNazwa);
        sekcjaInfix.add(infixPoleTxt);
        sekcjaInfix.add(infixData);
        sekcjaInfix.add(infixPoleDaty);
        sekcjaInfix.add(infixLicznik);
        sekcjaInfix.add(infixBrak);

//-----------POSTFIX----------------------------------------------------------------------------------------------------
        JPanel sekcjaPostfix = new JPanel();
        SpringLayout postfixLayout = new SpringLayout();
        postfix = new JLabel("Postfix: ");
        postfixNazwa = new JRadioButton("nazwa");
        postfixData = new JRadioButton("data");
        postfixLicznik = new JRadioButton("licznik");
        postfixBrak = new JRadioButton("brak", true);

        ButtonGroup postfixGroup = new ButtonGroup();
        postfixGroup.add(postfixNazwa);
        postfixGroup.add(postfixData);
        postfixGroup.add(postfixLicznik);
        postfixGroup.add(postfixBrak);

        postfixPoleTxt = new JTextField("tekst", 10);
        postfixPoleTxt.setEnabled(false);

        MaskFormatter postDataMask = new MaskFormatter("****"+"-"+"**"+"-"+"**");
        postfixPoleDaty = new JFormattedTextField(postDataMask);
        postfixPoleDaty.setColumns(8);
        postfixPoleDaty.setText(formatDaty.format(aktualnaData));
        postfixPoleDaty.setEnabled(false);

//-----------POSTFIX: LISTENER----------------
        postfixNazwa.addActionListener(this);
        postfixData.addActionListener(this);
        postfixLicznik.addActionListener(this);
        postfixBrak.addActionListener(this);

//-----------POSTFIX: ADD----------------
        postfixLayout.putConstraint(SpringLayout.WEST, postfix, 5, SpringLayout.WEST, sekcjaPostfix);
        sekcjaPostfix.add(postfix);
        sekcjaPostfix.add(postfixNazwa);
        sekcjaPostfix.add(postfixPoleTxt);
        sekcjaPostfix.add(postfixData);
        sekcjaPostfix.add(postfixPoleDaty);
        sekcjaPostfix.add(postfixLicznik);
        sekcjaPostfix.add(postfixBrak);

//-----------PANEL ZATWIERDZ--------------------------------------------------------------------------------------------
        JPanel sekcjaZatwierdz = new JPanel();
      //  sekcjaZatwierdz.setLayout(new BorderLayout());
        resault = new JLabel("Wynik: ");
        zatwierdzNazwe = new JButton("Podgląd");
        zatwierdzNazwe.addActionListener(this);
        sekcjaZatwierdz.add(zatwierdzNazwe);
        sekcjaZatwierdz.add(resault);

        wykonaj = new JButton("Wykonaj");
        wykonaj.addActionListener(this);
        sekcjaZatwierdz.add(wykonaj);
        pack();
        setVisible(true);

        add(sekcjaWprowadz);
        add(sekcjaZapisz);
        add(seksjaSepatator);
        add(sekcjaPrefix);
        add(sekcjaInfix);
        add(sekcjaPostfix);
        add(sekcjaZatwierdz);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == przegladaj){

            FileDialog fd = new FileDialog(this,"Przegladaj",FileDialog.LOAD);
            fd.setVisible(true);
            Zmieniacz.inSource = fd.getDirectory();
            Zmieniacz.outSource = fd.getDirectory();
            String plik = fd.getFile();
            System.out.println("Wybrano plik: " + plik);
            System.out.println("w katalogu: "+ Zmieniacz.inSource);
            System.out.println("Ścieżka: "+ Zmieniacz.inSource + plik);
            wprowadzSciezke.setText(Zmieniacz.inSource);
            wprowadzSciezke2.setText(Zmieniacz.outSource);
            Zmieniacz.licznikPlikow = Objects.requireNonNull(new File(Zmieniacz.inSource).listFiles()).length;
            inSourWysw.setText("liczba plikow: "+ Zmieniacz.licznikPlikow);

        }
        if(source == przegladaj2){
            FileDialog fd =new FileDialog(this,"Zapisz",FileDialog.LOAD);
            fd.setVisible(true);
            Zmieniacz.inSource =fd.getDirectory();
            String plik=fd.getFile();
            System.out.println("Wybrano plik: " + plik);
            System.out.println("w katalogu: "+ Zmieniacz.inSource);
            System.out.println("Ścieżka: "+ Zmieniacz.inSource + plik);
        }
        if(source == wczytaj)
        {
            Zmieniacz.inSource = wprowadzSciezke.getText();
            Zmieniacz.outSource = Zmieniacz.inSource;
            Zmieniacz.licznikPlikow = Objects.requireNonNull(new File(Zmieniacz.inSource).listFiles()).length;
            inSourWysw.setText("liczba plikow: "+ Zmieniacz.licznikPlikow);
            wprowadzSciezke2.setText(Zmieniacz.outSource);

        }
        if(source == wczytaj2)
        {
            Zmieniacz.outSource = wprowadzSciezke2.getText();
            if(!Zmieniacz.outSource.endsWith("\\"))
            {
                Zmieniacz.outSource = Zmieniacz.outSource+"\\";
                wprowadzSciezke2.setText(Zmieniacz.outSource);
            }
            System.out.println("Sciezka kopii: "+Zmieniacz.outSource);
        }
        //--------PREFIX----------------------
        if(source == prefixNazwa)
        {
            prefixPoleTxt.setEnabled(true);
            prefixPoleDaty.setEnabled(false);
        }
        if(source == prefixData)
        {
            prefixPoleTxt.setEnabled(false);
            prefixPoleDaty.setEnabled(true);
        }
        if(source == prefixLicznik)
        {
            prefixPoleTxt.setEnabled(false);
            prefixPoleDaty.setEnabled(false);
        }
        if (source == prefixBrak)
        {
            prefixPoleTxt.setEnabled(false);
            prefixPoleDaty.setEnabled(false);
        }
        //--------INFIX----------------------
        if(source == infixNazwa)
        {
            infixPoleTxt.setEnabled(true);
            infixPoleDaty.setEnabled(false);
        }
        if(source == infixData)
        {
            infixPoleTxt.setEnabled(false);
            infixPoleDaty.setEnabled(true);
        }
        if(source == infixLicznik)
        {
            infixPoleTxt.setEnabled(false);
            infixPoleDaty.setEnabled(false);
        }
        if (source == infixBrak)
        {
            infixPoleTxt.setEnabled(false);
            infixPoleDaty.setEnabled(false);
        }
        //--------POSTFIX----------------------
        if(source == postfixNazwa)
        {
            postfixPoleTxt.setEnabled(true);
            postfixPoleDaty.setEnabled(false);
        }
        if(source == postfixData)
        {
            postfixPoleTxt.setEnabled(false);
            postfixPoleDaty.setEnabled(true);
        }
        if(source == postfixLicznik)
        {
            postfixPoleTxt.setEnabled(false);
            postfixPoleDaty.setEnabled(false);
        }
        if (source == postfixBrak)
        {
            postfixPoleTxt.setEnabled(false);
            postfixPoleDaty.setEnabled(false);
        }

        if(source == zatwierdzNazwe) {

            Zmieniacz.separator = separatorPole.getText();

            if (!prefixLicznik.isSelected() && !infixLicznik.isSelected() && !postfixLicznik.isSelected()) {
                JOptionPane.showMessageDialog(null, "Nazwa musi zwierac licznik!", "Błąd", JOptionPane.ERROR_MESSAGE);
            } else if(prefixLicznik.isSelected() && infixLicznik.isSelected() || prefixLicznik.isSelected() && postfixLicznik.isSelected() || infixLicznik.isSelected() && postfixLicznik.isSelected()){
                JOptionPane.showMessageDialog(null, "Możesz wybrać tylko jeden licznik!", "Błąd", JOptionPane.ERROR_MESSAGE);
                resault.setText("Błąd: za dużo liczników");
            }
            else {
                for (int i = 0; i < 3; i++) {
                    if (prefixNazwa.isSelected() && !czyPrefix) {
                        Zmieniacz.prefix = prefixPoleTxt.getText();
                        czyPrefix = true;
                    } else if (prefixData.isSelected() && !czyPrefix) {
                        Zmieniacz.prefix = prefixPoleDaty.getText();
                        czyPrefix = true;
                    } else if (prefixLicznik.isSelected() && !czyPrefix) {
                        Zmieniacz.prefix = "01";
                        Zmieniacz.czyPrefixLiczy = true;
                        czyPrefix = true;
                    } else if (prefixBrak.isSelected() && !czyPrefix) {
                        Zmieniacz.prefix = "";
                        czyPrefix = true;
                    } else if (infixNazwa.isSelected() && !czyInfix) {
                        Zmieniacz.infix = infixPoleTxt.getText();
                        resault.setText("Nazwa: " + Zmieniacz.prefix + Zmieniacz.separator + Zmieniacz.infix + Zmieniacz.separator+" ");
                        czyInfix = true;
                    } else if (infixData.isSelected() && !czyInfix) {
                        Zmieniacz.infix = infixPoleDaty.getText();
                        resault.setText("Nazwa: " + Zmieniacz.prefix + Zmieniacz.separator + Zmieniacz.infix + Zmieniacz.separator+" ");
                        czyInfix = true;
                    } else if (infixLicznik.isSelected() && !czyInfix) {
                        Zmieniacz.infix = "01";
                        Zmieniacz.czyInfixLiczy = true;
                        resault.setText("Nazwa: " + Zmieniacz.prefix + Zmieniacz.separator + Zmieniacz.infix + Zmieniacz.separator+" ");
                        czyInfix = true;
                    } else if (infixBrak.isSelected() && !czyInfix) {
                        Zmieniacz.infix = "";
                        resault.setText("Nazwa: " + Zmieniacz.prefix + Zmieniacz.separator + "" + Zmieniacz.separator+" ");
                        czyInfix = true;
                    } else if (postfixNazwa.isSelected()) {
                        Zmieniacz.postfix = postfixPoleTxt.getText();
                        resault.setText("Nazwa: " + Zmieniacz.prefix + Zmieniacz.separator + Zmieniacz.infix + Zmieniacz.separator + Zmieniacz.postfix+" ");
                    } else if (postfixData.isSelected()) {
                        Zmieniacz.postfix = postfixPoleDaty.getText();
                        resault.setText("Nazwa: " + Zmieniacz.prefix + Zmieniacz.separator + Zmieniacz.infix + Zmieniacz.separator + Zmieniacz.postfix+" ");
                    } else if (postfixLicznik.isSelected()) {
                        Zmieniacz.postfix = "01";
                        Zmieniacz.czyPostfixLiczy = true;
                        resault.setText("Nazwa: " + Zmieniacz.prefix + Zmieniacz.separator + Zmieniacz.infix + Zmieniacz.separator + Zmieniacz.postfix+" ");
                    } else if (postfixBrak.isSelected()) {
                        Zmieniacz.postfix = "";
                        resault.setText("Nazwa: " + Zmieniacz.prefix + Zmieniacz.separator + Zmieniacz.infix + Zmieniacz.separator + Zmieniacz.postfix+" ");
                    }
                }
                czyInfix = false;
                czyPrefix = false;
                Zmieniacz.sprawdzacz = Zmieniacz.prefix + Zmieniacz.separator + Zmieniacz.infix + Zmieniacz.separator + Zmieniacz.postfix;
                try {
                    sprawdzZnaki(Zmieniacz.sprawdzacz);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        if(source == wykonaj)
        {
            if(czyPoprawnaNazwa) {
                if(prefixLicznik.isSelected() && infixLicznik.isSelected() || prefixLicznik.isSelected() && postfixLicznik.isSelected() || infixLicznik.isSelected() && postfixLicznik.isSelected()){
                    JOptionPane.showMessageDialog(null, "Możesz wybrać tylko jeden licznik!", "Błąd", JOptionPane.ERROR_MESSAGE);
                    resault.setText("Błąd: za dużo liczników");
                    czyPoprawnaNazwa = false;
                    System.out.println("Za duzo licznikow");
                }
                else
                new ZmienNazwe();
            }
            else
                JOptionPane.showMessageDialog(null, "Przed wykonaniem operacji nacisnij Podgląd.", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void sprawdzZnaki(String s) throws IOException {
        for(int i = 0 ; i < s.length() ; i++){
            if(s.charAt(i) == 47 || s.charAt(i) == 92 || s.charAt(i) == 58 || s.charAt(i) == 42 || s.charAt(i) == 63 || s.charAt(i) == 34 || s.charAt(i) == 60
            || s.charAt(i) == 62 || s.charAt(i) == 124)
            {
                JOptionPane.showMessageDialog(null, "Nazwa pliku nie może zawierać żadnego z następujących znaków: \\ / : * ? \" < > |", "Błąd", JOptionPane.ERROR_MESSAGE);
                resault.setText("Błąd: niedozwolony znak.");
                czyPoprawnaNazwa = false;
                break;
            }
            else czyPoprawnaNazwa = true;
        }

    }

}

