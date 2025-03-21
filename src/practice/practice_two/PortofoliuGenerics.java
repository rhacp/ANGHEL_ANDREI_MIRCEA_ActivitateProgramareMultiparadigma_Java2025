package practice.practice_two;

import javax.swing.text.DateFormatter;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class PortofoliuGenerics<T> {

    private List<T> portofoliu = new ArrayList<>();

    public PortofoliuGenerics() {}

    public PortofoliuGenerics(List<T> portofoliu) {
        this.portofoliu = portofoliu;
    }

    public List<T> getPortofoliu() {
        return portofoliu;
    }

    public void setPortofoliu(List<T> portofoliu) {
        this.portofoliu = portofoliu;
    }

    public void adaugaInstrument(T instrument) {
        portofoliu.add(instrument);
    }

    public T getInstrument(int index) {
        if (index >= 0 && index < portofoliu.size()) {
            return portofoliu.get(index);
        }

        return null;
    }

    public double getValoareInstrumente() {
        double value = 0;
        List<Instrument> listaInstrumente = (List<Instrument>) portofoliu;

        for (Instrument instrument : listaInstrumente) {
            value += instrument.valoare();
        }

        return value;
    }

    public void printFirstDateForEachInstrumentOperation() {
        List<Instrument> listaInstrumente = (List<Instrument>) portofoliu;

        for (int i = 0; i < listaInstrumente.size(); i++) {
            List<Instrument.Operatiune> copieListaOperatiuni = listaInstrumente.get(i).getOperatiuni();
            copieListaOperatiuni.sort(Instrument.Operatiune::compareTo);

            System.out.println(copieListaOperatiuni.get(0).getData());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PortofoliuGenerics<?> that = (PortofoliuGenerics<?>) o;
        return Objects.equals(portofoliu, that.portofoliu);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(portofoliu);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String separator = ",\n";

        sb.append("[\n");
        for (T instrument : portofoliu) {
            sb.append(instrument.toString()).append(separator);
        }

        sb.delete(sb.length() - 2, sb.length() - 1);

        String result = sb.toString().replaceAll("\n", "\n    ");
        result = result.substring(0, result.length() - 4);
        result += "]";

        return result;
    }

    public static void salvarePortofoliu(String caleFisier, PortofoliuGenerics portofoliuGenerics) {
        String toSave = portofoliuGenerics.toString();

//        try {
//            PrintWriter pw = new PrintWriter(caleFisier);
//            pw.write(toSave);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(caleFisier));
            writer.write(toSave);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static PortofoliuGenerics<Instrument> incarcarePortofoliu(String caleFisier) {
        PortofoliuGenerics<Instrument> poftofoliuDeReturnat = new PortofoliuGenerics<>();
        String helper = "";

        File file = new File(caleFisier);
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNext()) {
                helper += sc.nextLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        helper = helper.replaceAll("\\s+","");
        System.out.println(helper);
        int counterOne = 0;
        int counterTwo = 0;
        int helperOne = 0;
        int helperTwo = 0;
        int counterr = 0;

        String symbol = "";
        String tip = "";
        String data = "";
        double price = 0;
        int cantitate = 0;
        double procentDividente = 0;

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.M.d");
        ArrayList<Instrument.Operatiune> listaOperatiuni = new ArrayList<Instrument.Operatiune>();

        for (int i = 3; i < helper.length() - 1; i++) {

            if (helper.charAt(i) == ':') {
                counterOne = i + 2;
                helperOne = 1;
            }

            if (helper.charAt(i) == ',' || helper.charAt(i) == '}') {
                counterTwo = i - 1;
                helperTwo = 1;
            }

            if (helperOne == 1 && helperTwo == 1) {
                switch (counterr) {
                    case 0 -> symbol = helper.substring(counterOne, counterTwo);
                    case 1 -> tip = helper.substring(counterOne, counterTwo);
                    case 2 -> data = helper.substring(counterOne, counterTwo);
                    case 3 -> price = Double.parseDouble(helper.substring(counterOne, counterTwo));
                    case 4 -> cantitate = Integer.parseInt(helper.substring(counterOne, counterTwo));
                    case 5 -> procentDividente = Double.parseDouble(helper.substring(counterOne, counterTwo));
                }
                counterr++;
                helperOne = 0;
                helperTwo = 0;
            }

            if ((helper.charAt(i) == ',' && helper.charAt(i - 1) == '}' && helper.charAt(i - 2) == '\"') || (helper.charAt(i) == ',' && helper.charAt(i - 1) == '}' && helper.charAt(i - 2) == ']' && helper.charAt(i - 3) == '}') || (helper.charAt(i) == ',' && helper.charAt(i - 1) == ']' && helper.charAt(i - 2) == '}' && helper.charAt(i - 3) == '\"')) {
                TipOperatiune tipOperatiune = null;
                if (tip.equals("VANZARE")) {
                    tipOperatiune = TipOperatiune.VANZARE;
                } else if (tip.equals("CUMPARARE")) {
                    tipOperatiune = TipOperatiune.CUMPARARE;
                }

                Instrument.Operatiune operatiune = new Instrument.Operatiune(tipOperatiune, LocalDate.parse(data, dateTimeFormatter), price, cantitate);

                tip = "";
                data = "";
                price = 0;
                cantitate = 0;

                listaOperatiuni.add(operatiune);

                if (helper.charAt(i + 1) != '\"') {
                    counterr = 1;
                }
                helperTwo = 0;
            }

            if ((helper.charAt(i) == ',' && helper.charAt(i - 1) == '}' && helper.charAt(i - 2) == ']') || i == helper.length() - 2) {
                if (procentDividente != 0) {
                    Actiune actiune = new Actiune(symbol, listaOperatiuni, procentDividente);
                    poftofoliuDeReturnat.adaugaInstrument(actiune);
                } else {
                    Instrument instrument = new Instrument(symbol, listaOperatiuni);
                    poftofoliuDeReturnat.adaugaInstrument(instrument);
                }

                listaOperatiuni = new ArrayList<Instrument.Operatiune>();
                counterr = 0;

                symbol = "";
                procentDividente = 0;
            }
        }

        return poftofoliuDeReturnat;
    }
}
